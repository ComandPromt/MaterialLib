package com.materialfilechooser;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.materialfilechooser.icons.Folder;

public class Item extends JPanel {

	private JCheckBox chckbxNewCheckBox;

	private JLabel lblNewLabel;

	private JLabel lblNewLabel_1;

	public Item(boolean carpeta, String text) {

		lblNewLabel = new JLabel("");

		lblNewLabel_1 = new JLabel("");

		setBackground(Color.WHITE);

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				lblNewLabel_1.setBounds(85, 0, getWidth() - 75, 40);

				lblNewLabel_1.setFont(getFont().deriveFont(26f));

				lblNewLabel_1
						.setText(text.substring(0, 1).toUpperCase() + text.substring(1, text.length()).toLowerCase());

			}

		});

		if (carpeta) {

			lblNewLabel = new Folder(null, null);

		}

		setLayout(null);

		chckbxNewCheckBox = new JCheckBox("");

		chckbxNewCheckBox.setBounds(0, 0, 30, 40);

		add(chckbxNewCheckBox);

		lblNewLabel.setBounds(30, 0, 40, 40);

		lblNewLabel.setSize(40, 40);

		add(lblNewLabel);

		add(lblNewLabel_1);

	}
}
