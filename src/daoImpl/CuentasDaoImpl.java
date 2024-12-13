package daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.GenericDAO;
import dao.ICuentasDAO;
import dao.IGenericDAO;
import entidad.Clientes;
import entidad.Cuentas;
import entidad.TipoDeCuenta;

public class CuentasDaoImpl implements ICuentasDAO<Cuentas> {

    private static final String insert = "INSERT INTO Cuentas(id_cliente, fecha_alta, id_tipo_cuenta, num_cuenta, cbu, saldo, baja) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String update = "UPDATE Cuentas SET id_cliente=?, fecha_alta=?, id_tipo_cuenta=?, num_cuenta=?, cbu=?, saldo=?, baja=? WHERE id = ?";
    private static final String delete = "DELETE FROM Cuentas WHERE id = ?";
    private static final String readAll = "SELECT * FROM Cuentas";
    private static final String readById = "SELECT * FROM Cuentas WHERE id = ?";
    private static final String readByCbu = "SELECT * FROM Cuentas WHERE cbu = ?";
    private static final String readByIdCliente = "SELECT * FROM Cuentas WHERE id_cliente = ?";
    private static final String readLikeCbu = "SELECT * FROM Cuentas WHERE cbu = ?";

    @Override
    public boolean insert(Cuentas cuenta) {
    	Connection conexion = Conexion.getConexion().getSQLConexion();
    	PreparedStatement statement;
        try {
            statement = conexion.prepareStatement(insert);
            statement.setInt(1, cuenta.getCliente());
            statement.setDate(2, new java.sql.Date(cuenta.getFechaAlta().getTime()));
            statement.setInt(3, cuenta.getTipoCuenta());
            statement.setInt(4, cuenta.getNumCuenta());
            statement.setInt(5, cuenta.getCbu());
            statement.setDouble(6, cuenta.getSaldo());
            statement.setBoolean(7, cuenta.isBaja());

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
    public boolean update(Cuentas cuenta) {
    	Connection conexion = Conexion.getConexion().getSQLConexion();
    	PreparedStatement statement;
        try {
        	statement = conexion.prepareStatement(update);    
            statement.setInt(1, cuenta.getCliente());
            statement.setDate(2, new java.sql.Date(cuenta.getFechaAlta().getTime()));
            statement.setInt(3, cuenta.getTipoCuenta());
            statement.setInt(4, cuenta.getNumCuenta());
            statement.setInt(5, cuenta.getCbu());
            statement.setDouble(6, cuenta.getSaldo());
            statement.setBoolean(7, cuenta.isBaja());
            statement.setInt(8, cuenta.getId());

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
    public boolean delete(Cuentas cuenta) {
    	Connection conexion = Conexion.getConexion().getSQLConexion();
    	PreparedStatement statement;
        try {
        	statement = conexion.prepareStatement(delete);
            statement.setInt(1, cuenta.getId());
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
    public List<Cuentas> list() {
        List<Cuentas> cuentas = new ArrayList<>();
        Connection conexion = Conexion.getConexion().getSQLConexion();
    	PreparedStatement statement;
    	ResultSet resultSet;
        try {  
        	statement = conexion.prepareStatement(readAll);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idCliente = resultSet.getInt("id_cliente");
                //IGenericDAO<Clientes> clienteDao = new GenericDAO<Clientes>() {};
                //Clientes cliente = clienteDao.findById(idCliente);

                Date fechaAlta = resultSet.getDate("fecha_alta");
                int idTipoCuenta = resultSet.getInt("id_tipo_cuenta");
                //IGenericDAO<TipoDeCuenta> tipoDeCuentaDao = new GenericDAO<TipoDeCuenta>();
            	//TipoDeCuenta tipoCuenta=tipoDeCuentaDao.findById(idTipoCuenta);
               
                int numCuenta = resultSet.getInt("num_cuenta");
                int cbu = resultSet.getInt("cbu");
                double saldo = resultSet.getDouble("saldo");
                boolean baja = resultSet.getBoolean("baja");
                Cuentas cuenta = new Cuentas(id, idCliente, fechaAlta, idTipoCuenta, numCuenta, cbu, saldo, baja);
                cuentas.add(cuenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuentas;
    }

    @Override
    public Cuentas findById(int id) {
    	Cuentas cuenta = null;
		 Connection conexion = Conexion.getConexion().getSQLConexion();
		 PreparedStatement statement;
		 ResultSet resultSet;
	        try {
	            statement = conexion.prepareStatement(readById);
	            statement.setInt(1, id);
	            resultSet = statement.executeQuery(); 
	                if (resultSet.next()) {
	                    int idCliente = resultSet.getInt("id_cliente");
	                    //IGenericDAO<Clientes> clienteDao = new GenericDAO<Clientes>() {};
	                    //Clientes cliente = clienteDao.findById(idCliente);
	                    Date fechaAlta = resultSet.getDate("fecha_alta");
	                    int idTipoCuenta = resultSet.getInt("id_tipo_cuenta");
	                    //IGenericDAO<TipoDeCuenta> tipoDeCuentaDao = new GenericDAO<TipoDeCuenta>();
	                    //TipoDeCuenta tipoCuenta=tipoDeCuentaDao.findById(idTipoCuenta);                
	                    int numCuenta = resultSet.getInt("num_cuenta");
	                    int cbu = resultSet.getInt("cbu");
	                    double saldo = resultSet.getDouble("saldo");
	                    boolean baja = resultSet.getBoolean("baja");
	                    cuenta = new Cuentas(id, idCliente, fechaAlta, idTipoCuenta, numCuenta, cbu, saldo, baja);
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return cuenta;
    }

	@Override
	public List<Cuentas> findByIdCliente(int idClienteParameter) {
		 List<Cuentas> cuentas = new ArrayList<>();
	        Connection conexion = Conexion.getConexion().getSQLConexion();
	    	PreparedStatement statement;
	    	ResultSet resultSet;
	        try {  
	        	statement = conexion.prepareStatement(readByIdCliente);
	        	statement.setInt(1, idClienteParameter);
	            resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                int idCliente = resultSet.getInt("id_cliente");
	                //IGenericDAO<Clientes> clienteDao = new GenericDAO<Clientes>() {};
	                //Clientes cliente = clienteDao.findById(idCliente);

	                Date fechaAlta = resultSet.getDate("fecha_alta");
	                int idTipoCuenta = resultSet.getInt("id_tipo_cuenta");
	                //IGenericDAO<TipoDeCuenta> tipoDeCuentaDao = new GenericDAO<TipoDeCuenta>();
	            	//TipoDeCuenta tipoCuenta=tipoDeCuentaDao.findById(idTipoCuenta);
	               
	                int numCuenta = resultSet.getInt("num_cuenta");
	                int cbu = resultSet.getInt("cbu");
	                double saldo = resultSet.getDouble("saldo");
	                boolean baja = resultSet.getBoolean("baja");
	                Cuentas cuenta = new Cuentas(id, idCliente, fechaAlta, idTipoCuenta, numCuenta, cbu, saldo, baja);
	                cuentas.add(cuenta);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return cuentas;
	}

	@Override
	public Cuentas findByCbu(int cbuParametro) {
		Cuentas cuenta = null;
		 Connection conexion = Conexion.getConexion().getSQLConexion();
		 PreparedStatement statement;
		 ResultSet resultSet;
	        try {
	            statement = conexion.prepareStatement(readByCbu);
	            statement.setInt(1, cbuParametro);
	            resultSet = statement.executeQuery(); 
	                if (resultSet.next()) {
	                	int id = resultSet.getInt("id");
	                    int idCliente = resultSet.getInt("id_cliente");
	                    //IGenericDAO<Clientes> clienteDao = new GenericDAO<Clientes>() {};
	                    //Clientes cliente = clienteDao.findById(idCliente);
	                    Date fechaAlta = resultSet.getDate("fecha_alta");
	                    int idTipoCuenta = resultSet.getInt("id_tipo_cuenta");
	                    //IGenericDAO<TipoDeCuenta> tipoDeCuentaDao = new GenericDAO<TipoDeCuenta>();
	                    //TipoDeCuenta tipoCuenta=tipoDeCuentaDao.findById(idTipoCuenta);                
	                    int numCuenta = resultSet.getInt("num_cuenta");
	                    int cbu = resultSet.getInt("cbu");
	                    double saldo = resultSet.getDouble("saldo");
	                    boolean baja = resultSet.getBoolean("baja");
	                    cuenta = new Cuentas(id, idCliente, fechaAlta, idTipoCuenta, numCuenta, cbu, saldo, baja);
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return cuenta;
	}

	
}
