package daoImpl;

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

import dao.GenericDAO;
import dao.IGenericDAO;
import dao.IMovimientosDAO;
import entidad.Movimientos;
import entidad.Prestamos;

public class MovimientosDaoImpl implements IMovimientosDAO<Movimientos>{

	private static final String insert = "INSERT INTO Movimientos(id_tipo_movimiento, monto, fecha, id_cuenta) VALUES(?, ?, ?, ?)";
    private static final String update = "UPDATE Movimientos SET id_tipo_movimiento=?, monto=?, fecha=?, id_cuenta=? WHERE id = ?";
    private static final String delete = "DELETE FROM Movimientos WHERE id = ?";
    private static final String readAll = "SELECT * FROM Movimientos";
    private static final String readAllByIdCuenta = "SELECT * FROM Movimientos WHERE id_cuenta = ?";
    private static final String readById = "SELECT * FROM Movimientos WHERE id = ?";

    @Override
    public boolean insert(Movimientos movimiento) {
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
        try { 
        	statement = conexion.prepareStatement(insert);
            statement.setInt(1, movimiento.getTipoMovimiento());
            statement.setDouble(2, movimiento.getMonto());
            statement.setDate(3, new java.sql.Date(movimiento.getFecha().getTime()));
            statement.setDouble(4, movimiento.getIdCuenta());

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
    public boolean update(Movimientos movimiento) {
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
        try { 
        	statement = conexion.prepareStatement(update);
            statement.setInt(1, movimiento.getTipoMovimiento());
            statement.setDouble(2, movimiento.getMonto());
            statement.setDate(3, new java.sql.Date(movimiento.getFecha().getTime()));
            statement.setDouble(4, movimiento.getIdCuenta());
            statement.setInt(5, movimiento.getId());

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
    public boolean delete(Movimientos movimiento) {
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
        try {
        	statement = conexion.prepareStatement(delete);
            statement.setInt(1, movimiento.getId());

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
    public List<Movimientos> list() {
        List<Movimientos> movimientos = new ArrayList<>();
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
    	ResultSet resultSet;
        try {
             statement = conexion.prepareStatement(readAll);
             resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idCuenta = resultSet.getInt("id_cuenta");
                int TipoMovimiento = resultSet.getInt("id_tipo_movimiento");
                double Monto = resultSet.getDouble("monto");
                Date fecha = resultSet.getDate("fecha");
                Movimientos movimiento = new Movimientos(id, TipoMovimiento, idCuenta, Monto, fecha);
                movimientos.add(movimiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimientos;
    }

    @Override
    public Movimientos findById(int id) {
        Movimientos movimiento = null;
        PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
    	ResultSet resultSet;
        try {
            statement = conexion.prepareStatement(readById);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int TipoMovimiento = resultSet.getInt("id_tipo_movimiento");
                    double Monto = resultSet.getDouble("monto");
                    Date fecha = resultSet.getTimestamp("fecha");
                    int idCuenta = resultSet.getInt("id_cuenta");
                    movimiento = new Movimientos(id, TipoMovimiento, idCuenta, Monto, fecha);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimiento;
    }

	@Override
	public List<Movimientos> listByIdCuenta(int idCuentaParameter) {
		 List<Movimientos> movimientos = new ArrayList<>();
	        PreparedStatement statement;
	    	Connection conexion = Conexion.getConexion().getSQLConexion();
	    	ResultSet resultSet;
	        try {
	             statement = conexion.prepareStatement(readAllByIdCuenta);
	             statement.setInt(1, idCuentaParameter);
	             resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                int idCuenta = resultSet.getInt("id_cuenta");
	                int TipoMovimiento = resultSet.getInt("id_tipo_movimiento");
	                double Monto = resultSet.getDouble("monto");
	                Date fecha = resultSet.getDate("fecha");
	                Movimientos movimiento = new Movimientos(id, TipoMovimiento, idCuenta, Monto, fecha);
	                movimientos.add(movimiento);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return movimientos;
	}

}
