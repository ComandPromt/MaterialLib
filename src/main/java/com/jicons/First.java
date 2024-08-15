package com.jicons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class First extends ImageIcon {

	private Color color;

	private Component c;

	private float grosor;

	public void setGrosor(float grosor) {

		this.grosor = grosor;

		try {

			c.repaint();

		}

		catch (Exception e) {

		}

	}

	public void setColor(Color color) {

		this.color = color;

		try {

			c.repaint();

		}

		catch (Exception e) {

		}

	}

	public First() {

		this(Color.BLACK);

	}

	public First(Color color) {

		this.color = color;

		grosor = 1f;

	}

	@Override
	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {

		this.c = c;

		super.paintIcon(c, g, x, y);

		drawContent(c, g, x, y);

	}

	private void drawContent(Component c, Graphics g, int x, int y) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(c.getBackground());

		g2d.fillRect(0, 0, c.getWidth(), c.getHeight());

		g2d.setStroke(new BasicStroke(grosor));

		g2d.setColor(color);

		g2d.drawLine(c.getWidth(), 0, c.getWidth() / 2, c.getHeight() / 2);

		g2d.drawLine(c.getWidth(), c.getHeight(), c.getWidth() / 2, c.getHeight() / 2);

		g2d.drawLine(Math.round(c.getWidth() * 0.25f), c.getHeight() / 2, Math.round(c.getWidth() * 0.75f), 0);

		g2d.drawLine(Math.round(c.getWidth() * 0.25f), c.getHeight() / 2, Math.round(c.getWidth() * 0.75f),
				c.getHeight());

	}

}
