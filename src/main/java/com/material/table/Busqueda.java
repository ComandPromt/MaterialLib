package com.material.table;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.search.SearchTextField;

@SuppressWarnings("serial")
public class Busqueda extends JPanel {

	private SearchTextField textField;

	public void setLista(ArrayList<String> lista) {

		try {

			textField.setSuggestions(lista);

		}

		catch (Exception e) {

		}

	}

	public SearchTextField getTextField() {

		return textField;

	}

	public Busqueda(String header, ArrayList<String> lista) {

		setLayout(new GridLayout());

		textField = new SearchTextField(lista);

		add(textField);

	}

	public Busqueda(String header, String text, ArrayList<String> lista) {

		setLayout(new GridLayout());

		textField = new SearchTextField(lista);

		textField.getSearchField().setText(text);

		add(textField);

	}

	/**
	 * @wbp.parser.constructor
	 */
	public Busqueda(ArrayList<String> lista) {

		setLayout(new GridLayout());

		textField = new SearchTextField(lista);

		add(textField);

	}

}
