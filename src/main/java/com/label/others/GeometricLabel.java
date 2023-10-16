package com.label.others;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import javax.swing.JLabel;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class GeometricLabel extends JLabel {

	private int lados;

	private float dividir;

	private final double halfPI = Math.PI / 2.0;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private int grosor;

	private Color colorGrosor;

	private Color colorFondo;

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

	public GeometricLabel(String text) {

		colorFondo = Color.WHITE;

		setFont(getFont().deriveFont(Font.PLAIN, 20f));

		dividir = 2;

		setText(text);

		setBackground(Color.ORANGE);

	}

	public GeometricLabel(String text, int lados) {

		colorFondo = Color.WHITE;

		setFont(getFont().deriveFont(Font.PLAIN, 20f));

		dividir = 2;

		this.lados = lados;

		setText(text);

		setBackground(Color.ORANGE);

	}

	public int getLados() {

		return lados;

	}

	public void setLados(int lados) {

		this.lados = lados;

		repaint();

	}

	private Shape regularPolygon(int sides, double x, double y, double width, double height) {

		Path2D poly = new Path2D.Double(Path2D.WIND_EVEN_ODD, 12);

		if (sides > 3) {

			width = width / 2;

			height = height / 2;

			switch (sides) {

			case 5:

				height += height / 9.7;

				x -= width / 20;

				width += width / 20;

				break;

			case 6:

				x -= width / 6.5;

				width += width / 6.5;

				break;

			case 7:

				x -= width / 40;

				width += width / 40;

				height += height / 20;

				break;

			case 9:

				x -= width / 70;

				width += width / 70;

				height += height / 35;

				break;

			case 10:

				x -= width / 20;

				width += width / 20;

				break;

			case 11:

				x -= width / 100;

				width += width / 100;

				height += height / 50;

				break;

			case 13:

				height += height / 85;

				width += width / 300;

				break;

			case 15:

				width += width / 300;

				height += height / 100;

				break;

			case 17:

				height += height / 100;

				break;

			case 18:

				x -= width / 100;

				width += width / 90;

				break;

			}

			if (sides >= 19 && sides % 2 != 0) {

				height += height / 200;

			}

			x = x + width;

			y = y + height;

			Point2D.Double points[] = new Point2D.Double[sides];

			for (int i = 0; i < sides; i++) {

				double x1 = circleX(sides, i) * width + x;

				double y1 = circleY(sides, i) * height + y;

				double x2 = circleX(sides, (i + 1) % sides) * width + x;

				double y2 = circleY(sides, (i + 1) % sides) * height + y;

				points[i] = new Point2D.Double(x1, y1);

				points[(i + 1) % sides] = new Point2D.Double(x2, y2);

			}

			poly.moveTo(points[0].getX(), points[0].getY());

			for (int i = 1; i < sides; i++) {

				poly.lineTo(points[i].getX(), points[i].getY());

			}

			poly.closePath();

		}

		return poly;

	}

	double circleX(int sides, int angle) {

		double coeff = (double) angle / (double) sides;

		return epsilon(Math.cos(2 * coeff * Math.PI - halfPI));

	}

	double circleY(int sides, int angle) {

		double coeff = (double) angle / (double) sides;

		return epsilon(Math.sin(2 * coeff * Math.PI - halfPI));

	}

	static double epsilon(double v) {

		if (Math.abs(v) < 1.0E-10)

			return 0.0;

		return v;

	}

	@Override
	public void paint(Graphics g) {

		if (lados < 3) {

			lados = 3;

		}

		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(colorFondo);

		g2.fillRect(0, 0, getWidth(), getHeight());

		int anchura = getWidth();

		int altura = getHeight();

		g2.setColor(getBackground());

		if (lados == 3) {

			triangulo(g2, anchura, altura);

		}

		else {

			dibujarFigura(g2, anchura, altura);

		}

		g2.setColor(getForeground());

		g2.setFont(getFont());

		switch (lados) {

		case 3:

			dividir = 1.5f;

			break;

		default:

			dividir = 1.8f;

			break;

		}

		g2.drawString(getText(), getWidth() / 2 - g2.getFontMetrics().stringWidth(getText()) / 2,
				getHeight() / dividir);

	}

	private void dibujarFigura(Graphics2D g2, int anchura, int altura) {

		anchura -= grosor;

		altura -= grosor;

		altura -= grosor / 2;

		g2.fill(regularPolygon(lados, (grosor / 2), grosor / 2, anchura, altura));

		g2.setColor(colorGrosor);

		g2.setStroke(new BasicStroke(grosor));

		anchura += grosor;

		altura += grosor;

		g2.draw(regularPolygon(lados, 0, 0, anchura, altura));

	}

	private void triangulo(Graphics2D g2, int anchura, int altura) {

		anchura -= grosor;

		altura -= grosor;

		altura -= grosor / 2;

		g2.setColor(getBackground());

		Point punto1 = new Point((anchura / 2) + (grosor / 2), (grosor / 2));

		Point punto2 = new Point((grosor / 2), altura + (grosor / 2));

		Point punto3 = new Point(anchura + (grosor / 2), altura + (grosor / 2));

		int[] puntosX = { punto1.x, punto2.x, punto3.x };

		int[] puntosY = { punto1.y, punto2.y, punto3.y };

		g2.fillPolygon(puntosX, puntosY, 3);

		g2.setColor(colorGrosor);

		g2.setStroke(new BasicStroke(grosor));

		anchura += grosor;

		altura += grosor;

		punto1 = new Point((anchura / 2), 0);

		punto2 = new Point(0, altura);

		punto3 = new Point(anchura, altura);

		int[] puntosX2 = { punto1.x, punto2.x, punto3.x };

		int[] puntosY2 = { punto1.y, punto2.y, punto3.y };

		g2.drawPolygon(puntosX2, puntosY2, 3);

	}

}
