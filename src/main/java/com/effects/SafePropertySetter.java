package com.effects;

import java.awt.Component;
import java.util.concurrent.atomic.AtomicReference;

import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.KeyFrames;
import org.jdesktop.core.animation.timing.TimingTarget;
import org.jdesktop.core.animation.timing.TimingTargetAdapter;

public class SafePropertySetter<T> extends TimingTargetAdapter {

	private final AtomicReference<KeyFrames<T>> keyFrames;

	private final boolean isToAnimation;

	private final Getter<T> getter;

	private final Setter<T> setter;

	protected SafePropertySetter(KeyFrames<T> keyFrames, boolean isToAnimation, Getter<T> getter, Setter<T> setter) {

		this.keyFrames = new AtomicReference<>(keyFrames);

		this.isToAnimation = isToAnimation;

		this.getter = getter;

		this.setter = setter;

	}

	public static <T> TimingTarget getTarget(Setter<T> setter, T... values) {
		return (TimingTarget) new SafePropertySetter<>((new KeyFrames.Builder()).addFrames((Object[]) values).build(),

				false, null, setter);

	}

	public static <T> TimingTarget getTarget(Setter<T> setter, KeyFrames<T> keyFrames) {

		return (TimingTarget) new SafePropertySetter<>(keyFrames, false, null, setter);

	}

	public static <T> TimingTarget getTargetTo(Getter<T> getter, Setter<T> setter, T... values) {

		return getTargetTo(getter, setter, (new KeyFrames.Builder(values[0])).addFrames((Object[]) values).build());

	}

	public static <T> TimingTarget getTargetTo(GetterAndSetter<T> getterAndSetter, T... values) {

		return getTargetTo(getterAndSetter, getterAndSetter, values);

	}

	public static <T> TimingTarget getTargetTo(Getter<T> getter, Setter<T> setter, KeyFrames<T> keyFrames) {

		return (TimingTarget) new SafePropertySetter<>(keyFrames, true, getter, setter);

	}

	public static <T> TimingTarget getTargetTo(GetterAndSetter<T> getterAndSetter, KeyFrames<T> keyFrames) {

		return getTargetTo(getterAndSetter, getterAndSetter, keyFrames);

	}

	public static <U> Property<U> animatableProperty(Component component, U value) {

		return new Property<>(component, value);

	}

	public void timingEvent(Animator source, double fraction) {

		this.setter.setValue((T) ((KeyFrames) this.keyFrames.get()).getInterpolatedValueAt(fraction));

	}

	public void begin(Animator source) {

		if (this.isToAnimation) {

			KeyFrames.Builder<T> builder = new KeyFrames.Builder(this.getter.getValue());

			boolean first = true;

			for (KeyFrames.Frame<T> frame : this.keyFrames.get()) {

				if (first) {

					first = false;

					continue;

				}

				builder.addFrame(frame);

			}

			this.keyFrames.set(builder.build());

		}

		double fraction = (source.getCurrentDirection() == Animator.Direction.FORWARD) ? 0.0D : 1.0D;

		timingEvent(source, fraction);

	}

	public static class Property<T> implements GetterAndSetter<T> {

		private final Component component;

		private T value;

		public Property(Component component, T value) {

			this.component = component;

			this.value = value;

		}

		public T getValue() {

			return this.value;

		}

		public void setValue(T newValue) {

			this.value = newValue;

			if (this.component != null)

				this.component.repaint();

		}

	}

	public static interface GetterAndSetter<T> extends Getter<T>, Setter<T> {

	}

	public static interface Setter<T> {

		void setValue(T param1T);

	}

	public static interface Getter<T> {

		T getValue();

	}

}
