package com.datechooser.render;

import java.util.Date;

import com.datechooser.material.DateBetween;
import com.datechooser.material.DateChooser;

public class DefaultDateChooserRender implements DateChooserRender {

	@Override
	public String renderLabelCurrentDate(DateChooser dateChooser, Date date) {

		return "Today : " + dateChooser.getDateFormat().format(date);

	}

	@Override
	public String renderTextFieldDate(DateChooser dateChooser, Date date) {

		return dateChooser.getDateFormat().format(date);

	}

	@Override
	public String renderTextFieldDateBetween(DateChooser dateChooser, DateBetween dateBetween) {

		if (dateBetween.getToDate() != null) {

			if (dateBetween.getFromDate().compareTo(dateBetween.getToDate()) == 0) {

				return dateChooser.getDateFormat().format(dateBetween.getFromDate());

			}

			else {

				return dateChooser.getDateFormat().format(dateBetween.getFromDate()) + dateChooser.getBetweenCharacter()
						+ dateChooser.getDateFormat().format(dateBetween.getToDate());

			}

		}

		else {

			return dateChooser.getDateFormat().format(dateBetween.getFromDate());

		}

	}

}
