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

import com.buttons.indicators.IndicatorButton;
import com.buttons.indicators.Indicators;
import com.jicons.Anterior;
import com.jicons.First;
import com.jicons.Last;
import com.jicons.Posterior;
import com.spinner.simple.Spinner;

import mthos.JMthos;

@SuppressWarnings("serial")
class NumPagination extends JPanel {

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

	private int paso;

	private int numeroPaginas;

	private Color indicador;

	private Indicators indicadorType;

	public int getStep() {

		return step;

	}

	public void setNumeroPaginas(int numeroPaginas) {

		this.numeroPaginas = numeroPaginas;

	}

	public void setStep(int step) {

		this.step = step;

	}

	public void setPaso(int paso) {

		this.paso = paso;

	}

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

	public void setIndice(int indice) {

		this.indice = indice;

	}

	public int getIndice() {

		try {

			return numeros.getIndice();

		}

		catch (Exception e) {

			return 1;

		}

	}

	public void last(int filter, Cuerpo cuerpo) {

		try {

			indice = filter;

			calcularNumeroPaginas(cuerpo);

			if (indice >= numeroPaginas) {

				indice = numeroPaginas;

			}

			cuerpo.verDatos(

					JMthos.calcularSucesionAritmeticaAInt("1#0,2#" + cuerpo.getSplit() * cuerpo.getItems(), indice),
					cuerpo.getDatos());

			indice--;

			if ((filter - indice) + 1 < step) {

				verNumeros(1, filter, step, cuerpo);

			}

			else {

				verNumeros(indice, filter, step, cuerpo);

			}

			numeros.getNumeros().getLast().setPaintSelected(true);

			indice++;

		}

		catch (Exception e) {

		}

	}

	private void anterior(int filter, Cuerpo cuerpo) {

		try {

			if (numeros.getNumeroBotonPresionado() == -1) {

				--indice;

			}

			if (indice < 1) {

				indice = 1;

			}

			cuerpo.verDatos(
					JMthos.calcularSucesionAritmeticaAInt("1#0,2#" + cuerpo.getSplit() * cuerpo.getItems(), indice),
					cuerpo.getDatos());

			if ((filter - indice) < step) {

				verNumeros(1, filter, step, cuerpo);

			}

			else {

				verNumeros(indice, filter, step, cuerpo);

			}

			indice--;

			numeros.getNumeros().get(indice).setPaintSelected(true);

			indice++;

		}

		catch (Exception e) {

		}

	}

	public void posterior(int filter, Cuerpo cuerpo) {

		try {

			if (indice == 0) {

				indice = 1;

			}

			indice++;

			if (indice > numeroPaginas) {

				indice = numeroPaginas;

			}

			cuerpo.verDatos(
					JMthos.calcularSucesionAritmeticaAInt("1#0,2#" + cuerpo.getSplit() * cuerpo.getItems(), indice),
					cuerpo.getDatos());

			verNumeros(indice, filter, step, cuerpo);

			numeros.getNumeros().get(JMthos.encontrarIndice(numeros.getContadorNumeros(), indice))
					.setPaintSelected(true);

		}

		catch (Exception e) {

		}

	}

	public void verNumeros(int numbers, int filter, int step, Cuerpo cuerpo) {

		if (numbers == filter) {

			numbers--;

		}

		if (numbers == 0 && filter == 1 && step == 1) {

			numbers = 1;

		}

		numeros = new Numeros(numbers, filter, step, cuerpo, this, footerFont, pagForeground);

		numero.setBounds(Math.round(getWidth() * 0.15f), 0, Math.round(getWidth() * 0.7f), getHeight());

		numero.removeAll();

		numeros.setBackground(fondo);

		numero.add(numeros);

		numero.revalidate();

		numero.repaint();

	}

	public void verPrimero(int filter, Cuerpo cuerpo) {

		try {

			indice = 0;

			if (filter > 1) {

				verNumeros(1, filter, step, cuerpo);

				try {

					cuerpo.verDatos(0, cuerpo.getDatos());

				}

				catch (Exception e) {

				}

			}

			else {

				verNumeros(1, filter, step, cuerpo);

				try {

					cuerpo.verDatos(0, cuerpo.getDatos());

				}

				catch (Exception e) {

				}

			}

			numeros.getNumeros().getFirst().setPaintSelected(true);

		}

		catch (Exception e) {

		}

	}

	public NumPagination(Spinner spinner, int numbers, int filter, int step, Cuerpo cuerpo) {

		calcularNumeroPaginas(cuerpo);

		paso = filter;

		fondo = Color.WHITE;

		this.step = step;

		footerFont = getFont();

		numeros = new Numeros(1, numbers, step, cuerpo, this, footerFont, pagForeground);

		primero = new JLabel();

		primero.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				verPrimero(paso, cuerpo);

			}

		});

		primero.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				verPrimero(paso, cuerpo);

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

				anterior(paso, cuerpo);

			}

		});

		anterior.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				anterior(paso, cuerpo);

			}

		});

		siguiente = new JLabel();

		siguiente.setBounds(292, 0, 103, 300);

		siguiente.setBackground(Color.WHITE);

		siguiente.setIcon(new Posterior());

		siguiente.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				posterior(paso, cuerpo);

			}

		});

		siguiente.addMouseListener(new MouseAdapter() {
			@Override

			public void mousePressed(MouseEvent e) {

				posterior(paso, cuerpo);

			}

		});

		numero = new JPanel();

		numero.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				if (e.getWheelRotation() == 1) {

					posterior(paso, cuerpo);

				}

				else {

					anterior(paso, cuerpo);

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

				verPrimero(paso, cuerpo);

			}

		});

		last = new JLabel();

		last.setIcon(new Last());

		last.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				last(paso, cuerpo);

			}

		});

		last.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				last(paso, cuerpo);

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

	private void calcularNumeroPaginas(Cuerpo cuerpo) {

		numeroPaginas = JMthos.dividirYRedondearAEntero(cuerpo.getDatos().size(),
				(cuerpo.getItems() * cuerpo.getSplit()));

	}

	public Color getIndicador() {

		return indicador;

	}

	public void setIndicador(Color color) {

		try {

			indicador = color;

		}

		catch (Exception e) {

		}

	}

	public Indicators getIndicadorType() {

		return indicadorType;

	}

	public void setIndicadorType(Indicators indicadorType) {

		try {

			this.indicadorType = indicadorType;
			for (IndicatorButton valor : numeros.getNumeros()) {

				valor.setBorderType(indicadorType);

			}
		}

		catch (Exception e) {

		}

	}

	public void setIndicadorDashPattern(int pattern) {

		try {

			for (IndicatorButton valor : numeros.getNumeros()) {

				valor.setDashPattern(pattern);

			}

		}

		catch (Exception e) {

		}

	}

	public void setIndicadorDotPattern(int pattern) {

		try {

			for (IndicatorButton valor : numeros.getNumeros()) {

				valor.setDotPattern(pattern);

			}

		}

		catch (Exception e) {

		}

	}
}