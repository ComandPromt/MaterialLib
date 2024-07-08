package com.search;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RoundedSearchFieldWithButton extends JPanel {

	private JPanel panel2;

	private int angle;

	private BootSearchInputWithButton test;

	private SimpleSearchInputWithButton cero;

	public void setForeground(Color color) {

		try {

			if (angle == 0) {

				cero.setForeground(color);

			}

			else {

				test.setForeground(color);

			}

		}

		catch (Exception e) {

		}

	}

	public void setFondo(Color color) {

		try {

			panel2.setBackground(color);

		}

		catch (Exception e) {

		}

	}

	public void setBackground(Color color) {

		try {

			if (angle == 0) {

				cero.setBackground(color);

			}

			else {

				test.setBackground(color);

			}

		} catch (Exception e) {

		}

	}

	public void setButtonFont(Font font) {

		try {

			if (angle == 0) {

				cero.setButtonFont(font);

			}

			else {

				test.setButtonFont(font);

			}

		}

		catch (Exception e) {

		}

	}

	public void setFont(Font font) {

		try {

			if (angle == 0) {

				cero.setFont(font);

			}

			else {

				test.setFont(font);

			}

		}

		catch (Exception e) {

		}

	}

	public void setThickness(int thick) {

		try {

			if (angle == 0) {

				cero.setThickness(thick);

			}

			else {

				test.setThickness(thick);

			}

		}

		catch (Exception e) {

		}

	}

	public void setMagnifyingGlassBackgroundColor(Color color) {

		try {

			if (angle == 0) {

				cero.setMagnifyingGlassBackgroundColor(color);

			}

			else {

				test.setMagnifyingGlassBackgroundColor(color);

			}

		}

		catch (Exception e) {

		}

	}

	public void setMagnifyingGlassColor(Color color) {

		try {

			if (angle == 0) {

				cero.setMagnifyingGlassColor(color);

			}

			else {

				test.setMagnifyingGlassColor(color);

			}

		}

		catch (Exception e) {

		}

	}

	public RoundedSearchFieldWithButton(int angle, String text, String button) {

		setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout());

		this.angle = angle;

		if (angle == 0) {

			cero = new SimpleSearchInputWithButton(text, button);

			panel2 = cero;

		}

		else {

			panel2 = new JPanel();

			panel2.setLayout(new GridLayout());

			test = new BootSearchInputWithButton(text, button);

			test.setAngle(angle);

			panel2.add(test);

		}

		panel.add(panel2);

		add(panel);

		setFondo(Color.WHITE);

	}

	public void setButtonBackground(Color color) {

		try {

			if (angle == 0) {

				cero.getButton().setBackground(color);

			}

			else {

				test.setButtonBackground(color);

			}

		}

		catch (Exception e) {

		}

	}

	public void setBackColor(Color color) {

		try {

			if (angle == 0) {

				cero.setBackColor(color);

			}

			else {

				test.setBackColor(color);

			}

		}

		catch (Exception e) {

		}

	}

	public void setIconBackground(Color color) {

		try {

			if (angle == 0) {

				cero.setIconBackground(color);

			}

			else {

				test.setIconBackground(color);

			}

		}

		catch (Exception e) {

		}

	}

}
