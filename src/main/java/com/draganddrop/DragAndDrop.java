package com.draganddrop;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.SystemColor;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JLabel;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class DragAndDrop extends JLabel {

	public TitledBorder dragBorder;

	private int thickness;

	private Shape rect;

	private boolean round;

	private boolean dashed;

	private int pattern;

	private int angulo;

	private int altura;

	private String text;

	private Color background;

	private Color foreground;

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

		this.background = background;

		this.foreground = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || background == null || foreground == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, background, foreground, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public int getAngulo() {

		return angulo;

	}

	public void setAngulo(int angulo) {

		this.angulo = angulo;

		repaint();

	}

	public int getPattern() {

		return pattern;

	}

	public void setPattern(int pattern) {

		this.pattern = pattern;

		repaint();

	}

	public boolean isDashed() {

		return dashed;

	}

	public void setDashed(boolean dashed) {

		this.dashed = dashed;

		repaint();

	}

	public boolean isRound() {

		return round;

	}

	public void setRound(boolean round) {

		this.round = round;

		repaint();

	}

	public int getThickness() {

		return thickness;

	}

	public void setThickness(int thickness) {

		if (thickness < 1) {

			thickness = 0;

		}

		this.thickness = thickness;

		repaint();

	}

	public void setAltura(int altura) {

		this.altura = altura;

		repaint();

	}

	public DragAndDrop() {

		this("");

	}

	public DragAndDrop(String text) {

		altura = 0;

		angulo = 20;

		thickness = 1;

		round = true;

		dashed = true;

		pattern = 10;

		setFont(new Font("Dialog", Font.PLAIN, 30));

		setText(text);

		setForeground(SystemColor.desktop);

		setBackground(Color.WHITE);

		this.dragBorder = new javax.swing.border.TitledBorder("");

		setHorizontalAlignment(SwingConstants.CENTER);

		setThickness(3);

	}

	@Override
	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		if (thickness > 0) {

			if (dashed) {

				g2d.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10,
						new float[] { pattern, pattern }, 0));

			}

			else {

				g2d.setStroke(new BasicStroke(thickness));

			}

		}

		if (round) {

			rect = new RoundRectangle2D.Double(thickness, thickness, getWidth() - (thickness * 2),
					getHeight() - (thickness * 2), angulo, angulo);

		}

		else {

			rect = new Rectangle2D.Double(thickness, thickness, getWidth() - (thickness * 2),

					getHeight() - (thickness * 2));

		}

		if (altura > 0) {

			if (round) {

				rect = new RoundRectangle2D.Double(thickness, altura + thickness, (getWidth() - (thickness * 2)),
						(getHeight() - (thickness * 2)) - altura, angulo, angulo);

			}

			else {

				rect = new Rectangle2D.Double(thickness, altura + thickness, (getWidth() - (thickness * 2)),
						(getHeight() - (thickness * 2)) - altura);

			}

		}

		if (thickness > 0) {

			g2d.draw(rect);

		}

		g2d.setColor(getBackground());

		g2d.fill(rect);

		super.paint(g);

	}

}
