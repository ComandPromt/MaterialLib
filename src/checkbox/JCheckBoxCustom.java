package checkbox;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")

public class JCheckBoxCustom extends JCheckBox {

	int border = 4;

	Color checkColor = Color.WHITE;

	public void setBorder(int border) {

		this.border = border;

	}

	public void setCheckColor(Color checkColor) {

		this.checkColor = checkColor;

	}

	private int pos;

	public JCheckBoxCustom(String text, int position) {

		this.pos = position;

		setHorizontalAlignment(position);

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

		int x = 0;

		int max = 0;

		Graphics2D g2 = (Graphics2D) grphcs;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int ly = (getHeight() - 16) / 2;

		int sizeText = getText().length();

		if (pos == SwingConstants.CENTER) {

			x = (getWidth() / 2) - 27;

			max = getWidth() / 7;

			if (max <= 0 || sizeText >= max || max - sizeText < 3) {

				setHorizontalAlignment(SwingConstants.LEFT);

				x = 1;

			}

			else {

				int aumento = 0;

				int mitad = max / 2;

				int operacion = 0;

				int multiplicador = 0;

				int porcentaje = 0;

				int restar = (mitad / 2);

				porcentaje = (50 * sizeText) / mitad;

				if (sizeText < mitad) {

					x = (getWidth() / 2);

					if (sizeText <= (mitad / 3)) {

						porcentaje -= 8;

					}

					else {

						if (sizeText >= (mitad / 2)) {

							if (mitad - sizeText <= 3) {

								porcentaje -= (sizeText * 2) + (porcentaje / 6) - restar;

							}

							else {

								porcentaje = 1;

							}

						}

						else {

							porcentaje -= sizeText + 3;

						}
					}

					x -= (sizeText * 4) + porcentaje;

				}

				else {

					multiplicador = (30 * mitad) / 50;

					multiplicador++;

					if (porcentaje == 50) {

						multiplicador += 2;

					}

					if (porcentaje > 50 && porcentaje < 70) {

						multiplicador++;

					}

					if (porcentaje > 75) {

						multiplicador -= 2;

					}

					if (porcentaje >= 80 && porcentaje < 90) {

						multiplicador += 3;

					}

					if (porcentaje >= 90) {

						multiplicador += 2;

					}

					operacion = (multiplicador * 3) + (multiplicador - (sizeText * 3));

					aumento = max - (sizeText + operacion);

					x -= sizeText + aumento;

				}

			}

		}

		else

		{

			x = 1;

		}

		if (isSelected()) {

			if (isEnabled()) {

				g2.setColor(getBackground());

			}

			else {

				g2.setColor(Color.GRAY);

			}

			g2.fillRoundRect(x, ly, 16, 16, border, border);

			int[] px = { x + 3, x + 7, x + 13, x + 11, x + 7, x + 5 };

			int[] py = { ly + 8, ly + 14, ly + 5, ly + 3, ly + 10, ly + 6 };

			g2.setColor(checkColor);

			g2.fillPolygon(px, py, px.length);

		}

		else {

			g2.setColor(Color.GRAY);

			g2.fillRoundRect(x, ly, 16, 16, border, border);

			g2.setColor(checkColor);

			g2.fillRoundRect(++x, ++ly, 14, 14, border, border);

		}

		g2.dispose();

	}

}