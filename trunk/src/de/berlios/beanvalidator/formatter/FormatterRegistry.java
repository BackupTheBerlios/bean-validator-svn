package de.berlios.beanvalidator.formatter;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

public class FormatterRegistry {
	private static Logger logger = Logger.getLogger(FormatterRegistry.class);
	private static FormatterRegistry registry;
	private Map<Class,Formatter> table;

	private FormatterRegistry() {
		table = new HashMap<Class,Formatter>();
		logger.debug("FormatterRegistry initialized");
	}

	public static FormatterRegistry getInstance() {
		if (registry == null) {
			registry = new FormatterRegistry(); 
		}
		return registry;
	}

	public void addFormatter(Class classToFormat, Formatter formatter) {
		table.put(classToFormat,formatter);
		logger.debug("Added formatter (" + formatter.getClass().getName() + ") for" + classToFormat.getName());
	}

	public void removeFormatter(Class classToFormat) {
		if (table.containsKey(classToFormat)) {
			table.remove(classToFormat);
			logger.debug("Removed formatter for " + classToFormat.getName());
		}
	}

	public Formatter getFormatter(Class classToFormat) {
		Formatter formatter = null;
		if (table.containsKey(classToFormat)) {
			formatter = table.get(classToFormat);
		}
		return formatter;
	}

	public boolean hasFormatter(Class classToFormat) {
		return table.containsKey(classToFormat);
	}
}
