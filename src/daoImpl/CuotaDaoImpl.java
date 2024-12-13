package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import dao.CuotaDao;
import entidad.Cuota;

public class CuotaDaoImpl implements CuotaDao {

	private static final String LISTAR_TODAS = "SELECT id, id_prestamo, num_cuota, fecha_pago, monto, pago FROM Cuotas";
	private static final String OBTENER_POR_ID = "SELECT id, id_prestamo, num_cuota, fecha_pago, monto, pago FROM Cuotas WHERE id = ?";
	private static final String OBTENER_POR_PRESTAMO_ID = "SELECT id, id_prestamo, num_cuota, fecha_pago, monto, pago FROM Cuotas WHERE id_prestamo = ?";
	private static final String INSERTAR = "INSERT INTO Cuotas (id_prestamo, num_cuota, fecha_pago, monto, pago) VALUES (?, ?, ?, ?, ?)";
	private static final String ACTUALIZAR = "UPDATE Cuotas SET pago = ?, fecha_pago = ? WHERE id = ?";
	private static final String ELIMINAR = "DELETE FROM Cuotas WHERE id = ?";
	private static final String OBTENER_PENDIENTES = "SELECT id, id_prestamo, num_cuota, fecha_pago, monto, pago FROM Cuotas WHERE id_prestamo IN (SELECT id FROM Prestamos WHERE id_cliente = ?) AND pago = 0";

    @Override
    public List<Cuota> listarTodasLasCuotas() {
        List<Cuota> cuotas = new ArrayList<>();
        PreparedStatement ps;
        Connection conn = Conexion.getConexion().getSQLConexion();
        ResultSet resultSet;
        try {
            ps = conn.prepareStatement(LISTAR_TODAS);
            resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idPrestamo = resultSet.getInt("id_prestamo");
                int numCuota = resultSet.getInt("num_cuota");
                Date fechaPago = resultSet.getDate("fecha_pago");
                double monto = resultSet.getDouble("monto");
                boolean pago = resultSet.getBoolean("pago");

                Cuota cuota = new Cuota(
                    id, 
                    idPrestamo, 
                    numCuota, 
                    fechaPago, 
                    monto, 
                    pago
                );
                
                cuotas.add(cuota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuotas;
    }


    @Override
    public Cuota obtenerCuotaPorId(int id) {
        Cuota cuota = null;
        PreparedStatement statement;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        ResultSet resultSet;
        try {
            statement = conexion.prepareStatement(OBTENER_POR_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int idCuota = resultSet.getInt("id");
                int idPrestamo = resultSet.getInt("id_prestamo");
                int numCuota = resultSet.getInt("num_cuota");
                Date fechaPago = resultSet.getDate("fecha_pago");
                double monto = resultSet.getDouble("monto");
                boolean pago = resultSet.getBoolean("pago");

                cuota = new Cuota(
                    idCuota, 
                    idPrestamo, 
                    numCuota, 
                    fechaPago, 
                    monto, 
                    pago
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuota;
    }


    @Override
    public boolean insertarCuota(Cuota cuota) {
        PreparedStatement statement;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try {
            statement = conexion.prepareStatement(INSERTAR);
            statement.setInt(1, cuota.getIdPrestamo());
            statement.setInt(2, cuota.getNumCuota());
            statement.setDate(3, new java.sql.Date(cuota.getFechaPago().getTime()));
            statement.setDouble(4, cuota.getMonto());
            statement.setBoolean(5, cuota.getPago());
            
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizarCuota(Cuota cuota) {
    	PreparedStatement statement;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try {
        	statement = conexion.prepareStatement(ACTUALIZAR);
        	statement.setBoolean(1, cuota.getPago());
        	statement.setDate(2, new java.sql.Date(cuota.getFechaPago().getTime()));
        	statement.setInt(3, cuota.getId());
            
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
    public boolean eliminarCuota(int id) {
    	PreparedStatement statement;
     	Connection conexion = Conexion.getConexion().getSQLConexion();
         try {
         	statement = conexion.prepareStatement(ELIMINAR);
             statement.setInt(1, id);
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
    public List<Cuota> obtenerCuotasPendientesPorCliente(int clienteId) {
    	List<Cuota> cuotas = new ArrayList<>();
        PreparedStatement ps;
        Connection conn = Conexion.getConexion().getSQLConexion();
        ResultSet resultSet;
        try {
            ps = conn.prepareStatement(OBTENER_PENDIENTES);
            ps.setInt(1, clienteId);
            resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idPrestamo = resultSet.getInt("id_prestamo");
                int numCuota = resultSet.getInt("num_cuota");
                Date fechaPago = resultSet.getDate("fecha_pago");
                double monto = resultSet.getDouble("monto");
                boolean pago = resultSet.getBoolean("pago");

                Cuota cuota = new Cuota(
                    id, 
                    idPrestamo, 
                    numCuota, 
                    fechaPago, 
                    monto, 
                    pago
                );
                
                cuotas.add(cuota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuotas;
    }
    
        

	@Override
	public List<Cuota> listarCuotasByPrestamoId(int idPrestamoParameter) {
		List<Cuota> cuotas = new ArrayList<>();
        PreparedStatement ps;
        Connection conn = Conexion.getConexion().getSQLConexion();
        ResultSet resultSet;
        try {
            ps = conn.prepareStatement(OBTENER_POR_PRESTAMO_ID);
            ps.setInt(1, idPrestamoParameter);
            resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idPrestamo = resultSet.getInt("id_prestamo");
                int numCuota = resultSet.getInt("num_cuota");
                Date fechaPago = resultSet.getDate("fecha_pago");
                double monto = resultSet.getDouble("monto");
                boolean pago = resultSet.getBoolean("pago");

                Cuota cuota = new Cuota(
                    id, 
                    idPrestamo, 
                    numCuota, 
                    fechaPago, 
                    monto, 
                    pago
                );
                
                cuotas.add(cuota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuotas;
    }	
}
