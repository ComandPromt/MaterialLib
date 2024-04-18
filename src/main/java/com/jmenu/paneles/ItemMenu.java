package com.jmenu.paneles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.jmenu.simple.SimpleMenu;

@SuppressWarnings("serial")

public class ItemMenu extends JLabel {

	private Color fondo;

	private Color seleccion;

	private Color backgroundColor;

	private int indice;

	private SimpleMenu simpleMenu;

	private void setFondo(Color fondo) {

		this.fondo = fondo;

		repaint();

	}

	public Color getSelected() {

		return seleccion;

	}

	public void setSelected(Color selected) {

		this.seleccion = selected;

	}

	public Color getBackgroundColor() {

		return backgroundColor;

	}

	public void setBackgroundColor(Color backgroundColor) {

		this.backgroundColor = backgroundColor;

	}

	public ItemMenu(String text, Color selected, Color background) {

		super(text);

		setHorizontalAlignment(SwingConstants.CENTER);

		if (selected == null) {

			selected = Color.LIGHT_GRAY;

		}

		if (background == null) {

			background = Color.WHITE;

		}

		seleccion = selected;

		backgroundColor = background;

		fondo = backgroundColor;

		addMouseListener(new MouseAdapter() {

			@Override

			public void mouseEntered(MouseEvent e) {

				setFondo(seleccion);

			}

			@Override

			public void mouseExited(MouseEvent e) {

				setFondo(backgroundColor);

			}

			@Override

			public void mousePressed(MouseEvent e) {

				try {

					simpleMenu.setPanel(indice);

				}

				catch (Exception e1) {

				}

			}

		});

	}

	@Override
	protected void paintComponent(Graphics g) {

		g.setColor(fondo);

		g.fillRect(0, 0, getWidth(), getHeight());

		super.paintComponent(g);

	}

	public void setPanel(int indice, SimpleMenu simpleMenu) {

		this.indice = indice;

		this.simpleMenu = simpleMenu;

	}

	public int getIndice() {

		return indice;

	}

}
