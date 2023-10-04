package com.textField.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JToolTip;

import com.contextmenu.DefaultContextMenu;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class TextFieldIcons extends JTextField {

	private Icon prefixIcon;

	private Icon suffixIcon;

	private String text;

	private Color fondo;

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

				font = getFont().deriveFont(20f);

			}

			catch (Exception e) {

				font = new Font("Dialog", Font.PLAIN, 20);

			}

		}

		this.text = text;

		this.fondo = background;

		this.foreground = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || fondo == null || foreground == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, foreground, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public Icon getPrefixIcon() {

		return prefixIcon;

	}

	public void setPrefixIcon(Icon prefixIcon) {

		this.prefixIcon = prefixIcon;

		initBorder();

	}

	public Icon getSuffixIcon() {

		return suffixIcon;

	}

	public void setSuffixIcon(Icon suffixIcon) {

		this.suffixIcon = suffixIcon;

		initBorder();

	}

	public TextFieldIcons() {

		setFont(getFont().deriveFont(30f));

		setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

		DefaultContextMenu.addDefaultContextMenu(this);

	}

	@Override

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		paintIcon(g);

		if (isFocusOwner()) {

			g.setColor(new Color(4, 88, 167));

			g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

			g.drawRect(1, 1, getWidth() - 3, getHeight() - 3);

		}

		else {

			g.setColor(new Color(142, 142, 142));

			g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

			g.drawRect(1, 1, getWidth() - 3, getHeight() - 3);

		}

	}

	private void paintIcon(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		if (prefixIcon != null) {

			Image prefix = ((ImageIcon) prefixIcon).getImage();

			int y = (getHeight() - prefixIcon.getIconHeight()) / 2;

			g2.drawImage(prefix, 3, y, this);

		}

		if (suffixIcon != null) {

			Image suffix = ((ImageIcon) suffixIcon).getImage();

			int y = (getHeight() - suffixIcon.getIconHeight()) / 2;

			g2.drawImage(suffix, getWidth() - suffixIcon.getIconWidth() - 3, y, this);

		}

	}

	private void initBorder() {

		int left = 5;

		int right = 5;

		if (prefixIcon != null) {

			left = prefixIcon.getIconWidth() + 5;

		}

		if (suffixIcon != null) {

			right = suffixIcon.getIconWidth() + 5;

		}

		setBorder(javax.swing.BorderFactory.createEmptyBorder(5, left, 5, right));

	}

}
