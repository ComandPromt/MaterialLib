package com.textField.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.JToolTip;

import com.contextmenu.DefaultContextMenu;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class TextFieldSuggestion extends JTextField {

	private TextFieldSuggestionUI textUI;

	private String placeholderText = "Placeholder";

	private boolean showPlaceholder = true;

	private HorizontalAlignment hAlign = HorizontalAlignment.LEFT;

	private VerticalAlignment vAlign = VerticalAlignment.CENTER;

	private Color placeholderColor = Color.GRAY;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private Font placeholderFont;

	public enum HorizontalAlignment {

		LEFT, CENTER, RIGHT

	}

	public enum VerticalAlignment {

		TOP, CENTER, BOTTOM

	}

	public void setPlaceholderFont(Font placeholderFont) {

		this.placeholderFont = placeholderFont;

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

		this.placeholderText = text;

		this.fondo = background;

		this.colorTexto = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (placeholderText == null || fondo == null || colorTexto == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(placeholderText, fondo, colorTexto, border, fuente);

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

		setFont(new Font("Dialog", Font.PLAIN, 20));

		placeholderFont = getFont();

		setGrosor(2);

		addKeyListener(new KeyAdapter() {

			@Override

			public void keyReleased(KeyEvent e) {

				updatePlaceholderVisibility();

			}

		});

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

	public void setRound(int angle) {

		textUI.setRound(angle);

	}

	public int getRound() {

		return textUI.getRound();

	}

	public void setPlaceholder(String text) {

		this.placeholderText = text;

		repaint();

	}

	public void setPlaceholderHorizontalAlignment(HorizontalAlignment alignment) {

		this.hAlign = alignment;

		repaint();

	}

	public void setPlaceholderVerticalAlignment(VerticalAlignment alignment) {

		this.vAlign = alignment;

		repaint();

	}

	public void setPlaceholderColor(Color color) {

		this.placeholderColor = color;

		repaint();

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		Insets insets = getInsets();

		if (getText().isEmpty() && showPlaceholder) {

			g2.setColor(placeholderColor);

			g2.setFont(placeholderFont);

			String text = placeholderText;

			int textWidth = g2.getFontMetrics().stringWidth(text);

			int textHeight = g2.getFontMetrics().getAscent();

			int x = 0;

			int y = 0;

			switch (hAlign) {

			case LEFT:

				x = insets.left + (int) textUI.getGrosor();

				break;

			case CENTER:

				x = (getWidth() - textWidth) / 2;

				break;

			case RIGHT:

				x = (getWidth() - insets.right - textWidth) - (int) textUI.getGrosor();

				break;

			}

			switch (vAlign) {

			case TOP:

				y = insets.top + textHeight;

				break;

			case CENTER:

				y = (getHeight() - textHeight) / 2 + textHeight / 2;

				break;

			case BOTTOM:

				y = getHeight() - insets.bottom;

				break;

			}

			y += (placeholderFont.getSize() / 3) + 1;

			g2.drawString(text, x, y);

		}

	}

	@Override
	public void setText(String text) {

		super.setText(text);

		updatePlaceholderVisibility();

	}

	@Override
	public void setCaretColor(Color color) {

		super.setCaretColor(color);

	}

	@Override
	public void setFont(Font font) {

		super.setFont(font);

		repaint();

	}

	private void updatePlaceholderVisibility() {

		showPlaceholder = getText().isEmpty();

		repaint();

	}

}