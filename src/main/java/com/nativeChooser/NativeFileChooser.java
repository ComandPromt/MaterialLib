package com.nativeChooser;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toolTip.ToolTipLlamada;

import mthos.JMthos;

@SuppressWarnings("serial")

public class NativeFileChooser extends JPanel {

	private String folder;

	private List<File> archivos;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	@Override
	public void setToolTipText(String text) {

		setToolTip(text, null, null, null, null);

	}

	public void setToolTip(String text, Color background, Color foreground, Color border, Font font) {

		calcularTooltip(text, background, foreground, border, font);

	}

	private void calcularTooltip(String text, Color background, Color foreground, Color border, Font font) {

		if (background == null) {

			background = new Color(32, 39, 55);

		}

		if (foreground == null) {

			foreground = Color.WHITE;

		}

		if (border == null) {

			border = new Color(173, 173, 173);

		}

		if (font == null) {

			try {

				font = getFont().deriveFont(20f);

			}

			catch (Exception e) {

				font = new Font("Dialog", Font.PLAIN, 20);

			}

		}

		this.text = text;

		this.fondo = background;

		this.colorTexto = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || fondo == null || colorTexto == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, colorTexto, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public List<File> getArchivos() {

		return archivos;

	}

	public void setArchivos(List<File> archivos) {

		this.archivos = archivos;

	}

	public NativeFileChooser(String filter) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {

		this(false, filter);

	}

	public NativeFileChooser(boolean folder) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {

		this(folder, "all");

	}

	public NativeFileChooser() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		this(false, "all");

	}

	public NativeFileChooser(boolean folder, String filter) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		filter = JMthos.eliminarEspacios(filter, true);

		if (filter.isEmpty()) {

			filter = "all";

		}

		archivos = new LinkedList<>();

		showOpenFileDialog(folder, filter);

		setVisible(true);

	}

	public void showOpenFileDialog(boolean carpeta, String filtro) {

		archivos.clear();

		try {

			if (this.folder == null) {

				this.folder = System.getProperty("user.home");

			}

			JFileChooser fileChooser = new JFileChooser();

			fileChooser.setCurrentDirectory(new File(this.folder));

			fileChooser.setMultiSelectionEnabled(true);

			if (carpeta) {

				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			}

			else {

				switch (filtro) {

				case "":
				case "none":

				case "all":

					break;

				case "images":

					fileChooser
							.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));

					break;

				default:

					fileChooser.addChoosableFileFilter(
							new FileNameExtensionFilter(filtro + " Files (*." + filtro + ")", filtro));

					break;

				}

				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

			}

			fileChooser.setAcceptAllFileFilterUsed(true);

			int result = fileChooser.showOpenDialog(this);

			if (result == JFileChooser.APPROVE_OPTION) {

				File[] selectedFile = fileChooser.getSelectedFiles();

				addImages(carpeta, filtro, selectedFile);

				Pattern p = Pattern.compile("\\.[a-zA-Z0-9]{3}$");

				Matcher m = p.matcher(fileChooser.getSelectedFile().toString());

				if (m.find()) {

					this.folder = fileChooser.getSelectedFile().toString().substring(0,
							fileChooser.getSelectedFile().toString().lastIndexOf(JMthos.saberSeparador()));

				}

				else {

					this.folder = fileChooser.getCurrentDirectory().toString();

				}

			}

		}

		catch (Exception e) {

		}

	}

	public String getFolder() {

		return folder;

	}

	private void addImages(boolean carpeta, String filtro, File[] fc) {

		archivos.clear();

		Arrays.asList(fc).forEach(x -> {

			if (!carpeta) {

				String extension;

				if (x.isFile()) {

					switch (filtro) {

					case "":

					case "none":

					case "all":

						archivos.add(new File(x.getAbsolutePath()));

						break;

					case "images":

						extension = JMthos.extraerExtension(x.getAbsolutePath());

						if (extension.equals("jpeg") || extension.equals("bmp") || extension.equals("jpg")
								|| extension.equals("png") || extension.equals("gif")) {

							archivos.add(new File(x.getAbsolutePath()));

						}

						break;

					default:

						extension = JMthos.extraerExtension(x.getAbsolutePath());

						if (extension.equals(filtro)) {

							archivos.add(new File(x.getAbsolutePath()));

						}

						break;

					}

				}

			}

			else {

				if (x.isDirectory()) {

					archivos.add(new File(x.getAbsolutePath()));

				}

			}

		});

	}

}
