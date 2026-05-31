package model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private int orderId;
    private List<CartItem> items;
    private double totalPrice;
    private LocalDateTime orderDate;

    public Order(int orderId,
                 List<CartItem> items,
                 double totalPrice,
                 LocalDateTime orderDate) {

        this.orderId = orderId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }


    public int getOrderId() {
        return orderId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }




    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("===== RENDELÉS =====\n");
        sb.append("Rendelés ID: ").append(orderId).append("\n");
        sb.append("Dátum: ").append(orderDate).append("\n\n");

        for(CartItem item : items) {
            sb.append(item).append("\n");
        }

        sb.append("\nTeljes összeg: ")
                .append(totalPrice)
                .append(" Ft");

        return sb.toString();
    }
}