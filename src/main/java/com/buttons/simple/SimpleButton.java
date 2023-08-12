package com.buttons.simple;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JToolTip;

import org.edisoncor.gui.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class SimpleButton extends JButton {

	private boolean over;

	private Color color;

	private Color colorOver;

	private Color colorClick;

	private Color borderColor;

	private int radius = 0;

	private String text;

	private Color background;

	private Color foreground;

	private Color border;

	private Font fuente;

	public void setToolTip(String text, Color background, Color foreground, Color border, Font font) {

		if (background == null) {

			background = new Color(32, 39, 55);

		}

		if (foreground == null) {

			foreground = Color.WHITE;

		}

		if (border == null) {

			border = new Color(173, 173, 173);

		}

		if (font == null) {

			font = getFont().deriveFont(14f);

		}

		this.text = text;

		this.background = background;

		this.foreground = foreground;

		this.border = border;

		this.fuente = font;

		setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || background == null || foreground == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, background, foreground, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public enum FormTypes {

		CIRCLE, RECTANGLE, ROUNDED_RECTANGLE, CLASIC_RECTANGLE;

	}

	public void setForm(FormTypes type) {

		int valor = 0;

		switch (type) {

		case CIRCLE:

			valor = 90;

			break;

		case RECTANGLE:

			valor = 10;

			break;

		case ROUNDED_RECTANGLE:

			valor = 25;

			break;

		case CLASIC_RECTANGLE:

			valor = 0;

			break;

		}

		setRadius(valor);

	}

	public boolean isOver() {

		return over;

	}

	public void setOver(boolean over) {

		this.over = over;

	}

	public Color getColor() {

		return color;

	}

	public void setColor(Color color) {

		this.color = color;

		setBackground(color);

	}

	public Color getColorOver() {

		return colorOver;

	}

	public void setColorOver(Color colorOver) {

		this.colorOver = colorOver;

	}

	public Color getColorClick() {

		return colorClick;

	}

	public void setColorClick(Color colorClick) {

		this.colorClick = colorClick;

	}

	public Color getBorderColor() {

		return borderColor;

	}

	public void setBorderColor(Color borderColor) {

		this.borderColor = borderColor;

	}

	public int getRadius() {

		return radius;

	}

	public void setRadius(int radius) {

		this.radius = radius;

	}

	public void setColors(Color foreground, Color colorOver, Color background, Color click) {

		this.setForeground(foreground);

		this.setColorOver(colorOver);

		this.setColor(background);

		this.setColorClick(click);

	}

	public SimpleButton(String text) {

		setColor(Color.WHITE);

		colorOver = Color.WHITE;

		colorClick = Color.WHITE;

		borderColor = new Color(30, 136, 56);

		setBounds(70, 80, 100, 30);

		setText(text);

		setBorder(null);

		setFocusPainted(false);

		setContentAreaFilled(false);

		addMouseListener(new MouseAdapter() {

			@Override

			public void mouseEntered(MouseEvent me) {

				setBackground(colorOver);

				over = true;

			}

			@Override

			public void mouseExited(MouseEvent me) {

				setBackground(color);

				over = false;

			}

			@Override

			public void mousePressed(MouseEvent me) {

				setBackground(colorClick);

			}

			@Override

			public void mouseReleased(MouseEvent me) {

				if (over) {

					setBackground(colorOver);

				}

				else {

					setBackground(color);

				}

			}

		});

	}

	@Override

	protected void paintComponent(Graphics grphcs) {

		Graphics2D g2 = (Graphics2D) grphcs;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(borderColor);

		g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

		g2.setColor(getBackground());

		g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);

		super.paintComponent(grphcs);

	}

}
