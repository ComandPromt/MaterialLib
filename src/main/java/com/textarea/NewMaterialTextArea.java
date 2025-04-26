package com.textarea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolTip;
import javax.swing.border.LineBorder;

import com.scrollbar.ScrollBarUI;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class NewMaterialTextArea extends JPanel {

	private JScrollPane scrollPane;

	private NewTextArea textArea;

	private ScrollBarUI scroll;

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

	public JScrollPane getScrollPane() {

		return scrollPane;

	}

	public ScrollBarUI getScroll() {

		return scroll;

	}

	public void setScrollPane(JScrollPane scrollPane) {

		this.scrollPane = scrollPane;

	}

	public void setScroll(ScrollBarUI scroll) {

		this.scroll = scroll;

	}

	public NewMaterialTextArea() {

		setBorder(new LineBorder(new Color(0, 0, 0)));

		setLayout(new GridLayout());

		textArea = new NewTextArea();

		textArea.setWrapStyleWord(true);

		scrollPane = new JScrollPane(textArea);

		scrollPane.setBackground(Color.WHITE);

		scrollPane.setBorder(null);

		scroll = new ScrollBarUI();

		scrollPane.getVerticalScrollBar().setUI(scroll);

		scrollPane.getHorizontalScrollBar().setUI(new ScrollBarUI());

		add(scrollPane, BorderLayout.CENTER);

	}

}
