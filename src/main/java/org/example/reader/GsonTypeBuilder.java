package org.example.reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.entitiy.Order;
import org.example.entitiy.Store;
import org.example.adapter.OrdersAdapter;
import org.example.adapter.StoreAdapter;

public class GsonTypeBuilder {

    protected Gson buildOrderGsonType(Class<Order> ordersClass) {
        return new GsonBuilder()
                .registerTypeAdapter(ordersClass, new OrdersAdapter())
                .setPrettyPrinting()
                .create();
    }

    protected Gson buildStoreGsonType(Class<Store> storeClass) {
        return new GsonBuilder()
                .registerTypeAdapter(storeClass, new StoreAdapter())
                .setPrettyPrinting()
                .create();
    }
}
