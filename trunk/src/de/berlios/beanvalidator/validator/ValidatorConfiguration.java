package de.berlios.beanvalidator.validator;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;
import java.util.regex.Pattern;

import org.apache.log4j.*;
import org.apache.commons.digester.*;
import org.xml.sax.*;

import de.berlios.beanvalidator.helpers.Loader;

/**
 * Loads and stores system bean validator configuration.
 * @author $Author$
 * @version $Revision$
 */
public class ValidatorConfiguration {
	/**
	 * Default configuration file (XML)
	 */
	public static final String DEFAULT_XML_CFG  = "validator.cfg.xml";
	
	/**
	 * Default configuration file (Properties)
	 */
	public static final String DEFAULT_PROP_CFG = "validator.cfg.properties";
	
	private static Logger logger = Logger.getLogger(ValidatorConfiguration.class);
	private Map<Class,Validator> validatorMap;
	private Validator currentValidator;
	private File configFile;
	private boolean isXML = false;

	/**
	 * Default constructor
	 */
	public ValidatorConfiguration() {
		validatorMap = new HashMap<Class,Validator>();
		logger.debug("ValidatorConfiguration instantiated");
	}

	/**
	 * Constructor
	 * @param configFilename - path to configuration file
	 */
	public ValidatorConfiguration(String configFilename) {
		this();
		try {
			loadConfigFile(configFilename);
			logger.debug("Using config filename: " + configFilename);
		}
		catch (IOException ioe) {
			logger.debug("Failed to load config file at instantiation", ioe);
		}
	}

	/**
	 * Constructor
	 * @param configFile - java.io.File object that represents configuration file 
	 */
	public ValidatorConfiguration(File configFile) {
		this();
		try {
			loadConfigFile(configFile);
			logger.debug("Using config file: " + configFile.getName());
		}
		catch (IOException ioe) {
			logger.debug("Failed to load config file at instantiation", ioe);
		}
	}

	/**
	 * Loads configuration file
	 * @param configFile - java.io.File object that represents configuration file 
	 * @throws IOException
	 */
	public void loadConfigFile(File configFile) 
			throws IOException {
		if (!configFile.canRead()) {
			logger.debug("Cannot read file: " + configFile.getCanonicalPath());
			throw new IOException("Cannot read file: " + configFile.getCanonicalPath());
		}
		isXML = (configFile.getName().endsWith("xml")) ? true : false;
		this.configFile = configFile;
	}

	/**
	 * Loads configuration file
	 * @param configFilename - path to configuration file
	 * @throws IOException
	 */
	public void loadConfigFile(String configFilename) 
			throws IOException {
		URL configFileUrl = Loader.getResource(configFilename);
		try {
			loadConfigFile(new File(configFileUrl.toURI()));
			isXML = (configFilename.endsWith("xml")) ? true : false;
		}
		catch (URISyntaxException use) {
			logger.debug("Failed to load config file: " + configFilename, use);
			throw new IOException(use.getMessage());
		}
	}

	/**
	 * Loads configuration file
	 * Will attempt to load default configuration files, in this order:
	 * <ol>
	 * 	<li>validator.cfg.xml</li>
	 * 	<li>validator.cfg.properties</li>
	 * </ol>
	 * @throws IOException
	 */
	public void loadConfigFile()
			throws IOException {
		// Try XML config file 1st.
		try {
			logger.debug("Attempting to load configuration from [" + DEFAULT_XML_CFG + "]");
			loadConfigFile(DEFAULT_XML_CFG);
			isXML = true;
		}
		catch (IOException ioe) {
			// Try Properties config file 2nd.
			try {
				logger.debug("Attempting to load configuration from [" + DEFAULT_PROP_CFG + "]");
				loadConfigFile(DEFAULT_PROP_CFG);
				isXML = false;
			}
			catch (IOException ioe2) {
				logger.warn("Failed to locate configuration", ioe2);
				throw ioe2;
			}
		}
	}

	/**
	 * Add validator for bean of specified class
	 * @param beanClass - Class of validateable bean
	 * @param beanValidator - Validator to use for beans of class beanClass
	 */
	public void addBeanValidator(Class beanClass, Validator beanValidator) {
		this.currentValidator = beanValidator;
		validatorMap.put(beanClass,beanValidator);
	}

	/**
	 * Add validator for bean of specified class
	 * @param beanClassName - Class name of validateable bean
	 * @param beanValidatorClassName - Class name of Validator to use for beans of class beanClass
	 */
	public void addBeanValidator(String beanClassName, String beanValidatorClassName) {
		try {
			Class beanClass = Class.forName(beanClassName);
			
			Class validatorClass = Class.forName(beanValidatorClassName);
			Constructor validatorConstructor = validatorClass.getConstructor(new Class[] {});
			Validator beanValidator = (Validator)validatorConstructor.newInstance(new Object[] {});
			
			addBeanValidator(beanClass,beanValidator);
		}
		catch (Exception e) {
			logger.warn("Failed to load class", e);
		}
	}

	/**
	 * Add property rule to current (top-of-stack) validator
	 * @param propertyName - name of bean property
	 * @param propertyRule - regex to apply to named property
	 * @param isRequired - is property required?
	 */
	public void addValidatorPropertyRule(String propertyName, String propertyRule, String isRequired) {
		// Convert isRequired to boolean.
		Boolean b = new Boolean(isRequired);
		boolean required = b.booleanValue();
		if (this.currentValidator != null) {
			this.currentValidator.addPropertyRule(propertyName, propertyRule, required);
		}
	}

	/**
	 * Add property rule to current (top-of-stack) validator
	 * @param propRule - PropertyRule that encapsulates validator rule
	 * @see de.berlios.beanvalidator.validator.ValidatorConfiguration#addValidatorPropertyRule(String, String, String)
	 */
	protected void addValidatorPropertyRule(PropertyRule propRule) {
		if (this.currentValidator != null) {
			this.currentValidator.addPropertyRule(propRule.getName(), propRule.getRule().pattern(), propRule.isRequired());
		}
	}

	/**
	 * Construct ValidatorConfiguration from configuration file
	 * @param configFile - java.io.File object that represents configuration file
	 * @return configured ValidatorConfiguration instance
	 * @throws ValidatorException
	 */
	public ValidatorConfiguration configure(File configFile) 
			throws ValidatorException {
		try {
			loadConfigFile(configFile);
		}
		catch (IOException ioe) {
			logger.warn("Failed to load config file '" + configFile.getName() + "'", ioe);
			throw new ValidatorException(ioe);
		}
		
		return configure();
	}

	/**
	 * Construct ValidatorConfiguration from configuration file
	 * @param configFilename - path to configuration file
	 * @return configured ValidatorConfiguration instance
	 * @throws ValidatorException
	 */
	public ValidatorConfiguration configure(String configFilename) 
			throws ValidatorException {
		try {
			loadConfigFile(configFilename);
		}
		catch (IOException ioe) {
			logger.warn("Failed to load config file '" + configFilename + "'", ioe);
			throw new ValidatorException(ioe);
		}
		return configure();
	}

	/**
	 * Construct ValidatorConfiguration from default configuration file
	 * @return configured ValidatorConfiguration instance
	 * @throws ValidatorException
	 */
	public ValidatorConfiguration configure() 
			throws ValidatorException {
		ValidatorConfiguration validatorConfig = null;
		
		// If we haven't loaded a config file yet, try defaults.
		if (this.configFile == null) {
			try {
				loadConfigFile();
			}
			catch (IOException ioe) {
				logger.warn("No configuration to load!", ioe);
				throw new ValidatorException(ioe);
			}
		}
		
		// If XML, use Digester to load ValidatorConfiguration.
		if (isXML) {
			validatorConfig = parseXmlConfiguration();
		}
		// If Properties, use custom Properties parser to load
		// ValidatorConfiguration.
		else {
			validatorConfig = parsePropertiesConfiguration();
		}
		
		return validatorConfig;
	}

	/**
	 * Build ValidatorConfiguration from XML configuration file
	 * @return configured ValidatorConfiguration instance
	 * @throws ValidatorException
	 */
	protected ValidatorConfiguration parseXmlConfiguration() 
			throws ValidatorException {
		ValidatorConfiguration validatorConfig = null;
		
		Digester digester = new Digester();
		digester.register("-//FoundationIDC/Validator Configuration DTD 1.0//EN", 
				          "de/foundationidc/util/validator/validator-config-1.0.dtd");
		digester.setValidating(true);
		
		PropertyRuleCreationFactory propRuleFactory = new PropertyRuleCreationFactory();
		propRuleFactory.setDigester(digester);
		
		digester.addObjectCreate("validatorConfig", ValidatorConfiguration.class);
		digester.addCallMethod("validatorConfig/bean", "addBeanValidator");
		digester.addCallParam("validatorConfig/bean/class", 0);
		digester.addCallParam("validatorConfig/bean/validator/class", 1);
		digester.addFactoryCreate("validatorConfig/bean/validator/propertyRule", propRuleFactory, false);
		digester.addCallMethod("validatorConfig/bean/validator/propertyRule", "addValidatorPropertyRule");
		digester.addCallParam("validatorConfig/bean/validator/propertyRule", 0, true);
		
		try {
			validatorConfig = (ValidatorConfiguration)digester.parse(configFile);
		}
		catch (Exception e) {
			throw new ValidatorException(e);
		}
		
		return validatorConfig;
	}

	protected ValidatorConfiguration parsePropertiesConfiguration() {
		// TODO Write parsePropertiesConfiguration()
		return null;
	}
}

class PropertyRule {
	private String name;
	private Pattern rule;
	private boolean required;

	public PropertyRule() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Pattern getRule() {
		return rule;
	}

	public void setRule(Pattern rule) {
		this.rule = rule;
	}

	public boolean isRequired(boolean required) {
		this.required = required;
		return required;
	}

	public boolean isRequired() {
		return required;
	}
}

class PropertyRuleCreationFactory extends AbstractObjectCreationFactory implements ObjectCreationFactory {
	public Object createObject(Attributes attributes) {
		String ruleName  = attributes.getValue("name");
		Pattern ruleRule = Pattern.compile(attributes.getValue("pattern"));
		boolean required = Boolean.parseBoolean(attributes.getValue("required"));
		PropertyRule rule = new PropertyRule();
		rule.setName(ruleName);
		rule.setRule(ruleRule);
		rule.isRequired(required);
		
		return rule;
	}
}