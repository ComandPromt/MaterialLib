package com.scrollbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ScrollBarUI extends BasicScrollBarUI {

	private Color background;

	private Color thumbHover;

	private Color thumbClick;

	private Color thumbDisable;

	public void setBackground(Color background) {

		this.background = background;

	}

	public void setThumbHover(Color thumbHover) {

		this.thumbHover = thumbHover;

	}

	public void setThumbClick(Color thumbClick) {

		this.thumbClick = thumbClick;

	}

	public void setThumbDisable(Color thumbDisable) {

		this.thumbDisable = thumbDisable;

	}

	public ScrollBarUI() {

		background = Color.WHITE;

		thumbHover = Color.BLUE;

		thumbClick = Color.BLUE;

		thumbDisable = Color.BLUE;

	}

	@Override
	protected JButton createIncreaseButton(int i) {

		return new ScrollBarButton();

	}

	@Override
	protected JButton createDecreaseButton(int i) {

		return new ScrollBarButton();

	}

	@Override
	protected void paintTrack(Graphics grphcs, JComponent jc, Rectangle rctngl) {

		Graphics2D g2 = (Graphics2D) grphcs.create();

		g2.setColor(background);

		g2.fillRect(rctngl.x, rctngl.y, rctngl.width, rctngl.height);

		g2.dispose();

	}

	@Override
	protected Dimension getMinimumThumbSize() {

		return new Dimension(50, 50);

	}

	@Override
	protected void paintThumb(Graphics grphcs, JComponent jc, Rectangle rctngl) {

		Graphics2D g2 = (Graphics2D) grphcs.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int x = rctngl.x;

		int y = rctngl.y;

		int width = rctngl.width;

		int height = rctngl.height;

		int round;

		if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {

			y += 8;

			height -= 16;

			round = width;

		}

		else {

			x += 8;

			width -= 16;

			round = height;

		}

		if (isDragging) {

			g2.setColor(thumbClick);

		}

		else {

			if (isThumbRollover()) {

				g2.setColor(thumbHover);

			}

			else {

				g2.setColor(thumbDisable);

			}

		}

		g2.fillRoundRect(x, y, width, height, round, round);

		g2.dispose();

	}

	@SuppressWarnings("serial")
	private class ScrollBarButton extends JButton {

		public ScrollBarButton() {

			setBorder(BorderFactory.createEmptyBorder());

		}

		@Override
		public void paint(Graphics grphcs) {

		}

	}

}
