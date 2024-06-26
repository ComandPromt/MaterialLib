package com.textField.text;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JToolTip;

import com.contextmenu.DefaultContextMenu;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class TextFieldSuggestion extends JTextField {

	private TextFieldSuggestionUI textUI;

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

	public void setBorderColor(Color color) {

		textUI.setBorderColor(color);

	}

	public void setGrosor(int thickness) {

		textUI.setGrosor(thickness);

	}

	public TextFieldSuggestion() {

		this("");

	}

	public void setSelectedTextColor() {

		try {

			textUI.getTextfield().setSelectedTextColor(colorTexto);

		}

		catch (Exception e) {

		}

	}

	public TextFieldSuggestion(String text) {

		setOpaque(true);

		textUI = new TextFieldSuggestionUI(this);

		setUI(textUI);

		setText(text);

		DefaultContextMenu.addDefaultContextMenu(this);

		setFont(new Font("Diaglog", Font.PLAIN, 20));

		setGrosor(2);

	}

	public void addItemSuggestion(String text) {

		textUI.getItems().add(text);

	}

	public void removeItemSuggestion(String text) {

		textUI.getItems().remove(text);

	}

	public void clearItemSuggestion() {

		textUI.getItems().clear();

	}

	public void setRound(int round) {

		textUI.setRound(round);

	}

	public int getRound() {

		return textUI.getRound();

	}

}
