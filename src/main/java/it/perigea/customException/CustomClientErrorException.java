package it.perigea.customException;

public class CustomClientErrorException extends RuntimeException{

	private static final long serialVersionUID = 203244740156593938L;

	public CustomClientErrorException(String errorMessage) {
		super(errorMessage);
	}

}