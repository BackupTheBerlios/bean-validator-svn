package de.berlios.beanvalidator.test;

import de.berlios.beanvalidator.test.validator.ValidatorConfigurationTest;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * JUnit test suite for package de.berlios.beanvalidator.validator
 * @author $Author$
 * @version $Revision$
 * @see de.berlios.beanvalidator.validator
 */
public class ValidatorTests {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(ValidatorTests.suite());
	}

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for de.berlios.beanvalidator.util.test");
		//$JUnit-BEGIN$
		suite.addTest(ValidatorConfigurationTest.suite());
		//$JUnit-END$
		return suite;
	}

}
