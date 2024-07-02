package com.material.table;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;

import com.search.BootSearchInput;
import com.search.RoundedSearchFieldWithButton;
import com.search.SearchTextField;

@SuppressWarnings("all")

public class Main extends javax.swing.JFrame {
	private BootSearchInput lblNewLabel_2;
	private RoundedSearchFieldWithButton panel_1;

	public Main() throws IOException {

		setTitle("");

		initComponents();

		setVisible(true);

	}

	public static void main(String[] args) {

		try {

			new Main().setVisible(true);

		}

		catch (Exception e) {

		}

	}

	public void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		setResizable(false);

		LinkedList<String> lista = new LinkedList();

		lista.add("aa");
		lista.add("bb");
		lista.add("cc");
		lista.add("dd");
		lista.add("ee");
		lista.add("ff");

		SearchTextField panel = null;

		BootSearchInput lblNewLabel = null;

		try {
			panel_1 = new RoundedSearchFieldWithButton();
			lblNewLabel_2 = new BootSearchInput();
//		lblNewLabel.setEcho("a");
			lblNewLabel_2.setCloseFont(new Font("Arial", Font.PLAIN, 15));
			lblNewLabel_2.setText("yygfgjb\r\n");
			lblNewLabel_2.setBackColor(Color.WHITE);
			lblNewLabel_2.setIconBackground(Color.WHITE);
			lblNewLabel_2.setMagnifyingGlassColor(Color.BLACK);
			lblNewLabel_2.setAngle(50);

			lblNewLabel_2.setThickness(5);

			lblNewLabel_2.setIconRight(false);
			lblNewLabel_2.setPlaceholderColor(Color.RED);
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEADING);
			lblNewLabel_2.setPlaceholder("ssss");
			lblNewLabel_2.setCenterPlaceholder(true);
			lblNewLabel_2.setPlaceholderFont(new Font("Algerian", Font.PLAIN, 20));
			lblNewLabel_2.setBackground(Color.PINK);
			lblNewLabel_2.setForeground(Color.BLACK);
			lblNewLabel_2.setFont(new Font("Algerian", Font.PLAIN, 20));
			// Establecer el color del borde usando el nuevo método setBorderColor
			lblNewLabel_2.setBorderColor(Color.GREEN);

			lblNewLabel_2.setMagnifyingGlassBackgroundColor(Color.PINK);
			// Establecer el grosor del borde
			lblNewLabel_2.setThickness(3);
		} catch (Exception e) {
			e.printStackTrace();
		}

		JPanel panel_2 = new JPanel();

//		lblNewLabel_1.setHeader(Color.ORANGE);
//		lblNewLabel_1.setHeader("bbb");
//		lblNewLabel_1.setScrollBackground(Color.GREEN);
//		lblNewLabel_1.setScroll(Color.ORANGE);
//		lblNewLabel_1.setHintText(Color.RED);

		// lblNewLabel_1.setIcon(new
		// ImageIcon(Main.class.getResource("/imgs/imagenes/clean.png")));
		// lblNewLabel_1.setAngle(90);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(49).addComponent(panel_2,
										GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addGap(85).addComponent(lblNewLabel_2,
										GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addGap(37).addComponent(panel_1,
										GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(52, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(33)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE).addGap(43)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(59, Short.MAX_VALUE)));

		getContentPane().setLayout(layout);

		setSize(new Dimension(532, 433));

		setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent arg0) {

	}

	public void stateChanged(ChangeEvent e) {

	}
}
