package com.material.table;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.jicons.ClassicFolder;

@SuppressWarnings("serial")
class Cuerpo extends JPanel {

	private ArrayList<Pagina> paginas;

	private Color color;

	private Color checkColor;

	private ArrayList<String> datos;

	private int items;

	private int split;

	private Color activeColor;

	private Color activeBackground;

	private boolean activate;

	private ArrayList<Integer> indicesNoSeleccionados;

	private int numItems;

	private boolean filePath;

	private List<String> cabeceras;

	private String texto;

	private int tipo;

	private ImageIcon icono;

	private Color colorFileBottom;

	public ArrayList<Pagina> getPaginas() {

		return paginas;

	}

	public void setFilePath(String text, int tipo, ImageIcon icon, boolean filePath) {

		this.filePath = filePath;

		this.tipo = tipo;

		this.icono = icon;

		this.texto = text;

		repaint();

	}

	public void setFilePath(String text, int tipo, boolean filePath) {

		this.filePath = filePath;

		this.tipo = tipo;

		this.texto = text;

		repaint();

	}

	public void setFilePath(boolean filePath) {

		this.filePath = filePath;

		repaint();

	}

	void setNumItems(int split) {

		numItems = split;

	}

	public void setSplit(int split) {

		this.split = split;

	}

	public int getSplit() {

		return split;

	}

	public int getItems() {

		return items;

	}

	public Color getColor() {

		return color;

	}

	@Override
	public void setBackground(Color bg) {

		super.setBackground(bg);

		color = bg;

		try {

			for (Pagina valor : paginas) {

				valor.setBackground(bg);

			}

		}

		catch (Exception e) {

		}

	}

	public void verDatos(int init, ArrayList<String> lista) {

		borrarDatos();

		int split = getSplit();

		try {

			int vueltas = items;

			vueltas *= split;

			ArrayList<String> valores = new ArrayList<>();

			int contador = init;

			Pagina pagina;

			ClassicFolder carpetaClasica;

			for (int i = 0; i < vueltas; i++) {

				if (contador < lista.size()) {

					valores.add(lista.get(contador));

					if (valores.size() == split) {

						pagina = new Pagina(valores, getFont(), getForeground(), filePath);

						pagina.setFileBottom(colorFileBottom);

						pagina.getCheckBox().setSelected(activate);

						pagina.setBackground(color);

						pagina.setCheckBackground(checkColor);

						pagina.setCheckColor(activeColor);

						pagina.setColorActivo(activeBackground);

						if (!texto.isEmpty()) {

							if (icono == null) {

								carpetaClasica = new ClassicFolder(Color.LIGHT_GRAY);

								carpetaClasica.setRestarDerecha(7);

								icono = carpetaClasica;

							}

							pagina.setTextAndType(tipo, texto, icono);

							pagina.setFileBottom(colorFileBottom);

						}

						paginas.add(pagina);

						revalidate();

						add(pagina);

						valores.clear();

					}

				}

				else {

					valores.add("");

					if (valores.size() == split) {

						pagina = new Pagina(valores, getFont(), getForeground(), filePath);

						pagina.setBackground(color);

						pagina.setCheckBackground(checkColor);

						paginas.add(pagina);

						revalidate();

						add(pagina);

						valores.clear();
					}

				}

				contador++;

			}

		}

		catch (Exception e) {

		}

	}

	public void setDatos(ArrayList<String> datos) {

		this.datos = datos;

	}

	public ArrayList<String> getDatos() {

		return datos;

	}

	public Cuerpo(List<String> lista, int items, int split) {

		colorFileBottom = Color.PINK;

		texto = "";

		indicesNoSeleccionados = new ArrayList<>();

		this.split = split;

		this.items = items;

		setLayout(new GridLayout(items, 1));

		paginas = new ArrayList<>();

		datos = (ArrayList<String>) lista;

	}

	public void setCheckBackground(Color color) {

		checkColor = color;

	}

	public void setCheckActiveBodyBackground(Color color) {

		activeBackground = color;

	}

	public void setCheckColor(Color color) {

		activeColor = color;

	}

	public void borrarDatos() {

		removeAll();

		revalidate();

		repaint();

		paginas.clear();

	}

	public boolean isActivate() {

		return activate;

	}

	public void seleccionarTodo(boolean select) {

		activate = select;

		for (Pagina valor : paginas) {

			valor.getCheckBox().setSelected(select);

			valor.getCheckBox().revalidate();

			valor.getCheckBox().repaint();

		}

		revalidate();

		repaint();

	}

	public ArrayList<Integer> getIndicesNoSeleccionados() {

		return indicesNoSeleccionados;

	}

	public ArrayList<Integer> getIndicesElementosSeleccionados() {

		ArrayList<Integer> resultado = new ArrayList<>();

		indicesNoSeleccionados.clear();

		try {

			for (int i = 0; i < numItems; i++) {

				if (paginas.get(i).getCheckBox().isSelected()) {

					resultado.add(i);

				}

				else {

					indicesNoSeleccionados.add(i);

				}

			}

			for (int i = numItems; i < getDatos().size(); i++) {

				indicesNoSeleccionados.add(i);

			}

		}

		catch (Exception e) {

		}

		return resultado;

	}

	public List<String> getCabeceras() {

		return cabeceras;

	}

	public void setCabeceras(List<String> cabeceras) {

		this.cabeceras = cabeceras;

	}

	public void setFileBottom(Color color) {

		colorFileBottom = color;

		revalidate();

		repaint();

	}

}
