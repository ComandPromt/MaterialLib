package com.spinner.simple;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import com.buttons.round.NButton;
import com.spinner.decimal.DecimalSpinner;

@SuppressWarnings("serial")
public class SpinnerWithDefaultValue extends JPanel {

	public SpinnerWithDefaultValue() {

		this(false, "", null);

	}

	public SpinnerWithDefaultValue(String text) {

		this(false, text, null);

	}

	public SpinnerWithDefaultValue(boolean decimal, String text) {

		this(decimal, text, null);

	}

	public SpinnerWithDefaultValue(String text, ImageIcon icon) {

		this(false, text, icon);

	}

	public SpinnerWithDefaultValue(boolean decimal, String text, ImageIcon icon) {

		setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();

		add(panel);

		JSpinner spiner = new JSpinner();

		NButton lblNewLabel = new NButton("");

		lblNewLabel.setIcon(icon);

		if (decimal) {

			spiner = new Spinner(text);

		}

		else {

			spiner = new DecimalSpinner(text);

		}

		ArrayList<JComponent> lista = new ArrayList<>();

		lista.add(spiner);

		lista.add(lblNewLabel);

		ArrayList<Integer> porcentajes = new ArrayList<>();

		porcentajes.add(80);

		porcentajes.add(20);

	}

}
