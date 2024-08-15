package com.jicons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Delete extends ImageIcon {

	private Color color;

	private Component c;

	private float grosor;

	private Color lineColor;

	public void setLineColor(Color lineColor) {

		this.lineColor = lineColor;

		try {

			c.repaint();

		}

		catch (Exception e) {

		}

	}

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

	public Delete() {

		this(Color.BLACK);

	}

	public Delete(Color color) {

		this.color = color;

		grosor = 1f;

		lineColor = Color.BLACK;

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

		g2d.fillRect(Math.round(c.getWidth() * 0.25f), Math.round(c.getHeight() * 0.325f),
				Math.round(c.getWidth() * 0.5f), Math.round(c.getHeight() * 0.7f));

		g2d.fillRect(Math.round(c.getWidth() * 0.125f), Math.round(c.getHeight() * 0.15f),
				Math.round(c.getWidth() * 0.75f), Math.round(c.getHeight() * 0.125f));

		g2d.fillRect(Math.round(c.getWidth() * 0.375f), Math.round(c.getHeight() * 0.04f),
				Math.round(c.getWidth() * 0.25f), Math.round(c.getHeight() * 0.15f));

		g2d.setColor(lineColor);

		g2d.drawLine(Math.round(c.getWidth() * 0.36f), Math.round(c.getHeight() * 0.45f),
				Math.round(c.getWidth() * 0.36f), Math.round(c.getHeight() * 0.85f));

		g2d.drawLine(Math.round(c.getWidth() * 0.5f), Math.round(c.getHeight() * 0.45f),
				Math.round(c.getWidth() * 0.5f), Math.round(c.getHeight() * 0.85f));

		g2d.drawLine(Math.round(c.getWidth() * 0.65f), Math.round(c.getHeight() * 0.45f),
				Math.round(c.getWidth() * 0.65f), Math.round(c.getHeight() * 0.85f));

	}

}
