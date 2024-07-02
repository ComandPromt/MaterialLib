package com.material.table;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

import com.jicons.MovimientoTriangulos;

@SuppressWarnings("all")

public class Prueba extends javax.swing.JFrame {

	public Prueba() throws IOException {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
			}
		});

		getContentPane().setBackground(Color.WHITE);

		setTitle("");

		initComponents();

		setVisible(true);

	}

	public static void main(String[] args) {

		try {

			new Prueba().setVisible(true);

		}

		catch (Exception e) {

		}

	}

	public void initComponents() throws IOException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		String[] filtro = new String[1];

		filtro[0] = "jpg,png,gif";

		ArrayList<String> lista = new ArrayList<>();

		lista.add("aaaa");

		lista.add("bbb");

		JLabel label;

		JLabel orden;

		label = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		orden = new JLabel(" ");

		orden.setIcon(new MovimientoTriangulos());

		JPanel panel_2 = new JPanel();

		ArrayList<String> lista1 = new ArrayList<>();

		lista1.add("aaaa");
		lista1.add("aaaa");

		ArrayList<String> datos = new ArrayList<>();

		datos.add("11111");

		datos.add("222");
		datos.add("333");
		datos.add("444");
		datos.add("555");
		datos.add("6");
		datos.add("77");
		datos.add("888");
		datos.add("9");
		datos.add("10");

		try {

			BodyHeaderTable bodyHeaderTable = new BodyHeaderTable(lista1, datos, 4, 2, null);
//			bodyHeaderTable.setSeparador2(Color.BLUE);
//			bodyHeaderTable.setSeparador3(Color.GREEN);
//			bodyHeaderTable.setSeparador1(Color.RED);
			// bodyHeaderTable.setItemHeadBackground(Color.CYAN);
			// bodyHeaderTable.setFondoPaginacion(Color.ORANGE);

			// bodyHeaderTable.setCheckActiveHeaderBackground(Color.GRAY);

			// bodyHeaderTable.setBackground(Color.LIGHT_GRAY);

			// bodyHeaderTable.setCheckActiveHeader(Color.PINK);
			// bodyHeaderTable.setActiveIconBackground(Color.RED);
			// bodyHeaderTable.setDisableIconBackground(Color.WHITE);
			// bodyHeaderTable.setIconsBackground(Color.BLUE);

			bodyHeaderTable.setFont(new Font("Algerian", Font.PLAIN, 20));

			bodyHeaderTable.setPaginationFont(new Font("Algerian", Font.PLAIN, 20));

			bodyHeaderTable.setPaginationForeground(Color.BLUE);

			bodyHeaderTable.setHeadFont(new Font("Algerian", Font.PLAIN, 20));

			bodyHeaderTable.setHeadForeground(Color.GREEN);

			bodyHeaderTable.setForeground(Color.RED);

			panel_2.add(bodyHeaderTable);

		}

		catch (Exception e) {

			e.printStackTrace();

		}
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		getContentPane().add(panel_2);

		setSize(new Dimension(789, 625));

		setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent arg0) {

	}

	public void stateChanged(ChangeEvent e) {

	}
}
