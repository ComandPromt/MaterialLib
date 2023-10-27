package com.tabbedPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolTip;

import com.panels.image.ImagePanel;
import com.toolTip.ToolTipLlamada;

public class TabbedPaneTask extends JTabbedPane {

	private static final long serialVersionUID = 1L;

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

	public TabbedPaneTask() {

	}

	@Override
	public void addTab(String title, Component component) {

		super.addTab(title, component);

		int i = getTabCount();

		setTabComponentAt(i - 1, new PanelTitle(title));

	}

	@Override
	public void addTab(String title, Icon icon, Component cmpnt) {

		super.addTab(title, icon, cmpnt);

		int i = getTabCount();

		setTabComponentAt(i - 1, new PanelTitle(title, icon));

	}

	@Override
	public void addTab(String title, Icon icon, Component cmpnt, String tooltip) {

		super.addTab(title, icon, cmpnt, tooltip);

		int i = getTabCount();

		setTabComponentAt(i - 1, new PanelTitle(title, icon, tooltip));

	}

	private class PanelTitle extends JPanel {

		private static final long serialVersionUID = 1L;

		public PanelTitle(String title) {

			init(title, null, "");

		}

		public PanelTitle(String title, Icon icon) {

			init(title, icon, "");

		}

		public PanelTitle(String title, Icon icon, String tooltip) {

			init(title, icon, tooltip);

		}

		private void init(String title, Icon icon, String tooltip) {

			setPreferredSize(new Dimension(196, 64));

			setOpaque(false);

			JLabel lblTitle = new JLabel();

			ImagePanel lblIcon = new ImagePanel();

			JLabel lblDescripcion = new JLabel();

			setLayout(new java.awt.GridBagLayout());

			GridBagConstraints gridBagConstraints;

			lblTitle.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N

			lblTitle.setText(title);

			gridBagConstraints = new java.awt.GridBagConstraints();

			gridBagConstraints.gridx = 1;

			gridBagConstraints.gridy = 0;

			gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

			gridBagConstraints.ipadx = 76;

			gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;

			gridBagConstraints.insets = new java.awt.Insets(24, 6, 0, 13);

			add(lblTitle, gridBagConstraints);

			lblIcon.setIcon(icon);

			gridBagConstraints = new java.awt.GridBagConstraints();

			gridBagConstraints.gridx = 0;

			gridBagConstraints.gridy = 0;

			gridBagConstraints.gridheight = 2;

			gridBagConstraints.ipadx = 32;

			gridBagConstraints.ipady = 32;

			gridBagConstraints.insets = new java.awt.Insets(24, 13, 24, 0);

			add(lblIcon, gridBagConstraints);

			lblDescripcion.setText(tooltip);

			gridBagConstraints = new java.awt.GridBagConstraints();

			gridBagConstraints.gridx = 1;

			gridBagConstraints.gridy = 1;

			gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

			gridBagConstraints.ipadx = 76;

			gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

			gridBagConstraints.insets = new java.awt.Insets(0, 6, 24, 13);

			add(lblDescripcion, gridBagConstraints);

		}

	}

}
