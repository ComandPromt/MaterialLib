package com.effects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import org.jdesktop.core.animation.timing.KeyFrames;

public class MaterialShadowCircle {

	public static final int OFFSET_TOP = 5;

	public static final int OFFSET_LEFT = 5;

	public static final int OFFSET_BOTTOM = 5;

	public static final int OFFSET_RIGHT = 5;

	private static KeyFrames<Float> opacity1 = (new KeyFrames.Builder(Float.valueOf(0.0F)))
			.addFrame(Float.valueOf(0.12F), 0.2D).addFrame(Float.valueOf(0.16F), 0.4D)
			.addFrame(Float.valueOf(0.19F), 0.6D).addFrame(Float.valueOf(0.25F), 0.8D)
			.addFrame(Float.valueOf(0.3F), 1.0D).build();

	private static KeyFrames<Float> opacity2 = (new KeyFrames.Builder(Float.valueOf(0.0F)))
			.addFrame(Float.valueOf(0.24F), 0.2D).addFrame(Float.valueOf(0.23F), 0.4D)
			.addFrame(Float.valueOf(0.23F), 0.6D).addFrame(Float.valueOf(0.22F), 0.8D)
			.addFrame(Float.valueOf(0.22F), 1.0D).build();

	private static KeyFrames<Float> radius1 = (new KeyFrames.Builder(Float.valueOf(0.0F)))
			.addFrame(Float.valueOf(3.0F), 0.2D).addFrame(Float.valueOf(6.0F), 0.4D)
			.addFrame(Float.valueOf(20.0F), 0.6D).addFrame(Float.valueOf(28.0F), 0.8D)
			.addFrame(Float.valueOf(38.0F), 1.0D).build();

	private static KeyFrames<Float> radius2 = (new KeyFrames.Builder(Float.valueOf(0.0F)))
			.addFrame(Float.valueOf(2.0F), 0.2D).addFrame(Float.valueOf(6.0F), 0.4D).addFrame(Float.valueOf(6.0F), 0.6D)
			.addFrame(Float.valueOf(10.0F), 0.8D).addFrame(Float.valueOf(12.0F), 1.0D).build();

	private static KeyFrames<Float> offset1 = (new KeyFrames.Builder(Float.valueOf(0.0F)))
			.addFrame(Float.valueOf(1.0F), 0.2D).addFrame(Float.valueOf(3.0F), 0.4D)
			.addFrame(Float.valueOf(10.0F), 0.6D).addFrame(Float.valueOf(14.0F), 0.8D)
			.addFrame(Float.valueOf(19.0F), 1.0D).build();

	private static KeyFrames<Float> offset2 = (new KeyFrames.Builder(Float.valueOf(0.0F)))
			.addFrame(Float.valueOf(1.0F), 0.2D).addFrame(Float.valueOf(3.0F), 0.4D).addFrame(Float.valueOf(6.0F), 0.6D)
			.addFrame(Float.valueOf(10.0F), 0.8D).addFrame(Float.valueOf(15.0F), 1.0D).build();

	public static BufferedImage renderShadow(int width, int height, double level) {

		if (level < 0.0D || level > 5.0D)

			throw new IllegalArgumentException("Shadow level must be between 1 and 5 (inclusive)");

		BufferedImage shadow = new BufferedImage(width, height, 2);

		if (width > 0 && height > 0 && level != 0.0D) {

			BufferedImage shadow2 = new BufferedImage(width, height, 2);

			Graphics2D g = shadow.createGraphics();

			g.setComposite(AlphaComposite.SrcOver);

			makeShadow(shadow, ((Float) opacity1.getInterpolatedValueAt(level / 5.0D)).floatValue(),
					((Float) radius1.getInterpolatedValueAt(level / 5.0D)).floatValue(), 0.0F,
					((Float) offset1.getInterpolatedValueAt(level / 5.0D)).floatValue());

			makeShadow(shadow2, ((Float) opacity2.getInterpolatedValueAt(level / 5.0D)).floatValue(),
					((Float) radius2.getInterpolatedValueAt(level / 5.0D)).floatValue(), 0.0F,
					((Float) offset2.getInterpolatedValueAt(level / 5.0D)).floatValue());

			g.drawImage(shadow2, 0, 0, (ImageObserver) null);

			g.dispose();

		}

		return shadow;

	}

	public static BufferedImage renderCircularShadow(int radius, double level) {

		if (level < 0.0D || level > 5.0D)

			throw new IllegalArgumentException("Shadow level must be between 1 and 5 (inclusive)");

		BufferedImage shadow = new BufferedImage(radius, radius + 5 + 5, 2);

		if (level != 0.0D) {

			BufferedImage shadow2 = new BufferedImage(radius, radius + 5 + 5, 2);

			Graphics2D g = shadow.createGraphics();

			g.setComposite(AlphaComposite.SrcOver);

			makeCircularShadow(shadow, ((Float) opacity1.getInterpolatedValueAt(level / 5.0D)).floatValue(),
					((Float) radius1.getInterpolatedValueAt(level / 5.0D)).floatValue(), 0.0F,
					((Float) offset1.getInterpolatedValueAt(level / 5.0D)).floatValue());

			makeCircularShadow(shadow2, ((Float) opacity2.getInterpolatedValueAt(level / 5.0D)).floatValue(),
					((Float) radius2.getInterpolatedValueAt(level / 5.0D)).floatValue(), 0.0F,
					((Float) offset2.getInterpolatedValueAt(level / 5.0D)).floatValue());

			g.drawImage(shadow2, 0, 0, (ImageObserver) null);

			g.dispose();

		}

		return shadow;

	}

	private static void makeShadow(BufferedImage shadow, float opacity, float radius, float leftOffset,
			float topOffset) {

		Graphics2D g2 = shadow.createGraphics();

		g2.setColor(new Color(0.0F, 0.0F, 0.0F, opacity));

		g2.fill(new RoundRectangle2D.Float(5.0F + leftOffset, 5.0F + topOffset, (shadow.getWidth() - 5 - 5),
				(shadow.getHeight() - 5 - 5), 3.0F, 3.0F));

		g2.dispose();

		FastGaussianBlur.blur(shadow, radius);

	}

	private static void makeCircularShadow(BufferedImage shadow, float opacity, float radius, float leftOffset,
			float topOffset) {

		Graphics2D g2 = shadow.createGraphics();

		g2.setColor(new Color(0.0F, 0.0F, 0.0F, opacity));

		g2.fill(new Ellipse2D.Double((5.0F + leftOffset), (5.0F + topOffset), (shadow.getWidth() - 5 - 5),
				(shadow.getWidth() - 5 - 5)));

		g2.dispose();

		FastGaussianBlur.blur(shadow, radius);

	}

}
