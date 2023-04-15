package org.example;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonToObjectMapper{
    GsonTypeBuilder gsonTypeBuilder = new GsonTypeBuilder();
    TypeTokenMapper orderTokenMapper = new TypeTokenMapper();
    Class<Store> storeClass = Store.class;
    Class<Orders> ordersClass = Orders.class;
    public Store storeMapper(List<JsonReader> jsonReaders){
        Gson storeGson = gsonTypeBuilder.buildStoreGsonType(storeClass);
        return storeGson.fromJson(jsonReaders.get(0) , storeClass);
    }
    public List<Orders> ordersMapper(List<JsonReader> jsonReaders){
        Type ordersType = orderTokenMapper.assignOrderToken();
        Gson ordersGson = gsonTypeBuilder.buildOrderGsonType(ordersClass);
        return ordersGson.fromJson(jsonReaders.get(1) , ordersType);
    }
}
