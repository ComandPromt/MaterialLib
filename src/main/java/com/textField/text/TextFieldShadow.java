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

import com.contextmenu.DefaultContextMenu;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class TextFieldShadow extends JTextField {

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

	public void setRound(boolean round) {
		this.round = round;
		repaint();
	}

	public void setToolTipText(String text) {
		setToolTip(text, (Color) null, (Color) null, (Color) null, (Font) null);
	}

	public void setToolTip(String text, Color background, Color foreground, Color border, Font font) {
		calcularTooltip(text, background, foreground, border, font);
	}

	private void calcularTooltip(String text, Color background, Color foreground, Color border, Font font) {
		if (background == null)
			background = new Color(32, 39, 55);
		if (foreground == null)
			foreground = Color.WHITE;
		if (border == null)
			border = new Color(173, 173, 173);
		if (font == null)
			try {
				font = getFont().deriveFont(20.0F);
			} catch (Exception e) {
				font = new Font("Dialog", 0, 20);
			}
		this.text = text;
		this.fondo = background;
		this.colorTexto = foreground;
		this.border = border;
		this.fuente = font;
		super.setToolTipText(text);
	}

	public JToolTip createToolTip() {
		if (this.text == null || this.fondo == null || this.colorTexto == null || this.border == null)
			return super.createToolTip();
		ToolTipLlamada tip = new ToolTipLlamada(this.text, this.fondo, this.colorTexto, this.border, this.fuente);
		tip.setComponent(this);
		return (JToolTip) tip;
	}

	public TextFieldShadow() {
		this.round = true;
		this.direccionDeSombra = 60;
		this.distanciaDeSombra = 1;
		this.vertical = true;
		this.borde = 2.0F;
		this.opacidad = Float.valueOf(1.0F);
		setOpaque(false);
		setFont(new Font("Dialog", 0, 30));
		DefaultContextMenu.addDefaultContextMenu(this);
	}

	protected void paintBorder(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Stroke st = g2.getStroke();
		g2.setStroke(new BasicStroke(this.borde));
		if (this.round) {
			g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight() / 3, getHeight() / 3);
		} else {
			g2.drawRect(0, 0, getWidth(), getHeight());
		}
		g2.setStroke(st);
	}

	protected void paintComponent(Graphics g) {
		Shape rect;
		float x1 = 0.0F;
		float x2 = 0.0F;
		float y1 = 0.0F;
		float y2 = getHeight();
		if (!this.vertical) {
			x1 = 0.0F;
			y1 = 0.0F;
			x2 = getWidth();
			y2 = 0.0F;
		}
		Graphics2D g2 = (Graphics2D) g;
		Paint oldPaint = g2.getPaint();
		if (this.round) {
			rect = new RoundRectangle2D.Double(0.0D, 0.0D, (getWidth() - 1), (getHeight() - 1), (getHeight() / 3),
					(getHeight() / 3));
		} else {
			rect = new Rectangle2D.Double(0.0D, 0.0D, getWidth(), getHeight());
		}
		g2.clip(rect);
		g2.setPaint(new GradientPaint(x1, y1, getBackground(), x2, y2, getBackground().darker()));
		g2.fill(rect);
		g2.setPaint(oldPaint);
		super.paintComponent(g);
	}

	public Float getOpacidad() {
		return this.opacidad;
	}

	public void setOpacidad(Float opacidad) {
		if (opacidad.floatValue() < 0.0F) {
			opacidad = Float.valueOf(0.0F);
		} else if (opacidad.floatValue() > 1.0F) {
			opacidad = Float.valueOf(1.0F);
		}
		this.opacidad = opacidad;
	}

	public int getDireccionDeSombra() {
		return this.direccionDeSombra;
	}

	public void setDireccionDeSombra(int direccionDeSombra) {
		this.direccionDeSombra = direccionDeSombra;
		repaint();
	}

	public int getDistanciaDeSombra() {
		return this.distanciaDeSombra;
	}

	public void setDistanciaDeSombra(int distanciaDeSombra) {
		this.distanciaDeSombra = distanciaDeSombra;
		repaint();
	}

	public boolean isVertical() {
		return this.vertical;
	}

	public void setVertical(boolean vertical) {
		this.vertical = vertical;
		repaint();
	}

	public float getBorde() {
		return this.borde;
	}

	public void setBorde(float borde) {
		this.borde = borde;
	}
}
