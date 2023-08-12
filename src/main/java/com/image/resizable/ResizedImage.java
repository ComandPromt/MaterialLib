package com.image.resizable;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import util.Mthos;

@SuppressWarnings("serial")

public class ResizedImage extends JLabel {

	private int ancho;

	private int alto;

	private boolean aspectRatio;

	public void setAspectRatio(boolean aspectRatio) {

		this.aspectRatio = aspectRatio;

	}

	public boolean isAspectRatio() {

		return aspectRatio;

	}

	public ResizedImage() {

	}

	public int getAncho() {

		return ancho;

	}

	public int getAlto() {

		return alto;

	}

	@Override
	public void setIcon(Icon icon) {

		if (icon != null && getWidth() > 0 && getHeight() > 0 && icon.getIconWidth() != getWidth()
				&& icon.getIconHeight() != getHeight()) {

			ancho = getWidth();

			alto = getHeight();

			if (this.aspectRatio) {

				alto = (getWidth() * icon.getIconHeight()) / icon.getIconWidth();

			}

			icon = new ImageIcon(Mthos.getScaledImage(Mthos.iconToImage(icon), ancho, alto));

		}

		super.setIcon(icon);

	}

}
