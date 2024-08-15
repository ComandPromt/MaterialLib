package com.jicons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class MovimientoTriangulos extends ImageIcon {

	private Color color;

	private float grosor;

	private boolean parteSuperiorClicada;

	private Component c;

	private Color disableColor;

	private Color selectColor;

	private boolean first;

	private boolean invertColors;

	private boolean click;

	public boolean isParteSuperiorClicada() {

		return parteSuperiorClicada;

	}

	public void setSelectColor(Color selectColor) {

		this.selectColor = selectColor;

		try {

			c.repaint();

		}

		catch (Exception e) {

		}

	}

	public void setDisableColor(Color disableColor) {

		this.disableColor = disableColor;

		try {

			c.repaint();

		}

		catch (Exception e) {

		}

	}

	public void setGrosor(float grosor) {

		this.grosor = grosor;

		try {

			c.repaint();

		}

		catch (Exception e) {

		}

	}

	public void setColor(Color color) {

		this.color = color;

		try {

			c.repaint();

		}

		catch (Exception e) {

		}

	}

	public MovimientoTriangulos() {

		this(Color.BLACK);

	}

	public MovimientoTriangulos(Color color) {

		this.color = color;

		grosor = 1f;

		disableColor = new Color(230, 230, 230);

		selectColor = Color.BLACK;

	}

	@Override
	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {

		this.c = c;

		c.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				parteSuperiorClicada = e.getY() < c.getHeight() / 2;

				click = true;

				c.repaint();

			}

		});

		super.paintIcon(c, g, x, y);

		drawContent(c, g, x, y);

	}

	private void drawContent(Component c, Graphics g, int x, int y) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(c.getBackground());

		g2d.fillRect(0, 0, c.getWidth(), c.getHeight());

		g2d.setStroke(new BasicStroke(grosor));

		g2d.setColor(color);

		int[] equis = new int[3];

		int[] ye = new int[3];

		if (!c.isEnabled() || c.isFocusOwner()) {

			equis[0] = 0;

			equis[1] = c.getWidth() / 2;

			equis[2] = c.getWidth();

			ye[0] = Math.round(c.getWidth() * 0.475f);

			ye[1] = 0;

			ye[2] = Math.round(c.getWidth() * 0.475f);

			g2d.fillPolygon(equis, ye, 3);

			ye[0] = Math.round(c.getHeight() * 0.525f);

			ye[1] = c.getHeight();

			ye[2] = Math.round(c.getHeight() * 0.525f);

			g2d.setColor(disableColor);

			g2d.fillPolygon(equis, ye, 3);

		}

		else {

			if (first) {

				invertColors = parteSuperiorClicada ? c.getHeight() / 4 < c.getHeight() / 2
						: c.getHeight() / 4 >= c.getHeight() / 2;

				equis[0] = 0;

				equis[1] = c.getWidth() / 2;

				equis[2] = c.getWidth();

				ye[0] = Math.round(c.getHeight() * 0.5f);

				ye[1] = 0;

				ye[2] = Math.round(c.getHeight() * 0.5f);

				if (click) {

					parteSuperiorClicada = invertColors;

				}

				else {

					parteSuperiorClicada = !parteSuperiorClicada;

				}

				g2d.setColor(invertColors ? selectColor : disableColor);

				g2d.fillPolygon(equis, ye, 3);

				ye[0] = Math.round(c.getHeight() * 0.525f);

				ye[1] = c.getHeight();

				ye[2] = Math.round(c.getHeight() * 0.525f);

				g2d.setColor(invertColors ? disableColor : selectColor);

				g2d.fillPolygon(equis, ye, 3);

			}

			else {

				equis[0] = 0;

				equis[1] = c.getWidth() / 2;

				equis[2] = c.getWidth();

				ye[0] = Math.round(c.getHeight() * 0.5f);

				ye[1] = 0;

				ye[2] = Math.round(c.getHeight() * 0.5f);

				g2d.fillPolygon(equis, ye, 3);

				ye[0] = Math.round(c.getHeight() * 0.525f);

				ye[1] = c.getHeight();

				ye[2] = Math.round(c.getHeight() * 0.525f);

				g2d.fillPolygon(equis, ye, 3);

			}

		}

		first = true;

	}

}
