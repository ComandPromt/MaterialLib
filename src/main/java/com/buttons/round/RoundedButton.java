package com.buttons.round;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import com.panels.RoundedBorder;
import com.panels.RoundedPanel;

@SuppressWarnings("serial")
public class RoundedButton extends JButton {

	BufferedImage output;

	Graphics2D g2;

	public Icon imageIcon;

	private RoundedBorder borde;

	public RoundedBorder getBorde() {

		return borde;

	}

	public Icon getImageIcon() {

		return imageIcon;

	}

	@Override
	public void setHorizontalAlignment(int align) {

		this.getBorde().setHorizontalAlignment(align);

	}

	private String limpiarCadena(String cadena) {

		cadena = cadena.replace("file:/", "");

		return cadena;

	}

	@Override
	public void setForeground(Color fg) {

		try {

			borde.setTextColor(fg);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setBackground(Color bg) {

		try {

			borde.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public RoundedButton(String text) {

		output = new BufferedImage(2000, 2000, BufferedImage.TYPE_INT_ARGB);

		setContentAreaFilled(false);

		setFocusPainted(false);

		setText(text);

		borde = new RoundedBorder(text, 15, null, null, null);

		borde.setRounded(true);

		this.setBorder(borde);

		setHorizontalAlignment(SwingConstants.LEFT);

	}

	public void setIcon(String icon, int width, int height, int stroke) {

		icon = limpiarCadena(icon);

		g2 = output.createGraphics();

		imageIcon = new RoundedPanel(width, height, stroke, icon).pintar(g2);

		super.setIcon(imageIcon);

	}

	public void setRounded(boolean rounded) {

		borde.setRounded(rounded);

	}

}
