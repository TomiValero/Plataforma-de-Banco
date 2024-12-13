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
import entidad.TipoDeMovimiento;

public class TipoDeMovimientoDaoImpl implements IGenericDAO<TipoDeMovimiento>{

	private static final String insert = "INSERT INTO TiposDeMovimientos(descripcion) VALUES(?)";
    private static final String update = "UPDATE TiposDeMovimientos SET descripcion=? WHERE id=?";
    private static final String delete = "DELETE FROM TiposDeMovimientos WHERE id=?";
    private static final String readAll = "SELECT * FROM TiposDeMovimientos";
    private static final String readById = "SELECT * FROM TiposDeMovimientos WHERE id=?";

    @Override
    public boolean insert(TipoDeMovimiento tipoDeMovimiento) {
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
        try { 
        	statement = conexion.prepareStatement(insert);
            statement.setString(1, tipoDeMovimiento.getDescripcion());

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
    public boolean update(TipoDeMovimiento tipoDeMovimiento) {
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
        try {
        	statement = conexion.prepareStatement(update);
            statement.setString(1, tipoDeMovimiento.getDescripcion());
            statement.setInt(2, tipoDeMovimiento.getID());

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
    public boolean delete(TipoDeMovimiento tipoDeMovimiento) {
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
        try { 
        	statement = conexion.prepareStatement(delete);
            statement.setInt(1, tipoDeMovimiento.getID());

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
    public List<TipoDeMovimiento> list() {
        List<TipoDeMovimiento> tiposDeMovimiento = new ArrayList<>();
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
    	ResultSet resultSet;
        try {
        	statement = conexion.prepareStatement(readAll);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String descripcion = resultSet.getString("descripcion");

                TipoDeMovimiento tipoDeMovimiento = new TipoDeMovimiento(id, descripcion);
                tiposDeMovimiento.add(tipoDeMovimiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiposDeMovimiento;
    }

    @Override
    public TipoDeMovimiento findById(int id) {
        TipoDeMovimiento tipoDeMovimiento = null;
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
    	ResultSet resultSet;
        try { 
        	statement = conexion.prepareStatement(readById);
            statement.setInt(1, id);
        	resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String descripcion = resultSet.getString("descripcion");
                    tipoDeMovimiento = new TipoDeMovimiento(id, descripcion);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipoDeMovimiento;
    }

}
