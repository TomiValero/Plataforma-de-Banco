package daoImpl;

import java.util.List;
import java.sql.PreparedStatement;
import java.util.List;
import java.math.BigDecimal; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.IGenericDAO;
import entidad.TipoDeUsuarios;

public class TipoDeUsuarioDaoImpl implements IGenericDAO<TipoDeUsuarios>{

	private static final String insert = "INSERT INTO TiposDeUsuarios(descripcion) VALUES(?)";
    private static final String update = "UPDATE TiposDeUsuarios SET descripcion=? WHERE id=?";
    private static final String delete = "DELETE FROM TiposDeUsuarios WHERE id=?";
    private static final String readAll = "SELECT * FROM TiposDeUsuarios";
    private static final String readById = "SELECT * FROM TiposDeUsuarios WHERE id=?";

    @Override
    public boolean insert(TipoDeUsuarios tipoDeUsuario) {
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
        try {
        	statement = conexion.prepareStatement(insert);
            statement.setString(1, tipoDeUsuario.getDescripcion());

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
    public boolean update(TipoDeUsuarios tipoDeUsuario) {
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
        try { 
        	statement = conexion.prepareStatement(update);
            statement.setString(1, tipoDeUsuario.getDescripcion());
            statement.setInt(2, tipoDeUsuario.getID());

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
    public boolean delete(TipoDeUsuarios tipoDeUsuario) {
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
        try { 
        	statement = conexion.prepareStatement(delete);
            statement.setInt(1, tipoDeUsuario.getID());

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
    public List<TipoDeUsuarios> list() {
        List<TipoDeUsuarios> tiposDeUsuario = new ArrayList<>();
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
    	ResultSet resultSet;
        try {
              statement = conexion.prepareStatement(readAll);
              resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String descripcion = resultSet.getString("descripcion");

                TipoDeUsuarios tipoDeUsuario = new TipoDeUsuarios(id, descripcion);
                tiposDeUsuario.add(tipoDeUsuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiposDeUsuario;
    }

    @Override
    public TipoDeUsuarios findById(int id) {
        TipoDeUsuarios tipoDeUsuario = null;
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
    	ResultSet resultSet;
        try {
        	statement = conexion.prepareStatement(readById);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String descripcion = resultSet.getString("descripcion");
                    tipoDeUsuario = new TipoDeUsuarios(id, descripcion);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipoDeUsuario;
    }

}
