package com.buttons.round;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JToolTip;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class NButton extends JButton {

	private Animator animator;

	private int targetSize;

	private float animatSize;

	private Point pressedPoint;

	private float alpha;

	private Color effectColor;

	private boolean borderPaint;

	private Color borderColor;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private int grosor;

	private int round;

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

	public void setBorderColor(Color borderColor) {

		this.borderColor = borderColor;

		repaint();

	}

	public void setThickness(int thickness) {

		if (thickness < 1) {

			thickness = 0;

			setBorderPaint(false);

		}

		else {

			setBorderPaint(true);

		}

		this.grosor = thickness;

		repaint();

	}

	public Color borderColor() {

		return borderColor;

	}

	public void borderColor(Color borderColor) {

		this.borderColor = borderColor;

	}

	public boolean isBorderPaint() {

		return borderPaint;

	}

	public void setBorderPaint(boolean borderPaint) {

		this.borderPaint = borderPaint;

		repaint();

	}

	public Color getEffectColor() {

		return effectColor;

	}

	public void setEffectColor(Color effectColor) {

		this.effectColor = effectColor;

	}

	public void setRound(int round) {

		if (round > 365) {

			round = 365;

		}

		if (round < 0) {

			round = 0;

		}

		this.round = round;

	}

	public NButton(String text) {

		setBackground(Color.PINK);

		effectColor = new Color(173, 173, 173);

		setText(text);

		setFont(getFont().deriveFont(Font.PLAIN, 20f));

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				setFont(getFont());

			}

		});

		setOpaque(true);

		setFocusPainted(false);

		setContentAreaFilled(false);

		addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent me) {

				targetSize = Math.max(getWidth(), getHeight()) * 2;

				animatSize = 0;

				pressedPoint = me.getPoint();

				alpha = 0.5f;

				if (animator.isRunning()) {

					animator.stop();

				}

				animator.start();

			}

		});

		TimingTarget target = new TimingTargetAdapter() {

			@Override

			public void timingEvent(float fraction) {

				if (fraction > 0.5f) {

					alpha = 1 - fraction;

				}

				animatSize = fraction * targetSize;

				repaint();

			}

		};

		borderPaint = true;

		animator = new Animator(400, target);

		animator.setResolution(0);

		setBorder(null);

		setThickness(0);

	}

	@Override

	public void paint(Graphics grphcs) {

		int width = getWidth();

		int height = getHeight();

		Graphics2D g2 = (Graphics2D) grphcs.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(getBackground());

		if (round < 1) {

			round = height;

		}

		g2.fillRoundRect(0, 0, width - 3, height - 3, round, round);

		g2.setColor(getForeground());

		if (pressedPoint != null) {

			Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, round, round));

			g2.setColor(effectColor);

			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

			area.intersect(new Area(new Ellipse2D.Double((pressedPoint.x - animatSize / 2),
					(pressedPoint.y - animatSize / 2), animatSize, animatSize)));

			g2.fill(area);

		}

		g2.setComposite(AlphaComposite.SrcOver);

		if (borderPaint) {

			g2.setColor(borderColor);

			if (grosor > 0) {

				g2.setStroke(new BasicStroke(grosor));

			}

			g2.drawRoundRect(0, 0, width - 3, height - 3, round, round);

		}

		super.paint(grphcs);

	}

}
