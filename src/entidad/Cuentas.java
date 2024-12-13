package entidad;

import java.math.BigDecimal; 
import java.util.Date;

public class Cuentas {
    
    private int id;
    private int idCliente;
    private Date fechaAlta;
    private int tipoCuenta;
    private int numCuenta;
    private int cbu;
    private double saldo;
    private boolean baja;

    public Cuentas() {  
    }
    
    // Constructor
    public Cuentas(int id, int cliente, Date fechaAlta, int tipoCuenta, int numCuenta, int cbu, double saldo, boolean baja) {
        this.id = id;
        this.idCliente = cliente;
        this.fechaAlta = fechaAlta;
        this.tipoCuenta = tipoCuenta;
        this.numCuenta = numCuenta;
        this.cbu = cbu;
        this.saldo = saldo;
        this.baja = baja;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente() {
        return idCliente;
    }

    public void setCliente(int idcliente) {
        this.idCliente = idcliente;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(int tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    public int getCbu() {
        return cbu;
    }

    public void setCbu(int cbu) {
        this.cbu = cbu;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isBaja() {
        return baja;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }

    @Override
    public String toString() {
        return "Número de Cuenta=" + numCuenta + ", CBU=" + cbu + ", Saldo=" + saldo;
    }
}
