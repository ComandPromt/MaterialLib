package com.panels.others;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class AddDeleteListPanel extends JPanel {

	public AddDeleteListPanel() {

		setLayout(new GridLayout(2, 2));

		JPanel panel = new JPanel();

		add(panel);

		add(panel);

	}

}
