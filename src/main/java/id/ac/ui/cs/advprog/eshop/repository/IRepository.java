package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;
import java.util.Optional;

public interface IRepository<T> {
    T create(T item);
    Iterator<T> findAll();
    T findById(String id);
    T update(String id, T updatedItem);
    T delete(String id);
}