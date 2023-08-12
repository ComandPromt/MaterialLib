package com.chart;

@SuppressWarnings("serial")
public class SimpleBarLegendItem extends javax.swing.JPanel {

	private SimpleBarLabelColor lbColor;

	private javax.swing.JLabel lbName;

	public SimpleBarLegendItem(SimpleBarModelLegend data) {

		initComponents();

		setOpaque(false);

		lbColor.setBackground(data.getColor());

		lbName.setText(data.getName());

	}

	private void initComponents() {

		lbColor = new SimpleBarLabelColor();

		lbName = new javax.swing.JLabel();

		lbColor.setText("labelColor1");

		lbName.setForeground(new java.awt.Color(100, 100, 100));

		lbName.setText("Name");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);

		this.setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(lbColor, javax.swing.GroupLayout.PREFERRED_SIZE, 16,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(lbName)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addComponent(lbName).addGap(0, 0, Short.MAX_VALUE))
						.addComponent(lbColor, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap()));

	}

}