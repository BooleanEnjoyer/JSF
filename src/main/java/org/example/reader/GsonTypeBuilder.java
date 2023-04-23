package org.example.reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.entities.Orders;
import org.example.entities.Store;
import org.example.adapters.OrdersAdapter;
import org.example.adapters.StoreAdapter;

public class GsonTypeBuilder {

    protected Gson buildOrderGsonType( Class<Orders> ordersClass ){
        return new GsonBuilder()
                .registerTypeAdapter(ordersClass, new OrdersAdapter())
                .setPrettyPrinting()
                .create();
    }

    protected Gson buildStoreGsonType( Class<Store> storeClass){
        return new GsonBuilder()
                .registerTypeAdapter(storeClass, new StoreAdapter())
                .setPrettyPrinting()
                .create();
    }
}
