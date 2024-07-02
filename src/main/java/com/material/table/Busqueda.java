package com.material.table;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import com.textField.text.NTextField;

@SuppressWarnings("serial")
public class Busqueda extends JPanel {

	private NTextField textField;

	public NTextField getTextField() {

		return textField;

	}

	public void setBackground(Color color) {

		try {

			textField.setBackground(color);

			super.setBackground(color);

		}

		catch (Exception e) {

		}

	}

	public Busqueda(String header) {

		setLayout(new GridLayout(1, 0, 0, 0));

		textField = new NTextField(header, "");

		add(textField);

	}

	public Busqueda(String header, String text) {

		setLayout(new GridLayout(1, 0, 0, 0));

		textField = new NTextField(header, text);

		add(textField);

	}

	public Busqueda() {

		setLayout(new GridLayout(1, 0, 0, 0));

		textField = new NTextField("Search", "");

		add(textField);

	}

}
