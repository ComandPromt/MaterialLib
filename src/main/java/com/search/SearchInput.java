package com.search;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JToolTip;

import com.scrollbar.MaterialPanelDeslizante;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class SearchInput extends JTextField {

	private Icon prefixIcon;

	private Icon suffixIcon;

	private LinkedList<String> dataStory;

	private JPopupMenu menu;

	private PanelSearch search;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private LinkedList<String> dataTesting;

	private int index;

	private String[] resultadosBusqueda;

	private boolean seleccionado;

	public void setDataTesting(LinkedList<String> dataTesting) {

		this.dataTesting = dataTesting;

		repaint();

	}

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

			font = getFont().deriveFont(14f);

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

	public Icon getPrefixIcon() {

		return prefixIcon;

	}

	public void setPrefixIcon(Icon prefixIcon) {

		this.prefixIcon = prefixIcon;

		initBorder();

	}

	public Icon getSuffixIcon() {

		return suffixIcon;

	}

	public void setSuffixIcon(Icon suffixIcon) {

		this.suffixIcon = suffixIcon;

		initBorder();

	}

	private void txtSearchMouseClicked(java.awt.event.MouseEvent evt) {

		if (search.getItemSize() > 0) {

			menu.show(this, 0, getHeight());

		}

	}

	private List<DataSearch> search(String search) {

		List<DataSearch> list = new ArrayList<>();

		if (dataTesting != null && !dataTesting.isEmpty() && search != null && !search.isEmpty()) {

			int limitData = 8;

			boolean story;

			int contador = -1;

			for (String d : dataTesting) {

				if (list.size() != limitData && d.toLowerCase().contains(search)) {

					contador++;

					resultadosBusqueda[contador] = d;

					story = isStory(d);

					if (story) {

						list.add(0, new DataSearch(d, story));

					}

					else {

						list.add(new DataSearch(d, story));

					}

				}

			}

		}

		return list;

	}

	private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {

		try {

			String text = getText().trim().toLowerCase();

			search.setData(search(text));

			if (evt.getKeyCode() == KeyEvent.VK_ENTER && !text.isEmpty() && !dataStory.contains(text)) {

				dataStory.add(text);

			}

			if (seleccionado && evt.getKeyCode() == KeyEvent.VK_ENTER) {

				setText(resultadosBusqueda[index]);

			}

			else {

				if ((evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN)
						&& (!seleccionado && search.getComponentCount() > 0)) {

					seleccionado = true;

				}

				if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

					index++;

					if (index == search.getComponentCount()) {

						index = 0;

					}

					else if (search.getComponentCount() == 0) {

						index = -1;

					}

					if (index > -1) {

						search.getComponent(index).setBackground(Color.decode("#d6d8d5"));

					}

				}

				else if (evt.getKeyCode() == KeyEvent.VK_UP) {

					index--;

					if (index <= -1) {

						index = search.getComponentCount() - 1;

					}

					if (index > -1) {

						search.getComponent(index).setBackground(Color.decode("#d6d8d5"));

					}

				}

			}

			if (search.getItemSize() > 0) {

				menu.show(this, 0, getHeight());

				menu.setPopupSize(menu.getWidth(), (search.getItemSize() * 35) + 2);

			}

			else {

				menu.setVisible(false);

			}

		}

		catch (Exception e) {

		}

	}

	private boolean isStory(String text) {

		try {

			for (String d : dataStory) {

				if (d.toLowerCase().equals(text.toLowerCase())) {

					return true;

				}

			}

			return false;

		}

		catch (Exception e) {

			return false;

		}

	}

	private void removeHistory(String text) {

		for (int i = 0; i < dataStory.size(); i++) {

			String d = dataStory.get(i);

			if (d.toLowerCase().equals(text.toLowerCase())) {

				dataStory.remove(i);

			}

		}

	}

	public SearchInput(List<String> lista) {

		index = -1;

		resultadosBusqueda = new String[8];

		dataTesting = (LinkedList<String>) lista;

		dataStory = new LinkedList<>();

		setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 5, 7, 5));

		addMouseListener(new java.awt.event.MouseAdapter() {

			public void mouseClicked(java.awt.event.MouseEvent evt) {

				txtSearchMouseClicked(evt);

			}

		});

		addKeyListener(new java.awt.event.KeyAdapter() {

			public void keyReleased(java.awt.event.KeyEvent evt) {

				txtSearchKeyReleased(evt);

			}

		});

		menu = new JPopupMenu();

		search = new PanelSearch();

		menu.setBorder(BorderFactory.createLineBorder(new Color(164, 164, 164)));

		menu.add(new MaterialPanelDeslizante(search, null, null));

		menu.setFocusable(false);

		search.addEventClick(new EventClick() {

			@Override

			public void itemClick(DataSearch data) {

				menu.setVisible(false);

				setText(data.getText());

			}

			@Override
			public void itemRemove(Component com, DataSearch data) {

				search.remove(com);

				removeHistory(data.getText());

				menu.setPopupSize(menu.getWidth(), (search.getItemSize() * 35) + 2);

				if (search.getItemSize() == 0) {

					menu.setVisible(false);

				}

			}

		});

		setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/imagenes/search.png")));

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		paintIcon(g);

		if (isFocusOwner()) {

			g.setColor(new Color(6, 135, 196));

			g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

			g.drawRect(1, 1, getWidth() - 3, getHeight() - 3);

		}

		else {

			g.setColor(new Color(76, 181, 195));

			g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

			g.drawRect(1, 1, getWidth() - 3, getHeight() - 3);

		}

	}

	private void paintIcon(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		if (prefixIcon != null) {

			Image prefix = ((ImageIcon) prefixIcon).getImage();

			int y = (getHeight() - prefixIcon.getIconHeight()) / 2;

			g2.drawImage(prefix, 3, y, this);

		}

		if (suffixIcon != null) {

			Image suffix = ((ImageIcon) suffixIcon).getImage();

			int y = (getHeight() - suffixIcon.getIconHeight()) / 2;

			g2.drawImage(suffix, getWidth() - suffixIcon.getIconWidth() - 3, y, this);

		}

	}

	private void initBorder() {

		int left = 5;

		int right = 5;

		if (prefixIcon != null) {

			left = prefixIcon.getIconWidth() + 5;

		}

		if (suffixIcon != null) {

			right = suffixIcon.getIconWidth() + 5;

		}

		setBorder(javax.swing.BorderFactory.createEmptyBorder(7, left, 7, right));

	}

}
