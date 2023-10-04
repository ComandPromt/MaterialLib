package com.buttons.simple;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JToolTip;
import javax.swing.plaf.basic.BasicButtonUI;

import com.material.utils.ElevationEffect;
import com.material.utils.MaterialShadow;
import com.material.utils.RippleEffect;
import com.material.utils.Utils;
import com.materiallib.utils.MaterialColor;
import com.toolTip.ToolTipLlamada;

public class MaterialButton extends JButton {

	private RippleEffect ripple;

	private ElevationEffect elevation;

	private Type type;

	private boolean isMousePressed;

	private boolean isMouseOver;

	private Color rippleColor;

	private Cursor cursor;

	private int borderRadius;

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

				font = getFont().deriveFont(20f);

			}

			catch (Exception e) {

				font = new Font("Dialog", Font.PLAIN, 20);

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

	public MaterialButton() {

		type = Type.DEFAULT;

		borderRadius = 2;

		rippleColor = Color.WHITE;

		cursor = super.getCursor();

		type = Type.DEFAULT;

		isMousePressed = false;

		isMouseOver = false;

		rippleColor = Color.WHITE;

		cursor = super.getCursor();

		borderRadius = 2;

		setFont(getFont().deriveFont(Font.PLAIN, 20f));

		ripple = RippleEffect.applyTo(this);

		elevation = ElevationEffect.applyTo(this, 0);

		setOpaque(false);

		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent mouseEvent) {

				isMousePressed = true;

			}

			@Override
			public void mouseReleased(MouseEvent mouseEvent) {

				isMousePressed = false;

				repaint();

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				isMouseOver = true;

				repaint();

			}

			@Override
			public void mouseExited(MouseEvent e) {

				isMouseOver = false;

				repaint();

			}

		});

		setUI(new BasicButtonUI() {

			@Override
			public boolean contains(JComponent c, int x, int y) {
				return x > MaterialShadow.OFFSET_LEFT && y > MaterialShadow.OFFSET_TOP
						&& x < getWidth() - MaterialShadow.OFFSET_RIGHT
						&& y < getHeight() - MaterialShadow.OFFSET_BOTTOM;
			}

		});

	}

	public Type getType() {

		return type;

	}

	public void setType(Type type) {

		this.type = type;

		repaint();

	}

	@Override
	public void setBackground(Color bg) {

		super.setBackground(bg);

		setForeground(Utils.isDark(bg) ? MaterialColor.WHITE : MaterialColor.BLACK);

		setRippleColor(Utils.isDark(bg) ? MaterialColor.WHITE : Utils.darken(Utils.darken(bg)));

	}

	public Color getRippleColor() {

		return rippleColor;

	}

	public void setRippleColor(Color rippleColor) {

		this.rippleColor = rippleColor;

	}

	public int getBorderRadius() {

		return borderRadius;

	}

	public void setBorderRadius(int borderRadius) {

		this.borderRadius = borderRadius;

		elevation.setBorderRadius(borderRadius);

	}

	@Override
	public void setEnabled(boolean b) {

		super.setEnabled(b);

		elevation.setLevel(getElevation());

		super.setCursor(b ? cursor : Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

	}

	@Override
	public void setCursor(Cursor cursor) {

		super.setCursor(cursor);

		this.cursor = cursor;

	}

	@Override
	protected void processFocusEvent(FocusEvent focusEvent) {

		super.processFocusEvent(focusEvent);

		elevation.setLevel(getElevation());

	}

	@Override
	protected void processMouseEvent(MouseEvent mouseEvent) {

		super.processMouseEvent(mouseEvent);

		elevation.setLevel(getElevation());

	}

	private int getElevation() {

		if (isMousePressed) {
			return 2;

		}

		else if (type == Type.RAISED || isFocusOwner() || isMouseOver) {
			return 1;

		}

		else {

			return 0;

		}

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		if (type != Type.FLAT && isEnabled()) {

			elevation.paint(g);

		}

		g2.translate(MaterialShadow.OFFSET_LEFT, MaterialShadow.OFFSET_TOP);

		final int offset_lr = MaterialShadow.OFFSET_LEFT + MaterialShadow.OFFSET_RIGHT;

		final int offset_td = MaterialShadow.OFFSET_TOP + MaterialShadow.OFFSET_BOTTOM;

		if (isEnabled()) {

			g2.setColor(getBackground());

			g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - offset_lr, getHeight() - offset_td, borderRadius * 2,
					borderRadius * 2));

			g2.setColor(new Color(rippleColor.getRed() / 255f, rippleColor.getBlue() / 255f,
					rippleColor.getBlue() / 255f, 0.12f));

			if ((type == Type.FLAT && isMouseOver) || isFocusOwner()) {

				g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - offset_lr, getHeight() - offset_td,
						borderRadius * 2, borderRadius * 2));

			}

		}

		else {

			Color bg = getBackground();

			g2.setColor(new Color(bg.getRed() / 255f, bg.getGreen() / 255f, bg.getBlue() / 255f, 0.6f));

			g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - offset_lr, getHeight() - offset_td, borderRadius * 2,
					borderRadius * 2));

		}

		FontMetrics metrics = g.getFontMetrics(getFont());

		int x = (getWidth() - offset_lr - metrics.stringWidth(getText().toUpperCase())) / 2;

		int y = (getHeight() - offset_td - metrics.getHeight()) / 2 + metrics.getAscent();

		g2.setFont(getFont());

		if (isEnabled()) {

			g2.setColor(getForeground());

		}

		else {

			Color fg = getForeground();

			g2.setColor(new Color(fg.getRed() / 255f, fg.getGreen() / 255f, fg.getBlue() / 255f, 0.6f));

		}

		g2.drawString(getText().toUpperCase(), x, y);

		if (isEnabled()) {

			g2.setClip(new RoundRectangle2D.Float(0, 0, getWidth() - offset_lr, getHeight() - offset_td,
					Math.max(borderRadius * 2 - 4, 0), Math.max(borderRadius * 2 - 4, 0)));

			g2.setColor(rippleColor);

			ripple.paint(g2);

		}

	}

	public enum Type {

		DEFAULT,

		RAISED,

		FLAT

	}

}
