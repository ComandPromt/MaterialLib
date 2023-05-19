package com.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class RoundedBorder implements Border {

	int radius;

	Color inner;

	Color external;

	Color backgroundColor;

	String text;

	int width;

	private int height;

	private int padding;

	private Color textColor;

	private Font fuente;

	private boolean rounded;

	public void setFuente(Font fuente) {
		this.fuente = fuente;
	}

	public void setExternal(Color external) {

		this.external = external;

	}

	public void setBackground(Color backgroundColor) {

		this.backgroundColor = backgroundColor;

	}

	public void setTextColor(Color textColor) {

		this.textColor = textColor;

	}

	public void setPadding(int padding) {

		this.padding = padding;

	}

	public void setWidth(int width) {

		this.width = width;
	}

	public void setHeight(int height) {

		this.height = height;

	}

	public void setInner(Color inner) {

		this.inner = inner;

	}

	public RoundedBorder(String text, int radius, Color background, Color inner, Color external) {

		this.text = text;

		textColor = Color.BLACK;

		if (background == null) {

			background = Color.WHITE;

		}

		if (inner == null) {

			inner = Color.WHITE;
		}

		if (external == null) {

			external = Color.BLACK;

		}

		this.backgroundColor = background;

		this.inner = inner;

		this.external = external;

		this.radius = radius;

	}

	@Override
	public Insets getBorderInsets(Component c) {

		return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);

	}

	@Override
	public boolean isBorderOpaque() {

		return true;

	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

		if (this.width == 0) {

			this.width = c.getWidth();

		}

		if (this.height == 0) {

			this.height = c.getHeight();

		}

		g.setColor(backgroundColor);

		g.fillRect(x, y, this.width, this.height);

		g.setColor(inner);

		g.fillRoundRect(x, y, this.width, this.height, radius, radius);

		g.setColor(external);

		if (rounded) {

			g.drawRoundRect(x, y, this.width - 1, this.height - 1, radius, radius);

		}

		else {

			g.drawRect(x, y, this.width - 1, this.height - 1);

		}

		g.setColor(textColor);

		g.setFont(fuente);

		g.drawString(text, padding, (int) (this.height * 0.60));

	}

	public void setHorizontalAlignment(int alignment) {

		switch (alignment) {

		case SwingConstants.LEFT:

			padding = 3;

			break;

		case SwingConstants.RIGHT:

			padding = (calcularCentro(text) / 2) - (text.length() / 2);

			padding += calcularCentro(text) / 2;

			if (text.length() > 4) {

				padding -= text.length();

				padding -= 2;

			}

			else {

				switch (text.length()) {

				case 4:

					padding += text.length();

					padding -= 2;

					break;

				case 2:

					padding *= 1.5;

					break;

				case 1:

					padding *= 3;

					padding--;

					break;
				}

			}

			break;

		default:

			padding = calcularCentro(text) - calcularCentro(text) / 2;

			padding += (-10 + (text.length() - 1) * 5) * -1;

		}

	}

	private int calcularCentro(String text) {

		int resultado = 0;

		boolean sumar = text.length() != 3;

		for (int i = 0; i < text.length(); i++) {

			if (Character.toString(text.charAt(i)).equals(Character.toString(text.charAt(i)).toUpperCase())) {

				resultado += (fuente.getSize() * 13) / 20;

			}

			else {

				resultado += (fuente.getSize() * 9) / 20;

			}

			if (sumar || Character.toString(text.charAt(0)).equals(text.substring(0, 1).toLowerCase())) {

				resultado += 3;

			}

		}

		if (text.length() == 2 && Character.toString(text.charAt(0)).equals(text.substring(0, 1).toUpperCase())) {

			resultado -= 3;

		}

		return resultado;

	}

	public void setRounded(boolean rounded) {

		this.rounded = rounded;

	}

}
