package com.label.round;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JLabel;

@SuppressWarnings("serial")

public class RoundedLabel extends JLabel {

	private BufferedImage originalImage;

	private int grosor;

	private int radius;

	private Color borderColor;

	private Color fondo;

	public void setBorderColor(Color borderColor) {

		this.borderColor = borderColor;

	}

	public void setGrosor(int grosor) {

		if (grosor < 3 && grosor > 0) {

			grosor = 3;

		}

		this.grosor = grosor;

	}

	public void setRadius(int radius) {

		if (radius < 0) {

			radius = 0;

		}

		if (radius > 365) {

			radius = 365;

		}

		this.radius = radius;

	}

	public RoundedLabel(String text) {

		fondo = null;

		borderColor = Color.BLACK;

		grosor = 3;

		radius = 20;

		setText(text);

	}

	@Override
	public void setBackground(Color bg) {

		fondo = bg;

		repaint();

	}

	@Override
	public void setIcon(Icon defaultIcon) {

		try {

			String ruta = defaultIcon.toString();

			if (System.getProperty("os.name").contains("indows")) {

				ruta = ruta.replace("file:/", "");

			}

			else {

				ruta = ruta.replace("file:", "");

			}

			originalImage = loadFileImage(ruta);

			setPreferredSize(new Dimension(originalImage.getWidth(), originalImage.getHeight()));

			repaint();

		}

		catch (Exception e) {

		}

	}

	@Override
	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();

		if (fondo != null) {

			g.setColor(fondo);

			g.fillRect(0, 0, getWidth(), getHeight());

		}

		if (grosor > 2) {

			g2d.setStroke(new BasicStroke(grosor));

			g2d.setColor(borderColor);

			g2d.drawRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

		}

		Shape mask = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);

		g2d.setClip(mask);

		if (grosor > 2) {

			g2d.drawImage(originalImage, grosor / 2, grosor / 2, getWidth() - (grosor - 1), getHeight() - (grosor - 1),
					this);

		}

		else {

			g2d.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);

		}

		g2d.dispose();

		super.paintComponent(g);

	}

	private BufferedImage loadFileImage(String image) {

		try {

			return javax.imageio.ImageIO.read(new File(image));

		}

		catch (Exception e) {

			return null;

		}

	}

}
