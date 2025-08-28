package com.label.others;

import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class SimpleLabel extends JLabel {

	private ImageIcon originalImageIcon;

	private boolean defaultMaintainAspectRatio;

	private boolean defaultScaleByWidth;

	private int defaultFixedSize;

	public SimpleLabel(String text) {

		defaultMaintainAspectRatio = true;

		defaultScaleByWidth = true;

		defaultFixedSize = 0;

		setText(text);

		setHorizontalAlignment(SwingConstants.CENTER);

		initComponentListener();

	}

	public SimpleLabel() {

		this("");

	}

	private void initComponentListener() {

		this.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {

				applyScaledIcon(originalImageIcon, defaultMaintainAspectRatio, defaultScaleByWidth, defaultFixedSize);

			}

		});

	}

	@Override
	public void setIcon(Icon icon) {

		setearIcono(icon);

		if (originalImageIcon != null) {

			SwingUtilities.invokeLater(() -> {

				applyScaledIcon(this.originalImageIcon, defaultMaintainAspectRatio, defaultScaleByWidth,
						defaultFixedSize);

			});

		}

		else {

			super.setIcon(null);

		}

	}

	private void aplicarIcono() {

		if (originalImageIcon != null) {

			applyScaledIcon(this.originalImageIcon, defaultMaintainAspectRatio, defaultScaleByWidth, defaultFixedSize);

		}

		else {

			super.setIcon(null);

		}

	}

	private void setearIcono(Icon icon) {

		if (icon instanceof ImageIcon) {

			this.originalImageIcon = (ImageIcon) icon;

			if (this.originalImageIcon.getIconWidth() <= 0 || this.originalImageIcon.getIconHeight() <= 0) {

				System.out.println(
						"WARNING: ImageIcon loaded but has zero or negative initial dimensions. Path or file might be problematic.");

			}

		}

		else {

			if (icon != null && icon.getIconWidth() > 0 && icon.getIconHeight() > 0) {

				this.originalImageIcon = new ImageIcon(((ImageIcon) icon).getImage());

			}

			else {

				this.originalImageIcon = null;

			}

		}

	}

	public void setDefaultScalingOptions(Icon icon, boolean maintainAspectRatio, boolean scaleByWidth, int fixedSize) {

		this.defaultMaintainAspectRatio = maintainAspectRatio;

		this.defaultScaleByWidth = scaleByWidth;

		this.defaultFixedSize = fixedSize;

		setearIcono(icon);

		aplicarIcono();

	}

	public void setScaledIcon(Icon originalIcon, boolean maintainAspectRatio, boolean scaleByWidth) {

		if (!(originalIcon instanceof ImageIcon)) {

			System.err.println(
					"Error: setScaledIcon without fixedSize expects an ImageIcon. Provided Icon is not ImageIcon.");

			this.originalImageIcon = null;

			super.setIcon(null);

			return;

		}

		setScaledIcon((ImageIcon) originalIcon, maintainAspectRatio, scaleByWidth);

	}

	public void setScaledIcon(Icon originalIcon, boolean maintainAspectRatio, boolean scaleByWidth, int fixedSize) {
		if (!(originalIcon instanceof ImageIcon)) {
			System.err.println(
					"Error: setScaledIcon con fixedSize espera un ImageIcon. El Icon proporcionado no es ImageIcon.");
			this.originalImageIcon = null;
			super.setIcon(null);
			return;
		}

		setScaledIcon((ImageIcon) originalIcon, maintainAspectRatio, scaleByWidth, fixedSize);
	}

	public void setScaledIcon(ImageIcon originalIcon, boolean maintainAspectRatio, boolean scaleByWidth,
			int fixedSize) {

		setearIcono(originalIcon);

		this.defaultMaintainAspectRatio = maintainAspectRatio;

		this.defaultScaleByWidth = scaleByWidth;

		this.defaultFixedSize = fixedSize;

		applyScaledIcon(originalIcon, maintainAspectRatio, scaleByWidth, fixedSize);

	}

	public void setScaledIcon(ImageIcon originalIcon, boolean maintainAspectRatio, boolean scaleByWidth) {

		setScaledIcon(originalIcon, maintainAspectRatio, scaleByWidth, 0);

	}

	private void applyScaledIcon(ImageIcon iconToScale, boolean maintainAspectRatio, boolean scaleByWidth,
			int fixedSize) {

		if (iconToScale == null) {

			super.setIcon(null);

			return;

		}

		if (iconToScale.getIconWidth() <= 0 || iconToScale.getIconHeight() <= 0) {

			super.setIcon(iconToScale);

			return;

		}

		int labelWidth = getWidth();

		int labelHeight = getHeight();

		if ((labelWidth <= 0 || labelHeight <= 0) && fixedSize <= 0) {

			super.setIcon(iconToScale);

			return;

		}

		Image originalImage = iconToScale.getImage();

		int originalWidth = iconToScale.getIconWidth();

		int originalHeight = iconToScale.getIconHeight();

		int newWidth = labelWidth;

		int newHeight = labelHeight;

		if (fixedSize > 0) {

			if (scaleByWidth) {

				newWidth = fixedSize;

				newHeight = (int) ((double) originalHeight * newWidth / originalWidth);

			}

			else {

				newHeight = fixedSize;

				newWidth = (int) ((double) originalWidth * newHeight / originalHeight);

			}

			if (maintainAspectRatio) {

				if (labelWidth > 0 && newWidth > labelWidth) {

					newWidth = labelWidth;

					newHeight = (int) ((double) originalHeight * newWidth / originalWidth);

				}

				if (labelHeight > 0 && newHeight > labelHeight) {

					newHeight = labelHeight;

					newWidth = (int) ((double) originalWidth * newHeight / originalHeight);

				}

			}

		}

		else {

			if (maintainAspectRatio) {

				double aspectRatio = (double) originalWidth / originalHeight;

				if (scaleByWidth) {

					newHeight = (int) (labelWidth / aspectRatio);

					if (newHeight > labelHeight && labelHeight > 0) {

						newHeight = labelHeight;

						newWidth = (int) (labelHeight * aspectRatio);

					}

				}

				else {

					newWidth = (int) (labelHeight * aspectRatio);

					if (newWidth > labelWidth && labelWidth > 0) {

						newWidth = labelWidth;

						newHeight = (int) (labelWidth / aspectRatio);

					}

				}

			}

		}

		if (newWidth <= 0)
			newWidth = 1;

		if (newHeight <= 0)
			newHeight = 1;

		Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

		super.setIcon(new ImageIcon(scaledImage));

	}

}