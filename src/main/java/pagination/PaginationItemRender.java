package pagination;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public interface PaginationItemRender {

	public Object createPreviousIcon();

	public Object createNextIcon();

	public JButton createButton(Object value, boolean isPrevious, boolean isNext, boolean enable, Color foreground,
			Color hover, Color background);

	JButton createPaginationItem(Object value, boolean isPrevious, boolean isNext, boolean enable, Color foreground,
			Color hover, Color background, Font font);

}
