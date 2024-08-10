package com.textField.text;

import java.awt.BasicStroke;
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

	public enum HorizontalAlignment {
		LEFT, CENTER, RIGHT
	}

	public enum VerticalAlignment {
		TOP, CENTER, BOTTOM
	}

	private Icon prefixIcon;
	private Icon suffixIcon;
	private String placeholderText = "Placeholder";
	private Color placeholderColor = Color.GRAY;
	private HorizontalAlignment placeholderHAlignment = HorizontalAlignment.LEFT;
	private VerticalAlignment placeholderVAlignment = VerticalAlignment.CENTER;
	private String text;
	private Color fondo;
	private Color foreground;
	private Color border;
	private Font fuente;
	private Font placeholderFont;
	private int angle;

	private Color borderColor = new Color(142, 142, 142);
	private float borderWidth = 2.0f;

	public void setAngle(int angle) {

		this.angle = angle;

		repaint();

	}

	public void setPlaceholderFont(Font placeholderFont) {

		this.placeholderFont = placeholderFont;

		repaint();

	}

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
			} catch (Exception e) {
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
		} else {
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

	public TextFieldIcons(String text) {
		this();
		setText(text);
	}

	public TextFieldIcons() {
		angle = 50;
		setFont(getFont().deriveFont(30f));
		placeholderFont = getFont();
		setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
		DefaultContextMenu.addDefaultContextMenu(this);
		// Update placeholder visibility when the text changes
		addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				updatePlaceholderVisibility();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintIcon(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

		if (isFocusOwner()) {
			g2.setColor(new Color(4, 88, 167));
		} else {
			g2.setColor(borderColor);
		}

		g2.setStroke(new BasicStroke(borderWidth));
		g2.drawRoundRect((int) borderWidth / 2, (int) borderWidth / 2, getWidth() - ((int) borderWidth),
				getHeight() - ((int) borderWidth), angle, angle);

		if (getText().isEmpty()) {
			g2.setColor(placeholderColor);
			g2.setFont(placeholderFont);

			int padding = (prefixIcon != null) ? prefixIcon.getIconWidth() + 10 : 5;
			int textWidth = g2.getFontMetrics().stringWidth(placeholderText);
			int textHeight = g2.getFontMetrics().getAscent();

			int x = 0;
			int y = 0;

			switch (placeholderHAlignment) {
			case LEFT:
				x = padding + (int) borderWidth / 2;
				break;
			case CENTER:
				x = (getWidth() - textWidth) / 2;
				break;
			case RIGHT:
				x = getWidth() - textWidth - 5;
				x -= (int) borderWidth;
				break;
			}

			switch (placeholderVAlignment) {
			case TOP:
				y = textHeight + 5;
				break;
			case CENTER:
				y = (getHeight() + textHeight) / 2 - 2;
				break;
			case BOTTOM:
				y = getHeight() - 5;
				break;
			}
			y -= placeholderFont.getSize() / 7;
			g2.drawString(placeholderText, x, y);
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

	public void setPlaceholder(String text) {
		this.placeholderText = text;
		repaint();
	}

	public void setPlaceholderColor(Color color) {
		this.placeholderColor = color;
		repaint();
	}

	public void setPlaceholderHorizontalAlignment(HorizontalAlignment alignment) {
		this.placeholderHAlignment = alignment;
		repaint();
	}

	public void setPlaceholderVerticalAlignment(VerticalAlignment alignment) {
		this.placeholderVAlignment = alignment;
		repaint();
	}

	public void setBorderColor(Color color) {
		this.borderColor = color;
		repaint();
	}

	public void setBorderWidth(float width) {
		this.borderWidth = width;
		repaint();
	}

	@Override
	public void setText(String text) {
		super.setText(text);
		updatePlaceholderVisibility();
	}

	@Override
	public void setFont(Font font) {
		super.setFont(font);
		repaint();
	}

	private void updatePlaceholderVisibility() {
		repaint();
	}
}
