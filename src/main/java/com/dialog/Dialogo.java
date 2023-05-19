
package com.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import util.WindowDragger;
import util.WindowsUtil;

@SuppressWarnings("serial")

public class Dialogo extends javax.swing.JDialog {

	private org.edisoncor.gui.button.ButtonAction close;

	private org.edisoncor.gui.label.LabelMetric message;

	private org.edisoncor.gui.panel.PanelNice panel1;

	private org.edisoncor.gui.varios.TitleBar titleBar1;

	public void setMessage(String text) {

		message.setText(text);

	}

	public void setCloseText(String text) {

		close.setText(text);

	}

	public Dialogo() {

		initComponents();

		new WindowDragger(this, titleBar1);

		setLocationRelativeTo(null);

		WindowsUtil.makeWindowsShape(this, panel1.getShape());

		titleBar1.addCloseAction(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				close();

			}

		});

	}

	private void close() {

		this.dispose();

	}

	private void initComponents() {

		panel1 = new org.edisoncor.gui.panel.PanelNice();

		titleBar1 = new org.edisoncor.gui.varios.TitleBar();

		message = new org.edisoncor.gui.label.LabelMetric();

		close = new org.edisoncor.gui.button.ButtonAction();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		setTitle("Dialogo");

		setUndecorated(true);

		message.setText("Ejemplo de dialogo");

		close.setText("Cerrar");

		close.setMain(true);

		close.addActionListener(new java.awt.event.ActionListener() {

			@Override

			public void actionPerformed(java.awt.event.ActionEvent evt) {

				buttonAction1ActionPerformed(evt);

			}

		});

		javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);

		panel1.setLayout(panel1Layout);

		panel1Layout
				.setHorizontalGroup(
						panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(titleBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
								.addGroup(panel1Layout.createSequentialGroup().addGap(25, 25, 25)
										.addComponent(message, javax.swing.GroupLayout.DEFAULT_SIZE, 423,
												Short.MAX_VALUE)
										.addContainerGap())
								.addGroup(panel1Layout.createSequentialGroup().addGap(165, 165, 165)
										.addComponent(close, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGap(179, 179, 179)));

		panel1Layout.setVerticalGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panel1Layout.createSequentialGroup()
						.addComponent(titleBar1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(42, 42, 42)
						.addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
						.addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());

		getContentPane().setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();

	}

	private void buttonAction1ActionPerformed(java.awt.event.ActionEvent evt) {

		close();

	}

	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override

			public void run() {

				Dialogo dialog = new Dialogo();

				dialog.addWindowListener(new java.awt.event.WindowAdapter() {

					@Override

					public void windowClosing(java.awt.event.WindowEvent e) {

						System.exit(0);

					}

				});

				dialog.setVisible(true);

			}

		});

	}

}