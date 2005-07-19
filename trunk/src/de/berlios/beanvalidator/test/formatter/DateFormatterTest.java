package de.berlios.beanvalidator.test.formatter;

import de.berlios.beanvalidator.formatter.DateFormatter;
import java.util.*;
import junit.framework.*;

public class DateFormatterTest extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(DateFormatterTest.class);
	}

	public DateFormatterTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(DateFormatterTest.class);
	}

	/*
	 * Test method for 'de.berlios.beanvalidator.util.formatter.DateFormatter.DateFormatter()'
	 */
	public void testDateFormatter() {
		DateFormatter dateFormatter = new DateFormatter();
		assertNotNull(dateFormatter);
	}

	/*
	 * Test method for 'de.berlios.beanvalidator.util.formatter.DateFormatter.DateFormatter(String)'
	 */
	public void testDateFormatterString() {
		DateFormatter dateFormatter = new DateFormatter("MM/dd/yyyy");
		assertNotNull(dateFormatter);
	}

	/*
	 * Test method for 'de.berlios.beanvalidator.util.formatter.DateFormatter.format(Object)'
	 */
	public void testFormat() {
		DateFormatter dateFormatter = new DateFormatter();
		dateFormatter.setFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(1977,10,13);
		Date date = calendar.getTime();
		String formattedDate = dateFormatter.format(date);
		assertEquals(formattedDate,"11/13/1977");
	}

	/*
	 * Test method for 'de.berlios.beanvalidator.util.formatter.DateFormatter.setFormat(String)'
	 */
	public void testSetFormat() {
		DateFormatter dateFormatter = new DateFormatter();
		dateFormatter.setFormat("MM/dd/yyyy");
		assertNotNull(dateFormatter);
	}

}
