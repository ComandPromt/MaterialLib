package com.spinner.decimal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JSpinner;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;

import com.toolTip.ToolTipLlamada;

import mthos.JMthos;

@SuppressWarnings("serial")

public class DecimalSpinner extends JSpinner {

	protected boolean negativo;

	protected float numeroValor;

	protected float minValor;

	protected float maxValor;

	protected float incremento;

	private DecimalSpinnerUI.Editor editor;

	private int decimals;

	private DecimalSpinnerUI spinnerui;

	private Color fondo;

	private Color buttonBackground;

	private Color selectedColor;

	private Color buttonColor;

	private Color colorTexto;

	private Font font;

	private String text;

	private Color bk;

	private Color fg;

	private Color border;

	private Font fuente;

	@Override
	public void setToolTipText(String text) {

		setToolTip(text, null, null, null, null);

	}

	public void setHeaderFont(Font font) {

		try {

			editor.setHeaderFont(font);

		}

		catch (Exception e) {

		}

	}

	public void setToolTip(String text, Color background, Color foreground, Color border, Font font) {

		calcularTooltip(text, background, foreground, border, font);

	}

	public void sumarAlto(int alto) {

		try {

			editor.setSumarAlto(alto);

		}

		catch (Exception e) {

		}

	}

	public void setCenterText(boolean center) {

		try {

			editor.setCenterText(center);

		}

		catch (Exception e) {

		}

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

		this.bk = background;

		this.fg = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || bk == null || fg == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, bk, fg, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public float getDecimals() {

		return decimals;

	}

	public void setDecimals(int decimals) {

		if (decimals < 0) {

			decimals = 0;

		}

		this.decimals = decimals;

	}

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

		if (decimals == 0) {

			editor.setText(Float.toString(numeroValor));

		}

		else {

			editor.setText(Float.toString(JMthos.truncateFloat(numeroValor, decimals)));

		}

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

		editor.setHeaderText(text);

	}

	public String getLabelText() {

		return editor.getLabelText();

	}

	public void setMostrarUi(boolean mostrarUi) {

		setOpaque(false);

		setUI(new DecimalSpinnerUI(font, colorTexto, fondo, buttonBackground, selectedColor, buttonColor, mostrarUi,
				negativo, incremento, minValor, maxValor, decimals));

		ponerConstructor();

	}

	public DecimalSpinner(Font font) {

		buttonBackground = Color.decode("#e7e7e7");

		colorTexto = Color.BLACK;

		selectedColor = new Color(181, 181, 181);

		this.decimals = 2;

		this.negativo = false;

		this.incremento = 1;

		this.minValor = 0;

		this.maxValor = 0;

		setOpaque(false);

		if (font == null) {

			font = new Font("Dialog", Font.PLAIN, 20);
		}

		this.font = font;

		spinnerui = new DecimalSpinnerUI(font, colorTexto, fondo, buttonBackground, selectedColor, buttonColor, true,
				negativo, incremento, minValor, maxValor, decimals);

		setUI(spinnerui);

		ponerConstructor();

		setValor(numeroValor);

		sumarAlto(15);

	}

	public DecimalSpinner(String text) {

		this();

		setLabelText(text);

	}

	public DecimalSpinner() {

		buttonBackground = Color.decode("#e7e7e7");

		colorTexto = Color.BLACK;

		selectedColor = new Color(181, 181, 181);

		this.decimals = 2;

		this.negativo = false;

		this.incremento = 1;

		this.minValor = 0;

		this.maxValor = 0;

		setOpaque(false);

		font = new Font("Dialog", Font.PLAIN, 20);

		spinnerui = new DecimalSpinnerUI(font, colorTexto, fondo, buttonBackground, selectedColor, buttonColor, true,
				negativo, incremento, minValor, maxValor, decimals);

		setUI(spinnerui);

		ponerConstructor();

		setValor(numeroValor);

		sumarAlto(15);

	}

	public DecimalSpinner(int decimals, Font font) {

		buttonBackground = Color.decode("#e7e7e7");

		colorTexto = Color.BLACK;

		selectedColor = new Color(181, 181, 181);

		this.decimals = decimals;

		this.negativo = false;

		this.incremento = 1;

		this.minValor = 0;

		this.maxValor = 0;

		setOpaque(false);

		if (font == null) {

			font = new Font("Dialog", Font.PLAIN, 20);

		}

		this.font = font;

		spinnerui = new DecimalSpinnerUI(font, colorTexto, fondo, buttonBackground, selectedColor, buttonColor, true,
				negativo, incremento, minValor, maxValor, decimals);

		setUI(spinnerui);

		ponerConstructor();

		setValor(numeroValor);

		sumarAlto(15);

	}

	public DecimalSpinner(int decimals) {

		buttonBackground = Color.decode("#e7e7e7");

		colorTexto = Color.BLACK;

		selectedColor = new Color(181, 181, 181);

		this.decimals = decimals;

		this.negativo = false;

		this.incremento = 1;

		this.minValor = 0;

		this.maxValor = 0;

		font = new Font("Dialog", Font.PLAIN, 20);

		setOpaque(false);

		spinnerui = new DecimalSpinnerUI(font, colorTexto, fondo, buttonBackground, selectedColor, buttonColor, true,
				negativo, incremento, minValor, maxValor, decimals);

		setUI(spinnerui);

		ponerConstructor();

		setValor(numeroValor);

		sumarAlto(15);

	}

	public DecimalSpinner(int decimals, Font font, Color foreground, Color background, Color buttonBackground,
			Color buttonColor, Color selectedColor) {

		if (font == null) {

			font = new Font("Dialog", Font.PLAIN, 20);
		}

		this.font = font;

		if (background == null) {

			background = Color.WHITE;

		}

		if (buttonBackground == null) {

			buttonBackground = Color.decode("#e7e7e7");

		}

		if (foreground == null) {

			foreground = Color.BLACK;

		}

		if (selectedColor == null) {

			selectedColor = new Color(181, 181, 181);

		}

		this.colorTexto = foreground;

		this.buttonColor = buttonColor;

		this.selectedColor = selectedColor;

		this.buttonBackground = buttonBackground;

		this.fondo = background;

		this.decimals = decimals;

		this.negativo = false;

		this.incremento = 1;

		this.minValor = 0;

		this.maxValor = 0;

		setOpaque(false);

		spinnerui = new DecimalSpinnerUI(font, foreground, background, buttonBackground, selectedColor, buttonColor,
				true, negativo, incremento, minValor, maxValor, decimals);

		setUI(spinnerui);

		ponerConstructor();

		setValor(numeroValor);

		sumarAlto(15);

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

		editor.setHeaderText("");

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

					setValor(numeroValor);

				}

				catch (Exception e1) {

					numeroValor = editor.min;

					editor.setText(Float.toString(editor.min));

				}

			}

		});
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
