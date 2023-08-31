package com.materialfilechooser;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.materialfilechooser.icons.Archivo;
import com.materialfilechooser.icons.Folder;

@SuppressWarnings("serial")

public class ButtonScrollablePanel extends JPanel {

	private String rutaParaVer;

	private String seleccionadoParaVer;

	private boolean click3veces;

	private void addArchivo(boolean carpeta, GridBagConstraints constraints, Archivo labelTest) {

		(labelTest).addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				click3veces = false;

				seleccionadoParaVer = labelTest.getTexto();

				if (e.getClickCount() % 2 != 0) {

					if (carpeta) {

						MaterialFolderChooser.path.setText(rutaParaVer + labelTest.getTexto());

						if (new File(rutaParaVer + labelTest.getTexto()).exists()) {

							MaterialFolderChooser.path.setText(rutaParaVer + labelTest.getTexto());

							if (!rutaParaVer.endsWith(saberSeparador())) {

								rutaParaVer += saberSeparador();

							}

						}
					}

					else {

						MaterialFileChooser.path.setText(rutaParaVer + labelTest.getTexto());

						if (new File(rutaParaVer + labelTest.getTexto()).exists()) {

							MaterialFileChooser.path.setText(rutaParaVer + labelTest.getTexto());

							if (!rutaParaVer.endsWith(saberSeparador())) {

								rutaParaVer += saberSeparador();

							}

						}
					}

				}

				else {

					click3veces = true;

					if (carpeta) {

						if (new File(MaterialFolderChooser.path.getText()).exists()) {

							rutaParaVer = MaterialFolderChooser.path.getText();

							if (!rutaParaVer.endsWith(saberSeparador())) {

								rutaParaVer += saberSeparador();

							}

							MaterialFolderChooser.irAPath();

						}

					}

					else {

						try {

							if (new File(MaterialFolderChooser.path.getText()).exists()) {

								rutaParaVer = MaterialFolderChooser.path.getText();

								if (!rutaParaVer.endsWith(saberSeparador())) {

									rutaParaVer += saberSeparador();

								}

								MaterialFolderChooser.irAPath();

							}

						}

						catch (Exception e1) {

						}

					}

				}

			}

		});

		add((JLabel) labelTest, constraints);

	}

	private void addCarpeta(boolean carpeta, GridBagConstraints constraints, Folder labelTest) {

		(labelTest).addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				click3veces = false;

				seleccionadoParaVer = labelTest.getTexto();

				if (e.getClickCount() % 2 != 0) {

					if (carpeta) {

						MaterialFolderChooser.path.setText(rutaParaVer + labelTest.getTexto());

						if (new File(rutaParaVer + labelTest.getTexto()).exists()) {

							MaterialFolderChooser.path.setText(rutaParaVer + labelTest.getTexto());

							if (!rutaParaVer.endsWith(saberSeparador())) {

								rutaParaVer += saberSeparador();

							}

						}
					}

					else {

						MaterialFileChooser.path.setText(rutaParaVer + labelTest.getTexto());

						if (new File(rutaParaVer + labelTest.getTexto()).exists()) {

							MaterialFileChooser.path.setText(rutaParaVer + labelTest.getTexto());

							if (!rutaParaVer.endsWith(saberSeparador())) {

								rutaParaVer += saberSeparador();

							}

						}
					}

				}

				else {

					click3veces = true;

					if (carpeta) {

						if (new File(MaterialFolderChooser.path.getText()).exists()) {

							rutaParaVer = MaterialFolderChooser.path.getText();

							if (!rutaParaVer.endsWith(saberSeparador())) {

								rutaParaVer += saberSeparador();

							}

							MaterialFolderChooser.irAPath();

						}

					}

					else {
						try {
							if (new File(MaterialFolderChooser.path.getText()).exists()) {

								rutaParaVer = MaterialFolderChooser.path.getText();

								if (!rutaParaVer.endsWith(saberSeparador())) {

									rutaParaVer += saberSeparador();

								}

								MaterialFolderChooser.irAPath();

							}
						}

						catch (Exception e1) {

							if (new File(MaterialFileChooser.path.getText()).exists()) {

								rutaParaVer = MaterialFileChooser.path.getText();

								if (!rutaParaVer.endsWith(saberSeparador())) {

									rutaParaVer += saberSeparador();

								}

								MaterialFileChooser.irAPath();

							}

						}

					}

				}

			}

		});

		add((JLabel) labelTest, constraints);

	}

	public boolean isClick3veces() {

		return click3veces;

	}

	public void setRutaParaVer(String rutaParaVer) {

		this.rutaParaVer = rutaParaVer;

	}

	public String getRutaParaVer() {

		return rutaParaVer;

	}

	public String getSeleccionadoParaVer() {

		return seleccionadoParaVer;

	}

	String extraerExtension(String nombreArchivo) {

		String extension = "";

		if (nombreArchivo.length() >= 3) {

			extension = nombreArchivo.substring(
					nombreArchivo.length() - nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1).length());

		}

		return extension.toLowerCase();

	}

	LinkedList<String> listar(String ruta, String extension, boolean carpeta) {

		if (extension == null) {

			extension = "all";

		}

		LinkedList<String> lista = new LinkedList<>();

		File f = new File(ruta);

		ArrayList<String> videosPermitidos = new ArrayList<>();

		videosPermitidos.add("mp4");

		videosPermitidos.add("mpg");

		videosPermitidos.add("avi");

		videosPermitidos.add("mkv");

		ArrayList<String> imagenesPermitidas = new ArrayList<>();

		imagenesPermitidas.add("jpg");

		imagenesPermitidas.add("png");

		imagenesPermitidas.add("jpeg");

		imagenesPermitidas.add("gif");

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

					lista.add(fichero);

				}

				else if (!carpeta && folder.isFile()) {

					switch (extension) {

					case "all":

						lista.add(fichero);

						break;

					case "videos":

						if (videosPermitidos.contains(extensionArchivo)) {

							lista.add(fichero);

						}

						break;

					case "images":

						if (imagenesPermitidas.contains(extensionArchivo)) {

							lista.add(fichero);

						}

						break;

					default:

						if (extension.equals(extensionArchivo)) {

							lista.add(fichero);

						}

						break;

					}

				}

			}

		}

		Collections.sort(lista);

		return lista;

	}

	List<String> listarConArray(String ruta, String[] lista, boolean carpeta) {

		LinkedList<String> list = new LinkedList<>();

		File f = new File(ruta);

		if (!ruta.endsWith(saberSeparador())) {

			ruta += saberSeparador();

		}

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

					list.add(fichero);

				}

				else if (!carpeta && folder.isFile() &&

						Arrays.asList(lista).contains(extensionArchivo)) {

					list.add(fichero);

				}

			}

		}

		Collections.sort(list);

		return list;

	}

	String saberSeparador() {

		if (System.getProperty("os.name").contains("indows")) {

			return "\\";

		}

		else {

			return "/";

		}

	}

	String findLongestString(LinkedList<String> list) {

		String longestString = null;

		int maxLength = -1;

		for (String str : list) {

			if (str.length() > maxLength) {

				maxLength = str.length();

				longestString = str;

			}

		}

		return longestString;

	}

	void listarItems(String path, String extension, boolean carpeta) {

		LinkedList<String> lista;

		LinkedList<String> lista2 = null;

		if (extension != null && !extension.equals("null")) {

			try {

				GridBagConstraints constraints = new GridBagConstraints();

				if (!path.endsWith(saberSeparador())) {

					path += saberSeparador();

				}

				if (new File(path).exists()) {

					if (extension.equals("All (*.*)")) {

						extension = "all";

					}

					if (extension != null && extension.contains(",")) {

						lista = (LinkedList<String>) listarConArray(path, extension.split(","), carpeta);

						if (!carpeta) {

							lista2 = listar(path, "all", true);

						}

						if (lista2 != null && !lista2.isEmpty()) {

							for (int i = 0; i < lista2.size(); i++) {

								lista.add(lista2.get(i));

							}

							Collections.sort(lista);

						}
					}

					else {

						lista = listar(path, extension, carpeta);

					}

					int indice = 0;

					String mayorSize = findLongestString(lista);

					for (int row = 0; row < lista.size(); row++) {

						for (int col = 1; col <= 2; col++) {

							if (indice < lista.size()) {

								constraints.gridx = col;

								constraints.gridy = row;

								constraints.insets = new Insets(10, 10, 10, 10);

								constraints.fill = GridBagConstraints.HORIZONTAL;

								if (carpeta) {

									addCarpeta(carpeta, constraints,
											new Folder(lista.get(indice), mayorSize, lista.size()));

								}

								else {

									if (new File(path + lista.get(indice)).isFile()) {

										addArchivo(carpeta, constraints,
												new Archivo(lista.get(indice), mayorSize, lista.size()));

									}

									else {

										addCarpeta(carpeta, constraints,
												new Folder(lista.get(indice), mayorSize, lista.size()));

									}

								}

								indice++;

							}

							else {

								row = lista.size();

							}

						}

					}

				}

			}

			catch (Exception e) {

			}

		}

	}

	public ButtonScrollablePanel(String path, String extension, boolean carpeta) {

		rutaParaVer = System.getProperty("user.home") + saberSeparador();

		setLayout(new GridBagLayout());

		listarItems(path, extension, carpeta);

	}

}
