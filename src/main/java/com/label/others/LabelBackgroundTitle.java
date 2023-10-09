package com.label.others;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JToolTip;
import javax.swing.plaf.basic.BasicLabelUI;

import com.toolTip.ToolTipLlamada;

import mthos.JMthos;

@SuppressWarnings("serial")
public class LabelBackgroundTitle extends JLabel {

	private Image titleImage;

	private float shadowOffsetX;

	private float shadowOffsetY;

	private float shadowOpacity;

	private Color shadowColor;

	private int shadowDistance;

	private int shadowDirection;

	private Font titleFont;

	private Color titleColor;

	private Color colorSegundoBorde;

	private float titleOpacity;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private Font ffuente;

	@Override
	public void setForeground(Color fg) {

		setTitleColor(fg);

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

	@Override
	public void setIcon(Icon icon) {

		try {

			this.titleImage = JMthos.iconToBufferedImage(icon);

			repaint();

		}

		catch (Exception e) {

		}

	}

	public void setTitleOpacity(float titleOpacity) {

		if (titleOpacity < 0f) {

			titleOpacity = 0f;

		}

		if (titleOpacity > 1f) {

			titleOpacity = 1f;

		}

		this.titleOpacity = titleOpacity;

	}

	public void setLine(Color color) {

		this.colorSegundoBorde = color;

		repaint();

	}

	@Override
	public void setFont(Font font) {

		titleFont = font;

		repaint();

	}

	public void setShadowDirection(int shadowDirection) {

		this.shadowDirection = shadowDirection;

		repaint();

	}

	public void setShadowOpacity(float shadowOpacity) {

		if (shadowOpacity < 0f) {

			shadowOpacity = 0f;

		}

		if (shadowOpacity > 1f) {

			shadowOpacity = 1f;

		}

		this.shadowOpacity = shadowOpacity;

		repaint();

	}

	public LabelBackgroundTitle() {

		this("Text");

	}

	public LabelBackgroundTitle(final String text) {

		setBackground(Color.WHITE);

		ffuente = new Font("Dialog", Font.PLAIN, 20);

		shadowOpacity = 0.5f;

		shadowColor = new Color(0, 0, 0);

		shadowDistance = 5;

		shadowDirection = 60;

		titleFont = new Font("Dialog", Font.PLAIN, 30);

		titleColor = new Color(255, 255, 255);

		titleOpacity = .8f;

		setOpaque(false);

		setText(text);

		setUI(new BasicLabelUI() {

			@Override
			public Dimension getMinimumSize(JComponent c) {

				return getPreferredSize(c);

			}

			@Override
			public Dimension getMaximumSize(JComponent c) {

				return getPreferredSize(c);

			}

		});

		setFont(new Font("Dialog", Font.PLAIN, 30));

		computeShadow();

	}

	private void computeShadow() {

		double rads = Math.toRadians(shadowDirection);

		shadowOffsetX = (float) Math.cos(rads) * shadowDistance;

		shadowOffsetY = (float) Math.sin(rads) * shadowDistance;

	}

	@Override
	protected void paintComponent(Graphics g) {

		computeShadow();

		Graphics2D g2 = (Graphics2D) g;

		if (titleImage == null) {

			titleImage = createTitleImage(g2);

		}

		Composite composite = g2.getComposite();

		g2.setFont(ffuente);

		g2.setColor(getBackground());

		g2.fillRect(0, 0, getWidth(), getHeight());

		g2.setColor(Color.RED);

		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, titleOpacity));

		g2.drawImage(titleImage, 0, 0, getWidth(), getHeight(), null);

		g2.setComposite(composite);

	}

	private Image createTitleImage(Graphics2D g2) {

		FontRenderContext context = g2.getFontRenderContext();

		if ((getText() == null) || getText().length() <= 0) {

			setText("Title");

		}

		TextLayout layout = new TextLayout(getText(), titleFont, context);

		Rectangle2D bounds = layout.getBounds();

		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = image.createGraphics();

		setupGraphics(g2d);

		int[] arrowX = { getWidth(), getWidth(), getWidth() };

		int[] arrowY = { 3 + (int) bounds.getHeight() + 7, 7 + (int) bounds.getHeight() + 7,
				12 + (int) bounds.getHeight() + 7 };

		int npoints = 3;

		Polygon arrow = new Polygon(arrowX, arrowY, npoints);

		Composite composite = g2d.getComposite();

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, shadowOpacity));

		g2d.setColor(shadowColor);

		layout.draw(g2d, shadowOffsetX + 5.0f, layout.getAscent() - layout.getDescent() + shadowOffsetY + 5.0f);

		g2d.fillRect(5 + (int) shadowOffsetX, (int) shadowOffsetY + 5 + (int) bounds.getHeight() + 7, getWidth() - 10,
				5);

		g2d.translate(0, shadowOffsetY);

		g2d.fill(arrow);

		g2d.translate(0, -shadowOffsetY);

		g2d.setComposite(composite);

		g2d.setColor(titleColor);

		layout.draw(g2d, 5.0f, 5.0f + layout.getAscent() - layout.getDescent());

		g2d.setColor(colorSegundoBorde);

		g2d.fillRect(5, 5 + (int) bounds.getHeight() + 7, getWidth() - 5, 5);

		g2d.fill(arrow);

		g2d.dispose();

		Kernel kernel = new Kernel(3, 3,
				new float[] { 1f / 9f, 1 / 9f, 1f / 9f, 1f / 9f, 1 / 9f, 1f / 9f, 1f / 9f, 1 / 9f, 1f / 9f });
		ConvolveOp op = new ConvolveOp(kernel);

		return op.filter(op.filter(image, null), null);

	}

	private static void setupGraphics(Graphics2D g2) {

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	}

	public Color getShadowColor() {

		return shadowColor;

	}

	public void setShadowColor(Color shadowColor) {

		this.shadowColor = shadowColor;

		computeShadow();

		repaint();

	}

	public int getShadowDistance() {

		return shadowDistance;

	}

	public void setShadowDistance(int shadowDistance) {

		this.shadowDistance = shadowDistance;

		computeShadow();

		titleImage = null;

		repaint();

	}

	public Color getTitleColor() {

		return titleColor;

	}

	private void setTitleColor(Color titleColor) {

		this.titleColor = titleColor;

		computeShadow();

		titleImage = null;

		repaint();

	}

	public Font getTitleFont() {

		return titleFont;

	}

	public void setTitleFont(Font titleFont) {

		this.titleFont = titleFont;

		computeShadow();

		titleImage = null;

		repaint();

	}

}
