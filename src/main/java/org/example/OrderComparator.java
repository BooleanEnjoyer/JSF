package org.example;

import java.util.Comparator;

public class OrderComparator implements Comparator<Orders> {
    @Override
    public int compare(Orders o1, Orders o2) {
        return o1.getCompleteBy().minus(o1.getPickingTime()).compareTo(o2.getCompleteBy().minus(o2.getPickingTime()));
    }
}
