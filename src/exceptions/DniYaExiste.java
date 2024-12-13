package exceptions;

public class DniYaExiste extends Exception{

	public DniYaExiste() {
		
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "El DNI ya fue registrado";
	}

}
