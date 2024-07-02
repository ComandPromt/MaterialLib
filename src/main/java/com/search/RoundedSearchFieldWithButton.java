package com.search;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import com.layout.MaterialPanelLayout;

@SuppressWarnings("serial")
public class RoundedSearchFieldWithButton extends JPanel {

	public RoundedSearchFieldWithButton() {

		this(80);

	}

	public RoundedSearchFieldWithButton(int percent) {

		if (percent < 1) {

			percent = 80;

		}

		setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(0, 1, 0, 0));

		BootSearchInput lblNewLabel = new BootSearchInput();

		JButton btnNewButton = new JButton("New button");

		ArrayList<JComponent> lista = new ArrayList<>();

		JPanel panel1 = new JPanel();

		panel1.add(lblNewLabel);

		panel1.setLayout(new GridLayout(0, 1, 0, 0));

		lista.add(panel1);

		JPanel panel2 = new JPanel();

		panel2.add(btnNewButton);

		panel2.setLayout(new GridLayout(0, 1, 0, 0));

		lista.add(panel2);

		ArrayList<Integer> porcentajes = new ArrayList<>();

		porcentajes.add(80);

		porcentajes.add(20);

		add(new MaterialPanelLayout(lista, porcentajes, false));

	}

}
