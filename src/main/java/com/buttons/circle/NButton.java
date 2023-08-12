package com.buttons.circle;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
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

import org.edisoncor.gui.toolTip.ToolTipLlamada;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import util.Mthos;

@SuppressWarnings("serial")

public class NButton extends JButton {

	private Animator animator;

	private int targetSize;

	private float animatSize;

	private Point pressedPoint;

	private float alpha;

	private Color effectColor = new Color(173, 173, 173);

	private boolean borderPaint;

	public Color borderColor;

	private String text;

	private Color background;

	private Color foreground;

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

			font = getFont().deriveFont(14f);

		}

		this.text = text;

		this.background = background;

		this.foreground = foreground;

		this.border = border;

		this.fuente = font;

		setToolTipText(text);

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

	@Override
	public void setFont(Font font) {

		super.setFont(new Font(font.getFamily(), font.getStyle(), calculateTextSize(getText())));

		repaint();

	}

	public int calculateTextSize(String text) {

		int calculo = 0;

		try {

			int size = (getHeight() * 150) / (140);

			int ancho = (size * 100) / 150;

			calculo = (getWidth() * 90) / 380;

			int max = Math.round((float) getWidth() / (float) ancho);

			if (text.length() > max) {

				calculo = (getWidth() * 5 * 35) / (110 * text.length());

				calculo -= 7;

			}

		}

		catch (Exception e) {
		}

		return calculo;

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

	public NButton(String text) {

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				setFont(getFont());

			}

		});

		setBorder(null);

		setFocusPainted(false);

		setContentAreaFilled(false);

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setText(text);

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

	}

	@Override

	public void paint(Graphics grphcs) {

		super.paint(grphcs);

		int width = getWidth();

		int height = getHeight();

		Graphics2D g2 = (Graphics2D) grphcs.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(getBackground());

		g2.fillRoundRect(0, 0, width - 1, height - 1, height, height);

		g2.setColor(getForeground());

		g2.setFont(getFont());

		g2.drawString(getText(), Mthos.centrarTexto(getText(), getFont(), getWidth()), (int) ((getHeight() / 2) * 1.8));

		if (pressedPoint != null) {

			Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, height, height));

			g2.setColor(effectColor);

			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

			area.intersect(new Area(new Ellipse2D.Double((pressedPoint.x - animatSize / 2),
					(pressedPoint.y - animatSize / 2), animatSize, animatSize)));

			g2.fill(area);

		}

		g2.setComposite(AlphaComposite.SrcOver);

		if (borderPaint) {

			g2.setColor(borderColor);

			g2.drawRoundRect(0, 0, width - 1, height - 1, height, height);

		}

	}

}
