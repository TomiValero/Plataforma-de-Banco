package entidad;

public class TipoDeMovimiento {
	private int ID;
	private String descripcion;
	
	public TipoDeMovimiento() {  
    }
	
	public TipoDeMovimiento(int iD, String descripcion) {
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
		return "TipoDeMovimiento ID=" + ID + ", descripcion=" + descripcion;
	}
}
