package com.datechooser.material;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.datechooser.language.CalendarLanguage;

@SuppressWarnings("serial")

public class MaterialJDate extends JPanel {

	JTextField txt;

	DateChooser ch;

	CalendarLanguage language;

	public String getDate() {

		try {

			return txt.getText();

		}

		catch (Exception e) {

			return "";

		}

	}

	public void setFontCurrentDay(Font font) {

		try {

			ch.setFontCurrentDay(font);

		}

		catch (Exception e) {

		}

	}

	public void colorearTexto(Color fondo) {

		try {

			ch.colorearTexto(fondo);

		}

		catch (Exception e) {

		}

	}

	public void setFondo(Color fondo) {

		try {

			ch.setFondo(fondo);

		}

		catch (Exception e) {

		}

	}

	public MaterialJDate(CalendarLanguage language) {

		this.language = language;

		txt = new JTextField("");

		txt.setBackground(Color.WHITE);

		add(txt);

		ch = new DateChooser(this.language);

		ch.setTextField(txt);

		ch.toDay();

	}

	@Override
	public void setFont(Font font) {

		try {

			txt.setFont(font);

		}

		catch (Exception e) {

		}

	}

}
