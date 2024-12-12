package com.demo.demo.service.product;

import com.demo.demo.model.Product;
import com.demo.demo.repository.product.IProductRepo;
import com.demo.demo.repository.product.ProductRepo;

import java.util.List;
import java.util.Optional;

public class ProductService implements IProductService {
    private final IProductRepo iProductRepo = new ProductRepo();

    @Override
    public void addProduct(Product product) {
        iProductRepo.addProduct(product);
    }

    @Override
    public List<Product> findAllProducts() {
        return iProductRepo.findAllProducts();
    }
    @Override
    public Optional<Product> findProductById(int productID) {
        return iProductRepo.findProductById(productID);
    }

    @Override
    public Optional<Product> findProductByName(String productName) {
        return iProductRepo.findProductByName(productName);
    }

    @Override
    public List<Product> searchProduct(String searchKeyword) {
        return iProductRepo.searchProduct(searchKeyword);
    }

    @Override
    public void updateProduct(Product product) {
        iProductRepo.updateProduct(product);
    }

    @Override
    public boolean deleteProductById(int productID) {
        return iProductRepo.deleteProductById(productID);
    }

    @Override
    public boolean deleteProductByName(String productName) {
        return iProductRepo.deleteProductByName(productName);
    }
}
