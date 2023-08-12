package com.panels;

import java.awt.Button;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")

public class SimpleSliderButtonPanel extends JPanel {

	private ArrayList<Button> buttons;

	private ArrayList<Label> labels;

	private Color foregroundColor;

	private Color backgroundColor;

	private Color backgroundLabelColor;

	private int indice;

	private int iluminacion;

	private JPanel panel1 = new JPanel();

	private JPanel panel2 = new JPanel();

	public ArrayList<Label> getLabels() {

		return labels;

	}

	public void setLabels(ArrayList<Label> labels) {

		this.labels = labels;

	}

	public void setBackgroundLabel(Color color) {

		try {

			backgroundLabelColor = color;

			int contador = 0;

			for (Label label : labels) {

				if (iluminacion == contador) {

					label.setBackground(color);

				}

				else {

					label.setBackground(backgroundColor);

				}

				contador++;

			}

			panel2.setBackground(color);

		}

		catch (Exception e) {

		}

	}

	public int getIluminacion() {

		return iluminacion;

	}

	public void refrescar() {

		removeAll();

		for (Button boton : buttons) {

			add(boton);

		}

	}

	public ArrayList<Button> getButtons() {

		return buttons;

	}

	public void setButtons(ArrayList<Button> buttons) {

		this.buttons = buttons;

	}

	public void setForegroundColor(Color color) {

		this.foregroundColor = color;

		for (Button boton : buttons) {

			boton.setForeground(color);

		}

	}

	@Override
	public void setBackground(Color color) {

		try {

			this.backgroundColor = color;

			for (Button boton : buttons) {

				boton.setBackground(color);

			}

			panel1.setBackground(color);

		}

		catch (Exception e) {
		}

	}

	private void iluminar(int index) {

		iluminacion = index;

		for (int i = 0; i < labels.size(); i++) {

			if (i == index) {

				labels.get(i).setBackground(backgroundLabelColor);

			}

			else {

				labels.get(i).setBackground(backgroundColor);
			}

		}

	}

	public SimpleSliderButtonPanel(String[] items) {

		Label label;

		indice = 0;

		iluminacion = 0;

		foregroundColor = Color.BLACK;

		backgroundColor = Color.WHITE;

		buttons = new ArrayList<>();

		labels = new ArrayList<>();

		add(panel1);

		add(panel2);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		panel1.setBounds(0, 0, 400, 50);

		panel2.setBounds(0, 49, 400, 15);

		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

		backgroundLabelColor = Color.GREEN;

		Button boton;

		for (indice = 0; indice < items.length; indice++) {

			boton = new Button(items[indice]);

			boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

			boton.setFocusable(false);

			boton.setBackground(backgroundColor);

			boton.setForeground(foregroundColor);

			boton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N

			buttons.add(boton);

			label = new Label("");

			if (indice == 0) {

				label.setBackground(backgroundLabelColor);

			}

			else {

				label.setBackground(backgroundColor);

			}

			labels.add(label);

			panel1.add(boton);

			panel2.add(label);

			ponerIndice(buttons.get(indice), indice);

		}

	}

	private void ponerIndice(Button boton, int index) {

		boton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				iluminar(index);

			}

		});

	}

}
