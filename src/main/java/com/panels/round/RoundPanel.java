package com.panels.round;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;

import javax.swing.JToolTip;

import com.panels.gradient.GradientPanel;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class RoundPanel extends GradientPanel {

	protected float anchoDeBorde;

	protected float anchoDeSegundoBorde;

	protected Color colorDeBorde;

	protected Color colorDeSegundoBorde;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private int angulo;

	public int getAngulo() {

		return angulo;

	}

	public void setAngulo(int angulo) {

		this.angulo = angulo;

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

	public RoundPanel() {

		angulo = 10;

		anchoDeBorde = 4f;

		anchoDeSegundoBorde = 1.7f;

		colorDeBorde = new Color(173, 173, 173);

		colorDeSegundoBorde = Color.WHITE;

	}

	@Override
	protected void paintBorder(Graphics g) {

		int x = 4;

		int y = 4;

		int w = getWidth() - 9;

		int h = getHeight() - 9;

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setStroke(new BasicStroke(anchoDeBorde));

		g2.setColor(colorDeBorde);

		g2.drawRoundRect(x, y, w, h, angulo, angulo);

		g2.setStroke(new BasicStroke(anchoDeSegundoBorde));

		g2.setColor(colorDeSegundoBorde);

		g2.drawRoundRect(x + 2, y + 2, w - 4, h - 4, angulo, angulo);

		g2.dispose();

	}

	@Override
	protected void paintComponent(Graphics g) {

		int x = 7;

		int y = 7;

		int w = getWidth() - 11;

		int h = getHeight() - 11;

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Paint gp = getGradientePaint();

		g2.setPaint(gp);

		g2.fillRoundRect(x, y, w - 3, h - 3, angulo, angulo);

		g2.setColor(getForeground());

		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

		g2.dispose();

	}

	public float getAnchoDeBorde() {

		return anchoDeBorde;

	}

	public void setAnchoDeBorde(float anchoDeBorde) {

		this.anchoDeBorde = anchoDeBorde;

	}

	public Color getColorDeBorde() {

		return colorDeBorde;

	}

	public void setColorDeBorde(Color colorDeBorde) {

		this.colorDeBorde = colorDeBorde;

	}

	public Color getColorDeSegundoBorde() {

		return colorDeSegundoBorde;

	}

	public void setColorDeSegundoBorde(Color colorDeSegundoBorde) {

		this.colorDeSegundoBorde = colorDeSegundoBorde;

	}

	public float getAnchoDeSegundoBorde() {

		return anchoDeSegundoBorde;

	}

	public void setAnchoDeSegundoBorde(float anchoDeSegundoBorde) {

		this.anchoDeSegundoBorde = anchoDeSegundoBorde;

	}

}
