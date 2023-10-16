package com.scrollbar;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.textarea.TextArea;

@SuppressWarnings("serial")
public class MaterialPanelDeslizante extends JScrollPane {

	private Color fg;

	private Color bg;

	@Override

	public void setForeground(Color fg) {

		this.fg = fg;

		setVerticalScrollBar(new ScrollBarCustom(fg, bg));

	}

	@Override
	public void setBackground(Color bg) {

		this.bg = bg;

		setVerticalScrollBar(new ScrollBarCustom(fg, bg));

	}

	public void setColors(Color foreground, Color background) {

		setVerticalScrollBar(new ScrollBarCustom(foreground, background));

	}

	public MaterialPanelDeslizante(TextArea textArea, Color select, Color background) {

		super(textArea);

		setVerticalScrollBar(new ScrollBarCustom(select, background));

	}

	public MaterialPanelDeslizante(JTextArea textArea, Color select, Color background) {

		super(textArea);

		setVerticalScrollBar(new ScrollBarCustom(select, background));

		ScrollBarCustom test = new ScrollBarCustom(select, background);

		test.setOrientation(JScrollBar.HORIZONTAL);

		setHorizontalScrollBar(test);

	}

	public MaterialPanelDeslizante(JPanel panel, Color select, Color background) {

		super(panel);

		setVerticalScrollBar(new ScrollBarCustom(select, background));

		ScrollBarCustom test = new ScrollBarCustom(select, background);

		test.setOrientation(JScrollBar.HORIZONTAL);

		setHorizontalScrollBar(test);

	}

}