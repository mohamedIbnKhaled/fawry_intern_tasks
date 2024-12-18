package org.example;

import product.Product;
import product.ProductManager;

public class Main {
    public static void main(String[] args) {
        Product product = new Product("mohaemd",23);
        ProductManager productManager = new ProductManager();
        productManager.addProduct(product);
        System.out.println(productManager.searchProduct("mohaemd"));
    }
}