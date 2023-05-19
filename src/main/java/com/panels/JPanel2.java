package com.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JPanel2 extends JPanel {

	JavaLabel3 fondo;

	public JPanel2() {

		fondo = new JavaLabel3();

		add(fondo);

	}

	public int iluminacion;

	public Point punto;

	private Color background;

	private Color color;

	private int equis;

	private int ancho;

	public void setEquis(int equis) {

		this.equis = equis;

	}

	public void setAncho(int ancho) {

		this.ancho = ancho;
	}

	public void setColor(Color color) {

		if (color == null) {

			color = Color.GREEN;

		}

		this.color = color;

	}

	@Override
	public void setBackground(Color bg) {

		if (bg == null) {

			bg = Color.WHITE;

		}

		this.background = bg;

		super.setBackground(bg);

	}

	@Override
	protected void paintComponent(Graphics g) {

		if (ancho > 0) {

			fondo.setColor(color);

			fondo.setBackgroundColor(background);

			fondo.setEquis(equis);

			fondo.setAncho(ancho);

			fondo.setAlto(getHeight());

			fondo.repaint();

		}

	}

}
