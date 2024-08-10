package com.jmenu.simple;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class RSPopupMenu extends JPopupMenu {

	private Color colorBackgroud = new Color(255, 255, 255);

	private Color colorBorde = new Color(153, 153, 153);

	private int grosorBorde = 2;

	private Border borde = BorderFactory.createLineBorder(this.colorBorde, this.grosorBorde);

	private boolean mostrarBorde = true;

	public RSPopupMenu() {

		setBorder(this.borde);

	}

	public void paintComponent(Graphics g) {

		g.setColor(this.colorBackgroud);

		g.fillRect(0, 0, getWidth(), getHeight());

	}

	public Color getColorBackgroud() {

		return this.colorBackgroud;

	}

	public void setColorBackgroud(Color colorBackgroud) {

		this.colorBackgroud = colorBackgroud;

	}

	public Color getColorBorde() {

		return this.colorBorde;

	}

	public void setColorBorde(Color colorBorde) {

		this.colorBorde = colorBorde;

		setBorder(BorderFactory.createLineBorder(colorBorde, this.grosorBorde));

	}

	public int getGrosorBorde() {

		return this.grosorBorde;

	}

	public void setGrosorBorde(int grosorBorde) {

		this.grosorBorde = grosorBorde;

		setBorder(BorderFactory.createLineBorder(this.colorBorde, grosorBorde));

	}

	public boolean isMostrarBorde() {

		return this.mostrarBorde;

	}

	public void setMostrarBorde(boolean mostrarBorde) {

		this.mostrarBorde = mostrarBorde;

		if (this.mostrarBorde) {

			setBorder(BorderFactory.createLineBorder(this.colorBorde, this.grosorBorde));

		}

		else {

			setBorder(null);

		}

	}

}
