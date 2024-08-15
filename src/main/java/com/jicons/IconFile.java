package com.jicons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import mthos.JMthos;

@SuppressWarnings("serial")
public class IconFile extends ImageIcon {

	private Color fondo;

	private int grosor;

	private Component c;

	private int angle;

	private int tipo;

	private String texto;

	private Color borde;

	private Color reverse;

	private Color lineas;

	private Color separador;

	private Color bottomColor;

	private BufferedImage image;

	private boolean pintarLineas;

	private Color fondoCirculoFigure12;

	private Color bordeCirculoFigure12;

	private Color trianguloFigure12;

	public void setTrianguloFigure12(Color trianguloFigure12) {

		this.trianguloFigure12 = trianguloFigure12;

		repintar();

	}

	public void setBordeCirculoFigure12(Color bordeCirculoFigure12) {

		this.bordeCirculoFigure12 = bordeCirculoFigure12;

		repintar();

	}

	public void setFondoCirculoFigure11(Color fondoCirculoFigure11) {

		this.fondoCirculoFigure12 = fondoCirculoFigure11;

		repintar();

	}

	public void setPintarLineas(boolean pintarLineas) {

		this.pintarLineas = pintarLineas;

		repintar();

	}

	public void setImage(String image) {

		BufferedImage image2 = null;

		try {

			image2 = ImageIO.read(new File(image));
		}

		catch (IOException e) {

		}

		this.image = image2;

		repintar();

	}

	public void setBottomColor(Color bottomColor) {

		this.bottomColor = bottomColor;

		repintar();

	}

	private void repintar() {

		try {

			c.repaint();

		}

		catch (Exception e) {

		}

	}

	public void setTipo(int tipo) {

		this.tipo = tipo;

		repintar();

	}

	public void setSeparador(Color separador) {

		this.separador = separador;

		repintar();

	}

	public void setLineas(Color lineas) {

		this.lineas = lineas;

		repintar();

	}

	public void setReverse(Color reverse) {

		this.reverse = reverse;

		repintar();

	}

	public void setBorde(Color borde) {

		this.borde = borde;

		repintar();

	}

	public void setTexto(String texto) {

		this.texto = texto;

		repintar();

	}

	public void setGrosor(int grosor) {

		if (grosor <= 0) {

			grosor = 0;

		}

		else {

			grosor--;

		}

		this.grosor = grosor;

		repintar();

	}

	public void setFondo(Color fondo) {

		this.fondo = fondo;

		repintar();

	}

	public IconFile(String texto, int type) {

		this();

		this.texto = texto;

		setTipo(type);

	}

	public IconFile(String texto) {

		this();

		this.texto = texto;

	}

	public IconFile() {

		bottomColor = Color.WHITE;

		borde = Color.BLACK;

		lineas = Color.BLACK;

		separador = Color.BLACK;

		fondoCirculoFigure12 = Color.WHITE;

		bordeCirculoFigure12 = Color.BLACK;

		reverse = Color.LIGHT_GRAY;

		angle = 25;

		grosor = 2;

		fondo = Color.WHITE;

	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {

		this.c = c;

		int width = c.getWidth();

		int height = c.getHeight();

		Graphics2D g2d = (Graphics2D) g;

		g2d.setStroke(new BasicStroke(grosor));

		g2d.setColor(fondo);

		g2d.fillRect(0, 0, width, height);

		g2d.setColor(c.getBackground());

		FontMetrics metrics = g.getFontMetrics(c.getFont());

		switch (tipo) {

		case 2:

			base(width, height, g2d);

			esquinaDerecha(width, height, g2d);

			if (grosor > 0) {

				g2d.setColor(bottomColor);

				g2d.fillRoundRect(grosor, Math.round(height * 0.65625f), width - grosor * 2,
						Math.round(height * 0.336f) + 1, angle, angle);

				g2d.fillRect(grosor, Math.round(height * 0.65625f), width - 4, Math.round(height * 0.3f));

				g2d.setColor(separador);

				g2d.drawLine(grosor, Math.round(height * 0.65625f), width - grosor, Math.round(height * 0.65625f));

			}

			else {

				g2d.setColor(bottomColor);

				g2d.fillRect(2, Math.round(height * 0.65625f), width - grosor * 2, Math.round(height * 0.34f));

				g2d.setColor(separador);

				g2d.drawLine(1, Math.round(height * 0.65625f), width, Math.round(height * 0.65625f));

			}

			pintarBorde(width, height, g2d);

			escribirTexto(c, Math.round(height * 0.89f), width, height, g2d, metrics);

			break;

		case 3:

			base(width, height, g2d);

			g2d.setColor(c.getBackground());

			esquinaDerecha(width, height, g2d);

			pintarBorde(width, height, g2d);

			if (grosor == 0) {

				g2d.setColor(bottomColor);

				g2d.fillRect(Math.round(width * 0.1f), Math.round(height * 0.4f), Math.round(width * 0.8f),
						Math.round(height * 0.28f));

				g2d.setColor(separador);

				g2d.drawRect(Math.round(width * 0.1f), Math.round(height * 0.4f), Math.round(width * 0.8f),
						Math.round(height * 0.28f));

			}

			else {

				g2d.setColor(bottomColor);

				g2d.fillRoundRect(Math.round(width * 0.1f), Math.round(height * 0.4f), Math.round(width * 0.8f),
						Math.round(height * 0.28f), 25, 25);

				g2d.setColor(separador);

				g2d.drawRoundRect(Math.round(width * 0.1f), Math.round(height * 0.4f), Math.round(width * 0.8f),
						Math.round(height * 0.28f), 25, 25);

			}

			escribirTexto(c, Math.round(height * 0.6f) + 1, width, height, g2d, metrics);

			break;

		case 4:

			base(width, height, g2d);

			g2d.setColor(c.getBackground());

			esquinaDerecha(width, height, g2d);

			pintarBorde(width, height, g2d);

			int ye = Math.round(height * 0.955f) + c.getFont().getSize() / 14;

			escribirTexto(c, ye, width, height, g2d, metrics);

			ye = (Math.round(height * 0.95f) - c.getFont().getSize() / 2) + 2;

			if (c.getFont().getSize() > 50) {

				c.setFont(c.getFont().deriveFont(50f));

			}

			g2d.setColor(separador);

			if (c.getFont().getSize() < 45) {

				g2d.drawArc(Math.round(width * 0.1f),
						(ye - (ye / 12)) + JMthos.calcularSucesionAritmeticaAInt("35#2,40#5", c.getFont().getSize()),
						Math.round(width * 0.8f), 100, -0, 180);

			}

			else {

				g2d.drawArc(Math.round(width * 0.1f),
						(ye - (ye / 18)) + JMthos.calcularSucesionAritmeticaAInt("45#0,50#2", c.getFont().getSize()),
						Math.round(width * 0.8f), 100, -0, 180);
			}

			break;

		case 5:

			figura2(c, g, width, height, g2d);

			g2d.setColor(separador);

			if (grosor == 0) {

				g2d.fillRect(grosor, Math.round(height * 0.65f) - c.getFont().getSize(), Math.round(width * 0.55f),
						Math.round(height * 0.23f));

				g.setColor(borde);

				g2d.drawRect(grosor, Math.round(height * 0.65f) - c.getFont().getSize(), Math.round(width * 0.55f),
						Math.round(height * 0.23f));

			}

			else {

				g2d.fillRoundRect(grosor, Math.round(height * 0.65f) - c.getFont().getSize(), Math.round(width * 0.55f),
						Math.round(height * 0.23f), angle, angle);

				g.setColor(borde);

				g2d.drawRoundRect(grosor, Math.round(height * 0.65f) - c.getFont().getSize(), Math.round(width * 0.55f),
						Math.round(height * 0.23f), angle, angle);

			}

			escribirTexto(c, Math.round(width * 0.05f), Math.round(height * 0.6f) + 1, width, height, g2d, metrics);

			break;

		case 6:

			figura2(c, g, width, height, g2d);

			g.setColor(borde);

			if (grosor == 0) {

				g2d.drawRect(grosor, Math.round(height * 0.65f) - c.getFont().getSize(), Math.round(width * 0.55f),
						Math.round(height * 0.23f));

			}

			else {

				g.setColor(bottomColor);

				g2d.fillRoundRect(grosor, Math.round(height * 0.65f) - c.getFont().getSize(), width - (grosor * 2),
						Math.round(height * 0.23f), angle, angle);
				g.setColor(borde);

				g2d.drawRoundRect(grosor, Math.round(height * 0.65f) - c.getFont().getSize(), width - (grosor * 2),
						Math.round(height * 0.23f), angle, angle);

			}

			escribirTexto(c, Math.round(width * 0.05f), Math.round(height * 0.6f) + 1, width, height, g2d, metrics);

			break;

		case 7:

			base(width, height, g2d);

			g2d.setColor(c.getBackground());

			esquinaDerecha(width, height, g2d);

			pintarBorde(width, height, g2d);

			if (grosor == 0) {

				g2d.setColor(bottomColor);

				g2d.fillRect(Math.round(width * 0.1f), Math.round(height * 0.3f), Math.round(width * 0.8f),
						Math.round(height * 0.6f));

				g2d.setColor(separador);

				g2d.drawRect(Math.round(width * 0.1f), Math.round(height * 0.3f), Math.round(width * 0.8f),
						Math.round(height * 0.6f));

				if (image != null) {

					g2d.drawImage(image, Math.round(width * 0.1f), Math.round(height * 0.3f),
							Math.round(width * 0.1f) + Math.round(width * 0.8f),
							Math.round(height * 0.3f) + Math.round(height * 0.6f), 0, 0, image.getWidth(),
							image.getHeight(), null);

				}

			}

			else {

				g2d.setColor(bottomColor);

				g2d.fillRoundRect(Math.round(width * 0.1f), Math.round(height * 0.3f), Math.round(width * 0.8f),
						Math.round(height * 0.6f), 25, 25);

				g2d.setColor(separador);

				g2d.drawRoundRect(Math.round(width * 0.1f), Math.round(height * 0.3f), Math.round(width * 0.8f),
						Math.round(height * 0.6f), 25, 25);

				if (image != null) {

					g2d.setClip(new RoundRectangle2D.Float(Math.round(width * 0.1f), Math.round(height * 0.3f),
							Math.round(width * 0.8f), Math.round(height * 0.6f), angle, angle));

					g2d.drawImage(image, Math.round(width * 0.1f), Math.round(height * 0.3f),
							Math.round(width * 0.1f) + Math.round(width * 0.8f),
							Math.round(height * 0.3f) + Math.round(height * 0.6f), 0, 0, image.getWidth(),
							image.getHeight(), null);

				}

			}

			escribirTexto(c, Math.round(height * 0.66f) + 2, width, height, g2d, metrics);

			break;

		case 8:

			base(width, height, g2d);

			g2d.setColor(c.getBackground());

			esquinaDerecha(width, height, g2d);

			pintarBorde(width, height, g2d);

			int rectX = Math.round(width * 0.1f);

			int rectY = Math.round(height * 0.3f);

			int rectWidth = Math.round(width * 0.8f);

			int rectHeight = Math.round(height * 0.6f);

			if (grosor == 0) {

				g2d.setColor(bottomColor);

				g2d.fillRect(Math.round(width * 0.1f), Math.round(height * 0.3f), Math.round(width * 0.8f),
						Math.round(height * 0.6f));

				g2d.setColor(separador);

				g2d.drawRect(Math.round(width * 0.1f), Math.round(height * 0.3f), Math.round(width * 0.8f),
						Math.round(height * 0.6f));

				if (image != null) {

					g2d.drawImage(image, rectX, rectY, rectX + rectWidth, rectY + rectHeight, 0, 0, image.getWidth(),
							image.getHeight(), null);

				}

			}

			else {

				g2d.setColor(bottomColor);

				g2d.fillRoundRect(Math.round(width * 0.1f), Math.round(height * 0.3f), Math.round(width * 0.8f),
						Math.round(height * 0.6f), 25, 25);

				g2d.setColor(separador);

				g2d.drawRoundRect(Math.round(width * 0.1f), Math.round(height * 0.3f), Math.round(width * 0.8f),
						Math.round(height * 0.6f), angle, angle);

				escribirTexto(c, Math.round(width * 0.1f), c.getFont().getSize(), width, height, g2d, metrics);

				if (image != null) {

					g2d.setClip(new RoundRectangle2D.Float(rectX, rectY, rectWidth, rectHeight, angle, angle));

					g2d.drawImage(image, rectX, rectY, rectX + rectWidth, rectY + rectHeight, 0, 0, image.getWidth(),
							image.getHeight(), null);

				}

			}

			break;

		case 9:

			if (grosor == 0) {

				g2d.fillRect(Math.round(width * 0.1f), grosor / 2, (width - Math.round(width * 0.2f)),
						(height - grosor));

				g2d.fillRect(Math.round(width * 0.1f), grosor / 2, (width - Math.round(width * 0.2f)),
						(height - grosor));

				g2d.setColor(bottomColor);

				g2d.fillRect(1, Math.round(height * 0.5f), width - 2, Math.round(height * 0.4f));

				g2d.setColor(borde);

				g2d.drawRect(1, Math.round(height * 0.5f), width - 2, Math.round(height * 0.4f));

				esquinaDerecha2(true, width, height, g2d);

			}

			else {

				g2d.fillRoundRect(Math.round(width * 0.1f), grosor / 2, (width - Math.round(width * 0.2f)),
						(height - grosor), angle, angle);

				g2d.setColor(bottomColor);

				g2d.fillRoundRect(1, Math.round(height * 0.5f), width - 2, Math.round(height * 0.4f), angle, angle);

				g2d.setColor(borde);

				g2d.drawRoundRect(1, Math.round(height * 0.5f), width - 2, Math.round(height * 0.4f), angle, angle);

				esquinaDerecha2(false, width, height, g2d);

			}

			escribirTexto(c, Math.round(height * 0.76f) + 1, width, height, g2d, metrics);

			break;

		case 10:

			if (grosor == 0) {

				g2d.fillRect(Math.round(width * 0.1f), grosor / 2, (width - Math.round(width * 0.2f)),
						(height - grosor));

				g2d.fillRect(Math.round(width * 0.1f), grosor / 2, (width - Math.round(width * 0.2f)),
						(height - grosor));

				g2d.setColor(bottomColor);

				g2d.fillRect(1, Math.round(height * 0.5f), width - 2, Math.round(height * 0.4f));

				g2d.setColor(borde);

				g2d.drawRect(1, Math.round(height * 0.5f), width - 2, Math.round(height * 0.4f));

				esquinaDerecha2(true, width, height, g2d);

			}

			else {

				g2d.fillRoundRect(1, grosor / 2, Math.round(width * 0.9f), (height - grosor), angle, angle);

				g2d.setColor(bottomColor);

				g2d.fillRoundRect(Math.round(height * 0.2f), Math.round(height * 0.5f), Math.round(height * 0.66f),
						Math.round(height * 0.4f), angle, angle);

				g2d.setColor(borde);

				g2d.drawRoundRect(Math.round(height * 0.2f), Math.round(height * 0.5f), Math.round(height * 0.66f),
						Math.round(height * 0.4f), angle, angle);

				esquinaDerecha2(false, width, height, g2d);

			}

			escribirTexto(c, Math.round(height * 0.76f) + 1, width, height, g2d, metrics);

			break;

		case 11:

			base(width, height, g2d);

			g2d.setColor(c.getBackground());

			esquinaDerecha(width, height, g2d);

			pintarBorde(width, height, g2d);

			g2d.setColor(separador);

			g2d.drawLine(grosor, Math.round(height * 0.76f), width - grosor, Math.round(height * 0.76f));

			escribirTexto(c, Math.round(height * 0.95f), width, height, g2d, metrics);

			if (grosor == 0) {

				g2d.setColor(bottomColor);

				g2d.fillRect(Math.round(width * 0.1f), Math.round(height * 0.4f), Math.round(width * 0.8f),
						Math.round(height * 0.28f));

				g2d.drawImage(image, Math.round(width * 0.1f), Math.round(height * 0.25f),
						Math.round(width * 0.1f) + Math.round(width * 0.8f),
						Math.round(height * 0.25f) + Math.round(height * 0.46f), 0, 0, image.getWidth(),
						image.getHeight(), null);
			}

			else {

				g2d.setColor(bottomColor);

				g2d.fillRoundRect(Math.round(width * 0.1f), Math.round(height * 0.25f), Math.round(width * 0.8f),
						Math.round(height * 0.46f), 25, 25);

				g2d.setClip(new RoundRectangle2D.Float(Math.round(width * 0.1f), Math.round(height * 0.25f),
						Math.round(width * 0.8f), Math.round(height * 0.46f), 25, 25));

				g2d.drawImage(image, Math.round(width * 0.1f), Math.round(height * 0.25f),
						Math.round(width * 0.1f) + Math.round(width * 0.8f),
						Math.round(height * 0.25f) + Math.round(height * 0.46f), 0, 0, image.getWidth(),
						image.getHeight(), null);

			}

			break;

		case 12:

			base(width, height, g2d);

			g2d.setColor(c.getBackground());

			esquinaDerecha(width, height, g2d);

			pintarBorde(width, height, g2d);

			g2d.setColor(bottomColor);

			if (grosor == 0) {

				g2d.fillRect(2, Math.round(height * 0.76f), width - 4, Math.round(height * 0.24f) - 2);

			}

			else {

				g2d.fillRect(grosor, Math.round(height * 0.76f), width - grosor * 2, Math.round(height * 0.2f));

				g2d.fillRoundRect(grosor, Math.round(height * 0.76f), width - grosor * 2,
						Math.round(height * 0.24f) - grosor + 2, angle, angle);

			}

			g2d.setColor(separador);

			g2d.drawLine(grosor + 1, Math.round(height * 0.76f), (width - grosor * 2), Math.round(height * 0.76f));

			escribirTexto(c, Math.round(height * 0.95f), width, height, g2d, metrics);

			float dash1[] = { 10.0f };

			BasicStroke dashed = new BasicStroke(grosor, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1,
					0.0f);

			g2d.setStroke(dashed);

			int circleX = Math.round(width * 0.4f);

			g2d.setColor(fondoCirculoFigure12);

			g2d.fillOval(Math.round(width * 0.3f) + grosor * 2, Math.round(height * 0.3f) + grosor * 2,
					circleX - (grosor * 4), circleX - (grosor * 4));

			g2d.setColor(bordeCirculoFigure12);

			g2d.drawOval(Math.round(width * 0.3f), Math.round(height * 0.3f), circleX, circleX);

			int equis[] = new int[3];

			int ye2[] = new int[3];

			equis[0] = Math.round(width * 0.47f);

			equis[1] = Math.round(width * 0.47f);

			equis[2] = Math.round(width * 0.6f);

			ye2[0] = Math.round(height * 0.4f);

			ye2[1] = Math.round(height * 0.55f);

			ye2[2] = Math.round(height * 0.48f);

			g2d.setColor(trianguloFigure12);

			g2d.fillPolygon(equis, ye2, 3);

			break;

		default:

			base(width, height, g2d);

			esquinaDerecha(width, height, g2d);

			g2d.setColor(lineas);

			g2d.drawLine(Math.round(width * 0.2f), Math.round(height * 0.3f), Math.round(width * 0.8f),
					Math.round(height * 0.3f));

			g2d.drawLine(Math.round(width * 0.2f), Math.round(height * 0.4f), Math.round(width * 0.8f),
					Math.round(height * 0.4f));

			g2d.drawLine(Math.round(width * 0.2f), Math.round(height * 0.5f), Math.round(width * 0.8f),
					Math.round(height * 0.5f));

			g2d.drawLine(Math.round(width * 0.2f), Math.round(height * 0.6f), Math.round(width * 0.8f),
					Math.round(height * 0.6f));

			g2d.setColor(separador);

			if (grosor > 0) {

				g2d.drawLine(grosor, Math.round(height * 0.75f), width - grosor, Math.round(height * 0.75f));

			}

			else {

				g2d.drawLine(1, Math.round(height * 0.75f), width, Math.round(height * 0.75f));

			}

			g2d.setColor(bottomColor);

			if (grosor == 0) {

				g2d.fillRect(2, Math.round(height * 0.75f) + 1, width - 3, Math.round(height * 0.25f) - 2);

			}

			else {

				g2d.fillRect(grosor, Math.round(height * 0.75f), width - 4, Math.round(height * 0.2f));

				g2d.fillRoundRect(grosor, Math.round(height * 0.75f), width - grosor * 2,
						Math.round(height * 0.25f) - 1, angle, angle);
			}

			pintarBorde(width, height, g2d);

			escribirTexto(c, Math.round(height * 0.94f) - 1, width, height, g2d, metrics);

			break;

		}

	}

	private void figura2(Component c, Graphics g, int width, int height, Graphics2D g2d) {

		g2d.setColor(c.getBackground());

		if (grosor == 0) {

			g.fillRect(Math.round(width * 0.1f), 0, Math.round(width * 0.9f), Math.round(height * 0.94f));

		}

		else {

			g.fillRoundRect(Math.round(width * 0.1f), 2, Math.round(width * 0.89f), Math.round(height * 0.94f), angle,
					angle);

			g.drawArc(Math.round(width * 0.1f), 2, Math.round(width * 0.1f), Math.round(width * 0.1f), 180, -90);

			g.drawArc(Math.round(width * 0.1f), Math.round(height * 0.86f), Math.round(width * 0.1f),
					Math.round(width * 0.1f), 180, 90);

			g.drawArc(Math.round(width * 0.8f), Math.round(height * 0.05f), Math.round(width * 0.1f),
					Math.round(width * 0.17f), 180, 90);

			g.drawArc(Math.round(width * 0.89f) + 1, Math.round(height * 0.86f) + 1, Math.round(width * 0.1f),
					Math.round(width * 0.1f), 0, -90);

		}

		esquinaDerecha(width, height, g2d);

		g.setColor(borde);

		if (grosor == 0) {

			g2d.drawLine(Math.round(width * 0.1f), 0, Math.round(width * 0.1f), Math.round(height * 0.424f));

			g2d.drawLine(Math.round(width * 0.1f), 0, Math.round(width * 0.79f), 0);

			g2d.drawLine(Math.round(width * 0.79f), 0, Math.round(width * 0.79f), Math.round(height * 0.2f));

			g2d.drawLine(Math.round(width * 0.79f), Math.round(height * 0.2f), width, Math.round(height * 0.2f));

			g2d.drawLine(Math.round(width * 0.79f), 0, width, Math.round(height * 0.2f));

			g2d.drawLine(Math.round(width * 0.1f), Math.round(height * 0.66f), Math.round(width * 0.1f),
					Math.round(height * 0.94f));

			g2d.drawLine(Math.round(width * 0.1f), Math.round(height * 0.94f), width, Math.round(height * 0.94f));

			g2d.drawLine(width - 1, Math.round(height * 0.2f), width - 1, Math.round(height * 0.94f));

		}

		else {

			g.drawLine(Math.round(width * 0.15f), 2, Math.round(width * 0.8f), 2);

			g.drawLine(Math.round(width * 0.1f), Math.round(height * 0.05f), Math.round(width * 0.1f),
					Math.round(height * 0.42f));

			g.drawLine(Math.round(width * 0.1f), Math.round(height * 0.66f), Math.round(width * 0.1f),
					Math.round(height * 0.9f));

			g.drawLine(Math.round(width * 0.8f), 2, Math.round(width * 0.99f), Math.round(height * 0.2f));

			g.drawLine((width - grosor) + 1, Math.round(height * 0.2f), (width - grosor) + 1,
					Math.round(height * 0.9f));

			g.drawLine(Math.round(width * 0.15f), Math.round(height * 0.95f) - 1, Math.round(width * 0.95f),
					Math.round(height * 0.95f) - 1);

			g.drawLine(Math.round(width * 0.8f), 2, Math.round(width * 0.8f), Math.round(height * 0.12f));

			g.drawLine(Math.round(width * 0.85f), Math.round(height * 0.2f), width - grosor, Math.round(height * 0.2f));

			g.drawArc(Math.round(width * 0.1f), 2, Math.round(width * 0.1f), Math.round(width * 0.1f), 180, -90);

			g.drawArc(Math.round(width * 0.1f), Math.round(height * 0.86f), Math.round(width * 0.1f),
					Math.round(width * 0.1f), 180, 90);

			g.drawArc(Math.round(width * 0.8f), Math.round(height * 0.05f), Math.round(width * 0.1f),
					Math.round(width * 0.17f), 180, 90);

			g.drawArc(Math.round(width * 0.89f) + 1, Math.round(height * 0.86f) + 1, Math.round(width * 0.1f),
					Math.round(width * 0.1f), 0, -90);

		}

	}

	private void base(int width, int height, Graphics2D g2d) {

		if (grosor == 0) {

			g2d.fillRect(1, 1, width, height - 2);

		}

		else {

			g2d.fillRoundRect(grosor, grosor / 2, (width - grosor), (height - grosor), angle, angle);

		}

	}

	private void escribirTexto(Component c, int posX, int posY, int width, int height, Graphics2D g2d,
			FontMetrics metrics) {

		g2d.setFont(c.getFont());

		g2d.setColor(c.getForeground());

		g2d.drawString(texto, posX, posY);

	}

	private void escribirTexto(Component c, int posY, int width, int height, Graphics2D g2d, FontMetrics metrics) {

		g2d.setFont(c.getFont());

		g2d.setColor(c.getForeground());

		int textWidth = metrics.stringWidth(texto);

		int posX = (width - textWidth) / 2;

		g2d.drawString(texto, posX, posY);

	}

	private void pintarBorde(int width, int height, Graphics2D g2d) {

		g2d.setColor(borde);

		if (grosor > 0) {

			g2d.drawArc(grosor / 2, grosor / 2, Math.round(width * 0.1f), Math.round(width * 0.1f), 180, -90);

			g2d.drawLine(width - grosor, Math.round(height * 0.2f), width - grosor, Math.round(height * 0.97f));

			g2d.drawLine(Math.round(width * 0.05f), grosor / 2, Math.round(width * 0.8f), grosor / 2);

			g2d.drawLine(Math.round(width * 0.8f), grosor, width - grosor, Math.round(height * 0.2f));

			g2d.drawLine(Math.round(width * 0.8f), grosor, Math.round(width * 0.8f), Math.round(height * 0.1f));

			g2d.drawLine(Math.round(width * 0.85f), Math.round(height * 0.2f), width - grosor,
					Math.round(height * 0.2f));

			g2d.drawArc(Math.round(width * 0.8f), Math.round(height * 0.001f), Math.round(width * 0.1f),
					Math.round(height * 0.2f), 180, 90);

			g2d.drawArc(grosor / 2, Math.round(height * 0.92f) - grosor - 1, Math.round(width * 0.1f) - 5,
					Math.round(width * 0.1f) - 1, 180, 90);

			g2d.drawArc(Math.round(width * 0.89f) + 7, Math.round(height * 0.92f) - grosor + 1,
					Math.round(width * 0.1f) - 5, Math.round(width * 0.1f) - 2, 0, -90);

			g2d.drawLine(Math.round(width * 0.05f) - 1, height - 1, Math.round(width * 0.96f), height - 1);

			g2d.drawLine(grosor / 2, Math.round(height * 0.04f), grosor / 2, Math.round(height * 0.96f));

			g2d.setColor(fondo);

			g2d.fillRect(width - grosor / 2, 0, grosor, height);

		}

		else {

			g2d.drawLine(width - 1, Math.round(height * 0.2f), width - 1, height - 1);

			g2d.drawLine(1, 0, Math.round(width * 0.79f), 0);

			g2d.drawLine(Math.round(width * 0.79f), 0, width, Math.round(height * 0.2f));

			g2d.drawLine(Math.round(width * 0.79f), 1, Math.round(width * 0.79f), Math.round(height * 0.2f));

			g2d.drawLine(Math.round(width * 0.79f), Math.round(height * 0.2f), width, Math.round(height * 0.2f));

			g2d.drawLine(2, height - 1, width - 1, height - 1);

			g2d.drawLine(1, 0, 1, height - 1);

		}

	}

	private void esquinaDerecha2(boolean cero, int width, int height, Graphics2D g2d) {

		int equis[] = new int[3];

		int ye[] = new int[3];

		if (pintarLineas) {

			g2d.setColor(lineas);

			g2d.drawLine(Math.round(width * 0.2f), Math.round(height * 0.1f), Math.round(width * 0.72f),
					Math.round(height * 0.1f));

			g2d.drawLine(Math.round(width * 0.2f), Math.round(height * 0.2f), Math.round(width * 0.72f),
					Math.round(height * 0.2f));

			g2d.drawLine(Math.round(width * 0.2f), Math.round(height * 0.3f), Math.round(width * 0.72f),
					Math.round(height * 0.3f));

			g2d.drawLine(Math.round(width * 0.2f), Math.round(height * 0.4f), Math.round(width * 0.72f),
					Math.round(height * 0.4f));

		}

		g2d.setColor(fondo);

		if (grosor > 0) {

			equis[0] = Math.round(width * 0.8f);

			equis[1] = width - grosor;

			equis[2] = width;

			ye[0] = grosor / 2;

			ye[1] = grosor / 2;

			ye[2] = Math.round(height * 0.2f) + 2;

			g2d.fillPolygon(equis, ye, 3);

		}

		else {

			equis[0] = Math.round(width * 0.79f);

			equis[1] = width;

			equis[2] = width;

			ye[0] = 0;

			ye[1] = 0;

			ye[2] = Math.round(height * 0.2f) + 1;

			g2d.fillPolygon(equis, ye, 3);

		}

		g2d.setColor(reverse);

		if (grosor > 0) {

			equis[0] = Math.round(width * 0.8f);

			equis[1] = Math.round(width * 0.8f);

			equis[2] = Math.round(width * 0.9f);

			ye[0] = Math.round(height * 0.001f);

			ye[1] = Math.round(height * 0.06f);

			ye[2] = Math.round(height * 0.1f) + 1;

			g2d.fillPolygon(equis, ye, 3);

			equis[0] = Math.round(width * 0.85f);

			equis[1] = Math.round(width * 0.85f);

			equis[2] = Math.round(width * 0.9f);

			ye[0] = Math.round(height * 0.05f);

			ye[1] = Math.round(height * 0.1f);

			ye[2] = Math.round(height * 0.1f);

			g2d.fillPolygon(equis, ye, 3);

			g2d.fillArc(Math.round(width * 0.8f), Math.round(height * 0.01f), Math.round(width * 0.1f),
					Math.round(height * 0.09f), 180, 90);

		}

		else {

			if (cero) {

				equis[0] = Math.round(width * 0.79f);

				equis[1] = Math.round(width * 0.79f);

				equis[2] = Math.round(width * 0.9f) - 1;

				ye[0] = Math.round(height * 0.001f) - 1;

				ye[1] = Math.round(height * 0.1f) + 1;

				ye[2] = Math.round(height * 0.1f) + 1;

				g2d.fillPolygon(equis, ye, 3);

			}

			else {

				equis[0] = Math.round(width * 0.8f);

				equis[1] = Math.round(width * 0.8f);

				equis[2] = Math.round(width * 0.9f);

				ye[0] = Math.round(height * 0.001f);

				ye[1] = Math.round(height * 0.1f) + 1;

				ye[2] = Math.round(height * 0.1f) + 1;

				g2d.fillPolygon(equis, ye, 3);
			}

		}

	}

	private void esquinaDerecha(int width, int height, Graphics2D g2d) {

		int equis[] = new int[3];

		int ye[] = new int[3];

		g2d.setColor(fondo);

		equis[0] = Math.round(width * 0.79f);

		equis[1] = width - 1;

		equis[2] = width - 1;

		ye[0] = 0;

		ye[1] = 0;

		ye[2] = Math.round(height * 0.2f) + 1;

		g2d.fillPolygon(equis, ye, 3);

		g2d.setColor(reverse);

		if (grosor > 0) {

			equis[0] = Math.round(width * 0.8f);

			equis[1] = Math.round(width * 0.8f);

			equis[2] = width - grosor;

			ye[0] = Math.round(height * 0.01f);

			ye[1] = Math.round(height * 0.135f);

			ye[2] = Math.round(height * 0.2f);

			g2d.fillPolygon(equis, ye, 3);

			equis[0] = Math.round(width * 0.85f);

			equis[1] = Math.round(width * 0.85f);

			equis[2] = width - grosor;

			ye[0] = Math.round(height * 0.1f);

			ye[1] = Math.round(height * 0.2f);

			ye[2] = Math.round(height * 0.2f);

			g2d.fillPolygon(equis, ye, 3);

			g2d.fillArc(Math.round(width * 0.8f), Math.round(height * 0.001f), Math.round(width * 0.1f),
					Math.round(height * 0.2f), 180, 90);
		}

		else {

			equis[0] = Math.round(width * 0.803f) - 4;

			equis[1] = Math.round(width * 0.8012f) - 4;

			equis[2] = width;

			ye[0] = Math.round(height * 0.001f) - 1;

			ye[1] = Math.round(height * 0.2f);

			ye[2] = Math.round(height * 0.2f);

			g2d.fillPolygon(equis, ye, 3);

		}

	}

}