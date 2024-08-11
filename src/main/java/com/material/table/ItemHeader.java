package com.material.table;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.jicons.MovimientoTriangulos;

import mthos.JMthos;

@SuppressWarnings("serial")
class ItemHeader extends JPanel {

	private JLabel texto;

	private JLabel move;

	private MovimientoTriangulos mov;

	private int indice;

	public static Cuerpo cuerpo;

	public static int columnas;

	private JMthos test;

	public void setIndice(int indice) {

		this.indice = indice;

	}

	public void setSelectColor(Color color) {

		try {

			mov.setSelectColor(color);

		}

		catch (Exception e) {

		}

	}

	public void setDisableColor(Color color) {

		try {

			mov.setDisableColor(color);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setFont(Font font) {

		super.setFont(font);

		try {

			move.setFont(font);

			texto.setFont(font);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setForeground(Color fg) {

		super.setForeground(fg);

		try {

			move.setForeground(fg);

			texto.setForeground(fg);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setBackground(Color bg) {

		super.setBackground(bg);

		try {

			move.setBackground(bg);

			texto.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public void clickear(boolean click) {

		cuerpo.borrarDatos();

		int index = indice;

		if (index > 0) {

			index++;

		}

		ArrayList<String> lista = (ArrayList<String>) JMthos
				.sortedMapToList(test.convertirAMapYOrdenar(cuerpo.getDatos(), cuerpo.getSplit(), index, click));

		lista = (ArrayList<String>) JMthos.limpiarLista(lista, null);

		cuerpo.setDatos(lista);

		cuerpo.verDatos(0, lista);

	}

	private void clickear() {

		cuerpo.borrarDatos();

		int index = indice;

		if (index > 0) {

			index++;

		}

		ArrayList<String> lista = (ArrayList<String>) JMthos.sortedMapToList(
				test.convertirAMapYOrdenar(cuerpo.getDatos(), cuerpo.getSplit(), index, !mov.isParteSuperiorClicada()));

		lista = (ArrayList<String>) JMthos.limpiarLista(lista, null);

		cuerpo.setDatos(lista);

		cuerpo.verDatos(0, lista);

	}

	public ItemHeader(String text) {

		test = new JMthos();

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				texto.setBounds(0, 0, Math.round(getWidth() * 0.8f), getHeight());

				move.setBounds(Math.round(getWidth() * 0.8f), 0, Math.round(getWidth() * 0.2f) - 1, getHeight());

			}

		});

		setLayout(null);

		texto = new JLabel(text);

		texto.setHorizontalAlignment(SwingConstants.CENTER);

		texto.setBounds(0, 0, 254, 300);

		add(texto);

		move = new JLabel(" ");

		move.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				clickear();

			}

		});

		mov = new MovimientoTriangulos();

		move.setIcon(mov);

		move.setHorizontalAlignment(SwingConstants.CENTER);

		move.setBounds(257, 0, 193, 300);

		add(move);

		setBackground(Color.WHITE);

	}

}
