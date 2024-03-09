package com.buttons.simple;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

import mthos.JMthos;

@SuppressWarnings("serial")

public class ResizedButton extends JButton {

	private int ancho;

	private int alto;

	private boolean aspectRatio;

	private boolean centrar;

	private BufferedImage imagen;

	private int w;

	private int h;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private BufferedImage originalImage;

	private float alturaTexto;

	public float getAlturaTexto() {

		return alturaTexto;

	}

	public void setAlturaTexto(float alturaTexto) {

		this.alturaTexto = alturaTexto;

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

	public boolean isAspectRatio() {

		return aspectRatio;

	}

	public void setAspectRatio(boolean aspectRatio) {

		this.aspectRatio = aspectRatio;

		repaint();

	}

	public void setAspectRatio(boolean aspectRatio, int width, int height) {

		this.aspectRatio = aspectRatio;

		repaint();

	}

	/**
	 * @wbp.parser.constructor
	 */

	public ResizedButton(String text) {

		this(true, text);

	}

	public ResizedButton(boolean aspectRatio, String text) {

		alturaTexto = 2;

		setFont(new Font("Dialog", Font.PLAIN, 40));

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				w = getWidth();

				h = getHeight();

				repaint();

			}

		});

		this.aspectRatio = aspectRatio;

		setText(text);

	}

	public ResizedButton() {

		this(true);

	}

	public ResizedButton(int width, int height) {

		alturaTexto = 2;

		setFont(new Font("Dialog", Font.PLAIN, 40));

		w = width;

		h = height;

		this.aspectRatio = true;

	}

	public ResizedButton(boolean aspectRatio, int width, int height) {

		alturaTexto = 2;

		setFont(new Font("Dialog", Font.PLAIN, 40));

		w = width;

		h = height;

		this.aspectRatio = aspectRatio;

	}

	public ResizedButton(boolean aspectRatio) {

		alturaTexto = 2;

		setFont(new Font("Dialog", Font.PLAIN, 40));

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				w = getWidth();

				h = getHeight();

				repaint();

			}

		});

		this.aspectRatio = aspectRatio;

	}

	public int getAncho() {

		return ancho;

	}

	public int getAlto() {

		return alto;

	}

	public BufferedImage resizeImage(BufferedImage originalImage, int newWidth, int newHeight, boolean center) {

		int originalWidth = originalImage.getWidth();

		int originalHeight = originalImage.getHeight();

		double widthRatio = (double) newWidth / originalWidth;

		double heightRatio = (double) newHeight / originalHeight;

		int scaledWidth;

		int scaledHeight;

		int x = 0;

		int y = 0;

		if (center) {

			scaledWidth = newWidth;

			scaledHeight = newHeight;

		}

		else {

			double scaleFactor = Math.min(widthRatio, heightRatio);

			scaledWidth = (int) (originalWidth * scaleFactor);

			scaledHeight = (int) (originalHeight * scaleFactor);

			x = (newWidth - scaledWidth) / 2;

			y = (newHeight - scaledHeight) / 2;

		}

		BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());

		Graphics2D g2d = resizedImage.createGraphics();

		g2d.drawImage(originalImage, x, y, scaledWidth, scaledHeight, null);

		g2d.dispose();

		return resizedImage;

	}

	@Override
	public void paint(Graphics g) {

		try {

			g.setColor(getBackground());

			g.fillRect(0, 0, getWidth(), getHeight());

			if (originalImage == null) {

				originalImage = JMthos.iconToBufferedImage(getIcon());

			}

			if (aspectRatio) {

				w = getWidth();

				h = getHeight();

				Point punto = JMthos.getSizeOfImage(originalImage, w, h, aspectRatio);

				ancho = punto.x;

				alto = punto.y;

				if (getText().isEmpty()) {

					if (!centrar) {

						imagen = resizeImage(originalImage, ancho, alto, true);

						centrar = true;

					}

					g.drawImage(imagen, Math.round((w * 35) / 115f), 0, ancho, alto, null);

				}

				else {

					imagen = resizeImage(originalImage, ancho, alto, false);

					g.drawImage(imagen, 0, 0, ancho, alto, null);

				}

			}

			else {

				ancho = originalImage.getWidth();

				alto = originalImage.getHeight();

				imagen = resizeImage(originalImage, getWidth(), getHeight(), false);

				g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);

			}

			g.setColor(getForeground());

			g.setFont(getFont());

			if (!getText().isEmpty()) {

				g.drawString(getText(),
						(int) ((getWidth() / alturaTexto - g.getFontMetrics().stringWidth(getText()) / 2)) + 8,
						(Math.round((getHeight() / 2f) + (alturaTexto * 2))));

			}

			g.dispose();

		}

		catch (Exception e) {

		}

		super.paint(g);

	}

}
