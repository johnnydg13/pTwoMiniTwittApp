package jgProjectMiniTwitter;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;

public class CreateUserPanel extends MiniTwitterApp {
	
	 private static final long serialVersionUID = 1L;
	 private JPanel treePanel;
	 private Map<String, Observer> allUsers;

	 private JButton addUserButton;
	 private JButton addGroupButton;
	 private JButton clearAllButton;
	 private JTextField userId;
	 private JTextField groupId;

	/**
	 * Create the panel.
	 */
	protected CreateUserPanel(JPanel treePanel, Map<String, Observer> allUsers) 
	{
		
		super();
	    this.treePanel = treePanel;
	    this.allUsers = allUsers;

	    initComponents();
	    addComponents();
	}

	

	 protected void addComponents() 
	 {
		 addComponent(this, userId, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	     addComponent(this, addUserButton, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	     addComponent(this, groupId, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	     addComponent(this, addGroupButton, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	     addComponent(this, clearAllButton, 0, 2, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	 }

	 protected void initComponents() 
	 {
	     userId = new JTextField("User ID");
	     groupId = new JTextField("Group ID");

	     addUserButton = new JButton("Add User");
	     initializeAddUserButtonActionListener();

	     addGroupButton = new JButton("Add Group");
	     initializeAddGroupButtonActionListener();
	        
	     clearAllButton = new JButton("Delet All Users");
	     initializedClearAllButtonActionListener();
	 }

	
	protected void initializeAddUserButtonActionListener() 
	{
	     addUserButton.addActionListener(new ActionListener() {

	     @Override
		public void actionPerformed(ActionEvent arg0) 
	     {
	   
	         if (allUsers.containsKey(userId.getText())) 
	         {
	        	 InfoDialogBox dialogBox = new InfoDialogBox("Error:",
	                            "User already exists!",
	                            JOptionPane.ERROR_MESSAGE);
	         } 
	         else 
	         {
	             Observer child = new SingleUser(userId.getText());

	             allUsers.put(((User) child).getID(), child);
	             ((TreePanel) treePanel).addSingleUser((DefaultMutableTreeNode) child);
	         }
	      }
	     
	    });
	 }

	 protected void initializeAddGroupButtonActionListener() 
	 {
	        addGroupButton.addActionListener(new ActionListener() 
	        {

	            @Override
	            public void actionPerformed(ActionEvent e) 
	            {
	              
	                if (allUsers.containsKey(groupId.getText())) {
	                    InfoDialogBox dialogBox = new InfoDialogBox("Error:",
	                            "User already exists.",
	                            JOptionPane.ERROR_MESSAGE);
	                } 
	                else 
	                {
	                    Observer child = new GroupUser(groupId.getText());

	                    allUsers.put(((User) child).getID(), child);
	                    ((TreePanel) treePanel).addGroupUser((DefaultMutableTreeNode) child);
	                }
	            }
	        });
	    }

	    protected void initializedClearAllButtonActionListener() {
	        clearAllButton.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent arg0) 
	            {
	                
	            	allUsers.clear();
	            	InfoDialogBox dialogBox = new InfoDialogBox("Attention:",
                            "All Users Cleared!",
                            JOptionPane.ERROR_MESSAGE);
	            	
	            }
	        });
	    }



}
