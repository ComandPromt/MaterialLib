package com.jmenu.paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")

public class Cabecera extends JPanel {

	private IconoCabecera icono;

	private JLabel texto;

	private int anchoIcono;

	@Override
	public void setFont(Font font) {

		try {

			texto.setFont(font);

		}

		catch (Exception e) {

		}

	}

	public void setIcon(Icon icon) {

		try {

			icono.setIcon(icon);

		}

		catch (Exception e) {

		}

	}

	public void setText(String text) {

		texto.setText(text);

	}

	@Override
	public void setForeground(Color fg) {

		try {

			texto.setForeground(fg);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setBackground(Color bg) {

		super.setBackground(bg);

		try {

			icono.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public Cabecera() {

		anchoIcono = 45;

		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {

				icono.setBounds(0, 0, anchoIcono, getHeight());

				texto.setBounds(anchoIcono, 0, getWidth() - anchoIcono, getHeight());

			}

		});

		setLayout(null);

		icono = new IconoCabecera();

		icono.setBounds(0, 0, 204, 300);

		add(icono);

		texto = new JLabel("New label");

		texto.setFont(new Font("Dialog", Font.PLAIN, 20));

		texto.setHorizontalAlignment(SwingConstants.CENTER);

		texto.setBounds(203, 0, 247, 300);

		add(texto);

	}

	public void setAnchoIcono(int ancho) {

		anchoIcono = ancho;

	}

}
