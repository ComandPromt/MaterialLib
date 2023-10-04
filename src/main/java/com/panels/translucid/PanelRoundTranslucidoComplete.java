package com.panels.translucid;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JToolTip;

import com.panels.round.RoundPanel;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class PanelRoundTranslucidoComplete extends RoundPanel {

	protected float tran;

	private float inicial;

	private float superior;

	private MouseListener ml;

	private Thread downloader;

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

	public PanelRoundTranslucidoComplete() {

		tran = 0.5f;

		inicial = 0.5f;

		superior = 0.9f;

		ml = new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {

				aclarar(e.getPoint());

			}

			@Override
			public void mouseExited(MouseEvent e) {

				desvanecer(e.getPoint());

			}

		};

		addMouseListener(ml);

	}

	private void aclarar(Point p3) {

		if ((p3.getX() > 0) || (p3.getY() > 0) || (p3.getX() <= getWidth()) || (p3.getY() <= getHeight())) {

			if (downloader != null && downloader.isAlive()) {

				return;

			}

			downloader = new Thread(new Runnable() {

				public void run() {

					for (float i = inicial; i <= superior; i += 0.1) {

						try {

							Thread.sleep(30 + (int) (Math.random() * 100));

							setTran(i);

						}

						catch (InterruptedException ex) {

						}

					}

				}

			});

			downloader.start();

		}

	}

	private void desvanecer(Point p3) {

		if ((p3.getX() < 0) || (p3.getY() < 0) || (p3.getX() >= getWidth()) || (p3.getY() >= getHeight())) {

			if (downloader != null && downloader.isAlive()) {

				return;

			}

			downloader = new Thread(new Runnable() {

				public void run() {

					for (float i = superior; i >= inicial; i -= 0.1) {

						try {

							Thread.sleep(30 + (int) (Math.random() * 100));

							setTran(i);

						}

						catch (InterruptedException ex) {

						}

					}

				}

			});

			downloader.start();

		}

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		AlphaComposite newComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, tran);

		g2.setComposite(newComposite);

		super.paintComponent(g);

	}

	public float getTran() {

		return tran;

	}

	public void setTran(float tran) {

		if (tran >= 0 && tran <= 1) {

			this.tran = tran;

			repaint();

		}

	}

	public float getInicial() {

		return inicial;

	}

	public void setInicial(float inicial) {

		if (inicial >= 0 && inicial <= 1)

			this.inicial = inicial;

	}

	public float getSuperior() {

		return superior;

	}

	public void setSuperior(float superior) {

		if (superior >= 0 && superior <= 1)

			this.superior = superior;

	}

}
