package util;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.message.alerts.PopupAlerts;

public abstract class Mthos {

	private Mthos() {

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

	public static Image getScaledImage(Image srcImg, int w, int h) {

		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		g2.drawImage(srcImg, 0, 0, w, h, null);

		g2.dispose();

		return resizedImg;

	}

	public static String cleanURL(String url) {

		return url.replace("file:/", "");

	}

	private static Clipboard getSystemClipboard() {

		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();

		return defaultToolkit.getSystemClipboard();

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

	public static String saberSeparador() {

		if (System.getProperty("os.name").contains("indows")) {

			return "\\";

		}

		else {

			return "/";

		}

	}

}
