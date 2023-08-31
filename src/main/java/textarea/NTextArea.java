package textarea;

import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")

public class NTextArea extends TextAreaScroll {

	private TextArea textArea;

	public void setWrapStyleWord(boolean active) {

		textArea.setWrapStyleWord(active);

	}

	public void setText(String text) {

		textArea.setText(text);

	}

	public void setFont(Font font) {

		try {

			textArea.setFont(font);

		} catch (Exception e) {

		}

	}

	public void setHeaderBackground(Color bg) {

		try {

			super.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public void setBackgroundText(Color bg) {

		try {

			textArea.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public void setTextColor(Color fg) {

		try {

			textArea.setForeground(fg);

		}

		catch (Exception e) {

		}

	}

	public void setSelectionColor(Color selectionColor) {

		textArea.setSelectionColor(selectionColor);

	}

	public NTextArea(String text) {

		textArea = new TextArea(null, null, null, null);

		setHeader(text);

		textArea.setColumns(20);

		textArea.setRows(5);

		setSelectionColor(Color.WHITE);

		setViewportView(textArea);

	}

	public TextArea getTextArea() {

		return textArea;

	}

}
