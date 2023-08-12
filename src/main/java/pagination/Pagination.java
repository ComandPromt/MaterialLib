package pagination;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class Pagination extends JPanel {

	private PaginationItemRender paginationItemRender;

	private List<EventPagination> events = new ArrayList<>();

	private Page page;

	private Color foreground;

	private Color paginationColor;

	private Color hover;

	private Color backgroundItem;

	private Font font;

	public PaginationItemRender getPaginationItemRender() {

		return paginationItemRender;

	}

	public void setPaginationItemRender(PaginationItemRender paginationItemRender) {

		this.paginationItemRender = paginationItemRender;

		changePage(page.getCurrent(), page.getTotalPage());

	}

	public Pagination(Font font, Color foreground, Color paginationColor, Color hover, Color backgroundItem) {

		this.font = font;

		if (hover == null) {

			hover = Color.WHITE;

		}

		if (backgroundItem == null) {

			backgroundItem = Color.WHITE;

		}

		this.hover = hover;

		this.backgroundItem = backgroundItem;

		this.foreground = foreground;

		this.paginationColor = paginationColor;

		init();

	}

	private void init() {

		paginationItemRender = new DefaultPaginationItemRender();

		setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

		setPagination(1, 1);

	}

	private void runEvent() {

		for (EventPagination event : events) {

			event.pageChanged(page.getCurrent());

		}

	}

	private boolean isEnable(Object item) {

		return (item instanceof Page.BreakLabel || Integer.valueOf(item.toString()) != page.getCurrent());

	}

	public void addEventPagination(EventPagination event) {

		events.add(event);

	}

	public void setPagination(int current, int totalPage) {

		if (current > totalPage) {

			current = totalPage;

		}

		if (page == null || (page.getCurrent() != current || page.getTotalPage() != totalPage)) {

			changePage(current, totalPage);

		}

	}

	private void changePage(int current, int totalPage) {

		page = paginate(current, totalPage);

		removeAll();

		refresh();

		JButton cmdPrev = paginationItemRender.createPaginationItem("Previous", true, false, page.isPrevious(),
				foreground, null, null, font);

		cmdPrev.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				if (page.getCurrent() > 1) {

					setPagination(page.getCurrent() - 1, totalPage);

					runEvent();

				}

			}

		});

		add(cmdPrev);

		for (Object item : page.getItems()) {

			JButton cmd = paginationItemRender.createPaginationItem(item, false, false, isEnable(item), paginationColor,
					hover, backgroundItem, font);

			if (item instanceof Integer && (Integer.valueOf(item.toString()) == page.getCurrent())) {

				cmd.setSelected(true);

			}

			cmd.addActionListener(new ActionListener() {

				@Override

				public void actionPerformed(ActionEvent e) {

					if (!cmd.isSelected() && item != null && (item instanceof Page.BreakLabel)) {

						Page.BreakLabel pb = (Page.BreakLabel) item;

						setPagination(pb.getPage(), totalPage);

					}

					else {

						setPagination(Integer.valueOf(item.toString()), totalPage);

					}

					runEvent();

				}

			});

			add(cmd);

		}

		JButton cmdNext = paginationItemRender.createPaginationItem("Next", false, true, page.isNext(), foreground,
				null, null, font);
		cmdNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (page.getCurrent() < page.getTotalPage()) {

					setPagination(page.getCurrent() + 1, totalPage);

					runEvent();

				}

			}

		});

		add(cmdNext);

	}

	private void refresh() {

		repaint();

		revalidate();

	}

	private Page paginate(int current, int max) {

		boolean prev = current > 1;

		boolean next = current < max;

		List<Object> items = new ArrayList<>();

		items.add(1);

		if (current == 1 && max == 2) {

			return new Page(current, prev, next, items, max);

		}

		int r = 2;

		int r1 = current - r;

		int r2 = current + r;

		if (current > 4) {

			items.add(new Page.BreakLabel((r1 > 2 ? r1 : 2) - 1));

		}

		for (int i = r1 > 2 ? r1 : 2; i <= Math.min(max, r2); i++) {

			items.add(i);

		}

		if (r2 + 1 < max) {

			items.add(new Page.BreakLabel(Integer.valueOf(items.get(items.size() - 1).toString()) + 1));

		}

		if (r2 < max) {

			items.add(max);

		}

		return new Page(current, prev, next, items, max);

	}

}
