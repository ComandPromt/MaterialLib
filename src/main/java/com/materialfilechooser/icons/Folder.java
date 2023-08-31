package com.materialfilechooser.icons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

@SuppressWarnings("serial")

public class Folder extends JLabel {

	private Color background;

	private String texto;

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

	public Folder(String text, String textoMayor, int size) {

		super(text + saberEspacios(text, textoMayor, size));

		texto = text;

		background = Color.WHITE;

		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				if (background.equals(Color.WHITE)) {

					background = Color.decode("#EFEFEF");
				}

				else {

					background = Color.WHITE;

				}

				repaint();

			}

		});

	}

	@Override
	public void paint(Graphics g) {

		g.setColor(background);

		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(Color.WHITE);

		int ancho = 15;

		int alto = 15;

		g.setColor(Color.BLACK);

		int calculo = (int) Math.round(alto * 0.1666);

		int mitadX = ancho / 2;

		int[] xPoints = { 0, 0, ancho, ancho, mitadX, Math.round(ancho * 0.3333f) };

		int[] yPoints = { 0, alto, alto, calculo, calculo, 0 };

		Polygon polygon = new Polygon(xPoints, yPoints, xPoints.length);

		g.translate(0, 5);

		g.fillPolygon(polygon);

		g.setColor(Color.decode("#474747"));

		g.setFont(getFont().deriveFont(Font.PLAIN, 16f));

		g.drawString(texto, 18, (getHeight() / 2));

	}

}
