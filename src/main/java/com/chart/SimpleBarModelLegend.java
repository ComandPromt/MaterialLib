package com.chart;

import java.awt.Color;

class SimpleBarModelLegend {

	private String name;

	private Color color;

	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

	public Color getColor() {

		return color;

	}

	public void setColor(Color color) {

		this.color = color;

	}

	public SimpleBarModelLegend(String name, Color color) {

		this.name = name;

		this.color = color;

	}

	public SimpleBarModelLegend() {

	}

}
