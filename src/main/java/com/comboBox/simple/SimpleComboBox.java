package com.comboBox.simple;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import com.scrollbar.ScrollBarCustom;
import com.toolTip.ToolTipLlamada;

@SuppressWarnings({ "rawtypes", "serial" })

public class SimpleComboBox extends JComboBox {

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	private String labeText;

	private Color lineColor;

	private Color hoverLineColor;

	private boolean mouseOver;

	private Color colorSeleccion;

	private Color selectionForegroundColor;

	private Color foregroundMenuColor;

	private Color fondoSeleccion;

	private Color colorFlecha;

	private ComboUI combo;

	private Color hintTextColor;

	@Override
	public void setForeground(Color fg) {

		foregroundMenuColor = fg;

		repaint();

	}

	public Color getHintTextColor() {

		return hintTextColor;

	}

	public void setHintTextColor(Color hintTextColor) {

		this.hintTextColor = hintTextColor;

		repaint();

	}

	public void setArrowSize(float arrowSize) {

		combo.setArrowSize(arrowSize);

	}

	public Color getColorFlecha() {

		return colorFlecha;

	}

	public void setColorFlecha(Color colorFlecha) {

		combo.setColorFlecha(colorFlecha);

	}

	public Color getHoverLineColor() {

		return hoverLineColor;

	}

	public void setHoverLineColor(Color hoverLineColor) {

		this.hoverLineColor = hoverLineColor;

		repaint();

	}

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

	public void setFondoFlecha(Color fondoFlecha) {

		combo.setFondoFlecha(fondoFlecha);

	}

	public void setGrosor(int grosor) {

		combo.setGrosor(grosor);

	}

	public String getLabeText() {

		return labeText;

	}

	public void setLabeText(String labeText) {

		this.labeText = labeText;

	}

	public Color getLineColor() {

		return lineColor;

	}

	public void setLineColor(Color lineColor) {

		this.lineColor = lineColor;

	}

	public Color getColorSeleccion() {

		return colorSeleccion;

	}

	public Color getFondoSeleccion() {

		return fondoSeleccion;

	}

	public Color getSelectionForegroundColor() {

		return selectionForegroundColor;

	}

	public Color getForegroundMenuColor() {

		return foregroundMenuColor;

	}

	public void setSelectionForegroundColor(Color selectionForegroundColor) {

		this.selectionForegroundColor = selectionForegroundColor;

		repaint();

	}

	public void setForegroundMenuColor(Color foregroundMenuColor) {

		this.foregroundMenuColor = foregroundMenuColor;

		repaint();

	}

	public void setColorSeleccion(Color colorSeleccion) {

		this.colorSeleccion = colorSeleccion;

		repaint();

	}

	public void setFondoSeleccion(Color fondoSeleccion) {

		this.fondoSeleccion = fondoSeleccion;

		repaint();

	}

	public SimpleComboBox() {

		this(Color.GRAY, Color.WHITE, Color.WHITE, 1);

	}

	@SuppressWarnings("unchecked")

	public SimpleComboBox(Color scrollColor, Color scrollLine, Color bordeColor, int grosor) {

		setEditable(true);

		colorSeleccion = Color.WHITE;

		fondoSeleccion = Color.WHITE;

		setFont(getFont().deriveFont(Font.PLAIN, 20f));

		labeText = "Label";

		lineColor = new Color(3, 155, 216);

		setBackground(Color.WHITE);

		setBorder(new EmptyBorder(15, 3, 5, 3));

		combo = new ComboUI(this, scrollColor, scrollLine, bordeColor, grosor);

		setFondoFlecha(Color.WHITE);

		setColorFlecha(Color.BLACK);

		setUI(combo);

		setRenderer(new DefaultListCellRenderer() {

			@Override

			public Component getListCellRendererComponent(JList<?> jlist, Object o, int i, boolean bln, boolean bln1) {

				Component com = super.getListCellRendererComponent(jlist, o, i, bln, bln1);

				setBorder(new EmptyBorder(5, 5, 5, 5));

				if (bln) {

					com.setForeground(selectionForegroundColor);

					com.setBackground(colorSeleccion);

				}

				else {

					com.setForeground(foregroundMenuColor);

					com.setBackground(fondoSeleccion);

				}

				return com;

			}

		});

		setArrowSize(1.4f);

	}

	private class ComboUI extends BasicComboBoxUI {

		private Color colorFlecha;

		private Color fondoFlecha;

		private float arrowSize;

		private final Animator animator;

		private float location;

		private boolean show;

		private Color bordeColor;

		private int grosor;

		private Color scrollColor;

		private Color scrollLine;

		public void setGrosor(int grosor) {

			this.grosor = grosor;

			repaint();

		}

		public void setArrowSize(float arrowSize) {

			this.arrowSize = arrowSize;

			repaint();

		}

		public void setColorFlecha(Color colorFlecha) {

			this.colorFlecha = colorFlecha;

			repaint();

		}

		public void setFondoFlecha(Color fondoFlecha) {

			this.fondoFlecha = fondoFlecha;

			repaint();

		}

		public ComboUI(SimpleComboBox combo, Color scrollColor, Color scrollLine, Color bordeColor, int grosor) {

			this.bordeColor = bordeColor;

			if (grosor < 1) {

				grosor = 1;

			}

			this.grosor = grosor;

			this.scrollColor = scrollColor;

			this.scrollLine = scrollLine;

			arrowSize = 1.4f;

			bordeColor = new Color(200, 200, 200);

			addMouseListener(new MouseAdapter() {

				@Override

				public void mouseEntered(MouseEvent me) {

					mouseOver = true;

					repaint();

				}

				@Override

				public void mouseExited(MouseEvent me) {

					mouseOver = false;

					repaint();

				}

			});

			addMouseWheelListener(new MouseWheelListener() {

				@Override
				public void mouseWheelMoved(MouseWheelEvent e) {

					int valorIndice = getSelectedIndex();

					if (e.getWheelRotation() < 0) {

						valorIndice--;

					}

					else {

						valorIndice++;

					}

					if (valorIndice < 0) {

						valorIndice = 0;

					}

					if (valorIndice >= getItemCount()) {

						valorIndice = getItemCount();

						valorIndice--;

					}

					setSelectedIndex(valorIndice);

				}

			});

			addFocusListener(new FocusAdapter() {

				@Override
				public void focusGained(FocusEvent fe) {

					showing(false);

				}

				@Override
				public void focusLost(FocusEvent fe) {

					showing(true);

				}

			});

			addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent ie) {

					showing((!isFocusOwner() && (getSelectedIndex() == -1)));

				}

			});

			addPopupMenuListener(new PopupMenuListener() {

				@Override
				public void popupMenuWillBecomeVisible(PopupMenuEvent pme) {

					arrowButton.setBackground(new Color(200, 200, 200));

				}

				@Override
				public void popupMenuWillBecomeInvisible(PopupMenuEvent pme) {

					arrowButton.setBackground(new Color(150, 150, 150));

				}

				@Override
				public void popupMenuCanceled(PopupMenuEvent pme) {

					arrowButton.setBackground(new Color(150, 150, 150));

				}

			});

			TimingTarget target = new TimingTargetAdapter() {

				@Override
				public void timingEvent(float fraction) {

					location = fraction;

					repaint();

				}

			};

			animator = new Animator(300, target);

			animator.setResolution(0);

			animator.setAcceleration(0.5f);

			animator.setDeceleration(0.5f);

		}

		@Override
		protected JButton createArrowButton() {

			return new ArrowButton();

		}

		@Override
		protected ComboPopup createPopup() {

			BasicComboPopup pop = new BasicComboPopup(comboBox) {
				@Override
				protected JScrollPane createScroller() {

					list.setFixedCellHeight(30);

					JScrollPane scroll = new JScrollPane(list);

					ScrollBarCustom sb = new ScrollBarCustom(null, null);

					sb.setUnitIncrement(30);

					sb.setBackground(scrollLine);

					sb.setForeground(scrollColor);

					scroll.setVerticalScrollBar(sb);

					return scroll;

				}

			};

			pop.setBorder(new LineBorder(bordeColor, grosor));

			return pop;

		}

		@Override
		public void paint(Graphics grphcs, JComponent jc) {

			super.paint(grphcs, jc);

			Graphics2D g2 = (Graphics2D) grphcs;

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

			int width = getWidth();

			int height = getHeight();

			if (mouseOver) {

				g2.setColor(hoverLineColor);

			}

			else {

				g2.setColor(lineColor);

			}

			g2.fillRect(2, height - 1, width - 4, 1);

			createHintText(g2);

			createLineStyle(g2);

			g2.dispose();

		}

		private void createHintText(Graphics2D g2) {

			Insets in = getInsets();

			g2.setColor(hintTextColor);

			g2.drawString(labeText, in.right, getFont().getSize() - 5);

		}

		private void createLineStyle(Graphics2D g2) {

			if (isFocusOwner()) {

				double width = getWidth() - 4;

				int height = getHeight();

				g2.setColor(lineColor);

				double size;

				if (show) {

					size = width * (1 - location);

				}

				else {

					size = width * location;

				}

				double x = (width - size) / 2;

				g2.fillRect((int) (x + 2), height - 2, (int) size, 2);

			}

		}

		private void showing(boolean action) {

			if (animator.isRunning()) {

				animator.stop();

			}

			else {

				location = 1;

			}

			animator.setStartFraction(1f - location);

			show = action;

			location = 1f - location;

			animator.start();

		}

		private class ArrowButton extends JButton {

			public ArrowButton() {

				setContentAreaFilled(false);

				setBorder(new EmptyBorder(5, 5, 5, 5));

				setBackground(new Color(150, 150, 150));

			}

			@Override
			public void paint(Graphics grphcs) {

				super.paint(grphcs);

				Graphics2D g2 = (Graphics2D) grphcs;

				g2.setColor(fondoFlecha);

				g2.fillRect(0, 0, getWidth(), getHeight());

				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				int width = getWidth();

				int height = getHeight();

				int size = (int) (getHeight() / arrowSize);

				int x = (width - size) / 2;

				int y = (height - size) / 2 + 5;

				int[] px = { x, x + size, x + size / 2 };

				int[] py = { y, y, y + size };

				g2.setColor(colorFlecha);

				g2.fillPolygon(px, py, px.length);

				g2.dispose();

			}

		}

	}

}
