package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.adapter.OrdersAdapter;
import org.example.entitiy.Order;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Type;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderAdapterTest {

    @Test
    void deserializationOrdersTest() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Order.class , new OrdersAdapter()).create();
        String jsonOrderFile= """
                [
                  {
                    "orderId": "order-178",
                    "orderValue": "7.33",
                    "pickingTime": "PT5M",
                    "completeBy": "14:36"
                  },
                  {
                    "orderId": "order-3796",
                    "orderValue": "8.41",
                    "pickingTime": "PT10M",
                    "completeBy": "16:51"
                  },
                  {
                    "orderId": "order-4113",
                    "orderValue": "11.80",
                    "pickingTime": "PT5M",
                    "completeBy": "12:01"
                  },
                  {
                    "orderId": "order-761",
                    "orderValue": "8.35",
                    "pickingTime": "PT4M",
                    "completeBy": "11:14"
                  } ]""";
        Type OrderType = new TypeToken<List<Order>>(){}.getType();
        List<Order> jsonOrderList = gson.fromJson(jsonOrderFile , OrderType);
        assertEquals("order-4113", jsonOrderList.get(2).getId());
    }
}