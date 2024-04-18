package com.jmenu.paneles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class TextoConIcono extends JPanel {

	private JLabel lblNewLabel;

	private JLabel lblNewLabel_1;

	private int ancho;

	private int icono;

	private int alto;

	private Icon icon;

	public void setAlign(int align) {

		try {

			lblNewLabel.setHorizontalAlignment(align);

			lblNewLabel_1.setHorizontalAlignment(align);

		}

		catch (Exception e) {

		}

	}

	public void left(boolean left) {

		try {

			if (left) {

				lblNewLabel.setBounds(0, 0, icono, alto);

				lblNewLabel_1.setBounds(icono, 0, ancho - icono, alto);

			}

			else {

				lblNewLabel_1.setBounds(0, 0, ancho - icono, alto);

				lblNewLabel.setBounds(ancho - icono, 0, icono, alto);

			}

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setForeground(Color fg) {

		try {

			lblNewLabel_1.setForeground(fg);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setBackground(Color bg) {

		try {

			lblNewLabel.setBackground(bg);

			lblNewLabel_1.setBackground(bg);

			super.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public void setItemMenu(Color itemMenu) {

		try {

			lblNewLabel.setForeground(itemMenu);

			lblNewLabel_1.setForeground(itemMenu);

		}

		catch (Exception e) {

		}

	}

	public TextoConIcono(String text, Icon icon, int icono, Color fondo) {

		setLayout(null);

		this.icono = icono;

		this.icon = icon;

		lblNewLabel = new JLabel("");

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		lblNewLabel_1 = new JLabel(text);

		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		lblNewLabel_1.setText(text);

		setBackground(fondo);

		add(lblNewLabel);

		add(lblNewLabel_1);

	}

	@Override
	public void paint(Graphics g) {

		try {

			ancho = getWidth();

			alto = getHeight();

			left(true);

			if (icon != null && icon instanceof ImageIcon) {

				ImageIcon imageIcon = (ImageIcon) icon;

				Image image = imageIcon.getImage();

				Image newImage = image.getScaledInstance(icono, alto, Image.SCALE_SMOOTH);

				ImageIcon newIcon = new ImageIcon(newImage);

				lblNewLabel.setIcon(newIcon);

			}

		}

		catch (Exception e) {

		}

		super.paint(g);

	}

}
