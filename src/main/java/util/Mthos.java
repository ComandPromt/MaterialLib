package util;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import exceptions.customexceptions.MyOwnRuntimeException;

public abstract class Mthos {

	private static String os = System.getProperty("os.name");

	public static String saberFechaYHoraActual(String pattern) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);

		return dtf.format(LocalDateTime.now());

	}

	public static String saberFechaYHoraActual(boolean englishFormat) {

		String patron = "yyyy/MM/dd HH:mm:ss";

		if (!englishFormat) {

			patron = "dd/MM/yyyy HH:mm:ss";

		}

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(patron);

		return dtf.format(LocalDateTime.now());

	}

	public static boolean esVideo(String name) {

		boolean resultado = false;

		try {

			name = name.toLowerCase();

			if (name.endsWith(".mp4") || name.endsWith(".avi") || name.endsWith(".mpg") || name.endsWith(".mkv")
					|| name.endsWith(".webm")) {

				resultado = true;

			}

		}

		catch (Exception e) {
		}

		return resultado;

	}

	public static int calcularCentro(String text, Font font) {

		int resultado = 0;

		boolean sumar = text.length() != 3;

		for (int i = 0; i < text.length(); i++) {

			if (Character.toString(text.charAt(i)).equals(Character.toString(text.charAt(i)).toUpperCase())) {

				resultado += (font.getSize() * 13) / 20;

			}

			else {

				resultado += (font.getSize() * 9) / 20;

			}

			if (sumar || Character.toString(text.charAt(0)).equals(text.substring(0, 1).toLowerCase())) {

				resultado += 3;

			}

		}

		if (text.length() == 2 && Character.toString(text.charAt(0)).equals(text.substring(0, 1).toUpperCase())) {

			resultado -= 3;

		}

		return resultado;

	}

	public static int alinear(int alineacion, String text, Font fuente, int width) {

		int padding = 0;

		switch (alineacion) {

		case SwingConstants.LEFT:

			padding = 3;

			break;

		case SwingConstants.RIGHT:

			padding = (Mthos.calcularCentro(text, fuente) / 2) - (text.length() / 2);

			padding += Mthos.calcularCentro(text, fuente) / 2;

			if (text.length() > 4) {

				padding -= text.length();

				padding -= 2;

			}

			else {

				switch (text.length()) {

				case 1:

					padding *= 3;

					padding--;

					break;

				case 2:

					padding *= 1.5;

					break;

				case 4:

					padding += text.length();

					padding -= 2;

					break;

				}

			}

			break;

		default:

			padding = centrarTexto(text, fuente, width);

		}

		return padding;

	}

	public static int centrarTexto(String text, Font font, int width) {

		int resultado = 0;

		boolean sumar = text.length() != 3;

		for (int i = 0; i < text.length(); i++) {

			if (Character.toString(text.charAt(i)).equals(Character.toString(text.charAt(i)).toUpperCase())) {

				resultado += (font.getSize() * 14.5) / 20;

			}

			else {

				resultado += (font.getSize() * 9.5) / 20;

			}

			if (sumar || Character.toString(text.charAt(0)).equals(text.substring(0, 1).toLowerCase())) {

				resultado += 3;

			}

		}

		if (text.length() == 2 && Character.toString(text.charAt(0)).equals(text.substring(0, 1).toUpperCase())) {

			resultado -= 3;

		}

		resultado -= resultado / 2;

		resultado += (-10 + (text.length() - 1) * 5) * -1;

		if (width - resultado > (int) (width / 1.28f)) {

			resultado += (width / 3.9) - (width / (text.length() + 2));
		}

		if ((((int) (width / 1.28f)) - (width - resultado)) > 0
				&& (((int) (width / 1.28f)) - (width - resultado)) < 5) {

			resultado -= width / (text.length() + 2);

			resultado -= resultado / text.length() + 2;

		}

		return resultado;

	}

	public static int contarMinusculas(String text) {

		int contador = 0;

		int y = 0;

		for (int i = 0; i < text.length(); i++) {

			y = i;

			if (Character.toString(text.charAt(i)).equals(text.substring(i, ++y).toLowerCase())) {

				contador++;

			}

		}

		return contador;

	}

	public static boolean tieneMinuscula(String text) {

		boolean resultado = false;

		int y = 0;

		for (int i = 0; i < text.length(); i++) {

			y = i;

			if (Character.toString(text.charAt(i)).equals(text.substring(i, ++y).toLowerCase())) {

				resultado = true;

				i = text.length();

			}

		}

		return resultado;

	}

	public static Image iconToImage(Icon icon) {

		if (icon instanceof ImageIcon) {

			return ((ImageIcon) icon).getImage();

		}

		else {

			int w = icon.getIconWidth();

			int h = icon.getIconHeight();

			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

			GraphicsDevice gd = ge.getDefaultScreenDevice();

			GraphicsConfiguration gc = gd.getDefaultConfiguration();

			BufferedImage image = gc.createCompatibleImage(w, h);

			Graphics2D g = image.createGraphics();

			icon.paintIcon(null, g, 0, 0);

			g.dispose();

			return image;

		}

	}

	public static Image getScaledImage(Image srcImg, int w, int h) {

		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		g2.drawImage(srcImg, 0, 0, w, h, null);

		g2.dispose();

		return resizedImg;

	}

	public static ImageIcon resize(ImageIcon image, int width, int height) {

		BufferedImage bi = new BufferedImage(width, height, Transparency.TRANSLUCENT);

		Graphics2D g2d = bi.createGraphics();

		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));

		g2d.drawImage(image.getImage(), 0, 0, width, height, null);

		g2d.dispose();

		return new ImageIcon(bi);

	}

	public static int listarFicherosPorCarpeta(String carpeta, String filtro) {

		File exCarpeta = new File(carpeta);

		int ocurrencias = 0;

		if (exCarpeta.isDirectory()) {

			String extension;

			String nombreArchivo;

			File folder;

			for (final File ficheroEntrada : exCarpeta.listFiles()) {

				nombreArchivo = ficheroEntrada.getName();

				extension = extraerExtension(nombreArchivo);

				folder = new File(exCarpeta + saberSeparador() + nombreArchivo);

				if (!folder.isDirectory() && (extension.equals(filtro) || filtro.equals("."))) {

					ocurrencias++;

				}

			}

		}

		return ocurrencias;

	}

	public static int listarFicherosPorCarpeta(final File carpeta, String filtro) {

		int ocurrencias = 0;

		if (carpeta.isDirectory()) {

			String extension;

			String nombreArchivo;

			File folder;

			for (final File ficheroEntrada : carpeta.listFiles()) {

				nombreArchivo = ficheroEntrada.getName();

				extension = extraerExtension(nombreArchivo);

				folder = new File(carpeta + saberSeparador() + nombreArchivo);

				if (!folder.isDirectory() && (extension.equals(filtro) || filtro.equals("."))) {

					ocurrencias++;

				}

			}

		}

		return ocurrencias;

	}

	public static void ejecutarJava(String string, boolean cmd) {

		try {

			String cabecera = "";

			if (os.contains("indow")) {

				cabecera = "cmd /c ";

				if (cmd) {

					cabecera = "cmd /c start cmd.exe /K \"";

				}

			}

			Runtime.getRuntime().exec(cabecera + "cd " + string.substring(0, string.lastIndexOf(saberSeparador()))
					+ " && java -jar \"" + string + "\"");

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	private static Clipboard getSystemClipboard() {

		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();

		return defaultToolkit.getSystemClipboard();

	}

	public static void copy(String text) {

		try {

			Clipboard clipboard = getSystemClipboard();

			clipboard.setContents(new StringSelection(text), null);

		}

		catch (Exception e) {

		}

	}

	public static String[] getFonts() {

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		return ge.getAvailableFontFamilyNames();

	}

	public static String rutaActual() throws IOException {

		return new File(".").getCanonicalPath() + saberSeparador();

	}

	public static List<String> directorio(String ruta, String extension, boolean carpeta) {

		LinkedList<String> lista = new LinkedList<>();

		File f = new File(ruta);

		ArrayList<String> videosPermitidos = new ArrayList<>();

		videosPermitidos.add("mp4");

		videosPermitidos.add("mpg");

		videosPermitidos.add("avi");

		videosPermitidos.add("mkv");

		ArrayList<String> imagenesPermitidas = new ArrayList<>();

		videosPermitidos.add("jpg");

		videosPermitidos.add("png");

		videosPermitidos.add("jpeg");

		videosPermitidos.add("gif");

		if (f.exists()) {

			File[] ficheros = f.listFiles();

			String fichero = "";

			String extensionArchivo;

			File folder;

			for (int x = 0; x < ficheros.length; x++) {

				fichero = ficheros[x].getName();

				folder = new File(ruta + fichero);

				extensionArchivo = extraerExtension(fichero);

				if ((carpeta && folder.isDirectory())
						|| (!carpeta && (folder.isFile() && (extension.equals(".") || extension.equals(extensionArchivo)
								|| (extension.equals("videos") && videosPermitidos.contains(extension))
								|| (extension.equals("images") && !extensionArchivo.equals("gif")
										&& imagenesPermitidas.contains(extension))
								|| (extension.equals("allimages") && imagenesPermitidas.contains(extension)))))) {

					lista.add(ruta + fichero);

				}

			}

		}

		Collections.sort(lista);

		return lista;

	}

	public static void abrirCarpeta(String ruta) throws IOException {

		if (ruta != null && !ruta.equals("") && !ruta.isEmpty()) {

			if (os.contains("Linux")) {

				Runtime.getRuntime().exec("xdg-open " + ruta);

			}

			else if (os.contains("Mac")) {

				Runtime.getRuntime().exec("open " + ruta);

			}

			else {

				Runtime.getRuntime().exec("cmd /c C:\\Windows\\explorer.exe " + "\"" + ruta + "\"");

			}

		}

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

			extension = nombreArchivo.substring(
					nombreArchivo.length() - nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1).length());

		}

		return extension.toLowerCase();

	}

	public static String saberSeparador() {

		if (os.contains("indows")) {

			return "\\";

		}

		else {

			return "/";

		}

	}

	public static Connection connectSqlite(boolean local, String db) throws IOException, SQLException {

		String archivo = "";

		if (local) {

			archivo = new File(".").getCanonicalPath() + saberSeparador() + db;

		}

		else {

			archivo = db;

		}

		return DriverManager.getConnection("jdbc:sqlite:" + archivo);

	}

	public static List<String> leerArchivo(String file) {

		LinkedList<String> lista = new LinkedList<>();

		try (Scanner leer = new Scanner(new File(file))) {

			while (leer.hasNextLine()) {

				lista.add(leer.nextLine());

			}

		}

		catch (Exception e) {

			throw new MyOwnRuntimeException("Error al leer los datos");

		}

		return lista;

	}

	public static List<String> listSQLite(Connection connect, String query, String key) {

		LinkedList<String> lista = new LinkedList<>();

		ResultSet result;

		try (PreparedStatement st = connect.prepareStatement(query)) {

			result = st.executeQuery();

			while (result.next()) {

				lista.add(result.getString(key));

			}

		}

		catch (SQLException ex) {

			throw new MyOwnRuntimeException("Error al leer los datos");

		}

		return lista;

	}

	public static int eliminarArchivo(String file) throws IOException {

		Path archivo = Paths.get(file);

		short respuesta = 400;

		if (new File(file).exists()) {

			try {

				if (Files.deleteIfExists(archivo)) {

					respuesta = 200;

				}

			}

			catch (Exception e) {

				if (new File(file).isDirectory()) {

					respuesta = 501;

				}

				else {

					respuesta = 502;

				}

			}

		}

		else {

			respuesta = 404;

		}

		return respuesta;

	}

	public static int crearFichero(String file) {

		short respuesta = 400;

		try {

			File archivo = new File(file);

			if (archivo.exists() && archivo.createNewFile()) {

				respuesta = 200;

			}

		}

		catch (IOException e) {

			respuesta = 500;

		}

		return respuesta;

	}

	public static int renombrar(String ruta1, String ruta2) {

		File f1 = new File(ruta1);

		File f2 = new File(ruta2);

		short respuesta = 400;

		if (f1.exists() && f2.exists() && f1.renameTo(f2)) {

			respuesta = 200;

		}

		return respuesta;

	}

}
