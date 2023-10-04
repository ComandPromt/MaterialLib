package com.panels.round;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class NicePanel extends JPanel {

	private Color borderColor;

	private float anchoDeBorde;

	private int angulo;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	public int getAngulo() {

		return angulo;

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

	public NicePanel() {

		angulo = 16;

		borderColor = Color.WHITE;

		anchoDeBorde = 2;

		setLayout(new BorderLayout());

		setOpaque(false);

		setBackground(new Color(30, 30, 30, 216));

	}

	@Override
	protected void paintBorder(Graphics g) {

		Graphics2D graphics2d = (Graphics2D) g.create();

		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		GradientPaint paint = new GradientPaint(0, 0, borderColor, 0, getHeight(), borderColor);

		Stroke oldStroke = graphics2d.getStroke();

		graphics2d.setStroke(new BasicStroke(anchoDeBorde));

		graphics2d.setPaint(paint);

		graphics2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, angulo, angulo);

		graphics2d.setStroke(oldStroke);

		graphics2d.dispose();

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D graphics2d = (Graphics2D) g.create();

		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		graphics2d.setComposite(AlphaComposite.Src);

		graphics2d.setColor(getBackground());

		graphics2d.fillRoundRect(0, 0, getWidth(), getHeight(), angulo, angulo);

		graphics2d.dispose();

	}

	public Shape getShape() {

		return new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), angulo, angulo);

	}

	public float getAnchoDeBorde() {

		return anchoDeBorde;

	}

	public void setAnchoDeBorde(float anchoDeBorde) {

		this.anchoDeBorde = anchoDeBorde;

	}

	public Color getBorderColor() {

		return borderColor;

	}

	public void setBorderColor(Color borderColor) {

		this.borderColor = borderColor;

	}

}
