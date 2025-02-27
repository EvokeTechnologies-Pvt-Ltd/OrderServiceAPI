package org.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String orderNumber;
    private String date;
    private String channel;
    private String customerId;
    private ShipTo shipTo;
    private List<LineItem> lineItems;
    private double totalWeight;
    private double subtotal;
    private double total;

    // Getters and Setters

    public static class ShipTo {
        private String name;
        private String street;
        private String city;
        private String state;
        private String zip;

        // Getters and Setters
    }

    public static class LineItem {
        // Define fields for line items

        // Getters and Setters
    }

    //create a method to email validation
    public boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}