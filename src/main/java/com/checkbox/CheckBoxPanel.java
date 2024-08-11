package com.checkbox;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CheckBoxPanel extends JPanel {

	private LinkedList<CheckBoxCustom> lista;

	public LinkedList<CheckBoxCustom> getLista() {

		return lista;

	}

	public void setColorActivo(Color color) {

		for (CheckBoxCustom value : lista) {

			value.setColorActivo(color);

		}

	}

	public void setColorReposo(Color color) {

		for (CheckBoxCustom value : lista) {

			value.setColorReposo(color);

		}

	}

	public void setFondo(Color color) {

		for (CheckBoxCustom value : lista) {

			value.setFondo(color);

		}

	}

	public void setFondo2(Color color) {

		for (CheckBoxCustom value : lista) {

			value.setBackground(color);

		}

	}

	public void sumarAlto(int alto) {

		for (CheckBoxCustom value : lista) {

			value.sumarAlto(alto);

		}

	}

	public void setPosition(Point position) {

		for (CheckBoxCustom value : lista) {

			value.setPosition(position);

		}

	}

	public void setLeft(int left) {

		for (CheckBoxCustom value : lista) {

			value.setLeft(left);

		}

	}

	@Override
	public void setForeground(Color fg) {

		try {

			for (CheckBoxCustom value : lista) {

				value.setForeground(fg);

			}
		}

		catch (Exception e) {

		}
	}

	@Override
	public void setFont(Font font) {

		try {

			for (CheckBoxCustom value : lista) {

				value.setFont(font);

			}

		}

		catch (Exception e) {

		}

	}

	/**
	 * @wbp.parser.constructor
	 */
	public CheckBoxPanel(List<String> list) {

		lista = new LinkedList<>();

		for (int i = 0; i < list.size(); i++) {

			lista.add(new CheckBoxCustom(list.get(i)));

			add(lista.getLast());

		}

	}

	public CheckBoxPanel(List<String> list, int rows, int cols) {

		lista = new LinkedList<>();

		setLayout(new GridLayout(rows, cols));

		for (int i = 0; i < list.size(); i++) {

			lista.add(new CheckBoxCustom(list.get(i)));

			add(lista.getLast());

		}

	}

}
