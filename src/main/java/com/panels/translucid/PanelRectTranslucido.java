package com.panels.translucid;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JToolTip;

import com.panels.round.RoundPanel;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class PanelRectTranslucido extends RoundPanel {

	protected float tran;

	private float valor;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	@Override
	public void setToolTipText(String text) {

		setToolTip(text, null, null, null, null);

	}

	@Override
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

	public PanelRectTranslucido() {

		tran = 0.5f;

		valor = tran;

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		Composite oldComposite = g2.getComposite();

		AlphaComposite newComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, valor);

		g2.setComposite(newComposite);

		super.paintComponent(g);

		g2.setComposite(oldComposite);

	}

	public float getTran() {

		return tran;

	}

	public void setTran(float tran) {

		this.tran = tran;

		valor = tran;

	}

}
