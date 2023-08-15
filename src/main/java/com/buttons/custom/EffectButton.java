package com.buttons.custom;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;

import org.edisoncor.gui.toolTip.ToolTipLlamada;
import org.edisoncor.gui.util.ShadowRenderer;

@SuppressWarnings("serial")

public class EffectButton extends JButton {

	private int round = 10;

	private Color shadowColor = new Color(170, 170, 170);

	private BufferedImage imageShadow;

	private final Insets shadowSize = new Insets(2, 5, 8, 5);

	private final RippleEffect rippleEffect = new RippleEffect(this);

	private String text;

	private Color background;

	private Color foreground;

	private Color border;

	private Font fuente;

	public void setToolTip(String text, Color background, Color foreground, Color border, Font font) {

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

			font = getFont().deriveFont(14f);

		}

		this.text = text;

		this.background = background;

		this.foreground = foreground;

		this.border = border;

		this.fuente = font;

		setToolTipText(text);

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

	public int getRound() {

		return round;

	}

	public void setRound(int round) {

		this.round = round;

		createImageShadow();

		repaint();

	}

	public Color getShadowColor() {

		return shadowColor;

	}

	public void setShadowColor(Color shadowColor) {

		this.shadowColor = shadowColor;

		createImageShadow();

		repaint();

	}

	public void setRippleColor(Color color) {

		rippleEffect.setRippleColor(color);

	}

	public Color getRippleColor() {

		return rippleEffect.getRippleColor();

	}

	public EffectButton(String text) {

		setText(text);

		setFocusPainted(false);

		setBorder(new EmptyBorder(10, 12, 15, 12));

		setContentAreaFilled(false);

		setBackground(new Color(255, 255, 255));

		setForeground(new Color(80, 80, 80));

		rippleEffect.setRippleColor(new Color(220, 220, 220));

	}

	@Override
	protected void paintComponent(Graphics grphcs) {

		Graphics2D g2 = (Graphics2D) grphcs.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		double width = getWidth() - (shadowSize.left + shadowSize.right);

		double height = getHeight() - (shadowSize.top + shadowSize.bottom);

		double x = shadowSize.left;

		double y = shadowSize.top;

		g2.drawImage(imageShadow, 0, 0, null);

		g2.setColor(getBackground());

		Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, round, round));

		g2.fill(area);

		rippleEffect.reder(grphcs, area);

		g2.dispose();

		super.paintComponent(grphcs);

	}

	@Override
	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);

		createImageShadow();

	}

	private void createImageShadow() {

		int height = getHeight();

		int width = getWidth();

		if (width > 0 && height > 0) {

			imageShadow = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

			Graphics2D g2 = imageShadow.createGraphics();

			g2.drawImage(createShadow(), 0, 0, null);

			g2.dispose();

		}

	}

	private BufferedImage createShadow() {

		int width = getWidth() - (shadowSize.left + shadowSize.right);

		int height = getHeight() - (shadowSize.top + shadowSize.bottom);

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = img.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.fill(new RoundRectangle2D.Double(0, 0, width, height, round, round));

		g2.dispose();

		return new ShadowRenderer(5, 0.3f, shadowColor).createShadow(img);

	}

}
