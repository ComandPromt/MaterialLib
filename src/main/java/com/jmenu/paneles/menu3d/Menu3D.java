package com.jmenu.paneles.menu3d;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;

@SuppressWarnings("serial")

public class Menu3D extends JComponent {

	private List<EventMenu> events;

	private Color fondo;

	private Color arriba;

	private Color izquierda;

	private List<Menu3dItem> items;

	private int menuHeight = 50;

	private int shadowSize = 15;

	private int left = 60;

	private float angle = 150f;

	private int overIndex = -1;

	private int pressedIndex = -1;

	public void setBackgroundSelected(Color color) {

		try {

			for (Menu3dItem valor : items) {

				valor.setBackgroundSelected(color);

			}

		}

		catch (Exception e) {

		}

	}

	public void setColorShadowLeftSelected(Color color) {

		try {

			for (Menu3dItem valor : items) {

				valor.setColorShadowLeftSelected(color);

			}

		}

		catch (Exception e) {

		}

	}

	public void setColorShadowTopSelected(Color color) {

		try {

			for (Menu3dItem valor : items) {

				valor.setColorShadowTopSelected(color);

			}

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setBackground(Color bg) {

		try {

			for (Menu3dItem valor : items) {

				valor.setBackground(fondo);

			}

		}

		catch (Exception e) {

		}

	}

	void setColorShadowLeft(Color color) {

		try {

			for (Menu3dItem valor : items) {

				valor.setColorShadowLeft(color);

			}

		}

		catch (Exception e) {

		}

	}

	void setColorShadowTop(Color color) {

		try {

			for (Menu3dItem valor : items) {

				valor.setColorShadowTop(color);

			}

		}

		catch (Exception e) {

		}

	}

	private void iniciar() {

		setBackground(fondo);

		setColorShadowTop(arriba);

		setColorShadowLeft(izquierda);

		initAnimator();

	}

	public List<Menu3dItem> getItems() {

		return items;

	}

	public int getMenuHeight() {

		return menuHeight;

	}

	public void setMenuHeight(int menuHeight) {

		this.menuHeight = menuHeight;

	}

	public int getShadowSize() {

		return shadowSize;

	}

	public void setShadowSize(int shadowSize) {

		this.shadowSize = shadowSize;

	}

	public int getLeft() {

		return left;

	}

	public void setLeft(int left) {

		this.left = left;

	}

	public float getAngle() {

		return angle;

	}

	public void setAngle(float angle) {

		this.angle = angle;

	}

	public int getOverIndex() {

		return overIndex;

	}

	public void setOverIndex(int overIndex) {

		this.overIndex = overIndex;

	}

	public int getPressedIndex() {

		return pressedIndex;

	}

	public void setPressedIndex(int pressedIndex) {

		this.pressedIndex = pressedIndex;

	}

	public Menu3D(Color left, Color top, List<String> lista) {

		fondo = Color.WHITE;

		izquierda = left;

		arriba = top;

		events = new ArrayList<>();

		items = new ArrayList<>();

		for (String dato : lista) {

			addMenuItem(dato);

		}

		setLeft(50);

		setMenuHeight(100);

		setShadowSize(5);

		setPressedIndex(0);

		setOverIndex(0);

		setBackgroundSelected(Color.PINK);

		iniciar();

	}

	public Menu3D(LinkedList<String> lista, Color background, Color left, Color top) {

		if (lista == null || lista.isEmpty()) {

			lista = new LinkedList<>();

			lista.add("Text 1");

			lista.add("Text 2");

			lista.add("Text 3");

		}

		fondo = background;

		izquierda = left;

		arriba = top;

		events = new ArrayList<>();

		items = new ArrayList<>();

		for (String dato : lista) {

			addMenuItem(dato);

		}

		setLeft(50);

		setMenuHeight(100);

		setShadowSize(5);

		setPressedIndex(0);

		setOverIndex(0);

		setBackgroundSelected(Color.PINK);

		iniciar();

	}

	private void initAnimator() {

		MouseAdapter mouse = new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				int index = getOverIndex(e.getPoint());

				if ((index == 0 && pressedIndex == 0) || index != pressedIndex) {

					pressedIndex = index;

					if (pressedIndex != -1) {

						for (int i = 0; i < items.size(); i++) {

							if (i == index) {

								items.get(pressedIndex).getAnimator().show();

								hideMenu(pressedIndex);

							}

							else {

								items.get(i).setBackground(fondo);

							}

						}

					}

					runEvent();

				}

			}

		};

		addMouseListener(mouse);

	}

	public void addEvent(EventMenu event) {

		this.events.add(event);

	}

	private void runEvent() {

		for (EventMenu event : events) {

			event.menuSelected(pressedIndex);

		}

	}

	@Override
	public void setFont(Font font) {

		for (Menu3dItem valor : items) {

			valor.setFuente(font);

		}

	}

	public void addMenuItem(String menu) {

		int y = items.size() * menuHeight + left;

		Menu3dItem item = new Menu3dItem(this, left, y, menuHeight, shadowSize, menu, fondo, izquierda, arriba);

		items.add(item);

	}

	private int getOverIndex(Point mouse) {

		int index = -1;

		for (Menu3dItem d : items) {

			index++;

			if (d.isMouseOver(mouse)) {

				return index;

			}

		}

		return -1;

	}

	private void hideMenu(int exitIndex) {

		for (int i = 0; i < items.size(); i++) {

			if (i != exitIndex) {

				items.get(i).getAnimator().hide();

			}

		}

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		for (int i = items.size() - 1; i >= 0; i--) {

			items.get(i).render(g2, 360 - angle, left, this);

		}

		g2.dispose();

		super.paintComponent(g);

	}

}
