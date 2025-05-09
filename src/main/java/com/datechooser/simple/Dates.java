package com.datechooser.simple;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;

import com.datechooser.language.CalendarLanguage;

@SuppressWarnings("serial")

public final class Dates extends javax.swing.JPanel {

	private Event event;

	private final int MONTH;

	private final int YEAR;

	private final int DAY;

	private int m;

	private int y;

	private int selectDay = 0;

	private int startDate;

	private int max_of_month;

	private CalendarLanguage idioma;

	private com.datechooser.simple.Button cmd1;

	private com.datechooser.simple.Button cmd10;

	private com.datechooser.simple.Button cmd11;

	private com.datechooser.simple.Button cmd12;

	private com.datechooser.simple.Button cmd13;

	private com.datechooser.simple.Button cmd14;

	private com.datechooser.simple.Button cmd15;

	private com.datechooser.simple.Button cmd16;

	private com.datechooser.simple.Button cmd17;

	private com.datechooser.simple.Button cmd18;

	private com.datechooser.simple.Button cmd19;

	private com.datechooser.simple.Button cmd2;

	private com.datechooser.simple.Button cmd20;

	private com.datechooser.simple.Button cmd21;

	private com.datechooser.simple.Button cmd22;

	private com.datechooser.simple.Button cmd23;

	private com.datechooser.simple.Button cmd24;

	private com.datechooser.simple.Button cmd25;

	private com.datechooser.simple.Button cmd26;

	private com.datechooser.simple.Button cmd27;

	private com.datechooser.simple.Button cmd28;

	private com.datechooser.simple.Button cmd29;

	private com.datechooser.simple.Button cmd3;

	private com.datechooser.simple.Button cmd30;

	private com.datechooser.simple.Button cmd31;

	private com.datechooser.simple.Button cmd32;

	private com.datechooser.simple.Button cmd33;

	private com.datechooser.simple.Button cmd34;

	private com.datechooser.simple.Button cmd35;

	private com.datechooser.simple.Button cmd36;

	private com.datechooser.simple.Button cmd37;

	private com.datechooser.simple.Button cmd38;

	private com.datechooser.simple.Button cmd39;

	private com.datechooser.simple.Button cmd4;

	private com.datechooser.simple.Button cmd40;

	private com.datechooser.simple.Button cmd41;

	private com.datechooser.simple.Button cmd42;

	private com.datechooser.simple.Button cmd5;

	private com.datechooser.simple.Button cmd6;

	private com.datechooser.simple.Button cmd7;

	private com.datechooser.simple.Button cmd8;

	private com.datechooser.simple.Button cmd9;

	private com.datechooser.simple.Button cmdFr;

	private com.datechooser.simple.Button cmdMo;

	private com.datechooser.simple.Button cmdSa;

	private com.datechooser.simple.Button cmdSu;

	private com.datechooser.simple.Button cmdTh;

	private com.datechooser.simple.Button cmdTu;

	private com.datechooser.simple.Button cmdWe;

	public Dates(CalendarLanguage language) {

		this.idioma = language;

		initComponents();

		SimpleDateFormat df = new SimpleDateFormat(DateChooser.dateFormat);

		Date date = new Date();

		String toDay = df.format(date);

		DAY = Integer.valueOf(toDay.split(Character.toString(DateChooser.separator))[0]);

		MONTH = Integer.valueOf(toDay.split(Character.toString(DateChooser.separator))[1]);

		YEAR = Integer.valueOf(toDay.split(Character.toString(DateChooser.separator))[2]);

	}

	public void showDate(int month, int year, SelectedDate select) {

		m = month;

		y = year;

		Calendar cd = Calendar.getInstance();

		cd.set(year, month - 1, 1);

		int start = cd.get(Calendar.DAY_OF_WEEK);

		max_of_month = cd.getActualMaximum(Calendar.DAY_OF_MONTH);

		if (start == 1) {

			start += 7;

		}

		clear();

		start += 5;

		startDate = start;

		for (int i = 1; i <= max_of_month; i++) {

			Button cmd = (Button) getComponent(start);

			cmd.setColorSelected(getForeground());

			cmd.setText(i + "");

			if (i == DAY && month == MONTH && year == YEAR) {

				cmd.setBackground(new Color(224, 214, 229));

			}

			else {

				cmd.setBackground(Color.WHITE);

			}

			if (i == select.getDay() && month == select.getMonth() && year == select.getYear()) {

				cmd.setBackground(getForeground());

				cmd.setForeground(new Color(255, 255, 255));

			}

			start++;

		}

	}

	private void clear() {

		for (int i = 7; i < getComponentCount(); i++) {

			((JButton) getComponent(i)).setText("");

		}

	}

	public void clearSelected() {

		for (int i = 7; i < getComponentCount(); i++) {

			JButton cmd = (JButton) getComponent(i);

			if (MONTH == m && y == YEAR && !cmd.getText().equals("") && Integer.valueOf(cmd.getText()) == DAY) {

				cmd.setBackground(new Color(224, 214, 229));

				cmd.setForeground(new java.awt.Color(75, 75, 75));

			}

			else {

				cmd.setBackground(Color.WHITE);

				cmd.setForeground(new java.awt.Color(75, 75, 75));

			}

		}

		selectDay = 0;

	}

	private void addEvent() {

		for (int i = 7; i < getComponentCount(); i++) {

			((Button) getComponent(i)).setEvent(event);

		}

	}

	public void setSelected(int index) {

		selectDay = index;

	}

	private void initComponents() {

		cmdMo = new com.datechooser.simple.Button();

		cmdTu = new com.datechooser.simple.Button();

		cmdWe = new com.datechooser.simple.Button();

		cmdTh = new com.datechooser.simple.Button();

		cmdFr = new com.datechooser.simple.Button();

		cmdSa = new com.datechooser.simple.Button();

		cmdSu = new com.datechooser.simple.Button();

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

		cmd13 = new com.datechooser.simple.Button();

		cmd14 = new com.datechooser.simple.Button();

		cmd15 = new com.datechooser.simple.Button();

		cmd16 = new com.datechooser.simple.Button();

		cmd17 = new com.datechooser.simple.Button();

		cmd18 = new com.datechooser.simple.Button();

		cmd19 = new com.datechooser.simple.Button();

		cmd20 = new com.datechooser.simple.Button();

		cmd21 = new com.datechooser.simple.Button();

		cmd22 = new com.datechooser.simple.Button();

		cmd23 = new com.datechooser.simple.Button();

		cmd24 = new com.datechooser.simple.Button();

		cmd25 = new com.datechooser.simple.Button();

		cmd26 = new com.datechooser.simple.Button();

		cmd27 = new com.datechooser.simple.Button();

		cmd28 = new com.datechooser.simple.Button();

		cmd29 = new com.datechooser.simple.Button();

		cmd30 = new com.datechooser.simple.Button();

		cmd31 = new com.datechooser.simple.Button();

		cmd32 = new com.datechooser.simple.Button();

		cmd33 = new com.datechooser.simple.Button();

		cmd34 = new com.datechooser.simple.Button();

		cmd35 = new com.datechooser.simple.Button();

		cmd36 = new com.datechooser.simple.Button();

		cmd37 = new com.datechooser.simple.Button();

		cmd38 = new com.datechooser.simple.Button();

		cmd39 = new com.datechooser.simple.Button();

		cmd40 = new com.datechooser.simple.Button();

		cmd41 = new com.datechooser.simple.Button();

		cmd42 = new com.datechooser.simple.Button();

		setBackground(new java.awt.Color(255, 255, 255));

		setLayout(new java.awt.GridLayout(7, 7));

		cmdMo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));

		cmdMo.setForeground(new java.awt.Color(118, 118, 118));

		switch (this.idioma) {

		default:

		case ENGLISH:

			cmdMo.setText("Monday");

			cmdTu.setText("Tuesday");

			cmdWe.setText("Wednesday");

			cmdTh.setText("Thursday");

			cmdFr.setText("Friday");

			cmdSa.setText("Saturday");

			cmdSu.setText("Sunday");

			break;

		case ESPAÑOL:

			cmdMo.setText("Lunes");

			cmdTu.setText("Martes");

			cmdWe.setText("Mi�rcoles");

			cmdTh.setText("Jueves");

			cmdFr.setText("Viernes");

			cmdSa.setText("S�bado");

			cmdSu.setText("Domingo");

			break;

		}

		add(cmdMo);

		cmdTu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));

		cmdTu.setForeground(new java.awt.Color(118, 118, 118));

		add(cmdTu);

		cmdWe.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));

		cmdWe.setForeground(new java.awt.Color(118, 118, 118));

		add(cmdWe);

		cmdTh.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));

		cmdTh.setForeground(new java.awt.Color(118, 118, 118));

		add(cmdTh);

		cmdFr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));

		cmdFr.setForeground(new java.awt.Color(118, 118, 118));

		add(cmdFr);

		cmdSa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));

		cmdSa.setForeground(new java.awt.Color(118, 118, 118));

		add(cmdSa);

		cmdSu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));

		cmdSu.setForeground(new java.awt.Color(255, 1, 1));

		add(cmdSu);

		cmd1.setBackground(new java.awt.Color(255, 255, 255));

		cmd1.setForeground(new java.awt.Color(75, 75, 75));

		cmd1.setName("day");

		add(cmd1);

		cmd2.setBackground(new java.awt.Color(255, 255, 255));

		cmd2.setForeground(new java.awt.Color(75, 75, 75));

		cmd2.setName("day");

		add(cmd2);

		cmd3.setBackground(new java.awt.Color(255, 255, 255));

		cmd3.setForeground(new java.awt.Color(75, 75, 75));

		cmd3.setText("1");

		cmd3.setName("day");

		add(cmd3);

		cmd4.setBackground(new java.awt.Color(255, 255, 255));

		cmd4.setForeground(new java.awt.Color(75, 75, 75));

		cmd4.setText("2");

		cmd4.setName("day");

		add(cmd4);

		cmd5.setBackground(new java.awt.Color(255, 255, 255));

		cmd5.setForeground(new java.awt.Color(75, 75, 75));

		cmd5.setText("3");

		cmd5.setName("day");

		add(cmd5);

		cmd6.setBackground(new java.awt.Color(255, 255, 255));

		cmd6.setForeground(new java.awt.Color(75, 75, 75));

		cmd6.setText("4");

		cmd6.setName("day");

		add(cmd6);

		cmd7.setBackground(new java.awt.Color(255, 255, 255));

		cmd7.setForeground(new java.awt.Color(75, 75, 75));

		cmd7.setText("5");

		cmd7.setName("day");

		add(cmd7);

		cmd8.setBackground(new java.awt.Color(255, 255, 255));

		cmd8.setForeground(new java.awt.Color(75, 75, 75));

		cmd8.setText("6");

		cmd8.setName("day");

		add(cmd8);

		cmd9.setBackground(new java.awt.Color(255, 255, 255));

		cmd9.setForeground(new java.awt.Color(75, 75, 75));

		cmd9.setText("7");

		cmd9.setName("day");

		add(cmd9);

		cmd10.setBackground(new java.awt.Color(255, 255, 255));

		cmd10.setForeground(new java.awt.Color(75, 75, 75));

		cmd10.setText("8");

		cmd10.setName("day");

		add(cmd10);

		cmd11.setBackground(new java.awt.Color(255, 255, 255));

		cmd11.setForeground(new java.awt.Color(75, 75, 75));

		cmd11.setText("9");

		cmd11.setName("day");

		add(cmd11);

		cmd12.setBackground(new java.awt.Color(255, 255, 255));

		cmd12.setForeground(new java.awt.Color(75, 75, 75));

		cmd12.setText("10");

		cmd12.setName("day");

		add(cmd12);

		cmd13.setBackground(new java.awt.Color(255, 255, 255));

		cmd13.setForeground(new java.awt.Color(75, 75, 75));

		cmd13.setText("11");

		cmd13.setName("day");

		add(cmd13);

		cmd14.setBackground(new java.awt.Color(255, 255, 255));

		cmd14.setForeground(new java.awt.Color(75, 75, 75));

		cmd14.setText("12");

		cmd14.setName("day");

		add(cmd14);

		cmd15.setBackground(new java.awt.Color(255, 255, 255));

		cmd15.setForeground(new java.awt.Color(75, 75, 75));

		cmd15.setText("13");

		cmd15.setName("day");

		add(cmd15);

		cmd16.setBackground(new java.awt.Color(255, 255, 255));

		cmd16.setForeground(new java.awt.Color(75, 75, 75));

		cmd16.setText("14");

		cmd16.setName("day");

		add(cmd16);

		cmd17.setBackground(new java.awt.Color(255, 255, 255));

		cmd17.setForeground(new java.awt.Color(75, 75, 75));

		cmd17.setText("15");

		cmd17.setName("day");

		add(cmd17);

		cmd18.setBackground(new java.awt.Color(255, 255, 255));

		cmd18.setForeground(new java.awt.Color(75, 75, 75));

		cmd18.setText("16");

		cmd18.setName("day");

		add(cmd18);

		cmd19.setBackground(new java.awt.Color(255, 255, 255));

		cmd19.setForeground(new java.awt.Color(75, 75, 75));

		cmd19.setText("17");

		cmd19.setName("day");

		add(cmd19);

		cmd20.setBackground(new java.awt.Color(255, 255, 255));

		cmd20.setForeground(new java.awt.Color(75, 75, 75));

		cmd20.setText("18");

		cmd20.setName("day");

		add(cmd20);

		cmd21.setBackground(new java.awt.Color(255, 255, 255));

		cmd21.setForeground(new java.awt.Color(75, 75, 75));

		cmd21.setText("19");

		cmd21.setName("day");

		add(cmd21);

		cmd22.setBackground(new java.awt.Color(255, 255, 255));

		cmd22.setForeground(new java.awt.Color(75, 75, 75));

		cmd22.setText("20");

		cmd22.setName("day");

		add(cmd22);

		cmd23.setBackground(new java.awt.Color(255, 255, 255));

		cmd23.setForeground(new java.awt.Color(75, 75, 75));

		cmd23.setText("21");

		cmd23.setName("day");

		add(cmd23);

		cmd24.setBackground(new java.awt.Color(255, 255, 255));

		cmd24.setForeground(new java.awt.Color(75, 75, 75));

		cmd24.setText("22");

		cmd24.setName("day");

		add(cmd24);

		cmd25.setBackground(new java.awt.Color(255, 255, 255));

		cmd25.setForeground(new java.awt.Color(75, 75, 75));

		cmd25.setText("23");

		cmd25.setName("day");

		add(cmd25);

		cmd26.setBackground(new java.awt.Color(255, 255, 255));

		cmd26.setForeground(new java.awt.Color(75, 75, 75));

		cmd26.setText("24");

		cmd26.setName("day");

		add(cmd26);

		cmd27.setBackground(new java.awt.Color(255, 255, 255));

		cmd27.setForeground(new java.awt.Color(75, 75, 75));

		cmd27.setText("25");

		cmd27.setName("day");

		add(cmd27);

		cmd28.setBackground(new java.awt.Color(255, 255, 255));

		cmd28.setForeground(new java.awt.Color(75, 75, 75));

		cmd28.setText("26");

		cmd28.setName("day");

		add(cmd28);

		cmd29.setBackground(new java.awt.Color(255, 255, 255));

		cmd29.setForeground(new java.awt.Color(75, 75, 75));

		cmd29.setText("27");

		cmd29.setName("day");

		add(cmd29);

		cmd30.setBackground(new java.awt.Color(255, 255, 255));

		cmd30.setForeground(new java.awt.Color(75, 75, 75));

		cmd30.setText("28");

		cmd30.setName("day");

		add(cmd30);

		cmd31.setBackground(new java.awt.Color(255, 255, 255));

		cmd31.setForeground(new java.awt.Color(75, 75, 75));

		cmd31.setText("29");

		cmd31.setName("day");

		add(cmd31);

		cmd32.setBackground(new java.awt.Color(255, 255, 255));

		cmd32.setForeground(new java.awt.Color(75, 75, 75));

		cmd32.setText("30");

		cmd32.setName("day");

		add(cmd32);

		cmd33.setBackground(new java.awt.Color(255, 255, 255));

		cmd33.setForeground(new java.awt.Color(75, 75, 75));

		cmd33.setText("31");

		cmd33.setName("day");

		add(cmd33);

		cmd34.setBackground(new java.awt.Color(255, 255, 255));

		cmd34.setForeground(new java.awt.Color(75, 75, 75));

		cmd34.setName("day");

		add(cmd34);

		cmd35.setBackground(new java.awt.Color(255, 255, 255));

		cmd35.setForeground(new java.awt.Color(75, 75, 75));

		cmd35.setName("day");

		add(cmd35);

		cmd36.setBackground(new java.awt.Color(255, 255, 255));

		cmd36.setForeground(new java.awt.Color(75, 75, 75));

		cmd36.setName("day");

		add(cmd36);

		cmd37.setBackground(new java.awt.Color(255, 255, 255));

		cmd37.setForeground(new java.awt.Color(75, 75, 75));

		cmd37.setName("day");

		add(cmd37);

		cmd38.setBackground(new java.awt.Color(255, 255, 255));

		cmd38.setForeground(new java.awt.Color(75, 75, 75));

		cmd38.setName("day");

		add(cmd38);

		cmd39.setBackground(new java.awt.Color(255, 255, 255));

		cmd39.setForeground(new java.awt.Color(75, 75, 75));

		cmd39.setName("day");

		add(cmd39);

		cmd40.setBackground(new java.awt.Color(255, 255, 255));

		cmd40.setForeground(new java.awt.Color(75, 75, 75));

		cmd40.setName("day");

		add(cmd40);

		cmd41.setBackground(new java.awt.Color(255, 255, 255));

		cmd41.setForeground(new java.awt.Color(75, 75, 75));

		cmd41.setName("day");

		add(cmd41);

		cmd42.setBackground(new java.awt.Color(255, 255, 255));

		cmd42.setForeground(new java.awt.Color(75, 75, 75));

		cmd42.setName("day");

		add(cmd42);

	}

	public Event getEvent() {

		return event;

	}

	public void setEvent(Event event) {

		this.event = event;

		addEvent();

	}

	public void next() {

		if (selectDay == max_of_month) {

			selectDay = 0;

		}

		JButton cmd = (JButton) getComponent(startDate - 1 + selectDay + 1);

		String n = cmd.getText();

		if (!n.equals("") && Integer.valueOf(n) <= max_of_month) {

			selectDay++;

			event.execute(null, selectDay);

			cmd.setBackground(new Color(206, 110, 245));

		}

	}

	public void back() {

		if (selectDay <= 1) {

			selectDay = max_of_month + 1;

		}

		JButton cmd = (JButton) getComponent(startDate - 1 + selectDay - 1);

		String n = cmd.getText();

		if (!n.equals("") && cmd.getName() != null) {

			selectDay--;

			event.execute(null, selectDay);

			cmd.setBackground(new Color(206, 110, 245));

		}

	}

	public void up() {

		JButton cmd = (JButton) getComponent(startDate - 1 + selectDay - 7);

		String n = cmd.getText();

		if (!n.equals("") && cmd.getName() != null) {

			selectDay -= 7;

			event.execute(null, selectDay);

			cmd.setBackground(new Color(206, 110, 245));

		}

	}

	public void down() {

		if (getComponents().length > startDate - 1 + selectDay + 7) {

			JButton cmd = (JButton) getComponent(startDate - 1 + selectDay + 7);

			String n = cmd.getText();

			if (!n.equals("") && cmd.getName() != null) {

				selectDay += 7;

				event.execute(null, selectDay);

				cmd.setBackground(new Color(206, 110, 245));

			}

		}

	}

}
