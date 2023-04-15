package org.example;

import java.util.List;

public class OrderSorter {

    public void sortOrders(List<Orders> orders){
        orders.sort(new OrderComparator());
    }
}
