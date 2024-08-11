package com.label.others;

import java.awt.Color;
import java.awt.Font;
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

import com.filters.ColorTintFilter;
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

	private ColorTintFilter tint;

	private TextLayout layout;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private Color filterColor;

	private int horizontalAlignment = CENTER;

	private int verticalAlignment = CENTER;

	public Color getFilterColor() {

		return filterColor;

	}

	public void setFilterImage(Color filterColor) {

		this.filterColor = filterColor;

		setTinteImage(true);

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

			setFilterImage(getFilterColor());

			repaint();

		}

		catch (Exception e) {

		}

	}

	public LabelHeader(Icon icon) {

		super(icon);

		inicio("");

	}

	public LabelHeader() {

		this("Text");

	}

	public LabelHeader(String text) {

		inicio(text);

	}

	private void inicio(String text) {

		filterColor = Color.WHITE;

		colorDeSombra = Color.LIGHT_GRAY;

		direccionDeSombra = 0;

		distanciaDeSombra = 0;

		setOpaque(false);

		if (!text.isEmpty()) {

			setText(text);

		}

		setFont(new Font("Dialog", Font.PLAIN, 20));

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

		g2.setColor(getBackground());

		g2.fillRect(0, 0, getWidth(), getHeight());

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Paint oldPaint = g2.getPaint();

		g2.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);

		try {

			layout = new TextLayout(getText(), getFont(), g2.getFontRenderContext());

		}

		catch (Exception e) {

			layout = new TextLayout(" ", getFont(), g2.getFontRenderContext());

		}

		Rectangle2D bounds = layout.getBounds();

		int x = getHorizontalAlignmentPosition((int) bounds.getWidth());

		int y = getVerticalAlignmentPosition((int) bounds.getHeight());

		g2.setColor(colorDeSombra);

		layout.draw(g2, x + (int) Math.ceil(shadowOffsetX), y + (int) Math.ceil(shadowOffsetY));

		g2.setColor(getForeground());

		layout.draw(g2, x, y);

		g2.setPaint(oldPaint);

	}

	private int getHorizontalAlignmentPosition(int textWidth) {

		switch (horizontalAlignment) {

		case LEFT:

			return 0;

		case CENTER:

			return (getWidth() - textWidth) / 2;

		case RIGHT:

			return getWidth() - textWidth - (getFont().getSize() / 3);

		default:

			return 0;

		}

	}

	private int getVerticalAlignmentPosition(int textHeight) {

		switch (verticalAlignment) {

		case TOP:

			return textHeight;

		case CENTER:

			return (getHeight() + textHeight) / 2;

		case BOTTOM:

			return getHeight();

		default:

			return textHeight;

		}

	}

	public void setHorizontalAlignment(int alignment) {

		this.horizontalAlignment = alignment;

		repaint();

	}

	public void setVerticalAlignment(int alignment) {

		this.verticalAlignment = alignment;

		repaint();

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

	public void setTinteImage(boolean colored) {

		try {

			if (colored) {

				tint = new ColorTintFilter(filterColor, .5f);

				tint.filter(imagen, imagen);

				repaint();

			}

			else {

				tint = null;

			}

		}

		catch (Exception e) {

		}

	}

}
