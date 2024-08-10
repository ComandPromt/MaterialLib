package com.panels.image;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class PanelImage extends JPanel {

	private Icon icono;

	ImageIcon imagenIcon;

	private Image image;

	private Image image_default;

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

	public PanelImage() {

		setSize(150, 150);

	}

	protected void paintComponent(Graphics g) {

		Dimension height = getSize();

		g.drawImage(this.image, 0, 0, height.width, height.height, null);

		setOpaque(false);

		super.paintComponent(g);

	}

	public void setImagen(String icon) {

		setImagen(new ImageIcon(icon));

	}

	public void setImagen(Icon icon) {

		try {

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

		catch (Exception e) {

		}

	}

}
