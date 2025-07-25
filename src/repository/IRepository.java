package repository;

import java.util.List;
import java.util.Optional;

/*
 * The interface of repository classes to manger database registers.
 */

public interface IRepository <I, T> {

    /*
     * 
     * @param enity - enity data to persist on database.
     * @return new enity save/updated on database.
     */
    T save( T entity);

    /*
     * 
     * Tryng find register by id on database.
     * 
     * @param id
     * @return when fouded enity retunr it otherwise empty
     */

    Optional<T> finById ( I id);
  /*
   * 
   * List all entities on database.
   * 
   * @return all entities
   */
    List<T> findAll();
}
