package jgProjectMiniTwitter;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class OpenUserViewPanel extends MiniTwitterApp {
	
	private static final long serialVersionUID = 1L;
	private JButton openUserViewButton;
    private JPanel aPanel;

    private JPanel treePanel;
    private Map<String, Observer> allUsers;
    private Map<String, JPanel> openPanels;

	/**
	 * Create the panel.
	 */
	protected OpenUserViewPanel(JPanel treePanel, Map<String, Observer> allUsers) {
		
		 super();
		 this.treePanel = treePanel;
	     this.allUsers = allUsers;
	     initComponents();
	     addComponents();

	}
	
	protected void addComponents() {
        addComponent(this, openUserViewButton, 1, 2, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(this, aPanel, 1, 3, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }

    protected void initComponents() {
        openPanels = new HashMap<String, JPanel>();

        openUserViewButton = new JButton("Open User View");
        initializeOpenUserViewActionListener();
        aPanel = new JPanel();
    }

   
    protected DefaultMutableTreeNode getSelectedNode() {
        JTree tree = ((TreePanel) treePanel).getTree();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
        if (!((TreePanel) treePanel).getRoot().equals(selectedNode)) {
            selectedNode = (DefaultMutableTreeNode) selectedNode.getUserObject();
        }

        return selectedNode;
    }

  
    protected void initializeOpenUserViewActionListener() {
        openUserViewButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // get User selected in TreePanel
                DefaultMutableTreeNode selectedNode = getSelectedNode();

                // open user view UI on click, only open one window per User
                if (!allUsers.containsKey(((User) selectedNode).getID())) {
                    InfoDialogBox dialogBox = new InfoDialogBox("Error:",
                            "User does not exists!",
                            JOptionPane.ERROR_MESSAGE);
                } else if (selectedNode.getClass() == GroupUser.class) {
                    InfoDialogBox dialogBox = new InfoDialogBox("Error:",
                            "Cannot open user view.",
                            JOptionPane.ERROR_MESSAGE);
                } else if (openPanels.containsKey(((User) selectedNode).getID())) {
                    InfoDialogBox dialogBox = new InfoDialogBox("Error:",
                            "User view already open for " + ((User) selectedNode).getID() + "!",
                            JOptionPane.ERROR_MESSAGE);
                } else if (selectedNode.getClass() == SingleUser.class) {
                    UserViewPanel userView = new UserViewPanel(allUsers, openPanels, selectedNode);
                    openPanels.put(((User) selectedNode).getID(), userView);
                }
            }
        });
    }


}
