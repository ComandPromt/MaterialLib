package com.textField.text;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.panels.round.MaterialPanel;

@SuppressWarnings("serial")
public class TextFieldWithShadow extends JPanel {

	private JTextField textField;

	private MaterialPanel panel;

	public void setElevation(int elevation) {

		try {

			panel.setElevation(elevation);

		}

		catch (Exception e) {

		}

	}

	public MaterialPanel getPanel() {

		return panel;

	}

	public void setPanel(MaterialPanel panel) {

		this.panel = panel;

	}

	public JTextField getTextField() {

		return textField;

	}

	public void setTextField(JTextField textField) {

		this.textField = textField;

	}

	public void setType(int type) {

		switch (type) {

		case 2:

			textField = new NTextField();

			break;

		case 3:

			textField = new TextFieldConBorde();

			break;

		case 4:

			textField = new MaterialTextField();
			break;

		default:

			textField = new NewTextField();

			break;

		}

	}

	public TextFieldWithShadow() {

		setLayout(new GridLayout(1, 0, 0, 0));

		panel = new MaterialPanel();

		add(panel);

		panel.setLayout(new GridLayout(0, 1, 0, 0));

		textField = new NewTextField();

		panel.add(textField);

		textField.setColumns(10);

	}

}
