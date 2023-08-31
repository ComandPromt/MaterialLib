package scrollbar;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class MaterialPanelDeslizante extends JScrollPane {

	public MaterialPanelDeslizante(JPanel panel, Color select, Color background) {

		super(panel);

		setVerticalScrollBar(new ScrollBarCustom(select, background));

		ScrollBarCustom test = new ScrollBarCustom(select, background);

		test.setOrientation(JScrollBar.HORIZONTAL);

		setHorizontalScrollBar(test);

	}

}
