package com.textField.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.border.Border;

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

	public BootsTextField getInput() {

		return input;

	}

	public int getEspacioIcono() {

		return this.espacioIcono;

	}

	public void setEspacioIcono(int espacioIcono) {

		this.espacioIcono = espacioIcono;

	}

	public void setToolTipText(String text) {

		setToolTip(text, (Color) null, (Color) null, (Color) null, (Font) null);

	}

	public void setToolTip(String text, Color background, Color foreground, Color border, Font font) {

		calcularTooltip(text, background, foreground, border, font);

	}

	private void calcularTooltip(String text, Color background, Color foreground, Color border, Font font) {

		if (background == null)

			background = new Color(32, 39, 55);

		if (foreground == null)

			foreground = Color.WHITE;

		if (border == null)

			border = new Color(173, 173, 173);

		if (font == null)

			try {

				font = getFont().deriveFont(20.0F);

			}

			catch (Exception e) {

				font = new Font("Dialog", 0, 20);

			}

		this.text = text;

		this.fondo = background;

		this.colorTexto = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	public JToolTip createToolTip() {

		if (this.text == null || this.fondo == null || this.colorTexto == null || this.border == null)

			return super.createToolTip();

		ToolTipLlamada tip = new ToolTipLlamada(this.text, this.fondo, this.colorTexto, this.border, this.fuente);

		tip.setComponent(this);

		return (JToolTip) tip;

	}

	public int getNumeroEspacios() {

		return this.input.getNumeroEspacios();

	}

	public void setNumeroEspacios(int numeroEspacios) {

		try {

			this.input.setNumeroEspacios(numeroEspacios);

		}

		catch (Exception exception) {

		}

	}

	public int getAngulo() {

		return this.input.getAngulo();

	}

	public void setAngulo(int angulo) {

		try {

			this.input.setAngulo(angulo);

		}

		catch (Exception exception) {

		}

	}

	public String getPlaceholder() {

		return this.input.getPlaceholder();

	}

	public void setPlaceholder(Color color) {

		try {

			this.input.setPlaceholderColor(color);

		}

		catch (Exception exception) {

		}

	}

	public void setPlaceholder(String placeholder) {

		try {

			this.input.setPlaceholder(placeholder);

		}

		catch (Exception exception) {

		}

	}

	public Color getBordeActivo() {

		return this.input.getBordeActivo();

	}

	public void setBordeActivo(Color bordeActivo) {

		try {

			this.input.setBordeInactivo(bordeActivo);

		}

		catch (Exception exception) {

		}

	}

	public Color getBordeInactivo() {

		return this.input.getBordeInactivo();

	}

	public void setBordeInactivo(Color bordeInactivo) {

		try {

			this.input.setBordeInactivo(bordeInactivo);

		}

		catch (Exception exception) {

		}

	}

	public int getGrosor() {

		return this.input.getGrosor();

	}

	public void setGrosor(int grosor) {

		try {

			this.input.setGrosor(grosor);

		}

		catch (Exception exception) {

		}

	}

	public BootsTextFieldConIcono(String text) {

		this(text, true);

	}

	public BootsTextFieldConIcono() {

		this("", true);

	}

	public Color getPlaceholderColor() {

		return this.input.getPlaceholderColor();

	}

	public void setPlaceholderColor(Color placeholderColor) {

		try {

			this.input.setPlaceholderColor(placeholderColor);

		}

		catch (Exception exception) {

		}

	}

	public void setBackground(Color bg) {

		super.setBackground(bg);

		try {

			this.input.setBackground(getBackground());

			this.label.setBackground(getBackground());

		}

		catch (Exception exception) {

		}

	}

	public void setIcon(Icon icon) {

		this.icono = icon;

	}

	public BootsTextFieldConIcono(String text, boolean defaultIcon) {

		setBorder((Border) null);

		setFont(getFont().deriveFont(0, 20.0F));

		this.input = new BootsTextField(text);
		input.setGrosor(5);
		input.setPlaceholderColor(Color.BLUE);
		input.setPlaceholder("AAAA");

		this.input.setBounds(0, 0, 414, 300);

		DefaultContextMenu.addDefaultContextMenu(this.input);

		setLayout((LayoutManager) null);

		add(this.input);

		this.label = new JLabel("");

		this.label.setBounds(414, 0, 37, 300);

		add(this.label);

		addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent e) {

				BootsTextFieldConIcono.this.input.setBounds(0, 0, (int) (BootsTextFieldConIcono.this.getWidth() * 0.8F),
						BootsTextFieldConIcono.this.getHeight());

				int ancho = (int) (BootsTextFieldConIcono.this.getWidth() * 0.2F)
						- BootsTextFieldConIcono.this.espacioIcono;

				BootsTextFieldConIcono.this.label.setBounds(
						(int) (BootsTextFieldConIcono.this.getWidth() * 0.8F)
								+ BootsTextFieldConIcono.this.espacioIcono,
						0, ancho, BootsTextFieldConIcono.this.getHeight());

				if (BootsTextFieldConIcono.this.icono == null) {

					BootsTextFieldConIcono.this.label.setIcon(JMthos.resize(
							new ImageIcon(
									BootsTextFieldConIcono.class.getResource("/imgs/imagenes/text_field_boots.png")),
							ancho, BootsTextFieldConIcono.this.getHeight()));

				}

				else {

					BootsTextFieldConIcono.this.label
							.setIcon(JMthos.resize((ImageIcon) BootsTextFieldConIcono.this.icono, ancho,
									BootsTextFieldConIcono.this.getHeight()));
				}

			}

		});

	}

	public Font getFuentePlaceholder() {

		return this.input.getFuentePlaceholder();

	}

	public void setFuentePlaceholder(Font fuentePlaceholder) {

		try {

			this.input.setFuentePlaceholder(fuentePlaceholder);

		}

		catch (Exception exception) {

		}

	}

	public void setPlaceholderFont(Font font) {

		try {

			input.setFuentePlaceholder(font);

		}

		catch (Exception e) {

		}

	}

	public void setText(String string) {

		try {

			input.setText(string);

		}

		catch (Exception e) {

		}

	}

}
