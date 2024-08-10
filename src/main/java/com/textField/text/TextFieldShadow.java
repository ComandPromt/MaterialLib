package com.textField.text;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.contextmenu.DefaultContextMenu;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class TextFieldShadow extends JTextField {

	private int direccionDeSombra;

	private int distanciaDeSombra;

	private boolean vertical;

	private float borde;

	private Float opacidad;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private int angle;

	private String placeholder;

	private Color placeholderColor;

	private PlaceholderPosition placeholderPosition;

	private PlaceholderHorizontalPosition placeholderHorizontalPosition;

	private Font placeholderFont;

	public void setPlaceholderFont(Font placeholderFont) {

		this.placeholderFont = placeholderFont;

		repaint();

	}

	public void setAngle(int angle) {

		this.angle = angle;

		repaint();

	}

	public enum PlaceholderPosition {
		TOP, CENTER, BOTTOM
	}

	public enum PlaceholderHorizontalPosition {
		LEFT, CENTER, RIGHT
	}

	public TextFieldShadow(String text) {
		this();
		setText(text);
	}

	public TextFieldShadow() {
		angle = 50;
		this.direccionDeSombra = 60;
		this.distanciaDeSombra = 1;
		this.vertical = true;
		this.borde = 2.0F;
		this.opacidad = Float.valueOf(1.0F);

		setOpaque(false);
		setFont(new Font("Dialog", 0, 30));
		DefaultContextMenu.addDefaultContextMenu(this);

		placeholderColor = Color.GRAY; // Default placeholder color
		placeholderPosition = PlaceholderPosition.CENTER; // Default placeholder position
		placeholderHorizontalPosition = PlaceholderHorizontalPosition.CENTER; // Default horizontal placeholder position
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

	public void setPlaceholderPosition(PlaceholderPosition position) {
		this.placeholderPosition = position;
		repaint();
	}

	public PlaceholderHorizontalPosition getPlaceholderHorizontalPosition() {
		return placeholderHorizontalPosition;
	}

	public void setPlaceholderHorizontalPosition(PlaceholderHorizontalPosition position) {
		this.placeholderHorizontalPosition = position;
		repaint();
	}

	private void mostrarPlaceholder(Graphics g) {

		if (placeholder != null && getText().isEmpty()) {

			g.setColor(placeholderColor);

			g.setFont(placeholderFont);

			FontMetrics fm = g.getFontMetrics();

			int textWidth = fm.stringWidth(placeholder);

			int textHeight = fm.getHeight();

			int textYPosition;

			if (placeholderPosition == PlaceholderPosition.TOP) {

				textYPosition = getInsets().top + fm.getMaxAscent();

			}

			else if (placeholderPosition == PlaceholderPosition.CENTER) {

				textYPosition = (getHeight() - textHeight) / 2 + fm.getAscent();

			}

			else {

				textYPosition = getHeight() - getInsets().bottom - fm.getMaxDescent();

			}

			int textXPosition;

			if (placeholderHorizontalPosition == PlaceholderHorizontalPosition.LEFT) {

				textXPosition = getInsets().left;

			}

			else if (placeholderHorizontalPosition == PlaceholderHorizontalPosition.CENTER) {

				textXPosition = (getWidth() - textWidth) / 2;

			}

			else {

				textXPosition = getWidth() - getInsets().right - textWidth;

			}

			g.drawString(placeholder, textXPosition, textYPosition);

		}

	}

	@Override
	public void setToolTipText(String text) {

		setToolTip(text, (Color) null, (Color) null, (Color) null, (Font) null);

	}

	public void setToolTip(String text, Color background, Color foreground, Color border, Font font) {

		calcularTooltip(text, background, foreground, border, font);

	}

	private void calcularTooltip(String text, Color background, Color foreground, Color border, Font font) {

		if (background == null)
			background = new Color(32, 39, 55);

		if (foreground == null)
			foreground = Color.WHITE;

		if (border == null)
			border = new Color(173, 173, 173);

		if (font == null)

			try {

				font = getFont().deriveFont(20.0F);

			}

			catch (Exception e) {

				font = new Font("Dialog", 0, 20);

			}

		this.text = text;

		this.fondo = background;

		this.colorTexto = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override

	public JToolTip createToolTip() {

		if (this.text == null || this.fondo == null || this.colorTexto == null || this.border == null)
			return super.createToolTip();

		ToolTipLlamada tip = new ToolTipLlamada(this.text, this.fondo, this.colorTexto, this.border, this.fuente);

		tip.setComponent(this);

		return (JToolTip) tip;

	}

	@Override
	protected void paintBorder(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		Stroke st = g2.getStroke();

		g2.setStroke(new BasicStroke(this.borde));

		if (angle > 0) {

			g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, angle, angle);

		}

		else {

			g2.drawRect(0, 0, getWidth(), getHeight());

		}

		g2.setStroke(st);

	}

	@Override
	protected void paintComponent(Graphics g) {

		Shape rect;

		float x1 = 0.0F;

		float x2 = 0.0F;

		float y1 = 0.0F;

		float y2 = getHeight();

		if (!this.vertical) {

			x1 = 0.0F;

			y1 = 0.0F;

			x2 = getWidth();

			y2 = 0.0F;

		}

		Graphics2D g2 = (Graphics2D) g;

		Paint oldPaint = g2.getPaint();

		if (angle > 0) {

			rect = new RoundRectangle2D.Double(0.0D, 0.0D, (getWidth() - 1), (getHeight() - 1), angle, angle);

		}

		else {

			rect = new Rectangle2D.Double(0.0D, 0.0D, getWidth(), getHeight());

		}

		g2.clip(rect);

		g2.setPaint(new GradientPaint(x1, y1, getBackground(), x2, y2, getBackground().darker()));

		g2.fill(rect);

		g2.setPaint(oldPaint);

		super.paintComponent(g);

		if (getText().isEmpty() && placeholder != null) {

			mostrarPlaceholder(g);

		}

	}

	public Float getOpacidad() {

		return this.opacidad;

	}

	public void setOpacidad(Float opacidad) {

		if (opacidad.floatValue() < 0.0F) {

			opacidad = Float.valueOf(0.0F);

		}

		else if (opacidad.floatValue() > 1.0F) {

			opacidad = Float.valueOf(1.0F);

		}

		this.opacidad = opacidad;

	}

	public int getDireccionDeSombra() {

		return this.direccionDeSombra;

	}

	public void setDireccionDeSombra(int direccionDeSombra) {

		this.direccionDeSombra = direccionDeSombra;

		repaint();

	}

	public int getDistanciaDeSombra() {

		return this.distanciaDeSombra;

	}

	public void setDistanciaDeSombra(int distanciaDeSombra) {

		this.distanciaDeSombra = distanciaDeSombra;

		repaint();

	}

	public boolean isVertical() {

		return this.vertical;

	}

	public void setVertical(boolean vertical) {

		this.vertical = vertical;

		repaint();

	}

	public float getBorde() {

		return this.borde;

	}

	public void setBorde(float borde) {

		this.borde = borde;

	}

}
