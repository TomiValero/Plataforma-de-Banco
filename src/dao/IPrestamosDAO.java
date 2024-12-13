package dao;

import java.util.List;

public interface IPrestamosDAO<T> {
	boolean insert(T entity);
    boolean update(T entity);
    boolean delete(T entity);
    List<T> list();
    T findById(int id);
    List<T> findByEstado(String estado);
    List<T> findByIdCliente(int IdCliente);
}
