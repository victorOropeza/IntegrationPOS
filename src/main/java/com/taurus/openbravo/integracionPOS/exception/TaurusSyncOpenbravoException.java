package com.taurus.openbravo.integracionPOS.exception;

public class TaurusSyncOpenbravoException extends Exception{

	private static final long serialVersionUID = 1L;

	public TaurusSyncOpenbravoException() {
		super();
	}

	public TaurusSyncOpenbravoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TaurusSyncOpenbravoException(String message, Throwable cause) {
		super(message, cause);
	}

	public TaurusSyncOpenbravoException(String message) {
		super(message);
	}

	public TaurusSyncOpenbravoException(Throwable cause) {
		super(cause);
	}
}
