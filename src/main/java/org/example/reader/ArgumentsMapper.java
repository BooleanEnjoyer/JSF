package org.example.reader;

import com.google.gson.stream.JsonReader;
import org.example.entitiy.Order;
import org.example.entitiy.Store;

import java.util.List;

public class ArgumentsMapper {

    ArgumentsToJsonMapper argumentsToJsonMapper = new ArgumentsToJsonMapper();
    JsonToObjectMapper jsonToObjectMapper = new JsonToObjectMapper();

    public List<Order> mapOrders(String[] args) {
        List<JsonReader> jsonReaders = argumentsToJsonMapper.mapArgumentsToJson(args);
        return jsonToObjectMapper.mapJsonToOrders(jsonReaders);
    }

    public Store mapStore(String[] args) {
        List<JsonReader> jsonReaders = argumentsToJsonMapper.mapArgumentsToJson(args);
        return jsonToObjectMapper.mapJsonToStore(jsonReaders);
    }
}
