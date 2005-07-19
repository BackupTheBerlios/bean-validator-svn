package de.berlios.beanvalidator.test.validator;

import java.io.*;
import junit.framework.*;
import de.berlios.beanvalidator.validator.ValidatorConfiguration;
import de.berlios.beanvalidator.validator.ValidatorException;

public class ValidatorConfigurationTest extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(ValidatorConfigurationTest.class);
	}

	public ValidatorConfigurationTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(ValidatorConfigurationTest.class);
	}

	/*
	 * Test method for 'de.berlios.beanvalidator.util.validator.ValidatorConfiguration.loadConfigFile(File)'
	 */
	public void testLoadConfigFileFile() {
		File configFile = new File("src/validator.cfg.xml");
		ValidatorConfiguration cfg = new ValidatorConfiguration();
		try {
			cfg.loadConfigFile(configFile);
			assertTrue(true);
		}
		catch (IOException ioe) {
			fail(ioe.getMessage());
		}
	}

	/*
	 * Test method for 'de.berlios.beanvalidator.util.validator.ValidatorConfiguration.loadConfigFile(String)'
	 */
	public void testLoadConfigFileString() {
		ValidatorConfiguration cfg = new ValidatorConfiguration();
		try {
			cfg.loadConfigFile("validator.cfg.xml");
			assertTrue(true);
		}
		catch (IOException ioe) {
			fail(ioe.getMessage());
		}
	}

	/*
	 * Test method for 'de.berlios.beanvalidator.util.validator.ValidatorConfiguration.loadConfigFile()'
	 */
	public void testLoadConfigFile() {
		ValidatorConfiguration cfg = new ValidatorConfiguration();
		try {
			cfg.loadConfigFile();
			assertTrue(true);
		}
		catch (IOException ioe) {
			fail(ioe.getMessage());
		}
	}

//	/*
//	 * Test method for 'de.berlios.beanvalidator.util.validator.ValidatorConfiguration.configure(File)'
//	 */
//	public void testConfigureFile() {
//
//	}
//
//	/*
//	 * Test method for 'de.berlios.beanvalidator.util.validator.ValidatorConfiguration.configure(String)'
//	 */
//	public void testConfigureString() {
//
//	}
//
	/*
	 * Test method for 'de.berlios.beanvalidator.util.validator.ValidatorConfiguration.configure()'
	 */
	public void testConfigure() {
		ValidatorConfiguration cfg = new ValidatorConfiguration();
		try {
			ValidatorConfiguration config = cfg.configure();
			assertNotNull(config);
		}
		catch (ValidatorException ve) {
			fail(ve.getMessage());
		}
	}

}
