package com.buttons.simple;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class VerySimpleButton extends JButton {

	public VerySimpleButton(String text) {

		super(text);

		setBorderPainted(false);

		setFocusPainted(false);

	}

	public VerySimpleButton() {

		super();

		setBorderPainted(false);

		setFocusPainted(false);

	}

}
