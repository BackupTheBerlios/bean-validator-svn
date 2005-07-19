package de.berlios.beanvalidator.test.formatter;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import de.berlios.beanvalidator.formatter.Formatter;
import de.berlios.beanvalidator.formatter.LongFormatter;

/**
 * JUnit tests for LongFormatter
 * @author $Author$
 * @version $Revision$
 * @see de.berlios.beanvalidator.util.formatter.LongFormatter
 */
public class LongFormatterTest extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(LongFormatterTest.class);
	}

	public LongFormatterTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(LongFormatterTest.class);
	}

	/*
	 * Test method for 'de.berlios.beanvalidator.util.formatter.LongFormatter.format(Object)'
	 */
	public void testFormat() {
		Formatter longFormatter = new LongFormatter();
		Long testLong = new Long(120000000);
		String testLongString = longFormatter.format(testLong);
		assertEquals(testLongString, "120000000");
	}
}
