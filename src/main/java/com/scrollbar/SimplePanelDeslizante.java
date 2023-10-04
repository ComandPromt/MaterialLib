package com.scrollbar;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SimplePanelDeslizante extends JPanel {

	public MaterialPanelDeslizante materialPanel;

	public SimplePanelDeslizante(JPanel panel, Color select, Color background, final String text,
			final String separador) {

		if (select == null) {

			select = new Color(48, 144, 216);

		}

		if (background == null) {

			background = Color.WHITE;

		}

		setLayout(new GridLayout(0, 1, 0, 0));

		add(panel);

		materialPanel = new MaterialPanelDeslizante(panel, select, background);

		JList<String> list = new JList<String>();
		try {
			list.setModel(new javax.swing.AbstractListModel<String>() {

				String[] strings = text.split(separador);

				public int getSize() {

					return strings.length;

				}

				public String getElementAt(int i) {

					return strings[i];

				}

			});

		}

		catch (Exception e) {

		}

		add(materialPanel);

		materialPanel.setViewportView(list);

	}

}
