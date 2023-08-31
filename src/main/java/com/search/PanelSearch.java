package com.search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")

class PanelSearch extends javax.swing.JPanel {

	private EventClick event;

	public void addEventClick(EventClick event) {

		this.event = event;

	}

	public PanelSearch() {

		initComponents();

		setLayout(new MigLayout("fillx", "0[]0", "0[]0"));

	}

	public void setData(List<DataSearch> data) {

		this.removeAll();

		for (DataSearch d : data) {

			SearchItem item = new SearchItem(d);

			item.addEvent(new ActionListener() {

				@Override

				public void actionPerformed(ActionEvent ae) {

					event.itemClick(d);

				}

			}, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent ae) {

					event.itemRemove(item, d);

				}

			});

			this.add(item, "wrap");

		}

		repaint();

		revalidate();

	}

	public int getItemSize() {

		return getComponentCount();

	}

	private void initComponents() {

		setBackground(new java.awt.Color(255, 255, 255));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);

		this.setLayout(layout);

		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 438, Short.MAX_VALUE));

		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));

	}

}
