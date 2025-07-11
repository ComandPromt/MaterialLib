package com.textField.text;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;

import com.contextmenu.DefaultContextMenu;
import com.toolTip.ToolTipLlamada;

import mthos.JMthos;

@SuppressWarnings("serial")
public class TextFieldWithGradient extends JTextField {

	private boolean left;

	protected int anchoDeBorde;

	protected Color colorDeBorde;

	private String descripcion;

	private Color colorDeTextoBackground;

	private int angle;

	private Image image;

	private Icon icon;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private Color firstGradient;

	private Color secondGradient;

	private Font placeholderFont;

	public void setPlaceholderFont(Font placeholderFont) {

		this.placeholderFont = placeholderFont;

		repaint();

	}

	public enum PlaceholderVerticalPosition {

		TOP, CENTER, BOTTOM

	}

	public enum PlaceholderHorizontalPosition {

		LEFT, CENTER, RIGHT

	}

	private PlaceholderVerticalPosition placeholderVerticalPosition = PlaceholderVerticalPosition.CENTER;

	private PlaceholderHorizontalPosition placeholderHorizontalPosition = PlaceholderHorizontalPosition.LEFT;

	public void setAngle(int angle) {

		this.angle = angle;

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

			}

			catch (Exception e) {

				font = new Font("Dialog", Font.PLAIN, 20);

			}

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

		if (text == null || fondo == null || colorTexto == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, colorTexto, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public Icon getIcon() {

		return icon;

	}

	public void setIcon(Icon icon) {

		this.icon = icon;

		setImage(((ImageIcon) icon).getImage());

	}

	public Image getImage() {

		return image;

	}

	private void setImage(Image image) {

		this.image = image;

	}

	public TextFieldWithGradient(String text) {

		this();

		setText(text);

	}

	public void setFirstGradient(Color firstGradient) {

		this.firstGradient = firstGradient;

	}

	public void setSecondGradient(Color secondGradient) {

		this.secondGradient = secondGradient;

	}

	public TextFieldWithGradient() {

		firstGradient = Color.BLUE;

		secondGradient = Color.GREEN;

		left = false;

		anchoDeBorde = 2;

		colorDeBorde = new Color(173, 173, 173);

		descripcion = "Ingrese aquí el texto";

		colorDeTextoBackground = Color.GRAY;

		angle = 50;

		image = null;

		placeholderFont = new Font(getFont().getName(), Font.PLAIN, 25);

		setOpaque(false);

		setBorder(new EmptyBorder(0, 5, 0, 2));

		setPreferredSize(new Dimension(69, 20));

		setFont(new Font("Dialog", Font.PLAIN, 30));

		DefaultContextMenu.addDefaultContextMenu(this);

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		Paint oldPaint = g2.getPaint();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Shape r2d;

		if (angle > 0) {

			r2d = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), angle, angle);

		}

		else {

			r2d = new Rectangle2D.Double(0, 0, getWidth(), getHeight());

		}

		g2.clip(r2d);

		g2.setPaint(new GradientPaint(0.0f, 0.0f, getBackground(), 0.0f, getHeight(), getBackground()));

		if (angle > 0) {

			g2.fillRoundRect(0, 0, getWidth(), getHeight(), angle, angle);

		}

		else {

			g2.fillRect(0, 0, getWidth(), getHeight());

		}

		if (getImage() != null) {

			if (left) {

				g2.drawImage(image, 2, 2, getHeight() - 3, getHeight() - 3, null);

				setBorder(new EmptyBorder(2, (getHeight()), 2, 2));

			}

			else {

				g2.drawImage(image, getWidth() - getHeight(), 2, getHeight() - 3, getHeight() - 3, null);

				setBorder(new EmptyBorder(2, 5, 2, (getHeight())));

			}

		}

		if (getText().length() == 0) {

			Insets insets = getInsets();

			FontMetrics fm = getFontMetrics(getFont());

			g2.setColor(getForeground());

			if (getText() == null) {

				setText("  ");

			}

			String text = getText(); // o el texto del placeholder

			if (text != null && !text.trim().isEmpty()) {
				TextLayout layout = new TextLayout(text, placeholderFont, g2.getFontRenderContext());

				Rectangle2D bounds = layout.getBounds();
				int x;

				switch (placeholderHorizontalPosition) {

				case LEFT:

					x = anchoDeBorde + insets.left;

					break;

				case CENTER:

					x = (getWidth() - insets.left - insets.right - (int) bounds.getWidth()) / 2;

					break;

				case RIGHT:

					x = getWidth() - insets.right - (int) bounds.getWidth() - anchoDeBorde;

					break;

				default:

					x = anchoDeBorde + insets.left;

				}

				int y;

				switch (placeholderVerticalPosition) {

				case TOP:

					y = fm.getAscent() + insets.top;

					break;

				case CENTER:

					y = (getHeight() - insets.top - insets.bottom - fm.getMaxAscent() - fm.getMaxDescent()) / 2
							+ fm.getAscent() - 1;

					break;

				case BOTTOM:

					y = getHeight() - insets.bottom - fm.getDescent();

					break;

				default:

					y = (getHeight() - insets.top - insets.bottom - fm.getMaxAscent() - fm.getMaxDescent()) / 2
							+ fm.getAscent() - 1;

				}

				if (placeholderHorizontalPosition == PlaceholderHorizontalPosition.LEFT
						&& placeholderVerticalPosition != PlaceholderVerticalPosition.CENTER) {

					if (angle > 29) {

						x += JMthos.calcularSucesionAritmeticaAInt("30#0,40#5", angle);

					}

					y -= anchoDeBorde;

					y -= 7;

				}

				else if (placeholderHorizontalPosition == PlaceholderHorizontalPosition.RIGHT
						&& placeholderVerticalPosition != PlaceholderVerticalPosition.CENTER) {

					x -= JMthos.calcularSucesionAritmeticaAInt("0#0,10#5", angle);

					y -= anchoDeBorde;

					y -= 7;

				}

				g2.setColor(colorDeTextoBackground);

				layout.draw(g2, x, y);
			}

		}

		int ancho = anchoDeBorde;

		g2.setStroke(new BasicStroke((ancho * 2) + 3));

		g2.setPaint(new GradientPaint(0f, 0.0f, firstGradient, 0.0f, getHeight(), secondGradient));

		g2.drawRoundRect(0, 0, getWidth(), getHeight(), angle, angle);

		g2.setPaint(oldPaint);

		super.paintComponent(g);

	}

	@Override
	protected void paintBorder(Graphics g) {

		int w = getWidth() - 2, h = getHeight() - 2;

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setStroke(new BasicStroke(anchoDeBorde));

		g2.setColor(colorDeBorde);

		if (angle > 0) {

			int ancho = anchoDeBorde;

			switch (ancho) {

			case 1:

				g2.drawRoundRect(2, ancho + 2, (w - ancho) - 1, (h - (ancho * 2)) - 1, angle, angle);

				break;

			case 2:

			case 3:

				g2.drawRoundRect((ancho) + 1, ancho + 1, (w - ancho) - 2, (h - (ancho * 2)), angle, angle);

				break;

			case 4:

				g2.drawRoundRect((ancho) + 1, ancho + 2, (w - ancho) - 4, (h - (ancho * 2)), angle, angle);

				break;

			default:

				g2.drawRoundRect((ancho) + 2, ancho + 1, ((w - ancho) - ancho) - 2, (h - (ancho * 2)), angle, angle);

				break;

			}

		}

		else {

			g2.drawRect(0, 0, getWidth(), getHeight());

		}

		g2.dispose();

	}

	public Color getPlaceholderColor() {

		return colorDeTextoBackground;

	}

	public void setPlaceholderColor(Color colorDeTextoBackground) {

		this.colorDeTextoBackground = colorDeTextoBackground;

	}

	public Boolean getLeftIcon() {

		return left;

	}

	public void setLeftIcon(Boolean left) {

		this.left = left;

		repaint();

	}

	public float getAnchoDeBorde() {

		return anchoDeBorde;

	}

	public void setAnchoDeBorde(int anchoDeBorde) {

		this.anchoDeBorde = anchoDeBorde;

	}

	public Color getColorDeBorde() {

		return colorDeBorde;

	}

	public void setColorDeBorde(Color colorDeBorde) {

		this.colorDeBorde = colorDeBorde;

	}

	public String getDescripcion() {

		return descripcion;

	}

	public void setDescripcion(String descripcion) {

		this.descripcion = descripcion;

	}

	public void setPlaceholderPosition(PlaceholderVerticalPosition position) {

		this.placeholderVerticalPosition = position;

		repaint();

	}

	public void setPlaceholderHorizontalPosition(PlaceholderHorizontalPosition position) {

		this.placeholderHorizontalPosition = position;

		repaint();

	}

}
