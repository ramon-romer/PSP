package ceu.dam.ad.services;

public class ClienteNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3253395021472326137L;

	public ClienteNotFoundException() {
		super();
	}

	public ClienteNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ClienteNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClienteNotFoundException(String message) {
		super(message);
	}

	public ClienteNotFoundException(Throwable cause) {
		super(cause);
	}

	


}
