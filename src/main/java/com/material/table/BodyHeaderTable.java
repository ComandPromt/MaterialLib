package com.material.table;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import com.buttons.indicators.Indicators;
import com.checkbox.CheckBoxCustom;
import com.dialog.confirm.MessageDialog;
import com.dialog.confirm.MessageDialog.MessageType;
import com.material.table.item.AddItem;
import com.material.table.item.ItemHeader;
import com.panels.others.VerySimplePanel;
import com.search.SearchTextField;
import com.spinner.simple.Spinner;

import mthos.JMthos;

@SuppressWarnings("serial")
public class BodyHeaderTable extends JPanel {

	private CheckBoxCustom check;

	private NumPagination pagination;

	private Spinner spiner;

	private int filter;

	private Cuerpo cuerpo;

	private int cortePagination;

	private ArrayList<ItemHeader> items;

	private JPanel panel_1;

	private JPanel panel;

	private BotonesTabla botones;

	private JPanel customSeparator;

	private JPanel customSeparator2;

	private JPanel customSeparator3;

	private String deleteMsg;

	private int split;

	private String deleteAllMsg;

	private int corte;

	public void setDeleteIcon(String icon) {

		try {

			botones.getCrud().getDelete().setIcon(new ImageIcon(icon));

		}

		catch (Exception e) {

		}

	}

	public void setDeleteIcon(Icon icon) {

		try {

			botones.getCrud().getDelete().setIcon(icon);

		}

		catch (Exception e) {

		}

	}

	public void setDeleteIcon(ImageIcon icon) {

		try {

			botones.getCrud().getDelete().setIcon(icon);

		}

		catch (Exception e) {

		}

	}

	public void setAddIcon(String icon) {

		try {

			botones.getCrud().getNuevo().setIcon(new ImageIcon(icon));

		}

		catch (Exception e) {

		}

	}

	public void setAddIcon(Icon icon) {

		try {

			botones.getCrud().getNuevo().setIcon(icon);

		}

		catch (Exception e) {

		}

	}

	public void setAddIcon(ImageIcon icon) {

		try {

			botones.getCrud().getNuevo().setIcon(icon);

		}

		catch (Exception e) {

		}

	}

	public void setSearchBackground(Color color) {

		try {

			getSearchInput().setBackground(color);

		}

		catch (Exception e) {

		}

	}

	public SearchTextField getSearchInput() {

		try {

			return botones.getBusqueda().getTextField();

		}

		catch (Exception e) {

			return null;

		}

	}

	@Override
	public void setForeground(Color fg) {

		try {

			cuerpo.setForeground(fg);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setFont(Font font) {

		try {

			cuerpo.setFont(font);

		}

		catch (Exception e) {

		}

	}

	public void setActiveIconBackground(Color color) {

		try {

			for (ItemHeader valor : items) {

				valor.setSelectColor(color);

			}

		} catch (Exception e) {

		}

	}

	public void setHeadBackground(Color color) {

		try {

			panel_1.setBackground(color);

			for (ItemHeader valor : items) {

				valor.setBackground(color);

			}

		}

		catch (Exception e) {

		}

	}

	public void setFondoPaginacion(Color color) {

		try {

			pagination.setBackground(color);

		}

		catch (Exception e) {

		}

	}

	public void setCheckHeaderBackground(Color color) {

		try {

			check.setBackground(color);

		}

		catch (Exception e) {

		}

	}

	public void setCheckActiveHeader(Color color) {

		try {

			check.setCheckColor(color);

		}

		catch (Exception e) {

		}

	}

	public void setCheckActiveBody(Color color) {

		try {

			cuerpo.setCheckColor(color);

		}

		catch (Exception e) {

		}

	}

	public void setCheckBodyBackground(Color color) {

		try {

			cuerpo.setCheckBackground(color);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setBackground(Color bg) {

		try {

			cuerpo.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public int getFilter() {

		return filter;

	}

	public Spinner getSpiner() {

		return spiner;

	}

	public void setSeparador1(Color separado1) {

		customSeparator3.setBackground(separado1);

	}

	public void setSeparador2(Color separador1) {

		customSeparator.setBackground(separador1);

	}

	public void setSeparador3(Color separador2) {

		customSeparator2.setBackground(separador2);

	}

	private ArrayList<String> obtenerListaConSplit(ArrayList<String> lista, ArrayList<Integer> indices, int i) {

		ArrayList<String> resultado = new ArrayList<>();

		try {

			int contador = 0;

			if (!lista.equals(JMthos.obtenerListaConSplit(lista, cuerpo.getIndicesElementosSeleccionados().get(0),
					cuerpo.getSplit()))) {

				if (cuerpo.getIndicesElementosSeleccionados().size() < split
						&& cuerpo.getDatos().size() / ItemHeader.columnas <= split) {

					resultado = (ArrayList<String>) JMthos.borrarListaConSplit(cuerpo.getDatos(),
							cuerpo.getIndicesElementosSeleccionados(), ItemHeader.columnas);

				}

				else {

					for (int startIndex = 0; startIndex < lista.size(); startIndex++) {

						if (indices.contains(startIndex)) {

							if (startIndex == 0) {

								if (i >= lista.size()) {

									i = lista.size();

								}

								for (int x = 0; x < i; x++) {

									resultado.add(lista.get(x));

								}

							}

							else {

								contador = (startIndex + i / 2)
										+ JMthos.calcularSucesionAritmeticaAInt("1#0,2#1", startIndex);

								ArrayList<String> sublist = new ArrayList<>(lista.subList(contador, contador += i));

								for (String valor : sublist) {

									resultado.add(valor);

								}

							}

						}

					}

				}

			}

		}

		catch (Exception e) {

		}

		return resultado;

	}

	public void setDeleteMsg(String deleteMsg) {

		this.deleteMsg = deleteMsg;

	}

	public void setDeleteAllMsg(String deleteAllMsg) {

		this.deleteAllMsg = deleteAllMsg;

	}

	public BodyHeaderTable(List<String> columns, ArrayList<String> lista, int pageItems, int splitPagination,
			Color footer) {

		corte = splitPagination;

		split = pageItems;

		deleteMsg = "¿Quieres borrar los datos?";

		deleteAllMsg = "¿Quieres vaciar la tabla?";

		if (pageItems < 1) {

			pageItems = 4;

		}

		if (splitPagination < 1) {

			splitPagination = 2;

		}

		cortePagination = splitPagination;

		if (footer == null) {

			footer = Color.WHITE;

		}

		filter = pageItems;

		cuerpo = new Cuerpo(lista, pageItems, columns.size());

		cuerpo.setNumItems(split);

		int numeroPaginas = JMthos.dividirYRedondearAEntero(lista.size(), (cuerpo.getItems() * cuerpo.getSplit()));

		if (splitPagination > numeroPaginas) {

			splitPagination = numeroPaginas;

		}

		panel = new JPanel();

		panel.setBounds(0, 0, 450, 47);

		panel_1 = new JPanel();

		JPanel panel_2 = new JPanel();

		customSeparator = new JPanel();

		customSeparator2 = new JPanel();

		customSeparator3 = new JPanel();

		JPanel panel_5 = new JPanel();

		panel.setLayout(new GridLayout());

		panel_1.setLayout(new GridLayout());

		panel_5.setLayout(new GridLayout());

		cuerpo.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				System.out.println("Clickado el item ");

			}

		});

		pagination = new NumPagination(spiner, lista.size(), numeroPaginas, splitPagination, cuerpo);

		pagination.setCorte(corte);

		panel_2.setLayout(new GridLayout());

		check = new CheckBoxCustom();

		check.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				cuerpo.seleccionarTodo(check.isSelected());

				cuerpo.revalidate();

				cuerpo.repaint();

			}

		});

		botones = new BotonesTabla(cuerpo.getDatos());

		botones.getCrud().getNuevo().addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				botones.getCrud().getNuevo().addMouseListener(new MouseAdapter() {

					@Override

					public void mousePressed(MouseEvent e) {

						ArrayList<JComponent> componentes = new ArrayList<>();

						componentes.add(new VerySimplePanel(new AddItem(numeroPaginas, pagination, cuerpo, columns)));

						JMthos.showNewDialog(botones.getCrud().getNuevo(),
								JMthos.calcularSucesionAritmeticaAInt("1#300,2#400", columns.size()), 200, false, "Add",
								JMthos.convertArrayListToList(componentes));

					}

				});

			}

		});

		botones.getCrud().getDelete().addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				ArrayList<String> listaIndice = new ArrayList<>();

				ArrayList<Integer> datosSeleccionados = cuerpo.getIndicesElementosSeleccionados();

				MessageDialog dialogo;

				if (!datosSeleccionados.isEmpty()) {

					if (datosSeleccionados.size() == split) {

						dialogo = new MessageDialog(Color.RED, Color.WHITE, "", deleteAllMsg);

						if (dialogo.getMessageType().equals(MessageType.OK)) {

							cuerpo.setDatos(listaIndice);

							cuerpo.verDatos(0, listaIndice);

							pagination.setPaso((cuerpo.getItems() / columns.size()) / cuerpo.getSplit());

							pagination.setStep((cuerpo.getItems() / columns.size()) / cuerpo.getSplit());

							spiner.setMaxValor(1);

							cortePagination = 1;

							pagination.verNumeros(spiner.getValor(), numeroPaginas, 1, cuerpo);

							botones.getBusqueda().setLista(listaIndice);

						}

					}

					else {

						dialogo = new MessageDialog(Color.RED, Color.WHITE, "", deleteMsg);

						if (dialogo.getMessageType().equals(MessageType.OK)) {

							ArrayList<String> datos = cuerpo.getDatos();

							listaIndice = obtenerListaConSplit(datos, cuerpo.getIndicesNoSeleccionados(),
									cortePagination);

							cuerpo.setDatos(listaIndice);

							cuerpo.verDatos(0, listaIndice);

							int numeroPaginas = JMthos.dividirYRedondearAEntero(listaIndice.size(),
									(cuerpo.getItems() * cuerpo.getSplit()));

							spiner.setMaxValor(numeroPaginas);

							if (numeroPaginas == 1) {

								cortePagination = 1;

							}

							pagination.setNumeroPaginas(numeroPaginas);

							pagination.verNumeros(spiner.getValor(), numeroPaginas, cortePagination, cuerpo);

							botones.getBusqueda().setLista(listaIndice);

						}

					}

				}

			}

		});

		botones.getBusqueda().getTextField().getSearchField().

				addKeyListener(new KeyAdapter() {

					@Override

					public void keyPressed(KeyEvent e) {

						try {

							if (JMthos.isEnter(e)) {

								cuerpo.borrarDatos();

								cuerpo.verDatos(
										JMthos.calcularSucesionAritmeticaAInt(
												"1#0,2#" + cuerpo.getItems() * cuerpo.getSplit(), spiner.getValor()),
										JMthos.encontrarCoincidencias(cuerpo.getDatos(),
												botones.getBusqueda().getTextField().getSearchField().getText(),
												cuerpo.getSplit()));

							}

						}

				catch (Exception e1) {

						}

					}

				});

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				panel.setBounds(0, 0, getWidth(), Math.round(getHeight() * 0.105f));

				customSeparator3.setBounds(0, Math.round(getHeight() * 0.105f), getWidth(), 2);

				panel_1.setBounds(0, Math.round(getHeight() * 0.108f), Math.round(getWidth() * 0.025f),
						Math.round(getHeight() * 0.095f));

				panel_2.setBounds(Math.round(getWidth() * 0.025f), Math.round(getHeight() * 0.108f),
						getWidth() - Math.round(getWidth() * 0.025f), Math.round(getHeight() * 0.095f));

				customSeparator.setBounds(0, Math.round(getHeight() * 0.095f) + Math.round(getHeight() * 0.108f),
						getWidth(), 2);

				cuerpo.setBounds(0, Math.round(getHeight() * 0.206f), getWidth(), Math.round(getHeight() * 0.692f));

				pagination.setBounds(Math.round(getWidth() * 0.1f), Math.round(getHeight() * 0.9f),
						Math.round(getWidth() * 0.9f), Math.round(getHeight() * 0.1f));

				customSeparator2.setBounds(0, Math.round(getHeight() * 0.8958f), getWidth(), 2);

				panel_5.setBounds(0, Math.round(getHeight() * 0.9f), Math.round(getWidth() * 0.1f),
						Math.round(getHeight() * 0.1f));

			}

		});

		panel.add(botones);

		panel_1.add(check);

		panel_1.setBackground(Color.WHITE);

		items = new ArrayList<>();

		ItemHeader itemCabecera = null;

		for (int i = 0; i < columns.size(); i++) {

			itemCabecera = new ItemHeader(columns.get(i));

			itemCabecera.setIndice(i);

			itemCabecera.setIndice(i);

			items.add(itemCabecera);

			panel_2.add(itemCabecera);

		}

		try {

			ItemHeader.cuerpo = cuerpo;

			ItemHeader.columnas = columns.size();

		}

		catch (Exception e) {

		}

		customSeparator.setBackground(Color.BLACK);

		customSeparator2.setBackground(Color.BLACK);

		customSeparator3.setBackground(Color.BLACK);

		setLayout(null);

		add(customSeparator3);

		add(panel);

		add(panel_1);

		add(panel_2);

		add(customSeparator);

		add(cuerpo);

		add(customSeparator2);

		add(pagination);

		spiner = new Spinner(1, numeroPaginas);

		spiner.getEditor().addKeyListener(new KeyAdapter() {

			@Override

			public void keyReleased(KeyEvent e) {

				try {

					if (JMthos.isEnter(e)) {

						cuerpo.verDatos(
								JMthos.calcularSucesionAritmeticaAInt("1#0,2#" + cuerpo.getItems() * cuerpo.getSplit(),
										spiner.getValor()),
								cuerpo.getDatos());

						if ((filter - spiner.getValor()) <= numeroPaginas) {

							pagination.verNumeros(spiner.getValor(), numeroPaginas, cortePagination, cuerpo);
						}

						else {

							if (cortePagination > numeroPaginas) {

								pagination.verNumeros(1, filter, numeroPaginas, cuerpo);

							}

							else {

								pagination.verNumeros(spiner.getValor(), numeroPaginas, cortePagination, cuerpo);

							}

						}

						pagination.getNumeros().getNumeros().get(
								JMthos.encontrarIndice(pagination.getNumeros().getContadorNumeros(), spiner.getValor()))
								.setPaintSelected(true);

						pagination.setIndice(spiner.getValor());

						pagination.setCick(false);

					}

				}

				catch (Exception e2) {

					pagination.verNumeros(1, numeroPaginas, cortePagination, cuerpo);

					cuerpo.verDatos(0, cuerpo.getDatos());

				}

			}

		});

		panel_5.add(spiner);

		add(panel_5);

		setBackground(Color.WHITE);

		setCheckActiveBody(Color.WHITE);

		setCheckBodyBackground(new Color(69, 124, 235));

		setCheckActiveBodyBackground(new Color(69, 124, 235));

		getSearchInput().setBackground(Color.WHITE);

	}

	public void setCheckActiveBodyBackground(Color color) {

		try {

			cuerpo.setCheckActiveBodyBackground(color);

		}

		catch (Exception e) {

		}

	}

	public void setCheckActiveHeaderBackground(Color color) {

		try {

			check.setColorActivo(color);

		}

		catch (Exception e) {

		}

	}

	public void setDisableIconBackground(Color color) {

		try {

			for (ItemHeader valor : items) {

				valor.setDisableColor(color);

			}

		}

		catch (Exception e) {

		}

	}

	public void setIconsBackground(Color color) {

		try {

			botones.setBackground(color);

		}

		catch (Exception e) {

		}

	}

	public void setPaginationFont(Font font) {

		try {

			pagination.setFont(font);

		} catch (Exception e) {

		}

	}

	public void setPaginationForeground(Color color) {

		try {

			pagination.setForeground(color);

		}

		catch (Exception e) {

		}

	}

	public void setHeadForeground(Color color) {

		try {

			for (ItemHeader valor : items) {

				valor.setForeground(color);

			}

		}

		catch (Exception e) {

		}

	}

	public void setHeadFont(Font font) {

		try {

			for (ItemHeader valor : items) {

				valor.setFont(font);

			}

		}

		catch (Exception e) {

		}

	}

	public void setIndicadorType(Indicators indicador) {

		try {

			pagination.setIndicadorType(indicador);

		}

		catch (Exception e) {

		}

	}

	public void setIndicador(Color color) {

		try {

			pagination.setIndicador(color);

		}

		catch (Exception e) {

		}

	}

}
