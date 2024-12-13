package entidad;

import java.math.BigDecimal;
import java.util.Date;

public class Prestamos {

    private int id;
    private int idCliente;
    private int idCuenta;
    private Date fechaAlta;
    private double importePedido;
    private int cantCuotas;
    private double importePorMes;
    private String estado;

    public Prestamos() { }

    public Prestamos(int id, int idCliente, int idCuenta, Date fechaAlta, double importePedido, int cantCuotas, double importePorMes, String estado) {
        this.id = id;
        this.idCliente = idCliente;
        this.idCuenta = idCuenta;
        this.fechaAlta = fechaAlta;
        this.importePedido = importePedido;
        this.cantCuotas = cantCuotas;
        this.importePorMes = importePorMes;
        this.estado = estado;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCliente() { return idCliente; }
    public void setCliente(int cliente) { this.idCliente = cliente; }

    public int getCuenta() { return idCuenta; }
    public void setCuenta(int cuenta) { this.idCuenta = cuenta; }

    public Date getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(Date fechaAlta) { this.fechaAlta = fechaAlta; }

    public double getImportePedido() { return importePedido; }
    public void setImportePedido(double importePedido) { this.importePedido = importePedido; }

    public int getCantCuotas() { return cantCuotas; }
    public void setCantCuotas(int cantCuotas) { this.cantCuotas = cantCuotas; }

    public double getImportePorMes() { return importePorMes; }
    public void setImportePorMes(double importePorMes) { this.importePorMes = importePorMes; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
