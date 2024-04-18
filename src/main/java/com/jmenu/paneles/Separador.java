package com.jmenu.paneles;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Separador extends JLabel {

	private int grosor;

	private String texto;

	public int getGrosor() {

		return grosor;

	}

	public void setGrosor(int grosor) {

		this.grosor = grosor;

		repaint();

	}

	public Separador(String text, Color foreground, Color background) {

		if (foreground == null) {

			foreground = Color.BLACK;

		}

		if (background == null) {

			background = Color.WHITE;

		}

		texto = text;

		if (text != null) {

			setText(text);

			setHorizontalAlignment(SwingConstants.CENTER);

		}

		setForeground(foreground);

		setBackground(background);

	}

	@Override
	public void paint(Graphics g) {

		g.setColor(getBackground());

		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(getForeground());

		Graphics2D g2 = (Graphics2D) g;

		g2.setStroke(new BasicStroke(grosor));

		if (texto == null) {

			g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);

		}

		super.paint(g);

	}

}
