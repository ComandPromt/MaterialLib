
package com.label.others;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JToolTip;

import com.materiallib.utils.ColorTintFilter;
import com.toolTip.ToolTipLlamada;

import mthos.JMthos;

@SuppressWarnings("serial")
public class LabelHeader extends JLabel {

	private float shadowOffsetX;

	private float shadowOffsetY;

	private Color colorDeSombra;

	private int direccionDeSombra;

	private int distanciaDeSombra;

	protected BufferedImage imagen;

	private boolean colored;

	private ColorTintFilter tint;

	private TextLayout layout;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private Color filterColor;

	public Color getFilterColor() {

		return filterColor;

	}

	public void setFilterColor(Color filterColor) {

		this.filterColor = filterColor;

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

	@Override
	public void setIcon(Icon icon) {

		try {

			this.imagen = JMthos.iconToBufferedImage(icon);

			repaint();

		}

		catch (Exception e) {

		}

	}

	public LabelHeader(Icon icon) {

		super(icon);

	}

	public LabelHeader() {

		this("Text");

	}

	public LabelHeader(String text) {

		colorDeSombra = new Color(0, 0, 0);

		direccionDeSombra = 5;

		distanciaDeSombra = 5;

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

		g2.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);

		FontMetrics fm = getFontMetrics(getFont());

		try {

			layout = new TextLayout("Text", getFont(), g2.getFontRenderContext());

		}

		catch (Exception e) {

			layout = new TextLayout(getText(), getFont(), g2.getFontRenderContext());

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

	public void setSombra(Color colorDeSombra) {

		this.colorDeSombra = colorDeSombra;

		repaint();

	}

	public void setDireccionSombra(int direccionDeSombra) {

		this.direccionDeSombra = direccionDeSombra;

		repaint();

	}

	public void setDistanciaSombra(int distanciaDeSombra) {

		this.distanciaDeSombra = distanciaDeSombra;

		repaint();

	}

	public boolean isColored() {

		return colored;

	}

	public void setColored(boolean colored) {

		this.colored = colored;

		tint = new ColorTintFilter(filterColor, .5f);

		try {

			tint.filter(imagen, imagen);

		}

		catch (Exception e) {

		}

		repaint();

	}

}
