package org.example;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
public class TypeTokenMapper{
    public Type assignOrderToken(){
        return new TypeToken<List<Orders>>(){}.getType();
    }

}
