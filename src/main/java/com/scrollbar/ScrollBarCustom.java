package com.scrollbar;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollBar;

@SuppressWarnings("serial")

public class ScrollBarCustom extends JScrollBar {

	private ModernScrollBarUI bar;

	public void setScrollSize(int size) {

		bar.setThumbSize(size);

	}

	public void setBackground2(Color color) {

		bar.setFondo(color);

	}

	public ScrollBarCustom(Color foreground, Color background, Color background2, int size) {

		if (background2 == null) {

			background2 = Color.WHITE;

		}

		if (size < 1) {

			size = 40;

		}

		bar = new ModernScrollBarUI(background2, size);

		setUI(bar);

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
