package com.slider.tipo1;

import java.awt.Color;

import javax.swing.JSlider;

@SuppressWarnings("serial")
public class JSliderCustom2 extends JSlider {

	public JSliderCustom2() {
		setOpaque(false);
		setBackground(new Color(180, 180, 180));
		setForeground(new Color(69, 124, 235));
		setUI(new JSliderUI2(this));
	}
}
