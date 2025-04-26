package com.buttons.round;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

import com.effects.ElevationEffectCircle;
import com.effects.RippleEffect;
import com.material.utils.Alineacion;

@SuppressWarnings("serial")
public class RippleButton extends JButton {

	private RippleEffect ripple;

	private ElevationEffectCircle elevation;

	private Color rippleColor;

	private Cursor cursor;

	private float angle;

	private Image icon;

	private Alineacion iconAlineacion;

	private Alineacion textAlineacion;

	private Color fg;

	private int textWidth;

	private int textHeight;

	private int textX;

	private int textY;

	private FontMetrics metrics;

	private int iconWidth;

	private int iconHeight;

	private int iconX;

	private int iconY;

	private Color bg;

	private Graphics2D g2;

	public RippleButton(String text) {

		this();

		setText(text);

	}

	public RippleButton() {

		setPreferredSize(new Dimension(100, 100));

		setBackground(new Color(0, 112, 192));

		angle = 25f;

		setForeground(Color.WHITE);

		rippleColor = Color.WHITE;

		cursor = getCursor();

		setBorderPainted(false);

		setFocusPainted(false);

		ripple = RippleEffect.applyTo(this);

		elevation = ElevationEffectCircle.applyTo(this, 0);

		setOpaque(false);

		setUI(new BasicButtonUI() {

			public boolean contains(JComponent c, int x, int y) {

				return (x > 5 && y > 5 && x < getWidth() - 5 && y < getHeight() - 5);

			}

		});

		setIconAlignment(Alineacion.LEFT);

		setTextAlignment(Alineacion.CENTER);

	}

	public Color getRippleColor() {

		return this.rippleColor;

	}

	public void setRippleColor(Color rippleColor) {

		this.rippleColor = rippleColor;

	}

	public void setEnabled(boolean b) {

		super.setEnabled(b);

		this.elevation.setLevel(0);

		super.setCursor(b ? this.cursor : Cursor.getPredefinedCursor(0));

	}

	public void setCursor(Cursor cursor) {

		super.setCursor(cursor);

		this.cursor = cursor;

	}

	public void setIcon(ImageIcon icon) {

		this.icon = icon.getImage();

		repaint();

	}

	public void setIconAlignment(Alineacion alignment) {

		this.iconAlineacion = alignment;

		repaint();

	}

	public void setTextAlignment(Alineacion alignment) {

		this.textAlineacion = alignment;

		repaint();

	}

	protected void processFocusEvent(FocusEvent focusEvent) {

		super.processFocusEvent(focusEvent);

		this.elevation.setLevel(0);

	}

	protected void processMouseEvent(MouseEvent mouseEvent) {

		super.processMouseEvent(mouseEvent);

		this.elevation.setLevel(0);

	}

	private int calculateAlignedX(int contentWidth, Alineacion alignment) {

		switch (alignment) {

		case LEFT:

			return 10;

		case CENTER:

			return (getWidth() - contentWidth) / 2;

		case RIGHT:

			return getWidth() - contentWidth - 10; // Espacio de margen

		default:

			return 0;

		}

	}

	protected void paintComponent(Graphics g) {

		g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		if (isEnabled()) {

			elevation.paint(g);

		}

		if (isEnabled()) {

			g2.setColor(getBackground());

			g2.fill(new RoundRectangle2D.Float(0.0F, 0.0F, (getWidth()), (getHeight()), angle, angle));

			g2.setColor(new Color(this.rippleColor.getRed() / 255.0F, this.rippleColor.getGreen() / 255.0F,
					rippleColor.getBlue() / 255.0F, 0.12F));

			if (isFocusOwner()) {

				g2.fill(new RoundRectangle2D.Float(0.0F, 0.0F, (getWidth()), (getHeight()), angle, angle));

			}

		}

		else {

			bg = getBackground();

			g2.setColor(new Color(bg.getRed() / 255.0F, bg.getGreen() / 255.0F, bg.getBlue() / 255.0F, 0.6F));

			g2.fill(new RoundRectangle2D.Float(0.0F, 0.0F, (getWidth()), (getHeight()), angle, angle));

		}

		if (icon != null) {

			iconWidth = icon.getWidth(null);

			iconHeight = icon.getHeight(null);

			iconX = calculateAlignedX(iconWidth, iconAlineacion);

			iconY = (getHeight() - iconHeight) / 2;

			g2.drawImage(icon, iconX, iconY, null);

		}

		metrics = g.getFontMetrics(getFont());

		textWidth = metrics.stringWidth(getText().toUpperCase());

		textHeight = metrics.getHeight();

		textX = calculateAlignedX(textWidth, textAlineacion);

		textY = (getHeight() - textHeight) / 2 + metrics.getAscent();

		g2.setFont(getFont());

		if (isEnabled()) {

			g2.setColor(getForeground());

		}

		else {

			fg = getForeground();

			g2.setColor(new Color(fg.getRed() / 255.0F, fg.getGreen() / 255.0F, fg.getBlue() / 255.0F, 0.6F));

		}

		g2.drawString(getText().toUpperCase(), textX, textY);

		if (isEnabled()) {

			g2.setClip(new RoundRectangle2D.Float(0.0F, 0.0F, (getWidth()), (getHeight()), angle, angle));

			g2.setColor(this.rippleColor);

			ripple.paint(g2);

		}

	}

}
