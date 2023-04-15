package org.example;

import com.google.gson.stream.JsonReader;
import java.util.ArrayList;
import java.util.List;

public class JSF {

    ArgumentsValidator argumentsValidator = new ArgumentsValidator();
    ReaderFromJson ReaderFromJson = new ReaderFromJson();
    List<JsonReader> jsonReaders = new ArrayList<>();
    JsonToObjectMapper jsonToObjectMapper = new JsonToObjectMapper();
    OrderSorter orderSorter = new OrderSorter();
    PickersToOrderEnroller pickersToOrderEnroller = new PickersToOrderEnroller();
    Printer printer = new Printer();
    public void runJSF(String[] args){
        argumentsValidator.validateArguments(args);
        jsonReaders = ReaderFromJson.readArguments(args);
        Store store = jsonToObjectMapper.storeMapper(jsonReaders);
        List<Orders> orders= jsonToObjectMapper.ordersMapper(jsonReaders);
        orderSorter.sortOrders(orders);
        List<String> enrolledOrders = pickersToOrderEnroller.pickerToOrderEnroll(store,orders);
        printer.printEnrolledOrders(enrolledOrders);
    }
}
