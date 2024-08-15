package com.jicons;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Carpeta extends ImageIcon {

	@Override
	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {

		int width = c.getWidth();

		int height = c.getHeight();

		g.setColor(c.getBackground());

		g.fillRect(0, 0, width, height);

		g.setColor(c.getForeground());

		g.drawArc(0, Math.round(height * 0.165f), Math.round(width * 0.2f), Math.round(height * 0.34f), -180, -90);

		g.drawLine(0, Math.round(height * 0.3333f), 0, Math.round(height * 0.8333f));

		g.drawLine(Math.round(width * 0.1f), Math.round(height * 0.166f), Math.round(width * 0.5f),
				Math.round(height * 0.166f));

		g.drawLine(Math.round(width * 0.5f), Math.round(height * 0.166f), Math.round(width * 0.6f), 0);

		g.drawLine(Math.round(width * 0.6f), 0, Math.round(width * 0.8f), 0);

		g.drawLine(Math.round(width * 0.1f), height - 2, Math.round(width * 0.8f), height - 2);

		g.drawLine(Math.round(width * 0.9f), Math.round(height * 0.166f), Math.round(width * 0.9f),
				Math.round(height * 0.8333f));

		g.drawArc(Math.round(width * 0.7f), Math.round(height * 0.64f), Math.round(width * 0.2f),
				Math.round(height * 0.34f), 0, -90);

		g.drawArc(Math.round(width * 0.7f), 0, Math.round(width * 0.2f), Math.round(height * 0.34f), 0, 90);

		g.drawArc(0, Math.round(height * 0.64f), Math.round(width * 0.2f), Math.round(height * 0.34f), 180, 90);

		g.fillRoundRect(0, Math.round(height * 0.166f), width, Math.round(height * 0.833f), 25, 25);

		g.fillRoundRect(Math.round(width * 0.6f), 0, Math.round(width * 0.3f), Math.round(height * 0.25f), 25, 25);

		Graphics2D g2d = (Graphics2D) g;

		GeneralPath path = new GeneralPath();

		path.moveTo(width * 0.5f, height * 0.166f);

		path.lineTo(width * 0.6f, 0);

		path.lineTo(width * 0.8f, 0);

		path.lineTo(width * 0.5f, height * 0.166f);

		path.closePath();

		g2d.fill(path);

		int equis[] = new int[3];

		equis[0] = Math.round(width * 0.4f);

		equis[1] = Math.round(width * 0.6f);

		equis[2] = Math.round(width * 0.6f);

		int ye[] = new int[3];

		ye[0] = Math.round(width * 0.166f);

		ye[1] = 0;

		ye[2] = Math.round(height * 0.166f);

		g.fillPolygon(equis, ye, 3);

	}

}
