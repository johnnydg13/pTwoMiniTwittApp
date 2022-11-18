package jgProjectMiniTwitter;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class TreePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private DefaultMutableTreeNode rootNode;
	private DefaultTreeModel treeModel;
	private JTree tree;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
		protected TreePanel(DefaultMutableTreeNode root) {
		
		super(new GridLayout(1,0));
		rootNode = root;
        initComponents();
        addComponents();
	
	    }

	
	    protected JTree getTree() {
	        return this.tree;
	    }

	    
	    protected DefaultMutableTreeNode getRoot() {
	        return this.rootNode;
	    }

	  
	    protected void addGroupUser(DefaultMutableTreeNode child) {
	        DefaultMutableTreeNode parentNode = null;
	        TreePath parentPath = tree.getSelectionPath();

	        if (parentPath == null) {
	            parentNode = rootNode;
	        } else {
	            parentNode = (DefaultMutableTreeNode) parentPath.getLastPathComponent();
	        }

	        if (parentNode.getUserObject().getClass() == SingleUser.class) {
	            parentNode = (DefaultMutableTreeNode) parentNode.getParent();
	        }
	        addUser(parentNode, child, true);
	    }


	    protected void addSingleUser(DefaultMutableTreeNode child) {
	        DefaultMutableTreeNode parentNode = null;
	        TreePath parentPath = tree.getSelectionPath();

	        if (parentPath == null) {
	            parentNode = rootNode;
	        } else {
	            parentNode = (DefaultMutableTreeNode) parentPath.getLastPathComponent();
	        }

	        if (parentNode.getUserObject().getClass() == SingleUser.class) {
	            parentNode = (DefaultMutableTreeNode) parentNode.getParent();
	        }
	        addUser(parentNode, child, true);
	    }

	
	    protected void addUser(DefaultMutableTreeNode parent, DefaultMutableTreeNode child, boolean shouldBeVisible) {
	        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

	        if (parent == null) {
	            parent = rootNode;
	        }

	        treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

	        if (shouldBeVisible) {
	            tree.scrollPathToVisible(new TreePath(childNode.getPath()));
	        }

	        if (parent.getClass() != GroupUser.class) {
	            parent = (DefaultMutableTreeNode) parent.getUserObject();
	        }
	        ((GroupUser) parent).addUserInGroup((User) child);
	    }

	    protected void addComponents() {
	        add(scrollPane);
	    }

	    protected void initComponents() {
	        treeModel = new DefaultTreeModel(rootNode);
	        treeModel.addTreeModelListener(new MyTreeModelListener());

	        tree = new JTree(treeModel);
	        formatTree();

	        scrollPane = new JScrollPane(tree);
	    }

	    protected void formatTree() {
	        tree.setEditable(true);
	        tree.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);
	        tree.setShowsRootHandles(true);
	        tree.setSelectionRow(0);
	    }

	  
	    protected class MyTreeModelListener implements TreeModelListener {
	        public void treeNodesChanged(TreeModelEvent e) {
	        }
	        public void treeNodesInserted(TreeModelEvent e) {
	        }
	        public void treeNodesRemoved(TreeModelEvent e) {
	        }
	        public void treeStructureChanged(TreeModelEvent e) {
	        }
	    }


	

}
