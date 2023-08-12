package com.info.file;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import util.Mthos;

@SuppressWarnings("all")

public class InfoFile extends javax.swing.JFrame implements ActionListener, ChangeListener {

	private LinkedList<String> listaDatos;

	public JTextField file;

	private Color backgroundColor = new Color(240, 240, 240);

	public JLabel contador;

	private JLabel preview;

	int indice;

	public JButton copiar;

	public JButton abrir;

	public JButton ver;

	private JButton back;

	private JButton next;

	private Point mouseClickPoint;

	private Font fuente;

	private String title;

	private String font;

	public void setTextColor(Color textColor) {

		contador.setForeground(textColor);

		copiar.setForeground(textColor);

		abrir.setForeground(textColor);

		ver.setForeground(textColor);

		file.setForeground(textColor);

	}

	public void setBackgroundColor(Color backgroundColor) {

		this.backgroundColor = backgroundColor;

		getContentPane().setBackground(backgroundColor);

		copiar.setBackground(backgroundColor);

		abrir.setBackground(backgroundColor);

		ver.setBackground(backgroundColor);

		back.setBackground(backgroundColor);

		next.setBackground(backgroundColor);

	}

	public void setFont(String font, int type) {

		int tipo;

		switch (type) {

		default:

		case 1:

			tipo = Font.PLAIN;

			break;

		case 2:

			tipo = Font.BOLD;

			break;

		case 3:

			tipo = Font.ITALIC;

			break;

		}

		this.fuente = new Font(font, tipo, 14);

	}

	@Override

	public void setTitle(String title) {

		this.title = title;

	}

	private void rueda(MouseWheelEvent e) {

		if (e.getWheelRotation() < 0) {

			adelante();

		}

		else {

			atras();

		}

	}

	public InfoFile(List<String> lista) {

		title = "InfoFile";

		fuente = new Font("Tahoma", Font.PLAIN, 14);

		addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				mouseClickPoint = e.getPoint();

			}

		});

		addMouseMotionListener(new MouseAdapter() {

			@Override

			public void mouseDragged(MouseEvent e) {

				Point newPoint = e.getLocationOnScreen();

				newPoint.translate(-mouseClickPoint.x, -mouseClickPoint.y);

				setLocation(newPoint);

			}

		});

		if (lista.isEmpty()) {

			System.exit(0);

		}

		this.listaDatos = (LinkedList<String>) lista;

		setIconImage(Toolkit.getDefaultToolkit().getImage(InfoFile.class.getResource("/imgs/imagenes/info.png")));

		getContentPane().setBackground(this.backgroundColor);

		setTitle(title);

		initComponents();

		setUndecorated(true);

		setShape(new RoundRectangle2D.Double(0, 0, 500, 150, 50, 50));

	}

	public void verImagen(int index) {

		this.indice = 0;

		if (index < listaDatos.size()) {

			this.indice = index;

			previsualizar(listaDatos.get(index));

		}

		else {

			previsualizar(listaDatos.get(0));

		}

	}

	private void adelante() {

		indice++;

		if (indice >= listaDatos.size()) {

			indice = listaDatos.size();

			indice--;

		}

		previsualizar(listaDatos.get(indice));

	}

	private void atras() {

		if (indice > 0) {

			indice--;

		}

		previsualizar(listaDatos.get(indice));

	}

	private static Clipboard getSystemClipboard() {

		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();

		Clipboard systemClipboard = defaultToolkit.getSystemClipboard();

		return systemClipboard;

	}

	public static void copy(String text) {

		Clipboard clipboard = getSystemClipboard();

		clipboard.setContents(new StringSelection(text), null);

	}

	private void abrir(boolean archivo) {

		String ruta = file.getText();

		if (!ruta.isEmpty()) {

			try {

				if (archivo) {

					ruta = file.getText().substring(0, file.getText().lastIndexOf(Mthos.saberSeparador()));

				}

				if (System.getProperty("os.name").contains("indows")) {

					Runtime.getRuntime().exec("cmd /c C:\\Windows\\explorer.exe " + "\"" + ruta + "\"");

				}

				if (System.getProperty("os.name").contains("inux")) {

					Runtime.getRuntime().exec("xdg-open " + ruta);

				}

				if (System.getProperty("os.name").contains("ac")) {

					Runtime.getRuntime().exec("open " + ruta);

				}

			}

			catch (Exception e) {

			}

		}

	}

	private void initComponents() {

		indice = 0;

		JButton btnNewButton_1 = new JButton("");

		contador = new JLabel("");

		contador.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

				rueda(e);

			}

		});

		preview = new JLabel("");

		preview.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

				rueda(e);

			}

		});

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		setResizable(false);

		Date myDate = new Date();

		btnNewButton_1.setBorder(null);

		btnNewButton_1.setIcon(new ImageIcon(InfoFile.class.getResource("/imgs/imagenes/close.png")));

		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();

			}

		});

		JButton btnNewButton_1_1 = new JButton("");

		btnNewButton_1_1.setBorder(null);

		btnNewButton_1_1.setFocusPainted(false);

		btnNewButton_1_1.setIcon(new ImageIcon(InfoFile.class.getResource("/imgs/imagenes/min.png")));

		btnNewButton_1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				setState(JFrame.ICONIFIED);

			}

		});

		file = new JTextField();

		file.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

				rueda(e);

			}

		});

		file.setFont(fuente);

		file.setColumns(10);

		copiar = new JButton("Copy");

		copiar.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

				rueda(e);

			}

		});

		copiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				copy(file.getText());

			}

		});

		copiar.setFocusPainted(false);

		copiar.setBorder(null);

		copiar.setFont(fuente);

		copiar.setBackground(backgroundColor);

		copiar.setIcon(new ImageIcon(InfoFile.class.getResource("/imgs/imagenes/copy.png")));

		abrir = new JButton("Open");

		abrir.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

				rueda(e);

			}

		});

		abrir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				abrir(true);

			}

		});

		abrir.setFocusPainted(false);

		abrir.setBorder(null);

		abrir.setFont(fuente);

		abrir.setBackground(backgroundColor);

		abrir.setIcon(new ImageIcon(InfoFile.class.getResource("/imgs/imagenes/abrir.png")));

		ver = new JButton("View");

		ver.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

				rueda(e);

			}

		});

		ver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				abrir(false);

			}

		});

		ver.setFocusPainted(false);

		ver.setBorder(null);

		ver.setFont(fuente);

		ver.setBackground(backgroundColor);

		ver.setIcon(new ImageIcon(InfoFile.class.getResource("/imgs/imagenes/view.png")));

		back = new JButton("");

		back.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

				rueda(e);

			}

		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				atras();

			}

		});

		back.setFocusPainted(false);

		back.setBorder(null);

		back.setIcon(new ImageIcon(InfoFile.class.getResource("/imgs/imagenes/back.png")));

		back.setBackground(backgroundColor);

		next = new JButton("");

		next.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

				rueda(e);

			}

		});

		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				adelante();

			}

		});

		next.setFocusPainted(false);

		next.setBorder(null);

		next.setIcon(new ImageIcon(InfoFile.class.getResource("/imgs/imagenes/next.png")));

		next.setBackground(backgroundColor);

		preview.setHorizontalAlignment(SwingConstants.CENTER);

		file.setText(listaDatos.get(0));

		contador.setText("1 / 1");

		contador.setFont(fuente);

		if (listaDatos.size() == 1) {

			back.setEnabled(false);

			next.setEnabled(false);

		}

		previsualizar(listaDatos.get(0));

		JLabel btnNewButton_1_1_1 = new JLabel("");

		btnNewButton_1_1_1.setIcon(new ImageIcon(InfoFile.class.getResource("/imgs/imagenes/move.png")));

		btnNewButton_1_1_1.setBorder(null);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());

		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
								.addComponent(back, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(next, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(preview, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(copiar)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(abrir)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(ver))
								.addComponent(file, GroupLayout.PREFERRED_SIZE, 499, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup().addContainerGap()
										.addComponent(contador, GroupLayout.PREFERRED_SIZE, 233,
												GroupLayout.PREFERRED_SIZE)
										.addGap(149).addComponent(btnNewButton_1_1_1)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 22,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton_1,
												GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(1, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(contador,
								GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE))
						.addComponent(btnNewButton_1_1_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnNewButton_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(btnNewButton_1_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 25,
										Short.MAX_VALUE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(file, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout
								.createParallelGroup(Alignment.LEADING, false)
								.addComponent(back, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(copiar, GroupLayout.PREFERRED_SIZE, 36,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(abrir, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
										.addComponent(ver, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
								.addComponent(next, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
								.addComponent(preview, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));

		getContentPane().setLayout(layout);

		setSize(new Dimension(500, 150));

		setLocationRelativeTo(null);

	}

	private void previsualizar(String string) {

		try {

			int index = indice;

			index++;

			contador.setText(index + " / " + listaDatos.size());

			file.setText(string);

			BufferedImage image = ImageIO.read(new File(string));

			Image resizedImage = image.getScaledInstance(150, 60, 0);

			preview.setIcon(new ImageIcon(resizedImage));

		}

		catch (Exception e) {

		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
