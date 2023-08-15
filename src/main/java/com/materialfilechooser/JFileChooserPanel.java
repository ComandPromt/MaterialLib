package com.materialfilechooser;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JFileChooserPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField textField;

	private JFrame mainFrame;

	public JFileChooserPanel(final JFrame mainFrame) {

		this.mainFrame = mainFrame;

		setLayout(new GridLayout(0, 2, 0, 0));

		textField = new JTextField();

		add(textField);

		textField.setColumns(10);

		JButton btnNewButton = new JButton("New button");

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				ThreadDialog threadDialog = new ThreadDialog(mainFrame);

				threadDialog.setVisible(true);

			}

		});

		add(btnNewButton);

	}

}

class ThreadDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private boolean running;

	MaterialFileChooser panel;

	public ThreadDialog(Frame parent) {

		super(parent, "Thread Dialog", false);

		setSize(500, 500);

		setLocationRelativeTo(parent);

		panel = new MaterialFileChooser();

		panel.setSize(500, 500);

		add(panel);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

}
