package com.star.rating;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class StarRating extends javax.swing.JPanel {

	private List<EventStarRating> events = new ArrayList<>();

	private int star;

	private com.star.rating.Star star1;

	private com.star.rating.Star star2;

	private com.star.rating.Star star3;

	private com.star.rating.Star star4;

	private com.star.rating.Star star5;

	private boolean zero;

	public int getStar() {

		return star;

	}

	public void setStar(int star) {

		this.star = star;

		if (!zero && star < 1) {

			seleccionar(1);

		}

		else if (star > 4) {

			seleccionar(5);

		}

		else {

			seleccionar(star);

		}

	}

	public StarRating(boolean zero) {

		this.zero = zero;

		initComponents();

		init();

	}

	private void init() {

		setOpaque(false);

		setBackground(new Color(220, 220, 220));

		setForeground(new Color(238, 236, 0));

	}

	private void initComponents() {

		star1 = new com.star.rating.Star();

		star1.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				if (zero && e.getButton() == 3) {

					seleccionar(0);

				}

				else {

					seleccionar(1);

				}

			}
		});

		star2 = new com.star.rating.Star();

		star3 = new com.star.rating.Star();

		star4 = new com.star.rating.Star();

		star5 = new com.star.rating.Star();

		star1.setBorder(null);

		star2.setBorder(null);

		star3.setBorder(null);

		star4.setBorder(null);

		star5.setBorder(null);

		setLayout(new java.awt.GridLayout(1, 5));

		add(star1);

		star2.addActionListener(new java.awt.event.ActionListener() {

			@Override

			public void actionPerformed(java.awt.event.ActionEvent evt) {

				seleccionar(2);

			}

		});

		add(star2);

		star3.addActionListener(new java.awt.event.ActionListener() {

			@Override

			public void actionPerformed(java.awt.event.ActionEvent evt) {

				seleccionar(3);

			}

		});

		add(star3);

		star4.addActionListener(new java.awt.event.ActionListener() {

			@Override

			public void actionPerformed(java.awt.event.ActionEvent evt) {

				seleccionar(4);

			}

		});

		add(star4);

		star5.addActionListener(new java.awt.event.ActionListener() {

			@Override

			public void actionPerformed(java.awt.event.ActionEvent evt) {

				seleccionar(5);

			}

		});

		add(star5);

	}

	private void seleccionar(int rate) {

		star = rate;

		switch (rate) {

		case 1:

			star1.setSelected(true);

			star2.setSelected(false);

			star3.setSelected(false);

			star4.setSelected(false);

			star5.setSelected(false);

			break;

		case 2:

			star1.setSelected(true);

			star2.setSelected(true);

			star3.setSelected(false);

			star4.setSelected(false);

			star5.setSelected(false);

			break;

		case 3:

			star1.setSelected(true);

			star2.setSelected(true);

			star3.setSelected(true);

			star4.setSelected(false);

			star5.setSelected(false);

			break;

		case 4:

			star1.setSelected(true);

			star2.setSelected(true);

			star3.setSelected(true);

			star4.setSelected(true);

			star5.setSelected(false);

			break;

		case 5:

			star1.setSelected(true);

			star2.setSelected(true);

			star3.setSelected(true);

			star4.setSelected(true);

			star5.setSelected(true);

			break;

		default:

			star = 0;

			star1.setSelected(false);

			star2.setSelected(false);

			star3.setSelected(false);

			star4.setSelected(false);

			star5.setSelected(false);

			break;

		}

		runEvent();

	}

	@Override

	public void setBackground(Color color) {

		super.setBackground(color);

		for (Component com : getComponents()) {

			com.setBackground(color);

		}

	}

	@Override

	public void setForeground(Color color) {

		super.setForeground(color);

		for (Component com : getComponents()) {

			com.setForeground(color);

		}

	}

	public void addEventStarRating(EventStarRating event) {

		events.add(event);

	}

	private void runEvent() {

		for (EventStarRating event : events) {

			event.selected(star);

		}

	}

	public void setColors(Color color1, Color color2, Color color3, Color color4, Color color5) {

		if (color1 == null) {

			color1 = Color.YELLOW;

		}

		if (color2 == null) {

			color2 = Color.YELLOW;
		}

		if (color3 == null) {

			color3 = Color.YELLOW;

		}

		if (color4 == null) {

			color4 = Color.YELLOW;

		}

		if (color5 == null) {

			color5 = Color.YELLOW;

		}

		star1.setColor(color1);

		star2.setColor(color2);

		star3.setColor(color3);

		star4.setColor(color4);

		star5.setColor(color5);

	}

}
