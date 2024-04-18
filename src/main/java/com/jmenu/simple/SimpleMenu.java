package com.jmenu.simple;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import com.jmenu.paneles.Cabecera;
import com.jmenu.paneles.ItemMenu;
import com.jmenu.paneles.PanelItem;
import com.jmenu.paneles.Separador;

@SuppressWarnings("serial")

public class SimpleMenu extends JPanel {

	private Color colorCabecera;

	private Color itemMenu;

	private Cabecera cabecera;

	private JPanel categorias;

	private boolean menuAbierto;

	private JPanel menu;

	private List<String> categories;

	private boolean test;

	private ArrayList<ItemMenu> items;

	private Color seleccion;

	private Color fondoMenu;

	private JPanel btnNewButton;

	private JPanel panel;

	private int indice;

	private List<JPanel> paneles;

	private int ancho;

	private List<JComponent> componentes;

	private int alturaCabecera;

	private int alturaMenu;

	public int getAlturaMenu() {

		return alturaMenu;

	}

	public void setAlturaMenu(int alturaMenu) {

		this.alturaMenu = alturaMenu;

	}

	public int getAlturaCabecera() {

		return alturaCabecera;

	}

	public void setAlturaCabecera(int alturaCabecera) {

		this.alturaCabecera = alturaCabecera;

	}

	@Override
	public void setFont(Font font) {

		try {

			cabecera.setFont(font);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setForeground(Color fg) {

		try {

			cabecera.setForeground(fg);

		}

		catch (Exception e) {

		}

	}

	public void setHeaderIcon(Icon icon) {

		try {

			cabecera.setIcon(icon);

		}

		catch (Exception e) {

		}

	}

	public void setText(String text) {

		try {

			cabecera.setText(text);

		}

		catch (Exception e) {

		}

	}

	public int getAncho() {

		return ancho;

	}

	public void setAnchoMenu(int ancho) {

		if (ancho < 1) {

			ancho = 300;

		}

		this.ancho = ancho;

	}

	public JPanel getCategorias() {

		return categorias;

	}

	public void setCategorias(JPanel categorias) {

		this.categorias = categorias;

	}

	public Color getItemMenu() {

		return itemMenu;

	}

	public void setItemMenu(Color itemMenu) {

		this.itemMenu = itemMenu;

	}

	public Color getColorCabecera() {

		return colorCabecera;

	}

	public void setAnchoIcono(int ancho) {

		try {

			cabecera.setAnchoIcono(ancho);

		}

		catch (Exception e) {

		}

	}

	public void setColorCabecera(Color colorCabecera) {

		try {

			this.colorCabecera = colorCabecera;

			cabecera.setBackground(colorCabecera);

		}

		catch (Exception e) {

		}

	}

	public Color getSeleccionMenu() {

		return seleccion;

	}

	public void setSeleccionMenu(Color seleccionMenu) {

		this.seleccion = seleccionMenu;

	}

	public Color getFondoMenu() {

		return fondoMenu;

	}

	public void setFondoMenu(Color fondoMenu) {

		this.fondoMenu = fondoMenu;

	}

	public void clickMenu() {

		try {

			abrirMenu(!menuAbierto);

		}

		catch (Exception e1) {

		}

	}

	private void ponerLayout() {

		int num1 = 0;

		int num2 = 0;

		if (categories != null) {

			num1 = categories.size();

		}

		if (componentes != null) {

			num2 = componentes.size();

		}

		categorias.setLayout(new GridLayout(num1 + num2, 1));

	}

	public SimpleMenu(List<String> categories, List<JPanel> panels, List<JComponent> componentes) {

		try {

			alturaCabecera = 50;

			this.componentes = componentes;

			indice = -1;

			ancho = 145;

			try {

				this.panel = panels.get(0);

			}

			catch (Exception e) {
				e.printStackTrace();
			}

			paneles = panels;

			seleccion = Color.LIGHT_GRAY;

			fondoMenu = Color.WHITE;

			items = new ArrayList<>();

			addComponentListener(new ComponentAdapter() {

				@Override

				public void componentResized(ComponentEvent e) {

					cabecera.setBounds(0, 0, getWidth(), alturaCabecera);

					menu.setBounds(0, alturaCabecera, getWidth(), getHeight() - alturaCabecera);

					clickMenu();

				}

			});

			this.categories = categories;

			categorias = new JPanel();

			setLayout(null);

			cabecera = new Cabecera();

			colorCabecera = Color.WHITE;

			cabecera.setBackground(colorCabecera);

			cabecera.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {

					clickMenu();

				}

			});

			add(cabecera);

			cabecera.setLayout(null);

			menu = new JPanel();

			menu.setBackground(Color.GREEN);

			add(menu);

			menu.setLayout(new GridLayout());

			setPanel(0);

			abrirMenu(false);

			clickMenu();

		}

		catch (Exception e) {

		}

	}

	void abrirMenu(boolean abrir) {

		try {

			if (abrir) {

				menu.removeAll();

				if (alturaMenu < 1 || alturaMenu > menu.getHeight()) {

					alturaMenu = menu.getHeight();

				}

				else if (alturaMenu < menu.getHeight()) {

					categorias.setBackground(fondoMenu);

					menu.setBackground(fondoMenu);

				}

				categorias.setBounds(0, 0, ancho, alturaMenu);

				ponerLayout();

				menu.add(categorias);

				ItemMenu item;

				categorias.removeAll();

				items.clear();

				int contadorIndice = -1;

				if (categories != null) {

					for (int i = 0; i < categories.size(); i++) {

						if (categories.get(i).startsWith("#")) {

							if (categories.get(i).equals("#")) {

								categorias.add(new Separador(null, itemMenu, fondoMenu));

							}

							else {

								categorias.add(new Separador(categories.get(i).substring(1), itemMenu, fondoMenu));

							}

						}

						else {

							contadorIndice++;

							item = new ItemMenu(categories.get(i), seleccion, fondoMenu);

							if (itemMenu != null) {

								item.setForeground(itemMenu);

							}

							if (contadorIndice < paneles.size()) {

								item.setPanel(contadorIndice, this);

							}

							items.add(item);

							categorias.add(item);

						}

					}

				}

				if (componentes != null) {

					PanelItem textIcon;

					try {

						for (int i = 0; i < componentes.size(); i++) {

							textIcon = new PanelItem(componentes.get(i), fondoMenu);

							if (categories == null || categories.isEmpty()) {

								textIcon.setVacio(true);

							}

							textIcon.setItemMenu(itemMenu);

							textIcon.setForeground(seleccion);

							textIcon.setPanel(++contadorIndice, this);

							categorias.add(textIcon);

						}

					}

					catch (Exception e) {

						e.printStackTrace();

					}

				}

				categorias.setVisible(true);

				categorias.repaint();

				repaint();

				if (!test) {

					test = true;

					abrirMenu(false);

					abrirMenu(true);

				}

				if (indice > -1) {

					try {

						panel = paneles.get(indice);

					}

					catch (Exception e) {

					}

				}

				panel.setBounds(categorias.getWidth(), 0, menu.getWidth() - categorias.getWidth(), menu.getHeight());

				btnNewButton = panel;

				menu.add(btnNewButton);

			}

			else {

				categorias.removeAll();

				categorias.setVisible(false);

				if (panel != null) {

					panel.setBounds(0, 0, menu.getWidth(), menu.getHeight());

				}

				panel = new JPanel();

				btnNewButton = panel;

				menu.setLayout(null);

				menu.add(panel);

			}

			menuAbierto = !menuAbierto;

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setPanel(int indice) {

		try {

			this.indice = indice;

			this.panel = paneles.get(indice);

			menu.removeAll();

			categorias.setBounds(0, 0, getWidth(), menu.getHeight());

			ponerLayout();

			menu.add(categorias);

			ItemMenu item;

			categorias.removeAll();

			items.clear();

			for (int i = 0; i < categories.size(); i++) {

				item = new ItemMenu(categories.get(i), seleccion, fondoMenu);

				if (itemMenu != null) {

					item.setForeground(itemMenu);

				}

				items.add(item);

				categorias.add(item);

			}

			categorias.setVisible(true);

			panel.setBounds(categorias.getWidth(), 0, menu.getWidth() - categorias.getWidth(), menu.getHeight());

			btnNewButton = panel;

			menu.add(btnNewButton);

			abrirMenu(false);

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

}