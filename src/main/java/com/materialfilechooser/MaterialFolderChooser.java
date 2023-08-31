package com.materialfilechooser;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.buttons.round.RoundedButton;
import com.comboBox.comboSuggestion.ComboBoxSuggestion;
import com.materialfilechooser.buttons.CarpetaPadre;
import com.materialfilechooser.buttons.Go;
import com.materialfilechooser.buttons.Home;
import com.materialfilechooser.buttons.NewFolder;
import com.textField.simple.TextField;

import scrollbar.MaterialPanelDeslizante;

@SuppressWarnings("serial")

class MaterialFolderChooser extends JPanel {

	private static ButtonScrollablePanel buttonScrollablePanel;

	private MaterialPanelDeslizante panel_5;

	private static ComboBoxSuggestion<String> tipo;

	public static TextField path;

	private JLabel labelTipo;

	private void reset() {

		try {

			path.setText(path.getText().trim());

			path.setText(path.getText().replace("   ", "  "));

			path.setText(path.getText().replace("  ", " "));

			if (path.getText().equals("")) {

				path.setText(System.getProperty("user.home") + buttonScrollablePanel.saberSeparador());

			}

		}

		catch (Exception e) {

		}

	}

	static void irAPath() {

		if (buttonScrollablePanel != null) {

			String ext = "all";

			if (tipo.getSelectedItem() != null) {

				if (tipo.getSelectedItem().toString().equals("All (*.*)")) {

					ext = "all";

				}

				else {

					ext = tipo.getSelectedItem().toString();

				}

			}

			buttonScrollablePanel.removeAll();

			buttonScrollablePanel.revalidate();

			buttonScrollablePanel.repaint();

			buttonScrollablePanel.listarItems(path.getText(), ext, true);

		}

	}

	public MaterialFolderChooser(ThreadDialog dialogo, String[] filter, boolean all) {

		setBackground(Color.WHITE);

		setLayout(null);

		path = new TextField();
		path.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(final FocusEvent evt) {

				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						System.out.println("bbb");
						reset();
					}
				});
			}
		});

		path.addInputMethodListener(new InputMethodListener() {

			public void inputMethodTextChanged(InputMethodEvent event) {
				System.out.println("aaaaa");
				reset();

			}

			@Override
			public void caretPositionChanged(InputMethodEvent event) {

			}

		});

		path.addKeyListener(new KeyAdapter() {

			@Override

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					buttonScrollablePanel.setRutaParaVer(path.getText());

					irAPath();

				}

			}

			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == 8) {

					reset();

				}

			}

		});

		JPanel panel_1 = new JPanel();

		panel_1.setBackground(Color.WHITE);

		panel_1.setBounds(0, 356, 620, 157);

		add(panel_1);

		panel_1.setLayout(null);

		TextField panel_4 = new TextField();

		panel_4.setShadowColor(Color.LIGHT_GRAY);

		panel_4.setBackground(new Color(231, 231, 231));

		panel_4.setFont(new Font("Dialog", Font.PLAIN, 16));

		panel_4.setBounds(65, 0, 543, 48);

		panel_1.add(panel_4);

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

		tipo.setBounds(65, 54, 543, 40);

		tipo.addItem("All (*.*)");

		panel_1.add(tipo);

		RoundedButton btnNewButton_3 = new RoundedButton("Select");

		btnNewButton_3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				LinkedList<String> test = new LinkedList<String>();

				test.add("archivo seleccionado");

				dialogo.setLista(test);

				dialogo.dispose();

			}

		});

		btnNewButton_3.setFont(new Font("Dialog", Font.PLAIN, 16));

		btnNewButton_3.setText("Open");

		btnNewButton_3.setIcon(null);

		btnNewButton_3.setBounds(374, 106, 109, 40);

		panel_1.add(btnNewButton_3);

		RoundedButton btnNewButton_4 = new RoundedButton("Cancel");

		btnNewButton_4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				dialogo.dispose();

			}

		});

		btnNewButton_4.setFont(new Font("Dialog", Font.PLAIN, 16));

		btnNewButton_4.setBounds(491, 106, 117, 40);

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

		panel_3.setBounds(0, 0, 620, 59);

		add(panel_3);

		panel_3.setLayout(null);

		CarpetaPadre carpetaPadre = new CarpetaPadre(Color.decode("#AAAAAA"), null, null);

		carpetaPadre.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					String pth = path.getText();

					if (!pth.endsWith(buttonScrollablePanel.saberSeparador())) {

						pth += buttonScrollablePanel.saberSeparador();

					}

					pth = path.getText().substring(0,
							path.getText().lastIndexOf(buttonScrollablePanel.saberSeparador()));

					if (!buttonScrollablePanel.isClick3veces()) {

						if (pth.equals("") && !pth.equals(buttonScrollablePanel.saberSeparador())) {

							pth = buttonScrollablePanel.saberSeparador();

						}

					}

					else if (pth.equals("")) {

						pth = buttonScrollablePanel.saberSeparador();

					}

					if (path.getText().equals(pth + buttonScrollablePanel.saberSeparador())) {

						pth = pth.substring(0, pth.lastIndexOf(buttonScrollablePanel.saberSeparador()));

					}
					if (!pth.equals(buttonScrollablePanel.saberSeparador())
							&& !pth.endsWith(buttonScrollablePanel.saberSeparador())) {

						pth += buttonScrollablePanel.saberSeparador();

					}
					buttonScrollablePanel.setRutaParaVer(pth);

					path.setText(pth);

					irAPath();

				}

				catch (Exception e1) {

				}

			}

		});

		carpetaPadre.setBounds(436, 8, 39, 39);

		panel_3.add(carpetaPadre);

		Home btnNewButton_1 = new Home(Color.decode("#AAAAAA"), null);

		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				path.setText(System.getProperty("user.home") + buttonScrollablePanel.saberSeparador());

				buttonScrollablePanel.setRutaParaVer(path.getText());

				irAPath();

			}

		});

		btnNewButton_1.setBounds(499, 8, 39, 39);

		panel_3.add(btnNewButton_1);

		NewFolder btnNewButton_2 = new NewFolder(Color.decode("#AAAAAA"), null, null);

		btnNewButton_2.setBounds(558, 8, 39, 39);

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

		buttonScrollablePanel = new ButtonScrollablePanel(path.getText(), tipo.getSelectedItem().toString(), false);

		buttonScrollablePanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				System.out.println("Test " + e.getClickCount());

				if (e.getClickCount() % 2 != 0) {

					if (new File(path.getText()).exists() && !((path.getText() + buttonScrollablePanel.saberSeparador())
							.equals(buttonScrollablePanel.getRutaParaVer()))) {

						buttonScrollablePanel.setRutaParaVer(path.getText());

						if (!buttonScrollablePanel.getRutaParaVer().endsWith(buttonScrollablePanel.saberSeparador())) {

							buttonScrollablePanel.setRutaParaVer(
									buttonScrollablePanel.getRutaParaVer() + buttonScrollablePanel.saberSeparador());

						}
						System.out.println(path.getText());
						irAPath();

					}

				}

			}

		});

		buttonScrollablePanel.setBackground(Color.WHITE);

		buttonScrollablePanel.setBorder(new EmptyBorder(0, 0, 0, 0));

		buttonScrollablePanel.setSize(600, 600);

		panel_5 = new MaterialPanelDeslizante(buttonScrollablePanel, null, null);

		panel_5.setBounds(0, 64, 620, 280);

		add(panel_5);

		JPanel panel = new JPanel();

		panel.setBackground(Color.WHITE);

		panel.setBounds(0, 58, 620, 10);

		add(panel);

		irAPath();

	}

}
