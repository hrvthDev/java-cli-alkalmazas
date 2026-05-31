package service;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }



    public void listProducts() {
        for (Product p : products) {
            System.out.println(p);
        }
    }



    public Product findById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}