package panelTask;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class PanelTags extends javax.swing.JPanel {

	private EventTags event;

	private Color backgroundTextColor;

	private Item input;

	private Color borderColor;

	private Color overBackGround;

	private Color background;

	public void addEventTags(EventTags event) {

		this.event = event;

		((Item) getComponent(getComponentCount() - 1)).setEventTags(event);

	}

	@Override
	public void setForeground(Color fg) {

		try {

			this.input.getTxt().setForeground(fg);

			repaint();

		}

		catch (Exception e) {

		}

	}

	public void setOverBackgroundColor(Color color) {

		try {

			this.input.setOverBackgroundColor(color);

		}

		catch (Exception e) {

		}

	}

	public void setThickness(int thickness) {

		try {

			this.input.setThickness(thickness);

		}

		catch (Exception e) {

		}

	}

	public void setTagBackground(Color color) {

		try {

			this.input.getTxt().setBackground(color);

		}

		catch (Exception e) {

		}

	}

	public PanelTags(Color backgroundText, Color border, Color overBackGround, Color background) {

		if (border == null) {

			border = new Color(8, 62, 243);

		}

		if (overBackGround == null) {

			overBackGround = new Color(227, 227, 227);

		}

		if (background == null) {

			background = new Color(227, 227, 227);

		}

		borderColor = border;

		this.backgroundTextColor = backgroundText;

		this.overBackGround = overBackGround;

		this.background = background;

		initComponents();

		init();

	}

	private void init() {

		setBackground(Color.WHITE);

		setLayout(new WrapLayout(WrapLayout.LEFT));

		Item input = new Item("", borderColor, overBackGround, background);

		input.getTxt().setBackground(this.backgroundTextColor);

		input.addEventKey(new KeyAdapter() {

			@Override

			public void keyReleased(KeyEvent ke) {

				event.onKeyType(input, input.getText(), ke);

			}

		});

		input.addEventForInput(new EventInput() {

			@Override

			public void addItem(String text) {

				Item item = new Item(text, borderColor, overBackGround, background);

				item.addEventMouse();

				item.setEventTags(event);

				item.addEventRemove(new ActionListener() {

					@Override

					public void actionPerformed(ActionEvent ae) {

						if (event.isRemoveAble(item, item.getText())) {

							remove(item);

							refresh();

							event.onItemRemove(item, item.getText());

						}

					}

				});

				item.addEventKey(new KeyAdapter() {

					@Override

					public void keyReleased(KeyEvent ke) {

						event.onKeyType(item, item.getText(), ke);
					}

				});

				add(item, getComponentCount() - 1);

				event.onAddItem(item, item.getText());

				refresh();

			}

		});

		add(input);

		this.input = input;

	}

	private void refresh() {

		repaint();

		revalidate();

	}

	public List<String> getAllItems() {

		List<String> list = new ArrayList<>();

		for (Component com : getComponents()) {

			Item item = (Item) com;

			if (!item.isInput()) {

				list.add(item.getText());

			}

		}

		return list;

	}

	private void initComponents() {

		addMouseListener(new java.awt.event.MouseAdapter() {

			@Override

			public void mousePressed(java.awt.event.MouseEvent evt) {

				formMousePressed(evt);

			}

		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);

		this.setLayout(layout);

		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));

		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));

	}

	private void formMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMousePressed

		((Item) getComponent(getComponentCount() - 1)).grabFocus();
	}

}
