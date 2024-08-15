package com.material.table;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.checkbox.CheckBoxCustom;

@SuppressWarnings("serial")
class Pagina extends JPanel {

	private JPanel panel;

	private JPanel check;

	private CheckBoxCustom checkBox;

	private boolean filePath;

	public void setFilePath(boolean filePath) {

		this.filePath = filePath;

		repaint();

	}

	public CheckBoxCustom getCheckBox() {

		return checkBox;

	}

	@Override
	public void setBackground(Color bg) {

		try {

			check.setBackground(bg);

			panel.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public Pagina(List<String> lista, Font font, Color fg) {

		panel = new JPanel();

		check = new JPanel();

		setLayout(null);

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				check.setBounds(0, 0, Math.round(getWidth() * 0.025f), getHeight());

				panel.setLayout(new GridLayout(1, lista.size()));

				panel.setBounds(Math.round(getWidth() * 0.025f), 0, getWidth() - Math.round(getHeight() * 0.1f),
						getHeight());

				check.revalidate();

				panel.revalidate();

			}

		});

		checkBox = new CheckBoxCustom();

		check.setLayout(new GridLayout());

		check.add(checkBox);

		check.setBackground(Color.WHITE);

		panel.setBackground(Color.WHITE);

		if (!lista.isEmpty() && !lista.get(0).isEmpty()) {

			add(check);

		}

		JLabel label;

		for (int i = 0; i < lista.size(); i++) {

			if (filePath) {

			}

			else {

				label = new JLabel(lista.get(i));

				label.setHorizontalAlignment(SwingConstants.CENTER);

				label.setFont(font);

				label.setForeground(fg);

				panel.add(label);

			}

		}

		add(panel);

	}

	public void setCheckBackground(Color color) {

		try {

			checkBox.setBackground(color);

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

	public void setColorActivo(Color color) {

		try {

			checkBox.setColorActivo(color);

		}

		catch (Exception e) {

		}

	}

}
