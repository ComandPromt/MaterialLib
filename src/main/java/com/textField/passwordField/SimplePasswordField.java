package com.textField.passwordField;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPasswordField;
import javax.swing.JToolTip;

import com.contextmenu.DefaultContextMenu;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class SimplePasswordField extends JPasswordField {

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private boolean round;

	private int angulo;

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

		repaint();

	}

	public SimplePasswordField() {

		angulo = 90;

		setFont(getFont().deriveFont(30f));

		setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

		DefaultContextMenu.addDefaultContextMenu(this);

	}

	@Override
	protected void paintBorder(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		Stroke st = g2.getStroke();

		g2.setStroke(new BasicStroke(5));

		if (round) {

			g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, angulo, angulo);

		}

		else {

			g2.drawRect(0, 0, getWidth(), getHeight());

		}

		g2.setStroke(st);

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		Paint oldPaint = g2.getPaint();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Shape rect;

		if (round) {

			rect = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, angulo, angulo);

		}

		else {

			rect = new Rectangle2D.Double(0, 0, getWidth(), getHeight());

		}

		g2.clip(rect);

		g2.setPaint(new GradientPaint(0.0f, 0.0f, getBackground(), 0.0f, getHeight(), getBackground()));

		if (round) {

			g2.fillRoundRect(0, 0, getWidth(), getHeight(), 90, 90);

		}

		else {

			g2.fillRect(0, 0, getWidth(), getHeight());

		}

		g2.setPaint(oldPaint);

		super.paintComponent(g);

	}

}
