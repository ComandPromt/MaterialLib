package com.panels.others;

import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VerySimplePanel extends JPanel {

	public VerySimplePanel() {

		setLayout(new GridLayout());

	}

	public VerySimplePanel(JComponent component) {

		this();

		add(component);

	}

	public VerySimplePanel(LayoutManager layout, JComponent component) {

		setLayout(layout);

		add(component);

	}

}
