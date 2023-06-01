package com.chart;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

abstract class BlankPlotChartRender1 {

	public abstract String getLabelText(int index);

	public abstract void renderGraphics(BlankPlotChart1 chart, Graphics2D g2, Rectangle2D rectangle);

	public abstract void renderGraphics(BlankPlotChart2 chart, Graphics2D g2, Rectangle2D rectangle);

	public abstract void mouseMove(Rectangle2D rectangle, MouseEvent mouse);

	public abstract void renderSeries(BlankPlotChart1 chart, Graphics2D g2, SeriesSize size, int index);

	public abstract void renderSeries(BlankPlotChart1 chart, Graphics2D g2, SeriesSize size, int index,
			List<Path2D.Double> gra);

	public abstract boolean mouseMoving(BlankPlotChart1 chart, MouseEvent evt, Graphics2D g2, SeriesSize size,
			int index);

	public abstract void renderGraphics(Graphics2D g2, List<Path2D.Double> gra);

	public abstract int getMaxLegend();
}
