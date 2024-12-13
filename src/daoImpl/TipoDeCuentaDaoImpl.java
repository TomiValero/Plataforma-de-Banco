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
import entidad.TipoDeCuenta;

public class TipoDeCuentaDaoImpl implements IGenericDAO<TipoDeCuenta>{

	private static final String insert = "INSERT INTO TiposDeCuentas(descripcion) VALUES(?)";
    private static final String update = "UPDATE TiposDeCuentas SET descripcion=? WHERE id=?";
    private static final String delete = "DELETE FROM TiposDeCuentas WHERE id=?";
    private static final String readAll = "SELECT * FROM TiposDeCuentas";
    private static final String readById = "SELECT * FROM TiposDeCuentas WHERE id=?";

    @Override
    public boolean insert(TipoDeCuenta tipoDeCuenta) {
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
        try { 
        	statement = conexion.prepareStatement(insert);
            statement.setString(1, tipoDeCuenta.getDescripcion());

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
    public boolean update(TipoDeCuenta tipoDeCuenta) {
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
        try {
        	statement = conexion.prepareStatement(update);
            statement.setString(1, tipoDeCuenta.getDescripcion());
            statement.setInt(2, tipoDeCuenta.getID());

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
    public boolean delete(TipoDeCuenta tipoDeCuenta) {
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
        try { 
        	statement = conexion.prepareStatement(delete);
            statement.setInt(1, tipoDeCuenta.getID());

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
    public List<TipoDeCuenta> list() {
        List<TipoDeCuenta> tiposDeCuenta = new ArrayList<>();
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
    	ResultSet resultSet;
        try {
        	statement = conexion.prepareStatement(readAll);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String descripcion = resultSet.getString("descripcion");

                TipoDeCuenta tipoDeCuenta = new TipoDeCuenta(id, descripcion);
                tiposDeCuenta.add(tipoDeCuenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiposDeCuenta;
    }

    @Override
    public TipoDeCuenta findById(int id) {
        TipoDeCuenta tipoDeCuenta = null;
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
    	ResultSet resultSet;
        try { 
        	statement = conexion.prepareStatement(readById);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String descripcion = resultSet.getString("descripcion");
                    tipoDeCuenta = new TipoDeCuenta(id, descripcion);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipoDeCuenta;
    }
}
