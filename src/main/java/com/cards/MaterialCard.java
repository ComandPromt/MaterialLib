package com.cards;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import textarea.TextAreaScroll;

@SuppressWarnings("serial")
public class MaterialCard extends JPanel {

	JLabel panel1;

	TextAreaScroll panel2;

	public void setHeaderText(String text) {

		try {

			this.panel1.setText(text);

		}

		catch (Exception e) {

		}

	}

	public void setText(String text) {

		try {

			// panel2.setText(text);

		}

		catch (Exception e) {

		}

	}

	public void setHeaderFont(Font font) {

		try {

			this.panel1.setFont(font);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setFont(Font font) {

		try {

			this.panel2.setFont(font);

		}

		catch (Exception e) {

		}

	}

	public void setHeaderColor(Color color) {

		try {

			panel1.setBackground(color);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setBackground(Color bg) {

		try {

			panel2.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public void setHeaderForeground(Color fg) {

		try {

			panel1.setForeground(fg);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setForeground(Color fg) {

		try {

			panel2.setForeground(fg);

		}

		catch (Exception e) {

		}

	}

	public void setLabelText(String text) {

		try {

			panel2.setHeader(text);

		}

		catch (Exception e) {

		}

	}

	public MaterialCard() {

		panel1 = new JLabel("");

		panel2 = new TextAreaScroll();

		panel1.setOpaque(true);

		panel2.setOpaque(true);

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				panel1.setSize(getWidth(), (int) Math.round(getHeight() * 0.16));

				panel2.setSize(getWidth(), (int) Math.round(getHeight() * 0.85));

			}

		});

		setLayout(null);

		panel1.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.setBounds(0, 0, 451, 54);
		add(panel1);

		panel2.setBounds(0, 39, 451, 263);
		add(panel2);

		setHeaderColor(new Color(0, 204, 106));

		setHeaderForeground(Color.WHITE);

		setBackground(Color.WHITE);

	}
}
