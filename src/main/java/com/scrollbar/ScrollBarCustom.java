package com.scrollbar;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollBar;

@SuppressWarnings("serial")

public class ScrollBarCustom extends JScrollBar {

	public ScrollBarCustom(Color foreground, Color background) {

		setUI(new ModernScrollBarUI());

		setPreferredSize(new Dimension(8, 8));

		if (foreground == null) {

			foreground = new Color(48, 144, 216);

		}

		if (background == null) {

			background = Color.WHITE;

		}

		setForeground(foreground);

		setBackground(background);

	}

}
