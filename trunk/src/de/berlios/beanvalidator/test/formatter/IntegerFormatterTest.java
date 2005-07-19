package de.berlios.beanvalidator.test.formatter;

import de.berlios.beanvalidator.formatter.Formatter;
import de.berlios.beanvalidator.formatter.IntegerFormatter;
import junit.framework.*;

public class IntegerFormatterTest extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(IntegerFormatterTest.class);
	}

	public IntegerFormatterTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(IntegerFormatterTest.class);
	}

	/*
	 * Test method for 'de.berlios.beanvalidator.util.formatter.IntegerFormatter.format(Object)'
	 */
	public void testFormat() {
		Formatter intFormatter = new IntegerFormatter();
		Integer testInt = new Integer(12);
		String testIntString = intFormatter.format(testInt);
		assertEquals(testIntString, "12");
	}
}
