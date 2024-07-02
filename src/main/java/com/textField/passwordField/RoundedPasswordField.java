package com.textField.passwordField;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPasswordField;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;

import com.contextmenu.DefaultContextMenu;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class RoundedPasswordField extends JPasswordField {

	private int angle;

	private Color borderColor;

	private int thickness;

	private String text;

	private Color fondo;

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

	public RoundedPasswordField() {

		this(20, 15);

	}

	public RoundedPasswordField(int columns, int angle) {

		super(columns);

		setHorizontalAlignment(SwingConstants.CENTER);

		setForeground(Color.BLUE);

		setBackground(Color.PINK);

		this.angle = angle;

		this.borderColor = Color.BLACK;

		this.thickness = 1;

		setOpaque(false);

		setMargin(new Insets(0, columns, 0, 0));

		DefaultContextMenu.addDefaultContextMenu(this);

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(getBackground());

		g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, angle, angle));

		int triangleWidth = 15;

		int[] xPoints = { 5, 5 + triangleWidth, 5 };

		int[] yPoints = { 0, getHeight() / 2, getHeight() };

		g2.setColor(getBackground());

		g2.fillPolygon(xPoints, yPoints, 3);

		super.paintComponent(g);

		g2.dispose();

	}

	@Override
	protected void paintBorder(Graphics g) {

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(borderColor);

		g2.setStroke(new java.awt.BasicStroke(thickness));

		if (thickness == 1) {

			g2.draw(new RoundRectangle2D.Float(thickness / 2f, (thickness / 2f), getWidth() - thickness,
					(getHeight() - thickness) - 1, angle, angle));

		}

		else {

			g2.draw(new RoundRectangle2D.Float((thickness / 2f) - 1, (thickness / 2f) - 1, (getWidth() - thickness) + 1,
					(getHeight() - thickness) + 1, angle, angle));

		}

		g2.dispose();

	}

	public void setBorderColor(Color borderColor) {

		this.borderColor = borderColor;

		repaint();

	}

	public void setAngle(int angle) {

		this.angle = angle;

		repaint();

	}

	public void setThickness(int thickness) {

		this.thickness = thickness;

		repaint();

	}

}
