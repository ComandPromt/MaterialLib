package com.figures;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

@SuppressWarnings("serial")

public class ImageIconFromPaintMethod extends JComponent {

	private Color color;

	private Color backgroundColor;

	public ImageIconFromPaintMethod(Color color, Color background) {

		this.color = color;

		backgroundColor = background;

	}

	public ImageIcon createImageIconFromPaint(int width, int height, int figure) {
		try {
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

			Graphics2D g2d = bufferedImage.createGraphics();

			g2d.setColor(backgroundColor);

			g2d.fillRect(0, 0, width, height);

			g2d.setColor(color);

			switch (figure) {

			case 1:

				break;

			case 2:

				break;

			default:
				break;

			}

			g2d.dispose();

			return new ImageIcon(bufferedImage);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
