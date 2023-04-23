package org.example.reader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.example.entities.Orders;
import org.example.entities.Store;
import org.example.adapters.TypeTokenMapper;
import java.lang.reflect.Type;
import java.util.List;

public class JsonToObjectMapper{

    GsonTypeBuilder gsonTypeBuilder = new GsonTypeBuilder();
    TypeTokenMapper orderTokenMapper = new TypeTokenMapper();
    Class<Store> storeClass = Store.class;
    Class<Orders> ordersClass = Orders.class;

    protected Store mapJsonToStore(List<JsonReader> jsonReaders){
        Gson storeGson = gsonTypeBuilder
                .buildStoreGsonType(storeClass);
        return storeGson.fromJson(jsonReaders.get(0) , storeClass);
    }

    protected List<Orders> mapJsonToOrders(List<JsonReader> jsonReaders){
        Type ordersType = orderTokenMapper.assignOrderToken();
        Gson ordersGson = gsonTypeBuilder
                .buildOrderGsonType(ordersClass);
        return ordersGson.fromJson(jsonReaders.get(1) , ordersType);
    }
}
