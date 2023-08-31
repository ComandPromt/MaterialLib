package com.materialfilechooser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;

import com.copy.textarea.CopyTextAreaScroll;
import com.search.SearchInput;

@SuppressWarnings("all")

public class Test extends javax.swing.JFrame {

	public Test() throws IOException {

		getContentPane().setBackground(Color.LIGHT_GRAY);

		setTitle("");

		initComponents();

		setVisible(true);

	}

	public static void main(String[] args) {

		try {

			new Test().setVisible(true);

		}

		catch (Exception e) {

		}

	}

	public void initComponents() throws IOException {

		try {

			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

			setResizable(false);

			String[] filtro = new String[3];

			filtro[0] = "jpg,png";

			filtro[1] = "mp3";

			filtro[2] = "avi";

			JFileChooserPanel panel = new JFileChooserPanel(this, "aaa", "Explorar", false, filtro, true);
			panel.setToolTipText("aaaaaa");

			panel.setEffectColor(Color.BLUE);

			panel.setBorder(new EmptyBorder(0, 0, 0, 0));

			panel.setButtonBackground(Color.PINK);

			panel.setTitle("Seleccione un archivo");

			panel.setText("Explorar");

			CopyTextAreaScroll panel_1 = new CopyTextAreaScroll();
			panel_1.getTextarea().setSelectionColor(getForeground());
			panel_1.getTextarea().setBackgroundText(Color.WHITE);
			panel_1.getTextarea().setLineColor(Color.RED);
			panel_1.setBorderColor(Color.WHITE);
			panel_1.setBackground(Color.WHITE);
			panel_1.setForeground(Color.MAGENTA);
			panel_1.setText("Test");
			panel_1.setFont(new Font("Dialog", Font.PLAIN, 36));
			panel_1.setPanelLeftColor(Color.RED);
			panel_1.setLabelText("AQqgf");

			SearchInput panel_2 = new SearchInput();

			javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
			layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout
					.createSequentialGroup()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
							.addGroup(layout.createSequentialGroup().addGap(63).addComponent(panel_1,
									GroupLayout.PREFERRED_SIZE, 454, GroupLayout.PREFERRED_SIZE))
							.addGroup(layout.createSequentialGroup().addGap(52).addGroup(layout
									.createParallelGroup(Alignment.LEADING)
									.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 655, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(100, Short.MAX_VALUE)));
			layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
					.addGroup(layout.createSequentialGroup().addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE).addGap(18)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(55, Short.MAX_VALUE)));

			getContentPane().setLayout(layout);

			setSize(new Dimension(811, 654));

			setLocationRelativeTo(null);

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void actionPerformed(ActionEvent arg0) {

	}

	public void stateChanged(ChangeEvent e) {

	}
}
