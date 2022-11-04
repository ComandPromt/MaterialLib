package spinner;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JSpinner;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")

public class Spinner extends JSpinner {

	protected boolean negativo;

	protected int numeroValor;

	protected int minValor;

	protected int maxValor;

	protected boolean mostrarUi;

	protected int incremento;

	SpinnerUI.Editor editor;

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

		editor.setText(Integer.toString(numeroValor));

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

		editor.setLabelText(text);

	}

	public String getLabelText() {

		return editor.getLabelText();

	}

	public Spinner() {

		this.mostrarUi = true;

		this.negativo = false;

		this.incremento = 1;

		this.minValor = 0;

		this.maxValor = 0;

		setOpaque(false);

		setUI(new SpinnerUI(mostrarUi, negativo, incremento, minValor, maxValor));

		ponerConstructor();

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

					int notches = e.getWheelRotation();

					numeroValor = getValor();

					if (notches < 0) {

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

				catch (Exception e1) {

					numeroValor = editor.min;

					editor.setText(Integer.toString(editor.min));

				}

			}

		});
	}

	public Spinner(boolean showUI, boolean editable, boolean negativo, int min, int max, int incremento) {

		this.mostrarUi = showUI;

		this.negativo = negativo;

		this.incremento = incremento;

		this.minValor = min;

		this.maxValor = max;

		setOpaque(false);

		setUI(new SpinnerUI(mostrarUi, negativo, incremento, minValor, maxValor));

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

		editor.setText(Integer.toString(minValue));

	}

	public void setMaxValor(int maxValue) {

		editor.max = maxValue;

		maxValor = maxValue;

		editor.setText(Integer.toString(maxValue));

	}

}
