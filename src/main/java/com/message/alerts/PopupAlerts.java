package com.message.alerts;

import java.awt.Font;
import java.awt.Point;
import java.awt.Window;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.nio.file.Paths;

import mthos.JMthos;

public class PopupAlerts {

	float opacidad;

	private Font fuente;

	private Point size;

	private boolean redimensionar;

	public Font getFuente() {

		return fuente;

	}

	public Point getSize() {

		return size;

	}

	public void setSize(int width, int height) {

		redimensionar = true;

		size = new Point(width, height);

	}

	public PopupAlerts() {

		opacidad = 0.5f;

		fuente = new Font("Tahoma", Font.BOLD, 30);

		size = new Point(600, 300);

	}

	public PopupAlerts(float opacidad, int width, int height) {

		redimensionar = true;

		if (opacidad < 0f) {

			opacidad = 0f;

		}

		if (opacidad > 1f) {

			opacidad = 1f;

		}

		this.opacidad = opacidad;

		size = new Point(width, height);

	}

	public PopupAlerts(int width, int height) {

		redimensionar = true;

		opacidad = 0.5f;

		size = new Point(width, height);

		fuente = new Font("Tahoma", Font.BOLD, 30);

	}

	public float getOpacidad() {

		return opacidad;

	}

	public void setOpacidad(float opacidad) {

		this.opacidad = opacidad;

	}

	public enum AlertType {

		INFO, WARNING, ERROR, SUCCESS

	}

	public void setFuente(Font fuente) {

		this.fuente = fuente;

	}

	public void mensaje(String mensaje, int speed) {

		mensaje(mensaje, null, 0, "", speed);

	}

	public void mensaje(String mensaje) {

		mensaje(mensaje, null, 0, "", 352);

	}

	public void mensaje(String mensaje, AlertType type, int speed) {

		mensaje(mensaje, type, 0, "", speed);

	}

	public void mensaje(String mensaje, AlertType type) {

		mensaje(mensaje, type, 0, "", 352);

	}

	public void mensaje(String mensaje, AlertType type, int size, String image, int speed) {

		boolean nulo = true;

		if (speed < 1) {

			speed = 352;

		}

		if (type == null) {

			type = AlertType.INFO;

		}

		if (image == null) {

			image = "";

		}

		if (size < 1) {

			size = 18;

		}

		if (fuente == null) {

			fuente = new Font("Tahoma", Font.BOLD, size);

		}

		if (!image.isEmpty() && image.contains("file:/")) {

			nulo = false;

			image = JMthos.cleanURL(image);

		}

		switch (type) {

		case ERROR:

			AlertError error;

			error = new AlertError(speed);

			if (!nulo) {

				try {

					error.setImage(Paths.get(image).toUri().toURL());

				}

				catch (MalformedURLException e) {

				}

			}

			error.setTitulo(mensaje);

			error.titulo.setFont(fuente);

			error.setOpacity(opacidad);

			if (redimensionar) {

				error.setSize(this.size.x, this.size.y);

			}

			error.setLocationRelativeTo(null);

			error.setVisible(true);

			break;

		case INFO:

			AlertInformation informacion;

			informacion = new AlertInformation(speed);

			if (!nulo) {

				try {

					informacion.setImage(Paths.get(image).toUri().toURL());

				}

				catch (MalformedURLException e) {

				}

			}

			informacion.setTitulo(mensaje);

			informacion.titulo.setFont(fuente);

			informacion.setOpacity(opacidad);

			if (redimensionar) {

				informacion.setSize(this.size.x, this.size.y);

			}

			informacion.setLocationRelativeTo(null);

			informacion.setVisible(true);

			break;

		case WARNING:

			AlertWarningSalir salir;

			salir = new AlertWarningSalir(speed);

			if (!nulo) {

				try {

					salir.setImage(Paths.get(image).toUri().toURL());

				}

				catch (MalformedURLException e) {

				}

			}

			salir.setTitulo(mensaje);

			salir.titulo.setFont(fuente);

			salir.setOpacity(opacidad);

			if (redimensionar) {

				salir.setSize(this.size.x, this.size.y);

			}

			salir.setLocationRelativeTo(null);

			salir.setVisible(true);

			break;

		default:

			AlertSuccess exito;

			exito = new AlertSuccess(speed);

			if (!nulo) {

				try {

					exito.setImage(Paths.get(image).toUri().toURL());

				}

				catch (MalformedURLException e) {

				}

			}

			exito.setTitulo(mensaje);

			exito.titulo.setFont(fuente);

			exito.setOpacity(opacidad);

			if (redimensionar) {

				exito.setSize(this.size.x, this.size.y);

			}

			exito.setLocationRelativeTo(null);

			exito.setVisible(true);

			break;

		}

	}

	public static void setOpacity(Window window, float opacidad) {

		try {

			Class<?> awtUtilsClass = Class.forName("com.sun.awt.AWTUtilities");

			if (awtUtilsClass != null) {

				Method method = awtUtilsClass.getMethod("setWindowOpacity", Window.class, float.class);

				window.setLocationRelativeTo(null);

				method.invoke(null, window, opacidad);

			}

		}

		catch (Exception exp) {

		}

	}

}
