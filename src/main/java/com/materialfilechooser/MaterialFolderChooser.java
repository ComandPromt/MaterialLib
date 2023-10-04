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

import com.buttons.round.RoundedButton;
import com.comboBox.comboSuggestion.ComboBoxSuggestion;
import com.materialfilechooser.buttons.CarpetaPadre;
import com.materialfilechooser.buttons.Go;
import com.materialfilechooser.buttons.Home;
import com.materialfilechooser.buttons.NewFolder;
import com.scrollbar.MaterialPanelDeslizante;
import com.search.SearchInput;
import com.textField.text.MaterialTextField;

import mthos.JMthos;

@SuppressWarnings("serial")

class MaterialFolderChooser extends JPanel {

	private static ButtonScrollablePanel buttonScrollablePanel;

	private MaterialPanelDeslizante panel_5;

	private static ComboBoxSuggestion<String> tipo;

	public static MaterialTextField path;

	private JLabel labelTipo;

	public static LinkedList<String> test;

	private String folder;

	private static SearchInput search;

	private static void saberBuscador() {

		try {

			search.setDataTesting((LinkedList<String>) JMthos.listar(path.getText(), "all", true, true));

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

	public MaterialFolderChooser(JFrame frame, ThreadDialog dialogo, String title) {

		setBackground(Color.WHITE);

		setLayout(null);

		test = new LinkedList<String>();

		VentanaNuevoFile menu = new VentanaNuevoFile(frame, "New Folder", System.getProperty("user.home"), 470, 120);

		path = new MaterialTextField();

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

		panel_1.setBounds(0, 356, 735, 157);

		add(panel_1);

		panel_1.setLayout(null);

		search = new SearchInput(JMthos.listar(System.getProperty("user.home"), "all", true, true));

		search.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (!search.getText().isEmpty() && e.getKeyCode() == KeyEvent.VK_ENTER) {

					folder = JMthos.ponerSeparador(path.getText());

					path.setText(folder + search.getText());

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

		RoundedButton btnNewButton_3 = new RoundedButton("Select");

		btnNewButton_3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				dialogo.setLista(test);

				dialogo.dispose();

			}

		});

		btnNewButton_3.setFont(new Font("Dialog", Font.PLAIN, 20));

		btnNewButton_3.setText("Open");

		btnNewButton_3.setIcon(null);

		btnNewButton_3.setBounds(410, 106, 109, 40);

		panel_1.add(btnNewButton_3);

		RoundedButton btnNewButton_4 = new RoundedButton("Cancel");

		btnNewButton_4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				test.clear();

				dialogo.dispose();

			}

		});

		btnNewButton_4.setFont(new Font("Dialog", Font.PLAIN, 20));

		btnNewButton_4.setBounds(549, 106, 117, 40);

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

		panel_3.setBounds(0, 0, 735, 59);

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

		carpetaPadre.setBounds(403, 8, 39, 39);

		panel_3.add(carpetaPadre);

		Home btnNewButton_1 = new Home(Color.decode("#AAAAAA"), null);

		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				path.setText(System.getProperty("user.home"));

				buttonScrollablePanel.setRutaParaVer(path.getText());

				irAPath();

			}

		});

		btnNewButton_1.setBounds(454, 8, 39, 39);

		panel_3.add(btnNewButton_1);

		NewFolder btnNewButton_2 = new NewFolder(Color.decode("#AAAAAA"), null, null);

		btnNewButton_2.setBounds(505, 8, 39, 39);

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

		path.setText(System.getProperty("user.home"));

		path.setBackground(Color.decode("#E7E7E7"));

		path.setBounds(0, 0, 345, 59);

		panel_3.add(path);

		Go btnNewButton_6 = new Go(new Color(170, 170, 170), null);

		btnNewButton_6.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				irAPath();

			}

		});

		btnNewButton_6.setBounds(357, 8, 27, 39);

		panel_3.add(btnNewButton_6);

		RoundedButton btnNewButton_3_1 = new RoundedButton("Select");

		btnNewButton_3_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				folder = JMthos.ponerSeparador(path.getText());

				test = (LinkedList<String>) JMthos.listar(folder, "all", true, true);

				for (int i = 0; i < test.size(); i++) {

					test.set(i, folder + test.get(i) + JMthos.saberSeparador());

				}

			}

		});

		btnNewButton_3_1.setText("All");

		btnNewButton_3_1.setFont(new Font("Dialog", Font.PLAIN, 20));

		btnNewButton_3_1.setBounds(556, 8, 69, 40);

		panel_3.add(btnNewButton_3_1);

		RoundedButton btnNewButton_3_1_1 = new RoundedButton("Clear");

		btnNewButton_3_1_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				test.clear();

			}

		});

		btnNewButton_3_1_1.setFont(new Font("Dialog", Font.PLAIN, 20));

		btnNewButton_3_1_1.setBounds(637, 8, 86, 40);

		panel_3.add(btnNewButton_3_1_1);

		buttonScrollablePanel = new ButtonScrollablePanel(search, path.getText(), tipo.getSelectedItem().toString(),
				true);

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

		panel_5.setBounds(0, 64, 735, 280);

		add(panel_5);

		JPanel panel = new JPanel();

		panel.setBackground(Color.WHITE);

		panel.setBounds(0, 58, 735, 10);

		add(panel);

		irAPath();

	}
}
