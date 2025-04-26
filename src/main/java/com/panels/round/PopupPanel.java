package com.panels.round;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.border.LineBorder;

import com.buttons.simple.MetroButton;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class PopupPanel extends JPanel {

	JDialog dialogo;

	MetroButton btnNewButton;

	private JPanel cabecera;

	private JComponent panel;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private int lastX;

	private int lastY;

	private int grosorBorde;

	private JPanel cuerpo;

	private JPanel blanco;

	@Override

	public void setBackground(Color bg) {

		super.setBackground(bg);

		try {

			cabecera.setBackground(bg);

			btnNewButton.setBackground(bg);

			panel.setBackground(bg);

			cuerpo.setBackground(bg);

			blanco.setBackground(bg);

		}

		catch (Exception e) {

		}

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

		this.fondo = background;

		this.colorTexto = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || fondo == null || colorTexto == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, colorTexto, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	/**
	 * @wbp.parser.constructor
	 */
	public PopupPanel(JDialog dialogo, JComponent panel, int width, int height) {

		this(dialogo, panel, width, height, 1, Color.BLACK);

	}

	public PopupPanel(JDialog dialogo, JComponent panel, int width, int height, int grosor, Color borde) {

		if (grosor < 1) {

			grosor = 1;

		}

		grosorBorde = grosor;

		if (borde == null) {

			borde = Color.BLACK;

		}

		setBorder(new LineBorder(borde, grosor));

		btnNewButton = new MetroButton("X");

		btnNewButton.setBackground(Color.WHITE);

		btnNewButton.setFont(getFont().deriveFont(30f));

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				btnNewButton.setBounds(dialogo.getWidth() - 25 - grosorBorde, grosorBorde, 25, 25);

			}

		});

		this.dialogo = dialogo;

		setOpaque(false);

		btnNewButton.setSelectedIcon(null);

		btnNewButton.setBackground(Color.WHITE);

		btnNewButton.setIcon(null);

		btnNewButton.setBounds(dialogo.getWidth() - 25, 7, 25, 25);

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				dialogo.dispose();

			}

		});

		setLayout(null);

		add(btnNewButton);

		this.panel = panel;

		cabecera = new JPanel();

		cabecera.setBounds(grosor, grosor, width - 25, 32);

		blanco = new JPanel();

		blanco.setBounds(width - 25, 20, 25 - grosor, 25);

		blanco.setBackground(Color.WHITE);

		cabecera.setBackground(Color.WHITE);

		add(blanco);

		add(cabecera);

		cuerpo = new JPanel();

		cuerpo.setLayout(new GridLayout());

		cuerpo.setBounds(grosor, 32, width - (grosor * 2), (height - 32) - grosor);

		cuerpo.add(panel);

		add(cuerpo);

		addMouseMotionListener(new MouseAdapter() {

			@Override

			public void mouseDragged(MouseEvent e) {

				dialogo.setLocation(e.getXOnScreen() - lastX, e.getYOnScreen() - lastY);

			}

		});

	}

}
