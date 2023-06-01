package com.buttons.round;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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

	public void setBorderColor(Color color) {

		borde.setExternal(color);

	}

	public void setInnerColor(Color color) {

		borde.setInner(color);

	}

	public RoundedBorder getBorde() {

		return borde;

	}

	private void alinear(int alignment) {

		this.getBorde().setFuente(getFont());

		this.getBorde().setHorizontalAlignment(alignment);

		repaint();

	}

	@Override
	public void setHorizontalTextPosition(int alignment) {

		alinear(alignment);

	}

	@Override
	public void setHorizontalAlignment(int alignment) {

		alinear(alignment);

	}

	public Icon getImageIcon() {

		return imageIcon;

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

	@Override
	public void setText(String text) {

		try {

			borde.setText(text);

			repaint();

		}

		catch (Exception e) {

		}

	}

	public RoundedButton() {

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {

				setHorizontalAlignment(SwingConstants.CENTER);

				repaint();

			}

		});

		output = new BufferedImage(2000, 2000, BufferedImage.TYPE_INT_ARGB);

		setContentAreaFilled(false);

		setFocusPainted(false);

		setText("");

		borde = new RoundedBorder(getText(), 15, null, null, null);

		borde.setRounded(true);

		borde.setFuente(new Font("Tahoma", Font.PLAIN, 16));

		this.setBorder(borde);

		setHorizontalAlignment(SwingConstants.LEFT);

		setBackground(new Color(240, 240, 240));

	}

	public RoundedButton(String text) {

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {

				setHorizontalAlignment(SwingConstants.CENTER);

				repaint();

			}

		});

		output = new BufferedImage(2000, 2000, BufferedImage.TYPE_INT_ARGB);

		setContentAreaFilled(false);

		setFocusPainted(false);

		setText(text);

		borde = new RoundedBorder(text, 15, null, null, null);

		borde.setRounded(true);

		this.setBorder(borde);

		setHorizontalAlignment(SwingConstants.LEFT);

		setBackground(new Color(240, 240, 240));

	}

	public void setIcon(String icon, int width, int height, int stroke) {

		icon = limpiarCadena(icon);

		g2 = output.createGraphics();

		imageIcon = new RoundedPanel(width, height, stroke, icon).pintar(g2);

		super.setIcon(imageIcon);

	}

	public void setRounded(boolean rounded) {

		borde.setRounded(rounded);

		repaint();

	}

}
