package com.materialfilechooser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;

import com.materialfilechooser.buttons.Home;
import com.panels.PanelGlowingGradient;
import com.panels.PanelShadow;

@SuppressWarnings("all")

public class Test extends javax.swing.JFrame {

	public Test() throws IOException {
		getContentPane().setFont(new Font("Dialog", Font.PLAIN, 31));
		setForeground(Color.GREEN);
		setBackground(Color.WHITE);

		getContentPane().setBackground(Color.PINK);

		setTitle("Test");

		initComponents();

		setVisible(true);

	}

	public static void main(String[] args) {

		try {

			new Test().setVisible(true);

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void initComponents() throws IOException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		setResizable(false);

		PanelShadow panel = new PanelShadow();

		PanelGlowingGradient btnNewButton = new PanelGlowingGradient();

		Home btnNewButton_1 = new Home(null, null);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(54)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(111, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING,
								layout.createSequentialGroup().addGap(38).addComponent(btnNewButton,
										GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup().addContainerGap(309, Short.MAX_VALUE).addComponent(
								btnNewButton_1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)))
						.addGap(96)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(63).addComponent(panel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(46)
				.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE).addGap(40)
				.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(30, Short.MAX_VALUE)));

		getContentPane().setLayout(layout);

		setSize(new Dimension(532, 552));

		setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent arg0) {

	}

	public void stateChanged(ChangeEvent e) {

	}
}
