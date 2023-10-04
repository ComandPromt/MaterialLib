package com.textField.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import com.contextmenu.DefaultContextMenu;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class NTextField extends JTextField {

	private final Animator animator;

	private boolean animateHinText;

	private float location;

	private boolean show;

	private boolean mouseOver;

	private String labelText;

	private Color lineColor;

	private boolean center;

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

				font = getFont().deriveFont(14f);

			}

			catch (Exception e) {

				font = new Font("Dialog", Font.PLAIN, 14);

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

	public void setCenterText(boolean center) {

		this.center = center;

	}

	public String getLabelText() {

		return labelText;

	}

	public void setLabelText(String labelText) {

		this.labelText = labelText;

	}

	public Color getLineColor() {

		return lineColor;

	}

	public void setLineColor(Color lineColor) {

		this.lineColor = lineColor;

	}

	public NTextField() {

		animateHinText = true;

		mouseOver = false;

		labelText = "";

		lineColor = new Color(3, 155, 216);

		center = false;

		setFont(getFont().deriveFont(30f));

		setBorder(new EmptyBorder(20, 3, 10, 3));

		setSelectionColor(new Color(76, 204, 255));

		addMouseListener(new MouseAdapter() {

			@Override

			public void mouseEntered(MouseEvent me) {

				mouseOver = true;

				repaint();

			}

			@Override

			public void mouseExited(MouseEvent me) {

				mouseOver = false;

				repaint();

			}

		});

		addFocusListener(new FocusAdapter() {

			@Override

			public void focusGained(FocusEvent fe) {

				showing(false);

			}

			@Override

			public void focusLost(FocusEvent fe) {

				showing(true);

			}

		});

		TimingTarget target = new TimingTargetAdapter() {

			@Override

			public void begin() {

				animateHinText = getText().equals("");

			}

			@Override

			public void timingEvent(float fraction) {

				location = fraction;

				repaint();

			}

		};

		animator = new Animator(300, target);

		animator.setResolution(0);

		animator.setAcceleration(0.5f);

		animator.setDeceleration(0.5f);

		DefaultContextMenu.addDefaultContextMenu(this);

	}

	public NTextField(Color selectionColor) {

		if (selectionColor == null) {

			selectionColor = new Color(76, 204, 255);

		}

		animateHinText = true;

		mouseOver = false;

		labelText = "";

		lineColor = new Color(3, 155, 216);

		center = false;

		setBorder(new EmptyBorder(20, 3, 10, 3));

		setSelectionColor(selectionColor);

		addMouseListener(new MouseAdapter() {

			@Override

			public void mouseEntered(MouseEvent me) {

				mouseOver = true;

				repaint();

			}

			@Override

			public void mouseExited(MouseEvent me) {

				mouseOver = false;

				repaint();

			}

		});

		addFocusListener(new FocusAdapter() {

			@Override

			public void focusGained(FocusEvent fe) {

				showing(false);

			}

			@Override

			public void focusLost(FocusEvent fe) {

				showing(true);

			}

		});

		TimingTarget target = new TimingTargetAdapter() {

			@Override

			public void begin() {

				animateHinText = getText().equals("");

			}

			@Override

			public void timingEvent(float fraction) {

				location = fraction;

				repaint();

			}

		};

		animator = new Animator(300, target);

		animator.setResolution(0);

		animator.setAcceleration(0.5f);

		animator.setDeceleration(0.5f);

	}

	private void showing(boolean action) {

		if (animator.isRunning()) {

			animator.stop();

		}

		else {

			location = 1;

		}

		animator.setStartFraction(1f - location);

		show = action;

		location = 1f - location;

		animator.start();

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

		g2.setColor(new Color(150, 150, 150));

		FontMetrics ft = g2.getFontMetrics();

		Rectangle2D r2 = ft.getStringBounds(labelText, g2);

		double height = getHeight() - in.top - in.bottom;

		double textY = (height - r2.getHeight()) / 2;

		double size;

		if (animateHinText) {

			if (show) {

				size = 18 * (1 - location);

			}

			else {

				size = 18 * location;

			}

		}

		else {

			size = 18;

		}

		if (center) {

			int contador = labelText.length() * 4;

			g2.drawString(labelText, (getWidth() / 2) - contador, (int) (in.top + textY + ft.getAscent() - size));

		}

		else {

			g2.drawString(labelText, in.right, (int) (in.top + textY + ft.getAscent() - size));

		}

	}

	private void createLineStyle(Graphics2D g2) {

		if (isFocusOwner()) {

			double width = getWidth() - 4;

			int height = getHeight();

			g2.setColor(lineColor);

			double size;

			if (show) {

				size = width * (1 - location);

			}

			else {

				size = width * location;

			}

			double x = (width - size) / 2;

			g2.fillRect((int) (x + 2), height - 2, (int) size, 2);

		}

	}

	@Override
	public void setText(String string) {

		if (!getText().equals(string)) {

			showing(string.equals(""));

		}

		super.setText(string);

	}

}
