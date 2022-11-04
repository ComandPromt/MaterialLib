
package alertas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import Animacion.Fade;
import componente.PopupAlerts;

@SuppressWarnings("serial")

public class AlertWarningSalir extends javax.swing.JFrame {

	Timer timer;

	TimerTask task;

	int i = 32;

	private javax.swing.JLabel image;

	private javax.swing.JPanel jPanel1;

	public javax.swing.JLabel titulo;

	public void setImage(URL path) {

		image.setIcon(new ImageIcon(path));

	}

	public void setTitulo(String mensaje) {

		titulo.setText(mensaje);

	}

	public AlertWarningSalir(boolean modal) {

		setAlwaysOnTop(true);

		initComponents();

		this.setLocationRelativeTo(null);

		Ubicar(0);

		Fade.JFrameFadeOut(1f, 0f, 0.1f, 480, this, Fade.DISPOSE);

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

		addWindowListener(new java.awt.event.WindowAdapter() {

			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {

				formWindowClosing(evt);

			}

			@Override
			public void windowOpened(java.awt.event.WindowEvent evt) {

				formWindowOpened(evt);

			}

		});

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));

		image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/warning.png")));

		titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		titulo.setText("ALERT WARNING");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);

		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
								.addComponent(image, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
								.addComponent(titulo, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE))
						.addContainerGap()));

		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(48)
						.addComponent(image, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE).addGap(38)
						.addComponent(titulo, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(65, Short.MAX_VALUE)));

		jPanel1.setLayout(jPanel1Layout);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());

		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 399, Short.MAX_VALUE).addContainerGap()));

		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(jPanel1,
				GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		getContentPane().setLayout(layout);

		pack();

	}

	private void formWindowOpened(java.awt.event.WindowEvent evt) {

		task = new TimerTask() {

			@Override
			public void run() {

				if (i == 352) {

					timer.cancel();

				}

				else {

					Ubicar(i);

					i += 32;

					trasparencia((float) i / 352);

				}

			}

		};

		timer = new java.util.Timer();

		timer.schedule(task, 0, 2);

	}

	private void formWindowClosing(java.awt.event.WindowEvent evt) {

		setVisible(false);

		dispose();

	}

	private void trasparencia(float trasp) {

		PopupAlerts.setOpacity(this, trasp);

	}

	private void Ubicar(int y) {

		this.setLocation(550, y - 120);

	}

}
