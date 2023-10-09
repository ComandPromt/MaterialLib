package com.buttons.onoff;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class SwitchButton extends Component {

	private Timer timer;

	private float location;

	private boolean selected;

	private boolean mouseOver;

	private float speed;

	private float alpha;

	private Color alphaColor;

	private int angulo;

	public Color getAlphaColor() {

		return alphaColor;

	}

	public void setAlphaColor(Color alphaColor) {

		this.alphaColor = alphaColor;

		repaint();

	}

	public void setAlpha(float alpha) {

		if (alpha < 0f) {

			alpha = 0f;

		}

		else if (alpha > 1f) {

			alpha = 1f;

		}

		this.alpha = alpha;

		repaint();

	}

	private List<EventSwitchSelected> events;

	public float getSpeed() {

		return speed;

	}

	public void setSpeed(float speed) {

		this.speed = speed;

	}

	public boolean isSelected() {

		return selected;

	}

	public void setSelected(boolean selected) {

		this.selected = selected;

		timer.start();

		runEvent();

	}

	public int getAngulo() {

		return angulo;

	}

	public void setAngulo(int angulo) {

		this.angulo = angulo;

		repaint();

	}

	public SwitchButton() {

		angulo = 100;

		alphaColor = Color.GRAY;

		alpha = 0.5f;

		speed = 2f;

		setBackground(new Color(0, 174, 255));

		setPreferredSize(new Dimension(50, 25));

		setForeground(Color.ORANGE);

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		events = new ArrayList<>();

		location = 2;

		timer = new Timer(0, new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent ae) {

				if (isSelected()) {

					int endLocation = getWidth() - getHeight() + 2;

					if (location < endLocation) {

						location += speed;

						repaint();

					}

					else {

						timer.stop();

						location = endLocation;

						repaint();

					}

				}

				else {

					int endLocation = 2;

					if (location > endLocation) {

						location -= speed;

						repaint();

					}

					else {

						timer.stop();

						location = endLocation;

						repaint();

					}

				}

			}

		});

		addMouseListener(new MouseAdapter() {

			@Override

			public void mouseEntered(MouseEvent me) {

				mouseOver = true;

			}

			@Override

			public void mouseExited(MouseEvent me) {

				mouseOver = false;

			}

			@Override

			public void mouseReleased(MouseEvent me) {

				if (mouseOver && SwingUtilities.isLeftMouseButton(me)) {

					selected = !selected;

					timer.start();

					runEvent();

				}

			}

		});

	}

	@Override
	public void paint(Graphics grphcs) {

		Graphics2D g2 = (Graphics2D) grphcs;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int width = getWidth();

		int height = getHeight();

		if (alpha < 1) {

			g2.setColor(alphaColor);

			g2.fillRoundRect(0, 0, width, height, angulo, angulo);

		}

		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

		g2.setColor(getBackground());

		g2.fillRoundRect(0, 0, width, height, angulo, angulo);

		g2.setColor(getForeground());

		g2.setComposite(AlphaComposite.SrcOver);

		g2.fillOval((int) location, 2, height - 4, height - 4);

		super.paint(grphcs);

	}

	public float getAlpha() {

		return alpha;

	}

	private void runEvent() {

		for (EventSwitchSelected event : events) {

			event.onSelected(selected);

		}

	}

	public void addEventSelected(EventSwitchSelected event) {

		events.add(event);

	}

}
