package com.textField.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicTextFieldUI;

import com.contextmenu.DefaultContextMenu;
import com.material.utils.ShadowRenderer;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class MaterialTextField extends JTextField {

	private int round;

	private Color shadowColor;

	private BufferedImage imageShadow;

	private final Insets shadowSize;

	private String text;

	private Color fondo;

	private Color colorFondo;

	private Color border;

	private Font fuente;

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

		this.colorFondo = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || fondo == null || colorFondo == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, colorFondo, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public MaterialTextField() {

		round = 10;

		shadowColor = new Color(170, 170, 170);

		shadowSize = new Insets(2, 5, 8, 5);

		setFont(getFont().deriveFont(30f));

		setUI(new TextUI());

		setOpaque(false);

		setForeground(new Color(80, 80, 80));

		setSelectedTextColor(new Color(255, 255, 255));

		setSelectionColor(new Color(133, 209, 255));

		setBorder(new EmptyBorder(10, 12, 15, 12));

		setBackground(new Color(255, 255, 255));

		DefaultContextMenu.addDefaultContextMenu(this);

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

	private class TextUI extends BasicTextFieldUI {

		@Override
		protected void paintBackground(Graphics grphcs) {

		}

	}

}
