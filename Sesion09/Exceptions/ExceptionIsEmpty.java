package Sesion09.Exceptions;

public class ExceptionIsEmpty extends Exception {
	public ExceptionIsEmpty(String msg){
		super(msg);
	}

	public ExceptionIsEmpty() {
		super("El stack esta vacio");
	}
}