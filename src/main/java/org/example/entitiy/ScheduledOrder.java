package org.example.entitiy;

import java.time.LocalTime;

public class ScheduledOrder {

    Order order;
    String picker;
    LocalTime timeToPick;

    public ScheduledOrder(String picker, Order order,
                          LocalTime timeToPick) {
        this.picker = picker;
        this.order = order;
        this.timeToPick = timeToPick;
    }

    public String toString() {
        return picker + " " + order.getId() + " " + timeToPick;
    }

}
