package jgProjectMiniTwitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingleUser extends User implements Subject{
	
	private static final long serialVersionUID = 1L;

	private static final List<String> POSITIVE_WORDS = Arrays.asList("good", "great", "excellent", "etc.");

    private Map<String, Observer> followers;
    private Map<String, Subject> following;
    private List<String> newsFeed;

    private String latestMessage;
    private int positiveMessageCount;

    protected SingleUser(String id) {
        super(id);
        followers = new HashMap<String, Observer>();
        followers.put(this.getID(), this);
        following = new HashMap<String, Subject>();
        newsFeed = new ArrayList<String>();
    }

    
    protected Map<String, Observer> getFollowers() {
        return followers;
    }

    
    protected Map<String, Subject> getFollowing() {
        return following;
    }

   
    protected List<String> getNewsFeed() {
        return newsFeed;
    }

    
    protected void sendMessage(String message) {
        this.latestMessage = message;
        this.setMessageCount(this.getMessageCount() + 1);

        if (isPositiveMessage(message)) {
            ++positiveMessageCount;
        }

        notifyObservers();
    }

    
    protected String getLatestMessage() {
        return this.latestMessage;
    }

   
    protected int getPositiveMessageCount() {
        return positiveMessageCount;
    }

    
    @Override
	public boolean contains(String id) {
        return this.getID().equals(id);
    }

  
    @Override
	public int getGroupUserCount() {
        return 0;
    }

  
    @Override
	public int getSingleUserCount() {
        return 1;
    }

  
    @Override
	public void update(Subject subject) {
        newsFeed.add(0, (((SingleUser) subject).getID() + ": " + ((SingleUser) subject).getLatestMessage()));
    }

 
    @Override
	public void attach(Observer observer) {
        addFollower(observer);
    }


    @Override
    public void notifyObservers() {
        for (Observer obs : followers.values()) {
            obs.update(this);
        }
    }


    @Override
    protected void accept(Visitor visitor) {
        visitor.visitSingleUser(this);
    }

  
    protected void addFollower(Observer user) {
        this.getFollowers().put(((User) user).getID(), user);
        ((SingleUser) user).addUserToFollow(this);
    }

    
    protected void addUserToFollow(Subject toFollow){
        if (toFollow.getClass() == SingleUser.class) {
            getFollowing().put(((User) toFollow).getID(), toFollow);
        }
    }


    protected boolean isPositiveMessage(String message) {
        boolean positive = false;
        message = message.toLowerCase();
        for (String word : POSITIVE_WORDS) {
            if (message.contains(word)) {
                positive = true;
            }
        }
        return positive;
    }


}
