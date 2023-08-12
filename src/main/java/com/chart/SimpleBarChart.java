package com.chart;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class SimpleBarChart extends javax.swing.JPanel {

	private List<SimpleBarModelLegend> legends = new ArrayList<>();

	private List<SimpleBarModelChart> model = new ArrayList<>();

	private final int seriesSize = 12;

	private final int seriesSpace = 6;

	private SimpleBarBlankPlotChart blankPlotChart;

	private javax.swing.JPanel panelLegend;

	public SimpleBarChart() {

		initComponents();

		blankPlotChart.setBlankPlotChatRender(new SimpleBarBlankPlotChatRender() {

			@Override

			public String getLabelText(int index) {

				return model.get(index).getLabel();

			}

			@Override

			public void renderSeries(SimpleBarBlankPlotChart chart, Graphics2D g2, SeriesSize size, int index) {

				double totalSeriesWidth = (seriesSize * legends.size()) + (seriesSpace * (legends.size() - 1));

				double x = (size.getWidth() - totalSeriesWidth) / 2;

				for (int i = 0; i < legends.size(); i++) {

					SimpleBarModelLegend legend = legends.get(i);

					g2.setColor(legend.getColor());

					double seriesValues = chart.getSeriesValuesOf(model.get(index).getValues()[i], size.getHeight());

					g2.fillRect((int) (size.getX() + x), (int) (size.getY() + size.getHeight() - seriesValues),

							seriesSize, (int) seriesValues);

					x += seriesSpace + seriesSize;

				}

			}

		});

	}

	public void addLegend(String name, Color color) {

		SimpleBarModelLegend data = new SimpleBarModelLegend(name, color);

		legends.add(data);

		panelLegend.add(new SimpleBarLegendItem(data));

		panelLegend.repaint();

		panelLegend.revalidate();

	}

	public void addData(SimpleBarModelChart data) {

		model.add(data);

		blankPlotChart.setLabelCount(model.size());

		double max = data.getMaxValues();

		if (max > blankPlotChart.getMaxValues()) {

			blankPlotChart.setMaxValues(max);

		}

	}

	private void initComponents() {

		blankPlotChart = new SimpleBarBlankPlotChart();

		panelLegend = new javax.swing.JPanel();

		setBackground(new java.awt.Color(255, 255, 255));

		panelLegend.setOpaque(false);

		panelLegend.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);

		this.setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(panelLegend, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
								.addComponent(blankPlotChart, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(blankPlotChart, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
						.addGap(0, 0, 0).addComponent(panelLegend, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

	}

}
