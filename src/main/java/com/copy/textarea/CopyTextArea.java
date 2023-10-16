package com.copy.textarea;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.border.LineBorder;

import com.buttons.simple.SimpleButton;
import com.spinner.simple.Spinner;
import com.textarea.NTextArea;
import com.toolTip.ToolTipLlamada;

import mthos.JMthos;

@SuppressWarnings("serial")

public class CopyTextArea extends JPanel {

	private JPanel panel;

	private NTextArea textarea;

	private Spinner spinner;

	private String text;

	private Color fondo;

	private Color colorDeTexto;

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

				font = getFont().deriveFont(14f);

			}

			catch (Exception e) {

				font = new Font("Dialog", Font.PLAIN, 14);

			}

		}

		this.text = text;

		this.fondo = background;

		this.colorDeTexto = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || fondo == null || colorDeTexto == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, colorDeTexto, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	@Override
	public void setForeground(Color fg) {

		try {

			textarea.getTextArea().setForeground(fg);

		}

		catch (Exception e) {

		}

	}

	public void setEditable(boolean editable) {

		textarea.getTextArea().setEditable(editable);

	}

	public void setMaximumSize(int maximumSize) {

		this.spinner.setMaxValor(maximumSize);

	}

	public void setMinimumSize(int minimumSize) {

		this.spinner.setMinValor(minimumSize);

	}

	public void setPanelLeftColor(Color color) {

		this.panel.setBackground(color);

	}

	public String getText() {

		return textarea.getTextArea().getText();

	}

	public void setText(String text) {

		try {

			textarea.setText(text);
		}

		catch (Exception e) {

		}

	}

	public void setLabelText(String text) {

		try {

			textarea.setHeader(text);

		}

		catch (Exception e) {

		}

	}

	private void aumentar() {

		spinner.setValor(spinner.getValor() + 1);

		textarea.getTextArea().setFont(getFont().deriveFont((float) spinner.getValor()));

	}

	private void rueda(MouseWheelEvent e) {

		if (e.getWheelRotation() < 0) {

			aumentar();

		}

		else {

			disminuir();

		}

	}

	private void disminuir() {

		spinner.setValor(spinner.getValor() - 1);

		textarea.getTextArea().setFont(getFont().deriveFont((float) spinner.getValor()));

	}

	public void setBorderColor(Color color) {

		this.panel.setBorder(new LineBorder(color));

	}

	public void setFont(Font font) {

		try {

			spinner.setValor(font.getSize());

			textarea.getTextArea().setFont(font);

		}

		catch (Exception e) {

		}

	}

	private void resetear() {

		spinner.setValor(16);

		textarea.getTextArea().setFont(textarea.getTextArea().getFont().deriveFont(16f));

	}

	private void cambiarLetra() {

		try {

			textarea.getTextArea().setFont(getFont().deriveFont((float) spinner.getValor()));

		}

		catch (Exception e1) {

		}

	}

	public void setMinValor(int valor) {

		try {

			spinner.setMinValor(valor);

		}

		catch (Exception e) {

		}

	}

	public void setMaxValor(int valor) {

		try {

			spinner.setMaxValor(valor);

		}

		catch (Exception e) {

		}

	}

	public void setBorde(Color color) {

		setBorder(new LineBorder(color));

		panel.setBorder(new LineBorder(color));

	}

	public void setFontSize(int size) {

		spinner.setValor(size);

	}

	public void setMinFontSize(int size) {

		spinner.setMinValor(size);

	}

	public void setMaxFontSize(int size) {

		spinner.setMaxValor(size);

	}

	public void setNegativo(boolean negativo) {

		spinner.setNegativo(negativo);

	}

	public void setSelectionColor(Color color) {

		try {

			textarea.setSelectionColor(color);

		}

		catch (Exception e) {

		}

	}

	public CopyTextArea() {

		setBorder(new LineBorder(Color.BLACK));

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				super.componentResized(e);

				if (getHeight() % 6 != 0) {

					setSize(getWidth(), saberAltura(getHeight()));

				}

				panel.setSize(panel.getWidth(), getHeight());

				textarea.setSize(getWidth() - (panel.getWidth()), (getHeight() + 20) - 4);

			}

			private int saberAltura(int height) {

				for (int i = 6; i > 0; i--) {

					height--;

					if (height % 6 == 0) {

						i = 0;

						height += 6;

					}

				}

				return height;

			}

		});

		spinner = new Spinner();

		spinner.setFont(new Font("Dialog", Font.PLAIN, 22));

		spinner.getEditor().addMouseListener(new MouseAdapter() {
			@Override

			public void mousePressed(MouseEvent e) {

				cambiarLetra();

			}

		});

		spinner.setMinValor(30);

		spinner.setMaxValor(200);

		spinner.setNegativo(false);

		spinner.setValor(12);

		this.panel = new JPanel();

		panel.setBounds(0, 0, 56, 299);

		this.panel.setBorder(new LineBorder(Color.BLACK));

		this.panel.addMouseWheelListener(new MouseWheelListener() {

			@Override

			public void mouseWheelMoved(MouseWheelEvent e) {

				rueda(e);

			}

		});

		setLayout(null);

		this.panel.setBackground(Color.WHITE);

		add(this.panel);

		SimpleButton disminuir = new SimpleButton("");

		disminuir.setBorderColor(Color.WHITE);

		disminuir.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

				disminuir();

			}

		});

		disminuir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				disminuir();

			}

		});

		spinner.getEditor().addKeyListener(new KeyAdapter() {

			@Override

			public void keyReleased(KeyEvent e) {

				cambiarLetra();

			}

		});

		panel.setLayout(new GridLayout(0, 1, 0, 0));

		panel.add(spinner);

		disminuir.setIcon(new ImageIcon(CopyTextArea.class.getResource("/imgs/imagenes/zoom-out.png")));

		this.panel.add(disminuir);

		SimpleButton aumentar = new SimpleButton("");

		aumentar.setBorderColor(Color.WHITE);

		aumentar.addMouseWheelListener(new MouseWheelListener() {

			@Override

			public void mouseWheelMoved(MouseWheelEvent e) {

				aumentar();

			}

		});

		aumentar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				aumentar();

			}

		});

		aumentar.setIcon(new ImageIcon(CopyTextArea.class.getResource("/imgs/imagenes/zoom-in.png")));

		this.panel.add(aumentar);

		SimpleButton restaurar = new SimpleButton("");

		restaurar.setBorderColor(Color.WHITE);

		restaurar.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				resetear();

			}

		});

		restaurar.setIcon(new ImageIcon(CopyTextArea.class.getResource("/imgs/imagenes/actualizar.png")));

		this.panel.add(restaurar);

		SimpleButton copiar = new SimpleButton("");

		copiar.setBorderColor(Color.WHITE);

		copiar.addMouseWheelListener(new MouseWheelListener() {

			@Override

			public void mouseWheelMoved(MouseWheelEvent e) {

				JMthos.copy(textarea.getTextArea().getText());

			}

		});

		copiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JMthos.copy(textarea.getTextArea().getText());

			}

		});

		copiar.setIcon(new ImageIcon(CopyTextArea.class.getResource("/imgs/imagenes/copy.png")));

		panel.add(copiar);

		SimpleButton limpiar = new SimpleButton("");

		limpiar.setBorderColor(Color.WHITE);

		limpiar.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

				textarea.setText("");

			}

		});

		limpiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				textarea.setText("");

			}

		});

		limpiar.setIcon(new ImageIcon(CopyTextArea.class.getResource("/imgs/imagenes/clean.png")));

		panel.add(limpiar);

		textarea = new NTextArea("");

		textarea.setSelectionColor(new Color(223, 223, 223));

		textarea.setOpaque(true);

		textarea.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseEntered(MouseEvent e) {

				cambiarLetra();

			}

		});

		textarea.getTextArea().addMouseListener(new MouseAdapter() {

			@Override

			public void mouseEntered(MouseEvent e) {

				cambiarLetra();

			}

			@Override

			public void mousePressed(MouseEvent e) {

				cambiarLetra();

			}

		});

		textarea.setBounds(56, -19, 353, 318);

		textarea.setForeground(Color.WHITE);

		add(textarea);

		setForeground(Color.BLACK);

	}

	public void setHeader(Color color) {

		try {

			textarea.setHeader(color);

		}

		catch (Exception e) {

		}

	}

	public void setHeaderFont(Font font) {

		try {

			textarea.setHeaderFont(font);

		}

		catch (Exception e) {

		}

	}

}