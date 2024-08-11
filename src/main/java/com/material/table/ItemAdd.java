package com.material.table;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.buttons.round.NButton;
import com.comboBox.comboSuggestion.ComboBoxSuggestion;
import com.label.others.LabelHeader;
import com.textField.text.NTextField;

import mthos.JMthos;

@SuppressWarnings("serial")
class ItemAdd extends JPanel {

	private NTextField textField;

	private ComboBoxSuggestion<String> comboBox;

	private NButton add;

	private NButton delete;

	private ArrayList<String> lista;

	private LabelHeader columna;

	public LabelHeader getLabelHeader() {

		return columna;

	}

	public ArrayList<String> getList() {

		return lista;

	}

	public ItemAdd(String header) {

		textField = new NTextField();

		add = new NButton("+");

		lista = new ArrayList<>();

		columna = new LabelHeader(header);

		columna.setBackground(Color.WHITE);

		comboBox = new ComboBoxSuggestion<>();

		delete = new NButton("-");

		setBackground(Color.WHITE);

		add.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				String texto = textField.getText().trim();

				lista.add(texto);

				if (!texto.isEmpty()) {

					comboBox.addItem(texto);

				}

			}

		});

		delete.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				try {

					lista.remove(comboBox.getSelectedIndex());

					comboBox.removeAllItems();

					JMthos.agregarAComboBox(comboBox, lista);

				}

				catch (Exception e1) {

				}

			}

		});

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				int ancho = Math.round(getWidth() * 0.8f);

				int alto = Math.round(getHeight() * 0.4f);

				columna.setBounds(0, 0, getWidth(), Math.round(getHeight() * 0.2f));

				add.setBounds(ancho, Math.round(getWidth() * 0.2f), Math.round(getWidth() * 0.2f),
						Math.round(getHeight() * 0.2f));

				textField.setBounds(0, Math.round(getHeight() * 0.2f), ancho, alto);

				comboBox.setBounds(0, Math.round(getHeight() * 0.6f), ancho, alto);

				delete.setBounds(ancho, Math.round(getHeight() * 0.7f), Math.round(getWidth() * 0.2f),
						Math.round(getHeight() * 0.2f));

			}

		});

		setLayout(null);

		add(textField);

		textField.setColumns(10);

		add(add);

		add(comboBox);

		add(delete);

		add(columna);

	}
}
