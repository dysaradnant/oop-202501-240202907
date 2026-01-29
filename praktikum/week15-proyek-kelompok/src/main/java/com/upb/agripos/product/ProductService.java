package com.upb.agripos.product;

import java.util.List;

public class ProductService {
    private ProductRepository repo;

    public ProductService(ProductRepository repo) { this.repo = repo; }

    public void add(Product p) { repo.addProduct(p); }
    public void update(Product p) { repo.updateProduct(p); }
    public void delete(String kode) { repo.deleteProduct(kode); }
    public void reduceStock(String kode, int qty) { repo.reduceStock(kode, qty); }
    public List<Product> list() { return repo.listProducts(); }
    public Product get(String kode) { return repo.getProductByCode(kode); }
}