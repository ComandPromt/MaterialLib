package com.buttons.shadow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JToolTip;
import javax.swing.plaf.basic.BasicButtonUI;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class AquaButton extends JButton {

	private MouseAdapter ml;

	protected Modelo modelo;

	private Dimension buttonDimension;

	private boolean foco;

	private Color colorDeSombra;

	private int direccionDeSombra;

	private int distanciaDeSombra;

	private float shadowOffsetX;

	private float shadowOffsetY;

	private Image buttonHighlight;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private int angulo;

	public int getAngulo() {

		return angulo;

	}

	public void setAngulo(int angulo) {

		if (angulo < 0) {

			angulo = 0;

		}

		this.angulo = angulo;

		repaint();

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

	public enum Modelo {

		ROUND, RECT, ROUND_LEFT, ROUND_RIGHT, ROUND_TOP, ROUND_BOTTOM

	}

	public AquaButton(String text) {

		setFont(getFont().deriveFont(Font.PLAIN, 30f));

		modelo = Modelo.ROUND;

		buttonDimension = new Dimension(116, 35);

		foco = false;

		colorDeSombra = new Color(0, 0, 0);

		direccionDeSombra = 60;

		distanciaDeSombra = 1;

		setText(text);

		setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

		setOpaque(false);

		setContentAreaFilled(false);

		setFocusPainted(false);

		setForeground(Color.WHITE);

		setBackground(Color.blue);

		setSize(new Dimension(242, 64));

		setPreferredSize(new Dimension(242, 64));

		setUI(new BasicButtonUI() {

			@Override
			public Dimension getMinimumSize(JComponent c) {

				return getPreferredSize(c);

			}

			@Override
			public Dimension getMaximumSize(JComponent c) {

				return getPreferredSize(c);

			}

			@Override
			public Dimension getPreferredSize(JComponent c) {

				Insets insets = c.getInsets();

				Dimension d = new Dimension(buttonDimension);

				d.width += insets.left + insets.right;

				d.height += insets.top + insets.bottom;

				return d;

			}

		});

		addMouseListener(ml);

		addFocusListener(new FocusListener() {

			@Override

			public void focusGained(FocusEvent e) {

				foco = true;

			}

			@Override
			public void focusLost(FocusEvent e) {

				foco = false;

			}

		});

	}

	private void computeShadow() {

		double rads = Math.toRadians(direccionDeSombra);

		shadowOffsetX = (float) Math.cos(rads) * distanciaDeSombra;

		shadowOffsetY = (float) Math.sin(rads) * distanciaDeSombra;

	}

	public Color getColorDeSombra() {

		return colorDeSombra;

	}

	public void setColorDeSombra(Color colorDeSombra) {

		this.colorDeSombra = colorDeSombra;

		repaint();

	}

	public int getDireccionDeSombra() {

		return direccionDeSombra;

	}

	public void setDireccionDeSombra(int direccionDeSombra) {

		this.direccionDeSombra = direccionDeSombra;

		repaint();

	}

	public int getDistanciaDeSombra() {

		return distanciaDeSombra;

	}

	public void setDistanciaDeSombra(int distanciaDeSombra) {

		this.distanciaDeSombra = distanciaDeSombra;

		repaint();

	}

	@Override
	protected void paintComponent(Graphics g) {

		computeShadow();

		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Paint oldPaint = g2.getPaint();

		g2.clip(getShape(0));

		ButtonModel buttonModel = getModel();

		if (buttonModel.isArmed() || buttonModel.isPressed()) {

			g2.setPaint(gradienteInv());

			g2.fill(getShape(0));

			g2.setPaint(gradiente2Inv());

			g2.fill(getShape(1));

		}

		else {

			g2.setPaint(gradiente1());

			g2.fill(getShape(0));

			g2.setPaint(gradiente2());

			g2.fill(getShape(1));

		}

		if (buttonModel.isRollover() || foco) {

			g2.drawImage(buttonHighlight, 0, 3, getWidth(), getHeight() - 3, null);

		}

		FontMetrics fm = getFontMetrics(getFont());

		TextLayout layout = new TextLayout(getText(), getFont(), g2.getFontRenderContext());

		Rectangle2D bounds = layout.getBounds();

		int x = (int) (getWidth() - bounds.getWidth()) / 2;

		int y = (getHeight() - fm.getMaxAscent() - fm.getMaxDescent()) / 2;

		y += fm.getAscent() - 1;

		if (buttonModel.isArmed()) {

			x += 1;

			y += 1;

		}

		g2.setColor(colorDeSombra);

		layout.draw(g2, x + (int) Math.ceil(shadowOffsetX), y + (int) Math.ceil(shadowOffsetY));

		if (isEnabled())

			g2.setColor(getForeground());

		else

			g2.setColor(getForeground().darker());

		layout.draw(g2, x, y);

		g2.setPaint(oldPaint);

	}

	protected Shape getShape(int v) {

		switch (modelo) {

		case RECT:

			return new Rectangle2D.Float(0 + v, 0 + v, getWidth() - v - v, getHeight() - v - v);

		case ROUND:

			return new RoundRectangle2D.Float(0 + v, 0 + v, getWidth() - v - v, getHeight() - v - v, angulo,

					angulo);

		case ROUND_BOTTOM:

			return new RoundRectangle2D.Float(0 + v, 0 - 30 + v, getWidth() - v - v, getHeight() + 30 - v - v, angulo,
					angulo);

		case ROUND_LEFT:

			return new RoundRectangle2D.Float(0 + v, 0 + v, getWidth() + 30 - v - v, getHeight() - v - v, angulo,
					angulo);

		case ROUND_TOP:

			return new RoundRectangle2D.Float(0 + v, 0 + v, getWidth() - v - v, getHeight() + 30 - v - v, angulo,
					angulo);

		case ROUND_RIGHT:

			return new RoundRectangle2D.Float(0 - 30 + v, 0 + v, getWidth() + 30 - v - v, getHeight() - v - v, angulo,
					angulo);

		default:

			return new Rectangle2D.Float(0, 0, getWidth(), getHeight());

		}

	}

	protected Paint gradiente1() {

		return new LinearGradientPaint(0, 0, 0, getHeight(), new float[] { 0f, 1f },
				new Color[] { getBackground().darker(), getBackground().brighter() });

	}

	private Paint gradiente2() {

		return new LinearGradientPaint(0, 0, 0, getHeight(), new float[] { 0f, .5f, 1f },
				new Color[] { Color.white, getBackground().brighter(), getBackground().brighter().brighter() });

	}

	protected Paint gradienteInv() {

		return new LinearGradientPaint(0, 0, 0, getHeight(), new float[] { 0f, 1f },
				new Color[] { getBackground().brighter(), getBackground().darker() });

	}

	private Paint gradiente2Inv() {

		return new LinearGradientPaint(0, 0, 0, getHeight(), new float[] { 0f, .5f, 1f },
				new Color[] { getBackground().brighter().brighter(), getBackground().brighter(), Color.white });

	}

	public Modelo getModelo() {

		return modelo;

	}

	public void setModelo(Modelo modelo) {

		this.modelo = modelo;

	}

}
