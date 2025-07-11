package com.label.others;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class LabelImage extends JLabel implements Serializable {

	private transient ImageIcon imagenIcon;

	private transient Image image;

	private transient Image imageDefault;

	private String icono;

	public String getIcono() {

		return icono;

	}

	public ImageIcon getImagenIcon() {

		return imagenIcon;

	}

	public LabelImage(String text) {

		this();

		setText(text);

	}

	public LabelImage() {

		setPreferredSize(new Dimension(150, 150));

		setSize(new Dimension(150, 150));

	}

	protected void paintComponent(Graphics g) {

		Dimension height = getSize();

		g.drawImage(this.image, 0, 0, height.width, height.height, null);

		setOpaque(false);

		super.paintComponent(g);

	}

	public void setIcon(String icon) {

		icono = icon;

		setIcon(new ImageIcon(icon));

	}

	@Override
	public void setIcon(Icon icon) {

		Icon icono;

		if (icon != null) {

			icono = icon;

			this.imagenIcon = (ImageIcon) icono;

			this.image = this.imagenIcon.getImage();

		}

		else {

			this.image = this.imageDefault;

		}

		repaint();

	}

}
