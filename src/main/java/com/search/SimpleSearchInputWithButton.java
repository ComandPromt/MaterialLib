package com.search;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import com.buttons.animated.ButtonCustom;
import com.layout.MaterialPanelLayout;

@SuppressWarnings("serial")
public class SimpleSearchInputWithButton extends JPanel {

	private BootSearchInput lblNewLabel;

	private JButton btnNewButton;

	public void setThickness(int thick) {

		try {

			lblNewLabel.setThickness(thick);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setForeground(Color color) {

		try {

			lblNewLabel.setForeground(color);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setBackground(Color color) {

		try {

			lblNewLabel.setBackground(color);

		} catch (Exception e) {

		}

	}

	public void setFont(Font font) {

		try {

			lblNewLabel.setFont(font);

		}

		catch (Exception e) {

		}

	}

	public JButton getButton() {

		return btnNewButton;

	}

	public void setBtnNewButton(JButton btnNewButton) {

		this.btnNewButton = btnNewButton;

	}

	public BootSearchInput getInput() {

		return lblNewLabel;

	}

	public SimpleSearchInputWithButton(String text, String search) {

		this(text);

		btnNewButton.setText(search);

	}

	public SimpleSearchInputWithButton(String text) {

		this();

		lblNewLabel.setText(text);

	}

	public SimpleSearchInputWithButton() {

		this(80);

	}

	public SimpleSearchInputWithButton(int percent) {

		if (percent < 1) {

			percent = 80;

		}

		setLayout(new GridLayout(0, 1, 0, 0));

		lblNewLabel = new BootSearchInput(0);

		btnNewButton = new ButtonCustom("Search");

		ArrayList<JComponent> lista = new ArrayList<>();

		JPanel panel1 = new JPanel();

		panel1.add(lblNewLabel);

		panel1.setLayout(new GridLayout(0, 1, 0, 0));

		lista.add(panel1);

		JPanel panel2 = new JPanel();

		panel2.add(btnNewButton);

		panel2.setLayout(new GridLayout(0, 1, 0, 0));

		lista.add(panel2);

		ArrayList<Integer> porcentajes = new ArrayList<>();

		porcentajes.add(percent);

		porcentajes.add(100 - percent);

		add(new MaterialPanelLayout(lista, porcentajes, false));

	}

	public void setButtonFont(Font font) {

		try {

			btnNewButton.setFont(font);

		}

		catch (Exception e) {

		}

	}

	public void setMagnifyingGlassBackgroundColor(Color color) {

		try {

			lblNewLabel.setMagnifyingGlassBackgroundColor(color);

		}

		catch (Exception e) {

		}

	}

	public void setMagnifyingGlassColor(Color color) {

		try {

			lblNewLabel.setMagnifyingGlassColor(color);

		}

		catch (Exception e) {

		}

	}

	public void setBackColor(Color color) {

		try {

			lblNewLabel.setBackColor(color);

		}

		catch (Exception e) {

		}

	}

	public void setIconBackground(Color color) {

		try {

			lblNewLabel.setIconBackground(color);

		}

		catch (Exception e) {

		}

	}

}
