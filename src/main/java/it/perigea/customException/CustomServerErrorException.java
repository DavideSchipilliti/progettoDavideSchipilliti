package it.perigea.customException;

@SuppressWarnings("serial")
public class CustomServerErrorException extends RuntimeException{

	public CustomServerErrorException(String errorMessage) {
		super(errorMessage);
	}

}