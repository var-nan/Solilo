package main.solilo.exceptions;

public class InvalidKeyException extends Exception{
	public InvalidKeyException(String key) {
		super(key);
		System.out.println("Key is not present");
		// TODO: verify syntax
	}
}
