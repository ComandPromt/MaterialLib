package com.spinner.decimal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSpinnerUI;

import com.textField.text.NTextField;

import mthos.JMthos;

class DecimalSpinnerUI extends BasicSpinnerUI {

	private Editor editor;

	private float min;

	private float max;

	private float incremento;

	private boolean negativo;

	private boolean mostrarUi;

	private float numeroValor;

	private int decimals;

	private Editor edicion;

	private ArrowButton next;

	private ArrowButton back;

	private Color background;

	private Color buttonBackground;

	private Color selectedColor;

	private Color buttonColor;

	private Color foreground;

	private Font font;

	public DecimalSpinnerUI(Font font, Color foreground, Color background, Color buttonBackground, Color selectedColor,
			Color buttonColor, boolean mostrarUi, boolean negativo, float incremento, float minValor, float maxValor,
			int decimals) {

		if (font == null) {

			font = new Font("Dialog", Font.PLAIN, 20);

		}

		this.font = font;

		if (foreground == null) {

			foreground = Color.BLACK;

		}

		this.foreground = foreground;

		this.buttonColor = buttonColor;

		this.selectedColor = selectedColor;

		if (buttonBackground == null) {

			buttonBackground = Color.WHITE;

		}

		this.buttonBackground = buttonBackground;

		if (background == null) {

			background = Color.WHITE;

		}

		this.background = background;

		this.decimals = decimals;

		this.mostrarUi = mostrarUi;

		this.negativo = negativo;

		this.incremento = incremento;

		this.min = minValor;

		this.max = maxValor;

	}

	@Override

	public void installUI(JComponent jc) {

		super.installUI(jc);

		try {

			edicion = new Editor(spinner, font, foreground, background, min, max, negativo, incremento, mostrarUi);

			spinner.setEditor(edicion);

		}

		catch (Exception e) {

		}

		editor = (Editor) spinner.getEditor();

	}

	private void setValor(int decimals) {

		if (decimals == 0) {

			editor.setText(Float.toString(numeroValor));

		}

		else {

			editor.setText(Float.toString(JMthos.truncateFloat(numeroValor, decimals)));

		}

	}

	private float incrementar(float incremento, boolean b) {

		float num = Float.parseFloat(editor.getText());

		if (b) {

			return num + incremento;

		} else {

			return num - incremento;

		}

	}

	@Override

	protected Component createNextButton() {

		if (mostrarUi) {

			next = new ArrowButton(true);

			next.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					try {

						numeroValor = incrementar(editor.incremento, true);

						if (editor.max != 0 && numeroValor > editor.max) {

							numeroValor = editor.max;

						}

						setValor(decimals);

					}

					catch (Exception e1) {

					}

				}

			});

			return next;

		}

		else {

			return null;

		}

	}

	@Override
	protected Component createPreviousButton() {

		if (mostrarUi) {

			back = new ArrowButton(false);

			back.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					try {

						numeroValor = incrementar(editor.incremento, false);

						if (!editor.negativo && numeroValor < editor.min) {

							numeroValor = editor.min;

						}

						setValor(decimals);

					}

					catch (Exception e1) {

					}

				}

			});

			return back;

		}

		else {

			return null;

		}

	}

	@SuppressWarnings("serial")
	public class Editor extends NTextField implements ChangeListener, KeyListener {

		float min;

		float max;

		float incremento;

		boolean negativo;

		boolean mostrarUi;

		public Editor(JSpinner spinner, Font font, Color foreground, Color background, float min, float max,
				boolean negativo, float sumar, boolean mostrarUi) {

			setForeground(foreground);

			setBackground(background);

			spinner.addChangeListener(this);

			setEditable(false);

			if (font == null) {

				font = new Font("Dialog", Font.PLAIN, 20);

			}

			setFont(font);

			this.mostrarUi = mostrarUi;

			this.incremento = sumar;

			this.min = min;

			this.max = max;

			this.negativo = negativo;

			setText(Float.toString(min));

			spinner.addKeyListener(this);

		}

		@Override
		public void stateChanged(ChangeEvent ce) {

			JSpinner spinner = (JSpinner) ce.getSource();

			float comprobacion;

			try {

				comprobacion = Float.parseFloat(spinner.getValue().toString());

				setText(Float.toString(comprobacion));

			}

			catch (Exception e) {

				setText(Float.toString(min));

			}

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

	}

	@SuppressWarnings("serial")

	private class ArrowButton extends JButton {

		private final boolean next;

		public ArrowButton(boolean next) {

			this.next = next;

			setContentAreaFilled(false);

			setFocusable(false);

			setBorder(new EmptyBorder(5, 7, 5, 7));

			setBackground(new Color(231, 231, 231));

			setForeground(new Color(150, 150, 150));

			addMouseListener(new MouseAdapter() {

				@Override

				public void mousePressed(MouseEvent me) {

					setSelected(true);

				}

				@Override

				public void mouseReleased(MouseEvent me) {

					setSelected(false);

				}

			});

		}

		@Override

		public void paint(Graphics grphcs) {

			super.paint(grphcs);

			Graphics2D g2 = (Graphics2D) grphcs;

			if (buttonColor == null) {

				buttonColor = getForeground();

			}

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g2.setColor(buttonBackground);

			g2.fillRoundRect(0, 1, getWidth(), getHeight() - 2, 5, 5);

			if (next) {

				int width = getWidth();

				int height = getHeight();

				int size = 10;

				int x = (width - size) / 2;

				int y = (height - size) / 2;

				int px[] = { x + size / 2, x + size, x };

				int py[] = { y, y + size, y + size };

				if (isSelected()) {

					g2.setColor(selectedColor);

				}

				else {

					g2.setColor(buttonColor);

				}

				g2.fillPolygon(px, py, px.length);

			}

			else {

				int width = getWidth();

				int height = getHeight();

				int size = 10;

				int x = (width - size) / 2;

				int y = (height - size) / 2;

				int px[] = { x, x + size, x + size / 2 };

				int py[] = { y, y, y + size };

				if (isSelected()) {

					g2.setColor(selectedColor);

				}

				else {

					g2.setColor(buttonColor);

				}

				g2.fillPolygon(px, py, px.length);

			}

			g2.dispose();

		}

	}

}
