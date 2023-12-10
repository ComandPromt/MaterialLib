package com.textField.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;

import com.contextmenu.DefaultContextMenu;
import com.toolTip.ToolTipLlamada;

import mthos.JMthos;

@SuppressWarnings("serial")
public class BootsTextFieldConIcono extends JPanel {

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private BootsTextField input;

	private JLabel label;

	private int espacioIcono;

	private Icon icono;

	public int getEspacioIcono() {

		return espacioIcono;

	}

	public void setEspacioIcono(int espacioIcono) {

		this.espacioIcono = espacioIcono;

	}

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

	public int getNumeroEspacios() {

		return input.getNumeroEspacios();

	}

	public void setNumeroEspacios(int numeroEspacios) {

		try {

			input.setNumeroEspacios(numeroEspacios);

		}

		catch (Exception e) {

		}

	}

	public int getAngulo() {

		return input.getAngulo();

	}

	public void setAngulo(int angulo) {

		try {

			input.setAngulo(angulo);

		}

		catch (Exception e) {

		}

	}

	public String getPlaceholder() {

		return input.getPlaceholder();

	}

	public void setPlaceholder(String placeholder) {

		try {

			input.setPlaceholder(placeholder);

		}

		catch (Exception e) {

		}

	}

	public Color getBordeActivo() {

		return input.getBordeActivo();

	}

	public void setBordeActivo(Color bordeActivo) {

		try {

			input.setBordeInactivo(bordeActivo);

		}

		catch (Exception e) {

		}

	}

	public Color getBordeInactivo() {

		return input.getBordeInactivo();

	}

	public void setBordeInactivo(Color bordeInactivo) {

		try {

			input.setBordeInactivo(bordeInactivo);

		}

		catch (Exception e) {

		}

	}

	public int getGrosor() {

		return input.getGrosor();

	}

	public void setGrosor(int grosor) {

		try {

			input.setGrosor(grosor);

		}

		catch (Exception e) {

		}

	}

	public BootsTextFieldConIcono() {

		this("", true);

	}

	public Color getPlaceholderColor() {

		return input.getPlaceholderColor();

	}

	public void setPlaceholderColor(Color placeholderColor) {

		try {

			input.setPlaceholderColor(placeholderColor);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setBackground(Color bg) {

		super.setBackground(bg);

		try {

			input.setBackground(getBackground());

			label.setBackground(getBackground());

		}

		catch (Exception e) {

		}

	}

	public void setIcon(Icon icon) {

		icono = icon;

	}

	public BootsTextFieldConIcono(String text, boolean defaultIcon) {

		setBorder(null);

		setFont(getFont().deriveFont(Font.PLAIN, 20));

		input = new BootsTextField(text);

		input.setBounds(0, 0, 414, 300);

		DefaultContextMenu.addDefaultContextMenu(input);

		setLayout(null);

		add(input);

		label = new JLabel("");

		label.setBounds(414, 0, 37, 300);

		add(label);

		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {

				input.setBounds(0, 0, (int) (getWidth() * 0.8f), getHeight());

				int ancho = (int) (getWidth() * 0.2f) - espacioIcono;

				label.setBounds((int) (getWidth() * 0.8f) + espacioIcono, 0, ancho, getHeight());

				if (icono == null) {

					label.setIcon(JMthos.resize(
							new ImageIcon(
									BootsTextFieldConIcono.class.getResource("/imgs/imagenes/text_field_boots.png")),
							ancho, getHeight()));

				}

				else {

					label.setIcon(JMthos.resize((ImageIcon) icono, ancho, getHeight()));

				}

			}

		});

	}

	public Font getFuentePlaceholder() {

		return input.getFuentePlaceholder();

	}

	public void setFuentePlaceholder(Font fuentePlaceholder) {

		try {

			input.setFuentePlaceholder(fuentePlaceholder);

		}

		catch (Exception e) {

		}

	}

}
