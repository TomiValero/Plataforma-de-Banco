package entidad;

import java.util.Date;

public class Movimientos {
    
    private int id;
    private int tipoMovimiento;
    private int idCuenta;
    private double monto;
    private Date fecha;

    public Movimientos() {  
    }
    
    // Constructor
    public Movimientos(int id, int tipoMovimiento, int idCuenta, double Monto, Date fecha) {
        this.id = id;
        this.tipoMovimiento = tipoMovimiento;
        this.monto = Monto;
        this.fecha = fecha;
        this.idCuenta = idCuenta;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(int tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public double getMonto() {
        return monto;
    }

    public void setTipoMonto(double Monto) {
        this.monto = Monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    public int getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}

	@Override
    public String toString() {
        return "Movimiento ID=" + id + ", Tipo Movimiento=" + tipoMovimiento + 
               ", Tipo Monto=" + monto + ", Fecha=" + fecha;
    }
}

