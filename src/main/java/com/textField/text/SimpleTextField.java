package com.textField.text;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JTextField;
import javax.swing.JToolTip;

import com.contextmenu.DefaultContextMenu;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class SimpleTextField extends JTextField {

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private boolean round;

	private int angulo;

	private int grosor;

	private Color borderColor;

	public int getGrosor() {

		return grosor;

	}

	public void setGrosor(int grosor) {

		if (grosor < 1) {

			grosor = 0;

		}

		this.grosor = grosor;

		repaint();

	}

	public void setAngulo(int angulo) {

		this.angulo = angulo;

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

	public void setRound(boolean round) {

		this.round = round;

		grosor = 5;

		repaint();

	}

	public Color getBorderColor() {

		return borderColor;

	}

	public void setBorderColor(Color borderColor) {

		this.borderColor = borderColor;

		repaint();

	}

	public SimpleTextField() {

		grosor = 1;

		borderColor = Color.BLACK;

		angulo = 20;

		setFont(getFont().deriveFont(30f));

		setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

		DefaultContextMenu.addDefaultContextMenu(this);

	}

	@Override
	protected void paintBorder(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		Stroke st = g2.getStroke();

		g2.setStroke(new BasicStroke(grosor));

		g2.setColor(borderColor);

		if (round) {

			g2.drawRoundRect(grosor / 2, grosor / 2, getWidth() - grosor, getHeight() - grosor, angulo, angulo);

		}

		else {

			g2.drawRect(grosor / 2, grosor / 2, getWidth() - grosor, getHeight() - grosor);

		}

		g2.setStroke(st);

	}

}
