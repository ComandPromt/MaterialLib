package com.jmenu.simple;

import java.awt.Graphics;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class IconoPadre extends JLabel {

	@Override
	public void paint(Graphics g) {

		int xPoints[] = new int[3];

		int yPoints[] = new int[3];

		xPoints[0] = 0;

		xPoints[1] = getWidth() / 2;

		xPoints[2] = getWidth();

		yPoints[0] = 0;

		yPoints[1] = getHeight();

		yPoints[2] = 0;

		g.setColor(getBackground());

		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(getForeground());

		g.fillPolygon(xPoints, yPoints, 3);

	}

}
