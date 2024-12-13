package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.IGenericDAO;
import dao.IPrestamosDAO;
import entidad.Clientes;
import entidad.Cuentas;
import entidad.Prestamos;

public class PrestamoDaoImpl implements IPrestamosDAO<Prestamos> {

    private static final String insert = "INSERT INTO Prestamos (id_cliente, id_cuenta, fecha_alta, importe_pedido, cant_cuotas, importe_por_mes, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String readAll = "SELECT * FROM Prestamos";
    private static final String findById = "SELECT * FROM Prestamos WHERE id=?";
    private static final String findByEstado = "SELECT * FROM Prestamos WHERE estado=?";
    private static final String findByIdCliente = "SELECT * FROM Prestamos WHERE id_cliente=?";
    private static final String update = "UPDATE Prestamos SET estado=? WHERE id=?";
    private static final String delete = "DELETE FROM Prestamos WHERE id=?";

    @Override
    public boolean insert(Prestamos prestamo) {
        PreparedStatement statement;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try {
        	statement = conexion.prepareStatement(insert);
        	statement.setInt(1, prestamo.getCliente());
        	statement.setInt(2, prestamo.getCuenta());
        	statement.setDate(3, new Date(prestamo.getFechaAlta().getTime()));
        	statement.setDouble(4, prestamo.getImportePedido());
        	statement.setInt(5, prestamo.getCantCuotas());
        	statement.setDouble(6, prestamo.getImportePorMes());
        	statement.setString(7, prestamo.getEstado());
        	
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
    public List<Prestamos> list() {
        List<Prestamos> prestamos = new ArrayList<>();
        PreparedStatement ps;
        Connection conn = Conexion.getConexion().getSQLConexion();
        ResultSet resultSet;
        try {
            ps = conn.prepareStatement(readAll);
            resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                Prestamos prestamo = new Prestamos();
                int idPrestamo = resultSet.getInt("id");
                String estado = resultSet.getString("estado");
                Date fechaAlta = resultSet.getDate("fecha_alta");
                double importePedido = resultSet.getDouble("importe_pedido");
                int cantCuotas = resultSet.getInt("cant_cuotas");
                double importePorMes = resultSet.getDouble("importe_por_mes");
                int idCliente = resultSet.getInt("id_cliente");
                int idCuenta = resultSet.getInt("id_cuenta");

                prestamo = new Prestamos(
                        idPrestamo, 
                        idCliente, 
                        idCuenta, 
                        fechaAlta, 
                        importePedido, 
                        cantCuotas, 
                        importePorMes, 
                        estado
                    );   
                
                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamos;
    }

    @Override
    public Prestamos findById(int id) {
        Prestamos prestamo = null;
        PreparedStatement statement;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        ResultSet resultSet;
        try {
            statement = conexion.prepareStatement(findById);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int idPrestamo = resultSet.getInt("id");
                String estado = resultSet.getString("estado");
                Date fechaAlta = resultSet.getDate("fecha_alta");
                double importePedido = resultSet.getDouble("importe_pedido");
                int cantCuotas = resultSet.getInt("cant_cuotas");
                double importePorMes = resultSet.getDouble("importe_por_mes");
                int idCliente = resultSet.getInt("id_cliente");
                int idCuenta = resultSet.getInt("id_cuenta");

                prestamo = new Prestamos(
                        idPrestamo, 
                        idCliente, 
                        idCuenta, 
                        fechaAlta, 
                        importePedido, 
                        cantCuotas, 
                        importePorMes, 
                        estado
                    );            
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return prestamo;
    }


    @Override
    public boolean update(Prestamos prestamo) {
        PreparedStatement statement;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try {
        	statement = conexion.prepareStatement(update);
        	statement.setString(1, prestamo.getEstado());
        	statement.setInt(2, prestamo.getId());
            
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
    public boolean delete(Prestamos prestamo) {
    	 PreparedStatement statement;
     	Connection conexion = Conexion.getConexion().getSQLConexion();
         try {
         	statement = conexion.prepareStatement(delete);
             statement.setInt(1, prestamo.getId());

             if (statement.executeUpdate() > 0) {
                 conexion.commit();
                 return true;
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return false;
    }

    public List<Prestamos> findByEstado(String estadoParameter) {
    	List<Prestamos> prestamos = new ArrayList<>();
        PreparedStatement ps;
        Connection conn = Conexion.getConexion().getSQLConexion();
        ResultSet resultSet;
        try {
            ps = conn.prepareStatement(findByEstado);
            ps.setString(1, estadoParameter);
            resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                Prestamos prestamo = new Prestamos();
                int idPrestamo = resultSet.getInt("id");
                String estado = resultSet.getString("estado");
                Date fechaAlta = resultSet.getDate("fecha_alta");
                double importePedido = resultSet.getDouble("importe_pedido");
                int cantCuotas = resultSet.getInt("cant_cuotas");
                double importePorMes = resultSet.getDouble("importe_por_mes");
                int idCliente = resultSet.getInt("id_cliente");
                int idCuenta = resultSet.getInt("id_cuenta");

                prestamo = new Prestamos(
                        idPrestamo, 
                        idCliente, 
                        idCuenta, 
                        fechaAlta, 
                        importePedido, 
                        cantCuotas, 
                        importePorMes, 
                        estado
                    );   
                
                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamos;
    }

	@Override
	public List<Prestamos> findByIdCliente(int IdClienteParameter) {
		List<Prestamos> prestamos = new ArrayList<>();
        PreparedStatement ps;
        Connection conn = Conexion.getConexion().getSQLConexion();
        ResultSet resultSet;
        try {
            ps = conn.prepareStatement(findByIdCliente);
            ps.setInt(1, IdClienteParameter);
            resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                Prestamos prestamo = new Prestamos();
                int idPrestamo = resultSet.getInt("id");
                String estado = resultSet.getString("estado");
                Date fechaAlta = resultSet.getDate("fecha_alta");
                double importePedido = resultSet.getDouble("importe_pedido");
                int cantCuotas = resultSet.getInt("cant_cuotas");
                double importePorMes = resultSet.getDouble("importe_por_mes");
                int idCliente = resultSet.getInt("id_cliente");
                int idCuenta = resultSet.getInt("id_cuenta");

                prestamo = new Prestamos(
                        idPrestamo, 
                        idCliente, 
                        idCuenta, 
                        fechaAlta, 
                        importePedido, 
                        cantCuotas, 
                        importePorMes, 
                        estado
                    );   
                
                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamos;
	}
}
