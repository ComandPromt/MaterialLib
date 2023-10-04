package com.materialfilechooser;

import javax.swing.JDialog;
import javax.swing.JFrame;

class VentanaNuevoFile {

	private NuevaCarpeta panel;

	private JDialog popupDialog;

	private int width;

	private int height;

	public void setPath(String path) {

		panel.setFolder(path);

	}

	public VentanaNuevoFile(JFrame parentFrame, String title, String folder, int width, int height) {

		this.width = width;

		this.height = height;

		popupDialog = new JDialog(parentFrame, title, true);

		popupDialog.getContentPane().setLayout(null);

		panel = new NuevaCarpeta(folder);

	}

	public void createAndShowDialog() {

		panel.setPopup(popupDialog);

		panel.setBounds(0, 0, width, height);

		popupDialog.getContentPane().add(panel);

		popupDialog.setSize(width, height);

		popupDialog.setResizable(false);

		popupDialog.setLocationRelativeTo(null);

		popupDialog.setVisible(true);

	}

}
