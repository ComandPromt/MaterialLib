package com.material.table;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.layout.MaterialPanelLayout;

@SuppressWarnings("serial")
class BotonesTabla extends JPanel {

	private Busqueda busqueda;

	private Crud crud;

	private MaterialPanelLayout panel_1;

	public BotonesTabla() {

		this(Color.WHITE);

	}

	@Override
	public void setBackground(Color bg) {

		super.setBackground(bg);

		try {

			busqueda.setBackground(bg);

			crud.setBackground(bg);

			panel_1.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public BotonesTabla(Color background) {

		setLayout(new GridLayout());

		busqueda = new Busqueda();

		crud = new Crud();

		crud.setBackground(background);

		busqueda.setBackground(background);

		ArrayList<JComponent> lista = new ArrayList<>();

		lista.add(crud);

		lista.add(busqueda);

		ArrayList<Integer> porcentajes = new ArrayList<>();

		porcentajes.add(50);

		porcentajes.add(50);

		panel_1 = new MaterialPanelLayout(lista, porcentajes, false);

		panel_1.setBackground(background);

		add(panel_1);

	}

}
