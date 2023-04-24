package org.example;

import org.example.service.PickersToOrderEnroller;
import org.example.entitiy.Order;
import org.example.entitiy.Store;
import org.example.reader.ArgumentsMapper;
import org.example.service.Printer;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ArgumentsMapper argumentsMapper = new ArgumentsMapper();
        PickersToOrderEnroller pickersToOrderEnroller = new PickersToOrderEnroller();
        Printer printer = new Printer();
        List<Order> orders = argumentsMapper.mapOrders(args);
        Store store = argumentsMapper.mapStore(args);
        List<String> enrolledOrders = pickersToOrderEnroller.enrollPickerToOrders(store, orders);
        printer.printEnrolledOrders(enrolledOrders);

    }
}