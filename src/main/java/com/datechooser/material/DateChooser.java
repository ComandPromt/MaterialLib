package com.datechooser.material;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import com.buttons.round.NButton;
import com.datechooser.language.CalendarLanguage;
import com.datechooser.listener.DateChooserAction;
import com.datechooser.listener.DateChooserListener;
import com.datechooser.render.DateChooserRender;
import com.datechooser.render.DefaultDateChooserRender;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.jicons.Anterior;
import com.jicons.Posterior;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")

public class DateChooser extends JPanel {

	private final List<DateChooserListener> events;

	private DateSelectable dateSelectable;

	private RDate selectedDate;

	private boolean closePopupAfterSelected;

	private RDate[] selectedDateBetween;

	private int selectedCount;

	private Color themeColor;

	private SimpleDateFormat dateFormat;

	private DateSelectionMode dateSelectionMode;

	private JPopupMenu popup;

	private JTextField textField;

	private JButton labelCurrentDate;

	private String betweenCharacter;

	private DateChooserRender dateChooserRender;

	private JSpinner spMonth;

	private JSpinner spYear;

	private JPanel panelHeader;

	private JPanel panelTitle;

	private JPanel panelDate;

	private String allMonth[];

	private CalendarLanguage language;

	private JPanel panelOption;

	private JButton cmdBack;

	private JButton cmdNext;

	private JPanel panel;

	private Anterior anterior;

	private Posterior posterior;

	public DateChooser(CalendarLanguage language) {

		events = new ArrayList<>();

		closePopupAfterSelected = true;

		selectedDateBetween = new RDate[2];

		selectedCount = 0;

		themeColor = new Color(67, 127, 251);

		dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		dateSelectionMode = DateSelectionMode.SINGLE_DATE_SELECTED;

		betweenCharacter = " to ";

		dateChooserRender = new DefaultDateChooserRender();

		this.allMonth = new String[12];

		this.language = language;

		FlatIntelliJLaf.setup();

		anterior = new Anterior();

		posterior = new Posterior();

		init();

		setLayout(new MigLayout("wrap 0, gap 0, fill, hidemode 1", "[250,grow,fill]", "[grow 0,fill][grow,fill]3[][]"));

		setOpaque(true);

		panelHeader.setOpaque(true);

		panelOption.setOpaque(true);

		panelTitle.setOpaque(true);

		panelDate.setOpaque(true);

		panel.setOpaque(true);

		panel.setLayout(new GridLayout(1, 0, 0, 0));

		labelCurrentDate.setOpaque(true);

		setFondo(Color.WHITE);

	}

	public boolean isClosePopupAfterSelected() {

		return closePopupAfterSelected;

	}

	public void setClosePopupAfterSelected(boolean closePopupAfterSelected) {

		this.closePopupAfterSelected = closePopupAfterSelected;

	}

	public void setDateSelectable(DateSelectable dateSelectable) {

		this.dateSelectable = dateSelectable;

		for (Component com : panelDate.getComponents()) {

			if (com instanceof ButtonDate) {

				ButtonDate cmdDate = (ButtonDate) com;

				cmdDate.setEnabled(isDateSelectable(cmdDate.getDate().toDate()));

			}

		}

	}

	public Color getThemeColor() {

		return themeColor;

	}

	public void setThemeColor(Color themeColor) {

		this.themeColor = themeColor;

	}

	public SimpleDateFormat getDateFormat() {

		return dateFormat;

	}

	public void setDateFormat(SimpleDateFormat dateFormat) {

		this.dateFormat = dateFormat;

	}

	public String getBetweenCharacter() {

		return betweenCharacter;

	}

	public void setBetweenCharacter(String betweenCharacter) {

		this.betweenCharacter = betweenCharacter;

	}

	public DateSelectionMode getDateSelectionMode() {

		return dateSelectionMode;

	}

	public void setDateSelectionMode(DateSelectionMode dateSelectionMode) {

		this.dateSelectionMode = dateSelectionMode;

	}

	public DateChooserRender getDateChooserRender() {

		return dateChooserRender;

	}

	public void setDateChooserRender(DateChooserRender dateChooserRender) {

		this.dateChooserRender = dateChooserRender;

	}

	public void addActionDateChooserListener(DateChooserListener event) {

		events.add(event);

	}

	public void setTextField(JTextField textField) {

		this.textField = textField;

		textField.setEditable(false);

		textField.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseReleased(MouseEvent mouseEvent) {

				if (textField.isEnabled()) {

					if (SwingUtilities.isLeftMouseButton(mouseEvent)) {

						showPopup();

					}

				}

			}

		});

	}

	public void toDay() {

		setSelectedDate(new Date());

	}

	public void setSelectedDate(int day, int month, int year) {

		setSelectedDate(new RDate(day, month, year));

	}

	public void setSelectedDateBetween(int fromDay, int fromMonth, int fromYear, int toDay, int toMonth, int toYear) {

		setSelectedDateBetween(new RDate(fromDay, fromMonth, fromYear), new RDate(toDay, toMonth, toYear), false);

	}

	public void setSelectedDateBetween(int fromDay, int fromMonth, int fromYear, int toDay, int toMonth, int toYear,
			boolean displayLast) {

		setSelectedDateBetween(new RDate(fromDay, fromMonth, fromYear), new RDate(toDay, toMonth, toYear), displayLast);

	}

	public void setSelectedDateBetween(DateBetween dateBetween, boolean displayLast) {

		setSelectedDateBetween(new RDate(dateBetween.getFromDate()), new RDate(dateBetween.getToDate()), displayLast);

	}

	public boolean isDateSelected() {

		boolean selected = false;

		if (dateSelectionMode == DateSelectionMode.SINGLE_DATE_SELECTED) {

			if (selectedDate != null) {

				selected = true;

			}

		}

		else {

			if (selectedCount == 2) {

				selected = true;

			}

		}

		return selected;

	}

	public void clearDate() {

		selectedDate = null;

		selectedCount = 0;

		selectedDateBetween[0] = null;

		selectedDateBetween[1] = null;

		displayDate();

	}

	public void showPopup() {

		if (popup == null) {

			popup = new JPopupMenu();

			popup.add(this);

			popup.addPopupMenuListener(new PopupMenuListener() {

				@Override

				public void popupMenuWillBecomeVisible(PopupMenuEvent popupMenuEvent) {

				}

				@Override

				public void popupMenuWillBecomeInvisible(PopupMenuEvent popupMenuEvent) {

					for (Component component : panelDate.getComponents()) {

						if (component instanceof ButtonDate) {

							((ButtonDate) component).clearHover();

						}

					}

				}

				@Override

				public void popupMenuCanceled(PopupMenuEvent popupMenuEvent) {

				}

			});

		}

		popup.show(textField, 0, textField.getHeight());

	}

	public void setLabelCurrentDayVisible(boolean show) {

		labelCurrentDate.setVisible(show);

	}

	public void hidePopup() {

		if (popup != null) {

			popup.setVisible(false);

		}

	}

	private void closePopup() {

		if ((popup != null && popup.isVisible()) && isClosePopupAfterSelected()) {

			popup.setVisible(false);

		}

	}

	public Date getSelectedDate() throws DateChooserException {

		if (dateSelectionMode == DateSelectionMode.BETWEEN_DATE_SELECTED) {

			if (selectedCount == 2) {

				return getSelectedDateBetween().getFromDate();

			}

			else {

				return selectedDateBetween[0].toDate();

			}

		}

		return selectedDate.toDate();

	}

	public void setSelectedDate(Date date) {

		setSelectedDate(new RDate(date));

	}

	private void setSelectedDate(RDate date) throws DateChooserException {

		if (ErrorCheck.checkDate(date)) {

			selectedDateBetween[0] = date.copy();

			selectedDateBetween[1] = date.copy();

			selectedCount = 2;

			this.selectedDate = date;

			repaint();

			displayDate(date);

			displayDate();

			if (dateSelectionMode == DateSelectionMode.SINGLE_DATE_SELECTED) {

				runEventDateChanged(new DateChooserAction(DateChooserAction.METHOD_SET));

			}

			else {

				runEventDateBetweenChanged(new DateChooserAction(DateChooserAction.METHOD_SET));

			}

			closePopup();

		}

	}

	public DateBetween getSelectedDateBetween() throws DateChooserException {

		if (dateSelectionMode == DateSelectionMode.SINGLE_DATE_SELECTED) {

			throw new DateChooserException("Date chooser is current SINGLE_DATE_SELECTED mode");

		}

		if (selectedCount != 2) {

			throw new DateChooserException("Date between not selected");

		}

		DateBetween dateBetween = new DateBetween(selectedDateBetween[0].toDate(), selectedDateBetween[1].toDate());

		dateBetween.fixDate();

		return dateBetween;

	}

	public void setSelectedDateBetween(DateBetween dateBetween) {

		setSelectedDateBetween(new RDate(dateBetween.getFromDate()), new RDate(dateBetween.getToDate()), false);

	}

	private void displayDate(RDate date) {

		spMonth.setValue(indexToMonth(date.getMonth() - 1));

		spYear.setValue(date.getYear());

	}

	private boolean isDateSelectable(Date date) {

		return dateSelectable == null || dateSelectable.isDateSelectable(date);

	}

	private void setSelectedDateBetween(RDate fromDate, RDate toDate, boolean displayLast) {

		if (dateSelectionMode == DateSelectionMode.SINGLE_DATE_SELECTED) {

			throw new DateChooserException("Date chooser is current SINGLE_DATE_SELECTED mode");

		}

		else {

			if (ErrorCheck.checkDate(fromDate) && ErrorCheck.checkDate(toDate)) {

				selectedDateBetween[0] = fromDate;

				selectedDateBetween[1] = toDate;

				selectedCount = 2;

				if (displayLast) {

					displayDate(toDate);

				}

				else {

					displayDate(fromDate);

				}

				displayDate();

				runEventDateBetweenChanged(new DateChooserAction(DateChooserAction.METHOD_SET));

			}

		}

	}

	private synchronized void runEventDateChanged(DateChooserAction action) {

		for (DateChooserListener event : events) {

			event.dateChanged(getSelectedDate(), action);

		}

	}

	private synchronized void runEventDateBetweenChanged(DateChooserAction action) {

		for (DateChooserListener event : events) {

			event.dateBetweenChanged(getSelectedDateBetween(), action);

		}

	}

	private void init() {

		Color focusColor = UIManager.getColor("Component.focusColor");

		if (focusColor != null) {

			themeColor = focusColor;

		}

		switch (language) {

		case ESPAÑOL:

			allMonth[0] = "Enero";

			allMonth[1] = "Febrero";

			allMonth[2] = "Marzo";

			allMonth[3] = "Abril";

			allMonth[4] = "Mayo";

			allMonth[5] = "Junio";

			allMonth[6] = "Julio";

			allMonth[7] = "Agosto";

			allMonth[8] = "Septiembre";

			allMonth[9] = "Octubre";

			allMonth[10] = "Noviembre";

			allMonth[11] = "Diciembre";

			break;

		default:

			allMonth[0] = "January";

			allMonth[1] = "February";

			allMonth[2] = "March";

			allMonth[3] = "April";

			allMonth[4] = "May";

			allMonth[5] = "June";

			allMonth[6] = "July";

			allMonth[7] = "August";

			allMonth[8] = "September";

			allMonth[9] = "October";

			allMonth[10] = "November";

			allMonth[11] = "December";

			break;

		}

		createHeader();

		createDate();

		createLabelCurrentDate();

		RDate now = new RDate();

		spMonth.setValue(indexToMonth(now.getMonth() - 1));

		initEvent();

		spYear.setValue(now.getYear());

		setOpaque(true);

		cmdBack.setContentAreaFilled(true);

		cmdNext.setContentAreaFilled(true);

		panelHeader.setOpaque(true);

		panelOption.setOpaque(true);

		panelTitle.setOpaque(true);

		panelDate.setOpaque(true);

		labelCurrentDate.setOpaque(true);

		panelHeader.setOpaque(true);

		panelDate.setOpaque(true);

		panelOption.setOpaque(true);

		panelTitle.setOpaque(true);

	}

	private void initEvent() {

		spYear.addChangeListener((ChangeEvent e) -> showDate());

		spMonth.addChangeListener((ChangeEvent e) -> showDate());

	}

	private void createHeader() {

		panelHeader = new JPanel(new MigLayout("wrap 0, inset 0, fill", "[fill]", "[fill]"));

		panelOption = new JPanel(new MigLayout("fill, inset 0", "[grow 0][fill,45%][fill,30%][grow 0]", "[fill]"));

		cmdBack = new JButton(anterior);

		cmdNext = new JButton(posterior);

		spMonth = new JSpinner();

		spYear = new JSpinner();

		JSpinner.NumberEditor numberEditor = (JSpinner.NumberEditor) spYear.getEditor();

		numberEditor.getFormat().applyPattern("#0");

		AbstractDocument doc = new PlainDocument() {

			private static final long serialVersionUID = 1L;

			@Override

			public void setDocumentFilter(DocumentFilter filter) {

				if (filter instanceof DateChooserEditorDocumentFilter) {

					super.setDocumentFilter(filter);

				}

			}

		};

		cmdBack.addActionListener((ActionEvent e) -> {

			int month = monthToIndex(spMonth.getValue().toString());

			if (month == 0) {

				spMonth.setValue(indexToMonth(11));

				int year = Integer.parseInt(spYear.getValue().toString());

				spYear.setValue(year - 1);

			}

			else {

				spMonth.setValue(indexToMonth(month - 1));

			}

		});

		cmdNext.addActionListener((ActionEvent e) -> {

			int month = monthToIndex(spMonth.getValue().toString());

			if (month == 11) {

				spMonth.setValue(indexToMonth(0));

				int year = Integer.parseInt(spYear.getValue().toString());

				spYear.setValue(year + 1);

			}

			else {

				spMonth.setValue(indexToMonth(month + 1));

			}

		});

		doc.setDocumentFilter(new DateChooserEditorDocumentFilter());

		numberEditor.getTextField().setDocument(doc);

		spMonth.setModel(new SpinnerListModel(allMonth));

		panelOption.add(cmdBack, "h 30");

		panelOption.add(spMonth);

		panelOption.add(spYear);

		panelOption.add(cmdNext);

		panelHeader.add(panelOption);

		panelTitle = new JPanel(new MigLayout("fill, inset 0 0 2 0, gap 0", "[center, 100%]"));

		switch (language) {

		case ESPAÑOL:

			panelTitle.add(new JLabel("D"));

			panelTitle.add(new JLabel("L"));

			panelTitle.add(new JLabel("M"));

			panelTitle.add(new JLabel("X"));

			panelTitle.add(new JLabel("J"));

			panelTitle.add(new JLabel("V"));

			panelTitle.add(new JLabel("S"));

			break;

		default:

			panelTitle.add(new JLabel("Sun"));

			panelTitle.add(new JLabel("Mon"));

			panelTitle.add(new JLabel("Tue"));

			panelTitle.add(new JLabel("Wed"));

			panelTitle.add(new JLabel("Thu"));

			panelTitle.add(new JLabel("Fri"));

			panelTitle.add(new JLabel("Sat"));

			break;

		}

		panelHeader.add(panelTitle);

		add(panelHeader, "cell 0 0");

	}

	private void createDate() {

		panelDate = new JPanel(new MigLayout("wrap 7, fill, inset 0, gap 0", "[fill,10%]", "[fill,10%]"));

		add(panelDate, "cell 0 1");

	}

	public void colorearTexto(Color color) {

		try {

			labelCurrentDate.setForeground(color);

		}

		catch (Exception e) {

		}

	}

	public void setFontCurrentDay(Font font) {

		try {

			labelCurrentDate.setFont(font);

		}

		catch (Exception e) {

		}

	}

	private void createLabelCurrentDate() {

		labelCurrentDate = new NButton(dateChooserRender.renderLabelCurrentDate(this, new Date()));

		labelCurrentDate.setForeground(themeColor);

		labelCurrentDate.setContentAreaFilled(false);

		labelCurrentDate.setBorder(null);

		labelCurrentDate.setCursor(new Cursor(Cursor.HAND_CURSOR));

		labelCurrentDate.addActionListener(e -> {

			if (dateSelectionMode == DateSelectionMode.SINGLE_DATE_SELECTED) {

				toDay();

			}

			else {

				if (selectedCount == 1) {

					RDate now = new RDate();

					int month = monthToIndex(spMonth.getValue().toString()) + 1;

					int year = Integer.parseInt(spYear.getValue().toString());

					if (now.getMonth() == month && now.getYear() == year) {

						selectedDateBetween[1] = now;

						selectedCount = 2;

						repaint();

						displayDate();

						runEventDateBetweenChanged(new DateChooserAction(DateChooserAction.USER_SELECT));

						closePopup();

					}

					else {

						displayDate(new RDate());

					}

				}

				else {

					toDay();

				}

			}

		});

		panel = new JPanel();

		panel.setBackground(Color.WHITE);

		add(panel, "cell 0 2");

		add(labelCurrentDate, "cell 0 3");

	}

	private void showDate() {

		panelDate.removeAll();

		int month = monthToIndex(spMonth.getValue().toString());

		int year = Integer.parseInt(spYear.getValue().toString());

		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.YEAR, year);

		calendar.set(Calendar.MONTH, month);

		calendar.set(Calendar.DATE, 1);

		int startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;

		calendar.add(Calendar.DATE, -startDay);

		ButtonDate cmd;

		for (int i = 1; i <= 7 * 6; i++) {

			cmd = new ButtonDate(new RDate(calendar));

			cmd.setText(calendar.get(Calendar.DATE) + "");

			if (calendar.get(Calendar.MONTH) != month) {

				cmd.setForeground(new Color(170, 170, 170));

			}

			panelDate.add(cmd);

			calendar.add(Calendar.DATE, 1);

		}

		panelDate.revalidate();

	}

	private void displayDate() {

		if (textField != null) {

			if (dateSelectionMode == DateSelectionMode.SINGLE_DATE_SELECTED) {

				if (isDateSelected()) {

					textField.setText(dateChooserRender.renderTextFieldDate(this, getSelectedDate()));

				}

				else {

					textField.setText("");

				}

			}

			else {

				DateBetween dateBetween = new DateBetween();

				dateBetween.setFromDate(selectedDateBetween[0].toDate());

				if (selectedCount == 2) {

					dateBetween.setToDate(selectedDateBetween[1].toDate());

					dateBetween.fixDate();

				}

				if (selectedCount != 0) {

					textField.setText(dateChooserRender.renderTextFieldDateBetween(this, dateBetween));

				}

				else {

					textField.setText("");

				}

			}

		}

	}

	private int monthToIndex(String val) {

		for (int i = 0; i < allMonth.length; i++) {

			if (val.equals(allMonth[i])) {

				return i;

			}

		}

		return -1;

	}

	private String indexToMonth(int index) {

		return allMonth[index];

	}

	public static enum DateSelectionMode {

		SINGLE_DATE_SELECTED, BETWEEN_DATE_SELECTED

	}

	private class ButtonDate extends JButton {

		private RDate date;

		private boolean hover = false;

		private boolean selected = false;

		public ButtonDate(RDate date) {

			setEnabled(isDateSelectable(date.toDate()));

			this.date = date;

			setMargin(new Insets(0, 0, 0, 0));

			setContentAreaFilled(false);

			addMouseListener(new MouseAdapter() {

				@Override

				public void mouseEntered(MouseEvent e) {

					if (isEnabled()) {

						hover = true;

						if (dateSelectionMode == DateSelectionMode.BETWEEN_DATE_SELECTED && selectedCount == 1) {

							selectedDateBetween[1] = date;

						}

						panelDate.repaint();

					}

				}

				@Override

				public void mouseExited(MouseEvent e) {

					if (isEnabled()) {

						hover = false;

						panelDate.repaint();

					}

				}

			});

			addActionListener((ActionEvent e) -> {

				if (isEnabled()) {

					if (dateSelectionMode == DateSelectionMode.SINGLE_DATE_SELECTED) {

						selectedDate = date;

						panelDate.repaint();

						displayDate();

						runEventDateChanged(new DateChooserAction(DateChooserAction.USER_SELECT));

						closePopup();

					}

					else {

						if (selectedCount >= 2) {

							selectedCount = 0;

							selectedDateBetween[1] = null;

						}

						if (selectedCount == 0) {

							selectedDateBetween[0] = date;

							selectedCount++;

							displayDate();

						}

						else if (selectedCount == 1) {

							selectedDateBetween[1] = date;

							selectedCount++;

							displayDate();

							runEventDateBetweenChanged(new DateChooserAction(DateChooserAction.USER_SELECT));

							closePopup();

						}

						panelDate.repaint();

					}

				}

			});

		}

		public RDate getDate() {

			return date;

		}

		@SuppressWarnings("unused")

		public void setDate(RDate date) {

			this.date = date;

		}

		public void clearHover() {

			hover = false;

		}

		@Override

		protected void paintComponent(Graphics g) {

			Graphics2D g2 = (Graphics2D) g.create();

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			selected = false;

			if (dateSelectionMode == DateSelectionMode.SINGLE_DATE_SELECTED) {

				if (selectedDate != null && date.equals(selectedDate)) {

					g2.setColor(themeColor);

					g2.fill(createShape(0));

				}

				else {

					checkMouseHover(g2);

				}

			}

			else {

				if (selectedCount > 0) {

					if (selectedDateBetween[1] != null) {

						int compare = selectedDateBetween[0].compareTo(selectedDateBetween[1]);

						if (compare == 0) {

							if (date.equals(selectedDateBetween[0])) {

								g2.setColor(themeColor);

								g2.fill(createShape(selectedCount == 1 ? 1 : 0));

								selected = true;
							}

							else {

								checkMouseHover(g2);

							}

						}

						else {

							RDate fromDate;

							RDate toDate;

							if (compare == 1) {

								fromDate = selectedDateBetween[1];

								toDate = selectedDateBetween[0];

							}

							else {

								fromDate = selectedDateBetween[0];

								toDate = selectedDateBetween[1];

							}

							if (date.equals(fromDate)) {

								g2.setColor(themeColor);

								g2.fill(createShape(1));

								selected = true;

							}

							else if (date.equals(toDate)) {

								g2.setColor(themeColor);

								g2.fill(createShape(2));

								selected = true;

							}

							else {

								if (date.isBetweenOf(fromDate, toDate)) {

									g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));

									g2.setColor(themeColor);

									g2.fill(createShape(3));

								}

								else {

									if (selectedCount == 2) {

										checkMouseHover(g2);

									}

								}

							}

						}

					}

					else {

						if (date.equals(selectedDateBetween[0])) {

							g2.setColor(themeColor);

							g2.fill(createShape(1));

							selected = true;

						}

					}

				}

				else {

					checkMouseHover(g2);

				}

			}

			g2.dispose();

			super.paintComponent(g);

		}

		private void checkMouseHover(Graphics2D g2) {

			if (hover) {

				Composite oldComposite = g2.getComposite();

				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));

				g2.setColor(getForeground());

				int width = getWidth();

				int height = getHeight();

				int size = Math.min(width, height);

				int x = (width - size) / 2;

				int y = (height - size) / 2;

				g2.fill(new Ellipse2D.Double(x, y, size, size));

				g2.setComposite(oldComposite);

			}

		}

		@Override
		public Color getForeground() {

			if (selected) {

				return getContrastColor(themeColor);

			}

			else {

				return super.getForeground();

			}

		}

		public Color getContrastColor(Color color) {

			double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;

			return y >= 128 ? Color.BLACK : Color.WHITE;

		}

		private Shape createShape(int type) {

			double width = getWidth();

			double height = getHeight();

			double size = Math.min(width, height) - 2;

			double x = (width - size) / 2;

			double y = (height - size) / 2;

			Shape shape;

			Area area;

			switch (type) {

			case 1:

				area = new Area(new Ellipse2D.Double(0, y, size, size));

				area.add(new Area(new RoundRectangle2D.Double(size / 2, y, width - size / 2, size, 2, 2)));

				shape = area;

				break;

			case 2:

				area = new Area(new Ellipse2D.Double(width - size, y, size, size));

				area.add(new Area(new RoundRectangle2D.Double(0, y, width - size / 2, size, 2, 2)));

				shape = area;

				break;

			case 3:

				area = new Area(new RoundRectangle2D.Double(0, y, width, size, 2, 2));

				shape = area;

				break;

			default:

				area = new Area(new Ellipse2D.Double(x, y, size, size));

				shape = area;

				break;

			}

			return shape;

		}

	}

	public void setFondo(Color fondo) {

		try {

			setBackground(fondo);

			panelHeader.setBackground(fondo);

			panelDate.setBackground(fondo);

			labelCurrentDate.setBackground(fondo);

			panelOption.setBackground(fondo);

			cmdBack.setBackground(fondo);

			spMonth.setBackground(fondo);

			spYear.setBackground(fondo);

			cmdNext.setBackground(fondo);

			panelTitle.setBackground(fondo);

			anterior.setBackground(fondo);

			posterior.setBackground(fondo);

		}

		catch (Exception e) {

		}

	}

}
