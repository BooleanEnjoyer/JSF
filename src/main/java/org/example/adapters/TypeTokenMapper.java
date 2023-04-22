package org.example.adapters;

import com.google.gson.reflect.TypeToken;
import org.example.entities.Orders;
import java.lang.reflect.Type;
import java.util.List;

public class TypeTokenMapper{

    public Type assignOrderToken(){

        return new TypeToken<List<Orders>>(){}.getType();
    }

}
