package id.ac.ui.cs.advprog.eshop.service;

public interface itemServicePost<T> {
    T create(T item);
    T update(String id, T item);
    T delete(String id);
}
