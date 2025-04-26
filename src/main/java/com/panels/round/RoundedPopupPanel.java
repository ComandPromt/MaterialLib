package com.panels.round;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;

import com.buttons.simple.MetroButton;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class RoundedPopupPanel extends JPanel {

	private MetroButton btnNewButton;

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

	private int cornerRadius;

	@Override
	public void setBackground(Color bg) {

		super.setBackground(bg);

		try {

			cabecera.setBackground(bg);

			btnNewButton.setColorPressed(bg);

			btnNewButton.setColorHover(bg);

			btnNewButton.setColorNormal(bg);

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

	public RoundedPopupPanel(JDialog dialogo, String title, ImageIcon icon, JComponent panel, int width, int height) {

		this(dialogo, panel, title, icon, Color.BLACK, width, height, 1, 25);

	}

	public RoundedPopupPanel(JDialog dialogo, JComponent panel, String title, ImageIcon icono, Color borde, int width,
			int height, int thickness, int radius) {

		if (borde == null) {

			borde = Color.BLACK;

		}

		border = borde;

		if (radius < 0) {

			radius = 25;

		}

		cornerRadius = radius;

		if (thickness < 1) {

			thickness = 1;

		}

		grosorBorde = thickness;

		setOpaque(false);

		btnNewButton = new MetroButton("X");

		btnNewButton.setBackground(Color.WHITE);

		btnNewButton.setFont(getFont().deriveFont(30f));

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				int buttonSize = 25;

				int xPos = getWidth() - buttonSize - grosorBorde - (cornerRadius / 2);

				int yPos = grosorBorde;

				btnNewButton.setBounds(xPos, yPos + 3, buttonSize, buttonSize);

			}

		});

		btnNewButton.setSelectedIcon(null);

		btnNewButton.setIcon(null);

		btnNewButton.setBounds(getWidth() - 25 - grosorBorde - (cornerRadius / 2), grosorBorde, 25, 25);

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				dialogo.dispose();

			}

		});

		setLayout(null);

		add(btnNewButton);

		this.panel = panel;

		cabecera = new JPanel();

		cabecera.setLayout(null);

		JLabel icon = new JLabel();

		icon.setIcon(icono);

		JLabel label = new JLabel(title);

		label.setFont(getFont().deriveFont(20f));

		cabecera.add(icon);

		cabecera.add(label);

		icon.setBounds(5, 2, width, 32);

		cabecera.setBounds(0, 0, width, 32);

		label.setBounds((width / 2) - label.getText().length() * 5, 0, width - 25, 32);

		cabecera.setOpaque(false);

		blanco = new JPanel();

		cuerpo = new JPanel();

		cuerpo.setLayout(new GridLayout());

		setBackground(Color.WHITE);

		cuerpo.setBackground(Color.WHITE);

		cuerpo.add(panel);

		if (radius < 74) {

			cuerpo.setBounds((grosorBorde) + 8, 32, (width - (grosorBorde * 2)) - 15, height - 52);

			blanco.setBounds(width - 25, 20, 25 - grosorBorde * 4, 25);

		}

		else {

			if (radius > 90) {

				if (radius > 197) {

					cuerpo.setBounds((grosorBorde) + 60, 35, (width - (grosorBorde * 10)) - 80, height - 52);

					blanco.setBounds(width - 25, 20, 25 - grosorBorde * 9, 25);

				}

				else if (radius > 180) {

					cuerpo.setBounds((grosorBorde) + 57, 35, (width - (grosorBorde * 10)) - 75, height - 52);

					blanco.setBounds(width - 25, 20, 25 - grosorBorde * 9, 25);

				}

				else if (radius > 167) {

					cuerpo.setBounds((grosorBorde) + 45, 32, (width - (grosorBorde * 10)) - 50, height - 52);

					blanco.setBounds(width - 25, 20, 25 - grosorBorde * 9, 25);

				}

				else if (radius > 153) {

					cuerpo.setBounds((grosorBorde) + 40, 32, (width - (grosorBorde * 10)) - 40, height - 52);

					blanco.setBounds(width - 25, 20, 25 - grosorBorde * 9, 25);

				}

				else if (radius > 149) {

					cuerpo.setBounds((grosorBorde) + 35, 32, (width - (grosorBorde * 10)) - 40, height - 52);

					blanco.setBounds(width - 25, 20, 25 - grosorBorde * 9, 25);

				}

				else if (radius > 139) {

					cuerpo.setBounds((grosorBorde) + 35, 32, (width - (grosorBorde * 10)) - 30, height - 52);

					blanco.setBounds(width - 25, 20, 25 - grosorBorde * 9, 25);

				}

				else if (radius > 120) {

					cuerpo.setBounds((grosorBorde) + 30, 32, (width - (grosorBorde * 10)) - 20, height - 52);

					blanco.setBounds(width - 25, 20, 25 - grosorBorde * 9, 25);

				}

				else if (radius > 114) {

					cuerpo.setBounds((grosorBorde) + 25, 32, (width - (grosorBorde * 10)) - 20, height - 52);

					blanco.setBounds(width - 25, 20, 25 - grosorBorde * 9, 25);

				}

				else if (radius > 104) {

					cuerpo.setBounds((grosorBorde) + 22, 32, (width - (grosorBorde * 10)) - 20, height - 52);

					blanco.setBounds(width - 25, 20, 25 - grosorBorde * 9, 25);

				}

				else {

					cuerpo.setBounds((grosorBorde) + 8, 32, (width - (grosorBorde * 4)) - 20, height - 52);

					blanco.setBounds(width - 25, 20, 25 - grosorBorde * 8, 25);

				}

			}

			else {

				blanco.setBounds(width - 25, 20, 25 - grosorBorde * 6, 25);

				cuerpo.setBounds((grosorBorde) + 10, 32, (width - (grosorBorde * 2)) - 20, height - 52);

			}

		}

		add(blanco);

		add(cabecera);

		add(cuerpo);

		addMouseMotionListener(new MouseAdapter() {

			@Override

			public void mouseDragged(MouseEvent e) {

				dialogo.setLocation(e.getXOnScreen() - lastX, e.getYOnScreen() - lastY);

			}

		});

		addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				lastX = e.getX();

				lastY = e.getY();

			}

		});

		setBackground(panel.getBackground());

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setStroke(new BasicStroke(grosorBorde));

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(getBackground());

		g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

		g2.setColor(border != null ? border : getBackground());

		if (grosorBorde == 1) {

			g2.drawRoundRect(0, 0, (getWidth() - 1), getHeight() - 1, cornerRadius, cornerRadius);

		}

		else {

			g2.drawRoundRect((grosorBorde / 2) - 1, 0, (getWidth() - grosorBorde / 2), (getHeight() - grosorBorde / 2),
					cornerRadius, cornerRadius);

		}

	}

}
