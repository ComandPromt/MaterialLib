package checkbox;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")

public class CheckBoxLabel extends JPanel {

	private CheckBoxCustom checkBox;

	private JLabel label;

	@Override
	public void setFont(Font font) {

		try {

			label.setFont(font);

		}

		catch (Exception e) {

		}

	}

	public void setIcon(Icon icon) {

		try {

			label.setIcon(icon);

		}

		catch (Exception e) {

		}

	}

	public void setCheckBorder(int thickness) {

		try {

			checkBox.setBorder(thickness);

		}

		catch (Exception e) {

		}

	}

	public void setCheckColor(Color color) {

		try {

			checkBox.setCheckColor(color);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setBackground(Color bg) {

		try {

			checkBox.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public void setLabel(JLabel label) {

		this.label = label;

	}

	public JLabel getLabel() {

		return label;

	}

	public void setText(String text) {

		label.setText(text);

	}

	public boolean isSelected() {

		return this.checkBox.isSelected();

	}

	public CheckBoxLabel(Icon image) {

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setOpaque(false);

		this.checkBox = new CheckBoxCustom("");

		add(this.checkBox);

		label = new JLabel("");

		label.setIcon(image);

		label.setHorizontalAlignment(SwingConstants.LEFT);

		add(label);

	}

	public CheckBoxLabel(String text) {

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setOpaque(false);

		this.checkBox = new CheckBoxCustom();

		add(this.checkBox);

		label = new JLabel(text);

		label.setHorizontalAlignment(SwingConstants.LEFT);

		add(label);

	}

	public CheckBoxLabel(String text, Icon image) {

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setOpaque(false);

		this.checkBox = new CheckBoxCustom("");

		add(this.checkBox);

		label = new JLabel(text);

		label.setHorizontalAlignment(SwingConstants.LEFT);

		label.setIcon(image);

		add(label);

	}

}