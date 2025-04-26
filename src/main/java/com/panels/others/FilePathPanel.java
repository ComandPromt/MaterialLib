package com.panels.others;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import com.buttons.round.NButton;
import com.jicons.Carpeta;
import com.jicons.CopyOld;
import com.jicons.IconFile;
import com.textField.text.NTextField;

import mthos.JMthos;

@SuppressWarnings("serial")
public class FilePathPanel extends JPanel {

	private NTextField textField;

	private NButton boton;

	private NButton boton2;

	private NButton boton3;

	private JPanel panel2;

	private IconFile iconFile;

	public void setType(int tipo) {

		try {

			iconFile.setTipo(tipo);

		}

		catch (Exception e) {

		}

	}

	public void setTextField(NTextField textField) {

		this.textField = textField;

	}

	public void setBoton1(NButton boton) {

		this.boton = boton;

		panel2.setBackground(boton3.getBackground());

	}

	public void setBoton3(NButton boton2) {

		this.boton2 = boton2;

		panel2.setBackground(boton3.getBackground());

	}

	public void setBoton2(NButton boton3) {

		this.boton3 = boton3;

		panel2.setBackground(boton3.getBackground());
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

	/**
	 * @wbp.parser.constructor
	 */
	public FilePathPanel(String text, boolean showTextField) {

		this(text, 2, showTextField);

	}

	public FilePathPanel(String text, int type, boolean showTextField) {

		iconFile = new IconFile("", type);

		if (showTextField) {

			setLayout(new GridLayout(2, 1));

			textField = new NTextField(text);

			JPanel panel1 = new JPanel();

			panel1.setLayout(new GridLayout());

			panel1.add(textField);

			add(panel1);

		}

		else {

			setLayout(new GridLayout(1, 1));

		}

		panel2 = new JPanel();

		panel2.setLayout(new GridLayout(1, 3));

		boton = new NButton();

		boton.addMouseListener(new MouseAdapter() {
			@Override

			public void mousePressed(MouseEvent e) {

				JMthos.copy(text);

			}

		});

		boton.setBackground(Color.WHITE);

		boton.setIcon(new CopyOld());

		panel2.add(boton);

		boton3 = new NButton();

		panel2.setBackground(Color.WHITE);

		boton3.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				JMthos.abrir(text);

			}

		});

		boton3.setBackground(Color.WHITE);

		boton3.setIcon(iconFile);

		panel2.add(boton3);

		boton2 = new NButton();

		boton2.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				JMthos.abrir(JMthos.extraerCarpeta(text));

			}

		});

		boton2.setBackground(Color.WHITE);

		boton2.setIcon(new Carpeta());

		panel2.add(boton2);

		add(panel2);

	}

	public void setTextFileBottom(String text) {

		try {

			iconFile.setTexto(text);

			boton3.setIcon(iconFile);

		}

		catch (Exception e) {

		}

	}

	public void setFileBottom(Color color) {

		try {

			iconFile.setBottomColor(color);

			boton3.setIcon(iconFile);

		}

		catch (Exception e) {

		}

	}

}
