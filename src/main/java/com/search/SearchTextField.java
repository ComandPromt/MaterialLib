package com.search;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;

import com.contextmenu.DefaultContextMenu;
import com.scrollbar.ScrollBarCustom;

@SuppressWarnings("serial")
public class SearchTextField extends JPanel {

	private BootSearchInput searchField;

	private DefaultContextMenu suggestionPopup;

	private List<String> suggestions;

	private CustomTextAreaScroll customTextAreaScroll;

	public void setSuggestions(List<String> suggestions) {

		this.suggestions = suggestions;

	}

	public BootSearchInput getSearchField() {

		return searchField;

	}

	public void setBackground(Color color) {

		try {

			searchField.setBackground(color);

		}

		catch (Exception e) {

		}

	}

	public SearchTextField(List<String> lista) {

		setLayout(new GridLayout());

		suggestions = lista;

		searchField = new BootSearchInput();

		JTextPane suggestionPane = new JTextPane();

		customTextAreaScroll = new CustomTextAreaScroll(suggestionPane);

		customTextAreaScroll.setBackground(Color.WHITE);

		ScrollBarCustom sb = new ScrollBarCustom(getForeground(), getBackground());

		sb.setUnitIncrement(30);

		sb.setForeground(new Color(48, 144, 216));

		customTextAreaScroll.setVerticalScrollBar(sb);

		suggestionPopup = new DefaultContextMenu();

		suggestionPopup.setLayout(new BorderLayout());

		suggestionPopup.add(customTextAreaScroll, BorderLayout.CENTER);

		searchField.setPreferredSize(new Dimension(250, 30));

		searchField.setFont(new Font("Arial", Font.PLAIN, 14));

		add(searchField, BorderLayout.NORTH);

		searchField.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {

				updateSuggestions();

			}

			public void removeUpdate(DocumentEvent e) {

				updateSuggestions();

			}

			public void changedUpdate(DocumentEvent e) {

			}

		});

		suggestionPane.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {

					int offset = suggestionPane.viewToModel2D(e.getPoint());

					try {

						int rowStart = Utilities.getRowStart(suggestionPane, offset);

						int rowEnd = Utilities.getRowEnd(suggestionPane, offset);

						String selectedValue = suggestionPane.getText(rowStart, rowEnd - rowStart).trim();

						searchField.setText(selectedValue);

						suggestionPopup.setVisible(false);

						searchField.requestFocus();

					}

					catch (BadLocationException ex) {

					}

				}

			}

		});

		searchField.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				showSuggestions();

			}

		});

		suggestionPopup.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				e.consume();

			}

		});

		suggestionPopup.setFocusable(false);

	}

	private void updateSuggestions() {

		String searchText = searchField.getText().trim().toLowerCase();

		if (searchText.isEmpty()) {

			suggestionPopup.setVisible(false);

			return;

		}

		customTextAreaScroll.clearText();

		boolean matchFound = false;

		for (String suggestion : suggestions) {

			if (suggestion.toLowerCase().contains(searchText)) {

				customTextAreaScroll.appendText(suggestion, Color.BLACK);

				matchFound = true;

			}

		}

		suggestionPopup.setVisible(matchFound);

		if (suggestionPopup.isVisible()) {

			showSuggestions();

		}

	}

	private void showSuggestions() {

		int x = 0;

		int y = searchField.getHeight();

		suggestionPopup.setPopupSize(searchField.getWidth(), suggestionPopup.getPreferredSize().height);

		suggestionPopup.show(searchField, x, y);

	}

}
