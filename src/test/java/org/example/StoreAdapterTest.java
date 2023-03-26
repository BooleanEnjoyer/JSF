package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class StoreAdapterTest {

    @Test
    void deserializationStoreTest(){
        Gson gsonS = new GsonBuilder().registerTypeAdapter(Store.class , new StoreAdapter()).create();
        String jsonStoreFile="{\"pickers\": [\"P1\", \"P2\"],\"pickingStartTime\": \"09:00\", \"pickingEndTime\": \"10:00\"}";
        Store jsonStore = gsonS.fromJson(jsonStoreFile,Store.class);
        assertEquals("P2", jsonStore.getPickers().get(1));
    }

}