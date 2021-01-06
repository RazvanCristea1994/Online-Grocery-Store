package store.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<ID extends Serializable, T> {

    void save(T value);

    void update(T value);

    void delete(ID id);

    Optional<T> getById(ID id);

    List<T> getAll();
}
