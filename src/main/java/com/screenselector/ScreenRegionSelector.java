package com.screenselector;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.message.alerts.PopupAlerts;
import com.message.alerts.PopupAlerts.AlertType;

public class ScreenRegionSelector extends JFrame {

	private static final long serialVersionUID = 1L;

	private Point startPoint;

	private Point endPoint;

	private Rectangle currentRect;

	private JPanel drawingPanel;

	private int ancho;

	private int alto;

	public int getAncho() {

		return ancho;

	}

	public int getAlto() {

		return alto;

	}

	public ScreenRegionSelector(Color fondo, Color relleno) {

		this(fondo, relleno, 2);

	}

	public ScreenRegionSelector() {

		this(new Color(0, 0, 255, 100), Color.BLUE, 2);

	}

	public ScreenRegionSelector(Color fondo, Color relleno, int thickness) {

		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

		setUndecorated(true);

		setBackground(new Color(0, 0, 0, 1));

		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setAlwaysOnTop(true);

		drawingPanel = new JPanel() {

			private static final long serialVersionUID = 1L;

			@Override

			protected void paintComponent(Graphics g) {

				Graphics2D g2d = (Graphics2D) g;

				if (currentRect != null) {

					g2d.setColor(fondo);

					g2d.fillRect(currentRect.x, currentRect.y, currentRect.width, currentRect.height);

					g2d.setColor(relleno);

					g2d.setStroke(new BasicStroke(thickness));

					g2d.drawRect(currentRect.x, currentRect.y, currentRect.width, currentRect.height);

				}

			}

		};

		drawingPanel.setOpaque(false);

		getContentPane().add(drawingPanel, BorderLayout.CENTER);

		drawingPanel.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				startPoint = e.getPoint();

				endPoint = startPoint;

				currentRect = new Rectangle(startPoint.x, startPoint.y, 0, 0);

			}

			@Override

			public void mouseReleased(MouseEvent e) {

				endPoint = e.getPoint();

				updateRectangle();

				displayDimensions();

				dispose();

			}

		});

		drawingPanel.addMouseMotionListener(new MouseMotionAdapter() {

			@Override

			public void mouseDragged(MouseEvent e) {

				endPoint = e.getPoint();

				updateRectangle();

				drawingPanel.repaint();

			}

		});

		addKeyListener(new KeyAdapter() {

			@Override

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

					dispose();

				}

			}

		});

		setFocusable(true);

		requestFocusInWindow();

		setVisible(true);

	}

	private void updateRectangle() {

		if (startPoint == null || endPoint == null) {

			return;

		}

		int x = Math.min(startPoint.x, endPoint.x);

		int y = Math.min(startPoint.y, endPoint.y);

		int width = Math.abs(startPoint.x - endPoint.x);

		int height = Math.abs(startPoint.y - endPoint.y);

		currentRect = new Rectangle(x, y, width, height);

	}

	private void displayDimensions() {

		if (currentRect != null && currentRect.width > 0 && currentRect.height > 0) {

			ancho = currentRect.width;

			alto = currentRect.height;

		}

		else {

			PopupAlerts alertas = new PopupAlerts(600, 300);

			alertas.setFuente(alertas.getFuente().deriveFont(30f));

			alertas.mensaje("No se seleccionó un área válida", AlertType.INFO);

		}

	}

}