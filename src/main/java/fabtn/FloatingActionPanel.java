package fabtn;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Icon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FloatingActionPanel extends JPanel {

	private FloatingActionButton panel1;

	private JPanel panel2;

	private boolean left;

	public FloatingActionButton getMenu() {

		return panel1;

	}

	public void addItem(Icon icon, Color color) {

		panel1.addItem(icon, color);

	}

	public void changePanel(Component panel) {

		try {

			remove(1);

			panel2 = (JPanel) panel;

			panel2.setSize(getWidth(), getHeight());

			add(panel2);

			repaint();

			revalidate();

		}

		catch (Exception e) {

		}

	}

	public void setLeftPosition(boolean left) {

		this.left = left;

		if (left) {

			panel1.setSize(64, getHeight());

			panel2.setSize(getWidth(), getHeight());

		}

		else {

			panel1.setBounds(getWidth() - 64, 0, 64, getHeight());

			panel2.setSize(getWidth(), getHeight());

		}

	}

	public FloatingActionPanel() {

		left = true;

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				setLeftPosition(left);

			}

		});

		setLayout(null);

		panel1 = new FloatingActionButton();

		panel1.setBounds(0, 0, 32, 300);

		add(panel1);

		panel2 = new JPanel();

		panel2.setBounds(0, 0, 450, 300);

		add(panel2);

	}

}
