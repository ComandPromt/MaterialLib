package com.search;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

@SuppressWarnings("serial")
public class CustomTextAreaScroll extends JScrollPane {

	private JTextPane textPane;

	public CustomTextAreaScroll(JTextPane textPane) {

		super(textPane);

		this.textPane = textPane;

		initialize();

	}

	private void initialize() {

		setBorder(BorderFactory.createLineBorder(Color.GRAY));

		setPreferredSize(new Dimension(250, 100));

		textPane.setFont(new Font("Arial", Font.PLAIN, 14));

		textPane.setEditable(false);

		DefaultCaret caret = (DefaultCaret) textPane.getCaret();

		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

	}

	public JTextPane getTextPane() {

		return textPane;

	}

	public void appendText(String text, Color textColor) {

		StyledDocument doc = textPane.getStyledDocument();

		Style style = textPane.addStyle("Style", null);

		StyleConstants.setForeground(style, textColor);

		try {

			doc.insertString(doc.getLength(), text + "\n", style);

		}

		catch (BadLocationException e) {

		}

	}

	public void clearText() {

		textPane.setText("");

	}

}
