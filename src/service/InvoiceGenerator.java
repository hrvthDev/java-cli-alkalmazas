package service;

import model.CartItem;
import model.Order;

import java.io.FileWriter;
import java.io.IOException;

public class InvoiceGenerator {

    public static void generateInvoice(Order order) {

        String fileName = "invoice_" + order.getOrderId() + ".txt";

        try(FileWriter writer = new FileWriter(fileName)) {

            writer.write("===== SZÁMLA =====\n");
            writer.write("Rendelés ID: " + order.getOrderId() + "\n");
            writer.write("Dátum: " + order.getOrderDate() + "\n\n");

            writer.write("Termékek:\n");

            for(CartItem item : order.getItems()) {

                writer.write(
                        item.getProduct().getName()
                                + " | Ár:"
                                + item.getProduct().getPrice()
                                + " Ft"
                                + " | Mennyiség:"
                                + item.getQuantity()
                                + " | Összesen:"
                                + item.getTotalPrice()
                                + " Ft\n"
                );
            }


            writer.write("\n----------------------\n");
            writer.write("Végösszeg: " +
                    order.getTotalPrice() +
                    " Ft\n");


            writer.write("**********************************************\n");

            System.out.println("Számla sikeresen generálva: " + fileName);


        } catch(IOException e) {

            System.out.println("Hiba történt a számla generálása során");
            e.printStackTrace();
        }
    }
}