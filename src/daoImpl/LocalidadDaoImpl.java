package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Localidad;
import entidad.Provincia;
import dao.GenericDAO;
import dao.IGenericDAO;

public class LocalidadDaoImpl implements IGenericDAO<Localidad> {

    private static final String insert = "INSERT INTO Localidades(id_provincia, descripcion) VALUES(?, ?)";
    private static final String update = "UPDATE Localidades SET id_provincia = ?, descripcion = ? WHERE id = ?";
    private static final String delete = "DELETE FROM Localidades WHERE id = ?";
    private static final String readAll = "SELECT * FROM Localidades";
    private static final String readById = "SELECT * FROM Localidades WHERE id = ?";

    @Override
    public boolean insert(Localidad localidad) {
        Connection conexion = Conexion.getConexion().getSQLConexion();
        PreparedStatement statement;
        try {
    	   	statement = conexion.prepareStatement(insert);
            statement.setInt(1, localidad.getProvincia().getID());
            statement.setString(2, localidad.getDescripcion());
            if (statement.executeUpdate() > 0) {
                conexion.commit();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conexion.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean update(Localidad localidad) {
        Connection conexion = Conexion.getConexion().getSQLConexion();
        PreparedStatement statement;
        try { 
        	statement = conexion.prepareStatement(update);
            statement.setInt(1, localidad.getProvincia().getID());
            statement.setString(2, localidad.getDescripcion());
            statement.setInt(3, localidad.getID());
            if (statement.executeUpdate() > 0) {
                conexion.commit();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conexion.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delete(Localidad localidad) {
        Connection conexion = Conexion.getConexion().getSQLConexion();
        PreparedStatement statement;
        try {
        	statement = conexion.prepareStatement(delete);
            statement.setInt(1, localidad.getID());
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
    public List<Localidad> list() {
        List<Localidad> localidades = new ArrayList<>();
        PreparedStatement statement;
        ResultSet resultSet;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try {
        	statement = conexion.prepareStatement(readAll);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String descripcion = resultSet.getString("descripcion");
                int idProvincia = resultSet.getInt("id_provincia");
                Provincia provincia = new Provincia(idProvincia, "");
                Localidad localidad = new Localidad(id, provincia, descripcion);
                localidades.add(localidad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localidades;
    }

    @Override
    public Localidad findById(int id) {
        Localidad localidad = null;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        PreparedStatement statement;
    	ResultSet resultSet;
        try {
        	statement = conexion.prepareStatement(readById);
            statement.setInt(1, id);         
            resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int idLocalidad = resultSet.getInt("id");
                    String descripcion = resultSet.getString("descripcion");
                    int idProvincia = resultSet.getInt("id_provincia");
                    Provincia provincia = new Provincia(idProvincia, "");
                    localidad = new Localidad(idLocalidad, provincia, descripcion);
                }         
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localidad;
    }

}