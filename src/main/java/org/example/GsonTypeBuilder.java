package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class GsonTypeBuilder {

    public Gson buildOrderGsonType( Class<Orders> ordersClass ){
        return new GsonBuilder()
            .registerTypeAdapter(ordersClass, new OrdersAdapter())
            .setPrettyPrinting()
            .create();
    }

    public Gson buildStoreGsonType( Class<Store> storeClass){
        return new GsonBuilder()
            .registerTypeAdapter(storeClass, new StoreAdapter())
            .setPrettyPrinting()
            .create();
    }
}
