package com.materialfilechooser;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.search.SearchInput;

import mthos.JMthos;

@SuppressWarnings("serial")

class ButtonScrollablePanel extends JPanel {

	private String rutaParaVer;

	private String seleccionadoParaVer;

	private boolean click3veces;

	private LinkedList<String> listaCarpeta;

	private File archivoParaMirar;

	private String ruta;

	private void addArchivo(GridBagConstraints constraints, Archivo labelTest) {

		(labelTest).addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				click3veces = false;

				seleccionadoParaVer = labelTest.getTexto();

				archivoParaMirar = new File(rutaParaVer + labelTest.getTexto());

				if (!rutaParaVer.endsWith(JMthos.saberSeparador())) {

					rutaParaVer += JMthos.saberSeparador();

				}

				if (e.getClickCount() % 2 != 0 && archivoParaMirar.exists() && archivoParaMirar.isDirectory()) {

					MaterialFolderChooser.path.setText(rutaParaVer + labelTest.getTexto());

				}

				else {

					hacerBusqueda();

					click3veces = true;

					if (archivoParaMirar.exists() && archivoParaMirar.isDirectory()) {

						rutaParaVer = MaterialFolderChooser.path.getText();

						if (!rutaParaVer.endsWith(JMthos.saberSeparador())) {

							rutaParaVer += JMthos.saberSeparador();

						}

						MaterialFolderChooser.irAPath();

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

				if (!rutaParaVer.endsWith(JMthos.saberSeparador())) {

					rutaParaVer += JMthos.saberSeparador();

				}

				if (e.getClickCount() % 2 != 0) {

					if (carpeta) {

						MaterialFolderChooser.path.setText(rutaParaVer + labelTest.getTexto());

						if (new File(rutaParaVer + labelTest.getTexto()).exists()) {

							MaterialFolderChooser.path.setText(rutaParaVer + labelTest.getTexto());

							if (!rutaParaVer.endsWith(JMthos.saberSeparador())) {

								rutaParaVer += JMthos.saberSeparador();

							}

						}
					}

					else {

						MaterialFileChooser.path.setText(rutaParaVer + labelTest.getTexto());

						if (new File(rutaParaVer + labelTest.getTexto()).exists()) {

							MaterialFileChooser.path.setText(rutaParaVer + labelTest.getTexto());

							if (!rutaParaVer.endsWith(JMthos.saberSeparador())) {

								rutaParaVer += JMthos.saberSeparador();

							}

						}
					}

				}

				else {

					click3veces = true;

					if (carpeta) {

						if (new File(MaterialFolderChooser.path.getText()).exists()) {

							rutaParaVer = MaterialFolderChooser.path.getText();

							if (!rutaParaVer.endsWith(JMthos.saberSeparador())) {

								rutaParaVer += JMthos.saberSeparador();

							}

							MaterialFolderChooser.irAPath();

						}

					}

					else {

						try {

							if (new File(MaterialFolderChooser.path.getText()).exists()) {

								rutaParaVer = MaterialFolderChooser.path.getText();

								if (!rutaParaVer.endsWith(JMthos.saberSeparador())) {

									rutaParaVer += JMthos.saberSeparador();

								}

								MaterialFolderChooser.irAPath();

							}
						}

						catch (Exception e1) {

							if (new File(MaterialFileChooser.path.getText()).exists()) {

								rutaParaVer = MaterialFileChooser.path.getText();

								if (!rutaParaVer.endsWith(JMthos.saberSeparador())) {

									rutaParaVer += JMthos.saberSeparador();

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

	static String extraerExtension(String nombreArchivo) {

		String extension = "";

		if (nombreArchivo.length() >= 3) {

			extension = nombreArchivo.substring(
					nombreArchivo.length() - nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1).length());

		}

		return extension.toLowerCase();

	}

	void listarItems(String path, String extension, boolean carpeta) {

		LinkedList<String> lista = new LinkedList<>();

		LinkedList<String> lista2 = null;

		if (extension != null && !extension.equals("null")) {

			try {

				GridBagConstraints constraints = new GridBagConstraints();

				if (!path.endsWith(JMthos.saberSeparador())) {

					path += JMthos.saberSeparador();

				}

				if (new File(path).exists()) {

					if (extension.equals("All (*.*)")) {

						extension = "all";

					}

					if (extension != null) {

						if (extension.contains(",")) {

							lista = (LinkedList<String>) JMthos.listarConArray(path, extension.split(","), carpeta,
									true);

						}

						else {

							lista = (LinkedList<String>) JMthos.listar(path, extension, false, true);

						}

						if (!carpeta) {

							lista2 = (LinkedList<String>) JMthos.listar(path, "all", true, true);

						}

						if (lista2 != null && !lista2.isEmpty()) {

							for (int i = 0; i < lista2.size(); i++) {

								lista.add(lista2.get(i));

							}

							Collections.sort(lista);

						}
					}

					int indice = 0;

					String mayorSize = JMthos.findLongestString(lista);

					for (int row = 0; row < lista.size(); row++) {

						for (int col = 1; col <= 2; col++) {

							if (indice < lista.size()) {

								constraints.gridx = col;

								constraints.gridy = row;

								constraints.insets = new Insets(10, 10, 10, 10);

								constraints.fill = GridBagConstraints.HORIZONTAL;

								if (carpeta) {

									addCarpeta(carpeta, constraints,
											new Folder(lista.get(indice), mayorSize, lista.size(), carpeta));

								}

								else {

									if (new File(path + lista.get(indice)).isFile()) {

										addArchivo(constraints,
												new Archivo(lista.get(indice), mayorSize, lista.size(), carpeta));

									}

									else {

										addCarpeta(carpeta, constraints,
												new Folder(lista.get(indice), mayorSize, lista.size(), carpeta));

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

	public ButtonScrollablePanel(SearchInput search, String path, String extension, boolean carpeta) {

		this.ruta = path;

		hacerBusqueda();

		listaCarpeta = new LinkedList<String>();

		rutaParaVer = System.getProperty("user.home") + JMthos.saberSeparador();

		setLayout(new GridBagLayout());

		listarItems(path, extension, carpeta);

	}

	private void hacerBusqueda() {

		LinkedList<String> lista = (LinkedList<String>) JMthos.listar(ruta, "all", true, true);

		LinkedList<String> test2 = (LinkedList<String>) JMthos.listar(ruta, "all", false, true);

		for (int i = 0; i < test2.size(); i++) {

			lista.add(test2.get(i));

		}

		Collections.sort(lista);

	}

	public LinkedList<String> getLista() {

		return listaCarpeta;

	}

}
