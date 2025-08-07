package repository;

import java.util.List;
import java.util.Optional;

/**
 * Generic repository interface for managing database entities.
 *
 * @param <I> the type of the entity identifier
 * @param <T> the type of the entity
 */
public interface IRepository<I, T> {

    /**
     * Saves or updates the given entity in the database.
     *
     * @param entity the entity to be persisted
     * @return the saved or updated entity
     */
    T save(T entity);

    /**
     * Retrieves an entity by its identifier.
     *
     * @param id the identifier of the entity
     * @return an {@code Optional} containing the found entity, or empty if not found
     */
    Optional<T> findById(I id);

    /**
     * Retrieves all entities from the database.
     *
     * @return a list of all entities
     */
    List<T> findAll();
}
