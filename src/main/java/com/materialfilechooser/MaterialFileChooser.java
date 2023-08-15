package com.materialfilechooser;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.buttons.round.RoundedButton;
import com.comboBox.comboSuggestion.ComboBoxSuggestion;
import com.textField.simple.TextField;

@SuppressWarnings("serial")

public class MaterialFileChooser extends JPanel {

	public MaterialFileChooser() {

		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);

		panel.setBounds(0, 0, 450, 39);

		add(panel);
		panel.setLayout(null);

		JButton btnNewButton_5 = new JButton("X");
		btnNewButton_5.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnNewButton_5.setBackground(Color.WHITE);

		btnNewButton_5.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				removeAll();

			}

		});

		btnNewButton_5.setBounds(407, 0, 43, 39);

		panel.add(btnNewButton_5);

		JPanel panel_1 = new JPanel();

		panel_1.setBounds(0, 274, 450, 145);

		add(panel_1);

		panel_1.setLayout(null);

		TextField panel_4 = new TextField();
		panel_4.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_4.setBounds(65, 0, 309, 48);
		panel_1.add(panel_4);

		ComboBoxSuggestion comboBox = new ComboBoxSuggestion();
		comboBox.setFont(new Font("Dialog", Font.BOLD, 16));
		comboBox.setBounds(65, 60, 309, 34);
		panel_1.add(comboBox);

		RoundedButton btnNewButton_3 = new RoundedButton("Select");
		btnNewButton_3.setIcon(new ImageIcon(MaterialFileChooser.class.getResource("/com/nativeChooser/folder.png")));
		btnNewButton_3.setBounds(65, 108, 109, 25);
		panel_1.add(btnNewButton_3);

		RoundedButton btnNewButton_4 = new RoundedButton("Cancel");
		btnNewButton_4.setBounds(257, 108, 117, 25);
		panel_1.add(btnNewButton_4);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 38, 450, 39);
		add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 12, 70, 15);
		panel_3.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(83, 12, 161, 15);
		panel_3.add(panel_2);

		RoundedButton btnNewButton = new RoundedButton("");
		btnNewButton.setIcon(new ImageIcon(MaterialFileChooser.class.getResource("/com/nativeChooser/folder.png")));

		btnNewButton.setBounds(249, 0, 42, 39);
		panel_3.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("1");
		btnNewButton_1.setBounds(303, 7, 42, 25);
		panel_3.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("1");
		btnNewButton_2.setBounds(357, 7, 42, 25);
		panel_3.add(btnNewButton_2);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 76, 450, 186);
		add(panel_5);
	}
}
