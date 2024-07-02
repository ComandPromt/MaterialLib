package com.materialfilechooser;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JPanel;

import com.buttons.round.NButton;
import com.textField.text.NTextField;

import mthos.JMthos;

@SuppressWarnings("serial")
class NuevaCarpeta extends JPanel {

	private NTextField textField;

	private JDialog menu;

	String folder;

	private NButton btnNewButton;

	public void setFolder(String folder) {

		if (!this.folder.equals(folder)) {

			this.folder = folder;

			btnNewButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					try {

						new File(folder + textField.getText()).mkdir();

						menu.dispose();

					}

					catch (Exception e1) {

					}

				}

			});

		}

	}

	private void crearCarpeta(String folder) {

		try {

			if (!folder.endsWith(JMthos.saberSeparador())) {

				folder += JMthos.saberSeparador();

			}

			File carpeta = new File(folder + textField.getText());

			if (!carpeta.exists()) {

				carpeta.mkdir();

			}

			menu.dispose();

		}

		catch (Exception e1) {

		}

	}

	public NuevaCarpeta(String folder) {

		setBackground(Color.WHITE);

		setLayout(null);

		this.folder = folder;

		textField = new NTextField();

		textField.setForeground(Color.BLACK);

		textField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					crearCarpeta(folder);

				}

			}

		});

		textField.setFont(getFont().deriveFont(16f));

		textField.setBackground(Color.decode("#EEEEEE"));

		textField.setBounds(12, 12, 362, 64);

		textField.setBackground(Color.WHITE);

		add(textField);

		textField.setColumns(10);

		btnNewButton = new NButton("Go");

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				crearCarpeta(folder);

			}

		});

		btnNewButton.setBounds(386, 9, 64, 64);

		btnNewButton.setBackground(Color.decode("#ECECEC"));

		btnNewButton.setFont(getFont().deriveFont(16f));

		add(btnNewButton);

	}

	public void setPopup(JDialog popupDialog) {

		menu = popupDialog;

	}

}
