package com.spinner.simple;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.contextmenu.DefaultContextMenu;

import mthos.JMthos;

@SuppressWarnings("serial")
public class SimpleSpinner extends JTextField {

	private int valor;

	private int min;

	private int max;

	public int getValor() {

		return valor;

	}

	public int getMin() {

		return min;

	}

	public int getMax() {

		return max;

	}

	public void setValor(int valor) {

		setText(Integer.toString(valor));

	}

	@Override
	public void setText(String t) {

		try {

			valor = Integer.parseInt(t);

			if (valor >= min && valor <= max) {

				super.setText(t);

			}

			else {

				super.setText(Integer.toString(min));

			}

		}

		catch (Exception e) {

			super.setText(Integer.toString(min));

		}

	}

	public SimpleSpinner() {

		this(0, 100);

	}

	public SimpleSpinner(int min, int max) {

		setHorizontalAlignment(SwingConstants.CENTER);

		this.min = min;

		this.max = max;

		addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {

				try {

					valor = Integer.parseInt(getText());

					if (valor >= min && valor <= max) {

						setText(getText());

					}

					else {

						setText(Integer.toString(min));

					}

				}

				catch (Exception e1) {

					setText(Integer.toString(min));

				}

			}

		});

		addMouseWheelListener(new MouseWheelListener() {

			@Override

			public void mouseWheelMoved(MouseWheelEvent e) {

				try {

					valor = Integer.parseInt(getText());

					if (JMthos.scrollHaciaAbajo(e)) {

						if (valor < max) {

							valor++;

						}

						else {

							valor = max;

						}

					}

					else {

						valor--;

						if (valor < min) {

							valor = min;

						}

					}

					setText(Integer.toString(valor));

				}

				catch (Exception e1) {

					setText(Integer.toString(min));

				}

			}

		});

		setText(Integer.toString(min));

		DefaultContextMenu.addDefaultContextMenu(this);

	}

}
