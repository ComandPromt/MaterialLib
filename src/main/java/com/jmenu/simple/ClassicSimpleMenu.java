package com.jmenu.simple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import com.jmenu.paneles.Cabecera;
import com.jmenu.paneles.IconoCabecera;
import com.jmenu.paneles.ItemMenu;

@SuppressWarnings("serial")
public class ClassicSimpleMenu extends JPanel {

	private int anchoIcono;

	private int alto;

	private int ancho;

	private int alturaPanel;

	private boolean abrir;

	private JPanel panel;

	private IconoCabecera cabecera;

	private LinkedList<JComponent> items;

	public LinkedList<JComponent> getItems() {

		return items;

	}

	public JComponent getItem(int index) {

		JComponent resultado = null;

		if (items != null && !items.isEmpty()) {

			try {

				if (index < 0) {

					index = 0;

				}

				if (index >= items.size()) {

					index = items.size() - 1;

				}

				resultado = items.get(index);

			}

			catch (Exception e) {

			}

		}

		return resultado;

	}

	public JPanel getPanel() {

		return panel;

	}

	public int getAncho() {

		return ancho;

	}

	public void setAncho(int ancho) {

		this.ancho = ancho;

		repaint();

	}

	public void setIcon(Icon icon) {

		try {

			cabecera.setIcon(icon);

		}

		catch (Exception e) {

		}

	}

	public int getAlto() {

		return alto;

	}

	public void setAlto(int alto) {

		this.alto = alto;

		revalidate();

		repaint();

	}

	public int getAlturaPanel() {

		return alturaPanel;

	}

	public void setAlturaPanel(int alturaPanel) {

		this.alturaPanel = alturaPanel;

		revalidate();

		repaint();

	}

	private void click() {

		revalidate();

		repaint();

	}

	public void hacerClick() {

		abrir = !abrir;

		click();

	}

	public ClassicSimpleMenu(Color seleccion, Color fondo, List<String> lista, List<String> iconos,
			List<JComponent> components) {

		items = new LinkedList<>();

		addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				hacerClick();

			}

		});

		if (seleccion == null) {

			seleccion = Color.LIGHT_GRAY;

		}

		if (fondo == null) {

			fondo = Color.WHITE;

		}

		anchoIcono = 100;

		alto = 800;

		ancho = 180;

		alturaPanel = 100;

		setLayout(null);

		cabecera = new IconoCabecera(anchoIcono, anchoIcono);

		cabecera.setBackground(Color.WHITE);

		cabecera.setBounds(0, 0, anchoIcono, anchoIcono);

		add(cabecera);

		panel = new JPanel();

		panel.setBounds(0, anchoIcono, ancho, alturaPanel);

		if (lista != null) {

			JComponent item = null;

			Cabecera cabecera;

			String dato;

			if (lista != null && !lista.isEmpty()) {

				for (int i = 0; i < lista.size(); i++) {

					if (iconos != null && !iconos.isEmpty() && i < iconos.size()) {

						cabecera = new Cabecera();

						if (i < lista.size()) {

							cabecera.setText(lista.get(i));

						}

						dato = iconos.get(i);

						if (new File(dato).exists()) {

							cabecera.setIcon(new ImageIcon(dato));

						}

						cabecera.addMouseListener(new MouseAdapter() {

							@Override

							public void mousePressed(MouseEvent e) {

								hacerClick();

							}

						});

						items.add(cabecera);

						panel.add(cabecera);

					}

					else {

						item = new ItemMenu(lista.get(i), seleccion, fondo);

						item.addMouseListener(new MouseAdapter() {

							@Override

							public void mousePressed(MouseEvent e) {

								hacerClick();

							}

						});

						items.add(item);

						panel.add(item);

					}

				}

			}

		}

		if (components != null && !components.isEmpty()) {

			LinkedList<JComponent> listaPaneles = new LinkedList<>();

			for (int i = 0; i < components.size(); i++) {

				listaPaneles.add(components.get(i));

			}

			for (int i = 0; i < listaPaneles.size(); i++) {

				items.add(listaPaneles.get(i));

				panel.add(listaPaneles.get(i));

			}

		}

		panel.setLayout(new GridLayout(items.size(), 1));

		add(panel);

		panel.setVisible(false);

	}

	public int getAnchoIcono() {

		return anchoIcono;

	}

	public void setAnchoIcono(int anchoIcono) {

		this.anchoIcono = anchoIcono;

		revalidate();

		repaint();

	}

	@Override
	protected void paintComponent(Graphics g) {

		cabecera.setSize(anchoIcono, anchoIcono);

		if (abrir) {

			setSize(ancho, anchoIcono + alturaPanel);

			panel.setBounds(0, anchoIcono, ancho, alturaPanel);

			panel.setVisible(true);

		}

		else {

			setSize(anchoIcono, anchoIcono);

			panel.setBounds(0, anchoIcono, ancho, alturaPanel);

			panel.setVisible(false);

			super.paintComponent(g);

		}

	}

}
