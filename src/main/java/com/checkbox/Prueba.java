package com.checkbox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;

import com.panels.round.MaterialPanel;
import com.textField.text.MaterialTextField;

import mthos.JMthos;

@SuppressWarnings("all")

public class Prueba extends javax.swing.JFrame {

	public int suma(int num1, int num2) {

		return num1 + num2;

	}

	public Prueba() throws IOException {

		setTitle("");

		initComponents();

		setVisible(true);

	}

	public static void main(String[] args) {

		try {

			LinkedList<String> lista = (LinkedList<String>) JMthos.listar("/home/linux/Movil/0_Archivos/00/", "jpg",
					false, true);

			File file;

			String salida = "";

			String salida2 = "";

			for (String texto : lista) {

				salida = texto.substring(0, texto.lastIndexOf("/") + 1);

				salida2 = texto.substring(texto.lastIndexOf("/") + 2, texto.length());

				file = new File(texto);

				file.renameTo(new File(salida + salida2));

			}

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void initComponents() throws IOException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		setResizable(false);

		MaterialTextField panel = new MaterialTextField();

		panel.setHorizontalAlignment(SwingConstants.CENTER);

		MaterialPanel panel_1 = new MaterialPanel();
		panel_1.setForeground(Color.GREEN);
		panel_1.setElevation(4);
		panel_1.setBackground(Color.RED);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE).addGap(59))
						.addGroup(layout.createSequentialGroup()
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 457, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(59, Short.MAX_VALUE)))));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(113, Short.MAX_VALUE)));

		getContentPane().setLayout(layout);

		setSize(new Dimension(532, 433));

		setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent arg0) {

	}

	public void stateChanged(ChangeEvent e) {

	}
}
