package com.panels.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

	private Image image;

	private Icon icon;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private boolean aspectRatio;

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

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		if (image != null) {

			int panelWidth = getWidth();

			int panelHeight = getHeight();

			int imageWidth = image.getWidth(this);

			int imageHeight = image.getHeight(this);

			if (aspectRatio && imageWidth > 0 && imageHeight > 0) {

				double imageAspectRatio = (double) imageWidth / imageHeight;

				double panelAspectRatio = (double) panelWidth / panelHeight;

				int drawWidth, drawHeight, x = 0, y = 0;

				if (panelAspectRatio > imageAspectRatio) {

					drawHeight = panelHeight;

					drawWidth = (int) (panelHeight * imageAspectRatio);

					x = (panelWidth - drawWidth) / 2;

				}

				else {

					drawWidth = panelWidth;

					drawHeight = (int) (panelWidth / imageAspectRatio);

					y = (panelHeight - drawHeight) / 2;

				}

				g2.drawImage(image, x, y, drawWidth, drawHeight, null);

			}

			else {

				g2.drawImage(image, 0, 0, panelWidth, panelHeight, null);

			}

		}

	}

	public void setImage(String fileName) {

		try {

			if (new File(fileName).isFile()) {

				setIcon(new ImageIcon(fileName));

			}

		}

		catch (Exception e) {

		}

	}

	public Icon getIcon() {

		return icon;

	}

	public void setIcon(Icon icon) {

		this.icon = icon;

		if (icon != null) {

			image = ((ImageIcon) icon).getImage();

		}

		repaint();

	}

	public boolean isAspectRatio() {

		return aspectRatio;

	}

	public void setAspectRatio(boolean aspectRatio) {

		this.aspectRatio = aspectRatio;

		repaint();

	}

}