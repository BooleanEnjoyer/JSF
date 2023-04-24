package org.example.service;

import java.util.List;

public class Printer {

    public void printFileNotFound() {
        System.out.println("File not found");
    }

    public void printWrongArgumentAmount() {
        System.out.println("You passed wrong number of arguments");
    }

    public void printEnrolledOrders(List<String> enrolledOrders) {
        for (String completedOrder : enrolledOrders) {
            System.out.println(completedOrder);
        }
        printNumberOfEnrolledOrders(enrolledOrders);
    }

    private void printNumberOfEnrolledOrders(
            List<String> enrolledOrders) {
        int enrolledOrdersSize = enrolledOrders.size();
        System.out.println(
                "Number of completed orders " + enrolledOrdersSize
        );
    }
}
