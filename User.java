package jgProjectMiniTwitter;

import javax.swing.tree.DefaultMutableTreeNode;

public abstract class User extends DefaultMutableTreeNode implements Observer {
	
	  private static final long serialVersionUID = 1L;
	  private String id;
	  private int messageCount;
	  public abstract boolean contains(String id);
	  public abstract int getSingleUserCount();
	  public abstract int getGroupUserCount();

	  protected User(String id) {
	        super(id);
	        this.id = id;
	        this.setMessageCount(0);
	  }

	  
	  protected String getID() {
	        return id;
	  }

	  protected int getMessageCount() {
	        return messageCount;
	  }

	  protected void setMessageCount(int messageCount) {
	        this.messageCount = messageCount;
	  }


	  protected abstract void accept(Visitor visitor);

}
