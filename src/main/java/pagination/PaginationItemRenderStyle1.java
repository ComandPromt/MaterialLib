package pagination;

import java.awt.Color;

import javax.swing.JButton;

class PaginationItemRenderStyle1 extends DefaultPaginationItemRender {

	@Override
	public JButton createButton(Object value, boolean isPrevious, boolean isNext, boolean enable, Color foreground,
			Color hover, Color background) {

		JButton button = super.createButton(value, isPrevious, isNext, enable, foreground, hover, background);

		button.setForeground(foreground);

		button.setUI(new ButtonUI(hover, background));

		return button;

	}

	@Override
	public Object createPreviousIcon() {

		return "Previous";

	}

	@Override
	public Object createNextIcon() {

		return "Next";

	}

}
