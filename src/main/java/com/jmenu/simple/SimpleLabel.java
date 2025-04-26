package com.jmenu.simple;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
class SimpleLabel extends JLabel {

	public SimpleLabel(String text, JPanel panel, JComponent componente) {

		setText(text);

		setHorizontalAlignment(SwingConstants.CENTER);

		addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				panel.removeAll();

				panel.add(componente);

				panel.revalidate();

				panel.repaint();

			}

		});

	}

}
