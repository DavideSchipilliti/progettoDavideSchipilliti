package it.perigea.customException;

public class CustomServerErrorException extends RuntimeException{

	private static final long serialVersionUID = -4785193728438537334L;
	
	public CustomServerErrorException(String errorMessage) {
		super(errorMessage);
	}

}