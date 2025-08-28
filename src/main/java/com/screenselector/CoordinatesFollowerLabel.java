package com.screenselector;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class CoordinatesFollowerLabel extends JWindow {

	private static final long serialVersionUID = 1L;

	private JLabel coordsLabel;

	private int offsetX;

	private int offsetY;

	private GraphicsEnvironment ge;

	private GraphicsDevice gd;

	private DisplayMode dm;

	public void setFont(Font font) {

		coordsLabel.setFont(font);

		pack();

	}

	public void setColor(Color color) {

		coordsLabel.setForeground(color);

	}

	public CoordinatesFollowerLabel() {

		setAlwaysOnTop(true);

		setBackground(new Color(0, 0, 0, 0));

		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		offsetX = 20;

		offsetY = 10;

		coordsLabel = new JLabel() {

			private static final long serialVersionUID = 1L;

			@Override

			protected void paintComponent(Graphics g) {

				Graphics2D g2d = (Graphics2D) g;

				g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

				super.paintComponent(g2d);

			}

		};

		coordsLabel.setHorizontalAlignment(SwingConstants.CENTER);

		coordsLabel.setForeground(Color.RED);

		coordsLabel.setBackground(new Color(255, 255, 255, 150));

		coordsLabel.setOpaque(true);

		coordsLabel.setFont(coordsLabel.getFont().deriveFont(Font.BOLD, 30f));

		coordsLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

		getContentPane().add(coordsLabel);

		pack();

		ge = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();

		gd = ge.getDefaultScreenDevice();

		dm = gd.getDisplayMode();

	}

	public void updatePositionAndText(Point mousePoint) {

		int screenWidth = dm.getWidth();

		int screenHeight = dm.getHeight();

		int calculatedX = mousePoint.x + offsetX;

		int calculatedY = mousePoint.y + offsetY;

		if (calculatedX + getWidth() > screenWidth) {

			calculatedX = mousePoint.x - getWidth() - 5;

		}

		if (calculatedY + getHeight() > screenHeight) {

			calculatedY = mousePoint.y - getHeight() - 5;

		}

		coordsLabel.setText(mousePoint.x + " x " + mousePoint.y);

		int finalX = calculatedX;

		int finalY = calculatedY;

		SwingUtilities.invokeLater(() -> {

			coordsLabel.revalidate();

			coordsLabel.repaint();

			pack();

			setLocation(finalX, finalY);

			setVisible(true);

			revalidate();

			repaint();

		});

	}

	public void hideLabel() {

		setVisible(false);

	}

}
