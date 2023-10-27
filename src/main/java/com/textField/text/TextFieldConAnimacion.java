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

import com.contextmenu.DefaultContextMenu;
import com.toolTip.ToolTipLlamada;

import mthos.JMthos;

@SuppressWarnings("serial")
public class TextFieldConAnimacion extends JTextField {

	private boolean pintarBorde;

	private Font fuentePlaceholder;

	private int grosor;

	private String texto;

	private Color bordeActivo;

	private Color bordeInactivo;

	private String placeholder;

	private int angulo;

	private int numeroEspacios;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

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

		return numeroEspacios;

	}

	public void setNumeroEspacios(int numeroEspacios) {

		this.numeroEspacios = numeroEspacios;

		repaint();

	}

	public int getAngulo() {

		return angulo;

	}

	public void setAngulo(int angulo) {

		if (angulo < 0) {

			angulo = 0;

		}

		this.angulo = angulo;

	}

	public String getPlaceholder() {

		return placeholder;

	}

	public void setPlaceholder(String placeholder) {

		this.placeholder = placeholder;

		repaint();

	}

	public Color getBordeActivo() {

		return bordeActivo;

	}

	public void setBordeActivo(Color bordeActivo) {

		this.bordeActivo = bordeActivo;

		repaint();

	}

	public Color getBordeInactivo() {

		return bordeInactivo;

	}

	public void setBordeInactivo(Color bordeInactivo) {

		this.bordeInactivo = bordeInactivo;

		repaint();

	}

	public int getGrosor() {

		return grosor;

	}

	public void setGrosor(int grosor) {

		if (grosor < 0) {

			grosor = 0;

		}

		this.grosor = grosor;

	}

	public TextFieldConAnimacion() {

		this("");

	}

	public TextFieldConAnimacion(String text) {

		numeroEspacios = 2;

		fuentePlaceholder = new Font("Dialog", Font.PLAIN, 20);

		bordeActivo = Color.BLACK;

		bordeInactivo = Color.BLACK;

		angulo = 20;

		placeholder = "";

		texto = "";

		grosor = 1;

		setBorder(null);

		setFont(getFont().deriveFont(Font.PLAIN, 20));

		setText(text);

		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (isEditable() && !JMthos.tieneCaracterNoImprimible(e.getKeyCode())) {

					if (!texto.isEmpty()) {

						if (e.getKeyCode() == 8 && !texto.equals(JMthos.calcularNumeroEspacios(numeroEspacios))) {

							texto = Optional.ofNullable(texto).filter(s -> s.length() != 0)
									.map(s -> s.substring(0, s.length() - 1)).orElse(texto);

						}

						else if (e.getKeyCode() != 8) {

							texto += e.getKeyChar();

						}

					}

					if (texto.isEmpty()) {

						texto = JMthos.calcularNumeroEspacios(numeroEspacios);

					}

					setText(texto);

				}

			}

		});

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {

				pintarBorde = true;

				repaint();

			}

			@Override
			public void mouseExited(MouseEvent e) {

				pintarBorde = false;

				repaint();

			}

		});

		DefaultContextMenu.addDefaultContextMenu(this);

	}

	public Font getFuentePlaceholder() {

		return fuentePlaceholder;

	}

	public void setFuentePlaceholder(Font fuentePlaceholder) {

		this.fuentePlaceholder = fuentePlaceholder;

		repaint();

	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setStroke(new BasicStroke(grosor));

		g.drawRoundRect(0, 10, getWidth() - 1, (getHeight() - 1) - 10, angulo, angulo);

		if (pintarBorde) {

			setText(texto);

			setEnabled(true);

			g.setColor(bordeActivo);

			int calculo = angulo + (Math.round(placeholder.length() * ((fuentePlaceholder.getSize() * 12) / 20)));

			g2.setStroke(new BasicStroke(grosor));

			g.setColor(getBackground());

			g.drawLine(angulo, 10, calculo, 10);

			g2.setStroke(new BasicStroke(grosor));

			g.setColor(Color.RED);

			g.setFont(fuentePlaceholder);

			g.drawString(placeholder, angulo + 2, 15);

			g2.setStroke(new BasicStroke(grosor));

		}

		else {

			setText(" " + placeholder);

			setEnabled(false);

			g.setColor(bordeInactivo);

		}

	}

}
