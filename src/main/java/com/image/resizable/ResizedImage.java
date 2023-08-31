package com.image.resizable;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.JLabel;

import util.Mthos;

@SuppressWarnings("serial")

public class ResizedImage extends JLabel {

	private int ancho;

	private int alto;

	private boolean aspectRatio;

	private BufferedImage originalImage;

	private BufferedImage resizedImage;

	private int w;

	private int h;

	private Icon icono;

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

		BufferedImage resizedImage = new BufferedImage(scaledWidth, scaledHeight, originalImage.getType());

		Graphics2D g2d = resizedImage.createGraphics();

		g2d.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);

		g2d.dispose();

		return resizedImage;

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

				originalImage = Mthos.loadFileImage(ruta);

				if (aspectRatio) {

					Point punto = Mthos.getSizeOfImage(originalImage, w, h, aspectRatio);

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

				resizedImage = resizeImage(originalImage, ancho, alto);

				if (aspectRatio) {

					g.drawImage(resizedImage, 0, 0, ancho, alto, null);

				}

				else {

					g.drawImage(resizedImage, 0, 0, getWidth(), getHeight(), null);

				}

				g.dispose();

			}

		}

		catch (Exception e) {

		}

	}

}
