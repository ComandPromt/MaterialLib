package com.comboBox.comboSuggestion;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JComboBox;

@SuppressWarnings("serial")

public class ComboBoxSuggestion<E> extends JComboBox<E> {

	public ComboBoxSuggestion() {

		setUI(new ComboSuggestionUI());

		this.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

				int valorIndice = getSelectedIndex();

				if (e.getWheelRotation() < 0) {

					valorIndice--;

				}

				else {

					valorIndice++;

				}

				if (valorIndice < 0) {

					valorIndice = 0;

				}

				if (valorIndice >= getItemCount()) {

					valorIndice = getItemCount();

					valorIndice--;

				}

				setSelectedIndex(valorIndice);

			}

		});

	}

}
