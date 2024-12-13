package dao;

import java.util.List;

public class GenericDAO<T> implements IGenericDAO<T> {
	@Override
    public boolean insert(T entity) {
        return false;
    }

    @Override
    public boolean update(T entity) {
        return false;
    }

    @Override
    public boolean delete(T entity) {
        return false;
    }

    @Override
    public List<T> list() {
        return null;
    }

    @Override
    public T findById(int id) {
        return null;
    }

}
