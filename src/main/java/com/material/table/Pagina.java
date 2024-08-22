package com.material.table;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.checkbox.CheckBoxCustom;
import com.panels.others.FilePathPanel;

import mthos.JMthos;

@SuppressWarnings("serial")
class Pagina extends JPanel {

	private JPanel panel;

	private JPanel check;

	private CheckBoxCustom checkBox;

	private FilePathPanel filePanel;

	private List<String> lista;

	private Font fuente;

	private Color fg;

	private String texto;

	private int tipo;

	private Color bottom;

	private Icon icono;

	public FilePathPanel getFilePanel() {

		return filePanel;

	}

	public void setFilePanel(FilePathPanel filePanel) {

		this.filePanel = filePanel;

	}

	public CheckBoxCustom getCheckBox() {

		return checkBox;

	}

	@Override
	public void setBackground(Color bg) {

		try {

			check.setBackground(bg);

			panel.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public void setTextAndType(int type, String text, ImageIcon icon) {

		tipo = type;

		panel.removeAll();

		texto = text;

		JLabel label;

		icono = icon;

		try {

			for (int i = 0; i < lista.size(); i++) {

				if (JMthos.isWindowsPath(lista.get(i)) || JMthos.isLinuxPath(lista.get(i))) {

					filePanel = new FilePathPanel(lista.get(i), false);

					filePanel.setTextFileBottom(text);

					filePanel.getBoton3().setIcon(icon);

					filePanel.setType(type);

					filePanel.setFileBottom(bottom);

					panel.add(filePanel);

				}

				else {

					label = new JLabel(lista.get(i));

					label.setHorizontalAlignment(SwingConstants.CENTER);

					label.setFont(fuente);

					label.setForeground(fg);

					panel.add(label);

				}

			}

		}

		catch (Exception e) {

		}

		panel.revalidate();

		panel.repaint();

	}

	public Pagina(List<String> lista, Font font, Color fg, boolean file) {

		texto = "";

		this.lista = lista;

		fuente = font;

		this.fg = fg;

		panel = new JPanel();

		check = new JPanel();

		setLayout(null);

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				check.setBounds(0, 0, Math.round(getWidth() * 0.025f), getHeight());

				panel.setLayout(new GridLayout(1, lista.size()));

				panel.setBounds(Math.round(getWidth() * 0.025f), 0, getWidth() - Math.round(getHeight() * 0.1f),
						getHeight());

				check.revalidate();

				panel.revalidate();

			}

		});

		checkBox = new CheckBoxCustom();

		check.setLayout(new GridLayout());

		check.add(checkBox);

		check.setBackground(Color.WHITE);

		panel.setBackground(Color.WHITE);

		if (!lista.isEmpty() && !lista.get(0).isEmpty()) {

			add(check);

		}

		JLabel label;

		for (int i = 0; i < lista.size(); i++) {

			if (file && (new File(lista.get(i)).isFile() || JMthos.isWindowsPath(lista.get(i))
					|| JMthos.isLinuxPath(lista.get(i)))) {

				filePanel = new FilePathPanel(lista.get(i), false);

				panel.add(filePanel);

			}

			else {

				label = new JLabel(lista.get(i));

				label.setHorizontalAlignment(SwingConstants.CENTER);

				label.setFont(font);

				label.setForeground(fg);

				panel.add(label);

			}

		}

		add(panel);

	}

	public void setCheckBackground(Color color) {

		try {

			checkBox.setBackground(color);

		}

		catch (Exception e) {

		}

	}

	public void setCheckColor(Color color) {

		try {

			checkBox.setCheckColor(color);

		}

		catch (Exception e) {

		}

	}

	public void setColorActivo(Color color) {

		try {

			checkBox.setColorActivo(color);

		}

		catch (Exception e) {

		}

	}

	public void setFileBottom(Color color) {

		bottom = color;

		panel.removeAll();

		JLabel label;

		try {

			for (int i = 0; i < lista.size(); i++) {

				if (JMthos.isWindowsPath(lista.get(i)) || JMthos.isLinuxPath(lista.get(i))) {

					filePanel = new FilePathPanel(lista.get(i), false);

					filePanel.setTextFileBottom(texto);

					filePanel.setType(tipo);

					filePanel.setFileBottom(color);
					filePanel.getBoton3().setIcon(icono);
					panel.add(filePanel);

				}

				else {

					label = new JLabel(lista.get(i));

					label.setHorizontalAlignment(SwingConstants.CENTER);

					label.setFont(fuente);

					label.setForeground(fg);

					panel.add(label);

				}

			}

		}

		catch (Exception e) {

		}

		panel.revalidate();

		panel.repaint();

	}

}
