package org.example.adapter;

import com.google.gson.reflect.TypeToken;
import org.example.entitiy.Order;

import java.lang.reflect.Type;
import java.util.List;

public class TypeTokenMapper {

    public Type assignOrderToken() {

        return new TypeToken<List<Order>>() {
        }.getType();
    }

}
