package daoImpl;

import java.sql.Connection;  
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.GenericDAO;
import dao.IClientesDAO;
import dao.IGenericDAO;
import entidad.Clientes;
import entidad.Localidad;
import entidad.Provincia;

public class ClientesDaoImpl implements IClientesDAO<Clientes> {

    private static final String insert = "INSERT INTO Clientes (dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nac, direccion, id_localidad, id_provincia, correo, telefono, usuario, contraseña, baja, id_tipo_usu) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String update = "UPDATE Clientes SET dni=?, cuil=?, nombre=?, apellido=?, sexo=?, nacionalidad=?, fecha_nac=?, direccion=?, id_localidad=?, id_provincia=?, correo=?, telefono=?, usuario=?, contraseña=?, baja=?, id_tipo_usu=? WHERE id = ?";
    private static final String delete = "DELETE FROM Clientes WHERE id = ?";
    private static final String readAll = "SELECT * FROM clientes";
    private static final String readById = "SELECT * FROM Clientes WHERE id = ?";
    private static final String readByDni = "SELECT * FROM Clientes WHERE dni = ?";
    private static final String readByEmail = "SELECT * FROM Clientes WHERE correo = ?";
    private static final String readByUser = "SELECT * FROM Clientes WHERE usuario = ?";
    private static final String login = "SELECT * FROM Clientes WHERE usuario = ? AND contraseña = ?";

    //private IGenericDAO<Localidad> localidadDAO = new GenericDAO<Localidad>() {};
    //private IGenericDAO<Provincia> provinciaDAO = new GenericDAO<Provincia>() {};

    @Override
    public boolean insert(Clientes cliente) {
    	PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
        try {
            statement = conexion.prepareStatement(insert);
            statement.setString(1, cliente.getDni());
            statement.setString(2, cliente.getCuil());
            statement.setString(3, cliente.getNombre());
            statement.setString(4, cliente.getApellido());
            statement.setString(5, cliente.getSexo());
            statement.setString(6, cliente.getNacionalidad());
            statement.setDate(7, new Date(cliente.getFechaNac().getTime()));
            statement.setString(8, cliente.getDireccion());
            statement.setInt(9, cliente.getLocalidad());
            statement.setInt(10, cliente.getProvincia());
            statement.setString(11, cliente.getCorreo());
            statement.setString(12, cliente.getTelefono());
            statement.setString(13, cliente.getUsuario());
            statement.setString(14, cliente.getContrasena());
            statement.setBoolean(15, cliente.isBaja());
            statement.setInt(16, cliente.getTipoUsuario());

            if (statement.executeUpdate() > 0) {
                conexion.commit();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                Conexion.getConexion().getSQLConexion().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean update(Clientes cliente) {
    	PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
        try {
        	statement = conexion.prepareStatement(update);
            statement.setString(1, cliente.getDni());
            statement.setString(2, cliente.getCuil());
            statement.setString(3, cliente.getNombre());
            statement.setString(4, cliente.getApellido());
            statement.setString(5, cliente.getSexo());
            statement.setString(6, cliente.getNacionalidad());
            statement.setDate(7, new Date(cliente.getFechaNac().getTime()));
            statement.setString(8, cliente.getDireccion());
            statement.setInt(9, cliente.getLocalidad());
            statement.setInt(10, cliente.getProvincia());
            statement.setString(11, cliente.getCorreo());
            statement.setString(12, cliente.getTelefono());
            statement.setString(13, cliente.getUsuario());
            statement.setString(14, cliente.getContrasena());
            statement.setBoolean(15, cliente.isBaja());
            statement.setInt(16, cliente.getTipoUsuario());
            statement.setInt(17, cliente.getId());

            if (statement.executeUpdate() > 0) {
                conexion.commit();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                Conexion.getConexion().getSQLConexion().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delete(Clientes cliente) {
    	PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
        try {
        	statement = conexion.prepareStatement(delete);
            statement.setInt(1, cliente.getId());
            if (statement.executeUpdate() > 0) {
                conexion.commit();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Clientes> list() {
        List<Clientes> clientes = new ArrayList<>();
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
    	ResultSet resultSet;
        try {
        	statement = conexion.prepareStatement(readAll);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clientes.add(getCliente(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public Clientes findById(int id) {
        Clientes cliente = null;
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
    	ResultSet resultSet;     
        try {
        	statement = conexion.prepareStatement(readById);
            statement.setInt(1, id);
        	resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    cliente = getCliente(resultSet);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public Clientes login(String usuario, String contra) {

        Clientes cliente = null;
        PreparedStatement statement;
        ResultSet resultSet;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try {
        	statement = conexion.prepareStatement(login);
            statement.setString(1, usuario);
            statement.setString(2, contra);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cliente = getCliente(resultSet);
            } 
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }
    
    private Clientes getCliente(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String dni = resultSet.getString("dni");
        String cuil = resultSet.getString("cuil");
        String nombre = resultSet.getString("nombre");
        String apellido = resultSet.getString("apellido");
        String sexo = resultSet.getString("sexo");
        String nacionalidad = resultSet.getString("nacionalidad");
        Date fechaNac = resultSet.getDate("fecha_nac");
        String direccion = resultSet.getString("direccion");
        int idLocalidad = resultSet.getInt("id_localidad");
        //Localidad localidad = localidadDAO.findById(idLocalidad);
        int idProvincia = resultSet.getInt("id_provincia");
        //Provincia provincia = provinciaDAO.findById(idProvincia);
        String correo = resultSet.getString("correo");
        String telefono = resultSet.getString("telefono");
        String usuario = resultSet.getString("usuario");
        String contrasena = resultSet.getString("contraseña");
        boolean baja = resultSet.getBoolean("baja");
        int tipoUsuario = resultSet.getInt("id_tipo_usu");

        return new Clientes(id, dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, idLocalidad, idProvincia, correo, telefono, usuario, contrasena, baja, tipoUsuario);
    }

	@Override
	public Clientes findByDni(String dni) {
		Clientes cliente = null;
        PreparedStatement statement;
        ResultSet resultSet;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try {
            statement = conexion.prepareStatement(readByDni);
            statement.setString(1, dni);
            resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    cliente = getCliente(resultSet);
                }            
            
    	}
        catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
	}
	

	@Override
	public Clientes findByEmail(String email) {
		Clientes cliente = null;
        PreparedStatement statement;
        ResultSet resultSet;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try {
            statement = conexion.prepareStatement(readByEmail);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    cliente = getCliente(resultSet);
                }            
            
    	}
        catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
	}

	@Override
	public Clientes findByUser(String user) {
		Clientes cliente = null;
        PreparedStatement statement;
        ResultSet resultSet;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try {
            statement = conexion.prepareStatement(readByUser);
            statement.setString(1, user);
            resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    cliente = getCliente(resultSet);
                }            
            
    	}
        catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
	}
}
