package com.jmenu.paneles;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class IconoCabecera extends JLabel {

	public boolean icono;

	public Icon icon;

	private Icon originalIcon;

	public IconoCabecera() {
	}

	public IconoCabecera(int width, int height) {

		setSize(width, height);

	}

	@Override
	public void setIcon(Icon icon) {

		if (icon != null && icon.getIconWidth() > 0 && icon.getIconHeight() > 0) {

			icono = true;

			this.originalIcon = icon;

			repaint();

		}

	}

	private Icon resizeIcon(Icon icon, int width, int height) {

		if (icon instanceof ImageIcon) {

			Image originalImage = ((ImageIcon) icon).getImage();

			Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

			return new ImageIcon(resizedImage);

		}

		else {

			return icon;
		}

	}

	@Override
	public void paintComponent(Graphics g) {

		if (!icono) {

			g.setColor(getBackground());

			g.fillRect(0, 0, getWidth(), getHeight());

			final int UNIDAD = Math.round(getHeight() * 0.2f);

			g.setColor(getForeground());

			g.fillRect(0, 0, getWidth(), UNIDAD);

			g.fillRect(0, UNIDAD * 2, getWidth(), UNIDAD);

			g.fillRect(0, UNIDAD * 4, getWidth(), UNIDAD);

		}

		else if (originalIcon != null) {

			icon = resizeIcon(originalIcon, getWidth(), getHeight());

			super.setIcon(icon);

			super.paintComponent(g);

		}

	}

}
