package com.fontPicker;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.comboBox.comboFont.ComboFont;
import com.comboBox.comboSuggestion.ComboBoxSuggestion;
import com.layout.MaterialPanelLayout;

@SuppressWarnings("serial")

public class FontPicker extends JPanel {

	private ComboFont comboBox;

	private ComboBoxSuggestion<String> estilo;

	private JLabel panel_2;

	private int style;

	private int size;

	private JPanel panelTexto;

	private MaterialPanelLayout panel;

	private MaterialPanelLayout panel_1;

	public void setStyleForeground(Color color) {

		try {

			estilo.setForeground(color);

		}

		catch (Exception e) {

		}

	}

	public void setComboForeground(Color color) {

		try {

			comboBox.setForeground(color);

		}

		catch (Exception e) {

		}

	}

	public int getTextSize() {

		return size;

	}

	public void setTextSize(int size) {

		this.size = size;

		if (panel_2.getFont().getSize() != size) {

			panel_2.setFont(getFont().deriveFont(size));

		}

	}

	@Override
	public void setBackground(Color bg) {

		super.setBackground(bg);

		try {

			super.setBackground(bg);

			panel.setBackground(bg);

			panel_1.setBackground(bg);

			panel_2.setBackground(bg);

			comboBox.setBackground(bg);

			estilo.setBackground(bg);

			panelTexto.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public void setBorderColor(Color color) {

		try {

			comboBox.setBorderColor(color);

			estilo.setBorderColor(color);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setForeground(Color fg) {

		try {

			panel_2.setForeground(fg);

			comboBox.setForeground(fg);

		}

		catch (Exception e) {

		}

	}

	public FontPicker() {

		this(30f);

	}

	@Override
	public Font getFont() {

		try {

			return panel_2.getFont();

		}

		catch (Exception e) {

			return null;

		}
	}

	public void setPhrase(String text) {

		try {

			panel_2.setText(text);

		}

		catch (Exception e) {

		}

	}

	public FontPicker(float textSize) {

		setBorder(new LineBorder(new Color(0, 0, 0)));

		if (textSize < 1f) {

			textSize = 30f;

		}

		size = (int) textSize;

		setLayout(new GridLayout(1, 0));

		ArrayList<JComponent> lista = new ArrayList<>();

		panel_2 = new JLabel("The quick brown fox jumps over the lazy dog");

		comboBox = new ComboFont();

		estilo = new ComboBoxSuggestion<>();

		estilo.addItem("PLAIN");

		estilo.addItem("BOLD");

		estilo.addItem("CURSIVE");

		estilo.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				try {

					switch (estilo.getSelectedIndex()) {

					case 0:

						style = Font.PLAIN;

						break;

					case 1:

						style = Font.BOLD;

						break;

					case 2:

						style = Font.ITALIC;

						break;

					}

					panel_2.setFont(new Font(comboBox.getSelectedItem().toString(), style, size));

				}

				catch (Exception e1) {

				}

			}

		});

		style = Font.PLAIN;

		panel_2.setFont(new Font(comboBox.getItemAt(0).toString(), Font.PLAIN, size));

		comboBox.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				try {

					panel_2.setFont(new Font(comboBox.getSelectedItem().toString(), style, size));

				}

				catch (Exception e1) {

				}

			}

		});

		lista.add(comboBox);

		lista.add(estilo);

		ArrayList<Integer> porcentajes = new ArrayList<>();

		porcentajes.add(65);

		porcentajes.add(25);

		panel_1 = new MaterialPanelLayout(lista, porcentajes, false);

		ArrayList<JComponent> lista2 = new ArrayList<>();

		lista2.add(panel_1);

		panelTexto = new JPanel();

		panelTexto.setLayout(new GridLayout());

		panelTexto.add(panel_2);

		lista2.add(panelTexto);

		ArrayList<Integer> porcentajes2 = new ArrayList<>();

		porcentajes2.add(60);

		porcentajes2.add(40);

		panel = new MaterialPanelLayout(lista2, porcentajes2, true);

		add(panel);

	}

}
