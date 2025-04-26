package com.layout;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MaterialPanelLayout extends JPanel {

	private List<JComponent> componentes;
	private List<Integer> porcentajes; // Tamaños relativos en porcentaje (opcional)
	private boolean vertical;
	private int tamanioFijo; // Altura o ancho fijo para la cabecera

	/**
	 * @wbp.parser.constructor
	 */
	public MaterialPanelLayout(List<JComponent> components, int alturaCabeceraFija, boolean vertical) {
		this(components, null, alturaCabeceraFija, vertical);
	}

	public MaterialPanelLayout(JComponent[] components, List<Integer> porcentajes, int alturaCabeceraFija,
			boolean vertical) {
		this(Arrays.asList(components), porcentajes, alturaCabeceraFija, vertical);
	}

	public MaterialPanelLayout(JComponent[] components, List<Integer> porcentajes, boolean vertical) {
		this(Arrays.asList(components), porcentajes, 0, vertical);
	}

	public MaterialPanelLayout(List<JComponent> components, List<Integer> porcentajes, boolean vertical) {
		this(components, porcentajes, 0, vertical);
	}

	public MaterialPanelLayout(List<JComponent> componentes, List<Integer> porcentajes, int tamanioFijo,
			boolean vertical) {
		this.componentes = componentes;
		this.porcentajes = porcentajes;
		this.tamanioFijo = tamanioFijo;
		this.vertical = vertical;
		initialize();
	}

	private void initialize() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				redimensionar();
			}
		});

		if (tamanioFijo < 1) {

			setLayout(new BoxLayout(this, vertical ? BoxLayout.Y_AXIS : BoxLayout.X_AXIS));

		}

		else {

			setLayout(null);

		}

	}

	private void redimensionar() {
		removeAll();

		boolean usarPorcentajes = porcentajes != null && !porcentajes.isEmpty();
		int totalRestante = vertical ? getHeight() - tamanioFijo : getWidth() - tamanioFijo;
		int porcentaje = 0;
		int size;
		Dimension dimension;
		JComponent com;

		try {

			for (int i = 0; i < 2; i++) {
				com = componentes.get(i);

				if (i == 0 && tamanioFijo > 0) {
					// Cabecera con tamaño fijo
					size = tamanioFijo;

				} else if (usarPorcentajes && i - 1 < porcentajes.size()) {
					// Tamaño según porcentaje (ajustado a espacio restante)
					porcentaje = porcentajes.get(i - 1); // el índice de porcentaje empieza en 0 para el segundo
															// componente
					size = (int) (porcentaje / 100.0 * totalRestante);
				} else {
					// Tamaño igual entre los componentes restantes
					int restantes = componentes.size() - 1;
					size = restantes > 0 ? totalRestante / restantes : totalRestante;
				}

				dimension = vertical ? new Dimension(getWidth(), size) : new Dimension(size, getHeight());

				com.setPreferredSize(dimension);

				if (tamanioFijo > 0) {

					if (i == 0) {

						com.setBounds(0, 0, getWidth(), tamanioFijo);

					} else {
						com.setBounds(0, tamanioFijo, getWidth(), getHeight() - tamanioFijo);
					}

				}

				add(com);

			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		revalidate();
		repaint();
	}

}
