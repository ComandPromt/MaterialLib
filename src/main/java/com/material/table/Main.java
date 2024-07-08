package com.material.table;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;

import com.search.BootSearchInput;
import com.search.RoundedSearchFieldWithButton;
import com.search.SearchTextField;

@SuppressWarnings("all")

public class Main extends javax.swing.JFrame {

	private Busqueda lblNewLabel_2;

	private RoundedSearchFieldWithButton panel_1;

	public Main() throws IOException {
		getContentPane().setBackground(Color.WHITE);

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

		RoundedSearchFieldWithButton panel_2 = null;

		try {

			ArrayList<String> test = new ArrayList<>();

			test.add("aaa");

			test.add("bbb");

			test.add("ccc");

			test.add("ddd");

			test.add("eee");

			test.add("fff");

			lblNewLabel_2 = new Busqueda(test);

//		lblNewLabel.setEcho("a");
//			lblNewLabel_2.setCloseFont(new Font("Arial", Font.PLAIN, 15));
//			lblNewLabel_2.setText("yygfgjb\r\n");
//			lblNewLabel_2.setBackColor(Color.WHITE);
//			lblNewLabel_2.setIconBackground(Color.PINK);
//			lblNewLabel_2.setMagnifyingGlassColor(Color.BLACK);
//			lblNewLabel_2.setAngle(95);
//
//			lblNewLabel_2.setThickness(6);
//
//			lblNewLabel_2.setPlaceholderColor(Color.RED);
//			lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEADING);
//			lblNewLabel_2.setPlaceholder("ssss");
//			lblNewLabel_2.setCenterPlaceholder(true);
//			lblNewLabel_2.setPlaceholderFont(new Font("Algerian", Font.PLAIN, 20));
//			lblNewLabel_2.setBackground(Color.WHITE);
//			lblNewLabel_2.setForeground(Color.BLACK);
//			lblNewLabel_2.setFont(new Font("Algerian", Font.PLAIN, 20));
//			// Establecer el color del borde usando el nuevo método setBorderColor
//			lblNewLabel_2.setBorderColor(Color.BLUE);
//
//			lblNewLabel_2.setMagnifyingGlassBackgroundColor(Color.WHITE);
			// Establecer el grosor del borde

			panel_1 = new RoundedSearchFieldWithButton(100, "aaa", "bb");
			panel_1.setFondo(Color.WHITE);
			panel_1.setBorder(null);
			panel_1.setThickness(2);
			panel_1.setMagnifyingGlassColor(Color.WHITE);
			panel_1.setMagnifyingGlassBackgroundColor(Color.BLUE);
			panel_1.setForeground(Color.BLACK);

			panel_1.setFont(new Font("Algerian", Font.PLAIN, 25));

			panel_1.setButtonFont(new Font("Algerian", Font.PLAIN, 25));

			panel_1.setButtonBackground(Color.MAGENTA);

			panel_1.setBackground(Color.ORANGE);

			panel_1.setBackColor(Color.LIGHT_GRAY);

			panel_1.setIconBackground(Color.RED);

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		BootSearchInput lblNewLabel_2_1 = new BootSearchInput();
		lblNewLabel_2_1.setThickness(2);
		lblNewLabel_2_1.setText("yygfgjb\r\n");
		lblNewLabel_2_1.setPlaceholderFont(new Font("Algerian", Font.PLAIN, 20));
		lblNewLabel_2_1.setPlaceholderColor(Color.RED);
		lblNewLabel_2_1.setPlaceholder("ssss");
		lblNewLabel_2_1.setMagnifyingGlassColor(Color.BLACK);
		lblNewLabel_2_1.setMagnifyingGlassBackgroundColor(Color.WHITE);
		lblNewLabel_2_1.setIconBackground(Color.PINK);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEADING);
		lblNewLabel_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_1.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblNewLabel_2_1.setCloseFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_2_1.setCenterPlaceholder(true);
		lblNewLabel_2_1.setBorderColor(Color.BLUE);
		lblNewLabel_2_1.setBackground(Color.WHITE);
		lblNewLabel_2_1.setBackColor(Color.WHITE);
		lblNewLabel_2_1.setAngle(80);

		ArrayList<String> test = new ArrayList<>();
		lblNewLabel_2.setLayout(new GridLayout(1, 0, 0, 0));
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

//		lblNewLabel_1.setHeader(Color.ORANGE);
//		lblNewLabel_1.setHeader("bbb");
//		lblNewLabel_1.setScrollBackground(Color.GREEN);
//		lblNewLabel_1.setScroll(Color.ORANGE);
//		lblNewLabel_1.setHintText(Color.RED);

		// lblNewLabel_1.setIcon(new
		// ImageIcon(Main.class.getResource("/imgs/imagenes/clean.png")));
		// lblNewLabel_1.setAngle(90);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
				layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap(72, Short.MAX_VALUE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 406,
										GroupLayout.PREFERRED_SIZE)
								.addGap(40))
						.addGroup(layout.createSequentialGroup().addGap(55)
								.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 406,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(57, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
								.addContainerGap(22, Short.MAX_VALUE)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(23).addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
				.addGap(28).addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
				.addGap(33).addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(215, Short.MAX_VALUE)));

		getContentPane().setLayout(layout);

		setSize(new Dimension(532, 674));

		setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent arg0) {

	}

	public void stateChanged(ChangeEvent e) {

	}
}
