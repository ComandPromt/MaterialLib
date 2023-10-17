package com.cards;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;

import com.panels.round.RoundedPanel;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class RoundSimpleCard extends JPanel {

	private RoundedPanel panel1;

	private RoundedPanel panel2;

	private JLabel cabecera;

	private JLabel texto;

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

	public void setThickness(int thickness) {

		try {

			panel1.setThickness(thickness);

			panel2.setThickness(thickness);

		}

		catch (Exception e) {

		}

	}

	public void setRadius(int radius) {

		try {

			panel1.setRadius(radius);

			panel2.setRadius(radius);

		}

		catch (Exception e) {

		}

	}

	public void setHeaderBackground(Color color) {

		try {

			panel1.setBackground(color);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setBackground(Color color) {

		try {

			panel2.setBackground(color);

		}

		catch (Exception e) {

		}

	}

	public void setHeaderBorderColor(Color color) {

		try {

			panel1.setForeground(color);

		}

		catch (Exception e) {

		}

	}

	public void setBorderColor(Color color) {

		try {

			panel2.setForeground(color);

		}

		catch (Exception e) {

		}

	}

	public void setHeaderForeground(Color fg) {

		try {

			cabecera.setForeground(fg);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setForeground(Color fg) {

		try {

			texto.setForeground(fg);

		}

		catch (Exception e) {

		}

	}

	public void setHeaderText(String text) {

		try {

			cabecera.setText(text);

		}

		catch (Exception e) {

		}

	}

	public void setText(String text) {

		try {

			texto.setText(text);

		}

		catch (Exception e) {

		}

	}

	public void setHeaderFont(Font font) {

		try {

			cabecera.setFont(font);

			repaint();

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setFont(Font font) {

		try {

			texto.setFont(font);

			repaint();

		}

		catch (Exception e) {

		}

	}

	public RoundSimpleCard(String header, String text) {

		if (header == null) {

			header = "";

		}

		if (text == null) {

			text = "";

		}

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				panel1.setSize(getWidth(), (int) Math.round(getHeight() * 0.19));

				panel2.setBounds(0, (int) Math.round(getHeight() * 0.19), getWidth(),
						(int) Math.round(getHeight() * 0.80));

			}

		});

		setBackground(Color.WHITE);

		setOpaque(false);

		setBorder(null);

		setLayout(null);

		panel1 = new RoundedPanel();

		panel1.setForeground(Color.BLACK);

		panel1.setBackground(Color.ORANGE);

		panel1.setBounds(0, 0, 450, 65);

		add(panel1);

		panel1.setLayout(new GridLayout(0, 1, 0, 0));

		cabecera = new JLabel(header);

		cabecera.setFont(new Font("Dialog", Font.PLAIN, 25));

		cabecera.setHorizontalAlignment(SwingConstants.CENTER);

		panel1.add(cabecera);

		panel2 = new RoundedPanel();

		panel2.setForeground(Color.BLACK);

		panel2.setBackground(Color.PINK);

		panel2.setBounds(0, 40, 450, 235);

		add(panel2);

		panel2.setLayout(new GridLayout(1, 0, 0, 0));

		texto = new JLabel(text);

		texto.setFont(new Font("Dialog", Font.PLAIN, 25));

		texto.setHorizontalAlignment(SwingConstants.CENTER);

		panel2.add(texto);

	}

}
