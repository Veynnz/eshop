package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.repository.AbstractFullRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract service implementation for all items
 * @param <T> The type of item this service manages
 * @param <R> The type of repository this service uses
 */
public abstract class AbstractServiceImpl<T, R extends AbstractFullRepository<T>> implements itemServicePost<T>, itemServiceGet<T>  {

    protected final R repository;

    @Autowired
    public AbstractServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public T create(T item) {
        return repository.create(item);
    }

    @Override
    public List<T> findAll() {

        return repository.findAll();
    }

    @Override
    public T findById(String id) {
        return repository.findById(id);
    }

    @Override
    public T edit(String id, T item) {
        return repository.update(id, item);
    }

    @Override
    public T delete(String id) {
        return repository.delete(id);
    }
}