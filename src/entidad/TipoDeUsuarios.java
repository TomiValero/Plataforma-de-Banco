package entidad;

public class TipoDeUsuarios {
	private int ID;
	private String descripcion;
	
	public TipoDeUsuarios() {  
    }
	
	public TipoDeUsuarios(int iD, String descripcion) {
		ID = iD;
		this.descripcion = descripcion;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TipoDeUsuario ID=" + ID + ", descripcion=" + descripcion;
	}
}
