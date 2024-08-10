package com.textField.passwordField;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.panels.round.MaterialPanel;

@SuppressWarnings("serial")
public class PasswordFieldWithShadow extends JPanel {

	private JPasswordField textField;

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

	public JPasswordField getTextField() {

		return textField;

	}

	public void setTextField(JPasswordField textField) {

		this.textField = textField;

	}

	public void setType(int type) {

		// switch (type) {

//		case 2:
//
//			textField = new PasswordField();
//
//			break;
//
//		case 3:
//
//			textField = new SimplePasswordField();
//
//			break;
//
//		case 4:
//
//			textField = new PasswordIcons();
//			break;
//
//		default:
//
//			textField = new PasswordFieldWithIcon();
//
//			break;
//
//		}

	}

	public PasswordFieldWithShadow() {

		setLayout(new GridLayout(1, 0, 0, 0));

		panel = new MaterialPanel();

		add(panel);

		panel.setLayout(new GridLayout(0, 1, 0, 0));

		textField = new PasswordFieldWithIcon();

		panel.add(textField);

		textField.setColumns(10);

	}

}
