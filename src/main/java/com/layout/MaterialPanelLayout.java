package com.layout;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MaterialPanelLayout extends JPanel {

	private List<JComponent> components;

	private List<Integer> list;

	private boolean vertical;

	public MaterialPanelLayout(List<JComponent> components, List<Integer> list, boolean vertical) {

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				redimensionar();

			}

		});

		this.components = components;

		this.list = list;

		this.vertical = vertical;

		setLayout(new BoxLayout(this, vertical ? BoxLayout.Y_AXIS : BoxLayout.X_AXIS));

		redimensionar();

	}

	private void redimensionar() {

		try {

			removeAll();

			for (int i = 0; i < components.size(); i++) {

				JComponent com = components.get(i);

				int size = comprobarSize(list.get(i), vertical);

				if (vertical) {

					com.setPreferredSize(new Dimension(getWidth(), size));

				}

				else {

					com.setPreferredSize(new Dimension(size, getHeight()));

				}

				add(com);

			}

			revalidate();

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	private int comprobarSize(int porcentaje, boolean vertical) {

		return porcentaje > 0 ? (int) ((porcentaje / 100.0) * (vertical ? getHeight() : getWidth()))
				: vertical ? getHeight() / components.size() : getWidth() / components.size();

	}

}
