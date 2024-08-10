package com.material.utils;

import java.awt.Window;
import java.lang.reflect.Method;

public class RSAWTUtilities {

	public static void setOpaque(Window window, boolean opaque) {

		try {

			Class<?> awtUtilsClass = Class.forName("com.sun.awt.AWTUtilities");

			if (awtUtilsClass != null) {

				Method method = awtUtilsClass.getMethod("setWindowOpaque", new Class[] { Window.class, boolean.class });

				method.invoke(null, new Object[] { window, Boolean.valueOf(opaque) });

			}

		}

		catch (Exception exception) {

		}

	}

	public static void setOpacity(Window window, float opacidad) {

		try {

			Class<?> awtUtilsClass = Class.forName("com.sun.awt.AWTUtilities");

			if (awtUtilsClass != null) {

				Method method = awtUtilsClass.getMethod("setWindowOpacity", new Class[] { Window.class, float.class });

				method.invoke(null, new Object[] { window, Float.valueOf(opacidad) });

			}

		}

		catch (Exception exception) {

		}

	}

}