package org.example.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.example.entities.Orders;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class OrdersAdapter extends TypeAdapter<Orders> {

    @Override
    public void write(JsonWriter jsonWriter,
                      Orders orders){}

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
                jsonReader.peek();
                order.setOrderId(jsonReader.nextString());
            }

            if("orderValue".equals(fieldname)){
                jsonReader.peek();
                order.setOrderValue(
                        new BigDecimal(jsonReader.nextString())
                );
            }

            if("pickingTime".equals(fieldname)){
                jsonReader.peek();
                order.setPickingTime(
                        Duration.parse(jsonReader.nextString())
                );
            }

            if("completeBy".equals(fieldname)){
                jsonReader.peek();
                order.setCompleteBy(
                        LocalTime.parse(jsonReader.nextString())
                );
            }
        }
        jsonReader.endObject();
        return order;
    }
}
