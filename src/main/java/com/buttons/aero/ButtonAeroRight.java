package com.buttons.aero;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JToolTip;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class ButtonAeroRight extends JButton {

	private Image buttonHighlight;

	private float shadowOffsetX;

	private float shadowOffsetY;

	private Color colorDeSombra;

	private int direccionDeSombra;

	private int distanciaDeSombra;

	private boolean foco = false;

	private String text;

	private Color background;

	private Color foreground;

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

		this.background = background;

		this.foreground = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || background == null || foreground == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, background, foreground, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public ButtonAeroRight(Icon icon) {

		super(icon);

		colorDeSombra = new Color(0, 0, 0);

		direccionDeSombra = 60;

		distanciaDeSombra = 1;

		foco = false;

	}

	public ButtonAeroRight(String text) {

		colorDeSombra = new Color(0, 0, 0);

		direccionDeSombra = 60;

		distanciaDeSombra = 1;

		foco = false;

		setText(text);

		setOpaque(false);

		setContentAreaFilled(false);

		setFocusPainted(false);

		setBorderPainted(false);

		setFont(new Font("Dialog", Font.PLAIN, 20));

		setForeground(new Color(255, 255, 255));

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

	@Override
	protected void paintComponent(Graphics g) {

		computeShadow();

		Graphics2D g2 = (Graphics2D) g;

		Paint oldPaint = g2.getPaint();

		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0 - 10, 0, getWidth() + 10, getHeight(), 20, 20);

		g2.clip(r2d);

		ButtonModel modelo = getModel();

		Rectangle2D rect = new Rectangle2D.Double(0, 0, getWidth(), getHeight() / 2.0);

		if (modelo.isArmed() || modelo.isPressed())

			g2.setPaint(getBackground().darker());

		else

			g2.setPaint(getBackground());

		g2.fill(rect);

		rect = new Rectangle2D.Double(0, (getHeight() / 2.0) - 1.0, getWidth(), getHeight());

		if (modelo.isArmed() || modelo.isPressed())

			g2.setPaint(getBackground().darker().darker());

		else
			g2.setPaint(getBackground().darker());

		g2.fill(rect);

		if (modelo.isRollover() || foco) {

			g2.drawRect(0, 0, getWidth(), getHeight());

			g2.drawImage(buttonHighlight, 0, 0, getWidth(), getHeight(), null);

		}

		FontMetrics fm = getFontMetrics(getFont());

		TextLayout layout = new TextLayout(getText(), getFont(), g2.getFontRenderContext());

		Rectangle2D bounds = layout.getBounds();

		int x = (int) (getWidth() - bounds.getWidth()) / 2;

		int y = (getHeight() - fm.getMaxAscent() - fm.getMaxDescent()) / 2;

		y += fm.getAscent() - 1;

		if (modelo.isArmed()) {

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

	public Color getColorDeSombra() {

		return colorDeSombra;

	}

	public void setColorDeSombra(Color colorDeSombra) {

		this.colorDeSombra = colorDeSombra;

	}

	public int getDireccionDeSombra() {

		return direccionDeSombra;

	}

	public void setDireccionDeSombra(int direccionDeSombra) {

		this.direccionDeSombra = direccionDeSombra;

	}

	public int getDistanciaDeSombra() {

		return distanciaDeSombra;

	}

	public void setDistanciaDeSombra(int distanciaDeSombra) {

		this.distanciaDeSombra = distanciaDeSombra;

	}

}
