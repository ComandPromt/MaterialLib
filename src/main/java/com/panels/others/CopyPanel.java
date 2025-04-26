package com.panels.others;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jicons.CopyOld;

import mthos.JMthos;

@SuppressWarnings("serial")
public class CopyPanel extends JPanel {

	private JLabel lblNewLabel;

	private CopyOld copiar;

	public JLabel getButton() {

		return lblNewLabel;

	}

	public CopyOld getCopy() {

		return copiar;

	}

	@Override
	public void setBackground(Color bg) {

		super.setBackground(bg);

		try {

			lblNewLabel.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public void setIcon(ImageIcon icon) {

		try {

			lblNewLabel.setIcon(icon);

		}

		catch (Exception e) {

		}

	}

	public CopyPanel(String text, Color background) {

		this(text);

		setBackground(background);

	}

	/**
	 * @wbp.parser.constructor
	 */
	public CopyPanel(String text) {

		setLayout(new GridLayout(0, 1, 0, 0));

		lblNewLabel = new JLabel("");

		lblNewLabel.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				JMthos.copy(text);

			}

		});

		lblNewLabel.setBackground(Color.WHITE);

		copiar = new CopyOld();

		lblNewLabel.setIcon(copiar);

		add(lblNewLabel);

	}

}
