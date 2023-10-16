package com.textField.text;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalTextFieldUI;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

class TextFieldSuggestionUI extends MetalTextFieldUI {

	private JTextField textfield;

	private Border border;

	private List<String> items;

	private int round;

	public List<String> getItems() {

		return items;

	}

	public void setItems(List<String> items) {

		this.items = items;

	}

	public int getRound() {

		return round;

	}

	public void setRound(int round) {

		this.round = round;

		border.setRound(round);

		textfield.repaint();

	}

	public JTextField getTextfield() {

		return textfield;

	}

	public TextFieldSuggestionUI(JTextField textfield) {

		round = 20;

		items = new ArrayList<>();

		border = new Border(10);

		border.setRound(round);

		textfield.setBorder(border);

		textfield.setOpaque(false);

		textfield.setSelectedTextColor(Color.WHITE);

		textfield.setSelectionColor(new Color(54, 189, 248));

		textfield.addFocusListener(new FocusAdapter() {

			@Override

			public void focusGained(FocusEvent fe) {

				border.setColor(Color.RED);

				textfield.repaint();

			}

			@Override

			public void focusLost(FocusEvent fe) {

				border.setColor(Color.BLUE);

				textfield.repaint();

			}

		});

		this.textfield = textfield;

		AutoCompleteDecorator.decorate(textfield, items, false);

	}

	@Override
	protected void paintBackground(Graphics grphcs) {

		if (textfield.isOpaque()) {

			Graphics2D g2 = (Graphics2D) grphcs.create();

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g2.setColor(textfield.getBackground());

			g2.fillRoundRect(0, 0, textfield.getWidth(), textfield.getHeight(), round, round);

			g2.dispose();

		}

	}

	@SuppressWarnings("serial")
	private class Border extends EmptyBorder {

		private Color focusColor;

		private int round;

		private Color color;

		public void setRound(int round) {

			this.round = round;

		}

		public void setColor(Color color) {

			this.color = color;

		}

		public Border(int border) {

			super(border, border, border, border);

		}

		@SuppressWarnings("unused")
		public Border() {

			this(5);

		}

		@Override
		public void paintBorder(Component cmpnt, Graphics grphcs, int x, int y, int width, int height) {

			Graphics2D g2 = (Graphics2D) grphcs.create();

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			if (cmpnt.isFocusOwner()) {

				g2.setColor(focusColor);

			}

			else {

				g2.setColor(color);

			}

			g2.drawRoundRect(x, y, width - 1, height - 1, round, round);

			g2.dispose();

		}

	}

}
