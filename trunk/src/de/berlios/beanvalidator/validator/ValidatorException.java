package de.berlios.beanvalidator.validator;

public class ValidatorException extends Exception {
	static final long serialVersionUID = 7395670156704438520L;

	public ValidatorException() {
		super();
	}

	public ValidatorException(String message) {
		super(message);
	}

	public ValidatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidatorException(Throwable cause) {
		super(cause);
	}
}
