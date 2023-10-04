package com.checkbox;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;

import com.buttons.simple.MetroButton;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class PanelRedondo extends JPanel {

	private int radius = 20;

	JDialog dialogo;

	MetroButton btnNewButton;

	private String text;

	private Color background;

	private Color foreground;

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

		this.background = background;

		this.foreground = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || background == null || foreground == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, background, foreground, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public PanelRedondo(JDialog dialogo, Component panel, int width, int height) {

		btnNewButton = new MetroButton("X");

		btnNewButton.setFont(getFont().deriveFont(30f));

		btnNewButton.setOpaque(false);

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				btnNewButton.setBounds(dialogo.getWidth() - 25, 7, 25, 25);

			}

		});

		setBackground(Color.WHITE);

		this.dialogo = dialogo;

		setOpaque(false);

		setBorder(new EmptyBorder(10, 10, 10, 10));

		btnNewButton.setSelectedIcon(null);

		btnNewButton.setBackground(Color.WHITE);

		btnNewButton.setIcon(null);

		btnNewButton.setBounds(275, 0, 40, 40);

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				dialogo.dispose();

			}

		});

		setLayout(null);

		add(btnNewButton);

		panel.setBounds(0, 0, width, height);

		add(panel);

	}

	public void setRadius(int radius) {

		this.radius = radius;

		setBorder(new EmptyBorder(radius / 2, radius / 2, radius / 2, radius / 2));

		revalidate();

		repaint();

	}

	public int getRadius() {

		return radius;

	}

}
