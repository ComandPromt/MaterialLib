package com.cards;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class SimpleCard extends JPanel {

	JLabel panel1;

	JLabel panel2;

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

				font = getFont().deriveFont(14f);

			}

			catch (Exception e) {

				font = new Font("Dialog", Font.PLAIN, 14);

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

	public void setHeaderText(String text) {

		try {

			this.panel1.setText(text);

		}

		catch (Exception e) {

		}

	}

	public void setText(String text) {

		try {

			panel2.setText(text);

		}

		catch (Exception e) {

		}

	}

	public void setHeaderFont(Font font) {

		try {

			this.panel1.setFont(font);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setFont(Font font) {

		try {

			this.panel2.setFont(font);

		}

		catch (Exception e) {

		}

	}

	public void setHeaderColor(Color color) {

		try {

			panel1.setBackground(color);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setBackground(Color bg) {

		try {

			panel2.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public void setHeaderForeground(Color fg) {

		try {

			panel1.setForeground(fg);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setForeground(Color fg) {

		try {

			panel2.setForeground(fg);

		}

		catch (Exception e) {

		}

	}

	public SimpleCard(String header, String text) {

		if (header == null) {

			header = "";

		}

		if (text == null) {

			text = "";

		}

		panel1 = new JLabel(header);

		panel2 = new JLabel(text);

		panel1.setOpaque(true);

		panel2.setOpaque(true);

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				panel1.setSize(getWidth(), (int) Math.round(getHeight() * 0.16));

				panel2.setSize(getWidth(), (int) Math.round(getHeight() * 0.85));

			}

		});

		setLayout(null);

		panel1.setHorizontalAlignment(SwingConstants.CENTER);

		panel1.setBounds(0, 0, 451, 54);

		panel1.setFont(new Font("Dialog", Font.PLAIN, 30));

		add(panel1);

		panel2.setFont(new Font("Dialog", Font.PLAIN, 30));

		panel2.setHorizontalAlignment(SwingConstants.CENTER);

		panel2.setBounds(0, 55, 451, 247);

		add(panel2);

		setHeaderColor(new Color(0, 204, 106));

		setHeaderForeground(Color.WHITE);

		setBackground(Color.WHITE);

	}

}
