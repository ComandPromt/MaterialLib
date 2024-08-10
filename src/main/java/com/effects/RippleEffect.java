package com.effects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;

import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTargetAdapter;
import org.jdesktop.core.animation.timing.interpolators.AccelerationInterpolator;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;

import com.effects.SafePropertySetter.Property;

public class RippleEffect {

	private final List<RippleAnimation> ripples;

	private final JComponent target;

	private final SwingTimerTimingSource timer;

	private Color rippleColor;
	private final Point location;
	private float animate;

	public void setRippleColor(Color rippleColor) {

		this.rippleColor = rippleColor;

	}

	public Color getRippleColor() {

		return rippleColor;

	}

	private double getSize(Rectangle2D rec) {

		double size;

		if (rec.getWidth() > rec.getHeight()) {

			if (location.getX() < rec.getWidth() / 2) {

				size = rec.getWidth() - location.getX();

			}

			else {

				size = location.getX();

			}

		}

		else {

			if (location.getY() < rec.getHeight() / 2) {

				size = rec.getHeight() - location.getY();

			}

			else {

				size = location.getY();

			}

		}

		return size + (size * 0.1f);

	}

	private Shape getShape(double size) {

		double s = size * animate;

		double x = location.getX();

		double y = location.getY();

		Shape shape = new Ellipse2D.Double(x - s, y - s, s * 2, s * 2);

		return shape;

	}

	public void render(Graphics2D g2, Shape contain) {

		Area area = new Area(contain);

		area.intersect(new Area(getShape(getSize(contain.getBounds2D()))));

		g2.setColor(rippleColor);

		float alpha = 0.3f;

		if (animate >= 0.7f) {

			double t = animate - 0.7f;

			alpha = (float) (alpha - (alpha * (t / 0.3f)));

		}

		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

		g2.fill(area);

	}

	public RippleEffect(final JComponent component) {
		this.location = new Point(0, 0);
		rippleColor = new Color(255, 255, 255);
		ripples = new ArrayList<>();

		this.target = component;

		timer = new SwingTimerTimingSource();

		timer.init();

	}

	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		float rippleOpacity;

		Point rippleCenter;

		int rippleRadius;

		Color fg;

		for (RippleAnimation rippleAnimation : ripples) {

			rippleOpacity = rippleAnimation.rippleOpacity.getValue().floatValue();

			rippleCenter = rippleAnimation.rippleCenter;

			rippleRadius = rippleAnimation.rippleRadius.getValue();

			fg = g2.getColor();

			g2.setColor(new Color(fg.getRed() / 255f, fg.getGreen() / 255f, fg.getBlue() / 255f, rippleOpacity));

			g2.fillOval(rippleCenter.x - rippleRadius, rippleCenter.y - rippleRadius, 2 * rippleRadius,
					2 * rippleRadius);

		}

	}

	public void addRipple(Point point, int maxRadius) {

		final RippleAnimation ripple = new RippleAnimation(point, maxRadius);

		ripples.add(ripple);

		ripple.start();

	}

	public static RippleEffect applyTo(final JComponent target) {

		final RippleEffect rippleEffect = new RippleEffect(target);

		target.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				rippleEffect.addRipple(e.getPoint(), target.getWidth());

			}

		});

		return rippleEffect;

	}

	public static RippleEffect applyFixedTo(final JComponent target) {

		final RippleEffect rippleEffect = new RippleEffect(target);

		target.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				rippleEffect.addRipple(new Point(24, 24), target.getWidth() / 2);

			}

		});

		return rippleEffect;

	}

	public class RippleAnimation {

		private final Point rippleCenter;

		private final int maxRadius;

		private final Property<Integer> rippleRadius = SafePropertySetter.animatableProperty(target, 25);

		private final Property<Double> rippleOpacity = SafePropertySetter.animatableProperty(target, 0.0);

		private RippleAnimation(Point rippleCenter, int maxRadius) {

			this.rippleCenter = rippleCenter;

			this.maxRadius = maxRadius;

		}

		void start() {

			Animator rippleAnimator = new Animator.Builder(timer).setDuration(1000, TimeUnit.MILLISECONDS)
					.setEndBehavior(Animator.EndBehavior.HOLD).setInterpolator(new AccelerationInterpolator(0.2, 0.19))
					.addTarget(SafePropertySetter.getTarget(rippleRadius, 0, maxRadius / 2, maxRadius, maxRadius))
					.addTarget(SafePropertySetter.getTarget(rippleOpacity, 0.0, 0.4, 0.3, 0.0))
					.addTarget(new TimingTargetAdapter() {
						@Override
						public void end(Animator source) {
							ripples.remove(RippleAnimation.this);
						}
					}).build();

			rippleAnimator.start();

		}

	}

}
