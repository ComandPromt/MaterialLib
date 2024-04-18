package com.jmenu.paneles;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.jmenu.simple.SimpleMenu;

@SuppressWarnings("serial")
public class PanelItem extends JPanel {

	private JComponent component;

	private int indice;

	private SimpleMenu simpleMenu;

	private boolean vacio;

	@Override
	public void setBackground(Color bg) {

		try {

			component.setBackground(bg);

			super.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public void setItemMenu(Color itemMenu) {

		try {

			component.setForeground(itemMenu);

		}

		catch (Exception e) {

		}

	}

	public void setPanel(int indice, SimpleMenu simpleMenu) {

		this.indice = indice;

		this.simpleMenu = simpleMenu;

	}

	public PanelItem(JComponent component, final Color fondo) {

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				setBackground(getForeground());

			}

			@Override
			public void mouseExited(MouseEvent e) {

				setBackground(fondo);

			}

			@Override
			public void mousePressed(MouseEvent e) {

				try {

					simpleMenu.setPanel(indice);

					if (vacio) {

						simpleMenu.clickMenu();

						simpleMenu.clickMenu();

					}

				}

				catch (Exception e1) {

				}

			}

		});

		this.component = component;

		setBackground(fondo);

		setLayout(new GridLayout(1, 1));

		add(component);

	}

	public void setVacio(boolean vacio) {

		this.vacio = vacio;

	}

}
