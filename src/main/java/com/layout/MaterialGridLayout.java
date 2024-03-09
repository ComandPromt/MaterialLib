package com.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class MaterialGridLayout implements LayoutManager {

	private List<Component> components;

	private List<Integer> list;

	private boolean vertical;

	public MaterialGridLayout(List<Component> components, List<Integer> list, boolean vertical) {

		this.components = components;

		this.list = list;

		this.vertical = vertical;

		components.get(0).addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				layoutContainer((Container) e.getSource());

			}

		});

	}

	@Override
	public void addLayoutComponent(String name, Component comp) {

	}

	@Override
	public void removeLayoutComponent(Component comp) {

	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {

		return new Dimension(0, 0);

	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {

		return new Dimension(0, 0);

	}

	@Override
	public void layoutContainer(Container parent) {

		try {

			int totalSize = vertical ? parent.getHeight() : parent.getWidth();

			int totalComponents = components.size();

			for (int i = 0; i < totalComponents; i++) {

				Component comp = components.get(i);

				int size = comprobarSize(list.get(i), totalSize);

				if (vertical) {

					comp.setBounds(0, i * size, parent.getWidth(), size);

				}

				else {

					comp.setBounds(i * size, 0, size, parent.getHeight());

				}

			}

			parent.revalidate();

		}

		catch (Exception e) {

		}

	}

	private int comprobarSize(int porcentaje, int totalSize) {

		return porcentaje > 0 ? (int) ((porcentaje / 100.0) * totalSize) : totalSize / components.size();

	}

}
