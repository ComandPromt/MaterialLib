package com.buttons.simple;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JToolTip;
import javax.swing.border.Border;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class MetroButton extends JButton implements MouseListener, MouseMotionListener {

	private Color colorNormal;

	private Color colorPressed;

	private Color colorHover;

	private Border bordeMoved;

	private Color colorTextNormal;

	private Color colorTextPressed;

	private Color colorTextHover;

	private Font f;

	private String text;

	private Color fondo;

	private Color colorTexto;

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

		this.fondo = background;

		this.colorTexto = foreground;

		this.border = border;

		this.fuente = font;

		setToolTipText(text);

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

	public MetroButton(String text) {

		bordeMoved = javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0,

				new java.awt.Color(255, 255, 255));

		colorTextNormal = Color.BLACK;

		colorTextPressed = Color.BLACK;

		colorTextHover = Color.BLACK;

		f = new Font("Dialog", Font.PLAIN, 25);

		colorNormal = Color.WHITE;

		colorPressed = Color.WHITE;

		colorHover = Color.WHITE;

		this.setFont(f);

		this.setPreferredSize(new Dimension(150, 35));

		this.setSize(new Dimension(150, 35));

		this.setBorder(null);

		this.setText(text);

		this.setContentAreaFilled(false);

		this.setFocusPainted(false);

		this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		this.setBackground(this.colorNormal);

		this.setForeground(this.colorTextNormal);

		this.setOpaque(true);

		this.setVisible(true);

		this.addMouseListener(this);

		this.addMouseMotionListener(this);

	}

	@Override

	public void mousePressed(MouseEvent e) {

		this.setForeground(this.colorTextPressed);

		this.setBackground(this.colorPressed);

	}

	@Override

	public void mouseReleased(MouseEvent e) {

		this.setBackground(this.colorNormal);

		this.setForeground(this.colorTextNormal);

	}

	@Override

	public void mouseExited(MouseEvent e) {

		this.setBorder(null);

		this.setBackground(this.colorNormal);

		this.setForeground(this.colorTextNormal);

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

		this.setBackground(colorNormal);

		this.colorNormal = colorNormal;

	}

	public Color getColorTextNormal() {

		return colorTextNormal;

	}

	public void setColorTextNormal(Color colorTextNormal) {

		this.setForeground(colorTextNormal);

		this.colorTextNormal = colorTextNormal;

	}

	public Border getColorBorde() {

		return bordeMoved;

	}

	public void setColorBorde(Border bordeMoved) {

		this.bordeMoved = bordeMoved;

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		this.setForeground(this.colorTextHover);

		this.setBackground(this.colorHover);

		this.setBorder(bordeMoved);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

}
