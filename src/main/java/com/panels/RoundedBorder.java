package com.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

import util.Mthos;

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

	private int alineacion;

	public void setText(String text) {

		this.text = text;

	}

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

	public void setInner(Color inner) {

		this.inner = inner;

	}

	@Override
	public Insets getBorderInsets(Component c) {

		return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);

	}

	@Override
	public boolean isBorderOpaque() {

		return true;

	}

	public void setRounded(boolean rounded) {

		this.rounded = rounded;

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

	public void setHorizontalAlignment(int alignment) {

		alineacion = alignment;

	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

		this.width = c.getWidth();

		this.height = c.getHeight();

		g.setColor(backgroundColor);

		g.fillRect(x, y, this.width, this.height);

		g.setColor(inner);

		if (rounded) {

			g.fillRoundRect(x, y, this.width, this.height, radius, radius);

		}

		else {

			g.fillRect(x, y, this.width, this.height);

		}

		g.setColor(external);

		if (rounded) {

			g.drawRoundRect(x, y, this.width - 1, this.height - 1, radius, radius);

		}

		else {

			g.drawRect(x, y, this.width - 1, this.height - 1);

		}

		g.setColor(textColor);

		g.setFont(fuente);

		g.drawString(text, Mthos.alinear(alineacion, text, fuente, width), (int) (this.height * 0.60));

	}

}
