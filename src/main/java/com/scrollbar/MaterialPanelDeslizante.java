package com.scrollbar;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class MaterialPanelDeslizante extends JScrollPane {

	private ScrollBarCustom scroll;

	private ScrollBarCustom scroll2;

	public void setBackground(Color color, boolean vertical) {

		if (vertical) {

			scroll.setBackground(color);

		}

		else {

			scroll2.setBackground(color);
		}

	}

	public void setForeground(Color color, boolean vertical) {

		if (vertical) {

			scroll.setForeground(color);

		}

		else {

			scroll2.setForeground(color);
		}

	}

	public MaterialPanelDeslizante(JComponent component, Color select, Color background, Color background2, int size) {

		super(component);

		scroll = new ScrollBarCustom(select, background, background2, size);

		scroll2 = new ScrollBarCustom(select, background, background2, size);

		setVerticalScrollBar(scroll);

		scroll2.setOrientation(JScrollBar.HORIZONTAL);

		setHorizontalScrollBar(scroll2);

	}

	public void setBackground2(Color color, boolean vertical) {

		if (vertical) {

			scroll.setBackground2(color);

		}

		else {

			scroll2.setBackground2(color);
		}

	}

	public void setThumbSize(int size, boolean vertical) {

		if (vertical) {

			scroll.setScrollSize(size);

		}

		else {

			scroll2.setScrollSize(size);

		}

	}

}