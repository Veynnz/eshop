package id.ac.ui.cs.advprog.eshop.repository;

public interface IRepository<T> {
    public T create(T item);
    public T update(String id, T updatedItem);
    public T delete(String id);
}