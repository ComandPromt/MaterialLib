package com.scrollbar;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class SimplePanelDeslizante extends JPanel {

	public MaterialPanelDeslizante materialPanel;

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

	public SimplePanelDeslizante(JPanel panel, Color select, Color background, final String text,
			final String separador) {

		if (select == null) {

			select = new Color(48, 144, 216);

		}

		if (background == null) {

			background = Color.WHITE;

		}

		setLayout(new GridLayout(0, 1, 0, 0));

		add(panel);

		materialPanel = new MaterialPanelDeslizante(panel, select, background, Color.WHITE, 40);

		JList<String> list = new JList<String>();

		try {

			list.setModel(new javax.swing.AbstractListModel<String>() {

				String[] strings = text.split(separador);

				public int getSize() {

					return strings.length;

				}

				public String getElementAt(int i) {

					return strings[i];

				}

			});

		}

		catch (Exception e) {

		}

		add(materialPanel);

		materialPanel.setViewportView(list);

	}

}
