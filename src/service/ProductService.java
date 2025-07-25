package service;

import java.util.List;
import java.util.Optional;

import model.Product;
import repository.ProductRrepository;
/*
 * 
 * tHE BRIDGE BETWEEN {@Link product}
 */
public class ProductService {

    private final ProductRrepository productRepository;

    /*
     * 
     * To persist a new register on database.
     * 
     * @param product
     * @return new product on database with id
     */

    public ProductService(ProductRrepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product insert(Product product) {
        product.setId(null);
        return productRepository.save(product);
    }

    /*
     * to persist update data from register on database.
     * 
     * <p><b>Attention:
     * id is requerired</b></<p>
     * 
     * 
     * @ param product
     * @return new product instance od data on database.
     */

    public Product update(Product product) {

        /*
         *  to get all register on database.
         * 
         * @return
         * 
         */
        if (product.getId() == null) {
            throw new IllegalArgumentException(" Entity id is required");
        }

        return productRepository.save(product);
    }

    public List <Product> getAll() {


        /*
         * Tryring find product by id on database.
         * 
         * @param id
         * @return 
         * 
         * 
         */
        return productRepository.findAll();
    }
    public Optional<Product> findById( long id){
        return productRepository.finById(id);
    }
}
