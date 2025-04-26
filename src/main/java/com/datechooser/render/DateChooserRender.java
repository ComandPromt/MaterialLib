package com.datechooser.render;

import java.util.Date;

import com.datechooser.material.DateBetween;
import com.datechooser.material.DateChooser;

public interface DateChooserRender {

	public String renderLabelCurrentDate(DateChooser dateChooser, Date date);

	public String renderTextFieldDate(DateChooser dateChooser, Date date);

	public String renderTextFieldDateBetween(DateChooser dateChooser, DateBetween dateBetween);
}
