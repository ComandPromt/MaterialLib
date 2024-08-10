package com.buttons.simple;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

import mthos.JMthos;

@SuppressWarnings("serial")
public class ButtonWithSpike extends JButton {

	private int angle;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private int grosor;

	private Color colorGrosor;

	private Color colorFondo;

	private boolean center;

	private int cateto;

	private Color bordeColor;

	public void setBordeColor(Color bordeColor) {

		this.bordeColor = bordeColor;

		repaint();

	}

	public Color getBordeColor() {

		return bordeColor;

	}

	public void setCenter(boolean center) {

		this.center = center;

		repaint();

	}

	public void setAngle(int angle) {

		this.angle = angle;

		repaint();

	}

	public Color getColorGrosor() {

		return colorGrosor;

	}

	public Color getColorFondo() {

		return colorFondo;

	}

	public void setColorFondo(Color colorFondo) {

		this.colorFondo = colorFondo;

		repaint();

	}

	public void setColorGrosor(Color colorGrosor) {

		this.colorGrosor = colorGrosor;

	}

	public int getGrosor() {

		return grosor;

	}

	public void setGrosor(int grosor) {

		if (grosor < 1) {

			grosor = 1;

		}

		this.grosor = grosor;

		repaint();

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
	public ButtonWithSpike(String text) {

		this(text, 90);

	}

	public ButtonWithSpike(String text, int angle) {

		colorFondo = Color.WHITE;

		setFont(getFont().deriveFont(Font.PLAIN, 20f));

		this.angle = angle;

		setText(text);

		setBackground(Color.ORANGE);

		grosor = 1;

	}

	@Override
	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(colorFondo);

		g2.fillRect(0, 0, getWidth(), getHeight());

		g2.setColor(getBackground());

		triangulo(g2);

		g2.setColor(getBackground());

		switch (angle) {

		case 0:

			if (grosor == 1) {

				g2.fillRect(grosor + 1, grosor, (getWidth() - grosor * 3), getHeight() - grosor * 2);

			}

			else {

				switch (grosor) {

				case 3:

				case 4:

					g2.fillRect(grosor + 1, (grosor / 2), (getWidth() - ((grosor * 2))) - 2,
							((getHeight() - ((grosor * 2) - (grosor / 2)))) - 1);

					break;

				default:

					g2.fillRect(grosor, grosor / 2, (getWidth() - ((grosor * 2))),
							((getHeight() - ((grosor * 2) - (grosor / 2)))) + 1);

					break;

				}

			}

			break;

		case 90:

			g2.fillRect(cateto + grosor, grosor, (getWidth() - 2 * cateto) - grosor, getHeight() - grosor * 2);

			break;

		default:

			if (angle > 26) {

				if (grosor > 1) {

					g2.fillRect(cateto, (grosor / 2), (getWidth() - 2 * cateto), (getHeight() - grosor));

				}

				else {

					g2.fillRect(cateto + grosor, (grosor / 2) + 1, (getWidth() - 2 * cateto) - ((grosor * 2) - 1),
							(getHeight() - grosor * 2));

				}

				int[] equis = new int[3];

				int[] ye = new int[3];

				int[] equis2 = new int[3];

				if (grosor == 1) {

					ye[0] = (grosor / 2);

					equis[0] = ((cateto + (grosor / 2)) - (int) JMthos.reglaDeTres(10, 4, grosor)) + 1;

					equis[1] = (cateto + (grosor / 2) - (int) JMthos.reglaDeTres(10, 4, grosor)) + 1;

					equis[2] = (grosor / 2) + 1;

					ye[1] = ((getHeight() - grosor) + (int) JMthos.reglaDeTres(10, 4, grosor)) + 1;

					equis2[0] = (getWidth() - equis[0]) + 1;

					equis2[1] = (getWidth() - equis[1]) + 1;

					equis2[2] = getWidth() - 1;

				}

				else {

					ye[0] = (grosor / 2) - 1;

					equis[0] = (cateto + (grosor / 2)) - (int) JMthos.reglaDeTres(10, 4, grosor);

					if (grosor == 2 || grosor == 4) {

						equis[1] = (cateto + (grosor / 2) - (int) JMthos.reglaDeTres(10, 4, grosor)) - 1;

					}

					else {

						equis[1] = (cateto + (grosor / 2) - (int) JMthos.reglaDeTres(10, 4, grosor));
					}

					ye[1] = (getHeight() - grosor) + (int) JMthos.reglaDeTres(10, 4, grosor);

					if (grosor == 2) {

						equis2[0] = (getWidth() - equis[0]) + 1;

					}

					else {

						equis2[0] = (getWidth() - equis[0]);

					}

					if (grosor > 5) {

						equis2[1] = (getWidth() - equis[1]) + 1;

					}

					else {

						equis2[1] = (getWidth() - equis[1]);

					}

					equis2[2] = getWidth() - grosor / 2;

					equis[2] = (grosor / 2);

				}

				ye[2] = getHeight() / 2;

				g2.fillPolygon(equis, ye, 3);

				g2.fillPolygon(equis2, ye, 3);

			}

			break;

		}

		g2.setColor(getForeground());

		g2.setFont(getFont());

		if (center) {

			cateto = getWidth() / 2 - g2.getFontMetrics().stringWidth(getText()) / 2;

		}

		g2.drawString(getText(), cateto, (getHeight() / 2 + (getFont().getSize() / 2)) - 5);

	}

	private void triangulo(Graphics2D g2) {

		int spikeLength = Math.round(getWidth() * 0.1666f);

		cateto = spikeLength;

		int centroY = getHeight() / 2;

		if (grosor > 1) {

			cateto -= grosor / 2;

		}

		g2.setColor(getBordeColor());

		g2.setStroke(new BasicStroke(grosor));

		switch (angle) {

		case 0:

			g2.drawRect(grosor, 0, getWidth() - (grosor * 2), getHeight() - grosor);

			break;

		case 90:

			g2.drawLine(0, getHeight() / 2, spikeLength, centroY);

			g2.drawRect(spikeLength, 0, getWidth() - 2 * spikeLength, getHeight() - grosor);

			g2.drawLine(getWidth() - spikeLength, centroY, getWidth(), centroY);

			break;

		default:

			cateto = (int) (Math.round(JMthos.calcularTangente(angle)) * centroY);

			g2.drawLine(1, getHeight() / 2, (int) (Math.round(JMthos.calcularTangente(angle)) * centroY), 0);

			g2.drawLine(cateto, 0, (int) (getWidth() - Math.round(JMthos.calcularTangente(angle)) * centroY), 0);

			g2.drawLine(1, getHeight() / 2, (int) (Math.round(JMthos.calcularTangente(angle)) * centroY), getHeight());

			g2.drawLine((int) (Math.round(JMthos.calcularTangente(angle)) * centroY), getHeight(),
					(int) (getWidth() - Math.round(JMthos.calcularTangente(angle)) * centroY), getHeight());

			g2.drawLine((int) (getWidth() - Math.round(JMthos.calcularTangente(angle)) * centroY), 0, getWidth() - 1,
					getHeight() / 2);

			g2.drawLine((int) (getWidth() - Math.round(JMthos.calcularTangente(angle)) * centroY), getHeight(),
					getWidth() - 1, getHeight() / 2);

			g2.drawLine(cateto, getHeight() - 1,
					(int) (getWidth() - (Math.round(JMthos.calcularTangente(angle)) * centroY)), getHeight() - 1);

			break;

		}

	}

}
