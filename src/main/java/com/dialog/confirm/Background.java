package com.dialog.confirm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
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

	private Icon icon;

	private Color fondoPintura;

	public void setIcon(Icon icon) {

		this.icon = icon;

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

	public Background() {

		init();

	}

	private void init() {

		fondoPintura = Color.WHITE;

		setOpaque(false);

		setBackground(new Color(245, 245, 245));

		setForeground(new Color(118, 118, 118));

	}

	public void setFondo(Color color) {

		fondoPintura = color;

		repaint();

	}

	@Override
	protected void paintComponent(Graphics g) {

		g.setColor(fondoPintura);

		g.fillRect(0, 0, getWidth(), getHeight());

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

		if (icon != null) {

			Image img = ((ImageIcon) icon).getImage();

			int iconWidth = img.getWidth(this);

			int iconHeight = img.getHeight(this);

			int adjustedIconX = iconX + iconSpace;

			if (iconWidth > iconSize) {

				double scaleFactor = (double) iconSize / iconWidth;

				iconWidth = (int) (iconWidth * scaleFactor);

				iconHeight = (int) (iconHeight * scaleFactor);

			}

			else if (iconHeight < iconSize) {

				adjustedIconX = (iconSize + iconWidth * 2);

			}

			BufferedImage roundedImage = new BufferedImage(iconSize, iconSize, BufferedImage.TYPE_INT_ARGB);

			Graphics2D g2rounded = roundedImage.createGraphics();

			Ellipse2D.Double ellipse = new Ellipse2D.Double(0, 0, iconSize, iconSize);

			g2rounded.setClip(ellipse);

			g2rounded.drawImage(img, 0, 0, iconWidth, iconHeight + totalIconSpace * 2, this);

			g2rounded.dispose();

			int adjustedIconY = 0;

			g2.drawImage(roundedImage, adjustedIconX, adjustedIconY, this);

		}

		g2.dispose();

		super.paintComponent(g);

	}

}