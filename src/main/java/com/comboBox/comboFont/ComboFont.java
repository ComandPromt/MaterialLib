package com.comboBox.comboFont;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JComboBox;
import javax.swing.JToolTip;

import com.comboBox.comboSuggestion.ComboSuggestionUI;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings({ "serial", "rawtypes" })

public class ComboFont extends JComboBox {

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private ComboSuggestionUI comboBoxUi;

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

				font = getFont().deriveFont(14f);

			}

			catch (Exception e) {

				font = new Font("Dialog", Font.PLAIN, 14);

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

	public void setArrowInvisible(Color color) {

		comboBoxUi.setArrowInvisible(color);

	}

	public void setArrowVisible(Color color) {

		comboBoxUi.setArrowVisible(color);

	}

	public void setSelectionItemColor(Color color) {

		comboBoxUi.setSelectionItemColor(color);

	}

	public void setSelectionForegroundColor(Color color) {

		comboBoxUi.setSelectionForegroundColor(color);

	}

	public void setMenuColor(Color color) {

		comboBoxUi.setMenuColor(color);

	}

	public void setForegroundMenuColor(Color color) {

		comboBoxUi.setForegroundMenuColor(color);
	}

	public void setBorderColor(Color color) {

		comboBoxUi.setBorderColor(color);
	}

	@Override
	public void setForeground(Color fg) {

		try {

			comboBoxUi.setForegroundMenuColor(fg);

		}

		catch (Exception e) {

		}

	}

	@SuppressWarnings("unchecked")

	public ComboFont() {

		this(Color.WHITE);

	}

	public ComboFont(Color lineScrollColor) {

		comboBoxUi = new ComboSuggestionUI(lineScrollColor);

		setUI(comboBoxUi);

		setFont(new Font("Dialog", Font.PLAIN, 20));

		this.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

				int valorIndice = getSelectedIndex();

				if (e.getWheelRotation() < 0) {

					valorIndice--;

				}

				else {

					valorIndice++;

				}

				if (valorIndice < 0) {

					valorIndice = 0;

				}

				if (valorIndice >= getItemCount()) {

					valorIndice = getItemCount();

					valorIndice--;

				}

				setSelectedIndex(valorIndice);

			}

		});

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		String[] fontFamilies = ge.getAvailableFontFamilyNames();

		for (String ff : fontFamilies) {

			addItem(ff);

		}

	}

}
