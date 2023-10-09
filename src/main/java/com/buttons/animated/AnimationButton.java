package com.buttons.animated;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
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
public class AnimationButton extends JButton {

	private Animator animator;

	private int targetSize;

	private float animatSize;

	private Point pressedPoint;

	private float alpha;

	private Color effectColor;

	private int buttonAngle;

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

	public int getAngle() {

		return buttonAngle;

	}

	public void setAngle(int buttonAngle) {

		this.buttonAngle = buttonAngle;

		repaint();

	}

	public Color getEffectColor() {

		return effectColor;

	}

	public void setEffectColor(Color effectColor) {

		this.effectColor = effectColor;

	}

	public AnimationButton(String text) {

		effectColor = new Color(220, 220, 220);

		setText(text);

		setBorder(null);

		setBorderPainted(false);

		setContentAreaFilled(false);

		setFont(getFont().deriveFont(Font.PLAIN, 20f));

		setFocusPainted(false);

		setForeground(Color.BLACK);

		setCursor(new Cursor(Cursor.HAND_CURSOR));

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

		animator = new Animator(400, target);

		animator.setResolution(0);

		setEffectColor(Color.LIGHT_GRAY);

		setBackground(Color.PINK);

	}

	@Override

	protected void paintComponent(Graphics grphcs) {

		Graphics2D g2 = (Graphics2D) grphcs.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int width = getWidth();

		int height = getHeight();

		g2.setColor(getBackground());

		g2.fillRoundRect(0, 0, width, height, buttonAngle, buttonAngle);

		if (pressedPoint != null) {

			Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, height, height));

			g2.setColor(effectColor);

			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

			area.intersect(new Area(new Ellipse2D.Double((pressedPoint.x - animatSize / 2),
					(pressedPoint.y - animatSize / 2), animatSize, animatSize)));

			g2.fill(area);

		}

		g2.dispose();

		super.paintComponent(grphcs);

	}

}
