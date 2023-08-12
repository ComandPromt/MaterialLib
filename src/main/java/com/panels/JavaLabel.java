package com.panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class JavaLabel extends JLabel {

	private int figura;

	private Color enteredColor;

	private Color exitedColor;

	private boolean enteredCursor;

	private int grosor;

	private int anchura;

	private int altura;

	private boolean maximizar;

	private Color backGroundMouseEnteredColor;

	private Color backGroundMouseExitedColor;

	public void setBackGroundMouseEnteredColor(Color backGroundMouseEnteredColor) {

		this.backGroundMouseEnteredColor = backGroundMouseEnteredColor;

		repaint();

	}

	public void setBackGroundMouseExitedColor(Color backGroundMouseExitedColor) {

		this.backGroundMouseExitedColor = backGroundMouseExitedColor;

		repaint();

	}

	public void setExitedColor(Color exitedColor) {

		this.exitedColor = exitedColor;

		repaint();

	}

	public void setEnteredColor(Color enteredColor) {

		this.enteredColor = enteredColor;

		repaint();

	}

	public boolean isMaximizar() {

		return maximizar;

	}

	public void setGrosor(int grosor) {

		if (grosor < 2) {

			grosor = 2;

		}

		if (grosor > 9) {

			grosor = 9;

		}

		this.grosor = grosor;

		repaint();

	}

	public JavaLabel(int figura, Color enteredColor, Color exitedColor) {

		maximizar = false;

		anchura = 16;

		altura = 16;

		grosor = 2;

		enteredCursor = false;

		this.figura = figura;

		this.enteredColor = enteredColor;

		this.exitedColor = exitedColor;

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {

				enteredCursor = true;

				repaint();

			}

			@Override
			public void mouseExited(MouseEvent e) {

				enteredCursor = false;

				repaint();

			}

		});

	}

	@Override
	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		g2.setStroke(new BasicStroke(this.grosor));

		if (enteredCursor) {

			g2.setColor(backGroundMouseEnteredColor);

			g2.fillRect(0, 0, anchura, altura);

			g2.setColor(enteredColor);
		}

		else {

			g2.setColor(backGroundMouseExitedColor);

			g2.fillRect(0, 0, anchura, altura);

			g2.setColor(exitedColor);

		}

		switch (figura) {

		case 2:

			if (!maximizar) {

				g2.drawRect(0, 0, anchura, altura);

			}

			else {

				g2.drawRect(0, (25 * altura) / 100, (75 * anchura) / 100, (75 * altura) / 100);

				g2.drawLine((25 * anchura) / 100, 0, anchura, 0);

				g2.drawLine(anchura, 0, anchura, (75 * altura) / 100);

				g2.drawLine((25 * anchura) / 100, 0, (25 * anchura) / 100, (25 * altura) / 100);

				g2.drawLine((75 * anchura) / 100, (75 * altura) / 100, anchura, (75 * altura) / 100);

			}

			break;

		case 3:

			g2.drawLine(0, 0, anchura, altura);

			g2.drawLine(0, altura, anchura, 0);

			break;

		default:

			g2.drawLine(0, altura / 2, anchura, altura / 2);

			break;

		}

	}

	public void click() {

		if (maximizar) {

			maximizar = false;

		}

		else {

			maximizar = true;

		}

	}

}
