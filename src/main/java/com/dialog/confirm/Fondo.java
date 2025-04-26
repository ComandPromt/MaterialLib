package com.dialog.confirm;

import java.awt.Color;

import javax.swing.JFrame;

@SuppressWarnings("serial")

class Fondo extends JFrame {

	public Fondo(Color background) {

		setBackground(background);

		getContentPane().setBackground(background);

		setLocationRelativeTo(null);

	}

	public Fondo() {

		this(Color.RED);

	}

}
