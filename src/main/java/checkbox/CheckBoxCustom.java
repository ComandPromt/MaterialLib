package checkbox;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")

public class CheckBoxCustom extends JCheckBox {

	int border = 4;

	Color checkColor = Color.WHITE;

	String text;

	@Override
	public void setHorizontalAlignment(int alignment) {

		super.setHorizontalAlignment(SwingConstants.LEFT);

	}

	@Override
	public void setText(String text) {

		this.text = text;

		repaint();

	}

	public void setBorder(int border) {

		this.border = border;

		repaint();

	}

	public void setCheckColor(Color checkColor) {

		this.checkColor = checkColor;

		repaint();

	}

	public CheckBoxCustom() {

		setHorizontalAlignment(SwingConstants.LEFT);

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setOpaque(false);

		setFocusPainted(false);

		setContentAreaFilled(false);

		setBackground(new Color(69, 124, 235));

		text = "";

	}

	public CheckBoxCustom(String text) {

		this.text = text;

		setText(text);

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setOpaque(false);

		setFocusPainted(false);

		setContentAreaFilled(false);

		setBackground(new Color(69, 124, 235));

	}

	public CheckBoxCustom(String text, int position) {

		this.text = text;

		setText(text);

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setOpaque(false);

		setFocusPainted(false);

		setContentAreaFilled(false);

		setBackground(new Color(69, 124, 235));

	}

	@Override

	public void paint(Graphics grphcs) {

		super.paint(grphcs);

		int x = 1;

		Graphics2D g2 = (Graphics2D) grphcs;

		int fontSize = getFont().getSize();

		if (fontSize < 16) {

			fontSize = 16;

		}

		else if (fontSize > 16) {

			fontSize -= 10;

			if (fontSize < 16) {

				fontSize = 16;

			}

		}

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int ly = ((getHeight() - fontSize) / 2) + 1;

		if (isEnabled()) {

			g2.setColor(getBackground());

			if (isSelected()) {

				g2.fillRoundRect(x, ly, fontSize, fontSize, border, border);

				g2.setColor(checkColor);

				int[] px = { x + (Math.round((float) (fontSize * 0.375))), x,
						x + (Math.round((float) (fontSize * 0.1875))), x + (Math.round((float) (fontSize * 0.375))),
						x + (Math.round((float) (fontSize * 0.875))), x + fontSize };

				int[] py = { ly + fontSize, ly + (Math.round((float) (fontSize * 0.333))),
						ly + (Math.round((float) (fontSize * 0.166))), ly + (Math.round((float) (fontSize * 0.5833))),
						ly, ly + (Math.round((float) (fontSize * 0.25))) };

				g2.fillPolygon(px, py, px.length);

			}

			else {

				g2.setColor(getBackground());

				g2.fillRoundRect(x, ly, fontSize, fontSize, border, border);

			}

		}

		else {

			g2.setColor(getBackground());

			g2.fillRoundRect(x, ly, fontSize, fontSize, border, border);

		}

		g2.setColor(getForeground());

		g2.drawString(text, fontSize + 7, getHeight() / 1.5f);

	}

}