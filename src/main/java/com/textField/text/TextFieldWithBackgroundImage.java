package com.textField.text;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;

import com.contextmenu.DefaultContextMenu;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class TextFieldWithBackgroundImage extends JTextField {

	private Integer angle;

	private Icon icon;

	private float alfa;

	protected float anchoDeBorde;

	protected Color colorDeBorde;

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

		this.fondo = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || fondo == null || fondo == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, fondo, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public TextFieldWithBackgroundImage() {

		angle = 20;

		alfa = .7f;

		anchoDeBorde = 2f;

		colorDeBorde = new Color(173, 173, 173);

		setOpaque(false);

		setFont(getFont().deriveFont(30f));

		setBorder(new EmptyBorder(0, 5, 0, 2));

		setPreferredSize(new Dimension(69, 20));

		DefaultContextMenu.addDefaultContextMenu(this);

	}

	public float getAlfa() {

		return alfa;

	}

	public void setAlfa(float alfa) {

		if (alfa > 1)
			alfa = 1;

		if (alfa < 0)
			alfa = 0;

		this.alfa = alfa;

		repaint();

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		Paint oldPaint = g2.getPaint();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), angle, angle);

		g2.clip(r2d);

		g2.setPaint(new GradientPaint(0.0f, 0.0f, getBackground(), 0.0f, getHeight(), getBackground()));

		g2.fillRoundRect(0, 0, getWidth(), getHeight(), angle, angle);

		if (icon != null) {

			Composite oldComposite = g2.getComposite();

			AlphaComposite newComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alfa);

			g2.setComposite(newComposite);

			g2.drawImage(((ImageIcon) icon).getImage(), 0, 0, getWidth(), getHeight(), null);

			g2.setComposite(oldComposite);

		}

		g2.setPaint(oldPaint);

		super.paintComponent(g);

	}

	@Override
	protected void paintBorder(Graphics g) {

		int x = 1, y = 1;

		int w = getWidth() - 2, h = getHeight() - 2;

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setStroke(new BasicStroke(anchoDeBorde));

		g2.setColor(colorDeBorde);

		g2.drawRoundRect(x, y, w, h, angle, angle);

		g2.dispose();

	}

	public Icon getIcon() {

		return icon;

	}

	public void setIcon(Icon icon) {

		this.icon = icon;

		repaint();

	}

	public Integer getAngle() {

		return angle;

	}

	public void setAngle(Integer angle) {

		this.angle = angle;

		repaint();

	}

	public float getAnchoDeBorde() {

		return anchoDeBorde;

	}

	public void setAnchoDeBorde(float anchoDeBorde) {

		this.anchoDeBorde = anchoDeBorde;

	}

	public Color getColorDeBorde() {

		return colorDeBorde;

	}

	public void setColorDeBorde(Color colorDeBorde) {

		this.colorDeBorde = colorDeBorde;

	}

}
