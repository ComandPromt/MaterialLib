package pagination;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JButton;

class DefaultPaginationItemRender implements PaginationItemRender {

	@Override

	public JButton createPaginationItem(Object value, boolean isPrevious, boolean isNext, boolean enable,

			Color foreground, Color hover, Color background, Font font) {

		JButton cmd = createButton(value, isPrevious, isNext, enable, foreground, hover, background);

		if (font != null) {

			cmd.setFont(font);

		}

		if (isPrevious) {

			Object icon = createPreviousIcon();

			if (icon != null && (icon instanceof Icon)) {

				cmd.setIcon((Icon) icon);
			}

			else {
				cmd.setText(icon.toString());
			}

		}

		else if (isNext) {

			Object icon = createNextIcon();

			if (icon != null) {

				if (icon instanceof Icon) {

					cmd.setIcon((Icon) icon);

				}

				else {

					cmd.setText(icon.toString());

				}

			}

		}

		else {

			cmd.setText(value.toString());

		}

		if (!enable) {

			cmd.setFocusable(false);

		}

		return cmd;

	}

	@Override
	public JButton createButton(Object value, boolean isPrevious, boolean isNext, boolean enable, Color foreground,
			Color hover, Color background) {

		return new JButton();

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
