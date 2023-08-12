package com.chart;

import java.awt.Graphics2D;

public abstract class SimpleBarBlankPlotChatRender {

	public abstract String getLabelText(int index);

	public abstract void renderSeries(SimpleBarBlankPlotChart chart, Graphics2D g2, SeriesSize size, int index);

}
