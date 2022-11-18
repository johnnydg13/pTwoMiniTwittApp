package jgProjectMiniTwitter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

public class AdminControlPanel extends MiniTwitterApp {
	
	private static final long serialVersionUID = 1L;
	private static AdminControlPanel INSTANCE;
	private static JFrame frame;
	private JPanel treePanel;
	private JPanel addUserPanel;
	private JPanel openUserViewPanel;
	private JPanel showInfoPanel;
	private DefaultMutableTreeNode root;
	private Map<String, Observer> allUsers;

	protected static AdminControlPanel getInstance() 
	{
	        if (INSTANCE == null) 
	        {
	            synchronized (Driver.class) 
	            {
	                if (INSTANCE == null) {
	                    INSTANCE = new AdminControlPanel();
	                }
	            }
	        }
	        return INSTANCE;
	}

	/**
	 * Create the panel.
	 */
	protected AdminControlPanel() {
		
		 super();
		 initComponents();
	     addComponents();

	}
	
	 
	
	 protected void initComponents() 
	 {
	        frame = new JFrame("Mini Twitter App");
	        frame.setLayout(new GridBagLayout());
	        frame.setSize(800, 600);
	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        allUsers = new HashMap<String, Observer>();
	        root = new GroupUser("Root");
	        allUsers.put(((User) root).getID(), (Observer) this.root);

	        treePanel = new TreePanel(root);
	        addUserPanel = new CreateUserPanel(treePanel, allUsers);
	        openUserViewPanel = new OpenUserViewPanel(treePanel, allUsers);
	        showInfoPanel = new ShowInfoPanel(treePanel);

	        
	 }
	 
	 protected void addComponents() 
	 {
	        addComponent(frame, treePanel, 0, 0, 1, 6, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	        addComponent(frame, addUserPanel, 1, 0, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	        addComponent(frame, openUserViewPanel, 1, 2, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	        addComponent(frame, showInfoPanel, 1, 4, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	 }


}
