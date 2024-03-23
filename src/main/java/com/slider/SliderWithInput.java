package com.slider;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.buttons.round.NButton;
import com.textarea.NumberInput;

@SuppressWarnings("serial")

public class SliderWithInput extends JPanel {

	private JPanel panel;

	private NumberInput textArea;

	private NButton btnNewButton;

	private int defaultValor;

	public void setEffectColor(Color bg) {

		try {

			btnNewButton.setEffectColor(bg);

		}

		catch (Exception e) {

		}

	}

	public void setIncremento(int incremento) {

		try {

			textArea.setIncremento(incremento);

		}

		catch (Exception e) {

		}

	}

	public SliderWithInput() {

		this(0, 100, 1);

	}

	@Override
	public void setBackground(Color bg) {

		try {

			panel.setBackground(bg);

			textArea.setBackground(bg);

			btnNewButton.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public void setDefaultValor(int defaultValor) {

		this.defaultValor = defaultValor;

	}

	public SliderWithInput(int minValor, int maxValor, int incremento) {

		setLayout(new GridLayout(0, 1, 0, 0));

		defaultValor = minValor;

		panel = new JPanel();

		panel.setBackground(Color.WHITE);

		add(panel);

		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JSliderCustom lblNewLabel = new JSliderCustom();

		lblNewLabel.setMinimum(minValor);

		lblNewLabel.setMaximum(maxValor);

		textArea = new NumberInput(minValor, maxValor, lblNewLabel);

		lblNewLabel.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				if (e.getWheelRotation() < 0) {

					lblNewLabel.setValue(lblNewLabel.getValue() + textArea.getIncremento());

				}

				else {

					lblNewLabel.setValue(lblNewLabel.getValue() - textArea.getIncremento());

				}

			}

		});

		panel.add(lblNewLabel);

		btnNewButton = new NButton("");

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					lblNewLabel.setValue(defaultValor);

				}

				catch (Exception e1) {

				}

			}

		});

		textArea.setIncremento(incremento);

		btnNewButton.setBackground(Color.WHITE);

		lblNewLabel.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {

				textArea.setValor(lblNewLabel.getValue());

			}

		});

		panel.add(textArea);

		btnNewButton.setIcon(new ImageIcon(SliderWithInput.class.getResource("/imgs/imagenes/actualizar.png")));

		panel.add(btnNewButton);

	}

	public SliderWithInput(int minValor, int maxValor) {

		this(minValor, maxValor, 1);

	}

}
