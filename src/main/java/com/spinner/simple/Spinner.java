package com.spinner.simple;

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

@SuppressWarnings("serial")

public class Spinner extends JSpinner {

	protected boolean negativo;

	protected int numeroValor;

	protected int minValor;

	protected int maxValor;

	protected int incremento;

	private SpinnerUI.Editor editor;

	private Font ffuente;

	private Color fondo;

	private Color buttonBackground;

	private Color selectedColor;

	private Color buttonColor;

	private Color colorTexto;

	private String text;

	private Color bk;

	private Color fg;

	private Color border;

	private Font fuente;

	private boolean valorMaximo;

	private boolean valorMinimo;

	private SpinnerUI editor2;

	public Color getFondo() {

		return fondo;

	}

	public void setFondo(Color fondo) {

		this.fondo = fondo;

		repaint();

	}

	public Color getButtonBackground() {

		return buttonBackground;

	}

	public void setButtonBackground(Color buttonBackground) {

		this.buttonBackground = buttonBackground;

		repaint();

	}

	public Color getSelectedColor() {

		return selectedColor;

	}

	public void setSelectedColor(Color selectedColor) {

		this.selectedColor = selectedColor;

		repaint();

	}

	public Color getButtonColor() {

		return buttonColor;

	}

	public void setButtonColor(Color buttonColor) {

		this.buttonColor = buttonColor;

		repaint();

	}

	public Color getColorTexto() {

		return colorTexto;

	}

	public void setColorTexto(Color colorTexto) {

		this.colorTexto = colorTexto;

		repaint();
	}

	public boolean isValorMinimo() {

		return valorMinimo;

	}

	public void setValorMinimo(boolean valorMinimo) {

		this.valorMinimo = valorMinimo;

		editor2.setValorMinimo(valorMinimo);

	}

	public void setValorMaximo(boolean valorMaximo) {

		this.valorMaximo = valorMaximo;

		editor2.setValorMaximo(valorMaximo);

	}

	public boolean isValorMaximo() {

		return valorMaximo;

	}

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

	@Override
	public void setFont(Font font) {

		try {

			editor.setFont(font);

		}

		catch (Exception e) {

		}

	}

	public int getValor() {

		int resultado;

		editor = (SpinnerUI.Editor) getEditor();

		try {

			resultado = Integer.parseInt(editor.getText());

		}

		catch (Exception e) {

			editor.setText(Integer.toString(minValor));

			numeroValor = minValor;

			resultado = minValor;

		}

		return resultado;

	}

	public void setValor(int numeroValor) {

		if (valorMaximo) {

			if ((this.minValor != this.maxValor && this.minValor < this.maxValor
					&& (numeroValor >= this.minValor && numeroValor <= this.maxValor))
					|| (this.minValor == 0 && this.maxValor == 0)) {

				editor.setText(Integer.toString(numeroValor));

			}

			else {

				editor.setText(Integer.toString(this.minValor));

			}

		}

		else {

			editor.setText(Integer.toString(numeroValor));

		}

	}

	public void setIncremento(int incremento) {

		editor.incremento = incremento;

		this.incremento = incremento;

	}

	public int getMinValor() {

		return this.minValor;

	}

	public int getMaxValor() {

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

	public Spinner(Font font, Color foreground, Color background, Color buttonBackground, Color buttonColor,
			Color selectedColor) {

		valorMaximo = true;

		valorMinimo = true;

		this.negativo = false;

		this.incremento = 1;

		this.minValor = 0;

		this.maxValor = 100;

		if (font == null) {

			font = new Font("Dialog", Font.PLAIN, 20);

		}

		this.ffuente = font;

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

		setOpaque(false);

		editor2 = new SpinnerUI(true, ffuente, foreground, background, buttonBackground, selectedColor, buttonColor,
				negativo, incremento, minValor, maxValor, valorMaximo, valorMinimo);

		setUI(editor2);

		ponerConstructor();

		setValor(minValor);

		sumarAlto(15);

	}

	public Spinner(Font font) {

		valorMaximo = true;

		valorMinimo = true;

		buttonBackground = Color.decode("#e7e7e7");

		colorTexto = Color.BLACK;

		selectedColor = new Color(181, 181, 181);

		this.negativo = false;

		this.incremento = 1;

		this.minValor = 0;

		this.maxValor = 100;

		if (ffuente == null) {

			ffuente = new Font("Dialog", Font.PLAIN, 20);

		}

		this.ffuente = font;

		setOpaque(false);

		editor2 = new SpinnerUI(true, ffuente, colorTexto, fondo, buttonBackground, selectedColor, buttonColor,
				negativo, incremento, minValor, maxValor, valorMaximo, valorMinimo);

		setUI(editor2);

		ponerConstructor();

		setValor(minValor);

		sumarAlto(getFont().getSize());

	}

	public Spinner(int min, int max, int incremento) {

		valorMaximo = true;

		valorMinimo = true;

		this.negativo = false;

		this.minValor = min;

		this.maxValor = max;

		this.incremento = incremento;

		ffuente = new Font("Dialog", Font.PLAIN, 20);

		setOpaque(false);

		buttonBackground = Color.decode("#e7e7e7");

		colorTexto = Color.BLACK;

		selectedColor = new Color(181, 181, 181);

		editor2 = new SpinnerUI(true, ffuente, colorTexto, fondo, buttonBackground, selectedColor, buttonColor,
				negativo, incremento, minValor, maxValor, valorMaximo, valorMinimo);

		setUI(editor2);

		ponerConstructor();

		setValor(minValor);

		sumarAlto(getFont().getSize());

	}

	public Spinner(int min, int max, boolean negativo) {

		valorMaximo = true;

		valorMinimo = true;

		this.negativo = negativo;

		this.minValor = min;

		this.maxValor = max;

		this.incremento = 1;

		ffuente = new Font("Dialog", Font.PLAIN, 20);

		setOpaque(false);

		buttonBackground = Color.decode("#e7e7e7");

		colorTexto = Color.BLACK;

		selectedColor = new Color(181, 181, 181);

		editor2 = new SpinnerUI(true, ffuente, colorTexto, fondo, buttonBackground, selectedColor, buttonColor,
				negativo, incremento, minValor, maxValor, valorMaximo, valorMinimo);

		setUI(editor2);

		ponerConstructor();

		setValor(minValor);

		sumarAlto(getFont().getSize());

	}

	public Spinner(int min, int max) {

		valorMaximo = true;

		valorMinimo = true;

		this.minValor = min;

		this.maxValor = max;

		this.incremento = 1;

		this.negativo = false;

		ffuente = new Font("Dialog", Font.PLAIN, 20);

		setOpaque(false);

		buttonBackground = Color.decode("#e7e7e7");

		colorTexto = Color.BLACK;

		selectedColor = new Color(181, 181, 181);

		editor2 = new SpinnerUI(true, ffuente, colorTexto, fondo, buttonBackground, selectedColor, buttonColor,
				negativo, incremento, minValor, maxValor, valorMaximo, valorMinimo);

		setUI(editor2);

		ponerConstructor();

		setValor(minValor);

		sumarAlto(getFont().getSize());

	}

	public Spinner(int min, int max, boolean negativo, int incremento) {

		valorMaximo = true;

		valorMinimo = true;

		this.minValor = min;

		this.maxValor = max;

		this.negativo = negativo;

		this.incremento = incremento;

		ffuente = new Font("Dialog", Font.PLAIN, 20);

		setOpaque(false);

		buttonBackground = Color.decode("#e7e7e7");

		colorTexto = Color.BLACK;

		selectedColor = new Color(181, 181, 181);

		editor2 = new SpinnerUI(true, ffuente, colorTexto, fondo, buttonBackground, selectedColor, buttonColor,
				negativo, incremento, minValor, maxValor, valorMaximo, valorMinimo);

		setUI(editor2);

		ponerConstructor();

		setValor(minValor);

		sumarAlto(getFont().getSize());

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

	public void setHeaderFont(Font font) {

		try {

			editor.setHeaderFont(font);

		}

		catch (Exception e) {

		}

	}

	public Spinner(String text) {

		this();

		setLabelText(text);

	}

	public Spinner() {

		valorMaximo = true;

		valorMinimo = true;

		this.negativo = false;

		this.incremento = 1;

		this.minValor = 0;

		this.maxValor = 100;

		ffuente = new Font("Dialog", Font.PLAIN, 20);

		setOpaque(false);

		buttonBackground = Color.decode("#e7e7e7");

		colorTexto = Color.BLACK;

		selectedColor = new Color(181, 181, 181);

		editor2 = new SpinnerUI(true, ffuente, colorTexto, fondo, buttonBackground, selectedColor, buttonColor,
				negativo, incremento, minValor, maxValor, valorMaximo, valorMinimo);

		setUI(editor2);

		ponerConstructor();

		setMaxValor(maxValor);

		setMinValor(minValor);

		setValor(minValor);

		sumarAlto(20);

	}

	private void mover(boolean mover) {

		ponerValores();

		editor.setText(Integer.toString(numeroValor));

		if (numeroValor < editor.min) {

			numeroValor = editor.min;

		}

		if (numeroValor > editor.max) {

			numeroValor = editor.max;

		}

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

		editor.setText(Integer.toString(numeroValor));

	}

	private void ponerConstructor() {

		editor = (SpinnerUI.Editor) getEditor();

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

						if (valorMaximo && maxValor != 0 && numeroValor > maxValor) {

							numeroValor = editor.max;

						}

					}

					else {

						numeroValor -= editor.incremento;

						if (valorMinimo && numeroValor < minValor) {

							numeroValor = minValor;

						}

					}

					editor.setText(Integer.toString(numeroValor));

				}

				catch (Exception e1) {

					numeroValor = editor.min;

					editor.setText(Integer.toString(editor.min));

				}

			}

		});
	}

	public void setMostrarUi(boolean mostrarUi) {

		setOpaque(false);

		editor2 = new SpinnerUI(true, ffuente, colorTexto, fondo, buttonBackground, selectedColor, buttonColor,
				negativo, incremento, minValor, maxValor, valorMaximo, valorMinimo);

		setUI(editor2);

		ponerConstructor();

	}

	private void ponerValores() {

		maxValor = editor.max;

		minValor = editor.min;

		incremento = editor.incremento;

		negativo = editor.negativo;

		numeroValor = getValor();

	}

	public void ponerFiltro() {

		try {

			ponerValores();

			if (numeroValor > maxValor || numeroValor < minValor || editor.getText().matches("0[0-9]*")) {

				try {

					numeroValor = Integer.parseInt(editor.getText());

				}

				catch (Exception e) {

					numeroValor = minValor;

				}

			}

			else {

				numeroValor = Integer.parseInt(editor.getText());

				if (maxValor != 0 && numeroValor > maxValor) {

					numeroValor = maxValor;

				}

				if (numeroValor < minValor) {

					numeroValor = minValor;

				}

			}

			editor.setText(Integer.toString(numeroValor));

		}

		catch (Exception e) {

			numeroValor = minValor;

			editor.setText(Integer.toString(numeroValor));

		}

	}

	public void setMinValor(int minValue) {

		minValor = minValue;

		editor.min = minValue;

		if (getValor() < minValor) {

			editor.setText(Integer.toString(minValor));

		}

	}

	public void setMaxValor(int maxValue) {

		editor.max = maxValue;

		maxValor = maxValue;

	}

}
