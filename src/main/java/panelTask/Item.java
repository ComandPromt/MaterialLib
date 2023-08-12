package panelTask;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Item extends javax.swing.JLayeredPane {

	private javax.swing.JButton cmd;

	private javax.swing.JLabel lbIcon;

	private javax.swing.JTextArea txt;

	private EventTags eventTags;

	private ActionListener eventRemove;

	private KeyAdapter eventKey;

	private EventInput event;

	private boolean input;

	private boolean over;

	private Color overBackgroundColor;

	private Color backgroundColor;

	private int thickness;

	private Color borderColor;

	boolean isInput() {

		return input;

	}

	void addEventForInput(EventInput event) {

		this.event = event;

		input = true;

		txt.setFocusable(true);

		cmd.setIcon(null);

	}

	javax.swing.JTextArea getTxt() {

		return txt;

	}

	void addEventRemove(ActionListener event) {

		this.eventRemove = event;

	}

	void addEventKey(KeyAdapter event) {

		this.eventKey = event;

	}

	void setEventTags(EventTags event) {

		this.eventTags = event;

	}

	Item(String text, Color border, Color overBackground, Color background) {

		borderColor = border;

		overBackgroundColor = overBackground;

		thickness = 1;

		initComponents();

		txt.setText(text);

		txt.setFocusable(false);

		backgroundColor = background;

		lbIcon.setVisible(false);

	}

	public void setIcon(Icon icon) {

		lbIcon.setVisible(icon != null);

		lbIcon.setIcon(icon);

	}

	void addEventMouse() {

		addMouseEvent(this);

		addMouseEvent(cmd);

		addMouseEvent(txt);

	}

	private void addMouseEvent(Component com) {

		com.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseEntered(MouseEvent me) {

				over = true;

				repaint();

			}

			@Override

			public void mouseExited(MouseEvent me) {

				over = false;

				repaint();

			}

		});

	}

	private void initComponents() {

		txt = new Text();

		cmd = new javax.swing.JButton();

		lbIcon = new javax.swing.JLabel();

		addFocusListener(new java.awt.event.FocusAdapter() {

			@Override

			public void focusGained(java.awt.event.FocusEvent evt) {

				formFocusGained(evt);

			}

		});

		txt.setColumns(20);

		txt.setRows(5);

		txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

		txt.addFocusListener(new java.awt.event.FocusAdapter() {

			@Override

			public void focusLost(java.awt.event.FocusEvent evt) {

				txtFocusLost(evt);

			}

		});

		txt.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override

			public void mouseClicked(java.awt.event.MouseEvent evt) {

				txtMouseClicked(evt);

			}

		});

		txt.addKeyListener(new java.awt.event.KeyAdapter() {

			@Override

			public void keyReleased(java.awt.event.KeyEvent evt) {

				txtKeyReleased(evt);

			}

			@Override

			public void keyTyped(java.awt.event.KeyEvent evt) {

				txtKeyTyped(evt);

			}

		});

		cmd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/panelTask/close.png"))); // NOI18N

		cmd.setBorder(null);

		cmd.setContentAreaFilled(false);

		cmd.setFocusable(false);

		cmd.addActionListener(new java.awt.event.ActionListener() {

			@Override

			public void actionPerformed(java.awt.event.ActionEvent evt) {

				cmdActionPerformed(evt);

			}

		});

		lbIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		setLayer(txt, javax.swing.JLayeredPane.DEFAULT_LAYER);

		setLayer(cmd, javax.swing.JLayeredPane.DEFAULT_LAYER);

		setLayer(lbIcon, javax.swing.JLayeredPane.DEFAULT_LAYER);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);

		this.setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(3, 3, 3).addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, 11, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(cmd,
								javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(3, 3, 3)));

		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(3, 3, 3)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(cmd, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));

	}

	private void txtKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtKeyTyped

		if (evt.getKeyChar() == 10) {

			addItem();

		}

	}

	private void txtMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_txtMouseClicked

		if (SwingUtilities.isLeftMouseButton(evt)) {

			txt.setFocusable(true);

			txt.grabFocus();

		}

	}

	private void txtFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtFocusLost

		if (input) {

			addItem();

		}

		else {

			txt.setFocusable(false);

		}

	}

	private void cmdActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cmdActionPerformed

		if (eventRemove != null) {

			eventRemove.actionPerformed(evt);

		}

	}

	private void txtKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtKeyReleased

		eventKey.keyReleased(evt);

	}

	private void formFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_formFocusGained

		txt.grabFocus();

	}

	private void addItem() {

		String text = txt.getText().trim().replace("\n", "");

		if (eventTags != null && !eventTags.isAddAble(this, text)) {

			txt.setText(text);

			return;

		}

		if (input) {

			txt.setText("");

		}

		else {

			txt.setText(text);

			txt.setFocusable(false);

			if (eventTags != null) {

				eventTags.onEdit(this, text);

			}

		}

		if (!text.equals("") && input) {

			event.addItem(text);

		}

	}

	@Override
	public void setBackground(Color color) {

		try {

			this.backgroundColor = color;

			repaint();

			revalidate();

		}

		catch (Exception e) {

		}

	}

	void setOverBackgroundColor(Color color) {

		try {

			this.overBackgroundColor = color;

			repaint();

		}

		catch (Exception e) {

		}

	}

	void setThickness(int thickness) {

		try {

			this.thickness = thickness;

			repaint();

		}

		catch (Exception e) {

		}

	}

	@Override

	protected void paintComponent(Graphics grphcs) {

		if (!input) {

			Graphics2D g2 = (Graphics2D) grphcs;

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			if (over) {

				g2.setColor(overBackgroundColor);

				txt.setBackground(overBackgroundColor);

				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

				g2.setStroke(new BasicStroke(thickness));

				g2.setColor(borderColor);

				g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

			}

			else {

				g2.setColor(backgroundColor);

				txt.setBackground(backgroundColor);

				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

			}

		}

		super.paintComponent(grphcs);

	}

	String getText() {

		return txt.getText().trim();

	}

}
