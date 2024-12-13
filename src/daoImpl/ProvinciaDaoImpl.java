package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Clientes;
import entidad.Provincia;
import dao.GenericDAO;
import dao.IGenericDAO;

public class ProvinciaDaoImpl implements IGenericDAO<Provincia> {

    private static final String insert = "INSERT INTO Provincias(descripcion) VALUES(?)";
    private static final String update = "UPDATE Provincias SET descripcion = ? WHERE id = ?";
    private static final String delete = "DELETE FROM Provincias WHERE id = ?";
    private static final String readAll = "SELECT * FROM Provincias";
    private static final String readById = "SELECT * FROM Provincias WHERE id = ?";

    @Override
    public boolean insert(Provincia provincia) {
        Connection conexion = Conexion.getConexion().getSQLConexion();
        PreparedStatement statement;
        try {
        	statement = conexion.prepareStatement(insert);
            statement.setString(1, provincia.getDescripcion());
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
    public boolean update(Provincia provincia) {
        Connection conexion = Conexion.getConexion().getSQLConexion();
        PreparedStatement statement;
        try {
        	statement = conexion.prepareStatement(update);
            statement.setString(1, provincia.getDescripcion());
            statement.setInt(2, provincia.getID());
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
    public boolean delete(Provincia provincia) {
        Connection conexion = Conexion.getConexion().getSQLConexion();
        PreparedStatement statement;
        try { 
        	statement = conexion.prepareStatement(delete);
            statement.setInt(1, provincia.getID());
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
    public List<Provincia> list() {
        List<Provincia> provincias = new ArrayList<>();
        PreparedStatement statement;
        ResultSet resultSet;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try {
        	 statement = conexion.prepareStatement(readAll);      
             resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String descripcion = resultSet.getString("descripcion");
                provincias.add(new Provincia(id, descripcion));
            }
        }
         catch (SQLException e) {
            e.printStackTrace();
        }
        return provincias;
    }

    @Override
    public Provincia findById(int id) {
        Provincia provincia = null;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        PreparedStatement statement;
    	ResultSet resultSet;
        try{
        	statement = conexion.prepareStatement(readById);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int idProvincia = resultSet.getInt("id");
                    String descripcion = resultSet.getString("descripcion");
                    provincia = new Provincia(idProvincia, descripcion);
                }     
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return provincia;
    }

}
