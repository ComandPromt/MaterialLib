package util;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

public abstract class Mthos {

	public static String[] getFonts() {

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		return ge.getAvailableFontFamilyNames();

	}

	public static String rutaActual() throws IOException {

		return new File(".").getCanonicalPath() + saberSeparador();

	}

	public static LinkedList<String> directorio(String ruta, String extension, boolean carpeta) {

		LinkedList<String> lista = new LinkedList<String>();

		try {

			File f = new File(ruta);

			if (f.exists()) {

				File[] ficheros = f.listFiles();

				String fichero = "";

				String extensionArchivo;

				File folder;

				for (int x = 0; x < ficheros.length; x++) {

					fichero = ficheros[x].getName();

					folder = new File(ruta + fichero);

					extensionArchivo = extraerExtension(fichero);

					if (carpeta && folder.isDirectory()) {

						lista.add(ruta + fichero);
					}

					else {

						if (!carpeta && (folder.isFile() && (extension.equals(".") || extension.equals(extensionArchivo)

								||

								(extension.equals("images") && (extensionArchivo.equals("jpg")
										|| extensionArchivo.equals("jpeg") || extensionArchivo.equals("png")))

								|| (extension.equals("allimages")
										&& (extensionArchivo.equals("jpg") || extensionArchivo.equals("jpeg")
												|| extensionArchivo.equals("png") || extensionArchivo.equals("gif")))

						))) {

							lista.add(ruta + fichero);

						}
					}
				}

			}

		}

		catch (Exception e) {

		}

		Collections.sort(lista);

		return lista;

	}

	public static void crearCarpeta(String path) {

		new File(path).mkdir();

	}

	public static String cleanURL(String url) {

		return url.replace("file:/", "");

	}

	public static String extraerExtension(String nombreArchivo) {

		String extension = "";

		if (nombreArchivo.length() >= 3) {

			extension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1, nombreArchivo.length());

			extension = extension.toLowerCase();

		}

		return extension;

	}

	public static String saberSeparador() {

		if (System.getProperty("os.name").contains("indows")) {

			return "\\";

		}

		else {

			return "/";

		}

	}

}
