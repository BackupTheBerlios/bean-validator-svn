package de.berlios.beanvalidator.test.formatter;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import de.berlios.beanvalidator.formatter.Formatter;
import de.berlios.beanvalidator.formatter.FormatterRegistry;
import de.berlios.beanvalidator.formatter.IntegerFormatter;

/**
 * JUnit tests for FormatterRegistry
 * @author $Author$
 * @version $Revision$
 * @see de.berlios.beanvalidator.util.formatter.FormatterRegistry
 */
public class FormatterRegistryTest extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(FormatterRegistryTest.class);
	}

	public FormatterRegistryTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(FormatterRegistryTest.class);
	}

	/*
	 * Test method for 'de.berlios.beanvalidator.util.formatter.FormatterRegistry.getInstance()'
	 */
	public void testGetInstance() {
		FormatterRegistry registry = FormatterRegistry.getInstance();
		assertTrue(registry instanceof FormatterRegistry);
	}

	/*
	 * Test method for 'de.berlios.beanvalidator.util.formatter.FormatterRegistry.addFormatter(Class, Formatter)'
	 */
	public void testAddFormatter() {
		testGetInstance();
		FormatterRegistry registry = FormatterRegistry.getInstance();
		registry.addFormatter(Integer.class, new IntegerFormatter());
		assertTrue(registry.hasFormatter(Integer.class));
	}

	/*
	 * Test method for 'de.berlios.beanvalidator.util.formatter.FormatterRegistry.removeFormatter(Class)'
	 */
	public void testRemoveFormatter() {
		testGetInstance();
		testAddFormatter();
		FormatterRegistry registry = FormatterRegistry.getInstance();
		registry.removeFormatter(Integer.class);
		assertFalse(registry.hasFormatter(Integer.class));
	}

	/*
	 * Test method for 'de.berlios.beanvalidator.util.formatter.FormatterRegistry.getFormatter(Class)'
	 */
	public void testGetFormatter() {
		testGetInstance();
		testAddFormatter();
		FormatterRegistry registry = FormatterRegistry.getInstance();
		Formatter formatter = registry.getFormatter(Integer.class);
		assertTrue(formatter != null);
	}

	/*
	 * Test method for 'de.berlios.beanvalidator.util.formatter.FormatterRegistry.hasFormatter(Class)'
	 */
	public void testHasFormatter() {
		testGetInstance();
		testAddFormatter();
		FormatterRegistry registry = FormatterRegistry.getInstance();
		assertTrue(registry.hasFormatter(Integer.class));
		testRemoveFormatter();
		assertFalse(registry.hasFormatter(Integer.class));
	}

}
