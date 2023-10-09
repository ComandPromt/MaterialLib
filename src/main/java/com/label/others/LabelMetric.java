package com.label.others;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

import mthos.JMthos;

@SuppressWarnings("serial")
public class LabelMetric extends JLabel {

	private float shadowOffsetX;

	private float shadowOffsetY;

	private Color colorDeSombra;

	private Color colorTexto;

	private Color foreground1;

	private int direccionDeSombra;

	private int distanciaDeSombra;

	private String text;

	private Color fondo;

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

		this.foreground1 = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || fondo == null || foreground1 == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, foreground1, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public LabelMetric() {

		this("Text");

	}

	public LabelMetric(String text) {

		colorDeSombra = Color.BLACK;

		colorTexto = Color.WHITE;

		fuente = new Font("Dialog", Font.PLAIN, 30);

		distanciaDeSombra = 5;

		computeShadow();

		setText(text);

		setFont(fuente);

		setForeground(colorTexto);

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(getBackground());

		g2.fillRect(0, 0, getWidth(), getHeight());

		g2.drawImage(JMthos.iconToBufferedImage(getIcon()), 0, 0, getWidth(), getHeight(), null);

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Insets insets = getInsets();

		FontMetrics fm = getFontMetrics(getFont());

		if (getText() == null)
			setText("  ");

		TextLayout layout = new TextLayout(getText().length() <= 0 ? " " : getText(), getFont(),
				g2.getFontRenderContext());

		Rectangle2D bounds = layout.getBounds();

		int x = (int) (getWidth() - insets.left - insets.right - bounds.getWidth()) / 2;

		if (getHorizontalAlignment() != CENTER) {

			x = 0 + insets.left;

		}

		int y = (getHeight() - insets.top - insets.bottom - fm.getMaxAscent() - fm.getMaxDescent()) / 2;

		y += fm.getAscent() - 1;

		g2.setColor(colorDeSombra);

		layout.draw(g2, x + (int) Math.ceil(shadowOffsetX), y + (int) Math.ceil(shadowOffsetY));

		g2.setColor(getForeground());

		layout.draw(g2, x, y);

	}

	private void computeShadow() {

		double rads = Math.toRadians(direccionDeSombra);

		shadowOffsetX = (float) Math.cos(rads) * distanciaDeSombra;

		shadowOffsetY = (float) Math.sin(rads) * distanciaDeSombra;

	}

	public void setSombra(Color colorDeSombra) {

		this.colorDeSombra = colorDeSombra;

	}

	public void setDireccionSombra(int direccionDeSombra) {

		this.direccionDeSombra = direccionDeSombra;

		computeShadow();

		repaint();

	}

	public void setDistanciaSombra(int distanciaDeSombra) {

		this.distanciaDeSombra = distanciaDeSombra;

		computeShadow();

		repaint();

	}

}
