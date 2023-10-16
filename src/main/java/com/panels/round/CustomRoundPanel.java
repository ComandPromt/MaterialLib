package com.panels.round;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class CustomRoundPanel extends JPanel {

	private int roundTopLeft;

	private int roundTopRight;

	private int roundBottomLeft;

	private int roundBottomRight;

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

	public int getRoundTopLeft() {

		return roundTopLeft;

	}

	public void setRoundTopLeft(int roundTopLeft) {

		this.roundTopLeft = roundTopLeft;

		repaint();

	}

	public int getRoundTopRight() {

		return roundTopRight;

	}

	public void setRoundTopRight(int roundTopRight) {

		this.roundTopRight = roundTopRight;

		repaint();

	}

	public int getRoundBottomLeft() {

		return roundBottomLeft;

	}

	public void setRoundBottomLeft(int roundBottomLeft) {

		this.roundBottomLeft = roundBottomLeft;

		repaint();

	}

	public int getRoundBottomRight() {

		return roundBottomRight;

	}

	public void setRoundBottomRight(int roundBottomRight) {

		this.roundBottomRight = roundBottomRight;

		repaint();

	}

	public CustomRoundPanel() {

		setOpaque(false);

	}

	@Override
	protected void paintComponent(Graphics grphcs) {

		Graphics2D g2 = (Graphics2D) grphcs.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(getBackground());

		Area area = new Area(createRoundTopLeft());

		if (roundTopRight > 0) {

			area.intersect(new Area(createRoundTopRight()));

		}

		if (roundBottomLeft > 0) {

			area.intersect(new Area(createRoundBottomLeft()));

		}

		if (roundBottomRight > 0) {

			area.intersect(new Area(createRoundBottomRight()));

		}

		g2.fill(area);

		g2.dispose();

		super.paintComponent(grphcs);

	}

	private Shape createRoundTopLeft() {

		int width = getWidth();

		int height = getHeight();

		int roundX = Math.min(width, roundTopLeft);

		int roundY = Math.min(height, roundTopLeft);

		Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));

		area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));

		area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));

		return area;

	}

	private Shape createRoundTopRight() {

		int width = getWidth();

		int height = getHeight();

		int roundX = Math.min(width, roundTopRight);

		int roundY = Math.min(height, roundTopRight);

		Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));

		area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));

		area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));

		return area;

	}

	private Shape createRoundBottomLeft() {

		int width = getWidth();

		int height = getHeight();

		int roundX = Math.min(width, roundBottomLeft);

		int roundY = Math.min(height, roundBottomLeft);

		Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));

		area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));

		area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));

		return area;

	}

	private Shape createRoundBottomRight() {

		int width = getWidth();

		int height = getHeight();

		int roundX = Math.min(width, roundBottomRight);

		int roundY = Math.min(height, roundBottomRight);

		Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));

		area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));

		area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));

		return area;

	}

}
