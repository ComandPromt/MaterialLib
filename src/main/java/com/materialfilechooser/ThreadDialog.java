package com.materialfilechooser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.stream.Collectors;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.textField.text.NTextField;

class ThreadDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JFileChooserPanel superior;

	private JFrame mainFrame;

	private ChooserWithInput superior2;

	private NTextField field;

	private boolean unique;

	public void setLista(LinkedList<String> lista) {

		superior.setLista(lista);

	}

	public void setLista2(LinkedList<String> lista) {

		try {

			if (!lista.isEmpty()) {

				if (unique) {

					field.setText(lista.get(0));

				}

				else {

					if (lista.size() == 1) {

						field.setText(lista.get(0));

					}

					else {

						LinkedList<String> archivos = lista.stream().filter(path -> !Files.isDirectory(Paths.get(path)))
								.collect(Collectors.toCollection(LinkedList::new));

						field.setText(String.join(",", archivos));

					}

				}

			}

		}

		catch (Exception e) {

		}

		superior2.setLista(lista);

	}

	public ThreadDialog(JFrame parent, JFileChooserPanel superior, String originFolder, String title, boolean folder,
			String[] filtro, boolean all) {

		super(parent, title, false);

		mainFrame = parent;

		this.superior = superior;

		JPanel panel;

		if (folder) {

			panel = new MaterialFolderChooser(originFolder, mainFrame, this, title, false);

		}

		else {

			panel = new MaterialFileChooser(originFolder, mainFrame, this, filtro, all, title, false);

		}

		setSize(750, 550);

		add((JPanel) panel);

		setIconImage(null);

		setLocationRelativeTo(null);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	public ThreadDialog(JFrame parent, ChooserWithInput superior, String originFolder, String title, boolean folder,
			String[] filtro, boolean all, NTextField field, boolean unique) {

		super(parent, title, false);

		this.field = field;

		this.unique = unique;

		mainFrame = parent;

		this.superior2 = superior;

		JPanel panel;

		if (folder) {

			panel = new MaterialFolderChooser(originFolder, mainFrame, this, title, true);

		}

		else {

			panel = new MaterialFileChooser(originFolder, mainFrame, this, filtro, all, title, true);

		}

		setSize(750, 550);

		add((JPanel) panel);

		setIconImage(null);

		setLocationRelativeTo(null);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

}
