package roundedButtonsWithImage;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonRoundedWithImage extends JButton {

	BufferedImage output;

	Graphics2D g2;

	public Icon imageIcon;

	public Icon getImageIcon() {

		return imageIcon;

	}

	private String limpiarCadena(String cadena) {

		cadena = cadena.replace("file:/", "");

		return cadena;

	}

	public ButtonRoundedWithImage(String text) {

		output = new BufferedImage(2000, 2000, BufferedImage.TYPE_INT_ARGB);

		setContentAreaFilled(false);

		setText(text);

		this.setBorder(new RoundedBorder(15));

	}

	public void setIcon(String icon, int width, int height, int stroke) {

		icon = limpiarCadena(icon);

		g2 = output.createGraphics();

		imageIcon = new RoundedPanel(width, height, stroke, icon).pintar(g2);

		super.setIcon(imageIcon);

	}

}
