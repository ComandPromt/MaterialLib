package com.textField.passwordField;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

import com.panels.round.RoundedPanel;

@SuppressWarnings("serial")

public class PasswordPanel extends RoundedPanel {

	JPasswordField panel;

	private Icon prefixIcon;

	private Icon suffixIcon;

	private int left;

	public void setSuffixIcon(ImageIcon icon) {

		this.suffixIcon = icon;

		repaint();

	}

	public void setPrefixIcon(ImageIcon icon) {

		this.prefixIcon = icon;

		repaint();

	}

	public void setBackground(Color color) {

		try {

			super.setBackground(color);

			panel.setBackground(color);

		}

		catch (Exception e) {

		}

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		if (prefixIcon != null) {

			Image prefix = ((ImageIcon) prefixIcon).getImage();

			int y = (getHeight() - prefixIcon.getIconHeight()) / 2;

			g2.drawImage(prefix, thickness + left, y, this);

			panel.setBounds((prefixIcon.getIconWidth() + thickness) + 1, thickness, calcularAncho(), calcularAltura());

		}

		else {

			panel.setBounds(thickness + ((super.radius * 34) / 90), thickness, calcularAncho(), calcularAltura());

		}

		panel.setBounds(panel.getX(), panel.getY() + 1, panel.getWidth(), panel.getHeight());

		if (suffixIcon != null) {

			Image suffix = ((ImageIcon) suffixIcon).getImage();

			int y = (getHeight() - suffixIcon.getIconHeight()) / 2;

			try {

				g2.drawImage(suffix, getWidth() - ((prefixIcon.getIconWidth() + thickness) + 1), y, this);

			}

			catch (Exception e) {

				g2.drawImage(suffix, (getWidth() - suffixIcon.getIconWidth()) - thickness, y, this);

			}

		}

	}

	private int calcularAltura() {

		return getHeight() - Math.round(((thickness) * 2.5f));

	}

	private int calcularAncho() {

		int ancho = 0;

		try {

			if (prefixIcon != null) {

				ancho = ((((getWidth() - prefixIcon.getIconWidth()) * 275) / 288) - thickness);

			}

			else {

				ancho = (getWidth() - thickness);

			}

			if (suffixIcon != null) {

				ancho -= suffixIcon.getIconWidth();

			}

			if (super.radius > 0) {

				ancho -= ((super.radius * 34) / 90) * 2;

			}

			if (suffixIcon != null && prefixIcon == null) {

				ancho -= thickness;

			}

			else if (prefixIcon == null && suffixIcon == null) {

				ancho -= thickness;

				ancho -= 1;

			}

		}

		catch (Exception e) {

		}

		return ancho;

	}

	public PasswordPanel() {

		this(true);

	}

	public PasswordPanel(boolean icon) {

		setLayout(null);

		if (icon) {

			panel = new PasswordFieldWithIcon();

		}

		else {

			panel = new PasswordIcons();

		}

		add(panel);

	}

}
