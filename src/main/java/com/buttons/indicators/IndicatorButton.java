package com.buttons.indicators;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

import mthos.JMthos;

@SuppressWarnings("serial")
public class IndicatorButton extends JButton {

	private int grosor;

	private int radius;

	private Color borderColor;

	private String text;

	private Color back;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private Indicators borderType;

	private boolean paintSelected;

	private Color selectedColor;

	private int dashPattern;

	private int dotPattern;

	public void setBorderType(Indicators borderType) {

		this.borderType = borderType;

		repaint();

	}

	public void setSelectedColor(Color selectedColor) {

		this.selectedColor = selectedColor;

		repaint();

	}

	public void setPaintSelected(boolean paintSelected) {

		this.paintSelected = paintSelected;

		repaint();

	}

	public void setToolTip(String text, Color background, Color foreground, Color border, Font font) {

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

		this.back = background;

		this.colorTexto = foreground;

		this.border = border;

		this.fuente = font;

		setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || back == null || colorTexto == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, back, colorTexto, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public void setBorderColor(Color borderColor) {

		this.borderColor = borderColor;

		repaint();

	}

	public void setGrosor(int grosor) {

		if (grosor < 3 && grosor > 0) {

			grosor = 3;

		}

		this.grosor = grosor;

		repaint();

	}

	public void setRadius(int radius) {

		if (radius < 0) {

			radius = 0;

		}

		if (radius > 365) {

			radius = 365;

		}

		this.radius = radius;

		repaint();

	}

	public IndicatorButton(String text, Indicators borderType) {

		this(text);

		this.borderType = borderType;

	}

	public void setDashPattern(int dashPattern) {

		this.dashPattern = dashPattern;

		repaint();

	}

	public void setDotPattern(int dotPattern) {

		this.dotPattern = dotPattern;

		repaint();

	}

	public IndicatorButton(String text) {

		dashPattern = 10;

		dotPattern = 1;

		selectedColor = Color.BLACK;

		this.borderType = Indicators.UNDERLINED;

		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {

				repaint();

			}

		});

		borderColor = Color.BLACK;

		grosor = 3;

		radius = 20;

		setText(text);

		setOpaque(true);

		setContentAreaFilled(false);

		setFocusPainted(false);

		setBackground(Color.WHITE);

	}

	@Override
	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();

		g.setColor(getBackground());

		if (borderType == null) {

			borderType = Indicators.UNDERLINED;

		}

		if (borderType != Indicators.BORDE) {

			g.fillRoundRect(0, 0, getWidth() - grosor, getHeight() - grosor, radius, radius);

			pintarBorde(g2d);

		}

		else {

			g.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

		}

		Shape mask = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);

		g2d.setClip(mask);

		if (paintSelected) {

			g2d.setColor(selectedColor);

			if (grosor > 3) {

				g2d.setStroke(new BasicStroke(grosor - 2));

			}

			else {

				g2d.setStroke(new BasicStroke(2));

			}

			int borde = (int) JMthos.valorPorcentaje(getHeight(), 12.5f);

			switch (borderType) {

			case UNDERLINED:

				g2d.drawLine((grosor * 4) + grosor,
						((getHeight() / 2) + (int) JMthos.valorPorcentaje(getHeight(), 30)) + 3,
						(getWidth() - grosor * 4) - grosor,
						((getHeight() / 2) + (int) JMthos.valorPorcentaje(getHeight(), 30)) + 3);

				break;

			case BORDE:

				pintarBorde(g2d);

				break;

			case ROUNDED:

				g2d.drawRoundRect(borde, borde, getWidth() - borde * 2, getHeight() - borde * 2, radius, radius);

				break;

			case DASHED:

				float[] dashPattern1 = { dashPattern, dashPattern };

				BasicStroke dashed = new BasicStroke(grosor, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10,
						dashPattern1, 0);

				g2d.setStroke(dashed);

				g2d.drawRoundRect(borde, borde, getWidth() - borde * 2, getHeight() - borde * 2, radius, radius);

				break;

			default:

				float[] dotPattern2 = { dotPattern, JMthos.calcularSucesionAritmeticaAInt("1#9,2#8", dotPattern) };

				BasicStroke dotted = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 10, dotPattern2,
						0);

				g2d.setStroke(dotted);

				g2d.drawRoundRect(borde, borde, getWidth() - borde * 2, getHeight() - borde * 2, radius, radius);

				break;

			}

		}

		g2d.dispose();

		super.paintComponent(g);

	}

	private void pintarBorde(Graphics2D g2d) {

		if (grosor > 0) {

			g2d.setStroke(new BasicStroke(grosor));

			g2d.setColor(borderColor);

			g2d.drawRoundRect(grosor / 2, grosor / 2, getWidth() - grosor, getHeight() - grosor, radius, radius);

		}

	}

}
