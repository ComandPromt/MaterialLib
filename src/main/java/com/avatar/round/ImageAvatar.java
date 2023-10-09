package com.avatar.round;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class ImageAvatar extends JLabel {

	private Icon image;

	private int borderSize;

	private int borderSpace;

	private Color gradientColor1;

	private Color gradientColor2;

	private Color spaceColor;

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

				font = getFont().deriveFont(14f);

			}

			catch (Exception e) {

				font = new Font("Dialog", Font.PLAIN, 14);

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

	public ImageAvatar() {

		borderSize = 6;

		borderSpace = 5;

		gradientColor1 = new Color(255, 90, 90);

		gradientColor2 = new Color(42, 199, 80);

		spaceColor = Color.WHITE;

	}

	public void setSpaceColor(Color spaceColor) {

		this.spaceColor = spaceColor;

		repaint();

	}

	public Icon getImage() {

		return image;

	}

	public void setImage(Icon image) {

		this.image = image;

		repaint();

	}

	public int getBorderSize() {

		return borderSize;

	}

	public void setBorderSize(int borderSize) {

		this.borderSize = borderSize;

		repaint();

	}

	public int getBorderSpace() {

		return borderSpace;

	}

	public void setBorderSpace(int borderSpace) {

		this.borderSpace = borderSpace;

		repaint();

	}

	public Color getGradientColor1() {

		return gradientColor1;

	}

	public void setGradientColor1(Color gradientColor1) {

		this.gradientColor1 = gradientColor1;

		repaint();

	}

	public Color getGradientColor2() {

		return gradientColor2;

	}

	public void setGradientColor2(Color gradientColor2) {

		this.gradientColor2 = gradientColor2;

		repaint();

	}

	@Override

	protected void paintComponent(Graphics grphcs) {

		Graphics2D g2 = (Graphics2D) grphcs;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		createBorder(g2);

		if (image != null) {

			int width = getWidth();

			int height = getHeight();

			int diameter = Math.min(width, height) - (borderSize * 2 + borderSpace * 2);

			int x = (width - diameter) / 2;

			int y = (height - diameter) / 2;

			Rectangle size = getAutoSize(image, diameter);

			BufferedImage img = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);

			Graphics2D g2Img = img.createGraphics();

			g2Img.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g2Img.fillOval(0, 0, diameter, diameter);

			Composite composite = g2Img.getComposite();

			g2Img.setComposite(AlphaComposite.SrcIn);

			g2Img.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

			g2Img.drawImage(toImage(image), size.x, size.y, size.width, size.height, null);

			g2Img.setComposite(composite);

			g2Img.dispose();

			g2.drawImage(img, x, y, null);

		}

		super.paintComponent(grphcs);

	}

	private void createBorder(Graphics2D g2) {

		int width = getWidth();

		int height = getHeight();

		int diameter = Math.min(width, height);

		int x = (width - diameter) / 2;

		int y = (height - diameter) / 2;

		if (borderSpace > 0) {

			g2.setStroke(new BasicStroke(borderSpace + 2));

			g2.setColor(spaceColor);

			g2.drawOval(x + borderSize + 2, (y + borderSize + 2), (diameter - (borderSize + (borderSpace * 2))) - 1,
					(diameter - (borderSize + (borderSpace * 2))) - 1);

		}

		if (isOpaque()) {

			g2.setColor(getBackground());

			g2.fillOval(x, y, diameter, diameter);

		}

		Area area = new Area(new Ellipse2D.Double(x, y, diameter, diameter));

		int s = diameter -= (borderSize * 2);

		area.subtract(new Area(new Ellipse2D.Double(x + borderSize, y + borderSize, s, s)));

		g2.setPaint(new GradientPaint(0, 0, gradientColor1, width, height, gradientColor2));

		g2.fill(area);

	}

	private Rectangle getAutoSize(Icon image, int size) {

		int w = size;

		int h = size;

		int iw = image.getIconWidth();

		int ih = image.getIconHeight();

		double xScale = (double) w / iw;

		double yScale = (double) h / ih;

		double scale = Math.max(xScale, yScale);

		int width = (int) (scale * iw);

		int height = (int) (scale * ih);

		if (width < 1) {

			width = 1;

		}

		if (height < 1) {

			height = 1;

		}

		int cw = size;

		int ch = size;

		int x = (cw - width) / 2;

		int y = (ch - height) / 2;

		return new Rectangle(new Point(x, y), new Dimension(width, height));

	}

	private Image toImage(Icon icon) {

		return ((ImageIcon) icon).getImage();

	}

}
