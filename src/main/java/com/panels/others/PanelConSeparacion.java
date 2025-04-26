package com.panels.others;

import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class PanelConSeparacion extends JPanel {

	private int ancho;

	private int alto;

	public void setMargenes(int top, int left, int bottom, int right) {

		setBorder(new EmptyBorder(top, left, bottom, right));

	}

	public void setPorcentajeMargenes(int porcentaje, boolean calcularSobreAncho) {

		int dimension = 0;

		if (calcularSobreAncho) {

			dimension = ancho * (porcentaje / 100);

		}

		else {

			dimension = alto * (porcentaje / 100);

		}

		if (dimension > 0) {

			setBorder(new EmptyBorder(dimension, dimension, dimension, dimension));

		}

	}

	public void setMargenes(int pixels) {

		setBorder(new EmptyBorder(pixels, pixels, pixels, pixels));

	}

	public void setMargenSuperior(int pixels) {

		setBorder(new EmptyBorder(pixels, 0, 0, 0));

	}

	public void setMargenIzquierdo(int pixels) {

		setBorder(new EmptyBorder(0, pixels, 0, 0));

	}

	public void setMargenInferior(int pixels) {

		setBorder(new EmptyBorder(0, 0, pixels, 0));

	}

	public void setMargenDerecho(int pixels) {

		setBorder(new EmptyBorder(0, 0, 0, pixels));

	}

	public PanelConSeparacion(int rows, int cols) {

		setLayout(new GridLayout(rows, cols));

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				ancho = getWidth();

				alto = getHeight();

			}

		});

		ancho = getWidth();

		alto = getHeight();

	}

	public PanelConSeparacion() {

		this(1, 0);

	}

}
