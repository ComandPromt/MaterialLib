package com.dialog.confirm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
class Background extends JPanel {

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

				font = getFont().deriveFont(14f);

			}

			catch (Exception e) {

				font = new Font("Dialog", Font.PLAIN, 14);

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

	public Background() {

		init();

	}

	private void init() {

		setOpaque(false);

		setBackground(new Color(245, 245, 245));

		setForeground(new Color(118, 118, 118));

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int x = 0;

		int y = 40;

		int width = getWidth();

		int height = getHeight();

		int iconSpace = 7;

		int totalIconSpace = iconSpace * 2;

		int iconSize = y * 2;

		int iconX = (width - (iconSize + totalIconSpace)) / 2;

		int iconY = 0;

		Area area = new Area(new Rectangle2D.Double(x, y, width, height - y));

		area.subtract(new Area(
				new Ellipse2D.Double(iconX, iconY - iconSpace, iconSize + totalIconSpace, iconSize + totalIconSpace)));

		area.add(new Area(new Ellipse2D.Double(iconX + iconSpace, 0, iconSize, iconSize)));

		g2.setColor(getBackground());

		g2.fill(area);

		g2.dispose();

		super.paintComponent(g);

	}

}
