package com.draganddrop;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;
import java.util.TooManyListenersException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.buttons.simple.SimpleButton;
import com.layout.VerticalGridLayout;
import com.materialfilechooser.JFileChooserPanel;
import com.toolTip.ToolTipLlamada;

import mthos.JMthos;

@SuppressWarnings("serial")
public class FileDragAndDrop extends JPanel {

	public TitledBorder dragBorder;

	private int thickness;

	private Shape rect;

	private boolean round;

	private boolean dashed;

	private int pattern;

	private JLabel texto;

	private JFileChooserPanel boton;

	private JPanel panel1;

	private SimpleButton boton1;

	private LinkedList<String> lista;

	private String descripcion;

	private String text;

	private Color fondo;

	private Color colorFondo;

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

				font = getFont().deriveFont(14f);

			}

			catch (Exception e) {

				font = new Font("Dialog", Font.PLAIN, 14);

			}

		}

		this.text = text;

		this.fondo = background;

		this.colorFondo = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || fondo == null || colorFondo == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, colorFondo, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public LinkedList<String> getList() {

		return lista;

	}

	public void setList(LinkedList<String> lista) {

		this.lista = lista;

	}

	public int getPattern() {

		return pattern;

	}

	public void setPattern(int pattern) {

		this.pattern = pattern;

		repaint();

	}

	public boolean isDashed() {

		return dashed;

	}

	public void setDashed(boolean dashed) {

		this.dashed = dashed;

		repaint();

	}

	public boolean isRound() {

		return round;

	}

	public void setRound(boolean round) {

		this.round = round;

		repaint();

	}

	public int getThickness() {

		return thickness;

	}

	public void setThickness(int thickness) {

		if (thickness < 1) {

			thickness = 1;

		}

		this.thickness = thickness;

		repaint();

	}

	@Override
	public void setFont(Font font) {

		try {

			boton.setFont(font);

			texto.setFont(font);

		}

		catch (Exception e) {

		}

	}

	private void add(String string) {

		try {

			if (!lista.contains(string)) {

				lista.add(string);

			}

		}

		catch (Exception e) {

		}

	}

	public FileDragAndDrop(JFrame frame, String button, String text) {

		setFocusable(false);

		setBorder(new EmptyBorder(5, 5, 5, 5));

		descripcion = text;

		lista = new LinkedList<>();

		thickness = 1;

		round = true;

		dashed = true;

		pattern = 10;

		setFont(new Font("Dialog", Font.PLAIN, 25));

		setForeground(SystemColor.desktop);

		setBackground(Color.WHITE);

		this.dragBorder = new javax.swing.border.TitledBorder("");

		setLayout(new GridLayout(1, 0, 0, 0));

		DragAndDrop panel = new DragAndDrop("");

		panel.setThickness(0);

		panel.setBackground(Color.WHITE);

		panel.setLayout(new VerticalGridLayout(3, 1));

		try {

			new UtilDragAndDrop(panel, panel.dragBorder, true, new UtilDragAndDrop.Listener() {

				@Override

				public void filesDropped(java.io.File[] archivos) {

					if (archivos.length == 1) {

						add(archivos[0].toString());

						if (lista.size() == 1) {

							texto.setText(JMthos.saberNombreArchivo(lista.getFirst()));

						}

						else {

							texto.setText(JMthos.saberNombreArchivo(lista.getLast()));

						}

					}

					else {

						for (int i = 0; i < archivos.length; i++) {

							add(archivos[i].toString());

						}

						texto.setText(lista.size() + " archivos");

					}

				}

			});

		}

		catch (TooManyListenersException e1) {

		}

		add(panel);

		boton = new JFileChooserPanel(button);

		panel.add(boton);

		panel1 = new JPanel();

		panel1.setBackground(Color.WHITE);

		boton.add(panel1);

		boton1 = new SimpleButton("");

		boton1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				lista.clear();

				texto.setText(descripcion);

			}

		});

		boton1.setBorderColor(Color.WHITE);

		boton1.setBackground(Color.WHITE);

		boton1.setIcon(new ImageIcon(FileDragAndDrop.class.getResource("/imgs/imagenes/clean.png")));

		panel1.add(boton1);

		texto = new JLabel(text);

		texto.setFont(new Font("Dialog", Font.BOLD, 25));

		texto.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(texto);

	}

	@Override
	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		if (dashed) {

			g2d.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10,
					new float[] { pattern, pattern }, 0));

		}

		else {

			g2d.setStroke(new BasicStroke(thickness));

		}

		if (round) {

			rect = new RoundRectangle2D.Double(thickness, thickness, getWidth() - (thickness * 2),
					getHeight() - (thickness * 2), 20, 20);

		}

		else {

			rect = new Rectangle2D.Double(thickness, thickness, getWidth() - (thickness * 2),

					getHeight() - (thickness * 2));

		}

		g2d.draw(rect);

		g2d.setColor(getBackground());

		g2d.fill(rect);

		super.paint(g);

	}

}
