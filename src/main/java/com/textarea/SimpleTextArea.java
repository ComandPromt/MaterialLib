package com.textarea;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JToolTip;

import com.contextmenu.DefaultContextMenu;
import com.scrollbar.MaterialPanelDeslizante;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class SimpleTextArea extends JPanel {

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private TextArea textArea;

	private Color colorFondo;

	private boolean borde;

	private MaterialPanelDeslizante scrollPane;

	public void setText(String text) {

		try {

			textArea.setText(text);

		}

		catch (Exception e) {

		}

	}

	public void setSelectionColor(Color color) {

		try {

			textArea.setSelectionColor(color);

		}

		catch (Exception e) {

		}

	}

	public void setColors(Color foreground, Color background) {

		try {

			scrollPane.setColors(foreground, background);

		}

		catch (Exception e) {

		}

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

	public void setVerticalScrollBarPolicy(int policy) {

		try {

			scrollPane.setVerticalScrollBarPolicy(policy);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setForeground(Color fg) {

		try {

			textArea.setForeground(fg);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setBackground(Color bg) {

		try {

			colorFondo = bg;

			textArea.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setFont(Font font) {

		try {

			textArea.setFont(font);

		}

		catch (Exception e) {

		}

	}

	public boolean isBorde() {

		return borde;

	}

	public void setBorde(boolean borde) {

		this.borde = borde;

		repaint();

	}

	public void setForegroundScroll(Color color) {

		try {

			scrollPane.setForeground(color);

		}

		catch (Exception e) {

		}

	}

	public void setBackgroundScroll(Color color) {

		try {

			scrollPane.setBackground(color);

		}

		catch (Exception e) {

		}

	}

	public SimpleTextArea() {

		colorFondo = Color.WHITE;

		setLayout(new GridLayout(0, 1, 0, 0));

		textArea = new TextArea(Color.RED, Color.BLUE);

		textArea.setFont(new Font("Dialog", Font.PLAIN, 20));

		DefaultContextMenu.addDefaultContextMenu(textArea);

		scrollPane = new MaterialPanelDeslizante(textArea, null, null);

		add(scrollPane);

	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);

		if (!borde) {

			g.setColor(colorFondo);

			g.fillRect(0, 0, getWidth(), getHeight());

		}

	}

}
