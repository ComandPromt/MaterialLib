
package com.search;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;

import com.buttons.shadow.ShadowButton;
import com.contextmenu.DefaultContextMenu;
import com.jicons.Lupa;
import com.toolTip.ToolTipLlamada;

import mthos.JMthos;

@SuppressWarnings("serial")
public class BootSearchInputWithButton extends JTextField {

	private int angle;

	private Color borderColor;

	private int thickness;

	private Lupa magnifyingGlassIcon;

	private ImageIcon icono;

	private int margin;

	private String placeholder;

	private Color placeholderColor;

	private Font placeholderFont;

	private int placeholderXOffset;

	private boolean centerPlaceholder;

	private boolean iconRight;

	private Color magnifyingGlassBackgroundColor;

	private Color iconBackground;

	private Color backColor;

	private String text;

	private Color fondo;

	private Color border;

	private Font fuente;

	private Font closeFont;

	private Color closeBackground;

	private Color closeForeground;

	private String echo;

	private JButton rightButton;

	public JButton getButton() {

		return rightButton;

	}

	public void setButton(JButton rightButton) {

		this.rightButton = rightButton;

	}

	public void setCloseForeground(Color closeForeground) {

		this.closeForeground = closeForeground;

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

		this.fondo = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || fondo == null || fondo == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, fondo, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public void setIconBackground(Color iconBackground) {

		this.iconBackground = iconBackground;

		repaint();

	}

	public void setBackColor(Color backColor) {

		this.backColor = backColor;

		repaint();

	}

	public BootSearchInputWithButton(int angle) {

		this();

		this.angle = angle;

	}

	public BootSearchInputWithButton(String text, String button) {

		this();

		setText(text);

		rightButton.setText(button);

	}

	public BootSearchInputWithButton(String text) {

		this();

		setText(text);

	}

	public BootSearchInputWithButton() {

		this(20, 15, "");

	}

	public void setMagnifyingGlassColor(Color color) {

		try {

			magnifyingGlassIcon.setColor(color);

			repaint();

		}

		catch (Exception e) {
		}

	}

	public void setCloseFont(Font closeFont) {

		this.closeFont = closeFont;

		repaint();

	}

	public void setCloseBackground(Color closeBackground) {

		this.closeBackground = closeBackground;

		repaint();

	}

	public void setEcho(String echo) {

		this.echo = echo;

		repaint();

	}

	public BootSearchInputWithButton(int columns, int angle, String button) {

		super(columns);

		setHorizontalAlignment(SwingConstants.CENTER);

		setForeground(Color.BLUE);

		setBackground(Color.PINK);

		echo = "x";

		this.angle = angle;

		this.borderColor = Color.BLACK;

		this.thickness = 1;

		this.margin = 40;

		setOpaque(false);

		setMargin(new Insets(0, margin, 0, 0));

		this.magnifyingGlassIcon = new Lupa();

		this.icono = magnifyingGlassIcon;

		this.placeholder = "";

		this.placeholderColor = Color.GRAY;

		this.placeholderFont = getFont().deriveFont(Font.ITALIC);

		this.placeholderXOffset = 5;

		this.centerPlaceholder = false;

		this.iconRight = false;

		this.magnifyingGlassBackgroundColor = Color.WHITE;

		DefaultContextMenu.addDefaultContextMenu(this);

		rightButton = new ShadowButton(button, false);

		rightButton.setFont(new Font("Arial", Font.PLAIN, 12));

		add(rightButton);

		addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				int rectX = getWidth() - Math.round(margin / 1.5f);

				rectX -= 52;

				int rectY = (getHeight() / 2) - (margin / 4);

				if (e.getX() >= rectX && e.getX() <= rectX + 20 && e.getY() >= rectY && e.getY() <= rectY + 20) {

					setText("");

				}

			}

		});

		closeFont = new Font("Algerian", Font.PLAIN, 15);

		closeBackground = Color.BLACK;

		closeForeground = Color.WHITE;

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(getBackground());

		g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, angle, angle));

		int margen = margin - 10;

		margen = Math.max(margen, 30);

		g2.setColor(iconBackground);

		if (angle > 89) {

			g2.fillRoundRect(thickness / 2, (thickness * 4), Math.round((margen - 2 * thickness) * 1.5f),
					(getHeight() - (thickness * 8)), angle, angle);

		}

		else {

			g2.fillRoundRect(thickness / 2, 4, Math.round((margen - 2 * thickness) * 1.5f),
					(getHeight() - thickness * 2) + 1, angle, angle);

		}

		g2.setColor(backColor);

		int x = thickness / 2;

		int y = (int) (getHeight() * 0.2);

		int iconX = 5;

		int iconY = (getHeight() / 2) - (margen / 2);

		if (thickness > 4) {

			g2.fillOval(iconX + (int) JMthos.reglaDeTres(15, 12, thickness), iconY - 1, margen, margen);

			g2.drawImage(icono.getImage(), iconX + ((int) JMthos.reglaDeTres(15, 12, thickness) - 1), iconY - 3, margen,
					margen, this);

		}

		else {

			if (angle > 300) {

				g2.fillOval(iconX, iconY, margen, margen);

				g2.drawImage(icono.getImage(), iconX, iconY - 1, margen, margen, this);

			}

			else {

				g2.fillOval(iconX + 2, iconY, margen, margen);

				g2.drawImage(icono.getImage(), iconX + 2, iconY - 1, margen, margen, this);

			}

		}

		if (magnifyingGlassIcon != null) {

			iconX = iconRight ? getWidth() - margen - 5 : 5;

			iconY = (getHeight() / 2) - (margen / 2);

			g2.setColor(magnifyingGlassBackgroundColor);

			g2.fillOval(iconX, iconY, margen, margen);

			g2.drawImage(icono.getImage(), iconX, iconY, margen, margen, this);

		}

		super.paintComponent(g);

		if (getText().isEmpty() && !placeholder.isEmpty()) {

			g2.setFont(placeholderFont);

			g2.setColor(placeholderColor);

			x = getInsets().left + placeholderXOffset;

			y = getHeight() / 2 + g2.getFontMetrics().getAscent() / 2 - 2;

			if (centerPlaceholder) {

				int placeholderWidth = g2.getFontMetrics().stringWidth(placeholder);

				x = (getWidth() - placeholderWidth) / 2;

			}

			g2.drawString(placeholder, x, y);

		}

		g2.setColor(closeBackground);

		if (!getText().isEmpty()) {

			g2.fillRoundRect((getWidth() - Math.round(margin / 1.5f)) - 50, (getHeight() / 2) - (margin / 4), 20, 20,
					angle, angle);

			g2.setColor(closeForeground);

			g2.setFont(closeFont.deriveFont(15f));

			if (closeFont.getFontName().equals("Algerian")) {

				g2.drawString(echo, ((getWidth() - Math.round(margin / 1.5f)) + 5) - 50,
						((getHeight() / 2) - (margin / 4)) + 15);

			}

			else {

				g2.drawString(echo, ((getWidth() - Math.round(margin / 1.5f)) + 6) - 50,

						((getHeight() / 2) - (margin / 4)) + 14);

			}

		}

		rightButton.setBounds((getWidth() - margin) - 15, 12, margin, getHeight() - 25);

		g2.dispose();

	}

	@Override
	protected void paintBorder(Graphics g) {

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(borderColor);

		g2.setStroke(new java.awt.BasicStroke(thickness));

		if (thickness == 1) {

			g2.draw(new RoundRectangle2D.Float(thickness / 2f, (thickness / 2f), getWidth() - thickness,
					(getHeight() - thickness) - 1, angle, angle));

		}

		else {

			g2.draw(new RoundRectangle2D.Float((thickness / 2f) - 1, (thickness / 2f) - 1, (getWidth() - thickness) + 1,
					(getHeight() - thickness) + 1, angle, angle));

		}

		g2.dispose();

	}

	public void setBorderColor(Color borderColor) {

		this.borderColor = borderColor;

		repaint();

	}

	public void setAngle(int angle) {

		this.angle = angle;

		repaint();

	}

	public void setThickness(int thickness) {

		this.thickness = thickness;

		repaint();

	}

	public void setPlaceholder(String placeholder) {

		this.placeholder = placeholder;

		repaint();

	}

	public void setPlaceholderColor(Color placeholderColor) {

		this.placeholderColor = placeholderColor;

		repaint();

	}

	public void setPlaceholderFont(Font placeholderFont) {

		this.placeholderFont = placeholderFont;

		repaint();

	}

	public void setPlaceholderXOffset(int placeholderXOffset) {

		this.placeholderXOffset = placeholderXOffset;

		repaint();

	}

	public void setCenterPlaceholder(boolean centerPlaceholder) {

		this.centerPlaceholder = centerPlaceholder;

		repaint();

	}

	public void setIconRight(boolean iconRight) {

		this.iconRight = iconRight;

		repaint();

	}

	public void setMagnifyingGlassBackgroundColor(Color magnifyingGlassBackgroundColor) {

		this.magnifyingGlassBackgroundColor = magnifyingGlassBackgroundColor;

		repaint();

	}

	public void setButtonFont(Font font) {

		try {

			rightButton.setFont(font);

		}

		catch (Exception e) {

		}

	}

	public void setButtonBackground(Color color) {

		try {

			rightButton.setBackground(color);

			if (rightButton instanceof ShadowButton) {

				((ShadowButton) rightButton).setBorderColor(color);

			}

		}

		catch (Exception e) {

		}

	}

}
