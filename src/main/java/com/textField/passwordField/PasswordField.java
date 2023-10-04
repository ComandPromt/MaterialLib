
package com.textField.passwordField;

import static com.textField.text.MaterialTextField2.HINT_OPACITY_MASK;
import static com.textField.text.MaterialTextField2.LINE_OPACITY_MASK;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

import javax.swing.JPasswordField;
import javax.swing.JToolTip;
import javax.swing.text.DefaultCaret;

import com.material.utils.Utils;
import com.materiallib.utils.MaterialColor;
import com.textField.text.MaterialTextField2;
import com.toolTip.ToolTipLlamada;

public class PasswordField extends JPasswordField {

	private static final long serialVersionUID = 1L;

	private MaterialTextField2.FloatingLabel floatingLabel;

	private MaterialTextField2.Line line;

	private String hint;

	private Color accentColor;

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

	public PasswordField() {

		floatingLabel = new MaterialTextField2.FloatingLabel(this);

		line = new MaterialTextField2.Line(this);

		hint = "";

		accentColor = MaterialColor.CYAN_500;

		setBorder(null);

		setFont(getFont().deriveFont(Font.PLAIN, 20f));

		floatingLabel.setText("");

		setOpaque(false);

		setBackground(MaterialColor.TRANSPARENT);

		setCaret(new DefaultCaret() {

			private static final long serialVersionUID = 1L;

			@Override
			protected synchronized void damage(Rectangle r) {

				PasswordField.this.repaint();

			}

		});

		getCaret().setBlinkRate(500);

	}

	public String getLabel() {

		return floatingLabel.getText();

	}

	public void setLabel(String label) {

		floatingLabel.setText(label);

		repaint();

	}

	public String getHint() {

		return hint;

	}

	public void setHint(String hint) {

		this.hint = hint;

		repaint();

	}

	public Color getAccent() {

		return accentColor;

	}

	public void setAccent(Color accentColor) {

		this.accentColor = accentColor;

		floatingLabel.setAccent(accentColor);

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

		floatingLabel.update();

		line.update();

	}

	@Override
	protected void processFocusEvent(FocusEvent e) {

		super.processFocusEvent(e);

		floatingLabel.update();

		line.update();

		repaint();

	}

	@Override
	protected void processKeyEvent(KeyEvent e) {

		super.processKeyEvent(e);

		floatingLabel.update();

		line.update();

		repaint();

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

		if (!getHint().isEmpty() && getPassword().length == 0 && (getLabel().isEmpty() || isFocusOwner())
				&& floatingLabel.isFloatingAbove()) {

			g2.setColor(Utils.applyAlphaMask(getForeground(), HINT_OPACITY_MASK));

			FontMetrics metrics = g.getFontMetrics(g.getFont());

			g.drawString(getHint(), 0, metrics.getAscent() + 36);

		}

		floatingLabel.paint(g2);

		g2.setColor(Utils.applyAlphaMask(getForeground(), LINE_OPACITY_MASK));

		g2.fillRect(0, getHeight() - 9, getWidth(), 1);

		g2.setColor(accentColor);

		g2.fillRect((int) ((getWidth() - line.getWidth()) / 2), getHeight() - 10, (int) line.getWidth(), 2);

	}

}
