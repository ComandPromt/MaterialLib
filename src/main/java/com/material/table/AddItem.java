package com.material.table;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.buttons.round.NButton;
import com.layout.MaterialPanelLayout;

import mthos.JMthos;

@SuppressWarnings("serial")
class AddItem extends JPanel {

	private ArrayList<ItemAdd> datos;

	public ArrayList<ItemAdd> getDatos() {

		return datos;

	}

	public AddItem(int numeroPaginas, NumPagination pagination, Cuerpo cuerpo, List<String> cabeceras) {

		setLayout(new GridLayout());

		ArrayList<JComponent> lista = new ArrayList<>();

		datos = new ArrayList<>();

		JPanel datosItem = new JPanel();

		JPanel agregar = new JPanel();

		datosItem.setLayout(new GridLayout(0, cabeceras.size(), 0, 0));

		ItemAdd item;

		for (int i = 0; i < cabeceras.size(); i++) {

			item = new ItemAdd(cabeceras.get(i));

			this.datos.add(item);

			datosItem.add(item);

		}

		lista.add(datosItem);

		agregar.setLayout(new GridLayout());

		NButton boton = new NButton("Add");

		this.datos.get(0).getList();

		boton.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				ArrayList<String> lista = cuerpo.getDatos();

				for (int i = 0; i < datos.size(); i++) {

					lista = (ArrayList<String>) JMthos.agregarLista(lista, datos.get(i).getList());

				}

				cuerpo.setDatos(lista);

				int numeroPaginas = JMthos.dividirYRedondearAEntero(lista.size(),
						(cuerpo.getItems() * cuerpo.getSplit()));

				pagination.setPaso(numeroPaginas);

				int perpage = cuerpo.getItems();

				pagination.setStep(JMthos.divideAndCeil(lista.size(), perpage));

				int index = pagination.getIndice();

				if (index == 0) {

					index = 1;

				}

				pagination.setIndice(index);

				pagination.last(numeroPaginas, cuerpo);

			}

		});

		agregar.add(boton);

		lista.add(agregar);

		ArrayList<Integer> porcentajes = new ArrayList<>();

		porcentajes.add(85);

		porcentajes.add(15);

		MaterialPanelLayout panel = new MaterialPanelLayout(lista, porcentajes, true);

		add(panel);

	}

}
