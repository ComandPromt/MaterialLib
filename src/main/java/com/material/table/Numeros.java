package com.material.table;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.buttons.indicators.IndicatorButton;
import com.buttons.indicators.Indicators;

import mthos.JMthos;

@SuppressWarnings("serial")
class Numeros extends JPanel {

	private LinkedList<IndicatorButton> numeros;

	private IndicatorButton boton;

	private int indice;

	private IndicatorButton botonPresionado;

	private Color fondo;

	private int inicio;

	private int step;

	private int numbers;

	private Cuerpo cuerpo;

	private Font fuente;

	private Color fg;

	private int numeroBotonPresionado;

	private int presionado;

	private NumPagination pagination;

	private ArrayList<Integer> contadorNumeros;

	public ArrayList<Integer> getContadorNumeros() {

		return contadorNumeros;

	}

	public int getNumeroBotonPresionado() {

		return numeroBotonPresionado;

	}

	public void setFondo(Color color) {

		fondo = color;

		pintar(inicio, step, numbers, cuerpo);

	}

	public IndicatorButton getBoton() {

		return boton;

	}

	public LinkedList<IndicatorButton> getNumeros() {

		return numeros;

	}

	public int getIndice() {

		return indice;

	}

	/**
	 * @wbp.parser.constructor
	 */

	public Numeros(int init, int numbers, int step, Cuerpo cuerpo, NumPagination pagination, Font fuente, Color fg) {

		contadorNumeros = new ArrayList<>();

		this.pagination = pagination;

		presionado = -1;

		numeroBotonPresionado = -1;

		inicio = init;

		this.fuente = fuente;

		this.fg = fg;

		this.numbers = numbers;

		this.step = step;

		this.cuerpo = cuerpo;

		setLayout(new GridLayout(1, 0, 0, 0));

		numeros = new LinkedList<>();

		fondo = Color.WHITE;

		pintar(init, step, numbers, cuerpo);

	}

	public void pintar(int init, int step, int numbers, Cuerpo cuerpo) {

		removeAll();

		if (numbers == init) {

			init = numbers - (numbers - (step--));

			step++;

		}

		int contador = 0;

		contadorNumeros.clear();

		for (int i = init; contador < step; contador++) {

			if (i <= numbers) {

				contadorNumeros.add(i);

				boton = new IndicatorButton(Integer.toString(i));

				boton.setBorderType(pagination.getIndicadorType());

				try {

					if (pagination.getIndicadorType().equals(Indicators.BORDE)) {

						boton.setGrosor(1);

					}

					else {

						boton.setGrosor(0);

					}

				}

				catch (Exception e) {

				}

				boton.setSelectedColor(pagination.getIndicador());

				boton.setRadius(0);

				boton.setFont(fuente);

				boton.setForeground(fg);

				boton.setHorizontalAlignment(SwingConstants.CENTER);

				boton.addMouseListener(new MouseAdapter() {

					@Override

					public void mousePressed(MouseEvent e) {

						botonPresionado = (IndicatorButton) e.getSource();

						indice = Integer.parseInt(botonPresionado.getText());

						try {

							cuerpo.verDatos(
									JMthos.calcularSucesionAritmeticaAInt(
											"1#0,2#" + cuerpo.getItems() * cuerpo.getSplit(), indice),
									cuerpo.getDatos());

						}

						catch (Exception e1) {

						}

						presionado = indice;

						pagination.setIndice(presionado);

						presionado--;

						int buscarIndice = JMthos.encontrarIndice(contadorNumeros, indice);

						for (int i = 0; i < numeros.size(); i++) {

							if (i == buscarIndice) {

								numeros.get(i).setPaintSelected(true);

							}

							else {

								numeros.get(i).setPaintSelected(false);

							}

						}

						pagination.setCick(true);

					}

				});

				numeros.add(boton);

				add(numeros.getLast());

				i++;

			}

			else {

				contador = step;

			}

		}

		setBackground(fondo);

		revalidate();

		repaint();

	}

}