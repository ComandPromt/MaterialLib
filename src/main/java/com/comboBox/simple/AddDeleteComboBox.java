package com.comboBox.simple;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.buttons.animated.EffectButton;
import com.comboBox.comboSuggestion.ComboBoxSuggestion;
import com.dialog.confirm.MessageDialog;
import com.dialog.confirm.MessageDialog.MessageType;
import com.textField.text.NTextField;

import mthos.JMthos;

@SuppressWarnings("serial")
public class AddDeleteComboBox extends JPanel {

	private JPanel delete;

	private ComboBoxSuggestion<String> comboBox;

	private JPanel add;

	private JComponent c;

	private int ancho;

	@Override
	public void setForeground(Color fg) {

		try {

			add.setForeground(fg);

			delete.setForeground(fg);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setFont(Font font) {

		try {

			add.getComponent(0).setFont(font);

			delete.getComponent(0).setFont(font);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setBackground(Color bg) {

		super.setBackground(bg);

		try {

			add.setBackground(bg);

			delete.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public AddDeleteComboBox(String title, String text, ImageIcon icon, int width, int height) {

		if (width == 0) {
			width = 400;
		}
		ancho = width;
		c = this;

		comboBox = new ComboBoxSuggestion<String>();

		add = new JPanel();

		add.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				JTextField textField = new NTextField();

				EffectButton buttonOk = new EffectButton();

				buttonOk.setShadowColor(Color.WHITE);

				buttonOk.setBackground(Color.WHITE);

				EffectButton buttonCancel = new EffectButton();

				buttonCancel.setBackground(Color.WHITE);

				buttonCancel.setShadowColor(Color.WHITE);

				buttonOk = (EffectButton) JMthos.showCustomInputDialog(c, textField, buttonOk, buttonCancel, ancho,
						height, title, text, "OK", "CANCEL", icon, () -> {

							comboBox.addItem(textField.getText());

						});

			}

		});

		add.setLayout(new GridLayout());

		JLabel label = new JLabel("+");

		label.setHorizontalAlignment(SwingConstants.CENTER);

		add.add(label);

		delete = new JPanel();

		delete.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				if (comboBox.getItemCount() > 0) {

					MessageDialog dialogo = new MessageDialog(0, 0, Color.RED, Color.WHITE, "",
							"¿Desea borrar la URL seleccionada?");

					if (dialogo.getMessageType().equals(MessageType.OK)) {

						comboBox.removeItemAt(comboBox.getSelectedIndex());

					}

				}

			}

		});

		delete.setLayout(new GridLayout());

		label = new JLabel("-");

		label.setHorizontalAlignment(SwingConstants.CENTER);

		delete.add(label);

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				try {

					comboBox.setBounds(0, 0, Math.round(getWidth() * 0.8f), getHeight());

					add.setBounds(Math.round(getWidth() * 0.8f), 0, Math.round(getWidth() * 0.2f),
							Math.round(getHeight() * 0.5f));

					delete.setBounds(Math.round(getWidth() * 0.8f), Math.round(getHeight() * 0.5f),
							Math.round(getWidth() * 0.2f), Math.round(getHeight() * 0.5f));

				}

				catch (Exception e1) {

				}

			}

		});

		setLayout(null);

		add(comboBox);

		add(add);

		add(delete);

	}

}
