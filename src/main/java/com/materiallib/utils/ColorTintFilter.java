package com.materiallib.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.jdesktop.swingx.graphics.GraphicsUtilities;

public class ColorTintFilter extends AbstractFilter {

	private final Color mixColor;

	private final float mixValue;

	private int[] preMultipliedRed;

	private int[] preMultipliedGreen;

	private int[] preMultipliedBlue;

	public ColorTintFilter(Color mixColor, float mixValue) {

		if (mixColor == null) {

			throw new IllegalArgumentException("mixColor cannot be null");

		}

		this.mixColor = mixColor;

		if (mixValue < 0.0f) {

			mixValue = 0.0f;

		}

		else if (mixValue > 1.0f) {

			mixValue = 1.0f;

		}

		this.mixValue = mixValue;

		int mix_r = (int) (mixColor.getRed() * mixValue);

		int mix_g = (int) (mixColor.getGreen() * mixValue);

		int mix_b = (int) (mixColor.getBlue() * mixValue);

		float factor = 1.0f - mixValue;

		preMultipliedRed = new int[256];

		preMultipliedGreen = new int[256];

		preMultipliedBlue = new int[256];

		int value = 0;

		for (int i = 0; i < 256; i++) {

			value = (int) (i * factor);

			preMultipliedRed[i] = value + mix_r;

			preMultipliedGreen[i] = value + mix_g;

			preMultipliedBlue[i] = value + mix_b;

		}

	}

	public float getMixValue() {

		return mixValue;

	}

	public Color getMixColor() {

		return mixColor;

	}

	@Override
	public BufferedImage filter(BufferedImage src, BufferedImage dst) {

		if (dst == null) {

			dst = createCompatibleDestImage(src, null);

		}

		int width = src.getWidth();

		int height = src.getHeight();

		int[] pixels = new int[width * height];

		GraphicsUtilities.getPixels(src, 0, 0, width, height, pixels);

		mixColor(pixels);

		GraphicsUtilities.setPixels(dst, 0, 0, width, height, pixels);

		return dst;

	}

	private void mixColor(int[] pixels) {

		for (int i = 0; i < pixels.length; i++) {

			int argb = pixels[i];

			pixels[i] = (argb & 0xFF000000) | preMultipliedRed[(argb >> 16) & 0xFF] << 16
					| preMultipliedGreen[(argb >> 8) & 0xFF] << 8 | preMultipliedBlue[argb & 0xFF];

		}

	}

}
