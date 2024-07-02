package com.separator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CustomSeparator extends JPanel {

	private Color startColor;

	private Color endColor;

	private int lineHeight;

	private boolean isGradient;

	private List<Color> colors;

	public void setGrosor(int grosor) {

		this.lineHeight = grosor;

		repaint();

	}

	public CustomSeparator() {

		this(Color.BLACK, 3);

	}

	public CustomSeparator(Color lineColor) {

		this(lineColor, 3);

	}

	public CustomSeparator(Color startColor, Color endColor) {

		this(startColor, endColor, 3);

	}

	public CustomSeparator(Color lineColor, int lineHeight) {

		this(lineColor, lineColor, lineHeight, false);

	}

	public CustomSeparator(Color startColor, Color endColor, int lineHeight) {

		this(startColor, endColor, lineHeight, true);

	}

	public CustomSeparator(List<Color> colors) {

		this(colors, 3);

	}

	public CustomSeparator(List<Color> colors, int lineHeight) {

		this.colors = colors;

		if (colors.size() >= 2) {

			this.startColor = colors.get(0);

			this.endColor = colors.get(colors.size() - 1);

			this.lineHeight = lineHeight;

			this.isGradient = true;

		}

		else {

			throw new IllegalArgumentException("Debe proporcionar al menos dos colores para crear un gradiente.");

		}

	}

	private CustomSeparator(Color startColor, Color endColor, int lineHeight, boolean isGradient) {

		this.startColor = startColor;

		this.endColor = endColor;

		this.lineHeight = lineHeight;

		this.isGradient = isGradient;

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g.create();

		g2d.setStroke(new BasicStroke(lineHeight));

		int width = getWidth();

		int height = getHeight();

		if (isGradient) {

			if (colors != null && colors.size() >= 2) {

				Color[] colorArray = colors.toArray(new Color[0]);

				float[] fractions = new float[colorArray.length];

				for (int i = 0; i < colorArray.length; i++) {

					fractions[i] = (float) i / (colorArray.length - 1);

				}

				Paint gradientPaint = new LinearGradientPaint(0, 0, width, 0, fractions, colorArray);

				g2d.setPaint(gradientPaint);

				g2d.fillRect(0, height / 2 - lineHeight / 2, width, lineHeight);

			}

			else {

				Paint gradientPaint = new GradientPaint(0, 0, startColor, width, 0, endColor);

				g2d.setPaint(gradientPaint);

				g2d.fillRect(0, height / 2 - lineHeight / 2, width, lineHeight);

			}

		}

		else {

			g2d.setColor(startColor);

			g2d.fillRect(0, height / 2 - lineHeight / 2, width, lineHeight);

		}

		g2d.dispose();

	}

}
