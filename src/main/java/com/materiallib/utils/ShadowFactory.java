package com.materiallib.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ConvolveOp;
import java.awt.image.DataBufferInt;
import java.awt.image.Kernel;
import java.awt.image.WritableRaster;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

public class ShadowFactory {

	public static final String KEY_BLUR_QUALITY = "blur_quality";

	public static final String VALUE_BLUR_QUALITY_FAST = "fast";

	public static final String VALUE_BLUR_QUALITY_HIGH = "high";

	public static final String SIZE_CHANGED_PROPERTY = "shadow_size";

	public static final String OPACITY_CHANGED_PROPERTY = "shadow_opacity";

	public static final String COLOR_CHANGED_PROPERTY = "shadow_color";

	private int size = 5;

	private float opacity = 0.5f;

	private Color color = Color.BLACK;

	private HashMap<Object, Object> hints;

	private PropertyChangeSupport changeSupport;

	public ShadowFactory() {

		this(5, 0.5f, Color.BLACK);

	}

	public ShadowFactory(final int size, final float opacity, final Color color) {

		hints = new HashMap<Object, Object>();

		hints.put(KEY_BLUR_QUALITY, VALUE_BLUR_QUALITY_FAST);

		changeSupport = new PropertyChangeSupport(this);

		setSize(size);

		setOpacity(opacity);

		setColor(color);

	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {

		changeSupport.addPropertyChangeListener(listener);

	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {

		changeSupport.removePropertyChangeListener(listener);

	}

	public void setRenderingHint(final Object key, final Object value) {

		hints.put(key, value);

	}

	public Color getColor() {

		return color;

	}

	public void setColor(final Color shadowColor) {

		if (shadowColor != null) {

			Color oldColor = this.color;

			this.color = shadowColor;

			changeSupport.firePropertyChange(COLOR_CHANGED_PROPERTY, oldColor, this.color);

		}

	}

	public float getOpacity() {

		return opacity;

	}

	public void setOpacity(final float shadowOpacity) {

		float oldOpacity = this.opacity;

		if (shadowOpacity < 0.0) {

			this.opacity = 0.0f;

		}

		else if (shadowOpacity > 1.0f) {

			this.opacity = 1.0f;

		}

		else {

			this.opacity = shadowOpacity;

		}

		changeSupport.firePropertyChange(OPACITY_CHANGED_PROPERTY, oldOpacity, this.opacity);

	}

	public int getSize() {

		return size;

	}

	public void setSize(final int shadowSize) {

		int oldSize = this.size;

		if (shadowSize < 0) {

			this.size = 0;

		}

		else {

			this.size = shadowSize;

		}

		changeSupport.firePropertyChange(SIZE_CHANGED_PROPERTY, oldSize, this.size);

	}

	public BufferedImage createShadow(final BufferedImage image) {

		if (hints.get(KEY_BLUR_QUALITY) == VALUE_BLUR_QUALITY_HIGH) {

			BufferedImage subject = prepareImage(image);

			BufferedImage shadow = new BufferedImage(subject.getWidth(), subject.getHeight(),
					BufferedImage.TYPE_INT_ARGB);

			BufferedImage shadowMask = createShadowMask(subject);

			getLinearBlurOp(size).filter(shadowMask, shadow);

			return shadow;

		}

		return createShadowFast(image);

	}

	private BufferedImage prepareImage(final BufferedImage image) {

		BufferedImage subject = new BufferedImage(image.getWidth() + size * 2, image.getHeight() + size * 2,
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = subject.createGraphics();

		g2.drawImage(image, null, size, size);

		g2.dispose();

		return subject;

	}

	private BufferedImage createShadowFast(final BufferedImage src) {

		int shadowSize = this.size;

		int srcWidth = src.getWidth();

		int srcHeight = src.getHeight();

		int dstWidth = srcWidth + size;

		int dstHeight = srcHeight + size;

		int left = (shadowSize - 1) >> 1;

		int right = shadowSize - left;

		int yStop = dstHeight - right;

		BufferedImage dst = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_ARGB);

		int shadowRgb = color.getRGB() & 0x00FFFFFF;

		int[] aHistory = new int[shadowSize];

		int historyIdx;

		int aSum;

		ColorModel srcColorModel = src.getColorModel();

		WritableRaster srcRaster = src.getRaster();

		int[] dstBuffer = ((DataBufferInt) dst.getRaster().getDataBuffer()).getData();

		int lastPixelOffset = right * dstWidth;

		float hSumDivider = 1.0f / size;

		float vSumDivider = opacity / size;

		for (int srcY = 0, dstOffset = left * dstWidth; srcY < srcHeight; srcY++) {

			for (historyIdx = 0; historyIdx < shadowSize;) {

				aHistory[historyIdx++] = 0;

			}

			aSum = 0;

			historyIdx = 0;

			for (int srcX = 0; srcX < srcWidth; srcX++) {

				int a = (int) (aSum * hSumDivider);

				dstBuffer[dstOffset++] = a << 24;

				aSum -= aHistory[historyIdx];

				a = srcColorModel.getAlpha(srcRaster.getDataElements(srcX, srcY, null));

				aHistory[historyIdx] = a;

				aSum += a;

				if (++historyIdx >= shadowSize) {

					historyIdx -= shadowSize;

				}

			}

			for (int i = 0; i < shadowSize; i++) {

				int a = (int) (aSum * hSumDivider);

				dstBuffer[dstOffset++] = a << 24;

				aSum -= aHistory[historyIdx];

				if (++historyIdx >= shadowSize) {

					historyIdx -= shadowSize;

				}

			}

		}

		for (int x = 0, bufferOffset = 0; x < dstWidth; x++, bufferOffset = x) {

			aSum = 0;

			for (historyIdx = 0; historyIdx < left;) {

				aHistory[historyIdx++] = 0;

			}

			for (int y = 0; y < right; y++, bufferOffset += dstWidth) {

				int a = dstBuffer[bufferOffset] >>> 24;

				aHistory[historyIdx++] = a;

				aSum += a;

			}

			bufferOffset = x;

			historyIdx = 0;

			for (int y = 0; y < yStop; y++, bufferOffset += dstWidth) {

				int a = (int) (aSum * vSumDivider);

				dstBuffer[bufferOffset] = a << 24 | shadowRgb;

				aSum -= aHistory[historyIdx];

				a = dstBuffer[bufferOffset + lastPixelOffset] >>> 24;

				aHistory[historyIdx] = a;

				aSum += a;

				if (++historyIdx >= shadowSize) {

					historyIdx -= shadowSize;

				}

			}

			for (int y = yStop; y < dstHeight; y++, bufferOffset += dstWidth) {

				int a = (int) (aSum * vSumDivider);

				dstBuffer[bufferOffset] = a << 24 | shadowRgb;

				aSum -= aHistory[historyIdx];

				if (++historyIdx >= shadowSize) {

					historyIdx -= shadowSize;

				}

			}

		}

		return dst;

	}

	private BufferedImage createShadowMask(final BufferedImage image) {
		BufferedImage mask = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = mask.createGraphics();

		g2d.drawImage(image, 0, 0, null);

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, opacity));

		g2d.setColor(color);

		g2d.fillRect(0, 0, image.getWidth(), image.getHeight());

		g2d.dispose();

		return mask;

	}

	private ConvolveOp getLinearBlurOp(final int size) {

		float[] data = new float[size * size];

		float value = 1.0f / (float) (size * size);

		for (int i = 0; i < data.length; i++) {

			data[i] = value;

		}

		return new ConvolveOp(new Kernel(size, size, data));

	}

}
