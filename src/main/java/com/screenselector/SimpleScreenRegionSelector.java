package com.screenselector;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SimpleScreenRegionSelector extends JFrame {

	private static final long serialVersionUID = 1L;

	private Point selectedPoint = null;

	private JPanel drawingPanel;

	private CoordinatesFollowerLabel followerLabel;

	public void setBackColor(Color bgColor) {

		try {

			followerLabel.setBackground(bgColor);

		}

		catch (Exception e) {

		}

	}

	public Point getSelectedPoint() {

		return selectedPoint;

	}

	public void setTextFont(Font font) {

		followerLabel.setFont(font);

	}

	public void setColor(Color color) {

		followerLabel.setColor(color);

	}

	public SimpleScreenRegionSelector(Runnable onSelectionComplete) {

		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

		setUndecorated(true);

		setBackground(new Color(0, 0, 0, 1));

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		GraphicsDevice gd = ge.getDefaultScreenDevice();

		DisplayMode dm = gd.getDisplayMode();

		setBounds(0, 0, dm.getWidth(), dm.getHeight());

		setAlwaysOnTop(true);

		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		drawingPanel = new JPanel();

		drawingPanel.setOpaque(false);

		getContentPane().add(drawingPanel);

		drawingPanel.setLayout(new GridLayout(1, 0, 0, 0));

		followerLabel = new CoordinatesFollowerLabel();

		followerLabel.setColor(Color.BLACK);

		followerLabel.setVisible(false);

		drawingPanel.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				selectedPoint = new Point(e.getXOnScreen(), e.getYOnScreen());

			}

			@Override
			public void mouseReleased(MouseEvent e) {

				selectedPoint = new Point(e.getXOnScreen(), e.getYOnScreen());

				followerLabel.hideLabel();

				if (onSelectionComplete != null) {

					onSelectionComplete.run();

				}

				SwingUtilities.invokeLater(() -> dispose());

			}

		});

		drawingPanel.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {

				followerLabel.updatePositionAndText(new Point(e.getXOnScreen(), e.getYOnScreen()));

			}

			@Override
			public void mouseDragged(MouseEvent e) {

				followerLabel.updatePositionAndText(new Point(e.getXOnScreen(), e.getYOnScreen()));

			}

		});

		addKeyListener(new KeyAdapter() {

			@Override

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

					followerLabel.hideLabel();

					selectedPoint = null;

					SwingUtilities.invokeLater(() -> dispose());

				}

			}

		});

		setFocusable(true);

		requestFocusInWindow();

		setVisible(true);

	}

	@Override
	public void dispose() {

		if (followerLabel != null) {

			followerLabel.hideLabel();

		}

		super.dispose();

	}

}