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
            System.out.println("===== TERMÉKEK =====");
            System.out.println(p);
        }
        if(products.isEmpty()) {
            System.out.println("Nincs elérhető termék.");
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