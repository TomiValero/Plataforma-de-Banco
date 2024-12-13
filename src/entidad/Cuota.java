package entidad;

import java.util.Date;

public class Cuota {

    private int id;
    private int idPrestamo;
    private int numCuota;
    private Date fechaPago;
    private double monto;
    private boolean pago;

    public Cuota() {
    }

    public Cuota(int id, int idPrestamo, int numCuota, Date fechaPago, double monto, boolean pago) {
        this.id = id;
        this.idPrestamo = idPrestamo;
        this.numCuota = numCuota;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.pago = pago;
    }
    
    public Cuota(int id, boolean pago) {
        this.id = id;
        this.pago = pago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getNumCuota() {
        return numCuota;
    }

    public void setNumCuota(int numCuota) {
        this.numCuota = numCuota;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public boolean getPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

	@Override
	public String toString() {
		return "Cuota id=" + id + ", idPrestamo=" + idPrestamo + ", numCuota=" + numCuota + ", fechaPago=" + fechaPago
				+ ", monto=" + monto + ", pago=" + pago;
	}

    
}
