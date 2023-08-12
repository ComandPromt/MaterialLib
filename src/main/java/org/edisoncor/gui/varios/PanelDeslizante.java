package org.edisoncor.gui.varios;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class PanelDeslizante extends JScrollPane {

	private JPanel panel;

	public PanelDeslizante() {

		super();

		panel = new JPanel();

		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

		panel.setLayout(new java.awt.GridLayout());

		setViewportView(panel);

	}

	public void addPanel(JPanel panel) {

		this.panel.add(panel);

		this.panel.setSize(getWidth() * 2, getHeight());

	}

}
