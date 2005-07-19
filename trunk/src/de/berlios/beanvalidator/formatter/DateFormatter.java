package de.berlios.beanvalidator.formatter;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateFormatter implements Formatter {
	private SimpleDateFormat formatter;

	public DateFormatter() {}

	public DateFormatter(String format) {
		this();
		setFormat(format);
	}

	public String format(Object object) {
		Date date = (Date)object;
		String dateString = formatter.format(date);
		return dateString;
	}

	public void setFormat(String format) {
		this.formatter = new SimpleDateFormat(format);
	}
}
