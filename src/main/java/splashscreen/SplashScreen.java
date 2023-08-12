package splashscreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;

@SuppressWarnings("serial")

public class SplashScreen extends javax.swing.JDialog {

	private int sleep;

	private LinkedList<String> tasks;

	private static int ssleep;

	private static LinkedList<String> tareas;

	private static Icon icono;

	private static boolean mmodal;

	private int width;

	private int height;

	private static int ancho;

	private static int alto;

	private splashscreen.CurvesPanel curvesPanel1;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel lbStatus;

	private splashscreen.ProgressBarCustom pro;

	public void setTasks(LinkedList<String> tasks) {

		this.tasks = tasks;

	}

	public void load(JFrame frame, boolean modal, int sleep, LinkedList<String> list, Icon icon) {

		tasks = list;

		tareas = list;

		mmodal = modal;

		icono = icon;

		this.sleep = sleep;

		ssleep = sleep;

		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override

			public void run() {

				if (frame != null) {

					new SplashScreen(null, true, sleep, tasks, icon, width, height).setVisible(true);

					try {

						frame.setVisible(true);

					} catch (Exception e) {

					}

				}

			}

		});

	}

	public SplashScreen(JFrame frame, boolean modal, int sleep, String text, Icon icon, int width, int height) {

		super(frame, modal);

		if (width < 200) {

			width = 800;

		}

		if (height < 200) {

			height = 280;

		}

		ancho = width;

		alto = height;

		this.width = width;

		this.height = height;

		tasks = new LinkedList<String>();

		tasks.add(text);

		this.sleep = sleep;

		initComponents();

		try {

			if (icon == null) {

				jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/splashscreen/logo.png"))); // NOI18N

			}

			else {

				Image image = ((ImageIcon) icon).getImage();

				Image newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way

				icon = new ImageIcon(newimg);

				jLabel1.setIcon(icon);

			}

		}

		catch (Exception e) {

			jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/splashscreen/logo.png"))); // NOI18N

		}

		getContentPane().setBackground(new Color(221, 221, 221));

		setSize(width, height);

		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		load(frame, modal, sleep, tasks, icon);

	}

	public SplashScreen(JFrame frame, boolean modal, int sleep, LinkedList<String> list, Icon icon, int width,
			int height) {

		super(frame, modal);

		if (width < 200) {

			width = 800;

		}

		if (height < 200) {

			height = 280;

		}

		ancho = width;

		alto = height;

		this.width = width;

		this.height = height;

		tasks = list;

		this.sleep = sleep;

		initComponents();

		try {

			if (icon == null) {

				jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/splashscreen/logo.png"))); // NOI18N

			}

			else {

				Image image = ((ImageIcon) icon).getImage();

				Image newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way

				icon = new ImageIcon(newimg);

				jLabel1.setIcon(icon);

			}

		}

		catch (Exception e) {

			jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/splashscreen/logo.png"))); // NOI18N

		}

		getContentPane().setBackground(new Color(221, 221, 221));

		setSize(width, height);

		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		load(frame, modal, sleep, list, icon);

	}

	private void initComponents() {

		curvesPanel1 = new splashscreen.CurvesPanel();

		jLabel1 = new javax.swing.JLabel();

		pro = new splashscreen.ProgressBarCustom();

		lbStatus = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		setUndecorated(true);

		addWindowListener(new java.awt.event.WindowAdapter() {

			@Override

			public void windowOpened(java.awt.event.WindowEvent evt) {

				formWindowOpened(evt);

			}

		});

		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		lbStatus.setForeground(new java.awt.Color(200, 200, 200));

		lbStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		lbStatus.setText("Status");

		javax.swing.GroupLayout curvesPanel1Layout = new javax.swing.GroupLayout(curvesPanel1);

		curvesPanel1.setLayout(curvesPanel1Layout);

		curvesPanel1Layout
				.setHorizontalGroup(curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(curvesPanel1Layout.createSequentialGroup().addContainerGap(277, Short.MAX_VALUE)
								.addGroup(curvesPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(pro, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addContainerGap(278, Short.MAX_VALUE)));

		curvesPanel1Layout
				.setVerticalGroup(curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(curvesPanel1Layout.createSequentialGroup().addContainerGap(93, Short.MAX_VALUE)
								.addComponent(jLabel1).addGap(18, 18, 18)
								.addComponent(pro, javax.swing.GroupLayout.PREFERRED_SIZE, 5,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(lbStatus).addContainerGap(92, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(3, 3, 3).addComponent(curvesPanel1,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(3, 3, 3)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(3, 3, 3).addComponent(curvesPanel1,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(3, 3, 3)));

		pack();

		lbStatus.setFont(getFont().deriveFont(Font.BOLD, 20f));

		setLocationRelativeTo(null);

	}

	private void formWindowOpened(java.awt.event.WindowEvent evt) {

		new Thread(new Runnable() {

			@Override
			public void run() {

				try {

					int sumar = 100 / tasks.size();

					int percent = sumar;

					for (String task : tasks) {

						doTask(task, percent);

						if (percent < 100) {

							percent += sumar;

							if (percent > 100) {

								percent = 100;

							}

						}

					}

					dispose();

					curvesPanel1.stop();

				}

				catch (Exception e) {

					e.printStackTrace();

				}

			}

		}).start();

	}

	private void doTask(String taskName, int progress) throws Exception {

		lbStatus.setText(taskName);

		Thread.sleep(sleep);

		pro.setValue(progress);

	}

	public static void main(String args[]) {

		try {

			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {

				if ("Nimbus".equals(info.getName())) {

					javax.swing.UIManager.setLookAndFeel(info.getClassName());

					break;

				}

			}

		}

		catch (Exception ex) {

		}

		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override

			public void run() {

				SplashScreen dialog = new SplashScreen(new javax.swing.JFrame(), mmodal, ssleep, tareas, icono, ancho,
						alto);

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
