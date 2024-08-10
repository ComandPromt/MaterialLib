package com.effects;

import java.awt.image.BufferedImage;

public class FastGaussianBlur {

	public static void blur(BufferedImage image, double radius) {

		int w = image.getWidth();

		int h = image.getHeight();

		int[] pixels = new int[w * h];

		image.getRGB(0, 0, w, h, pixels, 0, w);

		int[][] channels = new int[4][];

		int[] red;

		int[] blurRed;

		for (int channel = 0; channel <= 3; channel++) {

			red = new int[w * h];

			blurRed = new int[w * h];

			for (int j = 0; j < w * h; j++)
				red[j] = pixels[j] >> channel * 8 & 0xFF;

			gaussBlur_4(red, blurRed, w, h, radius);

			channels[channel] = blurRed;

		}

		for (int i = 0; i < w * h; i++)
			pixels[i] = channels[0][i] | channels[1][i] << 8 | channels[2][i] << 16 | channels[3][i] << 24;

		image.setRGB(0, 0, w, h, pixels, 0, w);

	}

	private static int[] boxesForGauss(double sigma, int n) {

		double wIdeal = Math.sqrt(12.0D * sigma * sigma / n + 1.0D);

		int wl = (int) Math.floor(wIdeal);

		if (wl % 2 == 0)
			wl--;

		int wu = wl + 2;

		double mIdeal = (12.0D * sigma * sigma - (n * wl * wl) - (4 * n * wl) - (3 * n)) / (-4 * wl - 4);

		int m = (int) Math.round(mIdeal);

		int[] sizes = new int[n];

		for (int i = 0; i < n; i++)
			sizes[i] = (i < m) ? wl : wu;

		return sizes;

	}

	private static void gaussBlur_4(int[] scl, int[] tcl, int w, int h, double r) {

		int[] bxs = boxesForGauss(r, 3);

		boxBlur_4(scl, tcl, w, h, ((bxs[0] - 1) / 2));

		boxBlur_4(tcl, scl, w, h, ((bxs[1] - 1) / 2));

		boxBlur_4(scl, tcl, w, h, ((bxs[2] - 1) / 2));

	}

	private static void boxBlur_4(int[] scl, int[] tcl, int w, int h, double r) {

		System.arraycopy(scl, 0, tcl, 0, scl.length);

		boxBlurH_4(tcl, scl, w, h, r);

		boxBlurT_4(scl, tcl, w, h, r);

	}

	private static void boxBlurH_4(int[] scl, int[] tcl, int w, int h, double r) {

		double iarr = 1.0D / (r + r + 1.0D);

		int ti;

		int li;

		int ri;

		int fv;

		int lv;

		double val;

		int j;

		for (int i = 0; i < h; i++) {

			ti = i * w;

			li = ti;

			ri = (int) (ti + r);

			fv = scl[ti];

			lv = scl[ti + w - 1];

			val = (r + 1.0D) * fv;

			for (j = 0; j < r; j++)
				val += scl[ti + j];

			for (j = 0; j <= r; j++) {

				val += (scl[ri++] - fv);

				tcl[ti++] = (int) Math.round(val * iarr);

			}

			for (j = (int) (r + 1.0D); j < w - r; j++) {

				val += (scl[ri++] - scl[li++]);

				tcl[ti++] = (int) Math.round(val * iarr);

			}

			for (j = (int) (w - r); j < w; j++) {

				val += (lv - scl[li++]);

				tcl[ti++] = (int) Math.round(val * iarr);

			}

		}

	}

	private static void boxBlurT_4(int[] scl, int[] tcl, int w, int h, double r) {

		double iarr = 1.0D / (r + r + 1.0D);

		int ti;

		int li;

		int ri;

		int fv;

		int lv;

		double val;

		int j;

		for (int i = 0; i < w; i++) {

			ti = i;

			li = ti;

			ri = (int) (ti + r * w);

			fv = scl[ti];

			lv = scl[ti + w * (h - 1)];

			val = (r + 1.0D) * fv;

			for (j = 0; j < r; j++)

				val += scl[ti + j * w];

			for (j = 0; j <= r; j++) {

				val += (scl[ri] - fv);

				tcl[ti] = (int) Math.round(val * iarr);

				ri += w;

				ti += w;

			}

			for (j = (int) (r + 1.0D); j < h - r; j++) {

				val += (scl[ri] - scl[li]);

				tcl[ti] = (int) Math.round(val * iarr);

				li += w;

				ri += w;

				ti += w;

			}

			for (j = (int) (h - r); j < h; j++) {

				val += (lv - scl[li]);

				tcl[ti] = (int) Math.round(val * iarr);

				li += w;

				ti += w;

			}

		}

	}

}
