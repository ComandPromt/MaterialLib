package com.materialfilechooser;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JLabel;

import mthos.JMthos;

@SuppressWarnings("serial")

class Archivo extends JLabel {

	private Color fondo;

	private String texto;

	private String folder;

	public String getTexto() {

		return texto;

	}

	public static String saberEspacios(String text, String textoMayor, int size) {

		int diferencia = textoMayor.length() - text.length();

		String espacios = "";

		if (size > 30) {

			for (int i = 0; i < diferencia; i++) {

				espacios += "   ";

			}

		}

		else {

			if (textoMayor.length() == 1) {

				espacios += "     ";

			}

			else {

				for (int i = 0; i < Math.round(textoMayor.length() / 2.75); i++) {

					espacios += "      ";

				}

			}

		}

		return espacios;

	}

	public Archivo(String text, String textoMayor, int size, boolean carpeta) {

		super(text + saberEspacios(text, textoMayor, size));

		texto = text;

		fondo = Color.WHITE;

		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				if (fondo.equals(Color.WHITE)) {

					fondo = Color.decode("#EFEFEF");
				}

				else {

					fondo = Color.WHITE;

				}

				repaint();

			}

		});

	}

	@Override
	public void paint(Graphics g) {

		if (MaterialFileChooser.test == null) {

			MaterialFileChooser.test = new LinkedList<>();

		}

		if (fondo != Color.WHITE) {

			folder = MaterialFileChooser.path.getText();

			if (!folder.endsWith(JMthos.saberSeparador())) {

				folder += JMthos.saberSeparador();

			}

			if (!MaterialFileChooser.test.contains(folder + texto)) {

				MaterialFileChooser.test.add(folder + texto);

			}

		}

		g.setColor(fondo);

		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(Color.WHITE);

		int ancho = 15;

		int alto = 15;

		g.setColor(Color.BLACK);

		int calculoX = Math.round(ancho * 0.6666f);

		int calculoY = Math.round(getHeight() * 0.6666f);

		int[] xPoints = { 0, 0, ancho, ancho, ancho, calculoX };

		int[] yPoints = { 0, getHeight(), getHeight(), calculoY / 2, calculoY / 2, 0 };

		g.setColor(Color.BLACK);

		Polygon polygon = new Polygon(xPoints, yPoints, 6);

		g.fillPolygon(polygon);

		g.setColor(Color.WHITE);

		g.drawLine(Math.round(ancho * 0.1666f), Math.round(alto * 0.5f), ancho - Math.round(ancho * 0.1666f),
				Math.round(alto * 0.1666f) + (calculoY / 2) + 1);

		g.drawLine(Math.round(ancho * 0.1666f), Math.round(alto * 0.5f) + Math.round(alto * 0.1666f),
				ancho - Math.round(ancho * 0.1666f),
				(Math.round(alto * 0.1666f) + (calculoY / 2) + 1) + Math.round(alto * 0.1666f));

		g.drawLine(Math.round(ancho * 0.1666f), Math.round(alto * 0.5f) + (Math.round(alto * 0.1666f) * 2),
				ancho - Math.round(ancho * 0.1666f),
				(Math.round(alto * 0.1666f) + (calculoY / 2) + 1) + (Math.round(alto * 0.1666f)) * 2);

		g.setColor(Color.decode("#474747"));

		g.setFont(getFont().deriveFont(Font.PLAIN, 16f));

		g.drawString(texto, 18, (int) Math.round((getHeight() / 1.3)));

	}

}
