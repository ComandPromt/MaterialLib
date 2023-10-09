
package com.label.others;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JToolTip;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicLabelUI;

import com.filters.GraphicsUtil;
import com.filters.Reflection;
import com.material.utils.HyperlinkHandler;
import com.toolTip.ToolTipLlamada;

import mthos.JMthos;

@SuppressWarnings("serial")

public class LabelTask extends JLabel {

	private BufferedImage image;

	private Rectangle clickable;

	private Image buttonHighlight;

	private MouseAdapter ml;

	private float ghostValue;

	private String description;

	private Font categoryFont;

	private Font categorySmallFont;

	private float categorySmallOpacity;

	protected float anchoDeBorde;

	protected Color colorDeBorde;

	protected Color colorDeSegundoBorde;

	private float anchoSegundoBorde;

	private boolean reflejo;

	private boolean foco;

	private Thread zoommer;

	private int disminuirIcono;

	private Color header;

	private TextLayout layout;

	private Icon icono;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private int altura;

	private int anchura;

	private int angulo;

	private boolean segundoBorde;

	private float alturaTexto;

	private float alto;

	public int getAnchura() {

		return anchura;

	}

	public void setAnchura(int anchura) {

		if (anchura < 0) {

			anchura = 0;

		}

		this.anchura = anchura;

		repaint();

	}

	public float getAlturaTexto() {

		return alturaTexto;

	}

	public void setAlturaTexto(float alturaTexto) {

		if (alturaTexto < 0f) {

			alturaTexto = 0f;

			this.alturaTexto = alturaTexto;

		}

		this.alturaTexto = alturaTexto;

		repaint();

	}

	public float getAnchoDeBorde() {

		return anchoDeBorde;

	}

	public float getAnchoSegundoBorde() {

		return anchoSegundoBorde;

	}

	public int getAngulo() {

		return angulo;

	}

	public void setAnchoSegundoBorde(float anchoSegundoBorde) {

		if (anchoSegundoBorde < 0) {

			anchoSegundoBorde = 0;

		}

		this.anchoSegundoBorde = anchoSegundoBorde;

		repaint();

	}

	public void setAngulo(int angulo) {

		this.angulo = angulo;

		repaint();

	}

	public int getAltura() {

		return altura;

	}

	public void setAltura(int altura) {

		if (altura < 0) {

			altura = 0;

		}

		this.altura = altura;

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

	public void setOpacity(float categorySmallOpacity) {

		if (categorySmallOpacity < 0f) {

			categorySmallOpacity = 0f;

		}

		if (categorySmallOpacity > 1f) {

			categorySmallOpacity = 1f;

		}

		this.categorySmallOpacity = categorySmallOpacity;

		repaint();

	}

	public void setHeader(Color header) {

		this.header = header;

		repaint();

	}

	public void setDisminuirIcono(int escala) {

		this.disminuirIcono = escala;

		setIcon(getIcon());

		repaint();

	}

	public void setReflejo(boolean reflejo) {

		this.reflejo = reflejo;

		setIcon(getIcon());

	}

	@Override
	public void setIcon(Icon icon) {

		if (icon != null) {

			this.icono = icon;

		}

		if (icono != null) {

			this.image = JMthos.iconToBufferedImage(icono);

			if (reflejo) {

				BufferedImage mask = Reflection.createGradientMask(image.getWidth(), image.getHeight());

				this.image = Reflection.createReflectedPicture(image, mask);

			}

			disminuirIcono--;

			if (disminuirIcono <= 0) {

				disminuirIcono = 1;

			}

			this.image = JMthos.resizeImage(this.image, getWidth() / disminuirIcono, getWidth() / disminuirIcono);

			disminuirIcono++;

			repaint();

		}

	}

	public void setIcon(Icon icon, boolean reflejo) {

		this.reflejo = reflejo;

		if (icon != null) {

			setReflejo(reflejo);

			setIcon(icon);

			repaint();

		}

	}

	public LabelTask(String name) {

		alturaTexto = 0f;

		altura = 5;

		anchoSegundoBorde = 1.7f;

		angulo = 20;

		reflejo = false;

		disminuirIcono = 5;

		ghostValue = 0.0f;

		description = "Descripcion";

		categoryFont = new Font("Dialog", Font.PLAIN, 30);

		categorySmallOpacity = .8f;

		anchoDeBorde = 4f;

		colorDeBorde = new Color(173, 173, 173);

		colorDeSegundoBorde = Color.WHITE;

		setText(name);

		setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

		setOpaque(true);

		getImage();

		setSize(new Dimension(242, 64));

		setPreferredSize(new Dimension(242, 64));

		ml = new MouseAdapter() {

			@Override

			public void mouseEntered(MouseEvent e) {

				zoom();

			}

			@Override

			public void mouseClicked(MouseEvent e) {

				zoom();

			}

		};

		setUI(new BasicLabelUI() {

			@Override

			public Dimension getMinimumSize(JComponent c) {

				return getPreferredSize(c);

			}

			@Override

			public Dimension getMaximumSize(JComponent c) {

				return getPreferredSize(c);

			}

			@Override

			public Dimension getPreferredSize(JComponent c) {

				Insets insets = c.getInsets();

				Dimension d = new Dimension(new Dimension(116, 35));

				d.width += insets.left + insets.right;

				d.height += insets.top + insets.bottom;

				return d;

			}

		});

		addMouseListener(ml);

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

		setFont(new Font("Dialog", Font.PLAIN, 25));

		setIcon(new ImageIcon(LabelTask.class.getResource("/imgs/imagenes/informacion.png")));

		computeDimension();

		setBorde(Color.PINK);

		setBackground(Color.WHITE);

	}

	public String getDescription() {

		return description;

	}

	public void setDescripcion(String description) {

		this.description = description;

		repaint();

	}

	private void getImage() {

		if (getIcon() != null) {

			image = GraphicsUtil.toBufferedImage(((ImageIcon) getIcon()).getImage());

		}

	}

	private Dimension computeDimension() {

		try {

			Insets insets = getInsets();

			FontMetrics metrics = getFontMetrics(categoryFont);

			Rectangle2D bounds = metrics.getMaxCharBounds(getGraphics());

			int height = (int) bounds.getHeight() + metrics.getLeading();

			int nameWidth = SwingUtilities.computeStringWidth(metrics, getText());

			metrics = getFontMetrics(categorySmallFont);

			bounds = metrics.getMaxCharBounds(getGraphics());

			height += bounds.getHeight();

			int descWidth = SwingUtilities.computeStringWidth(metrics, description == null ? "" : description);

			int width = Math.max(nameWidth, descWidth);

			width += image.getWidth() + 10;

			clickable = new Rectangle(insets.left, insets.top, width, height);

			HyperlinkHandler.add(this, clickable);

			height = Math.max(height, image.getHeight());

			height += 4;

			return new Dimension(width + insets.left + insets.right, height + insets.top + insets.bottom);

		}

		catch (Exception e) {

			return new Dimension(0, 0);

		}

	}

	@Override
	public void paintComponent(Graphics g) {

		if (!isVisible()) {

			return;

		}

		Graphics2D g2 = (Graphics2D) g;

		setupGraphics(g2);

		Paint oldpaint = g2.getPaint();

		if (isOpaque()) {

			float x1 = 0;

			float x2 = 0;

			float y1 = 0;

			float y2 = getHeight();

			int x = 2;

			int y = 2;

			int w = getWidth() - 6;

			int h = getHeight() - 6;

			g2.setPaint(new GradientPaint(x1, y1, getBackground(), x2, y2, getBackground().darker()));

			g2.fillRoundRect(x, y, w, h, angulo, angulo);

			g2.setPaint(oldpaint);

		}

		float y = paintText(g2);

		getImage();

		paintImage(g2, y);

	}

	public boolean isReflejo() {

		return reflejo;

	}

	public boolean isSegundoBorde() {

		return segundoBorde;

	}

	public void setSegundoBorde(boolean segundoBorde) {

		this.segundoBorde = segundoBorde;

		repaint();

	}

	@Override
	public void paintBorder(Graphics g) {

		if (!isOpaque())
			return;

		int x = 2;

		int y = 2;

		int w = getWidth() - 6;

		int h = getHeight() - 5;

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setStroke(new BasicStroke(anchoDeBorde));

		g2.setColor(colorDeBorde);

		g2.drawRoundRect(x, y, w, h, angulo, angulo);

		if (segundoBorde) {

			g2.setStroke(new BasicStroke(anchoSegundoBorde));

			g2.setColor(colorDeSegundoBorde);

			g2.drawRoundRect(x + 2, y + 2, w - 4, h - 4, angulo, angulo);

		}

		g2.dispose();

	}

	private void paintImage(Graphics2D g2, float y) {

		Insets insets = getInsets();

		if (ghostValue > 0.0f) {

			int newWidth = (int) (image.getWidth() * (1.0 + ghostValue / 2.0));

			int newHeight = (int) (image.getHeight() * (1.0 + ghostValue / 2.0));

			Composite composite = g2.getComposite();

			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f * (1.0f - ghostValue)));

			g2.drawImage(image, insets.left + (image.getWidth() - newWidth) / 2,
					4 + (int) (y - newHeight / (5.0 / 3.0)) - (image.getWidth() - newWidth) / 2, newWidth, newHeight,
					null);

			g2.setComposite(composite);

		}

		g2.drawImage(image, null, 14 + anchura, 10 + altura);

		if (foco) {

			g2.drawImage(buttonHighlight, 14 + anchura, 10 + altura, getWidth(), getHeight(), null);

		}

	}

	private float paintText(Graphics2D g2) {

		g2.setFont(categoryFont);

		Insets insets = getInsets();

		try {

			layout = new TextLayout(getText(), categoryFont, g2.getFontRenderContext());

		}

		catch (Exception e) {

			layout = new TextLayout("Header", categoryFont, g2.getFontRenderContext());

		}

		float x = image.getWidth() + 10.0f;

		x += insets.left;

		if (!reflejo) {

			x += 6f;

		}

		float y = 4.0f + layout.getAscent() - layout.getDescent();

		y += insets.top;

		y += 5;

		g2.setColor(header);

		if (alturaTexto == 0f) {

			alturaTexto = y;

			alto = y;

		}

		else if (alto == 0f) {

			alto = y;

		}

		if (alturaTexto != alto) {

			layout.draw(g2, x, alturaTexto + alto);

		}

		else {

			layout.draw(g2, x, alto);

		}

		y += layout.getDescent();

		layout = new TextLayout(description == null ? " " : description, categorySmallFont, g2.getFontRenderContext());

		y += layout.getAscent();

		y -= 5;

		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, categorySmallOpacity));

		g2.setColor(getForeground());

		if (alturaTexto != alto) {

			layout.draw(g2, x, alturaTexto + y);

		}

		else {

			layout.draw(g2, x, y);

		}

		return y;

	}

	private void setupGraphics(Graphics2D g2) {

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

	}

	private void zoom() {

		if (zoommer != null && zoommer.isAlive()) {

			return;

		}

		zoommer = new Thread(new Runnable() {

			@Override
			public void run() {

				try {

					for (float i = 0; i <= 1; i += 0.1) {

						Thread.sleep(30 + (int) (Math.random() * 100));

						ghostValue = i;

						repaint();

					}

					Thread.sleep(30 + (int) (Math.random() * 100));

					ghostValue = 0;

					repaint();

				}

				catch (Exception e) {

				}

			}

		}

		);

		zoommer.start();

	}

	@Override
	public void setFont(Font font) {

		this.categorySmallFont = font;

		repaint();

	}

	public void setHeader(Font categoryFont) {

		this.categoryFont = categoryFont;

		repaint();

	}

	public void setAnchoBorde(float anchoDeBorde) {

		this.anchoDeBorde = anchoDeBorde;

		repaint();

	}

	public void setBorde(Color colorDeBorde) {

		this.colorDeBorde = colorDeBorde;

		repaint();

	}

	public void setSegundoBorde(Color colorDeSegundoBorde) {

		this.colorDeSegundoBorde = colorDeSegundoBorde;

		repaint();

	}

}
