package com.nativeChooser;

import java.awt.Toolkit;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import mthos.JMthos;

@SuppressWarnings("serial")

public class NativeFileChooser extends JFrame {

	public String folder;

	public NativeFileChooser() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		setIconImage(
				Toolkit.getDefaultToolkit().getImage(NativeFileChooser.class.getResource("/imgs/imagenes/folder.png")));

		this.setVisible(false);

	}

	public LinkedList<File> showOpenFileDialog(boolean carpeta, String filtro) {

		LinkedList<File> files = new LinkedList<File>();

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

				files = addImages(carpeta, filtro, selectedFile);

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

		return files;

	}

	public String getFolder() {

		return folder;

	}

	public static String saberSeparador() {

		if (System.getProperty("os.name").contains("indows")) {

			return "\\";

		}

		else {

			return "/";

		}

	}

	public static LinkedList<File> addImages(boolean carpeta, String filtro, File[] fc) {

		LinkedList<File> files = new LinkedList<File>();

		files.clear();

		Arrays.asList(fc).forEach(x -> {

			if (!carpeta) {

				String extension;

				if (x.isFile()) {

					switch (filtro) {

					case "":

					case "none":

					case "all":

						files.add(new File(x.getAbsolutePath()));

						break;

					case "images":

						extension = JMthos.extraerExtension(x.getAbsolutePath());

						if (extension.equals("jpeg") || extension.equals("bmp") || extension.equals("jpg")
								|| extension.equals("png") || extension.equals("gif")) {

							files.add(new File(x.getAbsolutePath()));

						}

						break;

					default:

						extension = JMthos.extraerExtension(x.getAbsolutePath());

						if (extension.equals(filtro)) {

							files.add(new File(x.getAbsolutePath()));

						}

						break;

					}

				}

			}

			else {

				if (x.isDirectory()) {

					files.add(new File(x.getAbsolutePath()));

				}

			}

		});

		return files;

	}

}
