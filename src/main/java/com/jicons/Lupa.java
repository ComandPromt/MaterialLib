package com.jicons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Lupa extends ImageIcon {

	private Color color;

	private float grosor;

	private int size;

	public Lupa(Color color) {

		this(30, color);

	}

	public Lupa() {

		this(30, Color.BLACK);

	}

	public Lupa(int size, Color color) {

		this.size = size;

		grosor = 2f;

		this.color = color;

		setImage(createImage(size));

	}

	private BufferedImage createImage(int size) {

		BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = image.createGraphics();

		g2d.setColor(color);

		g2d.setStroke(new BasicStroke(grosor));

		g2d.drawOval(5, 5, 20, 20);

		g2d.rotate(Math.toRadians(45), 15, 15);

		g2d.fillRoundRect(22, 15, 10, 4, 2, 2);

		g2d.dispose();

		return image;

	}

	public void setGrosor(float grosor) {

		this.grosor = grosor;

		setImage(createImage(size));

	}

	public void setColor(Color color) {

		this.color = color;

		setImage(createImage(size));

	}

}
