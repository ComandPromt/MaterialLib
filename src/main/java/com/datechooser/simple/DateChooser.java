package com.datechooser.simple;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

import com.datechooser.language.CalendarLanguage;

@SuppressWarnings("serial")

public final class DateChooser extends javax.swing.JPanel {

	private JTextField textRefernce;

	private final String MONTH_ENGLISH[] = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };

	private final String MONTH_SPANISH[] = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
			"Septiembre", "Octubre", "Noviembre", "Diciembre" };

	protected static String dateFormat = "dd/MM/yyyy";

	protected static char separator = '/';

	private int MONTH = 1;

	private int YEAR = 2021;

	private int DAY = 1;

	private int STATUS = 1;

	private int startYear;

	private SelectedDate selectedDate = new SelectedDate();

	private List<EventDateChooser> events;

	private CalendarLanguage idioma;

	private javax.swing.JLayeredPane MY;

	private com.datechooser.simple.Button cmdForward;

	private com.datechooser.simple.Button cmdMonth;

	private com.datechooser.simple.Button cmdPrevious;

	private com.datechooser.simple.Button cmdYear;

	private javax.swing.JPanel header;

	private javax.swing.JLabel lb;

	private javax.swing.JPopupMenu popup;

	private com.datechooser.simple.Slider slide;

	private JButton btnNewButton;

	private JButton btnNewButton_1;

	public static char getSeparator() {

		return separator;

	}

	public static void setSeparator(char separator) {

		DateChooser.separator = separator;

	}

	public static void setDateFormat(String dateFormat) {

		DateChooser.dateFormat = dateFormat;

	}

	public JTextField getTextRefernce() {

		return textRefernce;

	}

	public void addEventDateChooser(EventDateChooser event) {

		events.add(event);

	}

	public DateChooser(CalendarLanguage language) {

		this.idioma = language;

		initComponents();

		execute();

	}

	private void execute() {

		setForeground(new Color(204, 93, 93));

		events = new ArrayList<>();

		popup.add(this);

		toDay(false);

	}

	public void setTextReference(JTextField txt) {

		this.textRefernce = txt;

		this.textRefernce.setEditable(false);

		this.textRefernce.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent me) {

				if (textRefernce.isEnabled()) {

					showPopup();

				}

			}

		});

		setText(false, 0);

	}

	private void setText(boolean runEvent, int act) {

		if (textRefernce != null) {

			try {

				SimpleDateFormat df = new SimpleDateFormat(DateChooser.dateFormat);

				Date date = df
						.parse(DAY + Character.toString(separator) + MONTH + Character.toString(separator) + YEAR);

				textRefernce.setText(new SimpleDateFormat(DateChooser.dateFormat).format(date));
			}

			catch (ParseException e) {

			}

		}

		if (runEvent) {

			runEvent(act);

		}

	}

	private void runEvent(int act) {

		SelectedAction action = new SelectedAction() {

			@Override

			public int getAction() {

				return act;

			}

		};

		for (EventDateChooser event : events) {

			event.dateSelected(action, selectedDate);

		}

	}

	private Event getEventDay(Dates dates) {

		return (MouseEvent evt, int num) -> {

			dates.clearSelected();

			dates.setSelected(num);

			DAY = num;

			selectedDate.setDay(DAY);

			selectedDate.setMonth(MONTH);

			selectedDate.setYear(YEAR);

			setText(true, 1);

			if (evt != null && evt.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(evt)) {

				popup.setVisible(false);

			}

		};

	}

	private Event getEventMonth() {

		return (MouseEvent evt, int num) -> {

			MONTH = num;

			selectedDate.setDay(DAY);

			selectedDate.setMonth(MONTH);

			selectedDate.setYear(YEAR);

			setText(true, 2);

			Dates d = new Dates(this.idioma);

			d.setForeground(getForeground());

			d.setEvent(getEventDay(d));

			d.showDate(MONTH, YEAR, selectedDate);

			if (slide.slideToDown(d)) {

				ponerMes();

				cmdYear.setText(YEAR + "");

				STATUS = 1;

			}

		};
	}

	public void ponerMes() {

		switch (getIdioma()) {

		default:

		case ENGLISH:

			cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);

			break;

		case ESPAÑOL:

			cmdMonth.setText(MONTH_SPANISH[MONTH - 1]);

			break;

		}

	}

	private Event getEventYear() {

		return (MouseEvent evt, int num) -> {

			YEAR = num;

			selectedDate.setDay(DAY);

			selectedDate.setMonth(MONTH);

			selectedDate.setYear(YEAR);

			setText(true, 3);

			Months d = new Months(idioma);

			d.setEvent(getEventMonth());

			if (slide.slideToDown(d)) {

				ponerMes();

				cmdYear.setText(YEAR + "");

				STATUS = 2;

			}

		};

	}

	private void toDay(boolean runEvent) {

		Dates dates = new Dates(this.idioma);

		dates.setForeground(getForeground());

		dates.setEvent(getEventDay(dates));

		SimpleDateFormat df = new SimpleDateFormat(dateFormat);

		Date date = new Date();

		String toDay = df.format(date);

		DAY = Integer.valueOf(toDay.split(Character.toString(separator))[0]);

		MONTH = Integer.valueOf(toDay.split(Character.toString(separator))[1]);

		YEAR = Integer.valueOf(toDay.split(Character.toString(separator))[2]);

		selectedDate.setDay(DAY);

		selectedDate.setMonth(MONTH);

		selectedDate.setYear(YEAR);

		dates.showDate(MONTH, YEAR, selectedDate);

		slide.slideNon(dates);

		ponerMes();

		cmdYear.setText(YEAR + "");

		setText(runEvent, 0);

	}

	public void toDay() {

		toDay(true);

	}

	private void setDateNext() {

		Dates dates = new Dates(this.idioma);

		dates.setForeground(getForeground());

		dates.setEvent(getEventDay(dates));

		dates.showDate(MONTH, YEAR, selectedDate);

		if (slide.slideToLeft(dates)) {

			ponerMes();

			cmdYear.setText(YEAR + "");

		}

	}

	private void setDateBack() {

		Dates dates = new Dates(this.idioma);

		dates.setForeground(getForeground());

		dates.setEvent(getEventDay(dates));

		dates.showDate(MONTH, YEAR, selectedDate);

		if (slide.slideToRight(dates)) {

			ponerMes();

			cmdYear.setText(YEAR + "");

		}

	}

	private void setYearNext() {

		Years years = new Years();

		years.setEvent(getEventYear());

		startYear = years.next(startYear);

		slide.slideToLeft(years);

	}

	private void setYearBack() {

		if (startYear >= 1000) {

			Years years = new Years();

			years.setEvent(getEventYear());

			startYear = years.back(startYear);

			slide.slideToLeft(years);

		}
	}

	public void showPopup(Component com, int x, int y) {

		popup.show(com, x, y);

	}

	public void showPopup() {

		popup.show(textRefernce, 0, textRefernce.getHeight());

	}

	public void hidePopup() {

		popup.setVisible(false);

	}

	private void initComponents() {

		popup = new javax.swing.JPopupMenu() {

			@Override

			protected void paintComponent(Graphics grphcs) {

				grphcs.setColor(new Color(114, 113, 113));

				grphcs.fillRect(0, 0, getWidth(), getHeight());

				grphcs.setColor(Color.WHITE);

				grphcs.fillRect(1, 1, getWidth() - 2, getHeight() - 2);

			}

		};

		header = new javax.swing.JPanel();

		cmdForward = new com.datechooser.simple.Button();

		MY = new javax.swing.JLayeredPane();

		cmdMonth = new com.datechooser.simple.Button();

		lb = new javax.swing.JLabel();

		cmdYear = new com.datechooser.simple.Button();

		cmdPrevious = new com.datechooser.simple.Button();

		slide = new com.datechooser.simple.Slider();

		setBackground(new java.awt.Color(255, 255, 255));

		header.setBackground(new java.awt.Color(204, 93, 93));

		header.setMaximumSize(new java.awt.Dimension(262, 40));

		cmdForward.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		cmdForward.setIcon(new javax.swing.ImageIcon(getClass().getResource("forward.png")));

		cmdForward.setFocusable(true);

		cmdForward.setPaintBackground(false);

		cmdForward.addActionListener(new java.awt.event.ActionListener() {

			@Override

			public void actionPerformed(java.awt.event.ActionEvent evt) {

				cmdForwardActionPerformed(evt);

			}

		});

		java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0);

		flowLayout1.setAlignOnBaseline(true);

		MY.setLayout(flowLayout1);

		cmdMonth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		cmdMonth.setForeground(new java.awt.Color(255, 255, 255));

		cmdMonth.setFocusPainted(false);

		cmdMonth.setFont(new java.awt.Font("Kh Content", 0, 14));

		cmdMonth.setPaintBackground(false);

		cmdMonth.addActionListener(new java.awt.event.ActionListener() {

			@Override

			public void actionPerformed(java.awt.event.ActionEvent evt) {

				cmdMonthActionPerformed(evt);

			}

		});

		MY.add(cmdMonth);

		lb.setForeground(new java.awt.Color(255, 255, 255));

		lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		lb.setText(Character.toString(separator));

		MY.add(lb);

		cmdYear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		cmdYear.setForeground(new java.awt.Color(255, 255, 255));

		cmdYear.setFocusPainted(false);

		cmdYear.setFont(new java.awt.Font("Kh Content", 0, 14));

		cmdYear.setPaintBackground(false);

		cmdYear.addActionListener(new java.awt.event.ActionListener() {

			@Override

			public void actionPerformed(java.awt.event.ActionEvent evt) {

				cmdYearActionPerformed(evt);

			}

		});

		MY.add(cmdYear);

		cmdPrevious.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		cmdPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("previous.png")));

		cmdPrevious.setFocusable(true);

		cmdPrevious.setPaintBackground(false);

		cmdPrevious.addActionListener(new java.awt.event.ActionListener() {

			@Override

			public void actionPerformed(java.awt.event.ActionEvent evt) {

				cmdPreviousActionPerformed(evt);

			}

		});

		cmdPrevious.addKeyListener(new java.awt.event.KeyAdapter() {

			@Override

			public void keyPressed(java.awt.event.KeyEvent evt) {

				cmdPreviousKeyPressed(evt);

			}

		});

		javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);

		header.setLayout(headerLayout);

		headerLayout.setHorizontalGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						headerLayout.createSequentialGroup().addContainerGap()
								.addComponent(cmdPrevious, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(MY, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(cmdForward, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		headerLayout.setVerticalGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(cmdPrevious, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(MY, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cmdForward, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		slide.setLayout(new javax.swing.BoxLayout(slide, javax.swing.BoxLayout.LINE_AXIS));

		btnNewButton = new JButton("");

		btnNewButton.setIcon(new ImageIcon(DateChooser.class.getResource("close.png")));

		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnNewButton.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				popup.setVisible(false);

			}

		});

		btnNewButton_1 = new JButton("");

		btnNewButton_1.setIcon(new ImageIcon(DateChooser.class.getResource("today.png")));

		btnNewButton_1.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				toDay();

			}

		});

		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);

		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(slide, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
								.addComponent(header, GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
								.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 42,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnNewButton,
										GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(header, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(slide, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		this.setLayout(layout);

	}

	private void cmdPreviousActionPerformed(java.awt.event.ActionEvent evt) {

		switch (STATUS) {

		case 1:

			if (MONTH == 1) {

				MONTH = 12;

				YEAR--;

			}

			else {

				MONTH--;

			}

			setDateBack();

			break;

		case 3:

			setYearBack();

			break;

		default:

			if (YEAR >= 1000) {

				YEAR--;

				Months months = new Months(this.idioma);

				months.setEvent(getEventMonth());

				slide.slideToLeft(months);

				cmdYear.setText(YEAR + "");

			}

			break;

		}

	}

	private void cmdForwardActionPerformed(java.awt.event.ActionEvent evt) {

		if (STATUS == 1) {

			if (MONTH == 12) {

				MONTH = 1;

				YEAR++;

			}

			else {

				MONTH++;

			}

			setDateNext();

		}

		else if (STATUS == 3) {

			setYearNext();

		}

		else {

			YEAR++;

			Months months = new Months(this.idioma);

			months.setEvent(getEventMonth());

			slide.slideToLeft(months);

			cmdYear.setText(YEAR + "");

		}

	}

	private void cmdMonthActionPerformed(java.awt.event.ActionEvent evt) {

		if (STATUS != 2) {

			STATUS = 2;

			Months months = new Months(this.idioma);

			months.setEvent(getEventMonth());

			slide.slideToDown(months);

		}

		else {

			Dates dates = new Dates(this.idioma);

			dates.setForeground(getForeground());

			dates.setEvent(getEventDay(dates));

			dates.showDate(MONTH, YEAR, selectedDate);

			slide.slideToDown(dates);

			STATUS = 1;

		}

	}

	private void cmdYearActionPerformed(java.awt.event.ActionEvent evt) {

		if (STATUS != 3) {

			STATUS = 3;

			Years years = new Years();

			years.setEvent(getEventYear());

			startYear = years.showYear(YEAR);

			slide.slideToDown(years);

		}

		else {

			Dates dates = new Dates(this.idioma);

			dates.setForeground(getForeground());

			dates.setEvent(getEventDay(dates));

			dates.showDate(MONTH, YEAR, selectedDate);

			slide.slideToDown(dates);

			STATUS = 1;

		}

	}

	private void cmdPreviousKeyPressed(java.awt.event.KeyEvent evt) {

		if (evt.getKeyCode() == KeyEvent.VK_UP) {

			Component com = slide.getComponent(0);

			if (com instanceof Dates) {

				Dates d = (Dates) com;

				d.up();

			}

		}

		else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

			Component com = slide.getComponent(0);

			if (com instanceof Dates) {

				Dates d = (Dates) com;

				d.down();

			}

		}

		else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {

			Component com = slide.getComponent(0);

			if (com instanceof Dates) {

				Dates d = (Dates) com;

				d.back();

			}

		}

		else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {

			Component com = slide.getComponent(0);

			if (com instanceof Dates) {

				Dates d = (Dates) com;

				d.next();

			}

		}

	}

	public String getDateFormat() {

		return dateFormat;

	}

	public void setDateFormat(String dateFormat, char separator) {

		DateChooser.separator = separator;

		DateChooser.dateFormat = dateFormat;

	}

	public void setSelectedDate(Date date) {

		SimpleDateFormat df = new SimpleDateFormat(DateChooser.dateFormat);

		String d = df.format(date);

		DAY = Integer.valueOf(d.split(Character.toString(separator))[0]);

		MONTH = Integer.valueOf(d.split(Character.toString(separator))[1]);

		YEAR = Integer.valueOf(d.split(Character.toString(separator))[2]);

		selectedDate.setDay(DAY);

		selectedDate.setMonth(MONTH);

		selectedDate.setYear(YEAR);

		Dates dates = new Dates(this.idioma);

		dates.setForeground(getForeground());

		dates.setEvent(getEventDay(dates));

		dates.setSelected(DAY);

		dates.showDate(MONTH, YEAR, selectedDate);

		slide.slideNon(dates);

		ponerMes();

		cmdYear.setText(YEAR + "");

		setText(true, 0);

		STATUS = 1;

	}

	public SelectedDate getSelectedDate() {

		return selectedDate;

	}

	public void setSelectedDate(SelectedDate selectedDate) {

		this.selectedDate = selectedDate;

		DAY = selectedDate.getDay();

		MONTH = selectedDate.getMonth();

		YEAR = selectedDate.getYear();

		Dates dates = new Dates(this.idioma);

		dates.setForeground(getForeground());

		dates.setEvent(getEventDay(dates));

		dates.setSelected(DAY);

		dates.showDate(MONTH, YEAR, selectedDate);

		slide.slideNon(dates);

		ponerMes();

		cmdYear.setText(YEAR + "");

		setText(true, 0);

		STATUS = 1;

	}

	@Override

	public void setForeground(Color color) {

		super.setForeground(color);

		if (header != null) {

			header.setBackground(color);

			toDay(false);

		}

	}

	public CalendarLanguage getIdioma() {

		return idioma;

	}

}
