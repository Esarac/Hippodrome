package exception;

public class InvalidAmountException extends RuntimeException {

	public InvalidAmountException() {	
		super();
	}
	
	public InvalidAmountException(String msg) {
		super(msg);
	}
	
	public InvalidAmountException(String msg, Throwable err) {
		super(msg, err);
	}
	
}
