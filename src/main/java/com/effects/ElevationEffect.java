package com.effects;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;

import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.interpolators.SplineInterpolator;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;

public class ElevationEffect {

	private final SwingTimerTimingSource timer;

	protected final JComponent target;

	private Animator animator;

	protected final SafePropertySetter.Property<Double> level;

	protected final MaterialShadow shadow;

	protected int targetLevel;

	protected int borderRadius;

	private ElevationEffect(final JComponent component, int level) {

		targetLevel = 0;

		borderRadius = 2;

		this.target = component;

		timer = new SwingTimerTimingSource();

		timer.init();

		this.level = SafePropertySetter.animatableProperty(target, (double) level);

		this.targetLevel = level;

		shadow = new MaterialShadow();

	}

	public int getLevel() {

		return targetLevel;

	}

	public void setLevel(int level) {

		if (target.isShowing()) {

			if (level != targetLevel) {

				if (animator != null) {

					animator.stop();

				}

				animator = new Animator.Builder(timer).setDuration(500, TimeUnit.MILLISECONDS)
						.setEndBehavior(Animator.EndBehavior.HOLD)
						.setInterpolator(new SplineInterpolator(0.55, 0, 0.1, 1))
						.addTarget(SafePropertySetter.getTarget(this.level, this.level.getValue(), (double) level))
						.build();

				animator.start();

			}

			else {

				animator = null;

			}

		}

		else {

			animator = null;

			this.level.setValue((double) level);

		}

		targetLevel = level;

	}

	public int getBorderRadius() {

		return borderRadius;

	}

	public void setBorderRadius(int borderRadius) {

		this.borderRadius = borderRadius;

	}

	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		g2.setBackground(target.getParent().getBackground());

		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

		g.drawImage(shadow.render(target.getWidth(), target.getHeight(), borderRadius, level.getValue(),
				MaterialShadow.Type.SQUARE), 0, 0, null);

	}

	public static ElevationEffect applyTo(JComponent target, int level) {

		return new ElevationEffect(target, level);

	}

	public static ElevationEffect applyCirularTo(JComponent target, int level) {

		return new ElevationEffect.Circular(target, level);

	}

	public static class Circular extends ElevationEffect {

		private Circular(JComponent component, int level) {

			super(component, level);

		}

		@Override

		public void paint(Graphics g) {

			g.drawImage(shadow.render(target.getWidth(), target.getHeight(), borderRadius, level.getValue(),
					MaterialShadow.Type.CIRCULAR), 0, 0, null);

		}

	}

}