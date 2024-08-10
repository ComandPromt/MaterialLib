package com.label.others;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class LabelImage extends JLabel {

	private Icon icono;

	private ImageIcon imagenIcon;

	private Image image;

	private Image image_default;

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

	public void setIcon(Icon icon) {

		if (icon != null) {

			this.icono = icon;

			this.imagenIcon = (ImageIcon) this.icono;

			this.image = this.imagenIcon.getImage();

		}

		else {

			this.image = this.image_default;

		}

		repaint();

	}

}
