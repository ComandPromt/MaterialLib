package com.search;

import java.awt.Color;
import java.awt.Component;
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

@SuppressWarnings("serial")

public class SearchInput extends JTextField {

	private Icon prefixIcon;

	private Icon suffixIcon;

	private LinkedList<String> dataStory;

	private JPopupMenu menu;

	private PanelSearch search;

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

	private void txtSearchMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_txtSearchMouseClicked

		if (search.getItemSize() > 0) {

			menu.show(this, 0, getHeight());

		}

	}

	private List<DataSearch> search(String search) {

		int limitData = 7;

		List<DataSearch> list = new ArrayList<>();

		String dataTesting[] = { "300 - Rise of an Empire", "Cosmic Sin", "Deadlock", "Deliver Us from Eva",
				"Empire of the Ants", "Empire of the Ants", "Empire of the Ants", "Empire of the Ants",
				"Empire of the Ants", "Empire of the Ants",

				"Empire of the Sun", "Empire Records", "Empire State", "Four Good Days", "Frozen Fever", "Frozen",
				"The Courier", "The First Purge", "To Olivia", "Underworld" };

		for (String d : dataTesting) {

			if (list.size() != limitData && d.toLowerCase().contains(search)) {

				boolean story = isStory(d);

				if (story) {

					list.add(0, new DataSearch(d, story));

				}

				else {

					list.add(new DataSearch(d, story));

				}

			}

		}

		return list;

	}

	private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {

		String text = getText().trim().toLowerCase();

		search.setData(search(text));

		if (evt.getKeyCode() == KeyEvent.VK_ENTER && !text.isEmpty() && !dataStory.contains(text)) {

			dataStory.add(text);

		}

		if (search.getItemSize() > 0) {

			menu.show(this, 0, getHeight());

			menu.setPopupSize(menu.getWidth(), (search.getItemSize() * 35) + 2);

		}

		else {

			menu.setVisible(false);

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

	public SearchInput() {

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

		menu.add(search);

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
