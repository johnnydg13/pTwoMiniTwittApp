package jgProjectMiniTwitter;

import java.util.HashMap;
import java.util.Map;

public class GroupUser extends User{
	
	private static final long serialVersionUID = 1L;
	private Map<String,User> groupUsers;

    
    protected GroupUser(String id) {
        super(id);
        groupUsers = new HashMap<String,User>();
    }

    protected Map<String,User> getGroupUsers() {
        return groupUsers;
    }

   
    protected User addUserInGroup(User user) {
        if (!this.contains(user.getID())) {
            this.groupUsers.put(user.getID(), user);
        }
        return this;
    }

  
    @Override
	public boolean contains(String id) {
        boolean contains = false;
        for (User user : groupUsers.values()) {
            if (user.contains(id)) {
                contains = true;
            }
        }
        return contains;
    }

    
    @Override
	public int getSingleUserCount() {
        int count = 0;
        for (User user : this.groupUsers.values()) {
            count += user.getSingleUserCount();
        }
        return count;
    }


    @Override
	public int getGroupUserCount() {
        int count = 0;
        for (User user : this.groupUsers.values()) {
            if (user.getClass() == GroupUser.class) {
                ++count;
                count += user.getGroupUserCount();
            }
        }
        return count;
    }

  
    @Override
    protected int getMessageCount() {
        int msgCount = 0;
        for (User user : this.groupUsers.values()) {
            msgCount += user.getMessageCount();
        }
        return msgCount;
    }

 
    @Override
	public void update(Subject subject) {
        for (User user : groupUsers.values()) {
            ((Observer) user).update(subject);
        }
    }

  

    @Override
    protected void accept(Visitor visitor) {
        for (User user : groupUsers.values()) {
            user.accept(visitor);
        }
        visitor.visitGroupUser(this);
    }


    protected boolean containsGroupUser() {
        boolean containsGroup = false;
        for (User user : this.groupUsers.values()) {
            if (user.getClass() == GroupUser.class) {
                containsGroup = true;
            }
        }
        return containsGroup;
    }


}
