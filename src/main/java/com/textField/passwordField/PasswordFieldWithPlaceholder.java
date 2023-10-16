package com.textField.passwordField;

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
import javax.swing.JPasswordField;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;

import com.contextmenu.DefaultContextMenu;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class PasswordFieldWithPlaceholder extends JPasswordField {

	protected float anchoDeBorde;

	protected Color colorDeBorde;

	private String descripcion;

	private Color colorDeTextoBackground;

	private Integer angle;

	private Image image;

	private boolean round;

	private Icon icon;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private boolean left;

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

	public void setRound(boolean round) {

		this.round = round;

		repaint();

	}

	public PasswordFieldWithPlaceholder() {

		anchoDeBorde = 2f;

		colorDeBorde = new Color(173, 173, 173);

		descripcion = "Ingrese aquí el texto";

		colorDeTextoBackground = Color.GRAY;

		angle = 20;

		image = null;

		round = true;

		setOpaque(false);

		setBorder(new EmptyBorder(0, 5, 0, 2));

		setPreferredSize(new Dimension(69, 20));

		setFont(new Font("Dialog", Font.PLAIN, 20));

		setForeground(Color.BLACK);

		DefaultContextMenu.addDefaultContextMenu(this);

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		Paint oldPaint = g2.getPaint();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Shape r2d;

		if (round) {

			r2d = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), angle, angle);

		}

		else {

			r2d = new Rectangle2D.Double(0, 0, getWidth(), getHeight());

		}

		g2.clip(r2d);

		g2.setPaint(new GradientPaint(0.0f, 0.0f, getBackground(), 0.0f, getHeight(), getBackground()));

		if (round) {

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

			if (getText() == null)
				setText("  ");

			TextLayout layout = new TextLayout(descripcion == null ? " " : descripcion, getFont(),
					g2.getFontRenderContext());

			Rectangle2D bounds = layout.getBounds();

			int x = (int) (getWidth() - insets.left - insets.right - bounds.getWidth()) / 2;

			x = 0 + insets.left;

			int y = (getHeight() - insets.top - insets.bottom - fm.getMaxAscent() - fm.getMaxDescent()) / 2;

			y += fm.getAscent() - 1;

			g2.setColor(colorDeTextoBackground);

			layout.draw(g2, x, y);

		}

		g2.setPaint(new GradientPaint(0.0f, 0.0f, Color.BLACK, 0.0f, getHeight(), Color.BLACK));

		g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, angle, angle);

		g2.setPaint(oldPaint);

		super.paintComponent(g);

	}

	@Override
	protected void paintBorder(Graphics g) {

		int x = 1, y = 1;

		int w = getWidth() - 2, h = getHeight() - 2;

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setStroke(new BasicStroke(anchoDeBorde));

		g2.setColor(colorDeBorde);

		if (round) {

			g2.drawRoundRect(x, y, w, h, angle, angle);

		}

		else {

			g2.drawRect(0, 0, getWidth(), getHeight());

		}

		g2.dispose();

	}

	public Color getColorDeTextoBackground() {

		return colorDeTextoBackground;

	}

	public void setColorDeTextoBackground(Color colorDeTextoBackground) {

		this.colorDeTextoBackground = colorDeTextoBackground;

	}

	public Boolean getLeft() {

		return left;

	}

	public void setLeft(Boolean left) {

		this.left = left;

		repaint();

	}

	public float getAnchoDeBorde() {

		return anchoDeBorde;

	}

	public void setAnchoDeBorde(float anchoDeBorde) {

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

}
