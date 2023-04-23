package org.example;

import org.example.algorithm.PickersToOrderEnroller;
import org.example.entities.Orders;
import org.example.entities.Store;
import org.example.reader.ArgumentsMapper;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ArgumentsMapper argumentsMapper = new ArgumentsMapper();
        PickersToOrderEnroller pickersToOrderEnroller = new PickersToOrderEnroller();
        Printer printer = new Printer();
        List<Orders> orders = argumentsMapper.mapOrders(args);
        Store store = argumentsMapper.mapStore(args);
        List<String> enrolledOrders = pickersToOrderEnroller.enrollPickerToOrders(store,orders);
        printer.printEnrolledOrders(enrolledOrders);

    }
}