package com.textField.text;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;

import com.contextmenu.DefaultContextMenu;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class TextFieldConSombra extends JTextField {

	private int direccionDeSombra;
	private int distanciaDeSombra;
	private boolean vertical;
	private float borde;
	private Float opacidad;
	private String text;
	private Color fondo;
	private Color colorTexto;
	private Color border;
	private Font fuente;
	private boolean round;

	private String placeholder;
	private Color placeholderColor;
	private PlaceholderPosition placeholderVerticalPosition;
	private HorizontalPosition placeholderHorizontalPosition;

	private Font placeFont;

	public void setPlaceholderFont(Font font) {

		try {

			placeFont = font;

			repaint();

		}

		catch (Exception e) {

		}

	}

	public enum PlaceholderPosition {
		TOP, CENTER, BOTTOM
	}

	public enum HorizontalPosition {
		LEFT, CENTER, RIGHT
	}

	public TextFieldConSombra() {

		this("");

	}

	public TextFieldConSombra(String text) {

		round = true;

		direccionDeSombra = 60;

		distanciaDeSombra = 1;

		vertical = true;

		borde = 2f;

		opacidad = 1f;

		placeholderColor = Color.GRAY;

		placeholderVerticalPosition = PlaceholderPosition.CENTER;

		placeholderHorizontalPosition = HorizontalPosition.LEFT;

		setOpaque(false);

		setFont(new Font("Dialog", Font.PLAIN, 20));

		setText(text);

		placeFont = getFont();

		DefaultContextMenu.addDefaultContextMenu(this);

		addFocusListener(new java.awt.event.FocusAdapter() {

			public void focusGained(java.awt.event.FocusEvent e) {

				repaint();

			}

			public void focusLost(java.awt.event.FocusEvent e) {
				repaint();
			}
		});
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
		repaint();
	}

	public void setPlaceholderColor(Color placeholderColor) {
		this.placeholderColor = placeholderColor;
		repaint();
	}

	public void setPlaceholderVerticalPosition(PlaceholderPosition placeholderVerticalPosition) {
		this.placeholderVerticalPosition = placeholderVerticalPosition;
		repaint();
	}

	public void setPlaceholderHorizontalPosition(HorizontalPosition placeholderHorizontalPosition) {
		this.placeholderHorizontalPosition = placeholderHorizontalPosition;
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
			} catch (Exception e) {
				font = new Font("Dialog", Font.PLAIN, 20);
			}
		}
		this.text = text;
		this.fondo = background;
		this.colorTexto = foreground;
		this.border = border;
		this.fuente = font;
		super.setToolTipText(text);
	}

	@Override
	public JToolTip createToolTip() {
		if (text == null || fondo == null || colorTexto == null || border == null) {
			return super.createToolTip();
		} else {
			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, colorTexto, border, fuente);
			tip.setComponent(this);
			return tip;
		}
	}

	@Override
	protected void paintBorder(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Stroke st = g2.getStroke();
		g2.setStroke(new BasicStroke(borde));
		if (round) {
			g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight() / 3, getHeight() / 3);
		} else {
			g2.drawRect(0, 0, getWidth(), getHeight());
		}
		g2.setStroke(st);
	}

	@Override
	protected void paintComponent(Graphics g) {

		float x1 = 0;

		float x2 = 0;

		float y1 = 0;

		float y2 = getHeight();

		if (!vertical) {

			x1 = 0;

			y1 = 0;

			x2 = getWidth();

			y2 = 0;

		}

		Graphics2D g2 = (Graphics2D) g;

		Paint oldPaint = g2.getPaint();

		Shape rect;

		if (round) {

			rect = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, getHeight() / 3, getHeight() / 3);

		}

		else {

			rect = new Rectangle2D.Double(0, 0, getWidth(), getHeight());

		}

		g2.clip(rect);

		g2.setPaint(new GradientPaint(x1, y1, getBackground(), x2, y2, getBackground().darker()));

		g2.fill(rect);

		g2.setPaint(oldPaint);

		super.paintComponent(g);

		if (getText().isEmpty() && placeholder != null) {

			g2.setColor(placeholderColor);

			g2.setFont(placeFont);

			int y = calculatePlaceholderVerticalPosition(g2);

			int x = calculatePlaceholderHorizontalPosition(g2);

			g2.drawString(placeholder, x, y);

		}

	}

	private int calculatePlaceholderVerticalPosition(Graphics2D g2) {
		int y = getHeight() / 2 + g2.getFontMetrics().getAscent() / 2 - 2;
		if (placeholderVerticalPosition == PlaceholderPosition.TOP) {
			y = g2.getFontMetrics().getAscent() + getInsets().top;
		} else if (placeholderVerticalPosition == PlaceholderPosition.BOTTOM) {
			y = getHeight() - g2.getFontMetrics().getDescent() - getInsets().bottom;
		}
		return y;
	}

	private int calculatePlaceholderHorizontalPosition(Graphics2D g2) {
		int x = getInsets().left;
		if (placeholderHorizontalPosition == HorizontalPosition.CENTER) {
			x = (getWidth() - g2.getFontMetrics().stringWidth(placeholder)) / 2;
		} else if (placeholderHorizontalPosition == HorizontalPosition.RIGHT) {
			x = getWidth() - getInsets().right - g2.getFontMetrics().stringWidth(placeholder);
		}
		return x;
	}

	public Float getOpacidad() {
		return opacidad;
	}

	public void setOpacidad(Float opacidad) {
		if (opacidad < 0f) {
			opacidad = 0f;
		} else if (opacidad > 1f) {
			opacidad = 1f;
		}
		this.opacidad = opacidad;
	}

	public int getDireccionDeSombra() {
		return direccionDeSombra;
	}

	public void setDireccionDeSombra(int direccionDeSombra) {
		this.direccionDeSombra = direccionDeSombra;
		repaint();
	}

	public int getDistanciaDeSombra() {
		return distanciaDeSombra;
	}

	public void setDistanciaDeSombra(int distanciaDeSombra) {
		this.distanciaDeSombra = distanciaDeSombra;
		repaint();
	}

	public boolean isVertical() {
		return vertical;
	}

	public void setVertical(boolean vertical) {
		this.vertical = vertical;
		repaint();
	}

	public float getBorde() {
		return borde;
	}

	public void setBorde(float borde) {
		this.borde = borde;
		updateInsets();
		repaint();
	}

	private void updateInsets() {
		int borderPadding = (int) borde + 5; // Adding some extra padding
		setBorder(new EmptyBorder(borderPadding, borderPadding, borderPadding, borderPadding));
	}
}
