package org.example;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class OrdersAdapter extends TypeAdapter<Orders> {

    @Override
    public void write(JsonWriter jsonWriter, Orders orders) throws IOException {}

    @Override
    public Orders read(JsonReader jsonReader) throws IOException {
        Orders order = new Orders();
        jsonReader.beginObject();
        String fieldname = null;

        while (jsonReader.hasNext()){
            JsonToken token = jsonReader.peek();

            if(token.equals(JsonToken.NAME)){
                fieldname = jsonReader.nextName();
            }

            if("orderId".equals(fieldname)){
                token = jsonReader.peek();
                order.setOrderId(jsonReader.nextString());
            }

            if("orderValue".equals(fieldname)){
                token = jsonReader.peek();
                order.setOrderValue(new BigDecimal(jsonReader.nextString()));
            }

            if("pickingTime".equals(fieldname)){
                token = jsonReader.peek();
                order.setPickingTime(Duration.parse(jsonReader.nextString()));
            }

            if("completeBy".equals(fieldname)){
                token = jsonReader.peek();
                order.setCompleteBy(LocalTime.parse(jsonReader.nextString()));
            }
        }
        jsonReader.endObject();
        return order;
    }
}
