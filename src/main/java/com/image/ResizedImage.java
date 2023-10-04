package com.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

import mthos.JMthos;

@SuppressWarnings("serial")

public class ResizedImage extends JLabel {

	private int ancho;

	private int alto;

	private boolean aspectRatio;

	private BufferedImage originalImage;

	private BufferedImage imagen;

	private int w;

	private int h;

	private Icon icono;
	private String text;

	private Color background;

	private Color foreground;

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

		this.background = background;

		this.foreground = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || background == null || foreground == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, background, foreground, border, fuente);

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

	public ResizedImage(boolean aspectRatio) {

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				w = getWidth();

				h = getHeight();

				setIcon(icono);

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

	public BufferedImage resizeImage(BufferedImage originalImage, int newWidth, int newHeight) {

		int originalWidth = originalImage.getWidth();

		int originalHeight = originalImage.getHeight();

		double widthRatio = (double) newWidth / originalWidth;

		double heightRatio = (double) newHeight / originalHeight;

		double scaleFactor = Math.min(widthRatio, heightRatio);

		int scaledWidth = (int) (originalWidth * scaleFactor);

		int scaledHeight = (int) (originalHeight * scaleFactor);

		imagen = new BufferedImage(scaledWidth, scaledHeight, originalImage.getType());

		Graphics2D g2d = imagen.createGraphics();

		g2d.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);

		g2d.dispose();

		return imagen;

	}

	@Override
	public void setIcon(Icon defaultIcon) {

		if (defaultIcon != null) {

			icono = defaultIcon;

		}

		try {

			if (defaultIcon != null && w > 0) {

				String ruta = defaultIcon.toString();

				if (System.getProperty("os.name").contains("indows")) {

					ruta = ruta.replace("file:/", "");

				}

				else {

					ruta = ruta.replace("file:", "");

				}

				originalImage = JMthos.loadFileImage(ruta);

				if (aspectRatio) {

					Point punto = JMthos.getSizeOfImage(originalImage, w, h, aspectRatio);

					ancho = punto.x;

					alto = punto.y;

				}

				else {

					ancho = originalImage.getWidth();

					alto = originalImage.getHeight();

				}

				repaint();

			}

		}

		catch (Exception e) {

		}

	}

	@Override
	public void paint(Graphics g) {

		try {

			if (originalImage != null) {

				imagen = resizeImage(originalImage, ancho, alto);

				if (aspectRatio) {

					g.drawImage(imagen, 0, 0, ancho, alto, null);

				}

				else {

					g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);

				}

				g.dispose();

			}

		}

		catch (Exception e) {

		}

	}

}
