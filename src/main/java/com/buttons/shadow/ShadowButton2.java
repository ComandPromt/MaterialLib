
package com.buttons.shadow;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolTip;

import com.filters.GraphicsUtil;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class ShadowButton2 extends JButton {

	private Image buttonHighlight;

	private float shadowOffsetX;

	private float shadowOffsetY;

	private Color colorDeSombra;

	private int direccionDeSombra;

	private int distanciaDeSombra;

	private int angulo;

	private boolean vertical;

	private boolean foco;

	private String text;

	private Color fondo;

	private Color colorTexto;

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

	public ShadowButton2(Icon icon) {

		super(icon);

		colorDeSombra = new Color(0, 0, 0);

		direccionDeSombra = 60;

		distanciaDeSombra = 1;

		angulo = 20;

		vertical = true;

		foco = false;

		setFont(getFont().deriveFont(Font.PLAIN, 30f));

	}

	/**
	 * @wbp.parser.constructor
	 */

	public ShadowButton2() {

		this("");

	}

	public ShadowButton2(String text) {

		colorDeSombra = new Color(0, 0, 0);

		direccionDeSombra = 60;

		distanciaDeSombra = 1;

		angulo = 20;

		vertical = true;

		foco = false;

		setText(text);

		setOpaque(false);

		setContentAreaFilled(false);

		setFocusPainted(false);

		setBorderPainted(false);

		setFont(getFont().deriveFont(Font.PLAIN, 30f));

		setForeground(new Color(255, 255, 255));

		addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

				foco = true;

			}

			@Override
			public void focusLost(FocusEvent e) {

				foco = false;

			}

		});

	}

	private void computeShadow() {

		double rads = Math.toRadians(direccionDeSombra);

		shadowOffsetX = (float) Math.cos(rads) * distanciaDeSombra;

		shadowOffsetY = (float) Math.sin(rads) * distanciaDeSombra;

	}

	@Override
	protected void paintComponent(Graphics g) {

		computeShadow();

		float x1 = 0;

		float x2 = 0;

		float y1 = 0;

		float y2 = getHeight();

		if (!vertical) {

			x1 = 0;

			y1 = 0;

			x2 = getWidth();

			y2 = 0;

		}

		Graphics2D g2 = (Graphics2D) g;

		Paint oldPaint = g2.getPaint();

		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), angulo, angulo);

		g2.clip(r2d);

		ButtonModel modelo = getModel();

		RoundRectangle2D rect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), angulo, angulo);

		if (modelo.isArmed() || modelo.isPressed())

			g2.setPaint(new GradientPaint(x1, y1, getBackground().darker(), x2, y2, getBackground()));

		else

			g2.setPaint(new GradientPaint(x1, y1, getBackground(), x2, y2, getBackground().darker()));

		g2.fill(rect);

		if (getIcon() != null) {

			BufferedImage image = GraphicsUtil.toBufferedImage(((ImageIcon) getIcon()).getImage());

			g2.drawImage(image, 5, 5, getWidth() - 10, getHeight() - 10, null);

		}

		if (modelo.isRollover() || foco) {

			g2.drawRect(0, 0, getWidth(), getHeight());

			g2.drawImage(buttonHighlight, 0, 0, getWidth(), getHeight(), null);

		}

		FontMetrics fm = getFontMetrics(getFont());

		TextLayout layout;

		try {

			layout = new TextLayout(getText(), getFont(), g2.getFontRenderContext());

		}

		catch (Exception e) {

			layout = new TextLayout(" ", getFont(), g2.getFontRenderContext());

		}

		Rectangle2D bounds = layout.getBounds();

		int x = (int) (getWidth() - bounds.getWidth()) / 2;

		int y = (getHeight() - fm.getMaxAscent() - fm.getMaxDescent()) / 2;

		y += fm.getAscent() - 1;

		if (modelo.isArmed()) {

			x += 1;

			y += 1;

		}

		g2.setColor(colorDeSombra);

		layout.draw(g2, x + (int) Math.ceil(shadowOffsetX), y + (int) Math.ceil(shadowOffsetY));

		if (isEnabled())
			g2.setColor(getForeground());

		else
			g2.setColor(getForeground().darker());

		layout.draw(g2, x, y);

		g2.setPaint(oldPaint);

	}

	public Color getColorDeSombra() {

		return colorDeSombra;

	}

	public void setColorDeSombra(Color colorDeSombra) {

		this.colorDeSombra = colorDeSombra;

		repaint();

	}

	public int getDireccionDeSombra() {

		return direccionDeSombra;

	}

	public void setDireccionDeSombra(int direccionDeSombra) {

		this.direccionDeSombra = direccionDeSombra;

		repaint();

	}

	public int getDistanciaDeSombra() {

		return distanciaDeSombra;

	}

	public void setDistanciaDeSombra(int distanciaDeSombra) {

		this.distanciaDeSombra = distanciaDeSombra;

		repaint();

	}

	public int getAngulo() {

		return angulo;

	}

	public void setAngulo(int angulo) {

		this.angulo = angulo;

		repaint();

	}

	public boolean isVertical() {

		return vertical;

	}

	public void setVertical(boolean vertical) {

		this.vertical = vertical;

		repaint();

	}

}
