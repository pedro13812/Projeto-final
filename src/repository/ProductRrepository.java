package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Product;

/*
 * Repository of product table.
 * 
 */

public class ProductRrepository implements IRepository<Long, Product> {
    /*
     * fake database
     */
    private static final List<Product> FAKE_DATABASE = new ArrayList<>();
    /* fake las_insert_id() */
    private static Long currentId = 1l;

    @Override
    public Product save(Product entity) {
        var isUpdaste = entity.getId() != null && entity.getId() > 0;

        if (isUpdaste) {
            throw new RuntimeException(" implement it");
        } else {
            var newEntity = new Product();
            newEntity.setName(entity.getName());
            newEntity.setStock(entity.getStock());
            newEntity.setValue(entity.getValue());
            newEntity.setId(currentId);
            currentId++;

            FAKE_DATABASE.add(newEntity);
            return newEntity;
        }
    }
    


    @Override
    public Optional<Product> finById(Long id) {
        var product = FAKE_DATABASE.stream().filter(p -> p.getId() == id). findFirst();
        return product;
    }

    @Override
    public List<Product> findAll() {
        var clone = new ArrayList<Product>();
        FAKE_DATABASE.stream().forEach(clone::add);
        return clone;
    }

}
