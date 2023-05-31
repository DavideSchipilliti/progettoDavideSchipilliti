package it.perigea.customException;

public class CustomClientErrorException extends RuntimeException{

	private static final long serialVersionUID = -6377531231488638523L;
	
	public CustomClientErrorException(String errorMessage) {
		super(errorMessage);
	}

}