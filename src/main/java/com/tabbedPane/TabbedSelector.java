package com.tabbedPane;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTabbedPane;
import javax.swing.JToolTip;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class TabbedSelector extends JTabbedPane {

	private static final Insets NO_INSETS = new Insets(7, 0, 7, 7);

	protected Color colorDeBorde;

	private Color colorBackGround;

	protected Color colorDeSegundoBorde;

	private Color listColor;

	private Color selectionColor;

	private float selectionOpacity;

	private float selectionBorderOpacity;

	private float listOpacity;

	private float listBorderOpacity;

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

	public TabbedSelector() {

		colorDeBorde = new Color(173, 173, 173);

		colorBackGround = Color.BLACK;

		colorDeSegundoBorde = Color.WHITE;

		listColor = Color.WHITE;

		selectionColor = Color.WHITE;

		selectionOpacity = 0.2f;

		selectionBorderOpacity = 0.8f;

		listOpacity = 0.05f;

		listBorderOpacity = 0.4f;

		setForeground(Color.WHITE);

		setTabPlacement(LEFT);

		setUI(new TabbedPaneUI());

	}

	public Color getColorBackGround() {

		return colorBackGround;

	}

	public void setColorBackGround(Color colorBackGround) {

		this.colorBackGround = colorBackGround;

	}

	public Color getListColor() {

		return listColor;

	}

	public void setListColor(Color listColor) {

		this.listColor = listColor;

	}

	public Color getSelectionColor() {

		return selectionColor;

	}

	public void setSelectionColor(Color selectionColor) {

		this.selectionColor = selectionColor;

	}

	public Color getColorDeBorde() {

		return colorDeBorde;

	}

	public void setColorDeBorde(Color colorDeBorde) {

		this.colorDeBorde = colorDeBorde;

	}

	public Color getColorDeSegundoBorde() {

		return colorDeSegundoBorde;

	}

	public void setColorDeSegundoBorde(Color colorDeSegundoBorde) {

		this.colorDeSegundoBorde = colorDeSegundoBorde;

	}

	@Override
	public void setTabPlacement(int tabPlacement) {

		if (tabPlacement == LEFT)
			super.setTabPlacement(tabPlacement);

	}

	private class TabbedPaneUI extends BasicTabbedPaneUI {

		private int buttonHeight = 17;

		private int leftInset = 12;

		@Override
		protected void installComponents() {

			super.installComponents();

		}

		@Override
		protected void installDefaults() {

			super.installDefaults();

			tabAreaInsets.left = leftInset;

			selectedTabPadInsets = new Insets(0, 0, 0, 0);

		}

		@Override
		public int getTabRunCount(JTabbedPane pane) {

			return super.getTabRunCount(pane);

		}

		@Override
		protected Insets getContentBorderInsets(int tabPlacement) {

			return NO_INSETS;

		}

		@Override
		protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {

			return buttonHeight;

		}

		@Override
		protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {

			return super.calculateTabWidth(tabPlacement, tabIndex, metrics) + buttonHeight;

		}

		@Override
		protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {

			int tw = tabPane.getBounds().width;

			try {

				tw = rects[selectedIndex].width;

			}

			catch (Exception e) {

			}

			Graphics2D g2 = (Graphics2D) g;

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			Composite composite = g2.getComposite();

			g2.setColor(colorBackGround);

			g2.fillRect(0, 0, getWidth(), getHeight());

			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, listOpacity));

			g2.setColor(listColor);

			RoundRectangle2D background;

			background = new RoundRectangle2D.Double(13.0, 3.0, (double) tw - 26.0, (double) getHeight() - 6.0, 10, 10);

			g2.fill(background);

			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, listBorderOpacity));

			Stroke stroke = g2.getStroke();

			g2.setStroke(new BasicStroke(3.0f));

			g2.draw(background);

			g2.drawRoundRect(tw, 0, getWidth() - tw - 1, getHeight() - 1, 10, 10);

			g2.setStroke(stroke);

			g2.setComposite(composite);

			super.paintTabArea(g, tabPlacement, selectedIndex);

		}

		@Override
		protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect,
				Rectangle textRect) {

			super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);

		}

		@Override
		protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int tx, int ty, int tw, int th,
				boolean isSelected) {

			Graphics2D g2d = (Graphics2D) g;

			if (tabPlacement == JTabbedPane.LEFT)

				g2d.translate(0, 0);

			if (tabPlacement == JTabbedPane.RIGHT)

				g2d.translate(tx - buttonHeight, 0);

			if (isSelected) {

				paintSelection(g2d, tabIndex, tx, ty, tw, th);

			}

			else {

				g.setColor(getBackground());

				Graphics2D g2 = (Graphics2D) g.create();

				g2.setColor(colorDeSegundoBorde);
			}

			if (tabPlacement == JTabbedPane.LEFT)

				g2d.translate(0, 0);

			if (tabPlacement == JTabbedPane.BOTTOM)

				g2d.translate(-1 * tx, -1 * ty);

		}

		private void paintSelection(Graphics2D g2, int index, int tx, int ty, int tw, int th) {

			Composite composite = g2.getComposite();

			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, selectionOpacity));

			g2.setColor(selectionColor);

			RoundRectangle2D background;

			background = new RoundRectangle2D.Double(3.0, ty, (double) tw - 5.0, th, 12, 12);

			g2.fill(background);

			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, selectionBorderOpacity));

			Stroke stroke = g2.getStroke();

			g2.setStroke(new BasicStroke(3.0f));

			g2.draw(background);

			g2.setStroke(stroke);

			g2.setComposite(composite);

		}

		@Override
		protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex,
				String title, Rectangle textRect, boolean isSelected) {

			Rectangle r = rects[tabIndex];

			Graphics2D g2d = (Graphics2D) g;

			if (tabPlacement == JTabbedPane.LEFT)

				g2d.translate(0, r.y);

			if (tabPlacement == JTabbedPane.RIGHT)

				g2d.translate(r.width - buttonHeight, 0);

			FontMetrics fm = getFontMetrics();

			if (isSelected)

				g.setColor(getForeground());

			else
				g.setColor(getForeground().darker());

			g.drawString(title, (r.width / 2 - fm.stringWidth(title) / 2) + 1,
					buttonHeight / 2 + fm.getMaxDescent() + 2);

			if (tabPlacement == JTabbedPane.LEFT)

				g2d.translate(0, -1 * r.y);

			else if (tabPlacement == JTabbedPane.RIGHT)

				g2d.translate(-1 * r.x, -1 * r.y);

		}

		@Override
		protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h,
				boolean isSelected) {
		}

		@Override
		protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex,

				Rectangle iconRect, Rectangle textRect, boolean isSelected) {

		}

		@Override
		protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
		}

	}

}
