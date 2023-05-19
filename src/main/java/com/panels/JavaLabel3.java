package com.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;

@SuppressWarnings("serial")
public class JavaLabel3 extends Label {

	private Color color;

	private int equis;

	private int ancho;

	private int alto;

	private Color backgroundColor;

	public void setBackgroundColor(Color backgroundColor) {
		
		this.backgroundColor = backgroundColor;
	
	}

	public void setEquis(int equis) {
	
		this.equis = equis;
	
	}

	public void setAlto(int alto) {
	
		this.alto = alto;
	
	}

	public void setColor(Color color) {
	
		this.color = color;
	
	}

	public void setAncho(int ancho) {
	
		this.ancho = ancho;
	
	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);

		g.setColor(backgroundColor);

		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(color);

		g.fillRect(equis, 0, ancho, alto);

	}

}
