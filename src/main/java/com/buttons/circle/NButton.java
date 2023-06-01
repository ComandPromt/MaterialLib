package com.buttons.circle;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
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

	public NButton() {

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				repaint();

			}

		});

		setBorder(null);

		setFocusPainted(false);

		setContentAreaFilled(false);

		setCursor(new Cursor(Cursor.HAND_CURSOR));

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

		g2.drawString(getText(), Mthos.centrarTexto(getText(), getFont(), getWidth()), (int) (getHeight() * 0.60));

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
