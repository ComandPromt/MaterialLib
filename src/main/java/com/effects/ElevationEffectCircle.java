package com.effects;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;

import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.Interpolator;
import org.jdesktop.core.animation.timing.TimingSource;
import org.jdesktop.core.animation.timing.interpolators.SplineInterpolator;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;

public class ElevationEffectCircle {

	private final SwingTimerTimingSource timer;

	protected final JComponent target;

	private Animator animator;

	protected final SafePropertySetter.Property<Double> level;

	protected int targetLevel = 1;

	private ElevationEffectCircle(JComponent component, int level) {

		this.target = component;

		this.timer = new SwingTimerTimingSource();

		this.timer.init();

		this.level = SafePropertySetter.animatableProperty(this.target, Double.valueOf(level));

		this.targetLevel = level;

	}

	public int getLevel() {

		return this.targetLevel;

	}

	public void setLevel(int level) {

		if (level != this.targetLevel) {

			if (this.animator != null)

				this.animator.stop();

			this.animator = (new Animator.Builder((TimingSource) this.timer)).setDuration(500L, TimeUnit.MILLISECONDS)
					.setEndBehavior(Animator.EndBehavior.HOLD)
					.setInterpolator((Interpolator) new SplineInterpolator(0.55D, 0.0D, 0.1D, 1.0D))
					.addTarget(SafePropertySetter.getTarget(this.level,
							new Double[] { this.level.getValue(), Double.valueOf(level) }))
					.build();

			this.animator.start();

		}

		else {

			this.animator = null;

		}

		this.targetLevel = level;

	}

	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		g2.setBackground(this.target.getParent().getBackground());

		g2.setComposite(AlphaComposite.getInstance(3, 1.0F));

		g.drawImage(MaterialShadowCircle.renderCircularShadow(this.target.getWidth(),
				((Double) this.level.getValue()).doubleValue()), 0, 0, null);

	}

	public static ElevationEffectCircle applyTo(JComponent target, int level) {

		return new ElevationEffectCircle(target, level);

	}

	public static ElevationEffectCircle applyCirularTo(JComponent target, int level) {

		return new Circular(target, level);

	}

	public static class Circular extends ElevationEffectCircle {

		private Circular(JComponent component, int level) {

			super(component, level);

		}

		public void paint(Graphics g) {

			g.drawImage(MaterialShadowCircle.renderCircularShadow(this.target.getWidth(),
					((Double) this.level.getValue()).doubleValue()), 0, 0, null);

		}

	}

}
