package javaswingdev;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ClassicMenuPanel extends JPanel {

	private GradientDropdownMenu menu;

	private JPanel panel;

	public void addEvent(MenuEvent event) {

		menu.addEvent(event);

	}

	public void applay(JFrame frame) {

		menu.applay(frame);

	}

	public void addItem(String... menus) {

		menu.addItem(menus);

	}

	@Override
	public void setFont(Font font) {

		try {

			menu.setFont(font);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setForeground(Color fg) {

		try {

			menu.setForeground(fg);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void setBackground(Color bg) {

		try {

			menu.setBackground(bg);

		}

		catch (Exception e) {

		}

	}

	public void setHeaderGradient(boolean gradient) {

		menu.setHeaderGradient(gradient);

	}

	public void setGradientColor(Color color1, Color color2) {

		menu.setGradientColor(color1, color2);

	}

	public void showForm(Component com) {

		try {

			removeAll();

			panel = (JPanel) com;

			panel.setSize(getWidth(), getHeight());

			add(panel);

			repaint();

			revalidate();

		}

		catch (Exception e) {

		}

	}

	public ClassicMenuPanel() {

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				int altura = 1000;

				menu.setSize(getWidth(), altura);

				panel.setBounds(0, altura, getWidth(), getHeight() - altura);

				showForm(new JPanel());

			}

		});

		setLayout(null);

		menu = new GradientDropdownMenu();

		menu.setBounds(0, 0, 450, 43);

		menu.addEvent(new MenuEvent() {

			@Override

			public void selected(int index, int subIndex, boolean menuItem) {

				if (menuItem) {

					showForm(menu);
				}

			}

		});

		menu.setHeaderGradient(false);

		add(menu);

		panel = new JPanel();

		add(panel);

	}

}
