package com.buttons.simple;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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

import com.effects.ElevationEffect;
import com.effects.RippleEffect;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class MaterialButton extends JButton {

	private RippleEffect ripple;

	private ElevationEffect elevation;

	private MaterialType type;

	private boolean isMousePressed;

	private boolean isMouseOver;

	private Color rippleColor;

	private Cursor cursor;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private int angle;

	private FontMetrics metrics;

	private int x;

	private int y;

	private Color bg;

	private Color fg;

	private Graphics2D g2;

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

	public MaterialButton(String text) {

		this();

		setText(text);

	}

	public MaterialButton() {

		setPreferredSize(new Dimension(200, 40));

		setBorderPainted(false);

		setSize(new Dimension(200, 40));

		ripple = RippleEffect.applyTo(this);

		elevation = ElevationEffect.applyTo(this, 0);

		rippleColor = Color.WHITE;

		cursor = getCursor();

		type = MaterialType.DEFAULT;

		rippleColor = Color.WHITE;

		cursor = getCursor();

		setOpaque(false);

		addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent mouseEvent) {

				isMousePressed = true;

			}

			public void mouseReleased(MouseEvent mouseEvent) {

				isMousePressed = false;

				repaint();

			}

			public void mouseEntered(MouseEvent e) {

				isMouseOver = true;

				repaint();

			}

			public void mouseExited(MouseEvent e) {

				isMouseOver = false;

				repaint();

			}

		});

		setUI(new BasicButtonUI() {

			public boolean contains(JComponent c, int x, int y) {

				return (x > 0 && y > 0 && x < getWidth() - 0 && y < getHeight() - 0);

			}

		});

		setBackground(Color.LIGHT_GRAY);

		setRippleColor(Color.PINK);

		setFont(getFont().deriveFont(20f));

	}

	public MaterialType getType() {

		return this.type;

	}

	public void setType(MaterialType type) {

		this.type = type;

		repaint();

	}

	public Color getRippleColor() {

		return this.rippleColor;

	}

	public void setRippleColor(Color rippleColor) {

		this.rippleColor = rippleColor;

	}

	public void setEnabled(boolean b) {

		super.setEnabled(b);

		this.elevation.setLevel(getElevation());

		super.setCursor(b ? this.cursor : Cursor.getPredefinedCursor(0));

	}

	public void setCursor(Cursor cursor) {

		super.setCursor(cursor);

		this.cursor = cursor;

	}

	protected void processFocusEvent(FocusEvent focusEvent) {

		super.processFocusEvent(focusEvent);

		this.elevation.setLevel(getElevation());

	}

	protected void processMouseEvent(MouseEvent mouseEvent) {

		super.processMouseEvent(mouseEvent);

		this.elevation.setLevel(getElevation());

	}

	private int getElevation() {

		if (this.isMousePressed)

			return 2;

		if (this.type == MaterialType.RAISED || isFocusOwner() || this.isMouseOver)

			return 1;

		return 0;

	}

	protected void paintComponent(Graphics g) {

		g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		if (this.type != MaterialType.FLAT && isEnabled())

			this.elevation.paint(g);

		g2.translate(0, 0);

		if (isEnabled()) {

			g2.setColor(getBackground());

			g2.fill(new RoundRectangle2D.Float(0.0F, 0.0F, (getWidth() - 0), (getHeight() - 0), angle, angle));

			g2.setColor(new Color(this.rippleColor.getRed() / 255.0F, this.rippleColor.getBlue() / 255.0F,
					this.rippleColor.getBlue() / 255.0F, 0.12F));

			if ((this.type == MaterialType.FLAT && this.isMouseOver) || isFocusOwner())

				g2.fill(new RoundRectangle2D.Float(0.0F, 0.0F, (getWidth() - 0), (getHeight() - 0), angle, angle));

		}

		else {

			bg = getBackground();

			g2.setColor(new Color(bg.getRed() / 255.0F, bg.getGreen() / 255.0F, bg.getBlue() / 255.0F, 0.6F));

			g2.fill(new RoundRectangle2D.Float(0.0F, 0.0F, (getWidth() - 0), (getHeight() - 0), angle, angle));

		}

		metrics = g.getFontMetrics(getFont());

		x = (getWidth() - 0 - metrics.stringWidth(getText().toUpperCase())) / 2;

		y = (getHeight() - 0 - metrics.getHeight()) / 2 + metrics.getAscent();

		g2.setFont(getFont());

		if (isEnabled()) {

			g2.setColor(getForeground());

		}

		else {

			fg = getForeground();

			g2.setColor(new Color(fg.getRed() / 255.0F, fg.getGreen() / 255.0F, fg.getBlue() / 255.0F, 0.6F));

		}

		g2.drawString(getText().toUpperCase(), x, y);

		if (isEnabled()) {

			g2.setClip(new RoundRectangle2D.Float(0.0F, 0.0F, (getWidth() - 0), (getHeight() - 0), angle, angle));

			g2.setColor(this.rippleColor);

			this.ripple.paint(g2);

		}

	}

	public enum MaterialType {

		DEFAULT, RAISED, FLAT;

	}

}
