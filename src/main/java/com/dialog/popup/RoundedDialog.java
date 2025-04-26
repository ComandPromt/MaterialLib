package com.dialog.popup;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.panels.round.RoundedPopupPanel;

@SuppressWarnings("serial")
public class RoundedDialog extends JDialog {

	private RoundedPopupPanel contentPanel;

	private int lastX;

	private int lastY;

	/**
	 * @wbp.parser.constructor
	 */

	public RoundedDialog(JPanel panel, String title, ImageIcon icon, int width, int height, Color border, int thickness,
			int angle) {

		if (thickness > 5) {

			thickness = 5;

		}

		if (angle > 180) {

			angle = 180;

		}

		else if (angle < 0) {

			angle = 25;

		}

		setUndecorated(true);

		setSize(width, height);

		setShape(new RoundRectangle2D.Double(0, 0, width, height, angle, angle));

		contentPanel = new RoundedPopupPanel(this, panel, title, icon, border, width, height, thickness, angle);

		setContentPane(contentPanel);

		getRootPane().setOpaque(false);

		addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				lastX = e.getX();

				lastY = e.getY();

			}

		});

		addMouseMotionListener(new MouseAdapter() {

			@Override

			public void mouseDragged(MouseEvent e) {

				setLocation(e.getXOnScreen() - lastX, e.getYOnScreen() - lastY);

			}

		});

		setLocationRelativeTo(null);

		setVisible(true);

	}

}
