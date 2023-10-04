package com.toolTip;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;
import javax.swing.JToolTip;
import javax.swing.plaf.basic.BasicToolTipUI;

@SuppressWarnings("serial")
public class ToolTipLlamada extends JToolTip {

	protected Color colorDeBorde;

	private Dimension buttonDimension;

	private String text;

	public ToolTipLlamada(String text, Color background, Color foreground, Color border, Font font) {

		buttonDimension = new Dimension(116, 30);

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

		this.colorDeBorde = border;

		this.text = text;

		setFont(font);

		setForeground(foreground);

		setBackground(background);

		setOpaque(false);

		setUI(new BasicToolTipUI() {

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

				Dimension d = new Dimension(buttonDimension);

				d.width += insets.left + insets.right;

				d.height += insets.top + insets.bottom;

				return d;

			}

		});

	}

	@Override
	protected void paintBorder(Graphics g) {

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(colorDeBorde);

		g2.draw(getShape());

		g2.dispose();

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		TextLayout layout = new TextLayout(getTipText(), getFont(), g2.getFontRenderContext());

		Rectangle2D bounds = layout.getBounds();

		Paint gp = getGradientePaint();

		g2.setPaint(gp);

		g2.fill(getShape());

		g2.setColor(getBackground());

		int x = (int) (getWidth() - bounds.getWidth()) / 2;

		int y = (getHeight() / 2) + ((getHeight() / 2) / 3);

		g2.setColor(getForeground());

		g2.setFont(getFont());

		g2.drawString(text, x, y);

		g2.dispose();

	}

	public Color getColorDeBorde() {

		return colorDeBorde;

	}

	public void setColorDeBorde(Color colorDeBorde) {

		this.colorDeBorde = colorDeBorde;

	}

	public Paint getGradientePaint() {

		return new GradientPaint(0, 0, getBackground(), 0, getHeight(), getBackground().darker());

	}

	public Shape getShape() {

		Shape shape = new RoundRectangle2D.Float(10, 0, getWidth() - 11, getHeight() - 1, getHeight() / 3,
				getHeight() / 3);

		Area area = new Area(shape);

		GeneralPath path = new GeneralPath();

		path.moveTo(0, getHeight() / 2);

		path.lineTo(10, (getHeight() / 2) - 5);

		path.lineTo(10, (getHeight() / 2) + 5);

		path.closePath();

		area.add(new Area(path));

		return area;

	}

}
