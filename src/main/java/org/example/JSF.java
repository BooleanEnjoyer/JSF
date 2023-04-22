package org.example;

import com.google.gson.stream.JsonReader;
import org.example.algorithm.PickersToOrderEnroller;
import org.example.entities.Orders;
import org.example.entities.Store;
import org.example.reader.ArgumentsToJsonMapper;
import org.example.reader.ArgumentsValidator;
import org.example.reader.JsonToObjectMapper;
import java.util.ArrayList;
import java.util.List;

public class JSF {

    ArgumentsValidator argumentsValidator = new ArgumentsValidator();
    ArgumentsToJsonMapper ArgumentsToJsonMapper = new ArgumentsToJsonMapper();
    List<JsonReader> jsonReaders = new ArrayList<>();
    JsonToObjectMapper jsonToObjectMapper = new JsonToObjectMapper();
    PickersToOrderEnroller pickersToOrderEnroller = new PickersToOrderEnroller();
    Printer printer = new Printer();

    public void runJSF(String[] args){
        try {
            argumentsValidator.validateArguments(args);
        }
        catch (Exception e){
            System.exit(0);
        }
        jsonReaders = ArgumentsToJsonMapper.mapArgumentsToJson(args);
        Store store = jsonToObjectMapper.mapJsonToStore(jsonReaders);
        List<Orders> orders= jsonToObjectMapper.mapJsonToOrders(jsonReaders);
        List<String> enrolledOrders = pickersToOrderEnroller.enrollPickerToOrders(store,orders);
        printer.printEnrolledOrders(enrolledOrders);
    }
}
