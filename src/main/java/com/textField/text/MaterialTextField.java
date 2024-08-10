package com.textField.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.text.DefaultCaret;

import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.interpolators.SplineInterpolator;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;

import com.colores.Colores;
import com.contextmenu.DefaultContextMenu;
import com.material.utils.SafePropertySetter;
import com.material.utils.Utils;
import com.toolTip.ToolTipLlamada;

public class MaterialTextField extends JTextField {

	private static final long serialVersionUID = 1L;

	public static final int HINT_OPACITY_MASK = 0x99000000;

	public static final int LINE_OPACITY_MASK = 0x66000000;

	private FloatingLabel floatingLabel;

	private Line line;

	private String hint;

	private Color accentColor;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private Color hintColor;

	private Font hintFont;

	public void setPlaceholderFocusOff(Color color) {

		try {

			floatingLabel.setPlaceholderFocusOff(color);

		}

		catch (Exception e) {

		}

	}

	public void setHintFont(Font hintFont) {

		try {

			this.hintFont = hintFont;

			repaint();

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setToolTipText(String text) {

		setToolTip(text, null, null, null, null);

	}

	public void setToolTip(String text, Color background, Color foreground, Color border, Font font) {

		calcularTooltip(text, background, foreground, border, font);

	}

	private void calcularTooltip(String text, Color background, Color foreground, Color border, Font font) {

		if (background == null) {

			background = new Color(32, 39, 55);

		}

		if (foreground == null) {

			foreground = Color.WHITE;

		}

		if (border == null) {

			border = new Color(173, 173, 173);

		}

		if (font == null) {

			try {

				font = getFont().deriveFont(20f);

			}

			catch (Exception e) {

				font = new Font("Dialog", Font.PLAIN, 20);

			}

		}

		this.text = text;

		this.fondo = background;

		this.colorTexto = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || fondo == null || colorTexto == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, colorTexto, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public MaterialTextField() {

		super();

		hintFont = getFont().deriveFont(20f);

		hintColor = Color.LIGHT_GRAY;

		floatingLabel = new FloatingLabel(this);

		line = new Line(this);

		hint = "";

		accentColor = Colores.CYAN_500;

		setBorder(null);

		setBackground(Color.WHITE);

		setFont(getFont().deriveFont(Font.PLAIN, 20f));

		floatingLabel.setText("");

		setOpaque(false);

		setBackground(Colores.TRANSPARENT);

		setCaret(new DefaultCaret() {

			private static final long serialVersionUID = 1L;

			@Override
			protected synchronized void damage(Rectangle r) {

				this.repaint();

			}

		});

		getCaret().setBlinkRate(500);

		DefaultContextMenu.addDefaultContextMenu(this);

	}

	public MaterialTextField(String text) {

		this();

		setText(text);

	}

	public String getLabel() {

		return floatingLabel.getText();

	}

	public void setLabelFont(Font font) {

		try {

			floatingLabel.setFont(font);

			repaint();

		}

		catch (Exception e) {

		}

	}

	public void setLabel(String label) {

		try {

			floatingLabel.setText(label);

			repaint();

		} catch (Exception e) {

		}

	}

	public String getHint() {

		return hint;

	}

	public void setHint(String hint) {

		try {

			this.hint = hint;

			repaint();

		} catch (Exception e) {

		}

	}

	public Color getAccent() {

		return accentColor;

	}

	public void setAccent(Color accentColor) {

		try {

			this.accentColor = accentColor;

			floatingLabel.setAccent(accentColor);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setForeground(Color fg) {

		super.setForeground(fg);

		if (floatingLabel != null)
			floatingLabel.updateForeground();

	}

	@Override
	public void setText(String s) {

		super.setText(s);

		try {

			floatingLabel.update();

			line.update();

		} catch (Exception e) {

		}

	}

	@Override
	protected void processFocusEvent(FocusEvent e) {

		super.processFocusEvent(e);

		floatingLabel.update();

		line.update();

	}

	@Override
	protected void processKeyEvent(KeyEvent e) {

		super.processKeyEvent(e);

		floatingLabel.update();

		line.update();

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g2.setColor(getBackground());

		g2.fillRect(0, 0, getWidth(), getHeight());

		g2.translate(0, 9);

		super.paintComponent(g);

		g2.translate(0, -9);

		if (!getHint().isEmpty() && getText().isEmpty() && (getLabel().isEmpty() || isFocusOwner())
				&& floatingLabel.isFloatingAbove()) {

			g2.setFont(hintFont);

			g2.setColor(Utils.applyAlphaMask(hintColor, HINT_OPACITY_MASK));

			FontMetrics metrics = g.getFontMetrics(g.getFont());

			g.drawString(getHint(), 0, metrics.getAscent() + 36);

		}

		floatingLabel.paint(g2);

		g2.setColor(Utils.applyAlphaMask(getForeground(), LINE_OPACITY_MASK));

		g2.fillRect(0, getHeight() - 9, getWidth(), 1);

		g2.setColor(accentColor);

		g2.fillRect((int) ((getWidth() - line.getWidth()) / 2), getHeight() - 10, (int) line.getWidth(), 2);

	}

	public static class Line {

		private final SwingTimerTimingSource timer;

		private final JComponent target;

		private Animator animator;

		private SafePropertySetter.Property<Double> width;

		public Line(JComponent target) {

			this.target = target;

			this.timer = new SwingTimerTimingSource();

			timer.init();

			width = SafePropertySetter.animatableProperty(target, 0d);

		}

		public void update() {

			if (animator != null) {

				animator.stop();

			}

			animator = new Animator.Builder(timer).setDuration(200, TimeUnit.MILLISECONDS)
					.setEndBehavior(Animator.EndBehavior.HOLD).setInterpolator(new SplineInterpolator(0.4, 0, 0.2, 1))
					.addTarget(SafePropertySetter.getTarget(width, width.getValue(),
							target.isFocusOwner() ? (double) target.getWidth() + 1 : 0d))
					.build();

			animator.start();

		}

		public double getWidth() {

			return width.getValue();

		}

	}

	public static class FloatingLabel {

		private final SwingTimerTimingSource timer;

		private final JTextField target;

		private Animator animator;

		private final SafePropertySetter.Property<Double> y;

		private final SafePropertySetter.Property<Double> fontSize;

		private final SafePropertySetter.Property<Color> color;

		private String text;

		private Color accentColor = Colores.CYAN_500;

		private Font font;

		private Color placeholderFocusOff;

		public void setPlaceholderFocusOff(Color placeholderFocusOff) {

			this.placeholderFocusOff = placeholderFocusOff;

		}

		public void setFont(Font font) {

			try {

				this.font = font;

			} catch (Exception e) {

			}
		}

		public FloatingLabel(JTextField target) {

			this.target = target;

			this.timer = new SwingTimerTimingSource();

			timer.init();

			y = SafePropertySetter.animatableProperty(target, 36d);

			fontSize = SafePropertySetter.animatableProperty(target, 16d);

			color = SafePropertySetter.animatableProperty(target, Colores.MIN_BLACK);

			placeholderFocusOff = Color.BLACK;

			updateForeground();

		}

		public void updateForeground() {

			color.setValue(Utils.applyAlphaMask(target.getForeground(), HINT_OPACITY_MASK));

		}

		public Color getAccent() {

			return accentColor;

		}

		public void setAccent(Color accentColor) {

			this.accentColor = accentColor;

		}

		public void update() {

			if (animator != null) {

				animator.stop();

			}

			Animator.Builder builder = new Animator.Builder(timer).setDuration(200, TimeUnit.MILLISECONDS)
					.setEndBehavior(Animator.EndBehavior.HOLD).setInterpolator(new SplineInterpolator(0.4, 0, 0.2, 1));

			double targetFontSize = (target.isFocusOwner() || !target.getText().isEmpty()) ? 12d : 16d;

			if (fontSize.getValue() != targetFontSize) {

				builder.addTarget(SafePropertySetter.getTarget(fontSize, fontSize.getValue(), targetFontSize));

			}

			double targetY = target.isFocusOwner() || !target.getText().isEmpty() ? 16d : 36d;

			if (Math.abs(targetY - y.getValue()) > 0.1) {

				builder.addTarget(SafePropertySetter.getTarget(y, y.getValue(), targetY));

			}

			Color targetColor;

			if (target.isFocusOwner()) {

				targetColor = accentColor;

			}

			else {

				targetColor = Utils.applyAlphaMask(placeholderFocusOff, HINT_OPACITY_MASK);

			}

			if (!targetColor.equals(color.getValue())) {

				builder.addTarget(SafePropertySetter.getTarget(color, color.getValue(), targetColor));

			}

			animator = builder.build();

			animator.start();

		}

		public String getText() {

			return text;

		}

		public void setText(String text) {

			this.text = text;

		}

		public void paint(Graphics2D g) {

			g.setColor(color.getValue());

			FontMetrics metrics = g.getFontMetrics(g.getFont());

			g.setFont(font);

			g.drawString(getText(), 0, metrics.getAscent() + y.getValue().intValue());

		}

		public boolean isFloatingAbove() {

			return y.getValue() < 17d;

		}

	}

	public void setHintColor(Color color) {

		try {

			hintColor = color;

			repaint();

		}

		catch (Exception e) {

		}

	}

}