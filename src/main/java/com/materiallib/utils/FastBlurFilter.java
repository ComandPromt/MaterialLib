package com.materiallib.utils;

import java.awt.image.BufferedImage;

import org.jdesktop.swingx.graphics.GraphicsUtilities;

public class FastBlurFilter extends AbstractFilter {

	private final int radius;

	public FastBlurFilter() {

		this(3);

	}

	public FastBlurFilter(int radius) {

		if (radius < 1) {

			radius = 1;

		}

		this.radius = radius;

	}

	public int getRadius() {

		return radius;

	}

	@Override
	public BufferedImage filter(BufferedImage src, BufferedImage dst) {

		int width = src.getWidth();

		int height = src.getHeight();

		if (dst == null) {

			dst = createCompatibleDestImage(src, null);

		}

		int[] srcPixels = new int[width * height];

		int[] dstPixels = new int[width * height];

		GraphicsUtilities.getPixels(src, 0, 0, width, height, srcPixels);

		blur(srcPixels, dstPixels, width, height, radius);

		blur(dstPixels, srcPixels, height, width, radius);

		GraphicsUtilities.setPixels(dst, 0, 0, width, height, srcPixels);

		return dst;
	}

	public static void blur(int[] srcPixels, int[] dstPixels, int width, int height, int radius) {

		int windowSize = radius * 2 + 1;

		int sumAlpha;

		int sumRed;

		int sumGreen;

		int sumBlue;

		int srcIndex = 0;

		int dstIndex;

		int pixel;

		for (int y = 0; y < height; y++) {

			sumAlpha = sumRed = sumGreen = sumBlue = 0;

			dstIndex = y;

			pixel = srcPixels[srcIndex];

			sumAlpha += (radius + 1) * ((pixel >> 24) & 0xFF);

			sumRed += (radius + 1) * ((pixel >> 16) & 0xFF);

			sumGreen += (radius + 1) * ((pixel >> 8) & 0xFF);

			sumBlue += (radius + 1) * (pixel & 0xFF);

			for (int i = 1; i <= radius; i++) {

				pixel = srcPixels[srcIndex + (i <= width - 1 ? i : width - 1)];

				sumAlpha += (pixel >> 24) & 0xFF;

				sumRed += (pixel >> 16) & 0xFF;

				sumGreen += (pixel >> 8) & 0xFF;

				sumBlue += pixel & 0xFF;

			}

			for (int x = 0; x < width; x++) {

				dstPixels[dstIndex] = sumAlpha / windowSize << 24 | sumRed / windowSize << 16
						| sumGreen / windowSize << 8 | sumBlue / windowSize;

				dstIndex += height;

				int nextPixelIndex = x + radius + 1;

				if (nextPixelIndex >= width) {

					nextPixelIndex = width - 1;

				}

				int previousPixelIndex = x - radius;

				if (previousPixelIndex < 0) {

					previousPixelIndex = 0;

				}

				int nextPixel = srcPixels[srcIndex + nextPixelIndex];

				int previousPixel = srcPixels[srcIndex + previousPixelIndex];

				sumAlpha += (nextPixel >> 24) & 0xFF;

				sumAlpha -= (previousPixel >> 24) & 0xFF;

				sumRed += (nextPixel >> 16) & 0xFF;

				sumRed -= (previousPixel >> 16) & 0xFF;

				sumGreen += (nextPixel >> 8) & 0xFF;

				sumGreen -= (previousPixel >> 8) & 0xFF;

				sumBlue += nextPixel & 0xFF;

				sumBlue -= previousPixel & 0xFF;

			}

			srcIndex += width;

		}

	}

}
