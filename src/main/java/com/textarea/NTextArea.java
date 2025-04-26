package com.textarea;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JToolTip;

import com.contextmenu.DefaultContextMenu;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class NTextArea extends TextAreaScroll {

	private NewTextArea textArea;

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

	public void setWrapStyleWord(boolean active) {

		try {

			textArea.setWrapStyleWord(active);

		}

		catch (Exception e) {

		}

	}

	public void setEditable(boolean editable) {

		try {

			textArea.setEditable(editable);

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

	public void setText(String text) {

		textArea.setText(text);

	}

	public void setFont(Font font) {

		try {

			textArea.setFont(font);

		}

		catch (Exception e) {

		}

	}

	public void setHeaderBackground(Color bg) {

		try {

			super.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public void setBackgroundText(Color bg) {

		try {

			textArea.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public void setTextColor(Color fg) {

		try {

			textArea.setForeground(fg);

		}

		catch (Exception e) {

		}

	}

	public void setSelectionColor(Color selectionColor) {

		textArea.setSelectionColor(selectionColor);

	}

	public String getText() {

		return textArea.getText();

	}

	public NTextArea() {

		this("");

	}

	public NTextArea(String text) {

		setShow(true);

		textArea = new NewTextArea(null, null, null, null);

		setHeader(text);

		textArea.setColumns(20);

		textArea.setRows(5);

		textArea.setFont(new Font("Dialog", Font.PLAIN, 20));

		setSelectionColor(Color.WHITE);

		setViewportView(textArea);

		DefaultContextMenu.addDefaultContextMenu(textArea);

		setSelectionColor(new Color(223, 223, 223));

	}

	public NewTextArea getTextArea() {

		return textArea;

	}

}
