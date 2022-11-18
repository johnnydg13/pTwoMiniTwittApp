package jgProjectMiniTwitter;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

public abstract class MiniTwitterApp extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final Insets insets = new Insets(0, 0, 0, 0);

	/**
	 * Create the panel.
	 */
	protected MiniTwitterApp() {
		
		 super(new GridBagLayout());
    }

    protected static void addComponent(Container container, Component component, int gridx, int gridy,
            int gridwidth, int gridheight, int anchor, int fill) {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
            anchor, fill, insets, 0, 0);
        container.add(component, gbc);
    }




}
