package pagination;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PaginationPanel extends JPanel {

	Pagination pagination1;

	@Override
	public void setFont(Font font) {

		try {

			pagination1.setFont(font);

			repaint();

		}

		catch (Exception e) {

		}

	}

	public PaginationPanel(Font font, int start, int lenght, Color foreground, Color paginationColor, Color itemHover,
			Color itemBackground) {

		try {

			pagination1 = new Pagination(font, foreground, paginationColor, itemHover, itemBackground);

			pagination1.setOpaque(false);

			pagination1.setPagination(start, lenght);

			pagination1.setPaginationItemRender(new PaginationItemRenderStyle1());

			add(pagination1);

		}

		catch (Exception e) {

		}

	}

}
