package com.message.alerts;

import java.awt.Font;
import java.awt.Window;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.nio.file.Paths;

import util.Mthos;

public class PopupAlerts {

	static float opacidad = 0.5f;

	private static Font fuente;

	public static void setFuente(Font fuente) {

		PopupAlerts.fuente = fuente;

	}

	public static void mensaje(String mensaje, int type, int size, String path) {

		boolean nulo = true;

		if (fuente == null) {

			fuente = new Font("Tahoma", Font.BOLD, size);

		}

		if (path != null && !path.isEmpty() && path.contains("file:/")) {

			nulo = false;

			path = Mthos.cleanURL(path);

		}

		if (size <= 0) {

			size = 18;

		}

		switch (type) {

		case 1:

			AlertError error;

			error = new AlertError(false);

			if (!nulo) {

				try {

					error.setImage(Paths.get(path).toUri().toURL());

				}

				catch (MalformedURLException e) {

				}

			}

			error.setTitulo(mensaje);

			error.titulo.setFont(fuente);

			error.setOpacity(opacidad);

			error.setVisible(true);

			break;

		case 2:

			AlertInformation informacion;

			informacion = new AlertInformation(false);

			if (!nulo) {

				try {

					informacion.setImage(Paths.get(path).toUri().toURL());

				}

				catch (MalformedURLException e) {

				}

			}

			informacion.setTitulo(mensaje);

			informacion.titulo.setFont(fuente);

			informacion.setOpacity(opacidad);

			informacion.setVisible(true);

			break;

		case 3:

			AlertWarningSalir salir;

			salir = new AlertWarningSalir(false);

			if (!nulo) {

				try {

					salir.setImage(Paths.get(path).toUri().toURL());

				}

				catch (MalformedURLException e) {

				}

			}

			salir.setTitulo(mensaje);

			salir.titulo.setFont(fuente);

			salir.setOpacity(opacidad);

			salir.setVisible(true);

			break;

		case 4:

			AlertSuccess exito;

			exito = new AlertSuccess(false);

			if (!nulo) {

				try {

					exito.setImage(Paths.get(path).toUri().toURL());

				}

				catch (MalformedURLException e) {

				}

			}

			exito.setTitulo(mensaje);

			exito.titulo.setFont(fuente);

			exito.setOpacity(opacidad);

			exito.setVisible(true);

			break;

		}

	}

	public static void setOpacity(Window window, float opacidad) {

		try {

			Class<?> awtUtilsClass = Class.forName("com.sun.awt.AWTUtilities");

			if (awtUtilsClass != null) {

				Method method = awtUtilsClass.getMethod("setWindowOpacity", Window.class, float.class);

				method.invoke(null, window, opacidad);

			}

		}

		catch (Exception exp) {

		}

	}

}
