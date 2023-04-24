package org.example.comparator;

import org.example.entitiy.Order;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;

public class OrderComparator implements Comparator<Order> {

    @Override
    public int compare(Order orderOne, Order orderTwo) {

        LocalTime firstOrderCompleteTime = orderOne.getCompleteBy();
        Duration firstOrderPickingTime = orderOne.getPickingTime();
        LocalTime secondOrderCompleteTime = orderTwo.getCompleteBy();
        Duration secondOrderPickingTime = orderTwo.getPickingTime();

        return firstOrderCompleteTime
                .minus(firstOrderPickingTime)
                .compareTo(
                        secondOrderCompleteTime
                                .minus(secondOrderPickingTime)
                );
    }
}
