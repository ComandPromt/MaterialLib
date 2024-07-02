package com.draganddrop;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.LinkedList;
import java.util.TooManyListenersException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.buttons.simple.ResizedButton;
import com.jicons.Reload;
import com.layout.VerticalGridLayout;
import com.materialfilechooser.JFileChooserPanel;
import com.toolTip.ToolTipLlamada;

import mthos.JMthos;

@SuppressWarnings("serial")
public class FileDragAndDrop extends JPanel {

	private DragAndDrop panel;

	public TitledBorder dragBorder;

	private JLabel texto;

	private JFileChooserPanel boton;

	private JPanel panel1;

	private ResizedButton boton1;

	private LinkedList<String> lista;

	private String descripcion;

	private String text;

	private Color fondo;

	private Color colorFondo;

	private Color border;

	private Font fuente;

	private ResizedButton btnNewButton;

	@Override
	public void setToolTipText(String text) {

		setToolTip(text, null, null, null, null);

	}

	public void setToolTip(String text, Color background, Color foreground, Color border, Font font) {

		calcularTooltip(text, background, foreground, border, font);

	}

	private void calcularTooltip(String text, Color background, Color foreground, Color border, Font font) {

		if (background == null) {

			background = new Color(32, 39, 55);

		}

		if (foreground == null) {

			foreground = Color.WHITE;

		}

		if (border == null) {

			border = new Color(173, 173, 173);

		}

		if (font == null) {

			try {

				font = getFont().deriveFont(20f);

			}

			catch (Exception e) {

				font = new Font("Dialog", Font.PLAIN, 20);

			}

		}

		this.text = text;

		this.fondo = background;

		this.colorFondo = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || fondo == null || colorFondo == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, colorFondo, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public LinkedList<String> getList() {

		return lista;

	}

	public void setList(LinkedList<String> lista) {

		this.lista = lista;

	}

	@Override
	public void setForeground(Color bg) {

		try {

			texto.setForeground(bg);

		}

		catch (Exception e) {
		}

	}

	public void setButtonForeground(Color bg) {

		try {

			boton.setForeground(bg);

		}

		catch (Exception e) {
		}

	}

	@Override
	public void setBackground(Color bg) {

		super.setBackground(bg);

		try {

			panel1.setBackground(bg);

			panel.setBackground(bg);

			boton.setBackground(bg);

			boton1.setBackground(bg);

			btnNewButton.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	private void setAltura(int altura) {

		try {

			panel.setAltura(altura);

		}

		catch (Exception e) {

		}

	}

	public void setDashed(boolean dashed) {

		setPanelDashed(dashed);

	}

	public void setRound(boolean round) {

		panel.setRound(round);

	}

	public void setButtonFont(Font font) {

		try {

			boton.setFont(font);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setFont(Font font) {

		try {

			texto.setFont(font);

		}

		catch (Exception e) {

		}

	}

	private void add(String string) {

		try {

			if (!lista.contains(string)) {

				lista.add(string);

			}

		}

		catch (Exception e) {

		}

	}

	public void setButtonEffectColor(Color color) {

		try {

			boton.setEffectColor(color);

		}

		catch (Exception e) {

		}

	}

	public void setButtonColor(Color color) {

		try {

			boton.setButtonBackground(color);

		}

		catch (Exception e) {

		}

	}

	void setPanelDashed(boolean dashed) {

		try {

			panel.setDashed(dashed);

		}

		catch (Exception e) {

		}

	}

	void setPanelThickness(int thickness) {

		try {

			panel.setThickness(thickness);

		}

		catch (Exception e) {

		}

	}

	public FileDragAndDrop(String originPath, String button, String text, boolean carpeta) {

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				setAltura((int) (getHeight() / 2.833f));

			}

		});

		setFocusable(false);

		setBorder(new EmptyBorder(5, 5, 5, 5));

		descripcion = "";

		lista = new LinkedList<>();

		setFont(new Font("Dialog", Font.PLAIN, 25));

		setForeground(SystemColor.desktop);

		setBackground(Color.WHITE);

		this.dragBorder = new javax.swing.border.TitledBorder("");

		setLayout(new GridLayout(1, 0, 0, 0));

		panel = new DragAndDrop("");

		panel.setThickness(5);

		panel.setLayout(new VerticalGridLayout(3, 1));

		try {

			new UtilDragAndDrop(panel, panel.dragBorder, true, new UtilDragAndDrop.Listener() {

				@Override

				public void filesDropped(java.io.File[] archivos) {

					if (archivos.length == 1) {

						add(archivos[0].toString());

						if (lista.size() == 1) {

							texto.setText(JMthos.saberNombreArchivo(lista.getFirst()));

						}

						else {

							texto.setText(JMthos.saberNombreArchivo(lista.getLast()));

						}

					}

					else {

						for (int i = 0; i < archivos.length; i++) {

							add(archivos[i].toString());

						}

						texto.setText(lista.size() + " archivos");

					}

				}

			});

		}

		catch (TooManyListenersException e1) {

		}

		add(panel);

		boton = new JFileChooserPanel(originPath, button, text, carpeta);

		panel.add(boton);

		panel1 = new JPanel();

		panel1.setBackground(Color.WHITE);

		boton.add(panel1);

		boton1 = new ResizedButton(true);

		boton1.setBounds(155, 0, 58, 48);

		boton1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				lista.clear();

				texto.setText(descripcion);

			}

		});

		panel1.setLayout(null);

		boton1.setBackground(Color.WHITE);

		boton1.setIcon(new ImageIcon(FileDragAndDrop.class.getResource("/imgs/imagenes/clean.png")));

		panel1.add(boton1);

		btnNewButton = new ResizedButton(true);

		btnNewButton.setBounds(223, 0, 48, 48);

		btnNewButton.setIcon(new Reload());

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < boton.getList().size(); i++) {

					if (!lista.contains(boton.getList().get(i))) {

						lista.add(boton.getList().get(i));

					}

				}

				if (!lista.isEmpty()) {

					texto.setText(lista.size() + " archivos");

				}

			}

		});

		panel1.add(btnNewButton);

		texto = new JLabel("");

		texto.setFont(new Font("Dialog", Font.BOLD, 25));

		texto.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(texto);

	}

}
