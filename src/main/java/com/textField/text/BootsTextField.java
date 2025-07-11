package com.textField.text;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.border.Border;

import com.contextmenu.DefaultContextMenu;
import com.toolTip.ToolTipLlamada;

import mthos.JMthos;

@SuppressWarnings("serial")
public class BootsTextField extends JTextField {

	private boolean pintarBorde;

	private Font fuentePlaceholder;

	private int grosor;

	private String texto;

	private Color bordeActivo;

	private Color bordeInactivo;

	private String placeholder;

	private int angulo;

	private Color placeholderColor;

	private int numeroEspacios;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private boolean entro;

	private int numeroSpaces;

	private int sumarRestarLineaPlaceHolder;

	public void setSumarRestarLineaPlaceHolder(int sumarRestarLineaPlaceHolder) {

		this.sumarRestarLineaPlaceHolder = sumarRestarLineaPlaceHolder;

	}

	public String getTexto() {

		return texto;

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

		return this.numeroEspacios;

	}

	public void setNumeroEspacios(int numeroEspacios) {

		this.numeroEspacios = numeroEspacios;

		repaint();

	}

	public int getAngulo() {

		return this.angulo;

	}

	public void setAngulo(int angulo) {

		if (angulo < 0)

			angulo = 0;

		this.angulo = angulo;

		repaint();

	}

	public String getPlaceholder() {

		return this.placeholder;

	}

	public void setPlaceholder(String placeholder) {

		placeholder = placeholder.trim();

		this.placeholder = placeholder;

		repaint();

	}

	public Color getBordeActivo() {

		return this.bordeActivo;

	}

	public void setBordeActivo(Color bordeActivo) {

		this.bordeActivo = bordeActivo;

		repaint();

	}

	public Color getBordeInactivo() {

		return this.bordeInactivo;

	}

	public void setBordeInactivo(Color bordeInactivo) {

		this.bordeInactivo = bordeInactivo;

		repaint();

	}

	public int getGrosor() {

		return this.grosor;

	}

	public void setGrosor(int grosor) {

		if (grosor < 1)

			grosor = 1;

		this.grosor = grosor;

		repaint();

	}

	public BootsTextField() {

		this("");

	}

	public Color getPlaceholderColor() {

		return this.placeholderColor;

	}

	public void setPlaceholderColor(Color placeholderColor) {

		this.placeholderColor = placeholderColor;

		repaint();

	}

	public BootsTextField(String text) {

		this.placeholderColor = Color.BLACK;

		this.numeroEspacios = 2;

		this.fuentePlaceholder = new Font("Dialog", 0, 20);

		this.bordeActivo = Color.BLACK;

		this.bordeInactivo = Color.BLACK;

		this.angulo = 20;

		this.placeholder = "";

		this.texto = "";

		this.grosor = 1;

		setBorder((Border) null);

		setFont(getFont().deriveFont(0, 20.0F));

		setText(text);

		addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				if (isEditable() && !JMthos.tieneCaracterNoImprimible(e.getKeyCode())) {

					if (e.getKeyCode() == 8 && !texto.equals(JMthos.generarEspacios(numeroEspacios))) {

						texto = Optional.<String>ofNullable(texto).filter(s -> (s.length() != 0))
								.map(s -> s.substring(0, s.length() - 1)).orElse(texto);

					}

					else if (e.getKeyCode() != 8) {

						texto = String.valueOf(texto) + e.getKeyChar();

					}

					setText(texto);

				}

			}

		});

		addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

				pintarBorde = true;

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				pintarBorde = false;

				repaint();

			}

		});

		DefaultContextMenu.addDefaultContextMenu(this);

	}

	public Font getFuentePlaceholder() {

		return this.fuentePlaceholder;

	}

	public void setFuentePlaceholder(Font fuentePlaceholder) {

		this.fuentePlaceholder = fuentePlaceholder;

		repaint();

	}

	public void paint(Graphics g) {

		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setStroke(new BasicStroke(grosor));

		switch (grosor) {

		case 1:

			g.drawRoundRect(0, 10, getWidth() - 1, getHeight() - 1 - 10, angulo, angulo);

			break;

		case 2:

			g.drawRoundRect(1, 10, getWidth() - grosor, getHeight() - 1 - 10, angulo, angulo);

			break;

		default:

			if (grosor % 2 == 0) {

				g.drawRoundRect((grosor / 2) - 1, 10, (getWidth() - grosor) + 1, getHeight() - 2 - 10, angulo, angulo);

			}

			else {

				g.drawRoundRect((grosor / 2), 10, (getWidth() - grosor) + 1, getHeight() - 1 - 10, angulo, angulo);

			}

			break;

		}

		if (this.pintarBorde) {

			if (entro) {

				texto = "";

			}

			setText(" " + texto);

			setEnabled(true);

			g.setColor(bordeActivo);

			if (!placeholder.isEmpty()) {

				int calculo = 0;

				if (placeholder.length() < 6) {

					if (placeholder.length() == 1) {

						calculo = angulo + (placeholder.length() * 16);

					}

					else {

						calculo = angulo + (placeholder.length() * 13);

					}

				}

				else {

					if (placeholder.length() < 11) {

						calculo = angulo + (placeholder.length() * 12);

					}

					else if (placeholder.length() < 17) {

						calculo = angulo + Math.round(placeholder.length() * 11.5f);

					}

					else {

						calculo = angulo + Math.round(placeholder.length() * 11.5f);

					}

					numeroSpaces = JMthos.contarCaracter(placeholder, " ");

					if (numeroSpaces > 1) {

						if (numeroSpaces == 2) {

							calculo -= 5;

						}

						else {

							calculo -= JMthos.calcularSucesionAritmeticaAInt("3#30,4#35", numeroSpaces);

						}

					}

				}

				calculo += sumarRestarLineaPlaceHolder;

				g.setColor(getBackground());

				g.drawLine(angulo, 10, calculo, 10);

				g.setColor(placeholderColor);

				g.setFont(fuentePlaceholder);

				g.drawString(placeholder, angulo + 2, 15);

			}

			entro = false;

		}

		else {

			if (getText().trim().isEmpty()) {

				entro = true;

				texto = "";

				setText(" " + placeholder);

			}

			else if (!entro) {

				texto = getText().trim();

				texto = texto.trim();

				setText(" " + texto);

			}

			setEnabled(false);

			g.setColor(this.bordeInactivo);

		}

	}

}
