package com.buttons.animated;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class ButtonCustom extends JButton {

	private Color background;

	private Color colorHover;

	private Color colorPressed;

	private boolean mouseOver;

	private String text;

	private Color backgroundColor;

	private Color foreground;

	private Color border;

	private Font fuente;

	public void setToolTip(String text, Color background, Color foreground, Color border, Font font) {

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

			font = getFont().deriveFont(14f);

		}

		this.text = text;

		this.backgroundColor = background;

		this.foreground = foreground;

		this.border = border;

		this.fuente = font;

		setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || backgroundColor == null || foreground == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, backgroundColor, foreground, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public ButtonCustom(String text) {

		background = new Color(69, 191, 71);

		colorHover = new Color(76, 206, 78);

		colorPressed = new Color(63, 175, 65);

		mouseOver = false;

		setContentAreaFilled(false);

		setFocusPainted(false);

		setBorderPainted(false);

		setText(text);

		init();

	}

	private void init() {

		setBorder(null);

		setFocusPainted(false);

		setContentAreaFilled(false);

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setBackground(background);

		setForeground(Color.WHITE);

		setOpaque(true);

		addMouseListener(new MouseAdapter() {

			@Override

			public void mouseEntered(MouseEvent e) {

				mouseOver = true;

				ButtonCustom.super.setBackground(getColorHover());

			}

			@Override

			public void mouseExited(MouseEvent e) {

				mouseOver = false;

				ButtonCustom.super.setBackground(background);

			}

			@Override

			public void mousePressed(MouseEvent e) {

				ButtonCustom.super.setBackground(getColorPressed());

			}

			@Override

			public void mouseReleased(MouseEvent e) {

				if (mouseOver) {

					ButtonCustom.super.setBackground(getColorHover());

				}

				else {

					ButtonCustom.super.setBackground(background);

				}

			}

		});

	}

	@Override

	public void setBackground(Color bg) {

		background = bg;

		super.setBackground(bg);

	}

	public Color getColorHover() {

		return colorHover;

	}

	public void setColorHover(Color colorHover) {

		this.colorHover = colorHover;

	}

	public Color getColorPressed() {

		return colorPressed;

	}

	public void setColorPressed(Color colorPressed) {

		this.colorPressed = colorPressed;

	}

}
