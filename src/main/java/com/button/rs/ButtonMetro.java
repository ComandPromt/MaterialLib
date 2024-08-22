package com.button.rs;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class ButtonMetro extends JButton implements MouseListener, MouseMotionListener {

	private Color colorNormal;

	private Color colorPressed;

	private Color colorHover;

	private Border bordeMoved;

	private Color colorTextNormal;

	private Color colorTextPressed;

	private Color colorTextHover;

	private Font f;

	public ButtonMetro() {

		this("");

	}

	public ButtonMetro(String text) {

		setFocusPainted(false);

		colorNormal = new Color(0, 139, 139);

		colorPressed = new Color(0, 0, 0);

		colorHover = new Color(38, 86, 186);

		bordeMoved = javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(255, 255, 255));

		colorTextNormal = new Color(255, 255, 255);

		colorTextPressed = new Color(255, 255, 255);

		colorTextHover = new Color(255, 255, 255);

		f = new Font("Tahoma", Font.BOLD, 14);

		setFont(f);

		setPreferredSize(new Dimension(150, 35));

		setSize(new Dimension(150, 35));

		setBorder(null);

		setContentAreaFilled(false);

		setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		setBackground(colorNormal);

		setForeground(colorTextNormal);

		setOpaque(true);

		setVisible(true);

		addMouseListener(this);

		addMouseMotionListener(this);

		setText(text);

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

		setForeground(colorTextPressed);

		setBackground(colorPressed);

	}

	public void mouseReleased(MouseEvent e) {

		setBackground(colorNormal);

		setForeground(colorTextNormal);

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

		setBorder(null);

		setBackground(colorNormal);

		setForeground(colorTextNormal);

	}

	public Color getColorPressed() {

		return colorPressed;

	}

	public void setColorPressed(Color colorPressed) {

		this.colorPressed = colorPressed;

	}

	public Color getColorTextPressed() {

		return colorTextPressed;

	}

	public void setColorTextPressed(Color colorTextPressed) {

		this.colorTextPressed = colorTextPressed;

	}

	public Color getColorHover() {

		return colorHover;

	}

	public void setColorHover(Color colorHover) {

		this.colorHover = colorHover;

	}

	public Color getColorTextHover() {

		return colorTextHover;

	}

	public void setColorTextHover(Color colorTextHover) {

		this.colorTextHover = colorTextHover;

	}

	public Color getColorNormal() {

		return colorNormal;

	}

	public void setColorNormal(Color colorNormal) {

		setBackground(colorNormal);

		this.colorNormal = colorNormal;

	}

	public Color getColorTextNormal() {

		return colorTextNormal;

	}

	public void setColorTextNormal(Color colorTextNormal) {

		setForeground(colorTextNormal);

		this.colorTextNormal = colorTextNormal;

	}

	public Border getBorde() {

		return bordeMoved;

	}

	public void setBorde(Border bordeMoved) {

		this.bordeMoved = bordeMoved;

	}

	public void setColorBorde(Color color) {

		this.bordeMoved = javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, color);

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		setForeground(colorTextHover);

		setBackground(colorHover);

		setBorder(bordeMoved);

	}

}
