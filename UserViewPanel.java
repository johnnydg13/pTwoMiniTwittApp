package jgProjectMiniTwitter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.DefaultMutableTreeNode;

public class UserViewPanel extends MiniTwitterApp {
	
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private GridBagConstraints constraints;
	private JTextField toFollowTextField;
	private JTextArea tweetMessageTextArea;
	private JTextArea currentFollowingTextArea;
	private JTextArea newsFeedTextArea;
	private JScrollPane tweetMessageScrollPane;
	private JScrollPane currentFollowingScrollPane;
	private JScrollPane newsFeedScrollPane;
	private JButton followUserButton;
	private JButton postTweetButton;
	private Subject user;
	private Map<String, Observer> allUsers;
	private Map<String, JPanel> openPanels;

	/**
	 * Create the panel.
	 */
	protected UserViewPanel(Map<String, Observer> allUsers, Map<String, JPanel> allPanels, DefaultMutableTreeNode user) {
		
		 super();
		 this.user = (Subject) user;
	     this.allUsers = allUsers;
	     this.openPanels = allPanels;
	     initComponents();
	     addComponents();

		}
	
	 protected void addComponents() {
	        addComponent(frame, toFollowTextField, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	        addComponent(frame, followUserButton, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	        addComponent(frame, currentFollowingTextArea, 0, 1, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	        addComponent(frame, tweetMessageScrollPane, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	        addComponent(frame, postTweetButton, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	        addComponent(frame, newsFeedScrollPane, 0, 3, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	 }

	  protected void initComponents() 
	  {
	        frame = new JFrame("User View");
	        formatFrame();

	        constraints = new GridBagConstraints();
	        constraints.ipady = 100;

	        toFollowTextField = new JTextField("User ID");
	        followUserButton = new JButton("Follow User");
	        initializeFollowUserButtonActionListener();

	        currentFollowingTextArea = new JTextArea("Current Following: ");
	        formatTextArea(currentFollowingTextArea);
	        currentFollowingScrollPane = new JScrollPane(currentFollowingTextArea);
	        currentFollowingScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

	        tweetMessageTextArea = new JTextArea("Tweet Message");
	        tweetMessageScrollPane = new JScrollPane(tweetMessageTextArea);
	        tweetMessageScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

	        postTweetButton = new JButton("Post Tweet");
	        initializePostTweetButtonActionListener();

	        newsFeedTextArea = new JTextArea("News Feed: ");
	        formatTextArea(newsFeedTextArea);
	        newsFeedScrollPane = new JScrollPane(newsFeedTextArea);
	        newsFeedScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	        updateCurrentFollowingTextArea();
	        updateNewsFeedTextArea();
	    }

	    protected void formatTextArea(JTextArea textArea) {
	        textArea.setLineWrap(true);
	        textArea.setWrapStyleWord(true);
	        textArea.setRows(8);
	        textArea.setEditable(false);
	    }

	    protected void formatFrame() {
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.setLayout(new GridBagLayout());
	        frame.setSize(800, 400);
	        frame.setVisible(true);
	        frame.setTitle(((User) user).getID());
	        frame.addWindowListener(new WindowAdapter() {

	            public void windowClosing(WindowEvent e) {
	                openPanels.remove(((User) user).getID());
	            }
	        });
	    }

	  
	    protected void updateNewsFeedTextArea() {
	        String list = "News Feed: \n";

	        for (String news : ((SingleUser) user).getNewsFeed()) {
	            list += " - " + news + "\n";
	        }

	        newsFeedTextArea.setText(list);
	        newsFeedTextArea.setCaretPosition(0);
	    }

	 
	    protected void updateCurrentFollowingTextArea() {
	        String list = "Current Following: \n";
	        for (String following : ((SingleUser) user).getFollowing().keySet()) {
	            list += " - " + following + "\n";
	        }
	        currentFollowingTextArea.setText(list);
	        currentFollowingTextArea.setCaretPosition(0);
	    }


	    protected void initializePostTweetButtonActionListener() {
	        postTweetButton.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                ((SingleUser) user).sendMessage(tweetMessageTextArea.getText());

	                for (JPanel panel : openPanels.values()) {
	                    ((UserViewPanel) panel).updateNewsFeedTextArea();
	                }
	            }
	        });
	    }


	    protected void initializeFollowUserButtonActionListener() {
	        followUserButton.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                User toFollow = (User) allUsers.get(toFollowTextField.getText());

	                if (!allUsers.containsKey(toFollowTextField.getText())) {
	                    InfoDialogBox dialogBox = new InfoDialogBox("Error!",
	                            "User does not exist!",
	                            JOptionPane.ERROR_MESSAGE);

	                } else if (toFollow.getClass() == GroupUser.class) {
	                    InfoDialogBox dialogBox = new InfoDialogBox("Error!",
	                            "Cannot follow a group!",
	                            JOptionPane.ERROR_MESSAGE);
	                } else if (allUsers.containsKey(toFollowTextField.getText())) {
	                    ((Subject) toFollow).attach((Observer) user);
	                }

	                updateCurrentFollowingTextArea();
	            }
	        });
	    }

}
