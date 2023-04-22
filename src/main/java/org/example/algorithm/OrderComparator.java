package org.example.algorithm;

import org.example.entities.Orders;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;

public class OrderComparator implements Comparator<Orders> {

    @Override
    public int compare(Orders orderOne, Orders orderTwo) {

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
