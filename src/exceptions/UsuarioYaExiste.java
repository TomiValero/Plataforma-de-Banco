package exceptions;

public class UsuarioYaExiste extends Exception{

	public UsuarioYaExiste() {
		
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "El Usuario ya fue registrado";
	}

}
