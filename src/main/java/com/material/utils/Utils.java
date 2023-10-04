package com.material.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Utils {

	private static final boolean useSun2D;

	private static final Method getUsableBounds;

	static {

		boolean found = false;

		Method getMethod = null;

		try {

			Class sunGE = Class.forName("sun.java2d.SunGraphicsEnvironment");

			Method[] meths = sunGE.getDeclaredMethods();

			for (Method meth : meths) {

				if (meth.getName().equals("getUsableBounds")
						&& Arrays.equals(meth.getParameterTypes(), new Class[] { java.awt.GraphicsDevice.class })
						&& meth.getExceptionTypes().length == 0
						&& meth.getReturnType().equals(java.awt.Rectangle.class)) {

					getMethod = meth;

					found = true;

					break;

				}

			}

		}

		catch (ClassNotFoundException ex) {

			found = false;

		}

		useSun2D = found;

		getUsableBounds = getMethod;

	}

	public static Rectangle getScreenSize() {

		Rectangle screen;

		if (useSun2D) {

			try {

				Frame frame = new Frame();

				GraphicsConfiguration config = frame.getGraphicsConfiguration();

				screen = (Rectangle) getUsableBounds.invoke(null, config.getDevice());

				frame.dispose();

			}

			catch (Exception ex) {

				Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
				screen = new Rectangle(0, 0, size.width, size.height);

			}

		}

		else {

			Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

			screen = new Rectangle(0, 0, size.width, size.height);

		}

		return screen;

	}

	public static boolean isTranslucencySupported() {

		boolean nativeTrans;

		if (System.getProperty("java.version").contains("1.6")) {

			nativeTrans = false;

		}

		else {

			nativeTrans = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
					.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSLUCENT);
		}

		return nativeTrans;

	}

	public static boolean isDark(Color color) {

		return (color.getRed() * 0.2125 + color.getGreen() * 0.7154 + color.getBlue() * 0.0721) < (0.535 * 255);

	}

	public static Color darken(Color color) {

		return new Color(wrapU8B(color.getRed() - 30), wrapU8B(color.getGreen() - 30), wrapU8B(color.getBlue() - 30),
				color.getAlpha());

	}

	public static Color brighten(Color color) {

		return new Color(wrapU8B(color.getRed() + 30), wrapU8B(color.getGreen() + 30), wrapU8B(color.getBlue() + 30),
				color.getAlpha());

	}

	private static int wrapU8B(int i) {

		return Math.min(255, Math.max(0, i));

	}

	public static Color applyAlphaMask(Color color, int bitMask) {

		return new Color(color.getRGB() & 0x00FFFFFF | (bitMask & 0xFF000000), true);

	}

}
