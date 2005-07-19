package de.berlios.beanvalidator.helpers;

import java.net.URL;
import java.lang.IllegalAccessException;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 * Loads resources from various locations/sources. Inspired by
 * log4j's Loader class.
 * @author $Author$
 * @version $Revision$
 */
public class Loader {
	static final String TSTR = "Caught Exception while in Loader.getResource. This may be innocuous.";
	// We conservatively assume that we are running under Java 1.x
	static private boolean java1 = true;  
	//static private boolean ignoreTCL = false;

	static {
		String jVersion = System.getProperty("java.version", null);
		if(jVersion != null) {
			int i = jVersion.indexOf('.');
			if(i != -1) {	
				if(jVersion.charAt(i+1) != '1') {
					java1 = false;
				}
			} 
		}
	}

	public static URL getResource(String resource) {
		ClassLoader classLoader = null;
		URL url = null;
		
		try {
			// Try using thread context classloader 1st.
			if (!java1) {
				classLoader = getThreadClassLoader();
				if (classLoader != null) {
					url = classLoader.getResource(resource);
					if (url != null) {
						return url;
					}
				}
			}
			// Didn't find resource.
			// Try using classloader that loaded this class.
			classLoader = Loader.class.getClassLoader();
			if (classLoader != null) {
				url = classLoader.getResource(resource);
				if (url != null) {
					return url;
				}
			}
		}
		catch (Throwable t) {}
		
		// Last attempt!
		return ClassLoader.getSystemResource(resource);
	}

	private static ClassLoader getThreadClassLoader() 
			throws IllegalAccessException, InvocationTargetException{
		Method method = null;
		
		// Running JDK 1.2+?
		try {
			method = Thread.class.getMethod("getContextClassLoader", (Class[])null);
		}
		catch (NoSuchMethodException msme) {
			// Running JDK 1.1
			return null;
		}
		
		return (ClassLoader)method.invoke(Thread.currentThread(), (Object[])null);
	}
}
