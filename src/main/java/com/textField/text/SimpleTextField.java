package com.textField.text;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Stroke;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.contextmenu.DefaultContextMenu;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class SimpleTextField extends JTextField {

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private int angulo;

	private int grosor;

	private Color borderColor;

	private String placeholder;

	private Color placeholderColor;

	private PlaceholderPosition placeholderPosition;

	private PlaceholderPosition placeholderHorizontalPosition;

	private Font placeholderFont;

	private int margenIzquierdo;

	public void setPlaceholderFont(Font placeholderFont) {

		this.placeholderFont = placeholderFont;

		repaint();

	}

	public enum PlaceholderPosition {

		LEFT, TOP, CENTER, BOTTOM, RIGHT

	}

	public int getGrosor() {

		return grosor;

	}

	public void setGrosor(int grosor) {

		if (grosor < 1) {

			grosor = 0;

		}

		this.grosor = grosor;

		updateInsets();

		repaint();

	}

	private void updateInsets() {

		Insets insets = getInsets();

		this.margenIzquierdo = insets.left + grosor;

		repaint();

	}

	public void setAngulo(int angulo) {

		this.angulo = angulo;

		repaint();

	}

	@Override
	public void setToolTipText(String text) {

		setToolTip(text, null, null, null, null);

	}

	public void setToolTip(String text, Color background, Color foreground, Color border, Font font) {

		calcularTooltip(text, background, foreground, border, font);

	}

	public void setPlaceholderHorizontalPosition(PlaceholderPosition placeholderPosition) {

		this.placeholderHorizontalPosition = placeholderPosition;

		repaint();

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

		this.colorTexto = foreground;

		this.border = border;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || fondo == null || colorTexto == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, colorTexto, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public Color getBorderColor() {

		return borderColor;

	}

	public void setBorderColor(Color borderColor) {

		this.borderColor = borderColor;

		repaint();

	}

	public SimpleTextField(String text) {

		this();

		setText(text);

	}

	public SimpleTextField() {

		grosor = 1;

		borderColor = Color.BLACK;

		angulo = 50;

		setFont(getFont().deriveFont(30f));

		updateInsets();

		setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

		DefaultContextMenu.addDefaultContextMenu(this);

		placeholderColor = Color.GRAY;

		placeholderPosition = PlaceholderPosition.CENTER;

		placeholderHorizontalPosition = PlaceholderPosition.LEFT;

		placeholderFont = getFont();

		addFocusListener(new FocusListener() {

			@Override

			public void focusGained(FocusEvent e) {

				repaint();

			}

			@Override
			public void focusLost(FocusEvent e) {

				repaint();

			}

		});

		getDocument().addDocumentListener(new DocumentListener() {

			@Override

			public void insertUpdate(DocumentEvent e) {

				repaint();

			}

			@Override

			public void removeUpdate(DocumentEvent e) {

				repaint();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {

				repaint();

			}

		});

		addKeyListener(new KeyAdapter() {

			@Override

			public void keyReleased(KeyEvent e) {

				if (getText().isEmpty()) {

					mostrarPlaceholder();

				}

				else {

					repaint();

				}

			}

		});

	}

	public String getPlaceholder() {

		return placeholder;

	}

	public void setPlaceholder(String placeholder) {

		this.placeholder = placeholder;

		repaint();

	}

	public Color getPlaceholderColor() {

		return placeholderColor;

	}

	public void setPlaceholderColor(Color placeholderColor) {

		this.placeholderColor = placeholderColor;

		repaint();

	}

	public PlaceholderPosition getPlaceholderPosition() {

		return placeholderPosition;

	}

	public void setPlaceholderPosition(PlaceholderPosition placeholderPosition) {

		this.placeholderPosition = placeholderPosition;

		repaint();

	}

	private void mostrarPlaceholder() {

		if (placeholder != null && getText().isEmpty()) {

			Graphics g = getGraphics();

			if (g != null) {

				g.setColor(placeholderColor);

				g.setFont(placeholderFont.deriveFont(Font.ITALIC));

				int textYPosition = getInsets().top + g.getFontMetrics().getMaxAscent();

				switch (placeholderPosition) {

				case TOP:

					textYPosition = getInsets().top + g.getFontMetrics().getMaxAscent();

					break;

				case CENTER:

					textYPosition = (getHeight() - g.getFontMetrics().getHeight()) / 2 + g.getFontMetrics().getAscent();

					break;

				default:

					textYPosition = getHeight() - getInsets().bottom - g.getFontMetrics().getMaxDescent();

					break;

				}

				pintarPlaceholder(g, textYPosition);

			}

		}

	}

	private void pintarPlaceholder(Graphics g, int textYPosition) {

		g.setFont(placeholderFont);

		int insetX = margenIzquierdo;

		switch (placeholderHorizontalPosition) {

		case CENTER:

			g.drawString(placeholder, (getWidth() - g.getFontMetrics().stringWidth(placeholder)) / 2, textYPosition);

			break;

		case RIGHT:

			g.drawString(placeholder, getWidth() - insetX - g.getFontMetrics().stringWidth(placeholder), textYPosition);

			break;

		default:

			g.drawString(placeholder, insetX, textYPosition);

			break;

		}

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		if (placeholder != null && getText().isEmpty() && !isFocusOwner()) {

			g.setColor(placeholderColor);

			g.setFont(placeholderFont);

			int textYPosition = getInsets().top + g.getFontMetrics().getMaxAscent();

			switch (placeholderPosition) {

			case TOP:

				textYPosition = getInsets().top + g.getFontMetrics().getMaxAscent();

				break;

			case CENTER:

				textYPosition = (getHeight() - g.getFontMetrics().getHeight()) / 2 + g.getFontMetrics().getAscent();

				break;

			default:

				textYPosition = getHeight() - getInsets().bottom - g.getFontMetrics().getMaxDescent();

				break;

			}

			pintarPlaceholder(g, textYPosition);

		}

	}

	@Override
	protected void paintBorder(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		Stroke st = g2.getStroke();

		g2.setStroke(new BasicStroke(grosor));

		g2.setColor(borderColor);

		if (angulo > 0) {

			g2.drawRoundRect(grosor / 2, grosor / 2, getWidth() - grosor, getHeight() - grosor, angulo, angulo);

		}

		else {

			g2.drawRect(grosor / 2, grosor / 2, getWidth() - grosor, getHeight() - grosor);

		}

		g2.setStroke(st);

	}

}
