package com.dialog.popup;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.panels.round.PanelRedondo;

public class VentanaEmergente {

	public VentanaEmergente(JFrame frame, JPanel panel, String title, int width, int height, boolean round) {

		JDialog popupDialog = new JDialog(frame, title, true);

		if (round) {

			popupDialog.setUndecorated(true);

			popupDialog.setBackground(new Color(0, 0, 0, 0));

			popupDialog.setContentPane(new PanelRedondo(popupDialog, panel, width, height));

		}

		else {

			popupDialog.getContentPane().setLayout(null);

			panel.setBounds(0, 0, width, height);

			popupDialog.getContentPane().add(panel);

		}

		popupDialog.setSize(width, height);

		popupDialog.setResizable(false);

		popupDialog.setLocationRelativeTo(null);

		popupDialog.setVisible(true);

	}

}