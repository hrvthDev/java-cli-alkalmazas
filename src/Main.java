import model.CartItem;
import model.Order;
import model.Product;
import service.Cart;
import service.InventoryManager;
import service.InvoiceGenerator;
import util.InputHelper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final InventoryManager inventory =
            new InventoryManager();

    private static final Cart cart = new Cart();

    private static final List<Order> orders =
            new ArrayList<>();

    private static int nextOrderId = 1;

    public static void main(String[] args) {

        mainMenu();

        InputHelper.closeScanner();
    }



    private static void mainMenu() {

        boolean running = true;

        while (running) {
            System.out.println("\n===== FŐMENÜ =====");
            System.out.println("1. Admin mód");
            System.out.println("2. Vásárlói mód");
            System.out.println("0. Kilépés");

            int choice = InputHelper.readInt("Választás: ");

            switch (choice) {

                case 1 -> adminMenu();
                case 2 -> customerMenu();
                case 0 -> {
                    running = false;
                    System.out.println("Kilépés...");
                }
                default -> System.out.println("Hibás választás.");
            }
        }
    }



    private static void adminMenu() {

        boolean back = false;

        while (!back) {

            System.out.println("\n===== ADMIN MENÜ =====");
            System.out.println("1. Új termék hozzáadása");
            System.out.println("2. Termékek listázása");
            System.out.println("3. Statisztika");
            System.out.println("0. Vissza");

            int choice = InputHelper.readInt("Választás: ");

            switch (choice) {

                case 1 -> addProduct();
                case 2 -> inventory.listProducts();
                case 3 -> showStatistics();
                case 0 -> back = true;
                default -> System.out.println("Hibás választás.");
            }
        }
    }



    private static void customerMenu() {

        boolean back = false;

        while (!back) {

            System.out.println("\n===== VÁSÁRLÓI MENÜ =====");
            System.out.println("1. Termékek listázása");
            System.out.println("2. Kosárba helyezés");
            System.out.println("3. Kosár megtekintése");
            System.out.println("4. Fizetés");
            System.out.println("0. Vissza");

            int choice = InputHelper.readInt("Választás: ");

            switch (choice) {

                case 1 -> inventory.listProducts();
                case 2 -> addToCart();
                case 3 -> cart.showCart();
                case 4 -> checkout();
                case 0 -> back = true;
                default -> System.out.println("Hibás választás.");
            }
        }
    }



    private static void addProduct() {

        int id = InputHelper.readInt("ID: ");
        String name = InputHelper.readString("Név: ");
        double price = InputHelper.readDouble("Ár: ");
        int stock = InputHelper.readInt("Készlet: ");
        String category = InputHelper.readString("Kategória: ");

        inventory.addProduct(new Product(id, name, price, stock, category));

        System.out.println("Termék hozzáadva.");
    }


    private static void addToCart() {

        inventory.listProducts();

        int productId = InputHelper.readInt("Termék ID: ");
        int quantity = InputHelper.readInt("Mennyiség: ");

        Product selectedProduct = inventory.findById(productId);

        if (selectedProduct == null) {
            System.out.println("Nincs ilyen termék.");
            return;
        }

        if (quantity > selectedProduct.getStock()) {
            System.out.println("Nincs elegendő készlet.");
            return;
        }

        if(quantity <= 0) {
            System.out.println("A mennyiségnek pozitívnak kell lennie.");
            return;
        }

        cart.addToCart(selectedProduct, quantity);

        System.out.println("Termék kosárba helyezve.");
    }



    private static void checkout() {

        if (cart.isEmpty()) {
            System.out.println("A kosár üres.");
            return;
        }

        double total = cart.calculateTotal();

        for (CartItem item : cart.getItems()) {
            Product p = item.getProduct();
            p.setStock(p.getStock() - item.getQuantity());
        }

        Order order = new Order(
                nextOrderId++,
                new ArrayList<>(cart.getItems()),
                total,
                LocalDateTime.now()
        );

        orders.add(order);

        InvoiceGenerator.generateInvoice(order);

        System.out.println("Sikeres fizetés.");
        System.out.println("Fizetendő: " + total + " Ft");
        System.out.println("Köszönjük a vásárlást!");

        cart.clearCart();
        System.out.println("Kosár kiürítve.");
    }



    private static void showStatistics() {

        if(orders.isEmpty()) {
            System.out.println("Még nincs rendelés.");
            return;
        }
        double revenue = 0;

        for (Order order : orders) {
            revenue += order.getTotalPrice();
        }

        System.out.println("\n===== STATISZTIKA =====");
        System.out.println("Rendelések száma: " + orders.size());
        System.out.println("Teljes bevétel: " + revenue + " Ft");
    }
}