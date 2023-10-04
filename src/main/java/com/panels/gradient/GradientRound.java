package com.panels.gradient;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class GradientRound extends JComponent {

	private GradientType gradientType;

	private Color colorGradient;

	private int radius;

	private String text;

	private Color background;

	private Color foreground;

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

		this.background = background;

		this.foreground = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

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

	public GradientRound() {

		gradientType = GradientType.HORIZONTAL;

		colorGradient = new Color(255, 255, 255);

		setOpaque(true);

		setColorGradient(Color.BLACK);

		setBackground(Color.WHITE);

	}

	@Override
	protected void paintComponent(Graphics g) {

		if (isOpaque()) {

			Graphics2D g2 = (Graphics2D) g.create();

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			Insets inset = getInsets();

			int width = getWidth() - inset.left - inset.right;

			int height = getHeight() - inset.top - inset.bottom;

			int x1;

			int x2;

			int y1;

			int y2;

			if (gradientType == null) {

				x1 = inset.left;

				y1 = inset.top;

				x2 = inset.left + width;

				y2 = inset.top;

			}

			else {

				switch (gradientType) {

				case HORIZONTAL:

					x1 = inset.left;

					y1 = inset.top;

					x2 = inset.left + width;

					y2 = inset.top;

					break;

				case VERTICAL:

					x1 = inset.left;

					y1 = inset.top;

					x2 = inset.left;

					y2 = inset.top + height;

					break;

				case DIAGONAL_1:

					x1 = inset.left;

					y1 = inset.top + height;

					x2 = inset.left + width;

					y2 = inset.top;

					break;

				default:

					x1 = inset.left;

					y1 = inset.top;

					x2 = inset.left + width;

					y2 = inset.top + height;

					break;

				}

			}

			Point p1 = new Point(x1, y1);

			Point p2 = new Point(x2, y2);

			g2.setPaint(new GradientPaint(p1, getBackground(), p2, colorGradient));

			g2.fill(new RoundRectangle2D.Double(inset.left, inset.top, width, height, radius, radius));

			g2.dispose();

		}

		super.paintComponent(g);

	}

	public GradientType getGradientType() {

		return gradientType;

	}

	public void setGradientType(GradientType gradientType) {

		this.gradientType = gradientType;

		repaint();

	}

	public Color getColorGradient() {

		return colorGradient;

	}

	public void setColorGradient(Color colorGradient) {

		this.colorGradient = colorGradient;

		repaint();

	}

	public int getRadius() {

		return radius;

	}

	public void setRadius(int radius) {

		this.radius = radius;

		repaint();

	}

	public enum GradientType {

		VERTICAL, HORIZONTAL, DIAGONAL_1, DIAGONAL_2

	}

}
