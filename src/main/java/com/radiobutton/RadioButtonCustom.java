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

		setOpaque(false);

		setFont(new Font("Dialog", Font.PLAIN, 20));

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setBackground(background);

	}

	public RadioButtonCustom(String text, Color background) {

		setText(text);

		setFont(new Font("Dialog", Font.PLAIN, 20));

		setOpaque(false);

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setBackground(background);

	}

	public RadioButtonCustom(String text) {

		setText(text);

		setFont(new Font("Dialog", Font.PLAIN, 20));

		setOpaque(false);

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setBackground(new Color(69, 124, 235));

	}

	public RadioButtonCustom() {

		setOpaque(false);

		setFont(new Font("Dialog", Font.PLAIN, 20));

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setBackground(new Color(69, 124, 235));

	}

	@Override
	public void paint(Graphics grphcs) {

		super.paint(grphcs);

		Graphics2D g2 = (Graphics2D) grphcs;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int ly = (getHeight() - 16) / 2;

		if (isSelected()) {

			if (isEnabled()) {

				g2.setColor(getBackground());

			}

			else {

				g2.setColor(Color.GRAY);

			}

			g2.fillOval(1, ly, 16, 16);

			g2.setColor(Color.WHITE);

			g2.fillOval(2, ly + 1, 14, 14);

			if (isEnabled()) {

				g2.setColor(getBackground());

			}

			else {

				g2.setColor(Color.GRAY);

			}

			g2.fillOval(5, ly + 4, 8, 8);

		}

		else {

			if (isFocusOwner()) {

				g2.setColor(getBackground());

			}

			else {

				g2.setColor(Color.GRAY);

			}

			g2.fillOval(1, ly, 16, 16);

			g2.setColor(Color.WHITE);

			g2.fillOval(2, ly + 1, 14, 14);

		}

		g2.dispose();

	}

}
