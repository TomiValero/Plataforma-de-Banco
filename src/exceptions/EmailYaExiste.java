package exceptions;

public class EmailYaExiste extends Exception{

	public EmailYaExiste() {
		
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "El Email ya fue registrado";
	}

}
