package com.dialog.confirm;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import com.buttons.animated.ButtonCustom;

@SuppressWarnings("serial")
public class MessageDialog extends javax.swing.JDialog {

	private Background background1;

	private ButtonCustom cmdCancel;

	private ButtonCustom cmdOK;

	private javax.swing.JLabel lbTitle;

	private JLabel txt;

	private final JFrame fram;

	private Animator animator;

	private Glass glass;

	private boolean show;

	private MessageType messageType;

	public void setIcon(Icon icon) {

		try {

			if (icon == null) {

				icon = new javax.swing.ImageIcon(getClass().getResource("icon.png"));

			}

			background1.setIcon(icon);

		}

		catch (Exception e) {

		}

	}

	public void setFont(Font font) {

		try {

			lbTitle.setFont(font);

			txt.setFont(font);

			cmdCancel.setFont(font);

			cmdOK.setFont(font);

		}

		catch (Exception e) {

		}

	}

	public MessageDialog(String title, String message, Font font) {

		this(null, null, title, message, null, font);

	}

	public MessageDialog(Color fondo, Color background, String title, String message, int fontSize) {

		this(fondo, background, title, message, null, new Font("Dialog", Font.PLAIN, fontSize));

	}

	public MessageDialog(String title, String message, int fontSize) {

		this(null, null, title, message, null, new Font("Dialog", Font.PLAIN, fontSize));

	}

	public MessageDialog(Color fondo, Color background, String title, String message, Font font) {

		this(fondo, background, title, message, null, font);

	}

	public MessageDialog(Color fondo, Color background, String title, String message) {

		this(fondo, background, title, message, null, null);

	}

	public MessageDialog(String title, String message) {

		this(null, null, title, message, null, null);

	}

	public MessageDialog(Color fondo, Color background, String title, String message, Icon icon, Font font) {

		super(new Fondo(background), true);

		if (icon == null) {

			icon = new javax.swing.ImageIcon(getClass().getResource("icon.png"));

		}
		if (font == null) {

			font = new Font("Dialog", Font.PLAIN, 25);

		}

		if (fondo == null) {

			fondo = Color.WHITE;

		}

		if (background == null) {

			background = Color.LIGHT_GRAY;

		}

		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {

				cmdCancel.setBounds(0, getHeight() - cmdCancel.getHeight(), getWidth() / 2, cmdCancel.getHeight());

				cmdOK.setBounds(getWidth() / 2, getHeight() - cmdCancel.getHeight(), getWidth() / 2,
						cmdCancel.getHeight());

				txt.setBounds(0, Math.round(getHeight() * 0.33f), getWidth(), txt.getHeight());

				lbTitle.setBounds(0, Math.round(getHeight() * 0.57f), getWidth(), lbTitle.getHeight());

			}

		});

		messageType = MessageType.CANCEL;

		this.fram = new Fondo(background);

		initComponents();

		background1.setFondo(fondo);

		background1.setBackground(background);

		setIcon(icon);

		init();

		setFont(font);

		showMessage(message, title);

	}

	private void init() {

		setBackground(new Color(0, 0, 0, 0));

		SimpleAttributeSet center = new SimpleAttributeSet();

		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);

		txt.setOpaque(false);

		txt.setBackground(new Color(0, 0, 0, 0));

		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {

			@Override

			public void windowClosing(WindowEvent e) {

				closeMessage();

			}

		});

		animator = new Animator(350, new TimingTargetAdapter() {

			@Override

			public void timingEvent(float fraction) {

				float f = show ? fraction : 1f - fraction;

				glass.setAlpha(f - f * 0.3f);

				setOpacity(f);

			}

			@Override

			public void end() {

				if (show == false) {

					dispose();

					glass.setVisible(false);

				}

			}

		});

		animator.setResolution(0);

		animator.setAcceleration(.5f);

		animator.setDeceleration(.5f);

		setOpacity(0f);

		glass = new Glass();

	}

	private void startAnimator(boolean show) {

		if (animator.isRunning()) {

			float f = animator.getTimingFraction();

			animator.stop();

			animator.setStartFraction(1f - f);

		}

		else {

			animator.setStartFraction(0f);

		}

		this.show = show;

		animator.start();

	}

	public void showMessage(String title, String message) {

		fram.setGlassPane(glass);

		glass.setVisible(true);

		lbTitle.setText(title);

		txt.setText(message);

		setLocationRelativeTo(fram);

		startAnimator(true);

		setVisible(true);

	}

	public void closeMessage() {

		startAnimator(false);

	}

	public MessageType getMessageType() {

		return messageType;

	}

	private void initComponents() {

		background1 = new Background();

		cmdCancel = new ButtonCustom("");

		cmdOK = new ButtonCustom("");

		lbTitle = new javax.swing.JLabel();

		txt = new JLabel();

		txt.setHorizontalAlignment(SwingConstants.CENTER);

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		setUndecorated(true);

		background1.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));

		cmdCancel.setBackground(new java.awt.Color(245, 71, 71));

		cmdCancel.setText("Cancel");

		cmdCancel.setColorHover(new java.awt.Color(255, 74, 74));

		cmdCancel.setColorPressed(new java.awt.Color(235, 61, 61));

		cmdCancel.setFont(new Font("Dialog", Font.PLAIN, 20));

		cmdCancel.addActionListener(new java.awt.event.ActionListener() {

			@Override

			public void actionPerformed(java.awt.event.ActionEvent evt) {

				cmdCancelActionPerformed(evt);

			}

		});

		cmdOK.setText("OK");

		cmdOK.setFont(new Font("Dialog", Font.PLAIN, 20));

		cmdOK.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(java.awt.event.ActionEvent evt) {

				cmdOKActionPerformed(evt);

			}

		});

		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		lbTitle.setFont(new Font("Dialog", Font.PLAIN, 20));

		lbTitle.setForeground(new java.awt.Color(76, 76, 76));

		lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		lbTitle.setText("Message Title");

		txt.setFont(new Font("Dialog", Font.PLAIN, 20));

		txt.setForeground(new java.awt.Color(245, 71, 71));

		txt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		txt.setText("Message Text\nSimple");

		txt.setFocusable(false);

		javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);

		background1Layout.setHorizontalGroup(background1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(background1Layout.createSequentialGroup()
						.addComponent(cmdCancel, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE).addGap(3)
						.addComponent(cmdOK, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
				.addComponent(txt, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
				.addComponent(lbTitle, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE));

		background1Layout.setVerticalGroup(background1Layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(background1Layout.createSequentialGroup().addGap(96)
						.addComponent(lbTitle, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txt, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(background1Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(cmdCancel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(cmdOK, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))));

		background1.setLayout(background1Layout);

		getContentPane().add(background1);

		pack();

	}

	private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {

		messageType = MessageType.CANCEL;

		closeMessage();

	}

	private void cmdOKActionPerformed(java.awt.event.ActionEvent evt) {

		messageType = MessageType.OK;

		closeMessage();

	}

	public enum MessageType {

		CANCEL, OK

	}

}
