package org.example;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PickersToOrderEnroller {
    public boolean thereIsNoTimeToPickOrder(Orders order , LocalTime timeToPick){
        Duration durationToPickNextOrder = Duration.between(timeToPick ,
                order.getCompleteBy().minus(order.getPickingTime()));
        return durationToPickNextOrder.isNegative();
    }
    public boolean orderIsTaken(List<String> enrolledOrders , Orders order){
        for (String completedOrder : enrolledOrders) {
            if (completedOrder.contains(order.getOrderId() + " ")) {
                return true;
            }
        }
        return false;
    }
    public boolean previousPickingTimeIsLessThanOrderStartTime(Orders order , List<Orders> ordersList, int currentOrderIndex){
        Duration timeLookup;
        timeLookup = Duration.between(order.getCompleteBy().minus(order.getPickingTime()),
                ordersList.get(currentOrderIndex).getCompleteBy());
        if(timeLookup.isNegative() || timeLookup.isZero()){
            return true;
        }
        return false;
    }
    public boolean currentValueIsEqualOrGreater(BigDecimal currentOrderValue, BigDecimal higherOrderValue){
        if(currentOrderValue.compareTo(higherOrderValue) > 0){
            return true;
        }
        return false;
    }
    public Orders findHigherValueOrder(List<Orders> orders , int currentOrderIndex
            , LocalTime timeToPick , List<String> enrolledOrders){
        BigDecimal higherOrderValue;
        BigDecimal currentOrderValue = orders.get(currentOrderIndex).getOrderValue();
        for (int higherValueIndex = currentOrderIndex + 1; higherValueIndex < orders.size(); higherValueIndex++) {
            higherOrderValue = orders.get(higherValueIndex).getOrderValue();
            if (currentValueIsEqualOrGreater(currentOrderValue , higherOrderValue)
                    || thereIsNoTimeToPickOrder(orders.get(higherValueIndex) , timeToPick)
                    || previousPickingTimeIsLessThanOrderStartTime(orders.get(higherValueIndex) , orders , currentOrderIndex)
                    || orderIsTaken( enrolledOrders ,orders.get(higherValueIndex))) {
                continue;
            }
            currentOrderIndex = higherValueIndex;
            break;
        }
        return orders.get(currentOrderIndex);
    }
    public LocalTime shiftTimeToNextPick(LocalTime timeToPick, Orders order){
        return timeToPick.plus(order.getPickingTime());
    }
    public LocalTime resetTimeToPickingStart(Store store){
        return store.getPickingStartTime();
    }
    public List<String> pickerToOrderEnroll(Store store, List<Orders> orders){
        LocalTime timeToPick=store.getPickingStartTime();
        List<String> enrolledOrders = new ArrayList<>();
        List<String> pickerList = store.getPickers();
        int currentOrderIndex=0;
        for(String picker : pickerList) {
            for(Orders order : orders) {
                if (thereIsNoTimeToPickOrder(order , timeToPick)
                        || orderIsTaken(enrolledOrders, order)){
                    currentOrderIndex++;
                    continue;
                }
                order = findHigherValueOrder(orders, currentOrderIndex , timeToPick , enrolledOrders);
                enrolledOrders.add(picker +" "+ order.getOrderId() +" "+ timeToPick);
                timeToPick = shiftTimeToNextPick(timeToPick, order);
                currentOrderIndex++;
            }
            currentOrderIndex=0;
            timeToPick = resetTimeToPickingStart(store);
        }
        return enrolledOrders;
    }
}
