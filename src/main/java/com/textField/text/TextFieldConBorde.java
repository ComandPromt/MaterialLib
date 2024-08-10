package com.textField.text;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JToolTip;

import com.contextmenu.DefaultContextMenu;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class TextFieldConBorde extends JTextField {

	private boolean pintarBorde;

	private int grosor;

	private Color bordeActivo;

	private Color bordeInactivo;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private String placeholder;

	private Color placeholderColor;

	private PlaceholderPosition placeholderVerticalPosition;

	private HorizontalPosition placeholderHorizontalPosition;

	private Font placeFont;

	public enum PlaceholderPosition {

		TOP, CENTER, BOTTOM

	}

	public enum HorizontalPosition {

		LEFT, CENTER, RIGHT

	}

	public TextFieldConBorde() {

		this("");

	}

	public TextFieldConBorde(String text) {

		bordeActivo = Color.BLACK;

		bordeInactivo = Color.LIGHT_GRAY;

		grosor = 4;

		placeholderColor = Color.GRAY;

		placeholderVerticalPosition = PlaceholderPosition.CENTER;

		placeholderHorizontalPosition = HorizontalPosition.LEFT;

		setFont(getFont().deriveFont(Font.PLAIN, 20));

		setText(text);

		addMouseListener(new MouseAdapter() {

			@Override

			public void mouseEntered(MouseEvent e) {

				pintarBorde = true;

				repaint();

			}

			@Override

			public void mouseExited(MouseEvent e) {

				pintarBorde = false;

				repaint();

			}

		});

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

			}

			catch (Exception e) {

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

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, colorTexto, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setStroke(new BasicStroke(grosor));

		if (pintarBorde) {

			g.setColor(bordeActivo);

		}

		else {

			g.setColor(bordeInactivo);

		}

		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

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

		}

		else if (placeholderVerticalPosition == PlaceholderPosition.BOTTOM) {

			y = getHeight() - g2.getFontMetrics().getDescent() - getInsets().bottom;

		}

		return y;

	}

	private int calculatePlaceholderHorizontalPosition(Graphics2D g2) {

		int x = getInsets().left;

		switch (placeholderHorizontalPosition) {

		case CENTER:
			x = (getWidth() - g2.getFontMetrics().stringWidth(placeholder)) / 2;

			break;

		case RIGHT:

			x = getWidth() - getInsets().right - g2.getFontMetrics().stringWidth(placeholder);

			x -= 5;

			break;

		default:

			x += 5;

			break;

		}

		return x;

	}

	public Color getBordeActivo() {

		return bordeActivo;

	}

	public void setBordeActivo(Color bordeActivo) {

		this.bordeActivo = bordeActivo;

		repaint();

	}

	public Color getBordeInactivo() {

		return bordeInactivo;

	}

	public void setBordeInactivo(Color bordeInactivo) {

		this.bordeInactivo = bordeInactivo;

		repaint();

	}

	public int getGrosor() {

		return grosor;

	}

	public void setGrosor(int grosor) {

		if (grosor < 0) {

			grosor = 0;

		}

		this.grosor = grosor;

		repaint();

	}

	public void setPlaceholderFont(Font font) {

		try {

			placeFont = font;

			repaint();

		}

		catch (Exception e) {

		}

	}

}
