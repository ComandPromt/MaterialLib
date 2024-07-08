package com.material.table;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jicons.Anterior;
import com.jicons.First;
import com.jicons.Last;
import com.jicons.Posterior;

import mthos.JMthos;

@SuppressWarnings("serial")
public class NumPagination extends JPanel {

	private Numeros numeros;

	private JPanel numero;

	private JLabel siguiente;

	private JLabel last;

	private JLabel anterior;

	private JLabel primero;

	private int step;

	private int indice;

	private Color fondo;

	private Font footerFont;

	private Color pagForeground;

	@Override
	public void setForeground(Color fg) {

		pagForeground = fg;

	}

	@Override
	public void setFont(Font font) {

		footerFont = font;

	}

	public Numeros getNumeros() {

		return numeros;

	}

	@Override
	public void setBackground(Color bg) {

		try {

			primero.setBackground(bg);

			numero.setBackground(bg);

			anterior.setBackground(bg);

			siguiente.setBackground(bg);

			last.setBackground(bg);

			fondo = bg;

		}

		catch (Exception e) {

		}

	}

	public int getIndice() {

		try {

			return numeros.getIndice();

		}

		catch (Exception e) {

			return 1;

		}

	}

	private void last(int filter, Cuerpo cuerpo) {

		indice = filter;

		cuerpo.verDatos(

				JMthos.calcularSucesionAritmeticaAInt("1#0,2#" + cuerpo.getSplit() * cuerpo.getItems(), indice),
				cuerpo.getDatos());

		verNumeros(indice, filter, step, cuerpo);

	}

	private void anterior(int filter, Cuerpo cuerpo) {

		--indice;

		if (indice < 1) {

			indice = 1;

		}

		cuerpo.verDatos(JMthos.calcularSucesionAritmeticaAInt("1#0,2#" + cuerpo.getSplit() * cuerpo.getItems(), indice),
				cuerpo.getDatos());

		verNumeros(indice, filter, step, cuerpo);

	}

	public void posterior(int filter, Cuerpo cuerpo) {

		++indice;

		if (indice > filter) {

			indice = filter;

		}

		if (indice == 1) {

			indice = 2;

		}

		cuerpo.verDatos(

				JMthos.calcularSucesionAritmeticaAInt("1#0,2#" + cuerpo.getSplit() * cuerpo.getItems(), indice),
				cuerpo.getDatos());

		verNumeros(indice, filter, step, cuerpo);

	}

	public void verNumeros(int numbers, int filter, int step, Cuerpo cuerpo) {

		if (numbers == filter) {

			numbers--;

		}

		numeros = new Numeros(numbers, filter, step, cuerpo, footerFont, pagForeground);

		numero.setBounds(Math.round(getWidth() * 0.15f), 0, Math.round(getWidth() * 0.7f), getHeight());

		numero.removeAll();

		numeros.setBackground(fondo);

		numero.add(numeros);

		numero.revalidate();

		numero.repaint();

	}

	private void verPrimero(int filter, Cuerpo cuerpo) {

		indice = 0;

		verNumeros(1, filter, step, cuerpo);

		try {

			cuerpo.verDatos(0, cuerpo.getDatos());

		}

		catch (Exception e) {

		}

	}

	public NumPagination(int numbers, int filter, int step, Cuerpo cuerpo) {

		fondo = Color.WHITE;

		this.step = step;

		footerFont = getFont();

		numeros = new Numeros(1, numbers, step, cuerpo, footerFont, pagForeground);

		primero = new JLabel();

		primero.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				verPrimero(filter, cuerpo);

			}

		});

		primero.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				verPrimero(filter, cuerpo);

			}

		});

		last = new JLabel();

		last.setIcon(new Last());

		primero.setBounds(0, 0, 97, 300);

		primero.setBackground(Color.WHITE);

		primero.setIcon(new First());

		anterior = new JLabel();

		anterior.setBackground(Color.WHITE);

		anterior.setIcon(new Anterior());

		anterior.setBounds(88, 0, 89, 300);

		anterior.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				anterior(filter, cuerpo);

			}

		});

		anterior.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				anterior(filter, cuerpo);

			}

		});

		siguiente = new JLabel();

		siguiente.setBounds(292, 0, 103, 300);

		siguiente.setBackground(Color.WHITE);

		siguiente.setIcon(new Posterior());

		siguiente.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				posterior(filter, cuerpo);

			}

		});

		siguiente.addMouseListener(new MouseAdapter() {
			@Override

			public void mousePressed(MouseEvent e) {

				posterior(filter, cuerpo);

			}

		});

		numero = new JPanel();

		numero.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				if (e.getWheelRotation() == 1) {

					posterior(filter, cuerpo);

				}

				else {

					anterior(filter, cuerpo);

				}

			}

		});

		anterior.setLayout(new GridLayout());

		numero.setLayout(new GridLayout());

		siguiente.setLayout(new GridLayout());

		last.setLayout(new GridLayout());

		setLayout(null);

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				primero.setBounds(0, 0, Math.round(getWidth() * 0.1f), getHeight());

				anterior.setBounds(Math.round(getWidth() * 0.1f), 0, Math.round(getWidth() * 0.1f), getHeight());

				siguiente.setBounds(Math.round(getWidth() * 0.8f), -1, Math.round(getWidth() * 0.1f), getHeight());

				last.setBounds(Math.round(getWidth() * 0.9f), 0, Math.round(getWidth() * 0.1f), getHeight());

				verPrimero(filter, cuerpo);

			}

		});

		last = new JLabel();

		last.setIcon(new Last());

		last.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				last(filter, cuerpo);

			}

		});

		last.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				last(filter, cuerpo);

			}

		});

		primero.setBackground(Color.WHITE);

		last.setBackground(Color.WHITE);

		anterior.setBackground(Color.WHITE);

		siguiente.setBackground(Color.WHITE);

		last.setBounds(395, 0, 89, 300);

		add(primero);

		add(anterior);

		add(numero);

		add(siguiente);

		add(last);

	}

}