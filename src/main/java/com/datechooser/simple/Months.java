package com.datechooser.simple;

import com.datechooser.language.CalendarLanguage;

@SuppressWarnings("serial")

public final class Months extends javax.swing.JPanel {

	private Event event;

	private CalendarLanguage idioma;

	private com.datechooser.simple.Button cmd1;

	private com.datechooser.simple.Button cmd10;

	private com.datechooser.simple.Button cmd11;

	private com.datechooser.simple.Button cmd12;

	private com.datechooser.simple.Button cmd2;

	private com.datechooser.simple.Button cmd3;

	private com.datechooser.simple.Button cmd4;

	private com.datechooser.simple.Button cmd5;

	private com.datechooser.simple.Button cmd6;

	private com.datechooser.simple.Button cmd7;

	private com.datechooser.simple.Button cmd8;

	private com.datechooser.simple.Button cmd9;

	public Months(CalendarLanguage language) {

		this.idioma = language;

		initComponents();

	}

	private void addEvent() {

		for (int i = 0; i < getComponentCount(); i++) {

			((Button) getComponent(i)).setEvent(event);

		}

	}

	private void initComponents() {

		cmd1 = new com.datechooser.simple.Button();

		cmd2 = new com.datechooser.simple.Button();

		cmd3 = new com.datechooser.simple.Button();

		cmd4 = new com.datechooser.simple.Button();

		cmd5 = new com.datechooser.simple.Button();

		cmd6 = new com.datechooser.simple.Button();

		cmd7 = new com.datechooser.simple.Button();

		cmd8 = new com.datechooser.simple.Button();

		cmd9 = new com.datechooser.simple.Button();

		cmd10 = new com.datechooser.simple.Button();

		cmd11 = new com.datechooser.simple.Button();

		cmd12 = new com.datechooser.simple.Button();

		switch (idioma) {

		default:

		case ENGLISH:

			cmd1.setText("January");

			cmd2.setText("February");

			cmd3.setText("March");

			cmd4.setText("April");

			cmd5.setText("May");

			cmd6.setText("June");

			cmd7.setText("July");

			cmd8.setText("August");

			cmd9.setText("September");

			cmd10.setText("October");

			cmd11.setText("November");

			cmd12.setText("December");

			break;

		case ESPAÑOL:

			cmd1.setText("Enero");

			cmd2.setText("Febrero");

			cmd3.setText("Marzo");

			cmd4.setText("Abril");

			cmd5.setText("Mayo");

			cmd6.setText("Junio");

			cmd7.setText("Julio");

			cmd8.setText("Agosto");

			cmd9.setText("Septiembre");

			cmd10.setText("Octubre");

			cmd11.setText("Noviembre");

			cmd12.setText("Diciembre");

			break;

		}

		setBackground(new java.awt.Color(255, 255, 255));

		setLayout(new java.awt.GridLayout(4, 4));

		cmd1.setBackground(new java.awt.Color(255, 255, 255));

		cmd1.setForeground(new java.awt.Color(75, 75, 75));

		cmd1.setName("1");

		cmd1.setOpaque(true);

		add(cmd1);

		cmd2.setBackground(new java.awt.Color(255, 255, 255));

		cmd2.setForeground(new java.awt.Color(75, 75, 75));

		cmd2.setName("2");

		cmd2.setOpaque(true);

		add(cmd2);

		cmd3.setBackground(new java.awt.Color(255, 255, 255));

		cmd3.setForeground(new java.awt.Color(75, 75, 75));

		cmd3.setName("3");

		cmd3.setOpaque(true);

		add(cmd3);

		cmd4.setBackground(new java.awt.Color(255, 255, 255));

		cmd4.setForeground(new java.awt.Color(75, 75, 75));

		cmd4.setName("4");

		cmd4.setOpaque(true);

		add(cmd4);

		cmd5.setBackground(new java.awt.Color(255, 255, 255));

		cmd5.setForeground(new java.awt.Color(75, 75, 75));

		cmd5.setName("5");

		cmd5.setOpaque(true);

		add(cmd5);

		cmd6.setBackground(new java.awt.Color(255, 255, 255));

		cmd6.setForeground(new java.awt.Color(75, 75, 75));

		cmd6.setName("6");

		cmd6.setOpaque(true);

		add(cmd6);

		cmd7.setBackground(new java.awt.Color(255, 255, 255));

		cmd7.setForeground(new java.awt.Color(75, 75, 75));

		cmd7.setName("7");

		cmd7.setOpaque(true);

		add(cmd7);

		cmd8.setBackground(new java.awt.Color(255, 255, 255));

		cmd8.setForeground(new java.awt.Color(75, 75, 75));

		cmd8.setName("8");

		cmd8.setOpaque(true);

		add(cmd8);

		cmd9.setBackground(new java.awt.Color(255, 255, 255));

		cmd9.setForeground(new java.awt.Color(75, 75, 75));

		cmd9.setName("9");

		cmd9.setOpaque(true);

		add(cmd9);

		cmd10.setBackground(new java.awt.Color(255, 255, 255));

		cmd10.setForeground(new java.awt.Color(75, 75, 75));

		cmd10.setName("10");

		cmd10.setOpaque(true);

		add(cmd10);

		cmd11.setBackground(new java.awt.Color(255, 255, 255));

		cmd11.setForeground(new java.awt.Color(75, 75, 75));

		cmd11.setName("11");

		cmd11.setOpaque(true);

		add(cmd11);

		cmd12.setBackground(new java.awt.Color(255, 255, 255));

		cmd12.setForeground(new java.awt.Color(75, 75, 75));

		cmd12.setName("12");

		cmd12.setOpaque(true);

		add(cmd12);

	}

	public Event getEvent() {

		return event;

	}

	public void setEvent(Event event) {

		this.event = event;

		addEvent();

	}

}
