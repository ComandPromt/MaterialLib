package com.materialfilechooser;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.buttons.round.NButton;

public class JFileChooserPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private NButton btnNewButton;

	private ThreadDialog threadDialog;

	private LinkedList<String> lista;

	@Override
	public void setToolTipText(String text) {

		try {

			btnNewButton.setToolTipText(text);

		}

		catch (Exception e) {

		}

	}

	public void setThickness(int thickness) {

		try {

			btnNewButton.setThickness(thickness);

		}

		catch (Exception e) {

		}

	}

	public void setBorderColor(Color color) {

		try {

			btnNewButton.setBorderColor(color);

		}

		catch (Exception e) {

		}

	}

	public void setLista(LinkedList<String> lista) {

		this.lista = lista;

	}

	public LinkedList<String> getList() {

		return lista;

	}

	@Override
	public void setForeground(Color fg) {

		try {

			btnNewButton.setForeground(fg);

		}

		catch (Exception e) {

		}

	}

	public void setEffectColor(Color color) {

		try {

			btnNewButton.setEffectColor(color);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setFont(Font font) {

		try {

			btnNewButton.setFont(font);

		}

		catch (Exception e) {

		}

	}

	public void setButtonBackground(Color color) {

		try {

			btnNewButton.setBackground(color);

		}

		catch (Exception e) {

		}

	}

	public void setText(String text) {

		try {

			btnNewButton.setText(text);

		}

		catch (Exception e) {

		}

	}

	public void setTitle(String title) {

		try {

			threadDialog.setTitle(title);

		}

		catch (Exception e) {

		}

	}

	public JFileChooserPanel(String text, String title, boolean folder) {

		setBackground(Color.WHITE);

		lista = new LinkedList<>();

		btnNewButton = new NButton(text);

		btnNewButton.setEffectColor(Color.LIGHT_GRAY);

		btnNewButton.setBackground(Color.PINK);

		threadDialog = new ThreadDialog(new JFrame(), this, title, folder, null, true);

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				threadDialog.setVisible(true);

			}

		});

		setLayout(new GridLayout(0, 1, 0, 0));

		add(btnNewButton);

	}

	public JFileChooserPanel(JFrame mainFrame, String text, String title, boolean folder) {

		if (title == null) {

			title = "";

		}

		if (mainFrame != null) {

			setBackground(Color.WHITE);

			lista = new LinkedList<>();

			btnNewButton = new NButton("");

			btnNewButton.setEffectColor(Color.LIGHT_GRAY);

			btnNewButton.setText(text);

			btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 24));

			btnNewButton.setBackground(Color.PINK);

			threadDialog = new ThreadDialog(mainFrame, this, title, folder, null, true);

			btnNewButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					threadDialog.setVisible(true);

				}

			});

			setLayout(new GridLayout(0, 1, 0, 0));

			add(btnNewButton);

		}

	}

	public JFileChooserPanel(final JFrame mainFrame, String text, String title, boolean folder, String[] filtro,
			boolean all) {

		if (filtro == null || (filtro.length == 1 && filtro[0].toString().toLowerCase().equals("all"))) {

			filtro = null;

			folder = false;

			all = false;

		}

		if (mainFrame != null) {

			if (title == null) {

				title = "";

			}

			if (text == null) {

				text = "";

			}

			setBackground(Color.WHITE);

			lista = new LinkedList<>();

			btnNewButton = new NButton("New button");

			btnNewButton.setEffectColor(Color.LIGHT_GRAY);

			btnNewButton.setText(text);

			btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 24));

			btnNewButton.setBackground(Color.PINK);

			threadDialog = new ThreadDialog(mainFrame, this, title, folder, filtro, all);

			btnNewButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					threadDialog.setVisible(true);

				}

			});

			setLayout(new GridLayout(0, 1, 0, 0));

			add(btnNewButton);

		}

	}

}

class ThreadDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JFileChooserPanel superior;

	private JFrame mainFrame;

	public void setLista(LinkedList<String> lista) {

		superior.setLista(lista);

	}

	public ThreadDialog(JFrame parent, JFileChooserPanel superior, String title, boolean folder, String[] filtro,
			boolean all) {

		super(parent, title, false);

		mainFrame = parent;

		this.superior = superior;

		JPanel panel;

		if (folder) {

			panel = new MaterialFolderChooser(mainFrame, this, title);

		}

		else {

			panel = new MaterialFileChooser(mainFrame, this, filtro, all, title);

		}

		setSize(750, 550);

		add((JPanel) panel);

		setLocationRelativeTo(null);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

}
