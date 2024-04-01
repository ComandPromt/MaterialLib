package com.textField.passwordField;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JToolTip;

import com.contextmenu.DefaultContextMenu;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class PasswordIcons extends JPasswordField {

	private Icon prefixIcon;

	private Icon suffixIcon;

	private int y;

	private String text;

	private Color fondo;

	private Color border;

	private Font fuente;

	private int angle;

	private Color borderColor;

	private int thickness;

	private int restar;

	private int left;

	private int right;

	public void setRight(int right) {

		this.right = right;

	}

	public void setLeft(int left) {

		this.left = left;

		repaint();

	}

	public void setBorderColor(Color borderColor) {

		this.borderColor = borderColor;

		repaint();

	}

	public void setAngle(int angle) {

		if (angle < 0) {

			angle = 0;

		}

		else if (angle > 360) {

			angle = 360;

		}

		this.angle = angle;

		repaint();

	}

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

		this.fondo = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || fondo == null || fondo == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, fondo, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public Icon getPrefixIcon() {

		return prefixIcon;

	}

	public void setPrefixIcon(Icon prefixIcon) {

		this.prefixIcon = prefixIcon;

		repaint();

	}

	public Icon getSuffixIcon() {

		return suffixIcon;

	}

	public void setSuffixIcon(Icon suffixIcon) {

		this.suffixIcon = suffixIcon;

		repaint();

	}

	public PasswordIcons() {

		left = 3;

		right = 0;

		setFont(getFont().deriveFont(30f));

		setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

		DefaultContextMenu.addDefaultContextMenu(this);

	}

	public void setThickness(int thickness) {

		if (thickness < 0) {

			thickness = 0;

		}

		this.thickness = thickness;

		repaint();

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		paintIcon(g);

		g.setColor(borderColor);

		if (thickness > 0) {

			Graphics2D g2 = (Graphics2D) g;

			g2.setStroke(new BasicStroke(thickness));

			int medioThickness = thickness / 2;

			int menosThickness = thickness;

			menosThickness--;

			g.drawRoundRect(medioThickness, medioThickness, getWidth() - (menosThickness),
					getHeight() - (menosThickness), angle, angle);

		}

	}

	private void paintIcon(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		if (prefixIcon != null) {

			Image prefix = ((ImageIcon) prefixIcon).getImage();

			int y = (getHeight() - prefixIcon.getIconHeight()) / 2;

			g2.drawImage(prefix, thickness + left, y, this);

		}

		if (suffixIcon != null) {

			Image suffix = ((ImageIcon) suffixIcon).getImage();

			y = (getHeight() - suffixIcon.getIconHeight()) / 2;

			restar = thickness;

			restar--;

			g2.drawImage(suffix, ((getWidth() - suffixIcon.getIconWidth()) - restar) - right, y, this);

		}

	}

}
