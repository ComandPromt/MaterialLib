package com.dialog.confirm;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import com.buttons.round.NButton;

@SuppressWarnings("serial")
public class MessageDialog extends JDialog {

	private Background background1;

	private JLabel lbTitle;

	private JLabel txt;

	private final JFrame fram;

	private Animator animator;

	private Glass glass;

	private boolean show;

	private MessageType messageType;

	private int ancho;

	private int alto;

	private JPanel panel;

	private NButton btnNewButton;

	private NButton btnNewButton_1;

	@Override
	public void dispose() {

		try {

			fram.dispose();

			super.dispose();

		}

		catch (Exception e) {

		}

	}

	public void setBackgroundButtons(Color ok, Color cancel) {

		try {

			btnNewButton_1.setBackground(ok);

			btnNewButton.setBackground(cancel);

		}

		catch (Exception e) {

		}

	}

	public void setForegroundButtons(Color ok, Color cancel) {

		try {

			btnNewButton_1.setForeground(ok);

			btnNewButton.setForeground(cancel);

		}

		catch (Exception e) {

		}

	}

	public void setButtonFont(Font ok, Font cancel) {

		try {

			btnNewButton_1.setFont(ok);

			btnNewButton.setFont(cancel);

		}

		catch (Exception e) {

		}

	}

	public void setButtonText(String ok, String cancel) {

		try {

			btnNewButton_1.setText(ok);

			btnNewButton.setText(cancel);

		}

		catch (Exception e) {

		}

	}

	public void setFondo(Color fondo) {

		try {

			background1.setFondo(fondo);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setBackground(Color bgColor) {

		try {

			super.setBackground(bgColor);

			txt.setBackground(bgColor);

			lbTitle.setBackground(bgColor);

			panel.setBackground(bgColor);

		}

		catch (Exception e) {

		}

	}

	public void setMessage(Font font) {

		try {

			txt.setFont(font);

		}

		catch (Exception exception) {

		}

	}

	public void setTitle(Font font) {

		try {

			lbTitle.setFont(font);

		}

		catch (Exception exception) {

		}

	}

	public void setMessage(Color color) {

		try {

			txt.setForeground(color);

		}

		catch (Exception exception) {

		}

	}

	public void setTitle(Color color) {

		try {

			lbTitle.setForeground(color);

		}

		catch (Exception exception) {

		}

	}

	public void setIcon(Icon icon) {

		try {

			if (icon == null)
				icon = new ImageIcon(getClass().getResource("icon.png"));

			background1.setIcon(icon);

		}

		catch (Exception exception) {

		}

	}

	public void setFont(Font font) {

		try {

			lbTitle.setFont(font);

			txt.setFont(font);

		}

		catch (Exception exception) {

		}

	}

	/**
	 * @wbp.parser.constructor
	 */
	public MessageDialog(Color fondo, Color background, String title, String message, Icon icon, Font font,
			boolean cancelFirst) {

		super((Fondo) null, true);

		if (icon == null)
			icon = new ImageIcon(getClass().getResource("icon.png"));

		messageType = MessageType.CANCEL;

		fram = new Fondo(background);

		initComponents(cancelFirst);

		background1.setFondo(fondo);

		setIcon(icon);

		init();

		if (font == null)

			font = new Font("Dialog", 0, 30);

		if (fondo == null)

			fondo = Color.WHITE;

		if (background == null)

			background = Color.LIGHT_GRAY;

		setFont(font);

		showMessage(message, title);

	}

	public MessageDialog(String title, String mesage, Icon icon) {

		this(600, 300, null, null, title, mesage, icon, null, true);

	}

	public MessageDialog(String title, String mesage, Icon icon, boolean cancelFirst) {

		this(600, 300, null, null, title, mesage, icon, null, cancelFirst);

	}

	public MessageDialog(String title, String mesage) {

		this(600, 300, null, null, title, mesage, null, null, true);

	}

	public MessageDialog(String title, String mesage, boolean cancelFirst) {

		this(600, 300, null, null, title, mesage, null, null, cancelFirst);

	}

	public MessageDialog(String title, String message, Font font, Icon icon) {

		this(600, 300, (Color) null, (Color) null, title, message, icon, font, true);

	}

	public MessageDialog(String title, String message, Font font) {

		this(600, 300, (Color) null, (Color) null, title, message, (Icon) null, font, true);

	}

	public MessageDialog(String title, String message, Font font, boolean cancelFirst) {

		this(600, 300, (Color) null, (Color) null, title, message, (Icon) null, font, cancelFirst);

	}

	public MessageDialog(int width, int height, String title, String message, Font font, Icon icon,
			boolean cancelFirst) {

		this(width, height, (Color) null, (Color) null, title, message, icon, font, cancelFirst);

	}

	public MessageDialog(int width, int height, String title, String message, Font font, boolean cancelFirst) {

		this(width, height, (Color) null, (Color) null, title, message, (Icon) null, font, cancelFirst);

	}

	public MessageDialog(Color fondo, Color background, String title, String message, int fontSize, Icon icon,
			boolean cancelFirst) {

		this(600, 300, fondo, background, title, message, icon, new Font("Dialog", 0, fontSize), cancelFirst);

	}

	public MessageDialog(Color fondo, Color background, String title, String message, int fontSize, Icon icon) {

		this(600, 300, fondo, background, title, message, icon, new Font("Dialog", 0, fontSize), true);

	}

	public MessageDialog(Color fondo, Color background, String title, String message, int fontSize,
			boolean cancelFirst) {

		this(600, 300, fondo, background, title, message, (Icon) null, new Font("Dialog", 0, fontSize), cancelFirst);

	}

	public MessageDialog(Color fondo, Color background, String title, String message, Icon icon, Font font) {

		this(600, 300, fondo, background, title, message, icon, font, true);

	}

	public MessageDialog(Color fondo, Color background, String title, String message, int fontSize, Font font) {

		this(600, 300, fondo, background, title, message, (Icon) null, font, true);

	}

	public MessageDialog(Color fondo, Color background, String title, String message, int fontSize, Font font,
			Icon icon) {

		this(600, 300, fondo, background, title, message, icon, font, true);

	}

	public MessageDialog(int width, int height, Color fondo, Color background, String title, String message,
			int fontSize, boolean cancelFirst) {

		this(width, height, fondo, background, title, message, (Icon) null, new Font("Dialog", 0, fontSize),
				cancelFirst);

	}

	public MessageDialog(int width, int height, String title, String message, int fontSize, boolean cancelFirst) {

		this(width, height, (Color) null, (Color) null, title, message, (Icon) null, new Font("Dialog", 0, fontSize),
				cancelFirst);

	}

	public MessageDialog(int width, int height, Color fondo, Color background, String title, String message, Font font,
			boolean cancelFirst) {

		this(width, height, fondo, background, title, message, (Icon) null, font, cancelFirst);

	}

	public MessageDialog(int width, int height, Color fondo, Color background, String title, String message) {

		this(width, height, fondo, background, title, message, (Icon) null, (Font) null, true);

	}

	public MessageDialog(int width, int height, Color fondo, Color background, String title, String message,
			boolean cancelFirst) {

		this(width, height, fondo, background, title, message, (Icon) null, (Font) null, cancelFirst);

	}

	public MessageDialog(int width, int height, String title, String message, boolean cancelFirst) {

		this(width, height, (Color) null, (Color) null, title, message, (Icon) null, (Font) null, cancelFirst);

	}

	public MessageDialog(int width, int height, Color fondo, Color background, final String title, final String message,
			Icon icon, Font font, boolean cancelFirst) {

		super((Fondo) null, true);

		this.ancho = width;

		this.alto = height;

		this.lbTitle = new JLabel();

		if (title.isEmpty())

			this.lbTitle.setVisible(false);

		addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent e) {

				if (title.isEmpty() && message.isEmpty()) {

					MessageDialog.this.setSize(600, 275);

				}

				else {

					if (MessageDialog.this.ancho < 600)

						MessageDialog.this.ancho = 600;

					if (MessageDialog.this.alto < 275)

						MessageDialog.this.alto = 275;

					MessageDialog.this.setSize(MessageDialog.this.ancho, MessageDialog.this.alto);

				}

			}

		});

		if (icon == null)

			icon = new ImageIcon(getClass().getResource("icon.png"));

		if (font == null)

			font = new Font("Dialog", 0, 25);

		if (fondo == null)

			fondo = Color.RED;

		if (background == null)

			background = Color.WHITE;

		this.messageType = MessageType.CANCEL;

		this.fram = new Fondo(background);

		initComponents(cancelFirst);

		this.background1.setMove(true);

		this.background1.setFondo(fondo);

		this.background1.setBackground(background);

		setIcon(icon);

		init();

		setFont(font);

		showMessage(title, message);

	}

	private void init() {

		setBackground(new Color(0, 0, 0, 0));

		SimpleAttributeSet center = new SimpleAttributeSet();

		StyleConstants.setAlignment(center, 1);

		this.txt.setOpaque(false);

		setDefaultCloseOperation(0);

		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {

				MessageDialog.this.closeMessage();

			}

		});

		this.animator = new Animator(350, (TimingTarget) new TimingTargetAdapter() {

			public void timingEvent(float fraction) {

				float f = MessageDialog.this.show ? fraction : (1.0F - fraction);

				MessageDialog.this.glass.setAlpha(f - f * 0.3F);

				MessageDialog.this.setOpacity(f);

			}

			public void end() {

				if (!MessageDialog.this.show) {

					MessageDialog.this.dispose();

					MessageDialog.this.glass.setVisible(false);

				}

			}

		});

		this.animator.setResolution(0);

		this.animator.setAcceleration(0.5F);

		this.animator.setDeceleration(0.5F);

		setOpacity(0.0F);

		this.glass = new Glass();

	}

	private void startAnimator(boolean show) {

		if (this.animator.isRunning()) {

			float f = this.animator.getTimingFraction();

			this.animator.stop();

			this.animator.setStartFraction(1.0F - f);

		}

		else {

			this.animator.setStartFraction(0.0F);

		}

		this.show = show;

		this.animator.start();

	}

	void showMessage(String title, String message) {

		this.fram.setGlassPane(this.glass);

		this.glass.setVisible(true);

		this.lbTitle.setText(title);

		this.txt.setText(message);

		setLocationRelativeTo(this.fram);

		startAnimator(true);

		setVisible(true);

	}

	public void closeMessage() {

		startAnimator(false);

	}

	public MessageType getMessageType() {

		return this.messageType;

	}

	private void initComponents(boolean cancelFirst) {

		this.lbTitle = new JLabel();

		this.background1 = new Background();

		this.txt = new JLabel();

		this.txt.setHorizontalAlignment(0);

		setDefaultCloseOperation(2);

		setUndecorated(true);

		this.background1.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		this.lbTitle.setFont(new Font("Dialog", 0, 20));

		this.lbTitle.setHorizontalAlignment(0);

		this.lbTitle.setText("Message Title");

		this.txt.setFont(new Font("Dialog", 0, 20));

		this.txt.setHorizontalAlignment(0);

		this.txt.setText("Message Text\nSimple");

		this.txt.setFocusable(false);

		panel = new JPanel();

		GroupLayout background1Layout = new GroupLayout(this.background1);

		background1Layout.setHorizontalGroup(background1Layout.createParallelGroup(Alignment.LEADING)
				.addComponent(txt, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
				.addComponent(lbTitle, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE));

		background1Layout.setVerticalGroup(background1Layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(background1Layout.createSequentialGroup().addGap(96)
						.addComponent(lbTitle, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txt, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)));

		panel.setLayout(new GridLayout(0, 2, 0, 0));

		btnNewButton = new NButton("Cancel");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(245, 71, 71));

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				messageType = MessageType.CANCEL;

				closeMessage();

			}

		});

		btnNewButton_1 = new NButton("Ok");

		btnNewButton_1.setForeground(Color.WHITE);

		btnNewButton_1.setBackground(new Color(69, 191, 71));

		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				messageType = MessageType.OK;

				closeMessage();

			}

		});

		if (cancelFirst) {

			panel.add(btnNewButton);

			panel.add(btnNewButton_1);

		}

		else {

			panel.add(btnNewButton_1);

			panel.add(btnNewButton);

		}

		this.background1.setLayout(background1Layout);

		getContentPane().add(this.background1);

		pack();

	}

	public enum MessageType {

		CANCEL, OK;

	}

}
