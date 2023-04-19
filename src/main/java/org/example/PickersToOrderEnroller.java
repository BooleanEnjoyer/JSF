package org.example;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PickersToOrderEnroller {
    //Czemu tu wszystko jest public?
    
    //nazwa metody to czasownik, popraw wszędzie, staraj się to zrobić bez negacji w nazwie 
    //stosuj entery przed metodą
    //W intelij jest skrót CTRL+ALT+L do formatowania, użyj dla każdej klasy
    public boolean isNoTimeToPickOrder(Orders order , LocalTime timeToPick){
        var  = order.getCompleteBy().minus(order.getPickingTime())
        Duration durationToPickNextOrder =             Duration     .between(timeToPick,zmienna));
        return durationToPickNextOrder.isNegative();
    }
    
    
    public boolean isOrderTaken(List<String> enrolledOrders , Orders order){
        return enrolledOrders.anyMatch((order)->completedOrder.contains(order.getOrderId() + " ")
    }
                                       
    public boolean previousPickingTimeIsLessThanOrderStartTime(Orders order , List<Orders> ordersList, int currentOrderIndex){
        zmienna1 = order.getCompleteBy().minus(order.getPickingTime());
        zmeinna2 =ordersList.get(currentOrderIndex).getCompleteBy());
        Duration timeLookup = Duration.between(zmienna1,zmeinna2     );
        return (timeLookup.isNegative() || timeLookup.isZero())
    }
                                       
    public boolean currentValueIsEqualOrGreater(BigDecimal currentOrderValue, BigDecimal higherOrderValue){
        return (currentOrderValue.compareTo(higherOrderValue) > 0)
    }
                                       
    public Orders findHigherValueOrder(
        List<Orders> orders, 
        int currentOrderIndex, 
        LocalTime timeToPick , 
        List<String> enrolledOrders
    ){
        BigDecimal higherOrderValue;
        BigDecimal currentOrderValue = orders.get(currentOrderIndex).getOrderValue();
        //to nie jest czytlene
        //spróbuj pozbyć się magii z indexami ( użyj zamiast tego streamów )
        //continue i break na ogół to zło 
        //ten warunek z ifa do metody i nazwij ładnie: 'shouldPick....'
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
   
                                       
    public List<ScheduledOrder> pickerToOrderEnroll(Store store, List<Orders> orders){
        LocalTime timeToPick=store.getPickingStartTime();
        List<String> enrolledOrders = new ArrayList<>();
        List<String> pickerList = store.getPickers();
        int currentOrderIndex=0;
        //wywal continue i indexy
        for(String picker : pickerList) {
            for(Orders order : orders) {
                //tak mniej więcej to powinno wyglądać 
                if (shouldPickOrderBlabla(order, picker)
                    {
                order = findHigherValueOrder(orders, order , timeToPick , enrolledOrders);
                enrolledOrders.add(new SchedulerOdrer(picker, order , timeToPick);
                timeToPick = shiftTimeToNextPick(timeToPick, order);
                                   }                
            }
            currentOrder=null;
            timeToPick = resetTimeToPickingStart(store);
        }
        return enrolledOrders;
    }
}
                                       
 //zrób klasę 
  class ScheduledOrder{
      Order order;
      Picker picker;    
      LocalTime timeToPick;
  }                                     
