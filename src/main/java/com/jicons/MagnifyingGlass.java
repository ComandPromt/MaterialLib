package com.jicons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class MagnifyingGlass extends ImageIcon {

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

	public MagnifyingGlass() {

		this(Color.BLACK);

	}

	public MagnifyingGlass(Color color) {

		grosor = 2f;

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

		g2d.drawOval(5, 5, c.getWidth() / 2, c.getHeight() / 2);

		AffineTransform originalTransform = g2d.getTransform();

		int centerX = c.getWidth() / 2;

		int equis = Math.round(centerX * 0.68f);

		int ye = Math.round(centerX * 1f);

		int ancho = centerX / 2;

		int alto = Math.round(c.getHeight() * 0.5f);

		if (c.getWidth() < c.getHeight()) {

			equis = Math.round(c.getWidth() * 0.3f);

			ye = Math.round(c.getHeight() * 0.47f);

		}

		else if (c.getHeight() < c.getWidth()) {

			equis = Math.round(c.getWidth() * 0.55555f);

			ye = Math.round(c.getHeight() * 0.55f);

			ancho = Math.round(centerX * 0.29629f);

		}

		else {

			ye = Math.round(centerX * 0.9f);

		}

		g2d.rotate(Math.toRadians(-50), centerX, centerX);

		g2d.fillRoundRect(equis, ye, ancho, alto, 45, 45);

		g2d.setTransform(originalTransform);

	}

}
