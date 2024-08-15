package com.jicons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Lapiz extends ImageIcon {

	private Color color;

	private float grosor;

	private Component c;

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

	}

	public Lapiz() {

		this(Color.BLACK);

	}

	public Lapiz(Color color) {

		grosor = 1f;

		this.color = color;

	}

	@Override
	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {

		this.c = c;

		super.paintIcon(c, g, x, y);

		drawLapiz(c, g);

	}

	private void drawLapiz(Component c, Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(c.getBackground());

		g2d.fillRect(0, 0, c.getWidth(), c.getHeight());

		g2d.setStroke(new BasicStroke(grosor));

		int lapizWidth = Math.round(c.getWidth() * 0.1f);

		int lapizHeight = Math.round(c.getHeight() * 0.4f);

		int lapizX = (c.getWidth() - lapizWidth) / 2;

		int lapizY = (c.getHeight() - lapizHeight) / 2;

		lapizY -= 3;

		Shape lapizShape = crearLapizShape(lapizWidth, lapizHeight);

		AffineTransform rotateTransform = AffineTransform.getRotateInstance(Math.toRadians(225), lapizWidth / 2.0,
				lapizHeight / 2.0);

		Shape rotatedLapiz = rotateTransform.createTransformedShape(lapizShape);

		g2d.setColor(color);

		g2d.translate(lapizX - (Math.round(c.getWidth() * 0.166f)), lapizY - (Math.round(c.getHeight() * 0.425f)));

		g2d.scale(3.5, 3.5);

		g2d.draw(rotatedLapiz);

	}

	private Shape crearLapizShape(int width, int height) {

		int[] xPoints = { width / 2 - width / 5, width / 2 + width / 5, width / 2 };

		int[] yPoints = { height / 5, height / 5, height / 5 - height / 10 };

		GeneralPath lapizPath = new GeneralPath();

		lapizPath.append(new Rectangle2D.Double(width / 2 - width / 5, height / 5, width / 5 * 2, height * 3 / 5),
				false);

		lapizPath.moveTo(xPoints[0], yPoints[0]);

		for (int i = 1; i < xPoints.length; i++) {

			lapizPath.lineTo(xPoints[i], yPoints[i]);

		}

		lapizPath.closePath();

		lapizPath.append(
				new Rectangle2D.Double(width / 2 - width / 5 * 3 / 2, height * 4 / 5, width / 5 * 3, height / 10),
				false);

		return lapizPath;

	}

}