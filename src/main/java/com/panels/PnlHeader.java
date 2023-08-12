package com.panels;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("all")

public class PnlHeader extends JPanel {

	private Frame frame;

	private Color minimizeEnteredColor;

	private Color minimizeExitedColor;

	private Color maximizeEnteredColor;

	private Color maximizeExitedColor;

	private Color closeEnteredColor;

	private Color closeExitedColor;

	private Color backgroundEnteredColor;

	private Color backgroundExitedColor;

	private Color backgroundColor;

	private JavaLabel btnMinimize;

	private JavaLabel btnMaximize;

	private JavaLabel btnExit;

	public void setThickness(int thickness) {

		try {

			btnMinimize.setGrosor(thickness);

			btnExit.setGrosor(thickness);

			btnMaximize.setGrosor(thickness);

		}

		catch (Exception e) {

		}

	}

	public void setBackgroundEnteredColor(Color backgroundEnteredColor) {

		try {

			this.backgroundEnteredColor = backgroundEnteredColor;

			btnMinimize.setBackGroundMouseEnteredColor(backgroundEnteredColor);

			btnExit.setBackGroundMouseEnteredColor(backgroundEnteredColor);

			btnMaximize.setBackGroundMouseEnteredColor(backgroundEnteredColor);

		}

		catch (Exception e) {

		}

	}

	public void setBackgroundExitedColor(Color backgroundExitedColor) {

		try {

			this.backgroundExitedColor = backgroundExitedColor;

			btnMinimize.setBackGroundMouseExitedColor(backgroundExitedColor);

			btnExit.setBackGroundMouseExitedColor(backgroundExitedColor);

			btnMaximize.setBackGroundMouseExitedColor(backgroundExitedColor);

		} catch (Exception e) {

		}
	}

	public void setBackgroundColor(Color backgroundColor) {

		this.backgroundColor = backgroundColor;

		setBackground(backgroundColor);

	}

	public void setMinimizeEnteredColor(Color minimizeEnteredColor) {

		this.minimizeEnteredColor = minimizeEnteredColor;

		btnMinimize.setEnteredColor(minimizeEnteredColor);

	}

	public void setMinimizeExitedColor(Color minimizeExitedColor) {

		this.minimizeExitedColor = minimizeExitedColor;

		btnMinimize.setExitedColor(minimizeExitedColor);

	}

	public void setMaximizeEnteredColor(Color maximizeEnteredColor) {

		this.maximizeEnteredColor = maximizeEnteredColor;

		btnMaximize.setEnteredColor(maximizeEnteredColor);

	}

	public void setMaximizeExitedColor(Color maximizeExitedColor) {

		this.maximizeExitedColor = maximizeExitedColor;

		btnMaximize.setExitedColor(maximizeExitedColor);
	}

	public void setCloseEnteredColor(Color closeEnteredColor) {

		this.closeEnteredColor = closeEnteredColor;

		btnExit.setEnteredColor(closeEnteredColor);

	}

	public void setCloseExitedColor(Color closeExitedColor) {

		this.closeExitedColor = closeExitedColor;

		btnExit.setExitedColor(closeExitedColor);

	}

	public PnlHeader(Frame frame, boolean resize) {

		this.frame = frame;

		backgroundColor = Color.WHITE;

		minimizeEnteredColor = Color.RED;

		minimizeExitedColor = Color.BLACK;

		maximizeEnteredColor = Color.RED;

		maximizeExitedColor = Color.BLACK;

		closeEnteredColor = Color.RED;

		closeExitedColor = Color.BLACK;

		backgroundEnteredColor = Color.WHITE;

		backgroundExitedColor = Color.WHITE;

		setBackground(backgroundColor);

		btnMinimize = new JavaLabel(1, minimizeEnteredColor, minimizeExitedColor);

		btnMinimize.setBackGroundMouseEnteredColor(backgroundEnteredColor);

		btnMinimize.setBackGroundMouseExitedColor(backgroundExitedColor);

		btnMinimize.setIcon(new ImageIcon(PnlHeader.class.getResource("/imgs/imagenes/closePanel.png")));

		btnMinimize.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				frame.setExtendedState(Frame.ICONIFIED);

			}

		});

		btnMinimize.setOpaque(true);

		btnMinimize.setFocusable(false);

		btnMinimize.setBackground(backgroundColor);

		add(btnMinimize);

		if (resize) {

			btnMaximize = new JavaLabel(2, maximizeEnteredColor, maximizeExitedColor);

			btnMaximize.setBackGroundMouseEnteredColor(backgroundEnteredColor);

			btnMaximize.setBackGroundMouseExitedColor(backgroundExitedColor);

			btnMaximize.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {

					btnMaximize.click();

					btnMaximize.repaint();

					if (btnMaximize.isMaximizar()) {

						frame.setExtendedState(Frame.MAXIMIZED_BOTH);
					}

					else {

						frame.setExtendedState(Frame.NORMAL);

					}

				}

			});

			btnMaximize.setIcon(new ImageIcon(PnlHeader.class.getResource("/imgs/imagenes/closePanel.png")));

			btnMaximize.setOpaque(true);

			btnMaximize.setFocusable(false);

			btnMaximize.setBackground(backgroundColor);

			add(btnMaximize);

		}

		btnExit = new JavaLabel(3, closeEnteredColor, closeExitedColor);

		btnExit.setBackGroundMouseEnteredColor(backgroundEnteredColor);

		btnExit.setBackGroundMouseExitedColor(backgroundExitedColor);

		btnExit.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				frame.dispose();

			}

		});

		btnExit.setIcon(new ImageIcon(PnlHeader.class.getResource("/imgs/imagenes/closePanel.png")));

		btnExit.setOpaque(true);

		btnExit.setFocusable(false);

		btnExit.setBackground(backgroundColor);

		add(btnExit);

	}

}
