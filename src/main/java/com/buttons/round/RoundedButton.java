package com.buttons.round;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

import mthos.JMthos;

@SuppressWarnings("serial")

public class RoundedButton extends JButton {

	private BufferedImage originalImage;

	private int grosor;

	private int radius;

	private Color borderColor;

	private String text;

	private Color back;

	private Color colorTexto;

	private Color border;

	private Font fuente;

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

	}

	public void setGrosor(int grosor) {

		if (grosor < 3 && grosor > 0) {

			grosor = 3;

		}

		this.grosor = grosor;

	}

	public void setRadius(int radius) {

		if (radius < 0) {

			radius = 0;

		}

		if (radius > 365) {

			radius = 365;

		}

		this.radius = radius;

	}

	public RoundedButton(String text) {

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

	}

	@Override
	public void setIcon(Icon defaultIcon) {

		try {

			String ruta = defaultIcon.toString();

			if (System.getProperty("os.name").contains("indows")) {

				ruta = ruta.replace("file:/", "");

			}

			else {

				ruta = ruta.replace("file:", "");

			}

			originalImage = JMthos.loadFileImage(ruta);

			setPreferredSize(new Dimension(originalImage.getWidth(), originalImage.getHeight()));

			repaint();

		}

		catch (Exception e) {

		}

	}

	@Override
	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();

		g.setColor(getBackground());

		g.fillRoundRect(0, 0, getWidth() - grosor, getHeight() - grosor, radius, radius);

		if (grosor > 0) {

			g2d.setStroke(new BasicStroke(grosor));

			g2d.setColor(borderColor);

			g2d.drawRoundRect(grosor / 2, grosor / 2, getWidth() - grosor, getHeight() - grosor, radius, radius);

		}

		Shape mask = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);

		g2d.setClip(mask);

		if (grosor > 2) {

			g2d.drawImage(originalImage, grosor / 2, grosor / 2, getWidth() - (grosor - 1), getHeight() - (grosor - 1),
					this);

		}

		else {

			g2d.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);

		}

		g2d.dispose();

		super.paintComponent(g);

	}

}
