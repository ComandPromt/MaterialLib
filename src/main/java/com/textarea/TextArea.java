package com.textarea;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextArea;
import javax.swing.JToolTip;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jdesktop.animation.timing.Animator;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")
public class TextArea extends JTextArea {

	private TextAreaScroll scroll;

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

	public TextArea(Color foregroundScroll, Color backgroundScroll) {

		this(null, null, foregroundScroll, backgroundScroll);

	}

	public TextArea() {

		this(null, null, null, null);

	}

	public TextArea(Color selectionColor, Color lineColor, Color scrollColor, Color scrollLineColor) {

		if (lineColor == null) {

			lineColor = new Color(3, 155, 216);

		}

		setWrapStyleWord(true);

		setLineWrap(true);

		setBorder(new EmptyBorder(0, 0, 0, 0));

		if (selectionColor == null) {

			selectionColor = new Color(223, 223, 223);

		}

		setSelectionColor(selectionColor);

		getDocument().addDocumentListener(new DocumentListener() {

			@Override

			public void insertUpdate(DocumentEvent e) {

				try {

					getScroll().setAnimateHinText(getText().equals(""));

				}

				catch (Exception e1) {

				}

			}

			@Override
			public void removeUpdate(DocumentEvent e) {

				try {

					getScroll().setAnimateHinText(getText().equals(""));

				}

				catch (Exception e1) {

				}

			}

			@Override
			public void changedUpdate(DocumentEvent e) {

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

		setFont(new Font("Dialog", Font.PLAIN, 20));

	}

	private void showing(boolean action) {

		try {

			TextAreaScroll s = getScroll();

			Animator animator = s.getAnimator();

			float location = s.getAnimateLocation();

			if (animator.isRunning()) {

				animator.stop();

			}

			else {

				location = 1;

			}

			animator.setStartFraction(1f - location);

			s.setShow(action);

			location = 1f - location;

			s.setAnimateLocation(location);

			animator.start();

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setText(String string) {

		if (!getText().equals(string)) {

			showing(string.equals(""));

		}

		super.setText(string);

	}

	public TextAreaScroll getScroll() {

		try {

			if (scroll == null) {

				Component com = getParent();

				JViewport view = (JViewport) com;

				scroll = (TextAreaScroll) view.getParent();

			}

			scroll.setLineColor(scroll.getLineColor());

			scroll.setColors(scroll.getScrollForeground(), scroll.getScrollBackground());

			return scroll;

		}

		catch (Exception e) {

			return null;

		}

	}

}
