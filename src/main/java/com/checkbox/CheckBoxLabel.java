package com.checkbox;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class CheckBoxLabel extends JPanel {

	private CheckBoxCustom checkBox;

	private JLabel label;

	private String text;

	private Color background;

	private Color foreground;

	private Color border;

	private Font fuente;

	@Override
	public void setToolTipText(String text) {

		setToolTip(text, null, null, null, null);

	}

	public void setToolTip(String text, Color background, Color foreground, Color border, Font font) {

		calcularTooltip(text, background, foreground, border, font);

	}

	private void calcularTooltip(String text, Color background, Color foreground, Color border, Font font) {

		if (background == null) {

			background = new Color(32, 39, 55);

		}

		if (foreground == null) {

			foreground = Color.WHITE;

		}

		if (border == null) {

			border = new Color(173, 173, 173);

		}

		if (font == null) {

			try {

				font = getFont().deriveFont(14f);

			}

			catch (Exception e) {

				font = new Font("Dialog", Font.PLAIN, 14);

			}

		}

		this.text = text;

		this.background = background;

		this.foreground = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || background == null || foreground == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, background, foreground, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	@Override
	public void setFont(Font font) {

		try {

			label.setFont(font);

		}

		catch (Exception e) {

		}

	}

	public void setIcon(Icon icon) {

		try {

			label.setIcon(icon);

		}

		catch (Exception e) {

		}

	}

	public void setCheckBorder(int thickness) {

		try {

			checkBox.setBorder(thickness);

		}

		catch (Exception e) {

		}

	}

	public void setCheckColor(Color color) {

		try {

			checkBox.setCheckColor(color);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setBackground(Color bg) {

		try {

			checkBox.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public void setLabel(JLabel label) {

		this.label = label;

	}

	public JLabel getLabel() {

		return label;

	}

	public void setText(String text) {

		label.setText(text);

	}

	public boolean isSelected() {

		return this.checkBox.isSelected();

	}

	public CheckBoxLabel(Icon image) {

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setOpaque(false);

		this.checkBox = new CheckBoxCustom("");

		add(this.checkBox);

		label = new JLabel("");

		label.setIcon(image);

		label.setHorizontalAlignment(SwingConstants.LEFT);

		add(label);

	}

	public CheckBoxLabel(String text) {

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setOpaque(false);

		this.checkBox = new CheckBoxCustom();

		add(this.checkBox);

		label = new JLabel(text);

		label.setHorizontalAlignment(SwingConstants.LEFT);

		add(label);

	}

	public CheckBoxLabel(String text, Icon image) {

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setOpaque(false);

		this.checkBox = new CheckBoxCustom("");

		add(this.checkBox);

		label = new JLabel(text);

		label.setHorizontalAlignment(SwingConstants.LEFT);

		label.setIcon(image);

		add(label);

	}

}