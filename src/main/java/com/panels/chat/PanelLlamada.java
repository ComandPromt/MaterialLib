package com.panels.chat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JToolTip;

import com.panels.gradient.GradientPanel;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class PanelLlamada extends GradientPanel {

	public enum Orientacion {

		LEFT, RIGHT, TOP, BOTTOM

	}

	protected Color colorDeBorde;

	protected Orientacion orientacion;

	protected int distancia;

	protected int ancho;

	private float anchoDeBorde;

	private String text;

	private Color fondo;

	private Color colorTexto;

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

	public PanelLlamada() {

		colorDeBorde = new Color(173, 173, 173);

		orientacion = Orientacion.LEFT;

		distancia = 10;

		ancho = 10;

		anchoDeBorde = 1f;

		setOpaque(false);

	}

	@Override
	protected void paintBorder(Graphics g) {

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setStroke(new BasicStroke(anchoDeBorde));

		g2.setColor(colorDeBorde);

		g2.draw(getShape());

		g2.dispose();

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		g2.setPaint(getGradientePaint());

		g2.fill(getShape());

	}

	public Shape getShape() {

		Shape shape;

		Area area;

		GeneralPath path;

		switch (orientacion) {

		case LEFT:

			shape = new RoundRectangle2D.Float(distancia, 0, getWidth() - distancia - 1, getHeight() - 1,
					getHeight() / 3, getHeight() / 3);

			area = new Area(shape);

			path = new GeneralPath();

			path.moveTo(0, getHeight() / 2);

			path.lineTo(distancia, (getHeight() / 2) - ancho);

			path.lineTo(distancia, (getHeight() / 2) + ancho);

			path.closePath();

			area.add(new Area(path));

			break;

		case RIGHT:

			shape = new RoundRectangle2D.Float(0, 0, getWidth() - distancia, getHeight() - 1, getHeight() / 3,
					getHeight() / 3);

			area = new Area(shape);

			path = new GeneralPath();

			path.moveTo(getWidth(), getHeight() / 2);

			path.lineTo(getWidth() - distancia, (getHeight() / 2) - ancho);

			path.lineTo(getWidth() - distancia, (getHeight() / 2) + ancho);

			path.closePath();

			area.add(new Area(path));

			break;

		case BOTTOM:

			shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - distancia, getHeight() / 3,
					getHeight() / 3);

			area = new Area(shape);

			path = new GeneralPath();

			path.moveTo(getWidth() / 2, getHeight());

			path.lineTo(getWidth() / 2 - ancho, getHeight() - distancia);

			path.lineTo(getWidth() / 2 + ancho, getHeight() - distancia);

			path.closePath();

			area.add(new Area(path));

			break;

		case TOP:

			shape = new RoundRectangle2D.Float(0, distancia, getWidth() - 1, getHeight() - distancia - 1,
					getHeight() / 3, getHeight() / 3);

			area = new Area(shape);

			path = new GeneralPath();

			path.moveTo(getWidth() / 2, 0);

			path.lineTo(getWidth() / 2 - ancho, distancia);

			path.lineTo(getWidth() / 2 + ancho, distancia);

			path.closePath();

			area.add(new Area(path));

			break;

		default:

			shape = new RoundRectangle2D.Float(distancia, 0, getWidth() - distancia, getHeight(), getHeight() / 3,
					getHeight() / 3);

			area = new Area(shape);

			path = new GeneralPath();

			path.moveTo(0, getHeight() / 2);

			path.lineTo(distancia, (getHeight() / 2) - ancho);

			path.lineTo(distancia, (getHeight() / 2) + ancho);

			path.closePath();

			area.add(new Area(path));

			break;

		}

		return area;

	}

	public int getAncho() {

		return ancho;

	}

	public void setAncho(int ancho) {

		this.ancho = ancho;

	}

	public Color getColorDeBorde() {

		return colorDeBorde;

	}

	public void setColorDeBorde(Color colorDeBorde) {

		this.colorDeBorde = colorDeBorde;

	}

	public int getDistancia() {

		return distancia;

	}

	public void setDistancia(int distancia) {

		this.distancia = distancia;

	}

	public Orientacion getOrientacion() {

		return orientacion;

	}

	public void setOrientacion(Orientacion orientacion) {

		this.orientacion = orientacion;

	}

	public float getAnchoDeBorde() {

		return anchoDeBorde;

	}

	public void setAnchoDeBorde(float anchoDeBorde) {

		this.anchoDeBorde = anchoDeBorde;

	}

}
