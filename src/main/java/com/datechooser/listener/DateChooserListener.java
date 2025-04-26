package com.datechooser.listener;

import java.util.Date;

import com.datechooser.material.DateBetween;

public interface DateChooserListener {

	public void dateChanged(Date date, DateChooserAction action);

	public void dateBetweenChanged(DateBetween date, DateChooserAction action);

}
