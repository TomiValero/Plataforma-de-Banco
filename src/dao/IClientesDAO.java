package dao;

import java.util.List;

public interface IClientesDAO<T> {
	boolean insert(T entity);
    boolean update(T entity);
    boolean delete(T entity);
    List<T> list();
    T findById(int id);
    T findByDni(String dni);
    T findByEmail(String email);
    T findByUser(String user);
    T login(String usuario, String contra);
}
