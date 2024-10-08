package com.material.table;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import com.buttons.round.NButton;
import com.jicons.Delete;
import com.jicons.New;

@SuppressWarnings("serial")
class Crud extends JPanel {

	private NButton nuevo;

	private NButton delete;

	public NButton getNuevo() {

		return nuevo;

	}

	public NButton getDelete() {

		return delete;

	}

	@Override
	public void setBackground(Color bg) {

		try {

			nuevo.setBackground(bg);

			delete.setBackground(bg);

			super.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public Crud() {

		setBackground(Color.WHITE);

		setLayout(new GridLayout(1, 0, 0, 0));

		nuevo = new NButton("  ");

		nuevo.setBackground(Color.WHITE);

		New nuevo1 = new New();

		nuevo1.setGrosor(5f);

		nuevo.setIcon(nuevo1);

		delete = new NButton("  ");

		delete.setBackground(Color.WHITE);

		delete.setIcon(new Delete());

		add(nuevo);

		add(delete);

	}

}