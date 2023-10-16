package com.panels.round;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class RoundedPanel extends JPanel {

	private int radius;

	private int thickness;

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

	public int getThickness() {

		return thickness;

	}

	public void setThickness(int thickness) {

		if (thickness < 0) {

			thickness = 0;

		}

		this.thickness = thickness;

		repaint();

	}

	public RoundedPanel() {

		thickness = 0;

		radius = 20;

		setOpaque(false);

		setBorder(new EmptyBorder(10, 10, 10, 10));

		setLayout(new BorderLayout());

	}

	public void setRadius(int radius) {

		this.radius = radius;

		setBorder(new EmptyBorder(radius / 2, radius / 2, radius / 2, radius / 2));

		revalidate();

		repaint();

	}

	public int getRadius() {

		return radius;

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(getBackground());

		g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getRadius(), getRadius());

		g2.setColor(getForeground());

		g2.setStroke(new BasicStroke(thickness));

		g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getRadius(), getRadius());

		super.paintComponent(g);

	}

}
