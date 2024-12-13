package entidad;

import java.util.Date;

public class Clientes {
    
    private int id;
    private String dni;
    private String cuil;
    private String nombre;
    private String apellido;
    private String sexo;
    private String nacionalidad;
    private Date fechaNac;
    private String direccion;
    private int idLocalidad;
    private int idProvincia;
    private String correo;
    private String telefono;
    private String usuario;
    private String contrasena;
    private boolean baja;
    private int tipoUsuario;

    
    public Clientes() {
    }
    
    // Constructor
    public Clientes(int id, String dni, String cuil, String nombre, String apellido, String sexo,
                    String nacionalidad, Date fechaNac, String direccion, int localidad, 
                    int provincia, String correo, String telefono, String usuario, 
                    String contrasena, boolean baja, int tipoUsuario) {
        this.id = id;
        this.dni = dni;
        this.cuil = cuil;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.nacionalidad = nacionalidad;
        this.fechaNac = fechaNac;
        this.direccion = direccion;
        this.idLocalidad = localidad;
        this.idProvincia = provincia;
        this.correo = correo;
        this.telefono = telefono;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.baja = baja;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getLocalidad() {
        return idLocalidad;
    }

    public void setLocalidad(int localidad) {
        this.idLocalidad = localidad;
    }

    public int getProvincia() {
        return idProvincia;
    }

    public void setProvincia(int provincia) {
        this.idProvincia = provincia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isBaja() {
        return baja;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Cliente [ID=" + id + ", Nombre=" + nombre + " " + apellido + ", DNI=" + dni + 
               ", CUIL=" + cuil + ", Correo=" + correo + ", Usuario=" + usuario + "]";
    }
    
    public String nombreApellido() {
        return nombre + " " + apellido;
    }
}
