
package com.message.alerts;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Animacion.Fade;

@SuppressWarnings("serial")

public class AlertSuccess extends javax.swing.JFrame {

	private JLabel image;

	public JLabel titulo;

	private JPanel jPanel1;

	public void setImage(URL path) {

		image.setIcon(new ImageIcon(path));

	}

	public AlertSuccess(boolean modal) {

		this(352);

	}

	public AlertSuccess(int speed) {

		if (speed < 1) {

			speed = 480;

		}

		setAlwaysOnTop(true);

		initComponents();

		setLocationRelativeTo(null);

		Fade.JFrameFadeOut(1f, 0f, 0.1f, speed, this, Fade.DISPOSE);

	}

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();

		jPanel1.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				dispose();

			}

		});

		image = new javax.swing.JLabel();

		image.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				dispose();

			}

		});

		titulo = new javax.swing.JLabel();

		titulo.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				dispose();

			}

		});

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		setUndecorated(true);

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));

		image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/imagenes/success.png")));

		titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		titulo.setText("Webcamer introducida correctamente");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());

		getContentPane().setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		jPanel1.setLayout(new GridLayout(0, 1, 0, 0));
		jPanel1.add(image);
		jPanel1.add(titulo);

		pack();
	}

	public void setTitulo(String mensaje) {

		titulo.setText(mensaje);

	}

}
