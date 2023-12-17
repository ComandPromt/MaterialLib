package com.comboBox.comboSuggestion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.scrollbar.ScrollBarCustom;

public class ComboSuggestionUI extends BasicComboBoxUI {

	private Color borderColor;

	private Color arrowVisible;

	private Color arrowInvisible;

	private Color selectionColor;

	private Color selectionItemColor;

	private Color selectionForegroundColor;

	private Color menuColor;

	private Color foregroundMenuColor;

	private Color lineScrollColor;

	private Color scrollColor;

	public Color getBorderColor() {

		return borderColor;

	}

	public Color getArrowVisible() {

		return arrowVisible;

	}

	public Color getArrowInvisible() {

		return arrowInvisible;

	}

	public Color getSelectionItemColor() {

		return selectionItemColor;

	}

	public Color getSelectionForegroundColor() {

		return selectionForegroundColor;

	}

	public Color getMenuColor() {

		return menuColor;

	}

	public Color getForegroundMenuColor() {

		return foregroundMenuColor;

	}

	public void setBorderColor(Color borderColor) {

		this.borderColor = borderColor;

	}

	public void setArrowVisible(Color arrowVisible) {

		this.arrowVisible = arrowVisible;

	}

	public void setArrowInvisible(Color arrowInvisible) {

		this.arrowInvisible = arrowInvisible;

	}

	public void setSelectionItemColor(Color selectionItemColor) {

		this.selectionItemColor = selectionItemColor;

	}

	public void setSelectionForegroundColor(Color selectionForegroundColor) {

		this.selectionForegroundColor = selectionForegroundColor;

	}

	public void setMenuColor(Color menuColor) {

		this.menuColor = menuColor;

	}

	public void setForegroundMenuColor(Color foregroundMenuColor) {

		this.foregroundMenuColor = foregroundMenuColor;

	}

	public ComboSuggestionUI(Color scrollColor, Color lineScrollColor) {

		this.scrollColor = scrollColor;

		this.lineScrollColor = lineScrollColor;

		selectionColor = new Color(236, 236, 236);

		borderColor = new Color(128, 189, 255);

		arrowVisible = new Color(180, 180, 180);

		arrowInvisible = new Color(150, 150, 150);

		selectionItemColor = new Color(240, 240, 240);

		selectionForegroundColor = new Color(17, 155, 215);

		menuColor = Color.WHITE;

		foregroundMenuColor = Color.BLACK;

	}

	public ComboSuggestionUI(Color lineScrollColor) {

		this.lineScrollColor = lineScrollColor;

		selectionColor = new Color(236, 236, 236);

		borderColor = new Color(128, 189, 255);

		arrowVisible = new Color(180, 180, 180);

		arrowInvisible = new Color(150, 150, 150);

		selectionItemColor = new Color(240, 240, 240);

		selectionForegroundColor = new Color(17, 155, 215);

		menuColor = Color.WHITE;

		foregroundMenuColor = Color.BLACK;

	}

	@Override
	public void installUI(JComponent jc) {

		super.installUI(jc);

		Border border = new Border();

		JTextField txt = (JTextField) comboBox.getEditor().getEditorComponent();

		txt.addFocusListener(new FocusAdapter() {

			@Override

			public void focusGained(FocusEvent fe) {

				border.setColor(borderColor);

			}

			@Override

			public void focusLost(FocusEvent fe) {

				border.setColor(new Color(206, 212, 218));

			}

		});

		comboBox.addPopupMenuListener(new PopupMenuListener() {

			@Override

			public void popupMenuWillBecomeVisible(PopupMenuEvent pme) {

				arrowButton.setBackground(arrowVisible);

			}

			@Override

			public void popupMenuWillBecomeInvisible(PopupMenuEvent pme) {

				arrowButton.setBackground(arrowInvisible);

			}

			@Override

			public void popupMenuCanceled(PopupMenuEvent pme) {

				arrowButton.setBackground(new Color(150, 150, 150));

			}

		});

		AutoCompleteDecorator.decorate(comboBox);

		txt.setSelectionColor(selectionColor);

		txt.setBorder(new EmptyBorder(0, 4, 0, 4));

		comboBox.setBackground(Color.WHITE);

		comboBox.setBorder(border);

	}

	@Override

	protected JButton createArrowButton() {

		return new ArrowButton();

	}

	@Override

	protected ComboPopup createPopup() {

		return new ComboSuggestionPopup(comboBox);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })

	@Override

	protected ListCellRenderer createRenderer() {

		return new ListCellRenderer() {

			@Override

			public Component getListCellRendererComponent(JList jlist, Object e, int i, boolean bln, boolean bln1) {

				String text = e == null ? "" : e.toString();

				JLabel label = new JLabel(text);

				label.setFont(comboBox.getFont());

				if (i >= 0) {

					label.setBorder(new EmptyBorder(5, 8, 5, 8));

				}

				else {

					label.setBorder(null);

				}

				label.setOpaque(true);

				if (bln) {

					label.setBackground(selectionItemColor);

					label.setForeground(selectionForegroundColor);

				}

				else {

					label.setBackground(menuColor);

					label.setForeground(foregroundMenuColor);

				}

				return label;

			}

		};

	}

	@SuppressWarnings("serial")
	private class ComboSuggestionPopup extends BasicComboPopup {

		@SuppressWarnings("unchecked")

		public ComboSuggestionPopup(@SuppressWarnings("rawtypes") JComboBox combo) {

			super(combo);

			setBorder(new Border(1));

		}

		@Override

		protected JScrollPane createScroller() {

			JScrollPane scroll = super.createScroller();

			ScrollBarCustom sb = new ScrollBarCustom(null, null);

			if (scrollColor != null) {

				sb.setForeground(scrollColor);

			}

			sb.setBackground(lineScrollColor);

			sb.setPreferredSize(new Dimension(12, 70));

			scroll.setVerticalScrollBar(sb);

			return scroll;

		}

	}

	@SuppressWarnings("serial")

	private class ArrowButton extends JButton {

		public ArrowButton() {

			setContentAreaFilled(false);

			setBorder(new EmptyBorder(5, 5, 5, 5));

			setBackground(arrowInvisible);

		}

		@Override

		public void paint(Graphics grphcs) {

			super.paint(grphcs);

			Graphics2D g2 = (Graphics2D) grphcs.create();

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			int width = getWidth();

			int height = getHeight();

			int sizeX = 12;

			int sizeY = 8;

			int x = (width - sizeX) / 2;

			int y = (height - sizeY) / 2;

			int px[] = { x, x + sizeX, x + sizeX / 2 };

			int py[] = { y, y, y + sizeY };

			g2.setColor(getBackground());

			g2.fillPolygon(px, py, px.length);

			g2.dispose();

		}

	}

	@SuppressWarnings("serial")
	private class Border extends EmptyBorder {

		private Color focusColor;

		private Color color;

		@SuppressWarnings("unused")
		public Color getFocusColor() {

			return focusColor;

		}

		@SuppressWarnings("unused")
		public void setFocusColor(Color focusColor) {

			this.focusColor = focusColor;

		}

		@SuppressWarnings("unused")
		public Color getColor() {

			return color;

		}

		public void setColor(Color color) {

			this.color = color;

		}

		public Border(int border) {

			super(border, border, border, border);

			focusColor = new Color(128, 189, 255);

			color = new Color(206, 212, 218);

		}

		public Border() {

			this(5);

		}

		@Override
		public void paintBorder(Component cmpnt, Graphics grphcs, int x, int y, int width, int height) {

			Graphics2D g2 = (Graphics2D) grphcs.create();

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			if (cmpnt.isFocusOwner()) {

				g2.setColor(focusColor);

			}

			else {

				g2.setColor(color);

			}

			g2.drawRect(x, y, width - 1, height - 1);

			g2.dispose();

		}

	}

	public void setLineScrollColor(Color color) {

		this.lineScrollColor = color;

	}

}
