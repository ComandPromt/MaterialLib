package com.dialog.popup;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class MaterialJOptionPane {

	private static JLabel textoLabel;

	/**
	 * Muestra un cuadro de diálogo personalizado para la entrada de texto y permite
	 * al usuario confirmar o cancelar la acción. El botón de confirmación se
	 * configura para ejecutar una acción específica definida por el parámetro
	 * `callback`.
	 *
	 * @param component    El componente relativo al cual se posicionará el cuadro
	 *                     de diálogo.
	 * @param textField    El campo de texto donde el usuario puede ingresar
	 *                     información.
	 * @param buttonOk     El botón que confirma la entrada y cierra el diálogo.
	 * @param buttonCancel El botón que cancela la acción y cierra el diálogo.
	 * @param width        El ancho del cuadro de diálogo en píxeles. Si se pasa 0,
	 *                     se usará un ancho predeterminado de 400 píxeles.
	 * @param height       La altura del cuadro de diálogo en píxeles. Si se pasa 0,
	 *                     se usará una altura predeterminada de 200 píxeles.
	 * @param title        El título del cuadro de diálogo.
	 * @param label        El texto que describe el propósito del campo de entrada.
	 * @param ok           El texto que se muestra en el botón de confirmación.
	 * @param cancel       El texto que se muestra en el botón de cancelación.
	 * @param icon         El icono que se mostrará en el cuadro de diálogo. Si es
	 *                     null, no se mostrará ningún icono.
	 * @param callback     La acción que se debe realizar cuando se presione el
	 *                     botón de confirmación. Esta es una instancia de
	 *                     `Runnable` que define el comportamiento deseado.
	 * @return El botón de confirmación (`buttonOk`).
	 */

	public static JButton showCustomInputDialog(JComponent component, JTextField textField, JButton buttonOk,
			JButton buttonCancel, int width, int height, String title, String label, String ok, String cancel,
			ImageIcon icon, Runnable callback) {

		if (width == 0) {

			width = 400;

		}

		if (height == 0) {

			height = 200;

		}

		JPanel panel = new JPanel(new GridLayout(0, 1));

		panel.setBackground(Color.WHITE);

		textoLabel = new JLabel(label);

		textoLabel.setFont(new Font("Arial", Font.PLAIN, 20));

		panel.add(textoLabel);

		panel.add(textField);

		buttonOk.setText(ok);

		buttonCancel.setText(cancel);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		buttonPanel.setBackground(Color.WHITE);

		buttonPanel.add(buttonOk);

		buttonPanel.add(buttonCancel);

		panel.add(buttonPanel);

		JDialog dialog = new JDialog();

		dialog.setBackground(Color.WHITE);

		if (icon != null) {

			dialog.setIconImage(icon.getImage());

		}

		dialog.setTitle(title);

		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		dialog.getContentPane().add(panel);

		dialog.setSize(width, height);

		dialog.setLocationRelativeTo(component);

		buttonOk.addActionListener(e -> {

			if (callback != null) {

				callback.run();

			}

			dialog.dispose();

		});

		buttonCancel.addActionListener(e -> {

			dialog.dispose();

		});

		dialog.setVisible(true);

		return buttonOk;

	}

	public static JLabel getTextoLabel() {

		return textoLabel;

	}

}
