package textarea;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import scrollbar.ScrollBarCustom;

@SuppressWarnings("serial")
public class TextAreaScroll extends JScrollPane {

	private final Animator animator;

	private boolean animateHinText = true;

	private float animateLocation;

	private boolean show;

	private boolean mouseOver = false;

	private String labelText = "Label";

	private Font headerFont;

	private Color headerColor;

	private Color lineColor;

	private Color scrollForeground;

	private Color scrollBackground;

	private Color hintText;

	private ScrollBarCustom scrol;

	public void setHeaderFont(Font headerFont) {

		this.headerFont = headerFont;

	}

	public Color getLineColor() {

		return lineColor;

	}

	public void setHintText(Color hintText) {

		this.hintText = hintText;

	}

	public void setScroll(Color color) {

		this.scrollForeground = color;

	}

	public void setScrollBackground(Color scrollBackground) {

		this.scrollBackground = scrollBackground;

	}

	public Color getScrollForeground() {

		return scrollForeground;

	}

	public Color getScrollBackground() {

		return scrollBackground;

	}

	public TextAreaScroll() {

		headerColor = Color.BLACK;

		lineColor = new Color(3, 155, 216);

		setVerticalScrollBar(new ScrollBarCustom(null, null));

		scrol = new ScrollBarCustom(null, null);

		scrol.setOrientation(JScrollBar.HORIZONTAL);

		setHorizontalScrollBar(scrol);

		setBorder(new EmptyBorder(20, 3, 3, 3));

		TimingTarget target = new TimingTargetAdapter() {

			@Override

			public void timingEvent(float fraction) {

				setAnimateLocation(fraction);

				repaint();

			}

		};

		animator = new Animator(300, target);

		animator.setResolution(0);

		animator.setAcceleration(0.5f);

		animator.setDeceleration(0.5f);

		this.headerFont = new Font("Dialog", Font.PLAIN, 20);

	}

	void setColors(Color foreground, Color background) {

		this.scrollForeground = foreground;

		this.scrollBackground = background;

		setVerticalScrollBar(new ScrollBarCustom(foreground, background));

		scrol = new ScrollBarCustom(foreground, background);

		scrol.setOrientation(JScrollBar.HORIZONTAL);

		setHorizontalScrollBar(scrol);

	}

	@Override
	public void paint(Graphics grphcs) {

		super.paint(grphcs);

		Graphics2D g2 = (Graphics2D) grphcs;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

		int width = getWidth();

		int height = getHeight();

		if (mouseOver) {

			g2.setColor(lineColor);

		}

		else {

			g2.setColor(new Color(150, 150, 150));

		}

		g2.fillRect(2, height - 1, width - 4, 1);

		createHintText(g2);

		createLineStyle(g2);

		g2.dispose();

	}

	private void createHintText(Graphics2D g2) {

		Insets in = getInsets();

		g2.setColor(hintText);

		FontMetrics ft = g2.getFontMetrics();

		Rectangle2D r2 = ft.getStringBounds(labelText, g2);

		double height = in.top;

		double textY = (height - r2.getHeight()) / 2;

		double size;

		if (animateHinText) {

			if (show) {

				size = 18 * (1 - animateLocation);

			}

			else {

				size = 18 * animateLocation;

			}

		}

		else {

			size = 18;

		}

		if (labelText == null) {

			labelText = "";

		}

		try {

			g2.setFont(headerFont);

		}

		catch (Exception e) {

		}

		g2.setColor(headerColor);

		g2.drawString(labelText, in.right, (int) (in.top + textY + ft.getAscent() - size));

	}

	private void createLineStyle(Graphics2D g2) {

		double width = getWidth() - 4;

		int height = getHeight();

		g2.setColor(lineColor);

		double size;

		if (show) {

			size = width * (1 - animateLocation);

		}

		else {

			size = width * animateLocation;

		}

		double x = (width - size) / 2;

		g2.fillRect((int) (x + 2), height - 2, (int) size, 2);

	}

	protected Animator getAnimator() {

		return animator;

	}

	public float getAnimateLocation() {

		return animateLocation;

	}

	public void setAnimateLocation(float animateLocation) {

		this.animateLocation = animateLocation;

	}

	public boolean isMouseOver() {

		return mouseOver;

	}

	public boolean isAnimateHinText() {

		return animateHinText;

	}

	public void setAnimateHinText(boolean animateHinText) {

		this.animateHinText = animateHinText;

	}

	public String getLabelText() {

		return labelText;

	}

	public void setHeader(String labelText) {

		this.labelText = labelText;

	}

	public void setHeader(Color color) {

		this.headerColor = color;

	}

	public void setLineColor(Color lineColor) {

		this.lineColor = lineColor;

	}

	public boolean isShow() {

		return show;

	}

	public void setShow(boolean show) {

		this.show = show;

	}

}
