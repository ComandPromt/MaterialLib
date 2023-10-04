package com.panels.round;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;

import com.material.utils.ElevationEffect;
import com.material.utils.MaterialShadow;
import com.material.utils.Utils;
import com.materiallib.utils.MaterialColor;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class MaterialPanel extends JPanel {

	private final ElevationEffect elevation;

	private String text;

	private Color fondo;

	private Color colorTexto;

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

	public MaterialPanel() {

		elevation = ElevationEffect.applyTo(this, 1);

		setBorder(new EmptyBorder(MaterialShadow.OFFSET_TOP, MaterialShadow.OFFSET_LEFT, MaterialShadow.OFFSET_BOTTOM,
				MaterialShadow.OFFSET_RIGHT));

	}

	public int getElevation() {

		return elevation.getLevel();

	}

	public void setElevation(int elevation) {

		this.elevation.setLevel(elevation);

	}

	@Override
	public void setBackground(Color bg) {

		super.setBackground(bg);

		setForeground(Utils.isDark(bg) ? MaterialColor.WHITE : MaterialColor.DARK_BLACK);

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		elevation.paint(g);

		g.setClip(MaterialShadow.OFFSET_LEFT, MaterialShadow.OFFSET_TOP,
				getWidth() - MaterialShadow.OFFSET_LEFT - MaterialShadow.OFFSET_RIGHT,
				getHeight() - MaterialShadow.OFFSET_TOP - MaterialShadow.OFFSET_BOTTOM);

		super.paintComponent(g2);

		g.setClip(null);

	}

}
