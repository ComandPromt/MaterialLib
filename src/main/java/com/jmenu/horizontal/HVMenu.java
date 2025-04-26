package com.jmenu.horizontal;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.layout.MaterialPanelLayout;

@SuppressWarnings("serial")
public class HorizontalMenu extends JPanel {

	private JPanel cabecera;

	private JPanel panel1;

	private MaterialPanelLayout panel;

	public void setPanelBackground(Color color) {

		try {

			panel.setBackground(color);

		}

		catch (Exception e) {

		}

	}

	public void setHeaderaBackground(Color color) {

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

	public HorizontalMenu(List<String> list, List<JComponent> components, int porcentaje) {

		if (porcentaje < 1) {

			porcentaje = 50;

		}

		panel1 = new JPanel();

		setLayout(new GridLayout());

		cabecera = new JPanel();

		if (list != null && !list.isEmpty()) {

			cabecera.setLayout(new GridLayout(1, list.size()));

		}

		for (int i = 0; i < list.size(); i++) {

			cabecera.add(new SimpleLabel(list.get(i), panel1, components.get(i)));

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

		panel = new MaterialPanelLayout(lista, porcentajes, true);

		add(panel);

		setBackground(Color.WHITE);

	}

}
