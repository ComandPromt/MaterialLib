package com.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.buttons.round.RoundedButton;

@SuppressWarnings("serial")

public class RoundButtonPanelWithIndicator extends JPanel {

	private ArrayList<RoundedButton> buttons;

	private Color foregroundColor;

	private Color backgroundButtonColor;

	private int iluminacion;

	private JPanel panel1 = new JPanel();

	private JPanel2 panel2 = new JPanel2();

	private Font font;

	private Color indicador;

	public void setBackgroundLabel(Color color) {

		panel2.setBackground(color);

		iluminar(iluminacion);

	}

	public void setFontSize(float size) {

		font = getFont().deriveFont(size);

		setFont(font);

	}

	public void setHorizontalTextPosition(int alginment) {

		for (RoundedButton boton : buttons) {

			boton.setHorizontalAlignment(alginment);

		}

	}

	@Override
	public Font getFont() {

		return font;

	}

	public void setBorderButton(Color color) {

		for (RoundedButton boton : buttons) {

			boton.setBorderColor(color);

		}

	}

	@Override
	public void setFont(Font font) {

		this.font = font;

		if (buttons != null) {

			for (RoundedButton boton : buttons) {

				boton.setFont(font);

			}

		}

	}

	public void setColorLabel(Color color) {

		try {

			indicador = color;

			panel2.setColor(color);

			iluminar(iluminacion);

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

			for (RoundedButton boton : buttons) {

				boton.setBackground(color);

			}

		}

		catch (Exception e) {

		}

	}

	public void setBackgroundButton(Color color) {

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

		if (panel2.getWidth() > 0) {

			if (index >= buttons.size() || index < 0) {

				index = 0;

			}

			if (iluminacion != index) {

				iluminacion = index;

			}

			if (buttons.get(index).getWidth() > 0) {

				iluminacion = index;

				try {

					panel2.setAncho(buttons.get(iluminacion).getWidth());

					panel2.setSize(panel1.getWidth(), panel1.getHeight() / 2);

					panel2.setEquis(buttons.get(iluminacion).getX());

					panel2.setColor(indicador);

					panel2.repaint();

				}

				catch (Exception e) {

				}

			}

		}

	}

	public RoundButtonPanelWithIndicator(String text) {

		try {

			String[] items = text.split(",");

			addComponentListener(new ComponentAdapter() {

				@Override
				public void componentResized(ComponentEvent e) {

					panel2.setAncho(buttons.get(0).getWidth());

					panel2.setSize(getWidth(), panel1.getHeight() / 2);

					iluminar(0);

				}

			});

			iluminacion = 0;

			foregroundColor = Color.BLACK;

			backgroundButtonColor = Color.WHITE;

			buttons = new ArrayList<>();

			add(panel1);

			add(panel2);

			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			panel1.setBounds(0, 0, 400, 50);

			panel2.setBounds(0, 49, 400, 15);

			panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

			panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

			indicador = Color.GREEN;

			panel2.setColor(indicador);

			panel2.setBackground(Color.WHITE);

			RoundedButton boton;

			for (int indice = 0; indice < items.length; indice++) {

				boton = new RoundedButton(items[indice]);

				boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

				boton.setFocusable(false);

				boton.setBackground(backgroundButtonColor);

				boton.setForeground(foregroundColor);

				buttons.add(boton);

				panel1.add(boton);

				ponerIndice(buttons.get(indice), indice);

			}

			panel2.setBackground(Color.WHITE);

			setHorizontalTextPosition(SwingConstants.LEFT);

			panel2.repaint();

		}

		catch (Exception e) {

		}

	}

	public RoundButtonPanelWithIndicator(List<String> items) {

		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {

				panel2.setAncho(buttons.get(0).getWidth());

				panel2.setSize(getWidth(), panel1.getHeight() / 2);

				iluminar(0);

			}

		});

		iluminacion = 0;

		foregroundColor = Color.BLACK;

		backgroundButtonColor = Color.WHITE;

		buttons = new ArrayList<>();

		add(panel1);

		add(panel2);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		panel1.setBounds(0, 0, 400, 50);

		panel2.setBounds(0, 49, 400, 15);

		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

		indicador = Color.GREEN;

		panel2.setColor(indicador);

		panel2.setBackground(Color.WHITE);

		RoundedButton boton;

		for (int indice = 0; indice < items.size(); indice++) {

			boton = new RoundedButton(items.get(indice));

			boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

			boton.setFocusable(false);

			boton.setBackground(backgroundButtonColor);

			boton.setForeground(foregroundColor);

			buttons.add(boton);

			panel1.add(boton);

			ponerIndice(buttons.get(indice), indice);

		}

		panel2.setBackground(Color.WHITE);

		setHorizontalTextPosition(SwingConstants.LEFT);

		panel2.repaint();

	}

	public RoundButtonPanelWithIndicator(String[] items) {

		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {

				panel2.setAncho(buttons.get(0).getWidth());

				panel2.setSize(getWidth(), panel1.getHeight() / 2);

				iluminar(0);

			}

		});

		iluminacion = 0;

		foregroundColor = Color.BLACK;

		backgroundButtonColor = Color.WHITE;

		buttons = new ArrayList<>();

		add(panel1);

		add(panel2);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		panel1.setBounds(0, 0, 400, 50);

		panel2.setBounds(0, 49, 400, 15);

		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

		indicador = Color.GREEN;

		panel2.setColor(indicador);

		panel2.setBackground(Color.WHITE);

		RoundedButton boton;

		for (int indice = 0; indice < items.length; indice++) {

			boton = new RoundedButton(items[indice]);

			boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

			boton.setFocusable(false);

			boton.setBackground(backgroundButtonColor);

			boton.setForeground(foregroundColor);

			buttons.add(boton);

			panel1.add(boton);

			ponerIndice(buttons.get(indice), indice);

		}

		panel2.setBackground(Color.WHITE);

		setHorizontalTextPosition(SwingConstants.LEFT);

		panel2.repaint();

	}

	private void ponerIndice(RoundedButton boton, int index) {

		boton.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				iluminacion = index;

				iluminar(index);

			}

		});

	}

}
