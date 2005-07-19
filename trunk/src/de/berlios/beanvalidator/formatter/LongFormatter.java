package de.berlios.beanvalidator.formatter;

public class LongFormatter implements Formatter {
	public LongFormatter() {}

	public String format(Object object) {
		Long longObject = (Long)object;
		return longObject.toString();
	}

}
