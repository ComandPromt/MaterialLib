package com.spinner.decimal;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JSpinner;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")

public class DecimalSpinner extends JSpinner {

	protected boolean negativo;

	protected float numeroValor;

	protected float minValor;

	protected float maxValor;

	protected boolean mostrarUi;

	protected float incremento;

	DecimalSpinnerUI.Editor editor;

	public float getValor() {

		float resultado;

		editor = (DecimalSpinnerUI.Editor) getEditor();

		try {

			resultado = Float.parseFloat(editor.getText());

		}

		catch (Exception e) {

			editor.setText("" + minValor);

			numeroValor = minValor;

			resultado = minValor;

		}

		return resultado;

	}

	public void setValor(float numeroValor) {

		editor.setText(Float.toString(numeroValor));

	}

	public void setIncremento(float incremento) {

		editor.incremento = incremento;

		this.incremento = incremento;

	}

	public float getMinValor() {

		return this.minValor;

	}

	public float getMaxValor() {

		return this.maxValor;

	}

	public boolean isNegativo() {

		return this.negativo;

	}

	public void setNegativo(boolean negativo) {

		this.negativo = negativo;

		editor.negativo = negativo;

	}

	public void setLabelText(String text) {

		editor.setLabelText(text);

	}

	public String getLabelText() {

		return editor.getLabelText();

	}

	public DecimalSpinner() {

		this.mostrarUi = true;

		this.negativo = false;

		this.incremento = 1;

		this.minValor = 0;

		this.maxValor = 0;

		setOpaque(false);

		setUI(new DecimalSpinnerUI(mostrarUi, negativo, incremento, minValor, maxValor));

		ponerConstructor();

	}

	private void mover(boolean mover) {

		ponerValores();

		editor.setText(Float.toString(numeroValor));

		if (mover) {

			numeroValor += editor.incremento;

			if (editor.max != 0 && numeroValor > editor.max) {

				numeroValor = editor.max;

			}

		}

		else {

			numeroValor -= editor.incremento;

			if (!editor.negativo && numeroValor < editor.min) {

				numeroValor = editor.min;

			}

		}

		editor.setText(Float.toString(numeroValor));

	}

	private void ponerConstructor() {

		editor = (DecimalSpinnerUI.Editor) getEditor();

		editor.min = 0;

		editor.max = 0;

		editor.incremento = incremento;

		editor.negativo = negativo;

		editor.setHorizontalAlignment(SwingConstants.CENTER);

		editor.setEditable(true);

		editor.setLabelText("");

		editor.addKeyListener(new KeyAdapter() {

			@Override

			public void keyReleased(KeyEvent e) {

				switch (e.getKeyCode()) {

				case KeyEvent.VK_UP:

					mover(true);

					break;

				case KeyEvent.VK_DOWN:

					mover(false);

					break;

				default:

					ponerFiltro();

					break;

				}

			}

		});

		this.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

				try {

					ponerValores();

					numeroValor = getValor();

					if (e.getWheelRotation() < 0) {

						numeroValor += editor.incremento;

						if (editor.max != 0 && numeroValor > editor.max) {

							numeroValor = editor.max;

						}

					}

					else {

						numeroValor -= editor.incremento;

						if (!editor.negativo && numeroValor < editor.min) {

							numeroValor = editor.min;

						}

					}

					editor.setText(Float.toString(numeroValor));

				}

				catch (Exception e1) {

					numeroValor = editor.min;

					editor.setText(Float.toString(editor.min));

				}

			}

		});
	}

	public DecimalSpinner(boolean showUI, boolean editable, boolean negativo, float min, float max, float incremento) {

		this.mostrarUi = showUI;

		this.negativo = negativo;

		this.incremento = incremento;

		this.minValor = min;

		this.maxValor = max;

		setOpaque(false);

		setUI(new DecimalSpinnerUI(mostrarUi, negativo, incremento, minValor, maxValor));

		ponerConstructor();

	}

	private void ponerValores() {

		maxValor = editor.max;

		minValor = editor.min;

		incremento = editor.incremento;

		negativo = editor.negativo;

	}

	public void ponerFiltro() {

		try {

			ponerValores();

			if (numeroValor > editor.max || numeroValor < editor.min || editor.getText().matches("0[0-9]*")) {

				try {

					numeroValor = Float.parseFloat(editor.getText());

				}

				catch (Exception e) {

					numeroValor = editor.min;

				}

			}

			else {

				numeroValor = Float.parseFloat(editor.getText());

				if (editor.max != 0 && numeroValor > editor.max) {

					numeroValor = editor.max;

				}

				if (numeroValor < editor.min) {

					numeroValor = editor.min;

				}

			}

			editor.setText(Float.toString(numeroValor));

		}

		catch (Exception e) {

			numeroValor = editor.min;

			editor.setText(Float.toString(numeroValor));

		}

	}

	public void setMinValor(float minValue) {

		minValor = minValue;

		editor.min = minValue;

		editor.setText("" + minValue);

	}

	public void setMaxValor(float maxValue) {

		editor.max = maxValue;

		maxValor = maxValue;

		editor.setText("" + maxValue);

	}

}
