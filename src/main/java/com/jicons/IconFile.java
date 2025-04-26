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

		catch (IOException iOException) {

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

			this.c.repaint();

		}

		catch (Exception exception) {

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

		this.texto = "";

		this.bottomColor = Color.PINK;

		this.borde = Color.BLACK;

		this.lineas = Color.BLACK;

		this.separador = Color.BLACK;

		this.fondoCirculoFigure12 = Color.BLUE;

		this.bordeCirculoFigure12 = Color.BLACK;

		this.reverse = Color.LIGHT_GRAY;

		this.angle = 25;

		this.grosor = 2;

		this.fondo = Color.WHITE;

	}

	public void paintIcon(Component c, Graphics g, int x, int y) {

		int ye;

		float[] dash1;

		BasicStroke dashed;

		int circleX, equis[], ye2[];

		this.c = c;

		int width = c.getWidth();

		int height = c.getHeight();

		Graphics2D g2d = (Graphics2D) g;

		g2d.setStroke(new BasicStroke(this.grosor));

		g2d.setColor(this.fondo);

		g2d.fillRect(0, 0, width, height);

		g2d.setColor(c.getBackground());

		FontMetrics metrics = g.getFontMetrics(c.getFont());

		switch (this.tipo) {

		case 2:

			base(width, height, g2d);

			esquinaDerecha(width, height, g2d);

			if (this.grosor > 0) {

				g2d.setColor(this.bottomColor);

				g2d.fillRoundRect((this.grosor / 2) - 3, Math.round(height * 0.66F), (width + this.grosor) + 2,
						Math.round(height * 0.34F) - 1, this.angle, this.angle);

				g2d.fillRect(this.grosor, Math.round(height * 0.65625F), width - this.grosor,
						Math.round(height * 0.3F));

				g2d.setColor(this.separador);

				if (grosor == 1) {

					g2d.drawLine(0, Math.round(height * 0.65625F), width - this.grosor, Math.round(height * 0.65625F));

				}

				else {

					g2d.drawLine(grosor, Math.round(height * 0.65625F), width - this.grosor,
							Math.round(height * 0.65625F));

				}

			}

			else {

				g2d.setColor(this.bottomColor);

				g2d.fillRect(2, Math.round(height * 0.65625F), width - this.grosor * 2, Math.round(height * 0.34F));

				g2d.setColor(this.separador);

				g2d.drawLine(0, Math.round(height * 0.65625F), width, Math.round(height * 0.65625F));

			}

			pintarBorde(width, height, g2d);

			escribirTexto(c, Math.round(height * 0.92F), width, height, g2d, metrics);

			break;

		case 3:

			base(width, height, g2d);

			g2d.setColor(c.getBackground());

			esquinaDerecha(width, height, g2d);

			pintarBorde(width, height, g2d);

			if (this.grosor == 0) {

				g2d.setColor(this.bottomColor);

				g2d.fillRect(Math.round(width * 0.1F), Math.round(height * 0.4F), Math.round(width * 0.8F),
						Math.round(height * 0.28F));

				g2d.setColor(this.separador);

				g2d.drawRect(Math.round(width * 0.1F), Math.round(height * 0.4F), Math.round(width * 0.8F),
						Math.round(height * 0.28F));

			}

			else {

				g2d.setColor(this.bottomColor);

				g2d.fillRoundRect(Math.round(width * 0.1F), Math.round(height * 0.4F), Math.round(width * 0.8F),
						Math.round(height * 0.28F), 25, 25);

				g2d.setColor(this.separador);

				g2d.drawRoundRect(Math.round(width * 0.1F), Math.round(height * 0.4F), Math.round(width * 0.8F),
						Math.round(height * 0.28F), 25, 25);

			}

			escribirTexto(c, Math.round(height * 0.64F), width, height, g2d, metrics);

			break;

		case 4:

			base(width, height, g2d);

			g2d.setColor(c.getBackground());

			esquinaDerecha(width, height, g2d);

			pintarBorde(width, height, g2d);

			ye = (Math.round(height * 0.96F) + c.getFont().getSize() / 14) - 1;

			restarEscribirTexto(c, 2, ye, width, height, g2d, metrics);

			ye = Math.round(height * 0.95F) - c.getFont().getSize() / 2 + 2;

			if (c.getFont().getSize() > 50)

				c.setFont(c.getFont().deriveFont(50.0F));

			g2d.setColor(this.separador);

			if (c.getFont().getSize() < 45) {

				g2d.drawArc(Math.round(width * 0.1F),
						ye - ye / 12 + JMthos.calcularSucesionAritmeticaAInt("35#2,40#5", c.getFont().getSize()),
						Math.round(width * 0.8F), 100, 0, 180);

			}

			else {

				g2d.drawArc(Math.round(width * 0.1F),
						ye - ye / 18 + JMthos.calcularSucesionAritmeticaAInt("45#0,50#2", c.getFont().getSize()),
						Math.round(width * 0.8F), 100, 0, 180);

			}

			break;

		case 5:

			figura2(c, g, width, height, g2d);

			g2d.setColor(this.bottomColor);

			if (this.grosor == 0) {

				g2d.fillRect(this.grosor, Math.round(height * 0.65F) - c.getFont().getSize(), Math.round(width * 0.55F),
						Math.round(height * 0.23F));

				g.setColor(this.borde);

				g2d.drawRect(this.grosor, Math.round(height * 0.65F) - c.getFont().getSize(), Math.round(width * 0.55F),
						Math.round(height * 0.23F));

			}

			else {

				g2d.fillRoundRect(this.grosor, Math.round(height * 0.65F) - c.getFont().getSize(),
						Math.round(width * 0.55F), Math.round(height * 0.23F), this.angle, this.angle);

				g.setColor(this.borde);

				g2d.drawRoundRect(this.grosor, Math.round(height * 0.65F) - c.getFont().getSize(),
						Math.round(width * 0.55F), Math.round(height * 0.23F), this.angle, this.angle);

			}

			escribirTexto(c, Math.round(width * 0.085F), Math.round(height * 0.63F), width, height, g2d, metrics);

			break;

		case 6:

			figura2(c, g, width, height, g2d);

			g.setColor(this.borde);

			if (this.grosor < 2) {

				grosor = 2;

			}

			g.setColor(this.bottomColor);

			g2d.fillRoundRect(this.grosor, Math.round(height * 0.65F) - c.getFont().getSize(), width - this.grosor * 2,
					Math.round(height * 0.23F), this.angle, this.angle);

			g.setColor(this.borde);

			g2d.drawRoundRect(this.grosor, Math.round(height * 0.65F) - c.getFont().getSize(), width - this.grosor * 2,
					Math.round(height * 0.23F), this.angle, this.angle);

			escribirTexto(c, Math.round(width * 0.1F), Math.round(height * 0.63F), width, height, g2d, metrics);

			break;

		case 7:

			base(width, height, g2d);

			g2d.setColor(c.getBackground());

			esquinaDerecha(width, height, g2d);

			pintarBorde(width, height, g2d);

			if (this.grosor == 0) {

				g2d.setColor(this.bottomColor);

				g2d.fillRect(Math.round(width * 0.1F), Math.round(height * 0.3F), Math.round(width * 0.8F),
						Math.round(height * 0.6F));

				g2d.setColor(this.separador);

				g2d.drawRect(Math.round(width * 0.1F), Math.round(height * 0.3F), Math.round(width * 0.8F),
						Math.round(height * 0.6F));

				if (this.image != null)

					g2d.drawImage(this.image, Math.round(width * 0.1F), Math.round(height * 0.3F),
							Math.round(width * 0.1F) + Math.round(width * 0.8F),
							Math.round(height * 0.3F) + Math.round(height * 0.6F), 0, 0, this.image.getWidth(),
							this.image.getHeight(), null);

			}

			else {

				g2d.setColor(this.bottomColor);

				g2d.fillRoundRect(Math.round(width * 0.1F) - 1, Math.round(height * 0.3F), Math.round(width * 0.8F),
						Math.round(height * 0.6F), 25, 25);

				g2d.setColor(this.separador);

				g2d.drawRoundRect(Math.round(width * 0.09F), Math.round(height * 0.3F), Math.round(width * 0.8F),
						Math.round(height * 0.6F), 25, 25);

				if (this.image != null) {

					g2d.setClip(new RoundRectangle2D.Float(Math.round(width * 0.1F), Math.round(height * 0.3F),
							Math.round(width * 0.8F), Math.round(height * 0.6F), this.angle, this.angle));

					g2d.drawImage(this.image, Math.round(width * 0.1F), Math.round(height * 0.3F),
							Math.round(width * 0.1F) + Math.round(width * 0.8F),
							Math.round(height * 0.3F) + Math.round(height * 0.6F), 0, 0, this.image.getWidth(),
							this.image.getHeight(), null);

				}

			}

			escribirTexto(c, Math.round(height * 0.69F), width, height, g2d, metrics);

			break;

		case 9:

			if (this.grosor == 0) {

				g2d.fillRect(Math.round(width * 0.1F), this.grosor / 2, width - Math.round(width * 0.2F),
						height - this.grosor);

				g2d.fillRect(Math.round(width * 0.1F), this.grosor / 2, width - Math.round(width * 0.2F),
						height - this.grosor);

				g2d.setColor(this.bottomColor);

				g2d.fillRect(1, Math.round(height * 0.5F), width - 2, Math.round(height * 0.4F));

				g2d.setColor(this.borde);

				g2d.drawRect(1, Math.round(height * 0.5F), width - 2, Math.round(height * 0.4F));

				esquinaDerecha2(true, width, height, g2d);

			}

			else {

				g2d.fillRoundRect(Math.round(width * 0.1F), this.grosor / 2, width - Math.round(width * 0.2F),
						height - this.grosor, this.angle, this.angle);

				g2d.setColor(this.bottomColor);

				g2d.fillRoundRect(grosor / 2, Math.round(height * 0.5F), width - grosor, Math.round(height * 0.4F),
						this.angle, this.angle);

				g2d.setColor(this.borde);

				g2d.drawRoundRect(grosor / 2, Math.round(height * 0.5F), width - grosor, Math.round(height * 0.4F),
						this.angle, this.angle);

				esquinaDerecha4(width, height, g2d);

			}

			escribirTexto(c, Math.round(height * 0.8F), width, height, g2d, metrics);

			break;

		case 10:

			if (this.grosor == 0) {

				g2d.fillRect(Math.round(width * 0.1F), this.grosor / 2, width - Math.round(width * 0.2F),
						height - this.grosor);

				g2d.fillRect(Math.round(width * 0.1F), this.grosor / 2, width - Math.round(width * 0.2F),
						height - this.grosor);

				g2d.setColor(this.bottomColor);

				g2d.fillRect(1, Math.round(height * 0.5F), width - 2, Math.round(height * 0.4F));

				g2d.setColor(this.borde);

				g2d.drawRect(1, Math.round(height * 0.5F), width - 2, Math.round(height * 0.4F));

				esquinaDerecha2(true, width, height, g2d);

			}

			else {

				g2d.fillRoundRect(1, this.grosor / 2, Math.round(width * 0.9F), height - this.grosor, this.angle,
						this.angle);

				g2d.setColor(this.bottomColor);

				g2d.fillRoundRect(Math.round(width * 0.2F), Math.round(height * 0.5F), Math.round(width * 0.66F),
						Math.round(height * 0.4F), this.angle, this.angle);

				g2d.setColor(this.borde);

				g2d.drawRoundRect(Math.round(width * 0.2F), Math.round(height * 0.5F), Math.round(width * 0.66F),
						Math.round(height * 0.4F), this.angle, this.angle);

				esquinaDerecha5(width, height, g2d);

			}

			escribirTexto(c, Math.round(height * 0.8F), width, height, g2d, metrics);

			break;

		case 11:

			base(width, height, g2d);

			g2d.setColor(c.getBackground());

			esquinaDerecha(width, height, g2d);

			pintarBorde(width, height, g2d);

			g2d.setColor(this.separador);

			g2d.drawLine(this.grosor, Math.round(height * 0.76F), width - this.grosor, Math.round(height * 0.76F));

			escribirTexto(c, Math.round(height * 0.95264F), width, height, g2d, metrics);

			if (this.grosor == 0) {

				g2d.setColor(this.bottomColor);

				g2d.fillRect(Math.round(width * 0.1F), Math.round(height * 0.4F), Math.round(width * 0.8F),
						Math.round(height * 0.28F));
				try {

					g2d.drawImage(this.image, Math.round(width * 0.1F), Math.round(height * 0.25F),
							Math.round(width * 0.1F) + Math.round(width * 0.8F),
							Math.round(height * 0.25F) + Math.round(height * 0.46F), 0, 0, this.image.getWidth(),
							this.image.getHeight(), null);

				}

				catch (Exception exception) {

				}

			}

			else {

				g2d.setColor(this.bottomColor);

				g2d.fillRoundRect(Math.round(width * 0.1F), Math.round(height * 0.25F), Math.round(width * 0.8F),
						Math.round(height * 0.46F), 25, 25);

				g2d.setClip(new RoundRectangle2D.Float(Math.round(width * 0.1F), Math.round(height * 0.25F),
						Math.round(width * 0.8F), Math.round(height * 0.46F), 25.0F, 25.0F));

				try {

					g2d.drawImage(this.image, Math.round(width * 0.1F), Math.round(height * 0.25F),
							Math.round(width * 0.1F) + Math.round(width * 0.8F),
							Math.round(height * 0.25F) + Math.round(height * 0.46F), 0, 0, this.image.getWidth(),
							this.image.getHeight(), null);

				}

				catch (Exception exception) {

				}

			}

			break;

		case 8:

			base(width, height, g2d);

			g2d.setColor(c.getBackground());

			esquinaDerecha(width, height, g2d);

			g2d.setColor(this.bottomColor);

			if (this.grosor == 0) {

				g2d.fillRect(2, Math.round(height * 0.76F), width - 4, Math.round(height * 0.24F) - 2);

			}

			else {

				g2d.fillRect(this.grosor, Math.round(height * 0.76F), width - this.grosor * 2,
						Math.round(height * 0.2F));

				g2d.fillRoundRect(this.grosor, Math.round(height * 0.76F), width - this.grosor * 2,
						Math.round(height * 0.24F) - this.grosor, this.angle, this.angle);

			}

			pintarBorde(width, height, g2d);

			g2d.setColor(this.separador);

			g2d.drawLine(0, Math.round(height * 0.76F), width - this.grosor * 2, Math.round(height * 0.76F));

			escribirTexto(c, Math.round(height * 0.9F) + 5, width, height, g2d, metrics);

			dash1 = new float[] { 10.0F };

			dashed = new BasicStroke(this.grosor, 0, 0, 10.0F, dash1, 0.0F);

			g2d.setStroke(dashed);

			circleX = Math.round(width * 0.4F);

			g2d.setColor(this.fondoCirculoFigure12);

			g2d.fillOval(Math.round(width * 0.3F) + this.grosor * 2, Math.round(height * 0.3F) + this.grosor * 2,
					circleX - this.grosor * 4, circleX - this.grosor * 4);

			g2d.setColor(this.bordeCirculoFigure12);

			g2d.drawOval(Math.round(width * 0.3F), Math.round(height * 0.3F), circleX, circleX);

			equis = new int[3];

			ye2 = new int[3];

			equis[0] = Math.round(width * 0.47F);

			equis[1] = Math.round(width * 0.47F);

			equis[2] = Math.round(width * 0.6F);

			ye2[0] = Math.round(height * 0.45F);

			ye2[1] = Math.round(height * 0.65F);

			ye2[2] = Math.round(height * 0.55F);

			break;

		default:

			base(width, height, g2d);

			esquinaDerecha(width, height, g2d);

			g2d.setColor(this.bottomColor);

			if (this.grosor == 0) {

				g2d.fillRect(2, Math.round(height * 0.75F) + 1, width - 3, Math.round(height * 0.25F) - 2);

			}

			else {

				if (c.getWidth() < c.getHeight()) {

					g2d.fillRect(this.grosor, Math.round(height * 0.75F), width - grosor * 2,
							Math.round(height * 0.2F));

					g2d.fillRoundRect(grosor, Math.round(height * 0.75F) + 3, (width - grosor * 2),
							Math.round(height * 0.24F), this.angle, this.angle);

					g2d.fillRoundRect(0, Math.round(height * 0.88F), 20, 23, this.angle, this.angle);

					g2d.setColor(fondo);

					g2d.drawLine(0, Math.round(height * 0.9F), 0, Math.round(height * 0.95F));

				}

				else {

					g2d.fillRect(this.grosor, Math.round(height * 0.75F), width, Math.round(height * 0.2F));

					g2d.fillRoundRect(1, Math.round(height * 0.75F) + 3, width, Math.round(height * 0.23F), this.angle,
							this.angle);

					g2d.fillRoundRect(0, Math.round(height * 0.88F), 20, 23, this.angle, this.angle);

					g2d.setColor(fondo);

					g2d.drawLine(0, Math.round(height * 0.9F), 0, Math.round(height * 0.95F));

				}

			}

			g2d.setColor(this.lineas);

			g2d.drawLine(Math.round(width * 0.2F), Math.round(height * 0.2F), Math.round(width * 0.7F),
					Math.round(height * 0.2F));

			g2d.drawLine(Math.round(width * 0.2F), Math.round(height * 0.3F), Math.round(width * 0.7F),
					Math.round(height * 0.3F));

			g2d.drawLine(Math.round(width * 0.2F), Math.round(height * 0.4F), Math.round(width * 0.7F),
					Math.round(height * 0.4F));

			g2d.drawLine(Math.round(width * 0.2F), Math.round(height * 0.5F), Math.round(width * 0.7F),
					Math.round(height * 0.5F));

			g2d.drawLine(Math.round(width * 0.2F), Math.round(height * 0.6F), Math.round(width * 0.7F),
					Math.round(height * 0.6F));

			g2d.drawLine(Math.round(width * 0.2F), Math.round(height * 0.7F), Math.round(width * 0.7F),
					Math.round(height * 0.7F));

			pintarBorde(width, height, g2d);

			escribirTexto(c, Math.round(height * 0.95F), width, height, g2d, metrics);

			break;

		}

	}

	private void esquinaDerecha5(int width, int height, Graphics2D g2d) {

		int[] equis = new int[3];

		int[] ye = new int[3];

		if (this.pintarLineas) {

			g2d.setColor(this.lineas);

			g2d.drawLine(Math.round(width * 0.2F), Math.round(height * 0.1F), Math.round(width * 0.72F),
					Math.round(height * 0.1F));

			g2d.drawLine(Math.round(width * 0.2F), Math.round(height * 0.2F), Math.round(width * 0.72F),
					Math.round(height * 0.2F));

			g2d.drawLine(Math.round(width * 0.2F), Math.round(height * 0.3F), Math.round(width * 0.72F),
					Math.round(height * 0.3F));

			g2d.drawLine(Math.round(width * 0.2F), Math.round(height * 0.4F), Math.round(width * 0.72F),
					Math.round(height * 0.4F));

		}

		g2d.setColor(this.fondo);

		if (this.grosor > 0) {

			equis[0] = Math.round(width * 0.8F);

			equis[1] = width - this.grosor;

			equis[2] = width;

			ye[0] = this.grosor / 2;

			ye[1] = this.grosor / 2;

			ye[2] = Math.round(height * 0.2F) + 2;

			g2d.fillPolygon(equis, ye, 3);

		}

		else {

			equis[0] = Math.round(width * 0.79F);

			equis[1] = width;

			equis[2] = width;

			ye[0] = -1;

			ye[1] = -1;

			ye[2] = Math.round(height * 0.2F) + 1;

			g2d.fillPolygon(equis, ye, 3);

		}

		g2d.setColor(this.reverse);

		if (grosor > 4) {

			equis[0] = Math.round(width * 0.8F);

			equis[1] = Math.round(width * 0.8F);

			equis[2] = Math.round(width * 0.9F);

			ye[0] = Math.round(height * 0.001F) + 2;

			ye[1] = Math.round(height * 0.1F) + 2;

			ye[2] = Math.round(height * 0.1F) + 2;

			g2d.fillPolygon(equis, ye, 3);

		}

		else {

			equis[0] = Math.round(width * 0.8F);

			equis[1] = Math.round(width * 0.8F);

			equis[2] = Math.round(width * 0.9F) + 2;

			ye[0] = Math.round(height * 0.001F) + 2;

			ye[1] = Math.round(height * 0.1F) + 3;

			ye[2] = Math.round(height * 0.1F) + 3;

			g2d.fillPolygon(equis, ye, 3);

		}

	}

	private void esquinaDerecha4(int width, int height, Graphics2D g2d) {

		int[] equis = new int[3];

		int[] ye = new int[3];

		if (this.pintarLineas) {

			g2d.setColor(this.lineas);

			g2d.drawLine(Math.round(width * 0.2F), Math.round(height * 0.1F), Math.round(width * 0.72F),
					Math.round(height * 0.1F));

			g2d.drawLine(Math.round(width * 0.2F), Math.round(height * 0.2F), Math.round(width * 0.72F),
					Math.round(height * 0.2F));

			g2d.drawLine(Math.round(width * 0.2F), Math.round(height * 0.3F), Math.round(width * 0.72F),
					Math.round(height * 0.3F));

			g2d.drawLine(Math.round(width * 0.2F), Math.round(height * 0.4F), Math.round(width * 0.72F),
					Math.round(height * 0.4F));

		}

		g2d.setColor(this.fondo);

		if (this.grosor > 0) {

			equis[0] = Math.round(width * 0.8F);

			equis[1] = width - this.grosor;

			equis[2] = width;

			ye[0] = this.grosor / 2;

			ye[1] = this.grosor / 2;

			ye[2] = Math.round(height * 0.2F) + 2;

			g2d.fillPolygon(equis, ye, 3);

		}

		else {

			equis[0] = Math.round(width * 0.79F);

			equis[1] = width;

			equis[2] = width;

			ye[0] = -1;

			ye[1] = -1;

			ye[2] = Math.round(height * 0.2F) + 1;

			g2d.fillPolygon(equis, ye, 3);

		}

		g2d.setColor(this.reverse);

		if (grosor > 4) {

			equis[0] = Math.round(width * 0.8F);

			equis[1] = Math.round(width * 0.8F);

			equis[2] = Math.round(width * 0.9F);

			ye[0] = Math.round(height * 0.001F) + 2;

			ye[1] = Math.round(height * 0.1F) + 2;

			ye[2] = Math.round(height * 0.1F) + 2;

			g2d.fillPolygon(equis, ye, 3);

		}

		else {

			equis[0] = Math.round(width * 0.8F);

			equis[1] = Math.round(width * 0.8F);

			equis[2] = Math.round(width * 0.9F);

			ye[0] = Math.round(height * 0.001F) + 1;

			ye[1] = Math.round(height * 0.1F) + 1;

			ye[2] = Math.round(height * 0.1F) + 1;

			g2d.fillPolygon(equis, ye, 3);

		}

	}

	private void figura2(Component c, Graphics g, int width, int height, Graphics2D g2d) {

		g2d.setColor(c.getBackground());

		if (this.grosor == 0) {

			g.fillRect(Math.round(width * 0.1F), 0, Math.round(width * 0.9F), height);

		}

		else {

			g.fillRoundRect(Math.round(width * 0.1F), 2, Math.round(width * 0.89F), Math.round(height * 0.99F) + 1,
					this.angle, this.angle);

		}

		esquinaDerecha3(width, height, g2d);

		g.setColor(this.borde);

		if (this.grosor == 0) {

			g2d.drawLine(Math.round(width * 0.1F), 0, Math.round(width * 0.1F), Math.round(height * 0.7F));

			g2d.drawLine(Math.round(width * 0.1F), 0, Math.round(width * 0.79F), 0);

			g2d.drawLine(Math.round(width * 0.79F), 0, Math.round(width * 0.79F), Math.round(height * 0.2F));

			g2d.drawLine(Math.round(width * 0.79F), Math.round(height * 0.2F), width, Math.round(height * 0.2F));

			g2d.drawLine(Math.round(width * 0.79F), 0, width, Math.round(height * 0.2F));

			g2d.drawLine(Math.round(width * 0.1F), Math.round(height * 0.66F), Math.round(width * 0.1F), height);

			g2d.drawLine(Math.round(width * 0.1F), height - 1, width, height - 1);

			g2d.drawLine(width - 1, Math.round(height * 0.2F), width - 1, height);

		}

		else {

			g.drawLine(Math.round(width * 0.15F), 2, Math.round(width * 0.8F), 2);

			g.drawLine(Math.round(width * 0.1F), Math.round(height * 0.05F), Math.round(width * 0.1F),
					Math.round(height * 0.8F));

			g.drawLine(Math.round(width * 0.1F), Math.round(height * 0.66F), Math.round(width * 0.1F),
					Math.round(height * 0.96F));
			g.drawLine(Math.round(width * 0.8F) + 1, 2, Math.round(width * 0.99F) - 2, Math.round(height * 0.19F));

			g.drawLine(Math.round(width * 0.985F), Math.round(height * 0.195F), Math.round(width * 0.985F),
					Math.round(height * 0.95F));

			g.drawLine(Math.round(width * 0.8F), 2, Math.round(width * 0.8F), Math.round(height * 0.12F));

			g.drawLine(Math.round(width * 0.85F), Math.round(height * 0.2F), Math.round(width * 0.985F),
					Math.round(height * 0.2F));

			g.drawArc(Math.round(width * 0.095F) + 1, 2, Math.round(width * 0.1F), Math.round(width * 0.06F), 180, -90);

			g.drawArc(Math.round(width * 0.097F) + 1, Math.round(height * 0.92F), Math.round(width * 0.06F),
					Math.round(width * 0.1F) - 14, 180, 90);

			g.drawLine(Math.round(width * 0.13F), height - 1, Math.round(width * 0.96F), height - 1);

			g.drawArc(Math.round(width * 0.8F), Math.round(height * 0.03F) + 1, Math.round(width * 0.1F),
					Math.round(width * 0.13F), 180, 90);

			g.drawArc(Math.round(width * 0.93F) + 1, Math.round(height * 0.89F) + 2, Math.round(width * 0.1F) - 16,
					Math.round(width * 0.075F), 0, -90);

		}

	}

	private void base(int width, int height, Graphics2D g2d) {

		if (this.grosor == 0) {

			g2d.fillRect(1, 1, width, height - 2);

		}

		else {

			g2d.fillRoundRect(this.grosor, (this.grosor), (width - this.grosor * 2), (height - this.grosor) - 2,
					this.angle, this.angle);

		}

	}

	private void escribirTexto(Component c, int posX, int posY, int width, int height, Graphics2D g2d,
			FontMetrics metrics) {

		g2d.setFont(c.getFont());

		g2d.setColor(c.getForeground());

		g2d.drawString(this.texto, posX, posY);

	}

	private void restarEscribirTexto(Component c, int restar, int posY, int width, int height, Graphics2D g2d,
			FontMetrics metrics) {

		if (this.texto == null)
			this.texto = "";

		g2d.setFont(c.getFont());

		g2d.setColor(c.getForeground());

		int textWidth = metrics.stringWidth(this.texto);

		int posX = ((width - textWidth) / 2) - restar;

		g2d.drawString(this.texto, posX, posY);

	}

	private void escribirTexto(Component c, int posY, int width, int height, Graphics2D g2d, FontMetrics metrics) {

		if (this.texto == null) {

			this.texto = "";
		}

		g2d.setFont(c.getFont());

		g2d.setColor(c.getForeground());

		int textWidth = metrics.stringWidth(this.texto);

		int posX = (width - textWidth) / 2;

		g2d.drawString(this.texto, posX, posY);

	}

	private void pintarBorde(int width, int height, Graphics2D g2d) {

		g2d.setColor(this.borde);

		if (this.grosor > 0) {

			if (c.getWidth() == c.getHeight()) {

				g2d.drawArc((this.grosor / 2), Math.round(height * 0.95F), Math.round(width * 0.05F),
						Math.round(width * 0.04F), 180, 90);

				g2d.drawArc(Math.round(width * 0.93F), Math.round(height * 0.92F), Math.round(width * 0.06F),
						Math.round(width * 0.07F), 0, -90);

				g2d.drawLine(Math.round(width * 0.02F), height - 2, Math.round(width * 0.97F), height - 2);

			}

			else {

				if (c.getWidth() < c.getHeight()) {

					g2d.drawArc(this.grosor / 2, Math.round(height * 0.91F), Math.round(width * 0.1F),
							Math.round(width * 0.2F), 180, 90);

					g2d.drawArc(Math.round(width * 0.89F), Math.round(height * 0.95F), Math.round(width * 0.1F),
							Math.round(width * 0.1F), 0, -90);

					g2d.drawLine(Math.round(width * 0.05F), this.grosor / 2, Math.round(width * 0.8F), this.grosor / 2);

					g2d.drawLine(this.grosor / 2, Math.round(height * 0.04F), this.grosor / 2,
							Math.round(height * 0.96F));

					g2d.drawArc(this.grosor / 2, grosor, Math.round(width * 0.1F), Math.round(width * 0.14F), 180, -90);

					g2d.drawLine(Math.round(width * 0.04F), height - 1, Math.round(width * 0.95F), height - 1);

				}

				else {

					g2d.drawArc((this.grosor / 2) + 1, Math.round(height * 0.94F), Math.round(width * 0.05F),
							Math.round(width * 0.04F) - 1, 180, 90);

					g2d.drawArc(Math.round(width * 0.93F), Math.round(height * 0.92F) + 2, Math.round(width * 0.07F),
							Math.round(width * 0.05F), 0, -90);

					g2d.drawLine(this.grosor / 2, Math.round(height * 0.03F), this.grosor / 2,
							Math.round(height * 0.97F));

					g2d.drawLine(Math.round(width * 0.02F), height - 4, Math.round(width * 0.97F), height - 4);

					g2d.drawArc(2, -3, Math.round(width * 0.2F), Math.round(width * 0.05F), 180, -90);

				}

			}

			if (c.getWidth() == c.getHeight()) {

				g2d.drawLine(width - this.grosor, Math.round(height * 0.2F), width - this.grosor,
						Math.round(height * 0.98F));

			}

			else {

				g2d.drawLine(width - this.grosor, Math.round(height * 0.2F), width - this.grosor,
						Math.round(height * 0.97F));

			}

			g2d.drawLine(Math.round(width * 0.8F), this.grosor, width - this.grosor, Math.round(height * 0.2F));

			g2d.drawLine(Math.round(width * 0.8F), this.grosor, Math.round(width * 0.8F), Math.round(height * 0.1F));

			g2d.drawLine(Math.round(width * 0.85F), Math.round(height * 0.2F), width - this.grosor,
					Math.round(height * 0.2F));

			g2d.drawArc(Math.round(width * 0.8F), Math.round(height * 0.001F), Math.round(width * 0.1F),
					Math.round(height * 0.2F), 180, 90);

			if (!(c.getWidth() < c.getHeight())) {

				g2d.drawLine(Math.round(width * 0.04F), this.grosor / 2, Math.round(width * 0.8F), this.grosor / 2);

				g2d.drawLine(this.grosor / 2, Math.round(height * 0.04F), this.grosor / 2, Math.round(height * 0.97F));

			}

			g2d.setColor(this.fondo);

			g2d.fillRect(width - this.grosor / 2, 0, this.grosor, height);

			g2d.drawLine((width - grosor / 2), Math.round(height * 0.2F),

					(width - grosor / 2), Math.round(height * 0.21F));

		}

		else {

			g2d.drawLine(width - 1, Math.round(height * 0.2F), width - 1, height - 1);

			g2d.drawLine(1, 1, Math.round(width * 0.8F), 1);

			g2d.drawLine(Math.round(width * 0.79F) + 3, 1, width - 1, Math.round(height * 0.2F));

			g2d.drawLine(Math.round(width * 0.79F), 1, Math.round(width * 0.79F), Math.round(height * 0.2F));

			g2d.drawLine(Math.round(width * 0.79F), Math.round(height * 0.2F), width - 1, Math.round(height * 0.2F));

			g2d.drawLine(1, height - 1, width - 1, height - 1);

			g2d.drawLine(1, 1, 1, height - 1);

			g2d.drawLine(1, 1, Math.round(width * 0.79F), 1);

		}

	}

	private void esquinaDerecha2(boolean cero, int width, int height, Graphics2D g2d) {

		int[] equis = new int[3];

		int[] ye = new int[3];

		if (this.pintarLineas) {

			g2d.setColor(this.lineas);

			g2d.drawLine(Math.round(width * 0.2F), Math.round(height * 0.1F), Math.round(width * 0.72F),
					Math.round(height * 0.1F));

			g2d.drawLine(Math.round(width * 0.2F), Math.round(height * 0.2F), Math.round(width * 0.72F),
					Math.round(height * 0.2F));

			g2d.drawLine(Math.round(width * 0.2F), Math.round(height * 0.3F), Math.round(width * 0.72F),
					Math.round(height * 0.3F));

			g2d.drawLine(Math.round(width * 0.2F), Math.round(height * 0.4F), Math.round(width * 0.72F),
					Math.round(height * 0.4F));

		}

		g2d.setColor(this.fondo);

		if (this.grosor > 0) {

			equis[0] = Math.round(width * 0.8F);

			equis[1] = width - this.grosor;

			equis[2] = width;

			ye[0] = this.grosor / 2;

			ye[1] = this.grosor / 2;

			ye[2] = Math.round(height * 0.2F) + 2;

			g2d.fillPolygon(equis, ye, 3);

		}

		else {

			if (cero) {

				equis[0] = Math.round(width * 0.79F);

				equis[1] = width;

				equis[2] = width;

				ye[0] = -1;

				ye[1] = -1;

				ye[2] = Math.round(height * 0.2F) + 1;

				g2d.fillPolygon(equis, ye, 3);

			}

			else {

				equis[0] = Math.round(width * 0.79F);

				equis[1] = width;

				equis[2] = width;

				ye[0] = 0;

				ye[1] = 0;

				ye[2] = Math.round(height * 0.2F) + 1;

				g2d.fillPolygon(equis, ye, 3);

			}

		}

		g2d.setColor(this.reverse);

		if (this.grosor > 0) {

			equis[0] = Math.round(width * 0.8F);

			equis[1] = Math.round(width * 0.8F);

			equis[2] = Math.round(width * 0.9F);

			ye[0] = Math.round(height * 0.001F);

			ye[1] = Math.round(height * 0.06F);

			ye[2] = Math.round(height * 0.1F) + 1;

			g2d.fillPolygon(equis, ye, 3);

			equis[0] = Math.round(width * 0.85F);

			equis[1] = Math.round(width * 0.85F);

			equis[2] = Math.round(width * 0.9F);

			ye[0] = Math.round(height * 0.05F);

			ye[1] = Math.round(height * 0.1F);

			ye[2] = Math.round(height * 0.1F);

			g2d.fillPolygon(equis, ye, 3);

			g2d.fillArc(Math.round(width * 0.8F), Math.round(height * 0.01F), Math.round(width * 0.1F),
					Math.round(height * 0.09F), 180, 90);

		}

		else if (cero) {

			equis[0] = Math.round(width * 0.79F);

			equis[1] = Math.round(width * 0.79F);

			equis[2] = Math.round(width * 0.9F) - 1;

			ye[0] = Math.round(height * 0.001F) - 1;

			ye[1] = Math.round(height * 0.1F) + 1;

			ye[2] = Math.round(height * 0.1F) + 1;

			g2d.fillPolygon(equis, ye, 3);

		}

		else {

			equis[0] = Math.round(width * 0.8F);

			equis[1] = Math.round(width * 0.8F);

			equis[2] = Math.round(width * 0.9F);

			ye[0] = Math.round(height * 0.001F);

			ye[1] = Math.round(height * 0.1F) + 1;

			ye[2] = Math.round(height * 0.1F) + 1;

			g2d.fillPolygon(equis, ye, 3);

		}

	}

	private void esquinaDerecha3(int width, int height, Graphics2D g2d) {

		int[] equis = new int[3];

		int[] ye = new int[3];

		g2d.setStroke(new BasicStroke(this.grosor));

		g2d.setColor(this.fondo);

		equis[0] = Math.round(width * 0.8F) - 1;

		equis[1] = width;

		equis[2] = width;

		ye[0] = 0;

		ye[1] = 0;

		ye[2] = Math.round(height * 0.2F) + 1;

		g2d.fillPolygon(equis, ye, 3);

		g2d.setColor(this.reverse);

		if (this.grosor > 0) {

			equis[0] = Math.round(width * 0.8F) + 1;

			equis[1] = Math.round(width * 0.8F) + 1;

			equis[2] = (width - this.grosor) - 2;

			ye[0] = Math.round(height * 0.01F);

			ye[1] = Math.round(height * 0.135F);

			ye[2] = Math.round(height * 0.2F) - 2;

			g2d.fillPolygon(equis, ye, 3);

			equis[0] = Math.round(width * 0.85F);

			equis[1] = Math.round(width * 0.85F);

			equis[2] = (width - this.grosor) - 2;

			ye[0] = Math.round(height * 0.1F);

			ye[1] = Math.round(height * 0.2F) - 1;

			ye[2] = Math.round(height * 0.2F) - 1;

			g2d.fillPolygon(equis, ye, 3);

			g2d.fillArc(Math.round(width * 0.8F), Math.round(height * 0.005F) + 1, Math.round(width * 0.1F) + 1,
					Math.round(height * 0.2F) - 1, 180, 90);

			g2d.fillRect(Math.round(width * 0.85F), Math.round(height * 0.2F) - grosor, Math.round(width * 0.14F),
					Math.round(height * 0.01F) / 2);

			g2d.drawLine(Math.round(width * 0.85F), Math.round(height * 0.1F), Math.round(width * 0.85F),
					Math.round(height * 0.2F) - 1);

			g2d.drawLine(Math.round(width * 0.845F), Math.round(height * 0.1F), Math.round(width * 0.845F),
					Math.round(height * 0.2F) - 1);

		}

		else {

			equis[0] = Math.round(width * 0.803F) - 4;

			equis[1] = Math.round(width * 0.8012F) - 4;

			equis[2] = width;

			ye[0] = Math.round(height * 0.001F) - 1;

			ye[1] = Math.round(height * 0.2F);

			ye[2] = Math.round(height * 0.2F);

			g2d.fillPolygon(equis, ye, 3);

		}

	}

	private void esquinaDerecha(int width, int height, Graphics2D g2d) {

		int[] equis = new int[3];

		int[] ye = new int[3];

		g2d.setStroke(new BasicStroke(this.grosor));

		g2d.setColor(this.fondo);

		equis[0] = Math.round(width * 0.8F) - 1;

		equis[1] = width - grosor;

		equis[2] = width - grosor;

		ye[0] = 0;

		ye[1] = 0;

		ye[2] = Math.round(height * 0.2F);

		g2d.fillPolygon(equis, ye, 3);

		g2d.setColor(this.reverse);

		if (this.grosor > 0) {

			equis[0] = Math.round(width * 0.8F);

			equis[1] = Math.round(width * 0.8F);

			equis[2] = width - this.grosor;

			ye[0] = Math.round(height * 0.01F) - 2;

			ye[1] = Math.round(height * 0.135F);

			ye[2] = Math.round(height * 0.2F);

			g2d.fillPolygon(equis, ye, 3);

			equis[0] = Math.round(width * 0.85F);

			equis[1] = Math.round(width * 0.85F);

			equis[2] = (width - this.grosor);

			ye[0] = Math.round(height * 0.1F);

			ye[1] = Math.round(height * 0.2F);

			ye[2] = Math.round(height * 0.2F);

			g2d.fillPolygon(equis, ye, 3);

			g2d.fillArc(Math.round(width * 0.8F), Math.round(height * 0.001F), Math.round(width * 0.1F) + 1,
					Math.round(height * 0.2F), 180, 90);

			g2d.drawLine(Math.round(width * 0.85F), Math.round(height * 0.1F), Math.round(width * 0.85F),
					Math.round(height * 0.2F) - 1);

			g2d.drawLine(Math.round(width * 0.845F), Math.round(height * 0.1F), Math.round(width * 0.845F),
					Math.round(height * 0.2F) - 1);
		}

		else {

			equis[0] = Math.round(width * 0.803F) - 2;

			equis[1] = Math.round(width * 0.8012F) - 4;

			equis[2] = width;

			ye[0] = Math.round(height * 0.001F);

			ye[1] = Math.round(height * 0.2F);

			ye[2] = Math.round(height * 0.2F);

			g2d.fillPolygon(equis, ye, 3);

			g2d.fillRect(Math.round(width * 0.79F), 1, 3, Math.round(height * 0.15F));

		}

	}

}
