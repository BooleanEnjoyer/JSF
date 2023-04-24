package org.example.service;

import org.example.comparator.OrderComparator;
import org.example.entitiy.Order;
import org.example.entitiy.ScheduledOrder;
import org.example.entitiy.Store;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PickersToOrderEnroller {

    public List<String> enrollPickerToOrders(Store store,
                                             List<Order> orders) {
        sortOrders(orders);
        LocalTime timeToPick = store.getPickingStartTime();
        List<String> enrolledOrders = new ArrayList<>();
        List<String> pickerList = store.getPickers();
        Order orderToRemove = null;
        for (String picker : pickerList) {
            for (Order order : orders) {

                if (hasTimeToPickOrder(order, timeToPick)
                        && !isTaken(enrolledOrders, order)) {

                    order = findHigherValueOrder(orders, order,
                            timeToPick, enrolledOrders);
                    enrolledOrders.add(new ScheduledOrder(picker,
                            order, timeToPick).toString());
                    timeToPick = shiftTimeToNextPick(timeToPick,
                            order);
                    orderToRemove = order;
                }
            }
            orders.remove(orderToRemove);
            timeToPick = resetTimeToPickingStart(store);
        }
        return enrolledOrders;
    }

    private boolean hasTimeToPickOrder(Order order,
                                       LocalTime timeToPick) {
        LocalTime orderStartTime = order.getCompleteBy()
                .minus(order.getPickingTime());
        Duration durationToPickNextOrder = Duration
                .between(timeToPick, orderStartTime);
        return !durationToPickNextOrder.isNegative();
    }

    private boolean canBePickedAfterPreviousEnd(
            Order order,
            Order currentOrder
    ) {
        LocalTime timeToPickOrder = order.getCompleteBy().
                minus(order.getPickingTime());
        LocalTime currentCompleteTime = currentOrder.getCompleteBy();
        Duration timeLookup = Duration.
                between(timeToPickOrder, currentCompleteTime);
        return (!timeLookup.isNegative());
    }

    private boolean isTaken(List<String> enrolledOrders,
                            Order order) {
        return enrolledOrders.
                stream().
                anyMatch(matchedOrder -> matchedOrder.
                        contains(order.getId() + " ")
                );
    }

    private boolean isGreater(
            BigDecimal currentOrderValue,
            BigDecimal higherOrderValue) {
        return (currentOrderValue.compareTo(higherOrderValue) > 0);
    }

    private boolean canPick(List<String> enrolledOrders, Order order,
                            Order currentOrder,
                            BigDecimal higherOrderValue,
                            BigDecimal currentOrderValue,
                            LocalTime timeToPick) {
        boolean greater = isGreater(currentOrderValue,
                higherOrderValue);
        boolean timeToPickOrder = hasTimeToPickOrder(order,
                timeToPick);
        boolean canBePickedAfter = canBePickedAfterPreviousEnd(order,
                currentOrder);
        boolean taken = isTaken(enrolledOrders, order);
        return (greater && !taken && timeToPickOrder
                && canBePickedAfter);
    }

    private Order findHigherValueOrder(
            List<Order> orders,
            Order currentOrder,
            LocalTime timeToPick,
            List<String> enrolledOrders) {
        BigDecimal higherValue;
        BigDecimal currentValue = currentOrder.getValue();
        for (Order order : orders) {
            higherValue = order.getValue();
            if (isGreater(currentValue, higherValue)
                    && hasTimeToPickOrder(order, timeToPick)
                    && canBePickedAfterPreviousEnd(order, currentOrder)
                    && !isTaken(enrolledOrders, order)) {
                currentOrder = order;
                return currentOrder;
            }
        }
        return currentOrder;
    }

    private LocalTime shiftTimeToNextPick(LocalTime timeToPick,
                                          Order order) {
        return timeToPick.plus(order.getPickingTime());
    }

    private LocalTime resetTimeToPickingStart(Store store) {
        return store.getPickingStartTime();
    }

    private void sortOrders(List<Order> orders) {
        orders.sort(new OrderComparator());
    }

}
