package drag_and_drop;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class DragAndDrop extends JLabel {

	public TitledBorder dragBorder;

	public DragAndDrop(String title, String text) {

		setFont(new Font("Tahoma", Font.PLAIN, 16));

		setText(text);

		setForeground(SystemColor.desktop);

		setBackground(SystemColor.menu);

		this.dragBorder = new javax.swing.border.TitledBorder(title);

	}

}
