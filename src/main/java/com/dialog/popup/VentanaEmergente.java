package com.dialog.popup;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.panels.round.PopupPanel;

public class VentanaEmergente {

	public VentanaEmergente(JFrame frame, JPanel panel, String title, int width, int height, boolean defaultDesign,
			int thickness, ImageIcon icon) {

		this(frame, panel, title, width, height, defaultDesign, 25, Color.BLACK, thickness, icon);

	}

	public VentanaEmergente(JFrame frame, JPanel panel, String title, int width, int height, boolean defaultDesign,
			int angle, Color border, int thickness, ImageIcon icon) {

		JDialog popupDialog = new JDialog(frame, title, true);

		if (defaultDesign) {

			popupDialog.getContentPane().setLayout(new GridLayout());

			panel.setBounds(0, 0, width, height);

			popupDialog.getContentPane().add(panel);

			popupDialog.setIconImage(icon.getImage());

		}

		else {

			if (angle < 0) {

				angle = 0;

			}

			popupDialog.setUndecorated(true);

			popupDialog.setBackground(new Color(0, 0, 0, 0));

			if (angle < 1) {

				popupDialog.setContentPane(new PopupPanel(popupDialog, panel, width, height));

			}

			else {

				popupDialog = new RoundedDialog(panel, title, icon, width, height, border, thickness, angle);

			}

		}

		popupDialog.setSize(width, height);

		popupDialog.setResizable(false);

		popupDialog.setLocationRelativeTo(null);

		popupDialog.setVisible(true);

	}

}