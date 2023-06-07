package it.perigea.importer.customException;

@SuppressWarnings("serial")
public class CustomServerErrorException extends RuntimeException{

	public CustomServerErrorException(String errorMessage) {
		super(errorMessage);
	}

}