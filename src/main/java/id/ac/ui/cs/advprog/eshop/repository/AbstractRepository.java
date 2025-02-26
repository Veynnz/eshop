package id.ac.ui.cs.advprog.eshop.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class AbstractRepository<T> implements IRepository<T> {
    protected List<T> data = new ArrayList<>();

    @Override
    public T create(T item) {
        if (getId(item) == null) {
            setId(item, UUID.randomUUID().toString());
        }
        data.add(item);
        return item;
    }

    @Override
    public Iterator<T> findAll() {
        return data.iterator();
    }

    @Override
    public T findById(String id) {
        for (T item : data) {
            if (getId(item).equals(id)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public T update(String id, T updatedItem) {
        for (int i = 0; i < data.size(); i++) {
            if (getId(data.get(i)).equals(id)) {
                updateItem(data.get(i), updatedItem);
                return data.get(i);
            }
        }
        return null;
    }

    @Override
    public T delete(String id) {
        Iterator<T> iterator = data.iterator();
        while (iterator.hasNext()) {
            T item = iterator.next();
            if (getId(item).equals(id)) {
                iterator.remove();
                return item;
            }
        }
        return null;
    }

    protected abstract String getId(T item);
    protected abstract void setId(T item, String id);
    protected abstract void updateItem(T existingItem, T newItem);
}
