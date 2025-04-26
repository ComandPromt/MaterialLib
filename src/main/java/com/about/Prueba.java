package com.about;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;

import com.material.table.MaterialTable;

@SuppressWarnings("all")

public class Prueba extends javax.swing.JFrame {

	public Prueba() throws IOException {

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

		setResizable(false);

		ArrayList<String> cabecera = new ArrayList<>();

		cabecera.add("AAAAAAAAAA");

		cabecera.add("AAAAAAAAAA");

		cabecera.add("AAAAAAAAAA");

		ArrayList<String> datos = new ArrayList<>();

		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("111111111111");

		datos.add("2222222");

		datos.add("3333333");
		datos.add("AAAAAAAAAAAAA");

		datos.add("BBBBBBBBBBBBBB");

		datos.add("CCCCCCCCCC");

		// Anterior

		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		MaterialTable panel = new MaterialTable(cabecera, datos, 2, 2);

		getContentPane().add(panel);

		setSize(new Dimension(700, 700));

		setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent arg0) {

	}

	public void stateChanged(ChangeEvent e) {

	}

}
