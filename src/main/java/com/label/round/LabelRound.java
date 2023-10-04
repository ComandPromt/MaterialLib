package com.label.round;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JLabel;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class LabelRound extends JLabel {

	private float shadowOffsetX;

	private float shadowOffsetY;

	private Color colorDeSombra;

	private int direccionDeSombra;

	private int distanciaDeSombra;

	private int angulo;

	private boolean vertical;

	private boolean borde;

	protected float anchoDeBorde;

	protected float anchoSegundoBorde;

	protected Color colorDeBorde;

	protected Color colorDeSegundoBorde;

	private TextLayout layout;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private boolean segundoBorde;

	public boolean isSegundoBorde() {

		return segundoBorde;

	}

	public void setSegundoBorde(boolean segundoBorde) {

		this.segundoBorde = segundoBorde;

		repaint();

	}

	public float getAnchoSegundoBorde() {

		return anchoSegundoBorde;

	}

	public void setAnchoSegundoBorde(float anchoSegundoBorde) {

		if (anchoSegundoBorde < 0f) {

			anchoSegundoBorde = 0f;

		}

		this.anchoSegundoBorde = anchoSegundoBorde;

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

	public LabelRound() {

		this("Text");

	}

	public LabelRound(String text) {

		anchoSegundoBorde = 1.7f;

		colorDeSombra = new Color(0, 0, 0);

		direccionDeSombra = 5;

		distanciaDeSombra = 5;

		angulo = 20;

		vertical = true;

		borde = false;

		anchoDeBorde = 4f;

		colorDeBorde = new Color(173, 173, 173);

		colorDeSegundoBorde = Color.WHITE;

		setText(text);

		setOpaque(false);

		setFont(new Font("Dialog", Font.PLAIN, 30));

		setForeground(new Color(255, 255, 255));

		setBackground(Color.PINK);

	}

	private void computeShadow() {

		double rads = Math.toRadians(direccionDeSombra);

		shadowOffsetX = (float) Math.cos(rads) * distanciaDeSombra;

		shadowOffsetY = (float) Math.sin(rads) * distanciaDeSombra;

	}

	@Override
	protected void paintComponent(Graphics g) {

		computeShadow();

		Graphics2D g2 = (Graphics2D) g;

		Paint oldPaint = g2.getPaint();

		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), angulo, angulo);

		g2.clip(r2d);

		Rectangle2D rect = new Rectangle2D.Double(0, 0, getWidth(), getHeight() / 2.0);

		float x1 = 0;

		float x2 = 0;

		float y1 = 0;

		float y2 = getHeight();

		if (!vertical) {

			x1 = 0;

			y1 = 0;

			x2 = getWidth();

			y2 = 0;

		}

		Paint paint = new GradientPaint(x1, y1, getBackground(), x2, y2, getBackground().darker());

		g2.setPaint(paint);

		g2.fill(rect);

		rect = new Rectangle2D.Double(0, (getHeight() / 2.0) - 1.0, getWidth(), getHeight());

		g2.fill(rect);

		FontMetrics fm = getFontMetrics(getFont());

		try {

			layout = new TextLayout(getText(), getFont(), g2.getFontRenderContext());

		}

		catch (Exception e) {

			layout = new TextLayout("Text", getFont(), g2.getFontRenderContext());

		}

		Rectangle2D bounds = layout.getBounds();

		int x = (int) (getWidth() - bounds.getWidth()) / 2;

		int y = (getHeight() - fm.getMaxAscent() - fm.getMaxDescent()) / 2;

		y += fm.getAscent() - 1;

		g2.setColor(colorDeSombra);

		layout.draw(g2, x + (int) Math.ceil(shadowOffsetX), y + (int) Math.ceil(shadowOffsetY));

		g2.setColor(getForeground());

		layout.draw(g2, x, y);

		g2.setPaint(oldPaint);

	}

	@Override
	protected void paintBorder(Graphics g) {

		if (!isBorde())
			return;

		int x = 2;

		int y = 2;

		int w = getWidth() - 6;

		int h = getHeight() - 5;

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setStroke(new BasicStroke(anchoDeBorde));

		g2.setColor(colorDeBorde);

		g2.drawRoundRect(x, y, w, h, angulo, angulo);

		if (segundoBorde) {

			g2.setStroke(new BasicStroke(anchoSegundoBorde));

			g2.setColor(colorDeSegundoBorde);

			g2.drawRoundRect(x + 2, y + 2, w - 4, h - 4, angulo, angulo);

		}

		g2.dispose();

	}

	public boolean isBorde() {

		return borde;

	}

	public void setBorde(boolean borde) {

		this.borde = borde;

		repaint();

	}

	public void setAnchoDeBorde(float anchoDeBorde) {

		if (anchoDeBorde < 0f) {

			anchoDeBorde = 0f;

		}

		this.anchoDeBorde = anchoDeBorde;

		repaint();

	}

	public void setBorde(Color colorDeBorde) {

		this.colorDeBorde = colorDeBorde;

		repaint();

	}

	public void setSegundoBorde(Color colorDeSegundoBorde) {

		this.colorDeSegundoBorde = colorDeSegundoBorde;

		repaint();

	}

	public void setSombra(Color colorDeSombra) {

		this.colorDeSombra = colorDeSombra;

		repaint();

	}

	public void setDireccionDeSombra(int direccionDeSombra) {

		this.direccionDeSombra = direccionDeSombra;

		repaint();

	}

	public void setDistanciaDeSombra(int distanciaDeSombra) {

		this.distanciaDeSombra = distanciaDeSombra;

		repaint();

	}

	public void setAngulo(int angulo) {

		this.angulo = angulo;

		repaint();

	}

	public void setVertical(boolean vertical) {

		this.vertical = vertical;

		repaint();

	}

}
