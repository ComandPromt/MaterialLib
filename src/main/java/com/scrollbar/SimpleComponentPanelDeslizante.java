package com.scrollbar;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")

public class SimpleComponentPanelDeslizante extends JPanel {

	public MaterialPanelDeslizante materialPanel;

	public SimpleComponentPanelDeslizante(JPanel panel, Color select, Color background) {

		if (select == null) {

			select = new Color(48, 144, 216);

		}

		if (background == null) {

			background = Color.WHITE;

		}

		setLayout(new GridLayout(0, 1, 0, 0));

		add(panel);

		materialPanel = new MaterialPanelDeslizante(panel, select, background);

		add(materialPanel);

	}

}
