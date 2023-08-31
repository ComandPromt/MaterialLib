package util;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.message.alerts.PopupAlerts;

public abstract class Mthos {

	private Mthos() {

	}

	public static Point getSizeOfImage(BufferedImage originalImage, int newWidth, int newHeight, boolean resize) {

		Point punto;

		int originalWidth = originalImage.getWidth();

		int originalHeight = originalImage.getHeight();

		if (resize) {

			double widthRatio = (double) newWidth / originalWidth;

			double heightRatio = (double) newHeight / originalHeight;

			double scaleFactor = Math.min(widthRatio, heightRatio);

			punto = new Point((int) (originalWidth * scaleFactor), (int) (originalHeight * scaleFactor));

		}

		else {

			punto = new Point(originalWidth, originalHeight);

		}

		return punto;

	}

	public static BufferedImage loadFileImage(String image) {

		try {

			return javax.imageio.ImageIO.read(new File(image));

		}

		catch (Exception e) {

			return null;

		}

	}

	public static void copy(String text) {

		try {

			Clipboard clipboard = getSystemClipboard();

			clipboard.setContents(new StringSelection(text), null);

		}

		catch (Exception e) {

		}

	}

	public static void copiarConMensaje(String texto, String mensaje, int size) {

		if (!mensaje.isEmpty()) {

			PopupAlerts.mensaje(mensaje, 2, size, null);

			PopupAlerts.mensaje(mensaje, 2, size, null);

		}

		copy(texto);

	}

	public static String extraerExtension(String nombreArchivo) {

		String extension = "";

		try {

			if (nombreArchivo.length() >= 3) {

				extension = nombreArchivo.substring(
						nombreArchivo.length() - nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1).length());

			}

		}

		catch (Exception e) {

		}

		return extension.toLowerCase();

	}

	public static BufferedImage resizeImage(String path, int newWidth, int newHeight) {

		BufferedImage originalImage;

		try {

			originalImage = ImageIO.read(new File(path));

			int originalWidth = originalImage.getWidth();

			int originalHeight = originalImage.getHeight();

			double widthRatio = (double) newWidth / originalWidth;

			double heightRatio = (double) newHeight / originalHeight;

			double scaleFactor = Math.min(widthRatio, heightRatio);

			return new BufferedImage((int) (originalWidth * scaleFactor), (int) (originalHeight * scaleFactor),
					originalImage.getType());

		}

		catch (Exception e) {

			return null;

		}

	}

	public static String cleanURL(String url) {

		return url.replace("file:/", "");

	}

	private static Clipboard getSystemClipboard() {

		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();

		return defaultToolkit.getSystemClipboard();

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
