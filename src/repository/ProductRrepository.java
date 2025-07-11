package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Product;

public class ProductRrepository implements IRepository< Long, Product>{
    private static final List<Product> FAKE_DATABASE = new ArrayList<>();
    private static Long currentId = 1l;

    @Override
    public Product save(Product entity) {
        var isUpdaste = entity.getId() != null && entity.getId() > 0;

        if (isUpdaste){
          throw new RuntimeException( " implement it");
        } else{
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'finById'");
    }

    
    }
