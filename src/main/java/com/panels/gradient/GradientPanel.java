package com.panels.gradient;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class GradientPanel extends JPanel {

	public enum Gradiente {

		HORIZONTAL, VERTICAL, ESQUINA_1, ESQUINA_2, ESQUINA_3, ESQUINA_4, CIRCULAR, CENTRAL, AQUA

	}

	protected Gradiente gradiente;

	protected Color colorPrimario;

	protected Color colorSecundario;

	private Image image;

	private Icon icon;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	public GradientPanel() {

		gradiente = Gradiente.HORIZONTAL;

		colorPrimario = new Color(32, 39, 55);

		colorSecundario = Color.BLACK;

		setColorPrimario(Color.WHITE);

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
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (image != null)
			g2.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		else {

			Paint gp = getGradientePaint();

			g2.setPaint(gp);

			g2.fillRect(0, 0, getWidth(), getHeight());

			g2.setColor(getForeground());

			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

		}

		g2.dispose();

		super.paintChildren(g);

	}

	public Paint getGradientePaint() {

		int x1 = 0;

		int x2 = getWidth();

		int y1 = 0;

		int y2 = getHeight();

		switch (gradiente) {

		case HORIZONTAL:

			x1 = getWidth() / 2;

			y1 = 0;

			x2 = getWidth() / 2;

			y2 = getHeight();

			return new GradientPaint(x1, y1, colorPrimario, x2, y2, colorSecundario);

		case VERTICAL:

			x1 = 0;

			y1 = getHeight() / 2;

			x2 = getWidth();

			y2 = getHeight() / 2;

			return new GradientPaint(x1, y1, colorPrimario, x2, y2, colorSecundario);

		case ESQUINA_1:

			x1 = 0;

			y1 = 0;

			return new RadialGradientPaint(x1, y1, getWidth(), new float[] { 0f, 1f },
					new Color[] { colorPrimario, colorSecundario });

		case ESQUINA_2:

			x1 = getWidth();

			y1 = 0;

			return new RadialGradientPaint(x1, y1, getWidth(), new float[] { 0f, 1f },
					new Color[] { colorPrimario, colorSecundario });

		case ESQUINA_3:

			x1 = getWidth();

			y1 = getHeight();

			return new RadialGradientPaint(x1, y1, getWidth(), new float[] { 0f, 1f },
					new Color[] { colorPrimario, colorSecundario });

		case ESQUINA_4:

			x1 = 0;

			y1 = getHeight();

			return new RadialGradientPaint(x1, y1, getWidth(), new float[] { 0f, 1f },
					new Color[] { colorPrimario, colorSecundario });

		case CIRCULAR:

			x1 = getWidth() / 2;

			y1 = getHeight() / 2;

			return new RadialGradientPaint(x1, y1, getWidth(), new float[] { 0f, 0.5f },
					new Color[] { colorPrimario, colorSecundario });

		case CENTRAL:

			x1 = getWidth() / 2;

			y1 = 0;

			x2 = getWidth() / 2;

			y2 = getHeight();

			return new LinearGradientPaint(x1, y1, x2, y2, new float[] { 0f, 0.5f, 1f },
					new Color[] { colorPrimario, colorSecundario, colorPrimario });

		case AQUA:

			return new LinearGradientPaint(0, 0, 0, getHeight(), new float[] { 0f, 0.3f, 0.5f, 1f },
					new Color[] { colorPrimario.brighter().brighter(), colorPrimario.brighter(),
							colorSecundario.darker().darker(), colorSecundario.darker() });

		default:

			return new GradientPaint(0, 0, colorPrimario, x2, y2, colorSecundario);

		}

	}

	public Color getColorPrimario() {

		return colorPrimario;

	}

	public void setColorPrimario(Color colorPrimario) {

		this.colorPrimario = colorPrimario;

	}

	public Color getColorSecundario() {

		return colorSecundario;

	}

	public void setColorSecundario(Color colorSecundario) {

		this.colorSecundario = colorSecundario;

	}

	public Gradiente getGradiente() {

		return gradiente;

	}

	public void setGradiente(Gradiente gradiente) {

		this.gradiente = gradiente;

	}

	public Icon getIcon() {

		return icon;

	}

	public void setIcon(Icon icon) {

		this.icon = icon;

		if (icon != null)
			image = ((ImageIcon) icon).getImage();

	}

}
