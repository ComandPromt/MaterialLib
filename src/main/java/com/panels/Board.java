package com.panels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Board extends JPanel {

	private int figure;

	private int ancho;

	private int alto;

	public void setAncho(int ancho) {

		this.ancho = ancho;

	}

	public void setAlto(int alto) {

		this.alto = alto;

	}

	public void setFigure(int figure) {

		this.figure = figure;

		repaint();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.BLUE);

		g.fillRect(0, 0, 10, 10);

	}

}
