package com.panels.indicator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;

import com.buttons.round.RoundedButton;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

class SliderButtonPanel extends JPanel {

	private ArrayList<RoundedButton> buttons;

	private ArrayList<Label> labels;

	private Color foregroundColor;

	private Color labelBackgroundColor;

	private Color backgroundButtonColor;

	private Color labelColor;

	private int indice;

	private int iluminacion;

	private JPanel panel1;

	private JPanel panel2;

	private Font font;

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

	public void setHorizontalAlignment(int alginment) {

		for (int i = 0; i < buttons.size(); i++) {

			buttons.get(i).setHorizontalAlignment(alginment);

		}
	}

	public ArrayList<Label> getLabels() {

		return labels;

	}

	public void setLabels(ArrayList<Label> labels) {

		this.labels = labels;

	}

	@Override
	public Font getFont() {

		return font;

	}

	public void setFontSize(int size) {

		for (int i = 0; i < buttons.size(); i++) {

			buttons.get(i).getFont().deriveFont(size);

		}

	}

	public void setButtonBorder(Color color) {

		for (int i = 0; i < buttons.size(); i++) {

			buttons.get(i).setBorderColor(color);

		}

	}

	@Override
	public void setFont(Font font) {

		this.font = font;

		if (buttons != null) {

			for (int i = 0; i < buttons.size(); i++) {

				buttons.get(i).setFont(font);

			}

		}

	}

	public void setBackgroundLabel(Color color) {

		try {

			labelColor = color;

			int contador = 0;

			for (Label label : labels) {

				if (iluminacion == contador) {

					label.setBackground(color);

				}

				else {

					label.setBackground(labelBackgroundColor);

				}

				contador++;

			}

		}

		catch (Exception e) {

		}

	}

	public int getIluminacion() {

		return iluminacion;

	}

	public ArrayList<RoundedButton> getButtonRoundedWithImages() {

		return buttons;

	}

	public void setButtonRoundedWithImages(ArrayList<RoundedButton> buttons) {

		this.buttons = buttons;

	}

	@Override
	public void setForeground(Color color) {

		try {

			this.foregroundColor = color;

			for (RoundedButton boton : buttons) {

				boton.setForeground(color);

			}

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setBackground(Color color) {

		try {

			panel1.setBackground(color);

			for (int i = 0; i < buttons.size(); i++) {

				buttons.get(i).setBackground(color);
			}

		}

		catch (Exception e) {

		}

	}

	public void setButtonBackground(Color color) {

		try {

			this.backgroundButtonColor = color;

			for (RoundedButton boton : buttons) {

				boton.setBackground(color);

			}

		}

		catch (Exception e) {
		}

	}

	private void iluminar(int index) {

		iluminacion = index;

		for (int i = 0; i < labels.size(); i++) {

			if (i == index) {

				labels.get(i).setBackground(labelColor);

			}

			else {

				labels.get(i).setBackground(labelBackgroundColor);
			}

		}

	}

	public SliderButtonPanel(String[] items, Color backgroundButton, Color inner, Color external,
			Color labelBackground) {

		panel1 = new JPanel();

		panel2 = new JPanel();

		Label label;

		indice = 0;

		if (labelBackground == null) {

			labelBackground = Color.WHITE;

		}

		this.labelBackgroundColor = labelBackground;

		iluminacion = 0;

		foregroundColor = Color.BLACK;

		backgroundButtonColor = Color.WHITE;

		buttons = new ArrayList<>();

		labels = new ArrayList<>();

		add(panel1);

		add(panel2);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		panel1.setBounds(0, 0, 400, 50);

		panel2.setBounds(0, 49, 400, 15);

		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

		panel2.setBackground(labelBackgroundColor);

		labelColor = Color.GREEN;

		RoundedButton boton;

		for (indice = 0; indice < items.length; indice++) {

			boton = new RoundedButton(items[indice]);

			boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

			boton.setFocusable(false);

			boton.setBackground(backgroundButtonColor);

			boton.setForeground(foregroundColor);

			boton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16));

			buttons.add(boton);

			label = new Label("");

			if (indice == 0) {

				label.setBackground(labelColor);

			}

			else {

				label.setBackground(labelBackgroundColor);

			}

			labels.add(label);

			panel1.add(boton);

			panel2.add(label);

			ponerIndice(buttons.get(indice), indice);

		}

		setHorizontalAlignment(SwingConstants.LEFT);

	}

	private void ponerIndice(RoundedButton boton, int index) {

		boton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				iluminar(index);

			}

		});

	}

}
