package com.jicons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class IconNext extends ImageIcon {

	private BufferedImage iconImage;

	private Color color;

	private float grosor;

	private Color background;

	public IconNext() {

		this(16, 16);

	}

	public IconNext(Color color) {

		this(16, 16);

		this.color = color;

	}

	public IconNext(int width, int height) {

		color = Color.BLACK;

		grosor = 1f;

		background = Color.WHITE;

		generarImagen(width, height);

	}

	private void pintarPoligono(Graphics2D g2d, List<Point> points) {
		if (points == null || points.size() < 3) {
			// Un polígono necesita al menos 3 puntos.
			return;
		}

		int[] xPoints = new int[points.size()];
		int[] yPoints = new int[points.size()];

		for (int i = 0; i < points.size(); i++) {
			xPoints[i] = points.get(i).x;
			yPoints[i] = points.get(i).y;
		}

		Polygon poligono = new Polygon(xPoints, yPoints, points.size());

		try {
			g2d.setColor(background);
			g2d.fillRect(0, 0, getIconWidth(), getIconHeight());
			g2d.setStroke(new BasicStroke(grosor));
			g2d.setColor(color);
			g2d.draw(poligono); // Dibuja el contorno del polígono
			// g2d.fill(poligono); // Opcional: para rellenar el polígono
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void generarImagen(int width, int height) {
		iconImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = iconImage.createGraphics();

		// 1. Crea una nueva instancia de ArrayList.
		List<Point> puntosTriangulo = new ArrayList<>();

		// 2. Añade los puntos a la lista uno por uno.
		puntosTriangulo.add(new Point(width / 2, height / 4));
		puntosTriangulo.add(new Point(width / 4, height * 3 / 4));
		puntosTriangulo.add(new Point(width * 3 / 4, height * 3 / 4));

		pintarPoligono(g2d, puntosTriangulo);

		g2d.dispose();
		setImage(iconImage);
	}

	private void pintarIcono(Graphics2D g2d, int width, int height) {

		try {

			g2d.setColor(background);

			g2d.fillRect(0, 0, width, height);

			g2d.setStroke(new BasicStroke(grosor));

			g2d.setColor(color);

			g2d.drawLine(width / 2, 0, 0, height / 2);

			g2d.drawLine(0, height / 2, width / 2, height);

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
