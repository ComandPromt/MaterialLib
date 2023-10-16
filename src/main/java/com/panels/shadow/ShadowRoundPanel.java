package com.panels.shadow;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JToolTip;

import com.material.utils.ShadowRenderer;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class ShadowRoundPanel extends JPanel {

	private transient BufferedImage renderImage;

	private ShadowType shadowType;

	private int shadowSize;

	private float shadowOpacity;

	private Color shadowColor;

	private GradientType gradientType;

	private Color colorGradient;

	private int radius;

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

	public enum GradientType {

		VERTICAL, HORIZONTAL, DIAGONAL_1, DIAGONAL_2

	}

	public enum ShadowType {

		CENTER, TOP_RIGHT, TOP_LEFT, BOT_RIGHT, BOT_LEFT, BOT, TOP

	}

	public ShadowType getShadowType() {

		return shadowType;

	}

	public void setShadowType(ShadowType shadowType) {

		this.shadowType = shadowType;

	}

	public int getShadowSize() {

		return shadowSize;

	}

	public void setShadowSize(int shadowSize) {

		this.shadowSize = shadowSize;

	}

	public float getShadowOpacity() {

		return shadowOpacity;

	}

	public void setShadowOpacity(float shadowOpacity) {

		this.shadowOpacity = shadowOpacity;

	}

	public Color getShadowColor() {

		return shadowColor;

	}

	public void setShadowColor(Color shadowColor) {

		this.shadowColor = shadowColor;

	}

	public ShadowRoundPanel() {

		shadowType = ShadowType.BOT_LEFT;

		shadowSize = 6;

		shadowOpacity = 0.2f;

		shadowColor = Color.BLACK;

		gradientType = GradientType.HORIZONTAL;

		colorGradient = new Color(255, 255, 255);

		setOpaque(false);

		setBackground(Color.WHITE);

	}

	@Override
	protected void paintComponent(Graphics grphcs) {

		if (renderImage == null) {

			createRenderImage();

		}

		grphcs.drawImage(renderImage, 0, 0, null);

		super.paintComponent(grphcs);

	}

	@Override

	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);

		createRenderImage();

	}

	private void createRenderImage() {

		renderImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = renderImage.createGraphics();

		int size = shadowSize * 2;

		int x;

		int y;

		int width = getWidth() - size;

		int height = getHeight() - size;

		switch (shadowType) {

		case TOP:

			x = shadowSize;

			y = size;

			break;

		case BOT:

			x = shadowSize;

			y = 0;

			break;

		case TOP_LEFT:

			x = size;

			y = size;

			break;

		case TOP_RIGHT:

			x = 0;

			y = size;

			break;

		case BOT_LEFT:

			x = size;

			y = 0;

			break;

		case BOT_RIGHT:

			x = 0;

			y = 0;

			break;

		default:

			x = shadowSize;

			y = shadowSize;

			break;

		}

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g = img.createGraphics();

		createBackground(g, width, height);

		ShadowRenderer render = new ShadowRenderer(shadowSize, shadowOpacity, shadowColor);

		g2.drawImage(render.createShadow(img), 0, 0, null);

		g2.drawImage(img, x, y, null);

		g2.dispose();

	}

	private void createBackground(Graphics2D g2, int width, int height) {

		g2.setColor(getBackground());

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int x1;

		int x2;

		int y1;

		int y2;

		if (gradientType == null) {

			x1 = 0;

			y1 = 0;

			x2 = width;

			y2 = 0;

		}

		else {

			switch (gradientType) {

			case HORIZONTAL:

				x1 = 0;

				y1 = 0;

				x2 = width;

				y2 = 0;

				break;

			case VERTICAL:

				x1 = 0;

				y1 = 0;

				x2 = 0;

				y2 = height;

				break;

			case DIAGONAL_1:

				x1 = 0;

				y1 = height;

				x2 = width;

				y2 = 0;

				break;

			default:

				x1 = 0;

				y1 = 0;

				x2 = width;

				y2 = height;

				break;

			}

		}

		Point p1 = new Point(x1, y1);

		Point p2 = new Point(x2, y2);

		g2.setPaint(new GradientPaint(p1, getBackground(), p2, colorGradient));

		g2.fill(new RoundRectangle2D.Double(0, 0, width, height, radius, radius));

		g2.dispose();

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

}
