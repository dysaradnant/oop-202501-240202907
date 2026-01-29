package com.upb.agripos.product;

import java.util.List;

public interface ProductRepository {
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(String kode);
    void reduceStock(String kode, int quantity);
    List<Product> listProducts();
    Product getProductByCode(String kode);
}