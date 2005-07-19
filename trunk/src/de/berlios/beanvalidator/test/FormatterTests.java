package de.berlios.beanvalidator.test;

import de.berlios.beanvalidator.test.formatter.DateFormatterTest;
import de.berlios.beanvalidator.test.formatter.FormatterRegistryTest;
import de.berlios.beanvalidator.test.formatter.IntegerFormatterTest;
import de.berlios.beanvalidator.test.formatter.LongFormatterTest;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * JUnit test suite for package de.berlios.beanvalidator.formatter
 * @author $Author$
 * @version $Revision$
 * @see de.berlios.beanvalidator.formatter
 */
public class FormatterTests {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(FormatterTests.suite());
	}

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Tests for de.berlios.beanvalidator.util.formatter");
		//$JUnit-BEGIN$
		suite.addTest(LongFormatterTest.suite());
		suite.addTest(IntegerFormatterTest.suite());
		suite.addTest(DateFormatterTest.suite());
		suite.addTest(FormatterRegistryTest.suite());
		//$JUnit-END$
		return suite;
	}

}
