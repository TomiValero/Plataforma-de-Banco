package dao;

import java.util.List;

public interface IGenericDAO<T> {
	boolean insert(T entity);
    boolean update(T entity);
    boolean delete(T entity);
    List<T> list();
    T findById(int id);
}
