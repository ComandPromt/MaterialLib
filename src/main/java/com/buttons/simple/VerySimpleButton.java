package com.buttons.simple;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class VerySimpleButton extends JButton {

	public VerySimpleButton(String text) {

		setText(text);

		setBorder(null);

		setFocusPainted(false);

		setContentAreaFilled(false);

	}

}
