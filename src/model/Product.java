package model;

/*
 * 
 *  Enty from database
 */

public class Product {
    private Long id;
    private String name;
    private Integer value;
    private Integer stock;
    public Product() {
    }
    public Product(Long id, String name, Integer value, Integer stock) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.stock = stock;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", value=" + value + ", stock=" + stock + "]";
    }


    
}
