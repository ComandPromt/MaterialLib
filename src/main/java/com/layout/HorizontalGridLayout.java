package com.layout;

import java.awt.Container;
import java.awt.Insets;

@SuppressWarnings("serial")
public class HorizontalGridLayout extends VerticalGridLayout {

	public HorizontalGridLayout() {
		super();
	}

	public HorizontalGridLayout(int rows, int cols) {
		super(rows, cols);
	}

	public HorizontalGridLayout(int rows, int cols, int hgap, int vgap) {
		super(rows, cols, hgap, vgap);
	}

	@Override
	public void layoutContainer(Container parent) {
		synchronized (parent.getTreeLock()) {
			Insets insets = parent.getInsets();

			int ncomponents = parent.getComponentCount();
			int nrows = getRows();
			int ncols = getColumns();
			boolean ltr = parent.getComponentOrientation().isLeftToRight();

			if (ncomponents == 0) {
				return;
			}

			if (ncols > 0) {
				nrows = (ncomponents + ncols - 1) / ncols;
			} else {
				ncols = (ncomponents + nrows - 1) / nrows;
			}

			int totalGapsHeight = (nrows - 1) * getVgap();
			int heightWOInsets = parent.getHeight() - (insets.top + insets.bottom);
			int heightOnComponent = (heightWOInsets - totalGapsHeight) / nrows;
			int extraHeightAvailable = (heightWOInsets - (heightOnComponent * nrows + totalGapsHeight)) / 2;

			int totalGapsWidth = (ncols - 1) * getHgap();
			int widthWOInsets = parent.getWidth() - (insets.left + insets.right);
			int widthOnComponent = (widthWOInsets - totalGapsWidth) / ncols;
			int extraWidthAvailable = (widthWOInsets - (widthOnComponent * ncols + totalGapsWidth)) / 2;

			for (int r = 0, y = insets.top + extraHeightAvailable; r < nrows; r++, y += heightOnComponent + getVgap()) {
				for (int c = 0,
						x = insets.left + extraWidthAvailable; c < ncols; c++, x += widthOnComponent + getHgap()) {
					int i;
					if (ltr) {
						i = r * ncols + c;
					} else {
						i = r * ncols + (ncols - 1 - c);
					}
					if (i < ncomponents) {
						parent.getComponent(i).setBounds(x, y, widthOnComponent, heightOnComponent);
					}
				}
			}
		}
	}
}
