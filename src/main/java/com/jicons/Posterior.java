package com.jicons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Posterior extends ImageIcon {

	private BufferedImage iconImage;

	private Color color = Color.BLACK;

	private float grosor = 1f;

	private Color background = Color.WHITE;

	public Posterior() {

		this(16, 16);

	}

	public Posterior(Color color) {

		this(16, 16);

		this.color = color;

	}

	public Posterior(int width, int height) {

		generarImagen(width, height);

	}

	private void generarImagen(int width, int height) {

		iconImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = iconImage.createGraphics();

		pintarIcono(g2d, width, height);

		g2d.dispose();

		setImage(iconImage);

	}

	private void pintarIcono(Graphics2D g2d, int width, int height) {

		try {

			g2d.setColor(background);

			g2d.fillRect(0, 0, width, height);

			g2d.setStroke(new BasicStroke(grosor));

			g2d.setColor(color);

			g2d.drawLine(width / 2, 0, width, height / 2);

			g2d.drawLine(width, height / 2, width / 2, height);

		}

		catch (Exception e) {

		}

	}

	public void setGrosor(float grosor) {

		this.grosor = grosor;

		regenerar();

	}

	public void setColor(Color color) {

		this.color = color;

		regenerar();

	}

	public void setBackground(Color background) {

		this.background = background;

		regenerar();

	}

	private void regenerar() {

		if (iconImage != null) {

			generarImagen(iconImage.getWidth(), iconImage.getHeight());

		}

	}

}
