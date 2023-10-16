package com.textimage;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class TextImage extends JLabel {

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private int angulo;

	public int getAngulo() {

		return angulo;

	}

	public void setAngulo(int angulo) {

		this.angulo = angulo;

		repaint();

	}

	public void setSize(float size) {

		setFont(getFont().deriveFont(size));

		repaint();

	}

	public TextImage(String text) {

		setFont(new Font("Dialog", Font.BOLD, 100));

		setText(text);

	}

	public TextImage(String text, Icon icon) {

		setFont(new Font("Dialog", Font.BOLD, 100));

		setText(text);

		setIcon(icon);

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

		return textImage;

	}

	@Override
	public void setIcon(Icon textImage) {

		this.textImage = textImage;

		repaint();
	}

	private Icon textImage;

	@Override

	protected void paintComponent(Graphics grphcs) {

		int width = getWidth();

		int height = getHeight();

		grphcs.setColor(getBackground());

		grphcs.fillRoundRect(0, 0, width, height, angulo, angulo);

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = img.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g2.setFont(getFont());

		FontMetrics ft = g2.getFontMetrics();

		Rectangle2D r2 = ft.getStringBounds(getText(), g2);

		double x = (width - r2.getWidth()) / 2;

		double y = (height - r2.getHeight()) / 2;

		g2.setColor(getForeground());

		g2.drawString(getText(), (int) x, (int) (y + ft.getAscent()));

		if (textImage != null) {

			g2.setComposite(AlphaComposite.SrcIn);

			g2.drawImage(((ImageIcon) textImage).getImage(), 0, 0, width, height, null);

		}

		grphcs.drawImage(img, 0, 0, null);

	}

}
