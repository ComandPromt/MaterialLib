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

	private List<JComponent> components;

	private List<Integer> list;

	private boolean vertical;

	public MaterialPanelLayout(JComponent[] components, List<Integer> list, boolean vertical) {

		this.components = Arrays.asList(components);

		this.list = list;

		this.vertical = vertical;

		initialize();

	}

	public MaterialPanelLayout(List<JComponent> components, List<Integer> list, boolean vertical) {

		this.components = components;

		this.list = list;

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

		setLayout(new BoxLayout(this, vertical ? BoxLayout.Y_AXIS : BoxLayout.X_AXIS));

		redimensionar();

	}

	private void redimensionar() {

		removeAll();

		int porcentaje = 0;

		boolean nulo = false;

		if (list == null || list.isEmpty()) {

			porcentaje = Math.round(100 / components.size());

			nulo = true;

		}

		int size = 0;

		Dimension dimension;

		JComponent com;

		try {

			for (int i = 0; i < components.size(); i++) {

				if (!nulo && i < list.size()) {

					porcentaje = list.get(i);

				}

				com = components.get(i);

				size = (list != null && porcentaje > 0) ? comprobarSize(porcentaje, vertical)
						: (porcentaje > 0) ? porcentaje * getSize().height / 100 : getSize().height / components.size();

				dimension = vertical ? new Dimension(getSize().width, size) : new Dimension(size, getSize().height);

				com.setPreferredSize(dimension);

				add(com);

			}

		}

		catch (Exception e) {

		}

		revalidate();

	}

	private int comprobarSize(int porcentaje, boolean vertical) {

		return (int) (porcentaje / 100.0 * (vertical ? getSize().height : getSize().width));

	}

}
