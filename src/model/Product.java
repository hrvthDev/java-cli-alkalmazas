package model;

public class Product {

    private int id;
    private String name;
    private double price;
    private int stock;
    private String category;

    public Product(int id, String name, double price, int stock, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Név: " + name +
                " | Ár: " + price +
                " Ft | Készlet: " + stock +
                " | Kategória: " + category;
    }
}