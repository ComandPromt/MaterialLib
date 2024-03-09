package com.materialfilechooser;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolTip;

import com.buttons.round.NButton;
import com.toolTip.ToolTipLlamada;

public class JFileChooserPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private NButton btnNewButton;

	private ThreadDialog threadDialog;

	private LinkedList<String> lista;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private JFrame frame;

	private String carpeta;

	public void setToolTip(String text, Color background, Color foreground, Color border, Font font) {

		calcularTooltip(text, background, foreground, border, font);

	}

	public void setIcon(Image icon) {

		try {

			frame.setIconImage(icon);

		}

		catch (Exception e) {

		}

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

		this.colorTexto = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || fondo == null || colorTexto == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, colorTexto, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

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

	public String getFirstItem() {

		try {

			return lista.getFirst();

		}

		catch (Exception e) {

			return null;

		}

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

	/**
	 * @wbp.parser.constructor
	 */
	public JFileChooserPanel(String originPath, String title, String text, boolean folder) {

		if (originPath == null || originPath.isEmpty()) {

			carpeta = System.getProperty("user.home");

		}

		else {

			carpeta = originPath;

		}

		setBackground(Color.WHITE);

		lista = new LinkedList<>();

		btnNewButton = new NButton(text);

		btnNewButton.setEffectColor(Color.LIGHT_GRAY);

		btnNewButton.setBackground(Color.PINK);

		frame = new JFrame();

		frame.setIconImage(new ImageIcon(getClass().getResource("/imgs/imagenes/folder.png")).getImage());

		threadDialog = new ThreadDialog(frame, this, carpeta, title, folder, null, true);

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

			btnNewButton = new NButton(text);

			btnNewButton.setEffectColor(Color.LIGHT_GRAY);

			btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 24));

			btnNewButton.setBackground(Color.PINK);

			threadDialog = new ThreadDialog(mainFrame, this, System.getProperty("user.home"), title, folder, null,
					true);

			btnNewButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					threadDialog.setVisible(true);

				}

			});

			setLayout(new GridLayout(0, 1, 0, 0));

			add(btnNewButton);

		}

	}

	public JFileChooserPanel(String originPath, final JFrame mainFrame, String text, String title, boolean folder,
			String[] filtro, boolean all) {

		if (originPath == null || originPath.isEmpty()) {

			carpeta = System.getProperty("user.home");

		}

		else {

			carpeta = originPath;

		}

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

			btnNewButton = new NButton(text);

			btnNewButton.setEffectColor(Color.LIGHT_GRAY);

			btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 24));

			btnNewButton.setBackground(Color.PINK);

			threadDialog = new ThreadDialog(mainFrame, this, carpeta, title, folder, filtro, all);

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
