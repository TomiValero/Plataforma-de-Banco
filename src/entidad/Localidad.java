package entidad;

public class Localidad {
	
	private int ID;
	private Provincia provincia;
	private String descripcion;
	
	public Localidad() {  
    }
	
	public Localidad(int iD, Provincia provincia, String descripcion) {
		super();
		ID = iD;
		this.provincia = provincia;
		this.descripcion = descripcion;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Localidad ID=" + ID + ", Descripcion " + descripcion;
	}
	
	
	
}
