package com.materialfilechooser;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.buttons.simple.SimpleButton;
import com.comboBox.comboSuggestion.ComboBoxSuggestion;
import com.materialfilechooser.buttons.CarpetaPadre;
import com.materialfilechooser.buttons.Go;
import com.materialfilechooser.buttons.Home;
import com.materialfilechooser.buttons.NewFolder;
import com.scrollbar.MaterialPanelDeslizante;
import com.search.SearchInput;
import com.textField.text.TextFieldSuggestion;

import mthos.JMthos;

@SuppressWarnings("serial")

class MaterialFileChooser extends JPanel {

	private static ButtonScrollablePanel buttonScrollablePanel;

	private MaterialPanelDeslizante panel_5;

	public static TextFieldSuggestion path;

	public static LinkedList<String> test;

	private static ComboBoxSuggestion<String> tipo;

	private static SearchInput search;

	private String folder;

	static void irAPath(String folder) {

		if (buttonScrollablePanel != null) {

			buttonScrollablePanel.setRutaParaVer(path.getText());

			String ext = "all";

			buttonScrollablePanel.removeAll();

			buttonScrollablePanel.revalidate();

			buttonScrollablePanel.repaint();

			saberBuscador(tipo.getSelectedItem().toString());

			if (path.getText().isEmpty()) {

				path.setText(folder);

			}

			if (tipo.getSelectedItem() != null && !tipo.getSelectedItem().toString().equals("All (*.*)")) {

				ext = tipo.getSelectedItem().toString();

			}

			buttonScrollablePanel.listarItems(path.getText(), ext, false);

		}

	}

	public void setOriginPath(String folder) {

		try {

			buttonScrollablePanel = new ButtonScrollablePanel(folder, search, path.getText(),
					tipo.getSelectedItem().toString(), false);

		}

		catch (Exception e) {

		}

	}

	public MaterialFileChooser(String originPath, JFrame frame, final ThreadDialog dialogo, String[] filter,
			boolean all, String title, boolean input) {

		setBackground(Color.WHITE);

		setLayout(null);

		if (originPath == null || originPath.isEmpty()) {

			folder = System.getProperty("user.home");

		}

		else {

			folder = originPath;

		}

		test = new LinkedList<String>();

		final VentanaNuevoFile menu = new VentanaNuevoFile(frame, "New Folder", folder, 470, 120);

		path = new TextFieldSuggestion();

		path.addKeyListener(new KeyAdapter() {

			@Override

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					irAPath(folder);

				}

			}

		});

		JPanel panel_1 = new JPanel();

		panel_1.setBackground(Color.WHITE);

		panel_1.setBounds(0, 354, 735, 159);

		add(panel_1);

		panel_1.setLayout(null);

		search = new SearchInput(JMthos.listar(folder, "all", true, false));

		search.addKeyListener(new KeyAdapter() {

			@Override

			public void keyPressed(KeyEvent e) {

				if (!search.getText().isEmpty() && e.getKeyCode() == KeyEvent.VK_ENTER) {

					path.setText(JMthos.ponerSeparador(path.getText()) + search.getText());

				}

			}

		});

		search.setBackground(Color.WHITE);

		search.setFont(new Font("Dialog", Font.PLAIN, 20));

		search.setBounds(65, 0, 670, 48);

		panel_1.add(search);

		JLabel labelTipo = new JLabel("Type");

		labelTipo.setHorizontalAlignment(SwingConstants.CENTER);

		labelTipo.setFont(new Font("Dialog", Font.PLAIN, 16));

		labelTipo.setBounds(0, 46, 60, 62);

		panel_1.add(labelTipo);

		JLabel lblNewLabel_1 = new JLabel("Name");

		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 16));

		lblNewLabel_1.setBounds(0, 0, 60, 48);

		panel_1.add(lblNewLabel_1);

		tipo = new ComboBoxSuggestion<String>();

		tipo.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				try {

					if (path != null) {

						if (buttonScrollablePanel != null) {

							buttonScrollablePanel.setRutaParaVer(path.getText());

						}

						irAPath(folder);

						test.clear();

					}

				}

				catch (Exception e1) {

				}

			}

		});

		tipo.setFont(new Font("Dialog", Font.BOLD, 16));

		tipo.setBackground(Color.WHITE);

		tipo.setBounds(65, 58, 670, 40);

		panel_1.add(tipo);

		SimpleButton btnNewButton_3 = new SimpleButton("Open");

		btnNewButton_3.setText("Select");

		btnNewButton_3.setBorderColor(Color.BLACK);

		btnNewButton_3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (input) {

					dialogo.setLista2(test.stream().filter(path -> !Files.isDirectory(Paths.get(path)))
							.collect(Collectors.toCollection(LinkedList::new)));

				}

				else {

					dialogo.setLista(test.stream().filter(path -> !Files.isDirectory(Paths.get(path)))
							.collect(Collectors.toCollection(LinkedList::new)));

				}

				dialogo.dispose();

			}

		});

		btnNewButton_3.setFont(new Font("Dialog", Font.PLAIN, 20));

		btnNewButton_3.setBounds(480, 106, 109, 40);

		panel_1.add(btnNewButton_3);

		SimpleButton btnNewButton_4 = new SimpleButton("Cancel");

		btnNewButton_4.setBorderColor(Color.BLACK);

		btnNewButton_4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				test.clear();

				dialogo.dispose();

			}

		});

		btnNewButton_4.setFont(new Font("Dialog", Font.PLAIN, 20));

		btnNewButton_4.setBounds(600, 106, 117, 40);

		panel_1.add(btnNewButton_4);

		if (filter != null && filter.length > 0) {

			String extension = "";

			for (int i = 0; i < filter.length; i++) {

				extension = filter[i];

				extension = extension.replace("*", "");

				extension = extension.replace(".", "");

				extension = extension.trim();

				extension = extension.replace("   ", "  ");

				extension = extension.replace("  ", " ");

				extension = extension.replace(" ", "");

				tipo.addItem(extension);

			}

		}

		if ((filter == null || filter.length == 0) || all) {

			tipo.addItem("All (*.*)");

		}

		JPanel panel_3 = new JPanel();

		panel_3.setBackground(Color.WHITE);

		panel_3.setBounds(0, 0, 735, 33);

		add(panel_3);

		panel_3.setLayout(null);

		CarpetaPadre carpetaPadre = new CarpetaPadre(Color.decode("#AAAAAA"), null, null);

		carpetaPadre.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					String pth = JMthos.ponerSeparador(path.getText());

					pth = path.getText().substring(0, path.getText().lastIndexOf(JMthos.saberSeparador()));

					if (!buttonScrollablePanel.isClick3veces()) {

						if (pth.equals("") && !pth.equals(JMthos.saberSeparador())) {

							pth = JMthos.saberSeparador();

						}

					}

					else if (pth.equals("")) {

						pth = JMthos.saberSeparador();

					}

					if (path.getText().equals(pth + JMthos.saberSeparador())) {

						pth = pth.substring(0, pth.lastIndexOf(JMthos.saberSeparador()));

					}

					buttonScrollablePanel.setRutaParaVer(pth);

					path.setText(pth);

					irAPath(folder);

				}

				catch (Exception e1) {

				}

			}

		});

		carpetaPadre.setBounds(400, 0, 39, 29);

		panel_3.add(carpetaPadre);

		Home btnNewButton_1 = new Home(Color.decode("#AAAAAA"), null);

		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				path.setText(folder);

				buttonScrollablePanel.setRutaParaVer(path.getText());

				irAPath(folder);

			}

		});

		btnNewButton_1.setBounds(449, 0, 39, 29);

		panel_3.add(btnNewButton_1);

		NewFolder btnNewButton_2 = new NewFolder(Color.decode("#AAAAAA"), null, null);

		btnNewButton_2.setBounds(498, 0, 39, 29);

		btnNewButton_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String carpeta = path.getText();

				if (!carpeta.endsWith(JMthos.saberSeparador())) {

					carpeta += JMthos.saberSeparador();

				}

				menu.setPath(carpeta);

				menu.createAndShowDialog();

				irAPath(folder);

			}

		});

		panel_3.add(btnNewButton_2);

		path.setFont(new Font("Dialog", Font.PLAIN, 16));

		path.setText(folder);

		path.setBackground(Color.decode("#E7E7E7"));

		path.setBounds(0, -12, 345, 59);

		panel_3.add(path);

		Go ir = new Go(new Color(170, 170, 170), null);

		ir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				irAPath(folder);

			}

		});

		ir.setBounds(359, 0, 27, 29);

		panel_3.add(ir);

		SimpleButton btnNewButton_3_1 = new SimpleButton("All");

		btnNewButton_3_1.setBorderColor(Color.BLACK);

		btnNewButton_3_1.setForeground(Color.BLACK);

		btnNewButton_3_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String filtro = tipo.getSelectedItem().toString();

				if (filtro.contains(",")) {

					String[] filtros = filtro.split(",");

					LinkedList<String> lista2 = new LinkedList<>();

					test = new LinkedList<String>();

					for (int i = 0; i < filtros.length; i++) {

						if (i == 0) {

							test = (LinkedList<String>) JMthos.listar(path.getText(), filtros[i], false, true);

						}

						else {

							lista2 = (LinkedList<String>) JMthos.listar(path.getText(), filtros[i], false, true);

							for (int y = 0; y < lista2.size(); y++) {

								if (new File(lista2.get(y)).isFile()) {

									test.add(lista2.get(y));

								}

							}

						}

					}

				}

				else {

					if (filtro.equals("All (*.*)")) {

						filtro = "all";

					}

					test = (LinkedList<String>) JMthos.listar(path.getText(), filtro, false, true);

				}

				int contador = 0;

				for (int i = 0; i < test.size(); i++) {

					if (new File(path.getText() + test.get(i)).isFile()) {

						test.set(contador, path.getText() + test.get(i));

						contador++;

					}

				}

			}

		});

		btnNewButton_3_1.setFont(new Font("Dialog", Font.PLAIN, 20));

		btnNewButton_3_1.setBounds(560, 4, 66, 25);

		panel_3.add(btnNewButton_3_1);

		SimpleButton btnNewButton_3_1_1 = new SimpleButton("Clear");

		btnNewButton_3_1_1.setBorderColor(Color.BLACK);

		btnNewButton_3_1_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				test.clear();

			}

		});

		btnNewButton_3_1_1.setFont(new Font("Dialog", Font.PLAIN, 20));

		btnNewButton_3_1_1.setBounds(639, 4, 86, 25);

		panel_3.add(btnNewButton_3_1_1);

		buttonScrollablePanel = new ButtonScrollablePanel(folder, search, path.getText(),
				tipo.getSelectedItem().toString(), false);

		buttonScrollablePanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				if (e.getClickCount() == 1
						&& ((new File(path.getText()).exists() && !((path.getText() + JMthos.saberSeparador())
								.equals(buttonScrollablePanel.getRutaParaVer()))))) {

					buttonScrollablePanel.setRutaParaVer(path.getText());

					if (!buttonScrollablePanel.getRutaParaVer().endsWith(JMthos.saberSeparador())) {

						buttonScrollablePanel
								.setRutaParaVer(buttonScrollablePanel.getRutaParaVer() + JMthos.saberSeparador());

					}

					irAPath(folder);

				}

			}

		});

		buttonScrollablePanel.setBackground(Color.WHITE);

		buttonScrollablePanel.setBorder(new EmptyBorder(0, 0, 0, 0));

		buttonScrollablePanel.setSize(600, 600);

		panel_5 = new MaterialPanelDeslizante(buttonScrollablePanel, null, null, Color.WHITE, 40);

		panel_5.setBounds(0, 32, 735, 322);

		add(panel_5);

		irAPath(folder);

	}

	private static void saberBuscador(String filtro) {

		try {

			LinkedList<String> lista = (LinkedList<String>) JMthos.listar(path.getText(), "all", true, false);

			String busqueda = filtro;

			LinkedList<String> test2;

			if (filtro.contains(",")) {

				String[] filtros = filtro.split(",");

				for (int i = 0; i < filtros.length; i++) {

					test2 = (LinkedList<String>) JMthos.listar(path.getText(), filtros[i], false, false);

					for (int y = 0; y < test2.size(); y++) {

						lista.add(test2.get(y));

					}

				}

			}

			else {

				if (filtro.contains("All")) {

					busqueda = "all";

				}

				test2 = (LinkedList<String>) JMthos.listar(path.getText(), busqueda, false, false);

				for (int i = 0; i < test2.size(); i++) {

					lista.add(test2.get(i));

				}

			}

			Collections.sort(lista);

			search.setDataTesting(lista);

		}

		catch (Exception e) {

		}

	}

}
