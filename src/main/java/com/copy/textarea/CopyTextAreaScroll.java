package com.copy.textarea;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.buttons.simple.SimpleButton;
import com.spinner.simple.Spinner;

import textarea.NTextArea;
import util.Mthos;

@SuppressWarnings("serial")

public class CopyTextAreaScroll extends JPanel {

	private JPanel panel;

	private NTextArea textarea;

	private Spinner spinner;

	public NTextArea getTextarea() {

		return textarea;

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

		this.panel.setBackground(Color.WHITE);

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

		} catch (Exception e) {

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

	public CopyTextAreaScroll() {

		spinner = new Spinner();

		spinner.getEditor().addMouseListener(new MouseAdapter() {
			@Override

			public void mousePressed(MouseEvent e) {

				cambiarLetra();

			}

		});

		spinner.setBounds(1, 0, 53, 50);

		spinner.setMinValor(16);

		spinner.setMaxValor(200);

		spinner.setNegativo(false);

		spinner.setValor(12);

		this.panel = new JPanel();

		panel.setBounds(0, 0, 56, 300);

		this.panel.setBorder(new LineBorder(new Color(0, 0, 0)));

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

		disminuir.setBounds(1, 62, 53, 36);

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

		panel.setLayout(null);

		panel.add(spinner);

		disminuir.setIcon(new ImageIcon(CopyTextAreaScroll.class.getResource("/imgs/imagenes/zoom-out.png")));

		this.panel.add(disminuir);

		SimpleButton aumentar = new SimpleButton("");

		aumentar.setBounds(1, 108, 53, 36);

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

		aumentar.setIcon(new ImageIcon(CopyTextAreaScroll.class.getResource("/imgs/imagenes/zoom-in.png")));

		this.panel.add(aumentar);

		SimpleButton restaurar = new SimpleButton("");

		restaurar.setBounds(1, 156, 53, 36);

		restaurar.setBorderColor(Color.WHITE);

		restaurar.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				resetear();

			}

		});

		restaurar.setIcon(new ImageIcon(CopyTextAreaScroll.class.getResource("/imgs/imagenes/actualizar.png")));

		this.panel.add(restaurar);

		SimpleButton copiar = new SimpleButton("");

		copiar.setBounds(1, 204, 53, 36);

		copiar.setBorderColor(Color.WHITE);

		copiar.addMouseWheelListener(new MouseWheelListener() {

			@Override

			public void mouseWheelMoved(MouseWheelEvent e) {

				Mthos.copy(textarea.getTextArea().getText());

			}

		});

		copiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Mthos.copy(textarea.getTextArea().getText());

			}

		});

		copiar.setIcon(new ImageIcon(CopyTextAreaScroll.class.getResource("/imgs/imagenes/copy.png")));

		panel.add(copiar);

		SimpleButton limpiar = new SimpleButton("");

		limpiar.setBounds(1, 252, 53, 36);

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

		limpiar.setIcon(new ImageIcon(CopyTextAreaScroll.class.getResource("/imgs/imagenes/clean.png")));

		panel.add(limpiar);

		textarea = new NTextArea("");

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

		textarea.setBounds(56, 0, 394, 300);

		add(textarea);

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