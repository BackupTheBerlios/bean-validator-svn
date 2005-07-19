package de.berlios.beanvalidator.validator;

/**
 * Core behaviors provided by all JavaBean-compliant objects that have
 * a specific validator.
 * @author $Author$
 * @version $Revision$
 */
public interface Validateable {
	/**
	 * Get validator for this bean.
	 * @return Bean-specific validator
	 */
	public Validator getValidator();

	/**
	 * Set validator for this bean.
	 * @param validator - bean-specific validator
	 */
	public void setValidator(Validator validator);
}
