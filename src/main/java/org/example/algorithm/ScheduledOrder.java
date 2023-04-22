package org.example.algorithm;

import org.example.entities.Orders;
import java.time.LocalTime;

public class ScheduledOrder {

    Orders order;
    String picker;
    LocalTime timeToPick;

    public ScheduledOrder(String picker, Orders order,
                          LocalTime timeToPick){
        this.picker=picker;
        this.order=order;
        this.timeToPick=timeToPick;
    }

    public String toString(){
        return picker + " " +  order.getOrderId() + " " + timeToPick;
    }
}
