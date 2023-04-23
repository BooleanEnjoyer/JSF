package org.example.algorithm;

import org.example.entities.Orders;
import org.example.entities.Store;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PickersToOrderEnroller {

    private boolean hasTimeToPickOrder(Orders order,
                                       LocalTime timeToPick){
        LocalTime orderStartTime = order.getCompleteBy()
                .minus(order.getPickingTime());
        Duration durationToPickNextOrder = Duration
                .between(timeToPick,orderStartTime);
        return !durationToPickNextOrder.isNegative();
    }

    private boolean canBePickedAfterPreviousEnd(
            Orders order,
            Orders currentOrder
        ){
        LocalTime timeToPickOrder = order.getCompleteBy()
                .minus(order.getPickingTime());
        LocalTime currentCompleteTime = currentOrder.getCompleteBy();
        Duration timeLookup = Duration
                .between(timeToPickOrder, currentCompleteTime);
        return (!timeLookup.isNegative());
    }

    private boolean isTaken(List<String> enrolledOrders,
                            Orders order){
        return enrolledOrders.stream().anyMatch(
                matchedOrder -> matchedOrder
                        .contains(order.getOrderId() + " ")
        );
    }

    private boolean isGreater(
            BigDecimal currentOrderValue,
            BigDecimal higherOrderValue){
        return (currentOrderValue.compareTo(higherOrderValue) > 0);
    }

    private boolean canPick(List<String> enrolledOrders, Orders order,
                            Orders currentOrder,
                            BigDecimal higherOrderValue,
                            BigDecimal currentOrderValue,
                            LocalTime timeToPick){
        boolean greater = isGreater(currentOrderValue,
                higherOrderValue);
        boolean timeToPickOrder = hasTimeToPickOrder(order,
                timeToPick);
        boolean canBePickedAfter = canBePickedAfterPreviousEnd(order,
                currentOrder);
        boolean taken = isTaken( enrolledOrders ,order);
        return (greater && !taken && timeToPickOrder
                && canBePickedAfter);
    }

    private Orders findHigherValueOrder(
            List<Orders> orders,
            Orders currentOrder,
            LocalTime timeToPick,
            List<String> enrolledOrders){
        BigDecimal higherValue;
        BigDecimal currentValue = currentOrder.getOrderValue();
        for (Orders order : orders){
            higherValue = order.getOrderValue();
            if (isGreater(currentValue, higherValue)
            && hasTimeToPickOrder(order , timeToPick)
            && canBePickedAfterPreviousEnd(order , currentOrder)
            && !isTaken( enrolledOrders ,order))
            {
                currentOrder = order;
                return currentOrder;
            }
        }
        return currentOrder;
    }

    private LocalTime shiftTimeToNextPick(LocalTime timeToPick,
                                         Orders order){
        return timeToPick.plus(order.getPickingTime());
    }

    private LocalTime resetTimeToPickingStart(Store store){
        return store.getPickingStartTime();
    }

    private void sortOrders(List<Orders> orders){
        orders.sort(new OrderComparator());
    }

    public List<String> enrollPickerToOrders(Store store,
                                             List<Orders> orders){
        sortOrders(orders);
        LocalTime timeToPick=store.getPickingStartTime();
        List<String> enrolledOrders = new ArrayList<>();
        List<String> pickerList = store.getPickers();
        for(String picker : pickerList){
            for(Orders order : orders){
                if (hasTimeToPickOrder(order , timeToPick)
                && !isTaken(enrolledOrders, order)) {

                    order = findHigherValueOrder(orders, order,
                            timeToPick, enrolledOrders);
                    enrolledOrders.add(new ScheduledOrder(picker,
                            order, timeToPick).toString());
                    timeToPick = shiftTimeToNextPick(timeToPick,
                            order);
                }
            }
            timeToPick = resetTimeToPickingStart(store);
        }
        return enrolledOrders;
    }
}
