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
import java.util.LinkedList;

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

class MaterialFolderChooser extends JPanel {

	private static ButtonScrollablePanel buttonScrollablePanel;

	private MaterialPanelDeslizante panel_5;

	private static ComboBoxSuggestion<String> tipo;

	public static TextFieldSuggestion path;

	private JLabel labelTipo;

	public static LinkedList<String> test;

	private static SearchInput search;

	private String folder;

	private static void saberBuscador() {

		try {

			search.setDataTesting((LinkedList<String>) JMthos.listar(path.getText(), "all", true, false));

		}

		catch (Exception e) {

		}

	}

	static void irAPath() {

		if (buttonScrollablePanel != null) {

			buttonScrollablePanel.setRutaParaVer(path.getText());

			buttonScrollablePanel.removeAll();

			buttonScrollablePanel.revalidate();

			buttonScrollablePanel.repaint();

			saberBuscador();

			buttonScrollablePanel.listarItems(path.getText(), "all", true);

		}

	}

	public MaterialFolderChooser(String originPath, JFrame frame, ThreadDialog dialogo, String title, boolean input) {

		setBackground(Color.WHITE);

		setLayout(null);

		if (originPath == null || originPath.isEmpty()) {

			folder = System.getProperty("user.home");

		}

		else {

			folder = originPath;

		}

		test = new LinkedList<String>();

		VentanaNuevoFile menu = new VentanaNuevoFile(frame, "New Folder", folder, 470, 120);

		path = new TextFieldSuggestion();

		path.addKeyListener(new KeyAdapter() {

			@Override

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					irAPath();

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

		tipo = new ComboBoxSuggestion<>();

		tipo.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				if (path != null) {

					if (buttonScrollablePanel != null) {

						buttonScrollablePanel.setRutaParaVer(path.getText());

					}

					irAPath();

				}

			}

		});

		tipo.setBackground(Color.WHITE);

		tipo.setFont(new Font("Dialog", Font.BOLD, 16));

		tipo.setBounds(65, 58, 670, 40);

		tipo.addItem("All (*.*)");

		panel_1.add(tipo);

		SimpleButton btnNewButton_3 = new SimpleButton("Select");

		btnNewButton_3.setBorderColor(Color.BLACK);

		btnNewButton_3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				seleccionar(dialogo, input);

			}

			private void seleccionar(ThreadDialog dialogo, boolean input) {
				if (input) {

					dialogo.setLista2(test);

				}

				else {

					dialogo.setLista(test);

				}

				dialogo.dispose();
			}

		});

		btnNewButton_3.setFont(new Font("Dialog", Font.PLAIN, 20));

		btnNewButton_3.setIcon(null);

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

		JLabel lblNewLabel_1 = new JLabel("Name");

		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 16));

		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		lblNewLabel_1.setBounds(0, 0, 60, 48);

		panel_1.add(lblNewLabel_1);

		labelTipo = new JLabel("Type");

		labelTipo.setHorizontalAlignment(SwingConstants.CENTER);

		labelTipo.setFont(new Font("Dialog", Font.PLAIN, 16));

		labelTipo.setBounds(0, 46, 60, 62);

		panel_1.add(labelTipo);

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

					irAPath();

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

				buttonScrollablePanel.setRutaParaVer(folder);

				irAPath();

			}

		});

		btnNewButton_1.setBounds(449, 0, 39, 29);

		panel_3.add(btnNewButton_1);

		NewFolder btnNewButton_2 = new NewFolder(Color.decode("#AAAAAA"), null, null);

		btnNewButton_2.setBounds(498, 0, 39, 29);

		btnNewButton_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String carpeta = JMthos.ponerSeparador(path.getText());

				menu.setPath(carpeta);

				menu.createAndShowDialog();

				irAPath();

			}

		});

		panel_3.add(btnNewButton_2);

		path.setFont(new Font("Dialog", Font.PLAIN, 16));

		path.setText(folder);

		path.setBackground(Color.decode("#E7E7E7"));

		path.setBounds(0, -12, 345, 59);

		panel_3.add(path);

		Go btnNewButton_6 = new Go(new Color(170, 170, 170), null);

		btnNewButton_6.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				irAPath();

			}

		});

		btnNewButton_6.setBounds(359, 0, 27, 29);

		panel_3.add(btnNewButton_6);

		SimpleButton btnNewButton_3_1 = new SimpleButton("All");

		btnNewButton_3_1.setBorderColor(Color.BLACK);

		btnNewButton_3_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String folder = JMthos.ponerSeparador(path.getText());

				test = (LinkedList<String>) JMthos.listar(folder, "all", true, true);

				for (int i = 0; i < test.size(); i++) {

					test.set(i, folder + test.get(i) + JMthos.saberSeparador());

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
				tipo.getSelectedItem().toString(), true);

		buttonScrollablePanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				if (e.getClickCount() == 1
						&& ((new File(path.getText()).exists() && !((path.getText() + JMthos.saberSeparador())
								.equals(buttonScrollablePanel.getRutaParaVer()))))) {

					buttonScrollablePanel.setRutaParaVer(JMthos.ponerSeparador(path.getText()));

					irAPath();

				}

			}

		});

		buttonScrollablePanel.setBackground(Color.WHITE);

		buttonScrollablePanel.setBorder(new EmptyBorder(0, 0, 0, 0));

		buttonScrollablePanel.setSize(600, 600);

		panel_5 = new MaterialPanelDeslizante(buttonScrollablePanel, null, null);

		panel_5.setBounds(0, 32, 735, 322);

		add(panel_5);

		irAPath();

	}
}
