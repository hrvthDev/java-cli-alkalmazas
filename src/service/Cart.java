package service;

import model.CartItem;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<CartItem> items = new ArrayList<>();


    public void addToCart(Product product, int quantity) {

        for(CartItem item : items) {

            if(item.getProduct().getId() == product.getId()) {

                item.setQuantity(item.getQuantity() + quantity);

                return;
            }
        }

        items.add(new CartItem(product, quantity));
    }



    public void showCart() {

        if(items.isEmpty()) {
            System.out.println("A kosarad üres!");
            return;
        }

        System.out.println("\n===== KOSÁR =====");

        for(CartItem item : items) {
            System.out.println(item);
        }

        System.out.println("-----------------");
        System.out.println("Végösszeg: " + calculateTotal() + " Ft");
        System.out.println("Tételek száma: " + items.size());
    }


    public double calculateTotal() {

        double total = 0;

        for(CartItem item : items) {
            total += item.getTotalPrice();
        }

        return total;
    }

    public void clearCart() {
        items.clear();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}