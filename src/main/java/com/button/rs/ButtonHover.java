package com.button.rs;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;

@SuppressWarnings("serial")

public class ButtonHover extends JButton {

	private Color colorHover = new Color(67, 150, 209);

	private Color colorTextHover = new Color(255, 255, 255);

	private Color colorText = new Color(255, 255, 255);

	private Font f = new Font("Tahoma", 1, 14);

	private boolean isHover = false;

	Shape shape;

	public ButtonHover(String text) {

		this();

		setText(text);

	}

	public ButtonHover() {

		setFont(this.f);

		setPreferredSize(new Dimension(200, 40));

		setSize(new Dimension(200, 40));

		setBackground(new Color(0, 112, 192));

		setForeground(this.colorText);

		setCursor(new Cursor(12));

		setContentAreaFilled(false);

	}

	protected void paintComponent(Graphics g) {

		if (getModel().isRollover()) {

			g.setColor(this.colorHover);

			this.isHover = true;

		}

		else {

			g.setColor(getBackground());

			this.isHover = false;

		}

		if (this.isHover) {

			setForeground(this.colorTextHover);

		}

		else {

			setForeground(this.colorText);

		}

		g.fillRect(0, 0, (getSize()).width, (getSize()).height);

		super.paintComponent(g);

	}

	public boolean contains(int x, int y) {

		if (this.shape == null || !this.shape.getBounds().equals(getBounds()))
			this.shape = new Ellipse2D.Float(0.0F, 0.0F, getWidth(), getHeight());

		return this.shape.contains(x, y);

	}

	public Color getColorHover() {

		return this.colorHover;

	}

	public void setColorHover(Color colorHover) {

		this.colorHover = colorHover;

	}

	public Color getColorTextHover() {

		return this.colorTextHover;

	}

	public void setColorTextHover(Color colorTextHover) {

		this.colorTextHover = colorTextHover;

	}

	public Color getColorText() {

		return this.colorText;

	}

	public void setColorText(Color colorText) {

		this.colorText = colorText;

	}

}
