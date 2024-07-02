package com.search;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JToolTip;

import com.jicons.MagnifyingGlass;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

class SearchItem extends javax.swing.JPanel {

	private javax.swing.JLabel lbIcon;

	private javax.swing.JLabel lbRemove;

	private javax.swing.JLabel lbText;

	private ActionListener eventClick;

	private ActionListener eventRemove;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	@Override
	public void setToolTipText(String text) {

		setToolTip(text, null, null, null, null);

	}

	public void setToolTip(String text, Color background, Color foreground, Color border, Font font) {

		calcularTooltip(text, background, foreground, border, font);

	}

	private void calcularTooltip(String text, Color background, Color foreground, Color border, Font font) {

		if (background == null) {

			background = new Color(32, 39, 55);

		}

		if (foreground == null) {

			foreground = Color.WHITE;

		}

		if (border == null) {

			border = new Color(173, 173, 173);

		}

		if (font == null) {

			try {

				font = getFont().deriveFont(20f);

			}

			catch (Exception e) {

				font = new Font("Dialog", Font.PLAIN, 20);

			}

		}

		this.text = text;

		this.fondo = background;

		this.colorTexto = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || fondo == null || colorTexto == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, colorTexto, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public SearchItem(DataSearch data) {

		initComponents();

		setData(data);

	}

	private void setData(DataSearch data) {

		addEventMouse(this);

		addEventMouse(lbText);

		addEventMouse(lbRemove);

		lbText.setText(data.getText());

		if (data.isStory()) {

			lbText.setForeground(new Color(29, 106, 205));

			lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/imagenes/time.png")));

			lbRemove.setCursor(new Cursor(Cursor.HAND_CURSOR));

		}

		else {

			lbRemove.setText("");

		}

	}

	private void addEventMouse(Component com) {

		com.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseEntered(MouseEvent me) {

				setBackground(new Color(215, 216, 216));

			}

			@Override
			public void mouseExited(MouseEvent me) {

				setBackground(Color.WHITE);

			}

		});

	}

	public void addEvent(ActionListener eventClick, ActionListener eventRemove) {

		this.eventClick = eventClick;

		this.eventRemove = eventRemove;

	}

	private void initComponents() {

		lbIcon = new javax.swing.JLabel();

		lbText = new javax.swing.JLabel();

		lbRemove = new javax.swing.JLabel();

		setBackground(new java.awt.Color(255, 255, 255));

		lbIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		lbIcon.setIcon(new MagnifyingGlass());

		lbText.setFont(new java.awt.Font("sansserif", 0, 14));

		lbText.setForeground(Color.RED);

		lbText.setText("Text ...");

		lbText.addMouseListener(new java.awt.event.MouseAdapter() {

			public void mouseClicked(java.awt.event.MouseEvent evt) {

				lbTextMouseClicked(evt);

			}

		});

		lbRemove.setForeground(new java.awt.Color(147, 147, 147));

		lbRemove.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		lbRemove.setText("Remove");

		lbRemove.addMouseListener(new java.awt.event.MouseAdapter() {

			public void mouseClicked(java.awt.event.MouseEvent evt) {

				lbRemoveMouseClicked(evt);

			}

		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);

		this.setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(lbText, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(lbRemove,
								javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(lbText, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE).addComponent(lbRemove,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

	}

	private void lbRemoveMouseClicked(java.awt.event.MouseEvent evt) {

		if (!lbRemove.getText().trim().equals("")) {

			eventRemove.actionPerformed(null);

		}

	}

	private void lbTextMouseClicked(java.awt.event.MouseEvent evt) {

		eventClick.actionPerformed(null);

	}

}
