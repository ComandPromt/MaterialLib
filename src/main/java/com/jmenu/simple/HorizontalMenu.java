package com.jmenu.simple;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.label.others.SimpleLabel;
import com.layout.MaterialPanelLayout;

@SuppressWarnings("serial")
public class HorizontalMenu extends JPanel {

	private JPanel cabecera;

	private JPanel panel1;

	private MaterialPanelLayout panel;

	private ArrayList<SimpleLabel> header;

	public ArrayList<SimpleLabel> getHeader() {

		return header;

	}

	public void setContentPanel(JComponent newPanel) {

		panel1.removeAll();

		panel1.add(newPanel);

		panel1.revalidate();

		panel1.repaint();

	}

	public void setItemForeground(Color color) {

		for (int i = 0; i < cabecera.getComponentCount(); i++) {

			cabecera.getComponent(i).setForeground(color);

		}

	}

	public void setItemFont(Font font) {

		for (int i = 0; i < cabecera.getComponentCount(); i++) {

			cabecera.getComponent(i).setFont(font);

		}

	}

	public JPanel getCabecera() {

		return cabecera;

	}

	public void setPanelBackground(Color color) {

		try {

			panel.setBackground(color);

		}

		catch (Exception e) {

		}

	}

	public void setHeaderBackground(Color color) {

		try {

			cabecera.setBackground(color);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setBackground(Color bg) {

		try {

			super.setBackground(bg);

			cabecera.setBackground(bg);

			panel1.setBackground(bg);

			panel.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setForeground(Color fg) {

		try {

			try {

				for (int i = 0; i < cabecera.getComponentCount(); i++) {

					cabecera.getComponent(i).setForeground(fg);

				}

			}

			catch (Exception e) {

			}

		}

		catch (Exception e) {

		}

	}

	public void setIcons(List<ImageIcon> icons) {

		try {

			for (int i = 0; i < cabecera.getComponentCount(); i++) {

				JLabel component = (JLabel) cabecera.getComponent(i);

				component.setIcon(icons.get(i));

			}

		}

		catch (Exception e) {

		}

	}

	public void setFont(Font font) {

		try {

			for (int i = 0; i < cabecera.getComponentCount(); i++) {

				cabecera.getComponent(i).setFont(font);

			}

		}

		catch (Exception e) {

		}

	}

	public void setAlign(int align) {

		try {

			for (int i = 0; i < cabecera.getComponentCount(); i++) {

				JComponent component = (JComponent) cabecera.getComponent(i);

				component.setAlignmentX(align);

			}

		}

		catch (Exception e) {

		}

	}

	public HorizontalMenu(String list, String separator, List<JComponent> components) {

		this(Arrays.asList(list.split(separator)), components, 0);

	}

	public HorizontalMenu(String list, List<JComponent> components) {

		this(Arrays.asList(list.split(",")), components, 0);

	}

	public HorizontalMenu(List<String> list, List<JComponent> components) {

		this(list, components, 0);

	}

	public HorizontalMenu(List<String> list, List<JComponent> components, int porcentaje) {

		this(list, components, 0, porcentaje);

	}

	public HorizontalMenu(String list, String separator, List<JComponent> components, int porcentaje) {

		this(Arrays.asList(list.split(separator)), components, 0, porcentaje);

	}

	public HorizontalMenu(String list, List<JComponent> components, int alturaCabeceraFija, int porcentaje) {

		this(Arrays.asList(list.split(",")), components, alturaCabeceraFija, porcentaje);

	}

	public HorizontalMenu(String list, List<JComponent> components, String separador, int alturaCabeceraFija,
			int porcentaje) {

		this(Arrays.asList(list.split(separador)), components, alturaCabeceraFija, porcentaje);

	}

	public HorizontalMenu(String list, List<JComponent> components, int porcentaje) {

		this(Arrays.asList(list.split(",")), components, 0, porcentaje);

	}

	public HorizontalMenu(List<String> list, List<JComponent> components, int alturaCabeceraFija, int porcentaje) {

		if (alturaCabeceraFija == 0 && porcentaje < 1) {

			porcentaje = 50;

		}

		header = new ArrayList<>();

		panel1 = new JPanel();

		setLayout(new GridLayout());

		cabecera = new JPanel();

		if (list != null && !list.isEmpty()) {

			cabecera.setLayout(new GridLayout(1, list.size()));

		}

		SimpleLabel label;

		for (int i = 0; i < list.size(); i++) {

			label = new IndexedLabel(list.get(i), i, list.size(), components.get(i), this);

			header.add(label);

			cabecera.add(label);

		}

		panel1.setLayout(new GridLayout());

		if (!components.isEmpty()) {

			panel1.add(components.get(0));

		}

		ArrayList<JComponent> lista = new ArrayList<>();

		lista.add(cabecera);

		lista.add(panel1);

		ArrayList<Integer> porcentajes = new ArrayList<>();

		porcentajes.add(porcentaje);

		porcentajes.add(100 - porcentaje);

		panel = new MaterialPanelLayout(lista, porcentajes, alturaCabeceraFija, true);

		add(panel);

		setBackground(Color.WHITE);

	}

}
