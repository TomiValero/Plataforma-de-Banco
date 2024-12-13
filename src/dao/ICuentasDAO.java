package dao;

import java.util.List;

public interface ICuentasDAO<T> {
	boolean insert(T entity);
    boolean update(T entity);
    boolean delete(T entity);
    List<T> list();
    T findById(int id);
    T findByCbu(int cbu);
    List<T> findByIdCliente(int id);
}
