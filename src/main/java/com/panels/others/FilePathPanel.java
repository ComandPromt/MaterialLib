package com.panels.others;

import java.awt.GridLayout;

import javax.swing.JPanel;

import com.buttons.round.NButton;
import com.jicons.Carpeta;
import com.jicons.Copy;
import com.jicons.IconFile;
import com.textField.text.NTextField;

@SuppressWarnings("serial")
public class FilePathPanel extends JPanel {

	private NTextField textField;

	private NButton boton;

	private NButton boton2;

	private NButton boton3;

	public void setTextField(NTextField textField) {

		this.textField = textField;

	}

	public void setBoton1(NButton boton) {

		this.boton = boton;

	}

	public void setBoton3(NButton boton2) {

		this.boton2 = boton2;

	}

	public void setBoton2(NButton boton3) {

		this.boton3 = boton3;

	}

	public NTextField getTextField() {

		return textField;

	}

	public NButton getBoton1() {

		return boton;

	}

	public NButton getBoton3() {

		return boton2;

	}

	public NButton getBoton2() {

		return boton3;

	}

	public FilePathPanel(String text) {

		setLayout(new GridLayout(2, 1));

		textField = new NTextField(text);

		JPanel panel1 = new JPanel();

		panel1.setLayout(new GridLayout());

		panel1.add(textField);

		add(panel1);

		JPanel panel2 = new JPanel();

		panel2.setLayout(new GridLayout(1, 3));

		boton = new NButton();

		boton.setIcon(new Copy());

		panel2.add(boton);

		boton3 = new NButton();

		boton3.setIcon(new IconFile("GIF", 12));

		panel2.add(boton3);

		boton2 = new NButton();

		boton2.setIcon(new Carpeta());

		panel2.add(boton2);

		add(panel2);

	}

}
