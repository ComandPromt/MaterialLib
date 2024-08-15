package com.jicons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Reload extends ImageIcon {

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

	public Reload() {

		this(Color.BLACK);

	}

	public Reload(Color color) {

		grosor = 1f;

		this.color = color;

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

		int[] equis = new int[3];

		equis[0] = 0;

		equis[1] = Math.round(c.getWidth() * 0.25f);

		equis[2] = Math.round(c.getWidth() * 0.125f);

		int[] ye = new int[3];

		ye[0] = c.getHeight() / 2;

		ye[1] = c.getHeight() / 2;

		ye[2] = Math.round(c.getHeight() * 0.25f);

		g2d.fillPolygon(equis, ye, 3);

		g2d.drawArc(Math.round(c.getWidth() * 0.125f), 0, c.getWidth() - (Math.round(c.getWidth() * 0.125f) * 2),
				c.getHeight() - 1, 0, 135);

		g2d.drawArc(Math.round(c.getWidth() * 0.125f), 0, c.getWidth() - (Math.round(c.getWidth() * 0.125f) * 2),
				c.getHeight() - 1, -180, 135);

		equis[0] = Math.round(c.getWidth() * 0.75f);

		equis[1] = c.getWidth();

		equis[2] = Math.round(c.getWidth() * 0.875f);

		ye[0] = c.getHeight() / 2;

		ye[1] = c.getHeight() / 2;

		ye[2] = Math.round(c.getHeight() * 0.75f);

		g2d.fillPolygon(equis, ye, 3);

	}

}
