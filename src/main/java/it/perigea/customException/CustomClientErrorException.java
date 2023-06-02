package it.perigea.customException;

@SuppressWarnings("serial")
public class CustomClientErrorException extends RuntimeException{

	public CustomClientErrorException(String errorMessage) {
		super(errorMessage);
	}

}