package com.material.table;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import mthos.JMthos;

@SuppressWarnings("serial")
public class Numeros extends JPanel {

	private LinkedList<JLabel> numeros;

	private JLabel boton;

	private int indice;

	private JLabel botonPresionado;

	private Color fondo;

	private int inicio;

	private int step;

	private int numbers;

	private Cuerpo cuerpo;

	private Font fuente;

	private Color fg;

	public void setFondo(Color color) {

		fondo = color;

		pintar(inicio, step, numbers, cuerpo);

	}

	public JLabel getBoton() {

		return boton;

	}

	public LinkedList<JLabel> getNumeros() {

		return numeros;

	}

	public int getIndice() {

		return indice;

	}

	/**
	 * @wbp.parser.constructor
	 */

	public Numeros(int init, int numbers, int step, Cuerpo cuerpo, Font fuente, Color fg) {

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

		for (int i = init; contador < step; contador++) {

			if (i <= numbers) {

				boton = new JLabel("" + i);

				boton.setFont(fuente);

				boton.setForeground(fg);

				boton.setHorizontalAlignment(SwingConstants.CENTER);

				boton.addMouseListener(new MouseAdapter() {

					@Override

					public void mousePressed(MouseEvent e) {

						botonPresionado = (JLabel) e.getSource();

						indice = Integer.parseInt(botonPresionado.getText());

						try {

							cuerpo.verDatos(
									JMthos.calcularSucesionAritmeticaAInt(
											"1#0,2#" + cuerpo.getItems() * cuerpo.getSplit(), indice),
									cuerpo.getDatos());

						}

						catch (Exception e1) {

						}

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