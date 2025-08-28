package com.jmenu.simple;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import com.label.others.SimpleLabel;

@SuppressWarnings("serial")
class IndexedLabel extends SimpleLabel {

	public IndexedLabel(String text, int index, int size, JComponent component, HorizontalMenu panel) {

		super(text);

		addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				if (index < size) {

					panel.setContentPanel(component);

				}

			}

		});

	}

}
