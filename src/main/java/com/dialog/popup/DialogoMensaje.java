package com.dialog.popup;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import com.buttons.round.NButton;
import com.label.others.LabelHeader;
import com.label.others.LabelImage;
import com.material.utils.RSAWTUtilities;

@SuppressWarnings("serial")
public class DialogoMensaje extends JDialog {

	private int x;

	private int y;

	private LabelHeader cabecera;

	private LabelHeader texto;

	private JPanel jPanel1;

	private NButton rSButtonMetro1;

	private LabelImage icono;

	private int closeSize;

	private int iconWidth;

	private int grosor;

	public NButton getCloseButton() {

		return rSButtonMetro1;

	}

	public DialogoMensaje(JFrame parent, boolean modal, String header, String text) {

		super(parent, modal);

		closeSize = 30;

		iconWidth = 50;

		initComponents(header, text);

		RSAWTUtilities.setOpaque(this, false);

		setLocationRelativeTo(this);

		setVisible(true);

	}

	/**
	 * @wbp.parser.constructor
	 */
	public DialogoMensaje(JFrame parent, String header, String text) {

		this(parent, true, header, text);

	}

	public void setAltura(int altura) {

		iconWidth = altura;

		redimensionar();

		repaint();

	}

	private void redimensionar() {

		int x = 0;

		if (icono != null) {

			x = (iconWidth + grosor) + grosor / 2;

		}

		icono.setBounds(grosor, grosor, iconWidth, iconWidth);

		rSButtonMetro1.setBounds(((getWidth() - closeSize) + (grosor / 2)), grosor, closeSize - grosor, closeSize);

		cabecera.setBounds(x, grosor, ((getWidth() - (icono.getWidth() + closeSize)) - grosor), closeSize - grosor);

		texto.setBounds(x, grosor + cabecera.getHeight(), ((getWidth() - (icono.getWidth() + closeSize)) - grosor),
				(closeSize - grosor));

		jPanel1.setSize(getWidth(), icono.getHeight() + grosor * 2);

		setSize(getWidth(), icono.getHeight() + grosor * 2);

	}

	public LabelImage getIcono() {

		return icono;

	}

	public LabelHeader getCabecera() {

		return cabecera;

	}

	public LabelHeader getTexto() {

		return texto;

	}

	private void initComponents(String header, String text) {

		this.jPanel1 = new JPanel();

		this.cabecera = new LabelHeader(header);

		cabecera.setVerticalAlignment(SwingConstants.TOP);

		cabecera.setSombra(Color.LIGHT_GRAY);

		cabecera.setDistanciaSombra(5);

		cabecera.setDireccionSombra(10);

		grosor = 5;

		cabecera.setForeground(Color.BLACK);

		cabecera.setBackground(Color.WHITE);

		texto = new LabelHeader(text);

		texto.setSombra(Color.LIGHT_GRAY);

		texto.setForeground(Color.BLACK);

		texto.setBackground(Color.WHITE);

		icono = new LabelImage();

		jPanel1.addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				redimensionar();

			}

		});

		this.rSButtonMetro1 = new NButton("X");

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setUndecorated(true);

		this.jPanel1.setBackground(Color.WHITE);

		this.jPanel1.addMouseMotionListener(new MouseMotionAdapter() {

			public void mouseDragged(MouseEvent evt) {

				jPanel1MouseDragged(evt);

			}

		});

		this.jPanel1.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent evt) {

				jPanel1MousePressed(evt);

			}

		});

		this.rSButtonMetro1.setBackground(new Color(204, 0, 0));

		this.rSButtonMetro1.setText("X");

		this.rSButtonMetro1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {

				rSButtonMetro1ActionPerformed(evt);

			}

		});

		GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addComponent(icono, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
								.addComponent(texto, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
								.addComponent(cabecera, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(rSButtonMetro1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGap(42)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addComponent(icono, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
								.addComponent(rSButtonMetro1, GroupLayout.PREFERRED_SIZE, 24,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(jPanel1Layout.createSequentialGroup().addComponent(cabecera).addGap(7)
										.addComponent(texto)))
						.addGap(32)));

		this.jPanel1.setLayout(jPanel1Layout);

		GroupLayout layout = new GroupLayout(getContentPane());

		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(jPanel1,
				GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(33, Short.MAX_VALUE)));

		getContentPane().setLayout(layout);

		pack();

	}

	private void rSButtonMetro1ActionPerformed(ActionEvent evt) {

		dispose();

	}

	private void jPanel1MousePressed(MouseEvent evt) {

		this.x = evt.getX();

		this.y = evt.getY();

	}

	private void jPanel1MouseDragged(MouseEvent evt) {

		Point mueve = MouseInfo.getPointerInfo().getLocation();

		setLocation(mueve.x - this.x, mueve.y - this.y);

	}

}
