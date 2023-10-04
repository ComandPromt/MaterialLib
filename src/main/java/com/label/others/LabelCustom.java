
package com.label.others;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class LabelCustom extends JLabel {

	private float shadowOffsetX;

	private float shadowOffsetY;

	private Color colorDeSombra;

	private int direccionDeSombra;

	private int distanciaDeSombra;

	private boolean vertical;

	private boolean borde;

	protected float anchoDeBorde;

	protected Color colorDeBorde;

	private Tipo tipo;

	private Forma forma;

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

	public enum Tipo {

		NONE, CENTRAL, CURVE, CURVE_2, CURVE_3, CURVE_4

	}

	public enum Forma {

		ROUND, RECT, TOP, BOTTOM

	}

	public LabelCustom(Icon icon) {

		super(icon);

	}

	public LabelCustom() {

		this("Text");

	}

	public LabelCustom(String text) {

		tipo = Tipo.NONE;

		forma = Forma.ROUND;

		colorDeSombra = new Color(0, 0, 0);

		direccionDeSombra = 5;

		distanciaDeSombra = 5;

		vertical = true;

		borde = false;

		anchoDeBorde = 2f;

		colorDeBorde = Color.black;

		setOpaque(false);

		setText(text);

		setFont(new Font("Dialog", Font.PLAIN, 30));

		setForeground(new Color(255, 255, 255));

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

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Paint oldPaint = g2.getPaint();

		dibujaPaint(g2);

		FontMetrics fm = getFontMetrics(getFont());

		TextLayout layout;

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

	private void dibujaPaint(Graphics2D g2) {

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.clip(new Rectangle2D.Float(0, 0, getWidth(), getHeight()));

		float x1 = 0;

		float x2 = 0;

		float y1 = 0;

		float y2 = getHeight();

		Ellipse2D curve;

		Color startColor;

		Color endColor;

		Area area;

		switch (tipo) {

		case NONE:

			if (!vertical) {

				x1 = 0;

				y1 = 0;

				x2 = getWidth();

				y2 = 0;

			}

			Paint paint = new GradientPaint(x1, y1, getBackground(), x2, y2, getBackground().darker());

			g2.setPaint(paint);

			g2.fill(getFiguraRectangulo());

			break;

		case CENTRAL:

			if (!vertical) {

				x1 = 0;

				y1 = 0;

				x2 = getWidth();

				y2 = 0;

			}

			paint = new LinearGradientPaint(x1, y1, x2, y2, new float[] { 0f, 0.30f, 0.5f, 0.70f, 1f },
					new Color[] { getBackground().darker().darker(), getBackground(), getBackground().brighter(),
							getBackground(), getBackground().darker() });

			g2.setPaint(paint);

			g2.fill(getFiguraRectangulo());

			break;

		case CURVE:

			double bulletWidth = getWidth();

			double bulletHeight = getHeight();

			curve = new Ellipse2D.Double(-20, bulletHeight / 2.0, bulletWidth + 20 * 2, bulletHeight);

			startColor = getBackground().darker();

			endColor = getBackground().brighter();

			paint = g2.getPaint();

			g2.setPaint(new GradientPaint(0.0f, 0.0f, startColor, 0.0f, (float) bulletHeight / 2, endColor));

			g2.fill(getFiguraRectangulo());

			startColor = getBackground().darker().darker();

			endColor = getBackground().brighter();

			g2.setPaint(
					new GradientPaint(0.0f, (float) getHeight() / 2, startColor, 0.0f, (float) bulletHeight, endColor));

			area = new Area(getFiguraRectangulo());

			area.intersect(new Area(curve));

			g2.fill(area);

			g2.setPaint(paint);

			break;

		case CURVE_2:

			curve = new Ellipse2D.Double(-getWidth() / 2, 0, getWidth(), getHeight() * 2);

			startColor = getBackground().darker();

			endColor = getBackground().brighter();

			paint = g2.getPaint();

			g2.setPaint(new GradientPaint(0.0f, 0.0f, startColor, 0.0f, (float) getHeight() / 2, endColor));

			g2.fill(getFiguraRectangulo());

			startColor = getBackground().darker().darker();

			endColor = getBackground().brighter();

			g2.setPaint(
					new GradientPaint(0.0f, (float) getHeight() / 2, startColor, 0.0f, (float) getHeight(), endColor));

			area = new Area(getFiguraRectangulo());

			area.intersect(new Area(curve));

			g2.fill(area);

			g2.setPaint(paint);

			break;

		case CURVE_3:

			curve = new Ellipse2D.Double(-20, -getHeight() / 2.0, getWidth() + 20 * 2, getHeight());

			startColor = getBackground().darker();

			endColor = getBackground().brighter();

			paint = g2.getPaint();

			g2.setPaint(new GradientPaint(0.0f, 0.0f, startColor, 0.0f, (float) getHeight() / 2, endColor));

			g2.fill(getFiguraRectangulo());

			startColor = getBackground().darker().darker();

			endColor = getBackground().brighter();

			g2.setPaint(new GradientPaint(0.0f, 0f, startColor, 0.0f, (float) getHeight() / 2, endColor));

			area = new Area(getFiguraRectangulo());

			area.intersect(new Area(curve));

			g2.fill(area);

			g2.setPaint(paint);

			break;

		case CURVE_4:

			curve = new Ellipse2D.Double(getWidth() / 2, 0, getWidth() * 2, getHeight() * 2);

			startColor = getBackground().darker();

			endColor = getBackground().brighter();

			paint = g2.getPaint();

			g2.setPaint(new GradientPaint(0.0f, 0.0f, startColor, 0.0f, (float) getHeight() / 2, endColor));

			g2.fill(getFiguraRectangulo());

			startColor = getBackground().darker().darker();

			endColor = getBackground().brighter();

			g2.setPaint(
					new GradientPaint(0.0f, (float) getHeight() / 2, startColor, 0.0f, (float) getHeight(), endColor));

			area = new Area(getFiguraRectangulo());

			area.intersect(new Area(curve));

			g2.fill(area);

			g2.setPaint(paint);

			break;

		}

	}

	@Override
	protected void paintBorder(Graphics g) {

		if (!isBorde())
			return;

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setStroke(new BasicStroke(anchoDeBorde));

		g2.setColor(colorDeBorde);

		switch (forma) {

		case RECT:

			g2.drawRect(1, 1, getWidth() - 2, getHeight() - 2);

			break;

		case ROUND:

			g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 2, getHeight(), getHeight());

			break;

		case TOP:

			g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 2 + 40, getHeight(), getHeight());

			g2.drawLine(1, getHeight() - 2, getWidth(), getHeight() - 2);

			break;

		case BOTTOM:

			g2.drawRoundRect(1, 1 - 40, getWidth() - 3, getHeight() - 2 + 40, getHeight(), getHeight());

			g2.drawLine(1, 1, getWidth(), 1);

			break;

		}

		g2.dispose();

	}

	protected Shape getFiguraRectangulo() {

		switch (forma) {

		case RECT:

			return new Rectangle2D.Float(0, 0, getWidth(), getHeight());

		case ROUND:

			return new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), getHeight(), getHeight());

		case TOP:

			return new RoundRectangle2D.Float(0, 0, getWidth(), getHeight() + 40, getHeight(), getHeight());

		case BOTTOM:

			return new RoundRectangle2D.Float(0, -40, getWidth(), getHeight() + 40, getHeight(), getHeight());

		default:

			return new Rectangle2D.Float(0, 0, getWidth(), getHeight());

		}

	}

	public float getAnchoDeBorde() {

		return anchoDeBorde;

	}

	public void setAnchoDeBorde(float anchoDeBorde) {

		this.anchoDeBorde = anchoDeBorde;

	}

	public boolean isBorde() {

		return borde;

	}

	public void setBorde(boolean borde) {

		this.borde = borde;

	}

	public Color getColorDeBorde() {

		return colorDeBorde;

	}

	public void setBorde(Color colorDeBorde) {

		this.colorDeBorde = colorDeBorde;

	}

	public void setSombra(Color colorDeSombra) {

		this.colorDeSombra = colorDeSombra;

		repaint();

	}

	public void setDireccionSombra(int direccionDeSombra) {

		this.direccionDeSombra = direccionDeSombra;

		repaint();

	}

	public int getDistanciaDeSombra() {

		return distanciaDeSombra;

	}

	public void setDistanciaSombra(int distanciaDeSombra) {

		this.distanciaDeSombra = distanciaDeSombra;

		repaint();

	}

	public boolean isVertical() {

		return vertical;

	}

	public void setVertical(boolean vertical) {

		this.vertical = vertical;

		repaint();

	}

	public Tipo getTipo() {

		return tipo;

	}

	public void setTipo(Tipo tipo) {

		this.tipo = tipo;

	}

	public Forma getForma() {

		return forma;

	}

	public void setForma(Forma forma) {

		this.forma = forma;

	}

}
