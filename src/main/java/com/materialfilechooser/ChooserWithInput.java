package com.materialfilechooser;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.button.rs.RSButtonMetro;
import com.layout.MaterialPanelLayout;
import com.textField.text.NTextField;

@SuppressWarnings("serial")
public class ChooserWithInput extends JPanel {

	private RSButtonMetro boton;

	private JPanel panel;

	private ChooserWithInput este;

	private LinkedList<String> lista;

	private NTextField field;

	private JFrame frame;

	public void setLeft(int left) {

		try {

			field.setLeft(left);

		}

		catch (Exception e) {

		}

	}

	public void setHorizontalAlignment(int align) {

		try {

			field.setHorizontalAlignment(align);

		}

		catch (Exception e) {

		}

	}

	public RSButtonMetro getButton() {

		return boton;

	}

	public void setFont(Font font) {

		try {

			field.setFont(font);

		}

		catch (Exception e) {

		}

	}

	public void setText(String text) {

		try {

			field.setText(text);

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
	public void setBackground(Color bg) {

		super.setBackground(bg);

		try {

			boton.setColorBorde(bg);

			boton.setBackground(bg);

			boton.setColorHover(bg);

			boton.setColorTextNormal(bg);

			boton.setColorNormal(bg);

			boton.setColorPressed(bg);

			boton.setColorTextPressed(bg);

			panel.setBackground(Color.WHITE);

		}

		catch (Exception e) {

		}

	}

	public void setIcon(Image icon) {

		try {

			frame.setIconImage(icon);

		}

		catch (Exception e) {

		}

	}

	public void setHeader(String text) {

		try {

			field.setHeaderText(text);

		}

		catch (Exception e) {

		}

	}

	public ChooserWithInput(String title, String originFolder, boolean folder, boolean unique) {

		este = this;

		lista = new LinkedList<>();

		setLayout(new GridLayout(0, 1, 0, 0));

		List<JComponent> lista = new ArrayList<>();

		field = new NTextField();

		lista.add(field);

		boton = new RSButtonMetro("");

		boton.setIcon(new ImageIcon(getClass().getResource("/imgs/imagenes/file.png")));

		final AtomicReference<String> originFolderRef = new AtomicReference<>(originFolder);

		boton.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				try {

					String originFolder2 = originFolderRef.get();

					if (originFolder2 == null || originFolder2.isEmpty()) {

						originFolder2 = System.getProperty("user.home");

					}

					frame = new JFrame();

					frame.setIconImage(new ImageIcon(getClass().getResource("/imgs/imagenes/folder.png")).getImage());

					new ThreadDialog(frame, este, originFolder2, title, folder, null, true, field, unique)
							.setVisible(true);

				}

				catch (Exception e1) {

				}

			}

		});

		lista.add(boton);

		List<Integer> porcentajes = new ArrayList<>();

		porcentajes.add(90);

		porcentajes.add(10);

		panel = new MaterialPanelLayout(lista, porcentajes, false);

		add(panel);

		setBackground(Color.WHITE);

	}

}
