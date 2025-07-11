package com.dialog.popup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Test {

	public static void main(String[] args) {

		// Frame base para la ventana emergente
		JFrame frame = new JFrame("Demo Principal");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);

		JButton abrirDialogo = new JButton("Seleccionar Item");
		abrirDialogo.addActionListener(e -> mostrarVentanaSeleccion(frame));

		frame.getContentPane().add(abrirDialogo);
		frame.setVisible(true);
	}

	private static void mostrarVentanaSeleccion(JFrame frame) {
		String[] items = { "Opción 1", "Opción 2", "Opción 3", "Opción 4" };

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		JLabel titulo = new JLabel("Selecciona un ítem:");
		titulo.setFont(new Font("Arial", Font.BOLD, 18));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(titulo, BorderLayout.NORTH);

		JList<String> lista = new JList<>(items);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.setFont(new Font("Arial", Font.PLAIN, 16));
		JScrollPane scroll = new JScrollPane(lista);
		scroll.setPreferredSize(new Dimension(250, 120));
		panel.add(scroll, BorderLayout.CENTER);

		JPanel botones = new JPanel();
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		botones.add(aceptar);
		botones.add(cancelar);
		panel.add(botones, BorderLayout.SOUTH);

		aceptar.addActionListener(e -> {
			String seleccion = lista.getSelectedValue();
			if (seleccion != null) {
				JOptionPane.showMessageDialog(frame, "Seleccionaste: " + seleccion);
				Window window = SwingUtilities.getWindowAncestor(panel);
				if (window != null)
					window.dispose();
			}
		});

		cancelar.addActionListener(e -> {
			Window window = SwingUtilities.getWindowAncestor(panel);
			if (window != null)
				window.dispose();
		});

		// Crear ventana emergente personalizada
		new VentanaEmergente(frame, panel, "Lista de Selección", 300, 250, false, // diseño personalizado
				30, // ángulo de curvatura
				Color.GRAY, // color del borde
				3, // grosor del borde
				new ImageIcon() // icono (opcional)
		);
	}
}
