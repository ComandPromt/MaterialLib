package com.checkbox;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JCheckBox;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class CheckBoxCustom extends JCheckBox {

	int border;

	Color checkColor;

	String texto;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color borde;

	private Font fuente;

	private Color colorActivo;

	private Color colorReposo;

	@Override
	public void setBackground(Color bg) {

		colorReposo = bg;

		repaint();

	}

	public Color getCheckColor() {

		return checkColor;

	}

	public Color getColorActivo() {

		return colorActivo;

	}

	public Color getColorReposo() {

		return colorReposo;

	}

	public void setColorActivo(Color colorActivo) {

		this.colorActivo = colorActivo;

		repaint();

	}

	public void setColorReposo(Color colorReposo) {

		this.colorReposo = colorReposo;

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

		this.borde = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || fondo == null || colorTexto == null || borde == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, colorTexto, borde, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	@Override
	public void setHorizontalAlignment(int alignment) {

		super.setHorizontalAlignment(SwingConstants.LEFT);

	}

	@Override
	public void setText(String text) {

		this.texto = text;

		repaint();

	}

	public void setBorder(int border) {

		this.border = border;

		repaint();

	}

	public void setCheckColor(Color checkColor) {

		this.checkColor = checkColor;

		repaint();

	}

	public CheckBoxCustom(int position) {

		setFont(getFont().deriveFont(Font.PLAIN, 20f));

		colorActivo = new Color(69, 124, 235);

		colorReposo = new Color(69, 124, 235);

		border = 4;

		checkColor = Color.WHITE;

		setHorizontalAlignment(position);

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setOpaque(false);

		setFocusPainted(false);

		setContentAreaFilled(false);

		setBackground(new Color(69, 124, 235));

	}

	public CheckBoxCustom(String text, int position) {

		setFont(getFont().deriveFont(Font.PLAIN, 20f));

		border = 4;

		checkColor = Color.WHITE;

		setHorizontalAlignment(position);

		setText(text);

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setOpaque(false);

		setFocusPainted(false);

		setContentAreaFilled(false);

		setBackground(new Color(69, 124, 235));

		colorActivo = new Color(69, 124, 235);

		colorReposo = new Color(69, 124, 235);

	}

	public CheckBoxCustom() {

		setFont(getFont().deriveFont(Font.PLAIN, 20f));

		colorActivo = new Color(69, 124, 235);

		colorReposo = new Color(69, 124, 235);

		border = 4;

		checkColor = Color.WHITE;

		setHorizontalAlignment(SwingConstants.LEFT);

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setOpaque(false);

		setFocusPainted(false);

		setContentAreaFilled(false);

		setBackground(new Color(69, 124, 235));

		texto = "";

	}

	public CheckBoxCustom(String text) {

		setFont(getFont().deriveFont(Font.PLAIN, 20f));

		colorActivo = new Color(69, 124, 235);

		colorReposo = new Color(69, 124, 235);

		border = 4;

		checkColor = Color.WHITE;

		this.texto = text;

		setText(text);

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setOpaque(false);

		setFocusPainted(false);

		setContentAreaFilled(false);

		setBackground(new Color(69, 124, 235));

	}

	@Override

	public void paint(Graphics grphcs) {

		super.paint(grphcs);

		int x = 1;

		Graphics2D g2 = (Graphics2D) grphcs;

		int fontSize = getFont().getSize();

		if (fontSize < 16) {

			fontSize = 16;

		}

		else if (fontSize > 16) {

			fontSize -= 10;

			if (fontSize < 16) {

				fontSize = 16;

			}

		}

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int ly = ((getHeight() - fontSize) / 2) + 1;

		if (isEnabled()) {

			g2.setColor(colorActivo);

			if (isSelected()) {

				g2.fillRoundRect(x, ly, fontSize, fontSize, border, border);

				g2.setColor(checkColor);

				int[] px = { x + (Math.round((float) (fontSize * 0.375))), x,
						x + (Math.round((float) (fontSize * 0.1875))), x + (Math.round((float) (fontSize * 0.375))),
						x + (Math.round((float) (fontSize * 0.875))), x + fontSize };

				int[] py = { ly + fontSize, ly + (Math.round((float) (fontSize * 0.333))),
						ly + (Math.round((float) (fontSize * 0.166))), ly + (Math.round((float) (fontSize * 0.5833))),
						ly, ly + (Math.round((float) (fontSize * 0.25))) };

				g2.fillPolygon(px, py, px.length);

			}

			else {

				g2.setColor(colorReposo);

				g2.fillRoundRect(x, ly, fontSize, fontSize, border, border);

			}

		}

		else {

			g2.setColor(colorReposo);

			g2.fillRoundRect(x, ly, fontSize, fontSize, border, border);

		}

		g2.setColor(getForeground());

		g2.drawString(texto, fontSize + 7, getHeight() / 1.5f);

	}

}