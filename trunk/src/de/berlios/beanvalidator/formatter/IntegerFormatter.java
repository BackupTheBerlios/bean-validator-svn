package de.berlios.beanvalidator.formatter;

public class IntegerFormatter implements Formatter {
	public IntegerFormatter() {}

	public String format(Object object) {
		Integer intObject = (Integer)object;
		return intObject.toString();
	}
}
