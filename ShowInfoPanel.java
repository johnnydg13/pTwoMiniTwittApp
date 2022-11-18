package jgProjectMiniTwitter;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class ShowInfoPanel extends MiniTwitterApp {
	
	private static final long serialVersionUID = 1L;
	private JButton userTotalButton;
	private JButton groupTotalButton;
	private JButton messagesTotalButton;
	private JButton positivePercentageButton;
	private JPanel treePanel;

	/**
	 * Create the panel.
	 */
	protected ShowInfoPanel(JPanel treePanel) {
		
		super();
		this.treePanel = treePanel;
        initComponents();
        addComponents();

	}
	
	protected void addComponents() {
        addComponent(this, userTotalButton, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(this, groupTotalButton, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(this, messagesTotalButton, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(this, positivePercentageButton, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }

    protected void initComponents() {
        userTotalButton = new JButton("Show User Total");
        initializeUserTotalButtonActionListener();

        groupTotalButton = new JButton("Show Group Total");
        initializeGroupTotalButtonActionListener();

        messagesTotalButton = new JButton("Show Messages Total");
        initializeMessagesTotalButtonActionListener();

        positivePercentageButton = new JButton("Show Positive Percentage");
        initializePositivePercentageButtonActionListener();
    }

    
    protected DefaultMutableTreeNode getSelectedNode() {
        JTree tree = ((TreePanel) treePanel).getTree();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
        if (!((TreePanel) treePanel).getRoot().equals(selectedNode)) {
            selectedNode = (DefaultMutableTreeNode) selectedNode.getUserObject();
        }

        return selectedNode;
    }


    protected void initializeUserTotalButtonActionListener() {
        userTotalButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
           
                DefaultMutableTreeNode selectedNode = getSelectedNode();

                UserTotalVisitor visitor = new UserTotalVisitor();
                ((User) selectedNode).accept(visitor);
                String message = "Total number of inidividual users within "
                        + ((User) selectedNode).getID() + ": "
                        + Integer.toString(visitor.visitUser(((User) selectedNode)));

                InfoDialogBox popUp = new InfoDialogBox(((User) selectedNode).getID() + " information",
                        message, JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

  
    protected void initializeGroupTotalButtonActionListener() {
        groupTotalButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
         
                DefaultMutableTreeNode selectedNode = getSelectedNode();

                GroupTotalVisitor visitor = new GroupTotalVisitor();
                ((User) selectedNode).accept(visitor);
                String message = "Total number of groups within "
                        + ((User) selectedNode).getID() + ": "
                        + Integer.toString(visitor.visitUser(((User) selectedNode)));

                InfoDialogBox popUp = new InfoDialogBox(((User) selectedNode).getID() + " information",
                        message, JOptionPane.INFORMATION_MESSAGE);

            }
        });
    }

 
    protected void initializeMessagesTotalButtonActionListener() {
        messagesTotalButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
             
                DefaultMutableTreeNode selectedNode = getSelectedNode();

                MessagesTotalVisitor visitor = new MessagesTotalVisitor();
                ((User) selectedNode).accept(visitor);
                String message = "Total number of messages sent by "
                        + ((User) selectedNode).getID() + ": "
                        + Integer.toString(visitor.visitUser(((User) selectedNode)));

                InfoDialogBox popUp = new InfoDialogBox(((User) selectedNode).getID() + " information",
                        message, JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

  
    protected void initializePositivePercentageButtonActionListener() {
        positivePercentageButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode selectedNode = getSelectedNode();

                PositiveTotalVisitor positiveCountVisitor = new PositiveTotalVisitor();
                ((User) selectedNode).accept(positiveCountVisitor);
                int positiveCount = positiveCountVisitor.visitUser(((User) selectedNode));

                MessagesTotalVisitor messageCountVisitor = new MessagesTotalVisitor();
                ((User) selectedNode).accept(messageCountVisitor);
                int messageCount = messageCountVisitor.visitUser(((User) selectedNode));

                double percentage = 0;
                if (messageCount > 0) {
                    percentage = ((double) positiveCount / messageCount) * 100;
                }
                String percentageString = String.format("%.2f", percentage);

                String message = "Percentage of positive messages sent by "
                        + ((User) selectedNode).getID() + ": "
                        + percentageString + "%";

                InfoDialogBox popUp = new InfoDialogBox(((User) selectedNode).getID() + " information",
                        message, JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }


}
