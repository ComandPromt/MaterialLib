package com.radiobutton;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JRadioButton;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class RadioButtonCustom extends JRadioButton {

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private int left;

	private int altura;

	private int ancho;

	private int space;

	private Color fillColor;

	private Color deselectedColor;

	private Color ringColor;

	private Color deselectedBorderColor;

	private Color focus;

	public void setDeselectedColor(Color deselectedColor) {

		this.deselectedColor = deselectedColor;

		repaint();

	}

	public void setDeselectedBorderColor(Color deselectedBorderColor) {

		this.deselectedBorderColor = deselectedBorderColor;

		repaint();

	}

	public void setRingColor(Color ringColor) {

		this.ringColor = ringColor;

		repaint();

	}

	public void setFillColor(Color fillColor) {

		this.fillColor = fillColor;

		repaint();

	}

	public void setWidth(int width) {

		this.ancho = width;

		repaint();

	}

	public void setLeft(int left) {

		this.left = left;

		repaint();

	}

	public void setSpace(int space) {

		this.space = space;

		repaint();

	}

	public void setAltura(int altura) {

		this.altura = altura;

		repaint();

	}

	@Override
	public void setToolTipText(String text) {

		setToolTip(text, null, null, null, null);

	}

	public void setToolTip(String text, Color background, Color foreground, Color border, Font font) {

		calcularTooltip(text, background, foreground, border, font);

	}

	private void calcularTooltip(String text, Color background, Color foreground, Color border, Font font) {

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

			try {

				font = getFont().deriveFont(20f);

			}

			catch (Exception e) {

				font = new Font("Dialog", Font.PLAIN, 20);

			}

		}

		this.text = text;

		this.fondo = background;

		this.colorTexto = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || fondo == null || colorTexto == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, colorTexto, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public RadioButtonCustom(Color background) {

		this("");

		setBackground(background);

	}

	public RadioButtonCustom(String text, Color background) {

		this(text);

		setBackground(background);

	}

	public RadioButtonCustom(String text) {

		focus = Color.LIGHT_GRAY;

		border = new Color(69, 124, 235);

		ringColor = Color.WHITE;

		deselectedBorderColor = new Color(128, 128, 128);

		deselectedColor = Color.WHITE;

		fillColor = new Color(69, 124, 235);

		ancho = 16;

		space = 3;

		setText(text);

		setFont(new Font("Dialog", Font.PLAIN, 20));

		setFocusPainted(false);

		setOpaque(false);

		setCursor(new Cursor(Cursor.HAND_CURSOR));

	}

	public RadioButtonCustom() {

		this("");

	}

	@Override
	public void paint(Graphics grphcs) {

		Graphics2D g2 = (Graphics2D) grphcs;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(getBackground());

		g2.fillRect(0, 0, getWidth(), getHeight());

		int ly = (getHeight() - 16) / 2;

		if (isSelected()) {

			g2.setColor(border);

			g2.fillOval(left, ly + altura, ancho, ancho);

			g2.setColor(ringColor);

			g2.fillOval(1 + left, (ly + 1) + altura, ancho - 2, ancho - 2);

			g2.setColor(fillColor);

			g2.fillOval(4 + left, altura + (ly + 4), 8, 8);

		}

		else {

			if (isFocusOwner()) {

				g2.setColor(focus);

			}

			else {

				g2.setColor(deselectedBorderColor);

			}

			g2.fillOval(left, ly + altura, 16, 16);

			g2.setColor(deselectedColor);

			g2.fillOval(1 + left, (ly + 1) + altura, 14, 14);

		}

		g2.setColor(getForeground());

		g2.drawString(getText(), ancho + left + space, (getHeight() / 1.25f) + altura);

		g2.dispose();

	}

}
