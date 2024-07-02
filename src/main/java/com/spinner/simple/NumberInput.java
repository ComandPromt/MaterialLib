package com.spinner.simple;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import com.textField.text.NTextField;

@SuppressWarnings("serial")

public class NumberInput extends JPanel {

	private NTextField textField;

	private int minValor;

	private int numeroValor;

	private int incremento;

	private JSlider slider;

	public void setFont(Font font) {

		try {

			textField.setFont(font);

		}

		catch (Exception e) {

		}

	}

	public void setIncremento(int incremento) {

		this.incremento = incremento;

	}

	public void setMinValor(int minValor) {

		this.minValor = minValor;

	}

	public NumberInput() {

		this(0, 100);

	}

	private int getValor() {

		int resultado;

		try {

			resultado = Integer.parseInt(textField.getText());

		}

		catch (Exception e) {

			numeroValor = minValor;

			resultado = minValor;

		}

		return resultado;

	}

	public String getText() {

		try {

			return textField.getText();

		}

		catch (Exception e) {

			return String.valueOf(minValor);

		}

	}

	public void setHorizontalAlignment(int number) {

		try {

			textField.setHorizontalAlignment(number);
		}

		catch (Exception e) {

		}

	}

	public int getIncremento() {

		return incremento;

	}

	public void setBackground(Color bg) {

		try {

			textField.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public NumberInput(int minValor, int maxValor) {

		this.minValor = minValor;

		incremento = 1;

		setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();

		add(panel);

		panel.setLayout(new GridLayout(0, 1, 0, 0));

		textField = new NTextField();

		textField.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				try {

					numeroValor = getValor();

					if (e.getWheelRotation() < 0) {

						numeroValor -= incremento;

						if (numeroValor < minValor) {

							numeroValor = minValor;

						}

					}

					else {

						numeroValor += incremento;

						if (maxValor != 0 && numeroValor > maxValor) {

							numeroValor = maxValor;

						}

					}

					textField.setText(Integer.toString(numeroValor));

				}

				catch (Exception e1) {

					numeroValor = minValor;

					textField.setText(Integer.toString(minValor));

				}

			}

		});

		textField.setText(String.valueOf(minValor));

		textField.setHorizontalAlignment(SwingConstants.CENTER);

		textField.addKeyListener(new KeyAdapter() {

			@Override

			public void keyReleased(KeyEvent e) {

				try {

					if (Integer.parseInt(textField.getText()) >= minValor) {

						textField.setText(textField.getText());

						ponerValorSlider(Integer.parseInt(textField.getText()), e);

					}

					else {

						textField.setText(String.valueOf(minValor));

						ponerValorSlider(minValor, e);

					}

				}

				catch (Exception e1) {

					textField.setText(String.valueOf(minValor));

					ponerValorSlider(minValor, e);

				}

			}

		});

		panel.add(textField);

		textField.setColumns(10);

	}

	private void ponerValorSlider(int valor, KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			slider.setValue(valor);

		}

	}

	public NumberInput(int minValor, int maxValor, JSlider slider) {

		this.slider = slider;

		this.minValor = minValor;

		incremento = 1;

		setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();

		add(panel);

		panel.setLayout(new GridLayout(0, 1, 0, 0));

		textField = new NTextField();

		textField.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				try {

					numeroValor = getValor();

					if (e.getWheelRotation() < 0) {

						numeroValor += incremento;

						if (maxValor != 0 && numeroValor > maxValor) {

							numeroValor = maxValor;

						}

					}

					else {

						numeroValor -= incremento;

						if (numeroValor < minValor) {

							numeroValor = minValor;

						}

					}

					textField.setText(Integer.toString(numeroValor));

					slider.setValue(numeroValor);

				}

				catch (Exception e1) {

					numeroValor = minValor;

					slider.setValue(numeroValor);

					textField.setText(Integer.toString(minValor));

				}

			}

		});

		textField.setText(String.valueOf(minValor));

		textField.setHorizontalAlignment(SwingConstants.CENTER);

		textField.addKeyListener(new KeyAdapter() {

			@Override

			public void keyReleased(KeyEvent e) {

				try {

					if (Integer.parseInt(textField.getText()) >= minValor) {

						textField.setText(textField.getText());

					}

					else {

						textField.setText(String.valueOf(minValor));

					}

				}

				catch (Exception e1) {

					textField.setText(String.valueOf(minValor));

				}

			}

		});

		slider.setValue(minValor);

		panel.add(textField);

		textField.setColumns(10);

	}

	public void setValor(int value) {

		textField.setText(Integer.toString(value));

	}

}
