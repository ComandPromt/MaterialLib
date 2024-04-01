package com.textField.passwordField;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jicons.Eye;

public class PanelOjo extends JPanel {

	private Eye ojo;

	public void setTachado(boolean tachado) {

		try {

			ojo.setTachado(tachado);

		} catch (Exception e) {

		}

	}

	public Image getImage() {

		try {

			return ojo.getImage();

		}

		catch (Exception e) {

			return null;

		}

	}

	public PanelOjo() {

		setLayout(new GridLayout(0, 1, 0, 0));

		JLabel label = new JLabel();

		ojo = new Eye();

		label.setIcon(ojo);

		add(label);

	}

}
