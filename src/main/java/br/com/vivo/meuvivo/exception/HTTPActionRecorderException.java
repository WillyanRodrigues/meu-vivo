package br.com.vivo.meuvivo.exception;

public class HTTPActionRecorderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int status;
	
	private String message;
	
	public HTTPActionRecorderException(int status , String message) {
		this.status = status;
		this.message = message;
}

	public int getStatus() {
		return status;
	}


	public String getMessage() {
		return message;
	}

	
	

}
