package com.buttons.onoff;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JToolTip;
import javax.swing.SwingUtilities;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.Interpolator;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class OnOffButton extends javax.swing.JPanel {

	private javax.swing.JLabel lbOff;

	private javax.swing.JLabel lbOn;

	private Color switchColor;

	private Color switchOffColor;

	private Color disableColor;

	private int borderSize;

	private int space;

	private int round;

	private boolean on;

	private List<SwitchListener> events;

	private Animator animator;

	private float animate;

	private boolean mouseHover;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

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

	public Color getSwitchOffColor() {

		return switchOffColor;

	}

	public void setSwitchOffColor(Color switchOffColor) {

		this.switchOffColor = switchOffColor;

		repaint();

	}

	public Color getSwitchColor() {

		return switchColor;

	}

	public void setSwitchColor(Color switchColor) {

		this.switchColor = switchColor;

		repaint();

	}

	public Color getDisableColor() {

		return disableColor;

	}

	public void setDisableColor(Color disableColor) {

		this.disableColor = disableColor;

		repaint();

	}

	public int getBorderSize() {

		return borderSize;

	}

	public void setBorderSize(int borderSize) {

		this.borderSize = borderSize;

		repaint();

	}

	public int getSpace() {

		return space;

	}

	public void setSpace(int space) {

		this.space = space;

		repaint();

	}

	public int getRound() {

		return round;

	}

	public void setRound(int round) {

		this.round = round;

		repaint();

	}

	public boolean isOn() {

		return on;

	}

	public void setOn(boolean on) {

		this.on = on;

		runEvent();

		if (on) {

			animate = 0;

		}

		else {

			animate = 1;

		}

		repaint();

	}

	public void setOn(boolean on, boolean animate) {

		if (animate) {

			start(on);

		}

		else {

			setOn(on);

		}

	}

	public void addEventSwitchSelected(SwitchListener event) {

		events.add(event);

	}

	public OnOffButton() {

		switchColor = new Color(22, 160, 255);

		switchOffColor = new Color(190, 190, 190);

		disableColor = new Color(190, 190, 190);

		borderSize = 2;

		space = 2;

		round = 5;

		on = true;

		events = new ArrayList<>();

		initComponents();

		init();

	}

	private void init() {

		setOpaque(false);

		setBackground(new Color(255, 255, 255));

		setForeground(new Color(220, 220, 220));

		initAnimator();

		initMouseEvent();

	}

	private void initMouseEvent() {

		MouseAdapter mouseAdapter = new MouseAdapter() {

			@Override

			public void mouseEntered(MouseEvent e) {

				mouseHover = true;

			}

			@Override
			public void mouseExited(MouseEvent e) {

				mouseHover = false;

			}

			@Override
			public void mouseReleased(MouseEvent e) {

				if (mouseHover && isEnabled() && SwingUtilities.isLeftMouseButton(e)) {

					setOn(!on, true);

					runEvent();

				}

			}

		};

		addMouseListener(mouseAdapter);

	}

	private void initAnimator() {

		animator = new Animator(1000, new TimingTargetAdapter() {

			@Override
			public void timingEvent(float fraction) {

				if (!on) {

					animate = fraction;

				}

				else {

					animate = 1f - fraction;

				}

				repaint();

			}

		});

		animator.setResolution(0);

		animator.setInterpolator(new Interpolator() {
			@Override

			public float interpolate(float f) {

				return easeOutBounce(f);

			}

		});

	}

	private float easeOutBounce(float x) {

		double n1 = 7.5625;

		double d1 = 2.75;

		double v;

		if (x < 1 / d1) {

			v = n1 * x * x;

		}

		else if (x < 2 / d1) {

			v = n1 * (x -= 1.5 / d1) * x + 0.75;

		}

		else if (x < 2.5 / d1) {

			v = n1 * (x -= 2.25 / d1) * x + 0.9375;

		}

		else {

			v = n1 * (x -= 2.625 / d1) * x + 0.984375;

		}

		return (float) v;

	}

	private void runEvent() {

		for (SwitchListener event : events) {

			event.selectChange(on);

		}

	}

	private void start(boolean isOn) {

		if (animator.isRunning()) {

			float f = animator.getTimingFraction();

			animator.stop();

			float t = 1f - f;

			if (t > 1) {

				t = 1;
			}

			else if (t < 0) {

				t = 0;

			}

			animator.setStartFraction(t);

		}

		else {

			animator.setStartFraction(0f);

		}

		on = isOn;

		animator.start();

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int width = getWidth();

		int height = getHeight();

		createBorder(g2, width, height);

		createSwitch(g2, width, height);

		g2.dispose();

	}

	private void createBorder(Graphics2D g2, int width, int height) {

		int r = round == 999 ? height : round;

		Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, r, r));

		r = round == 999 ? height - borderSize * 2 : round;

		area.subtract(new Area(new RoundRectangle2D.Double(borderSize, borderSize, width - borderSize * 2,

				height - borderSize * 2, r, r)));

		g2.setColor(isEnabled() ? EvaluatorColor.evaluate(switchColor, switchOffColor, animate) : disableColor);

		g2.fill(area);

		double size = Math.max(width, height);

		size += size * 0.5f;

		double x = (width - size) / 2;

		double y = (height - size) / 2;

		area.intersect(new Area(new Arc2D.Double(x, y, size, size, -90, (1f - animate) * 360, Arc2D.PIE)));

		g2.fill(area);

	}

	private void createSwitch(Graphics2D g2, int width, int height) {

		int size = width / 2;

		int spaceSize = borderSize + space;

		int r = round == 999 ? height - spaceSize * 2 : round;

		Area area = new Area(new RoundRectangle2D.Double(spaceSize + size * animate, spaceSize, size - spaceSize * 2,
				height - spaceSize * 2, r, r));

		area.intersect(new Area(new RoundRectangle2D.Double(borderSize, borderSize, width - borderSize * 2,
				height - borderSize * 2, r, r)));

		g2.setColor(isEnabled() ? EvaluatorColor.evaluate(switchColor, switchOffColor, animate) : disableColor);

		g2.fill(area);

	}

	private void initComponents() {

		lbOn = new javax.swing.JLabel();

		lbOff = new javax.swing.JLabel();

		lbOn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		lbOn.setText("ON");

		lbOff.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		lbOff.setText("OFF");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);

		this.setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addComponent(lbOn, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(lbOff, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE).addGap(0, 0, 0)));

		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lbOff, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
								.addComponent(lbOn, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(0, 0, 0)));
	}

	@Override
	public void setForeground(Color fg) {

		super.setForeground(fg);

		if (lbOn != null) {

			lbOn.setForeground(fg);

			lbOff.setForeground(fg);

		}

	}

	@Override
	public void setFont(Font font) {

		super.setFont(font);

		if (lbOn != null) {

			lbOn.setFont(font);

			lbOff.setFont(font);

		}

	}

}
