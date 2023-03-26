package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Gson gson = new GsonBuilder().registerTypeAdapter(Orders.class , new OrdersAdapter()).setPrettyPrinting().create();

        Type OrderType = new TypeToken<List<Orders>>(){}.getType();
        Gson gsonS = new GsonBuilder().registerTypeAdapter(Store.class , new StoreAdapter()).setPrettyPrinting().create();

        JsonReader Store_reader = null;
        JsonReader Order_reader = null;
                    System.out.println("Arg 0 store " + args[0]);
                    System.out.println("Arg 1 orders " + args[1]);
        try {
            Store_reader = new JsonReader(new FileReader(args[0]));
            Order_reader = new JsonReader(new FileReader(args[1]));
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        if(Store_reader!=null && Order_reader!=null) {

            Store jsonStore = gsonS.fromJson(Store_reader,Store.class);
            List<Orders> jsonOrdersList = gson.fromJson(Order_reader , OrderType);
            jsonOrdersList.sort(new OrderComparator());
            LocalTime next_pick=jsonStore.getPickingStartTime();
            Duration possible_take;
            Duration higher_value_take;
            Duration lookup;
            BigDecimal current_value;
            BigDecimal possible_value;
            List<String> CompletedOrders = new ArrayList<>();
            StringBuilder ordersTaken=new StringBuilder();

            for(int i=0; i<jsonStore.getPickers().size(); i++) {
                for(int j=0; j<jsonOrdersList.size(); j++) {

                    possible_take = Duration.between(next_pick , jsonOrdersList.get(j).getCompleteBy().minus(jsonOrdersList.get(j).getPickingTime()));

                    if (possible_take.isNegative() || ordersTaken.indexOf(jsonOrdersList.get(j).getOrderId() + " ") != -1 && i>0) {
                        continue;
                    }
                    current_value=jsonOrdersList.get(j).getOrderValue();
                    for(int z=j+1; z<jsonOrdersList.size(); z++){

                        possible_value = jsonOrdersList.get(z).getOrderValue();
                        higher_value_take = Duration.between(next_pick , jsonOrdersList.get(z).getCompleteBy().minus(jsonOrdersList.get(z).getPickingTime()));
                        lookup = Duration.between(jsonOrdersList.get(z).getCompleteBy().minus(jsonOrdersList.get(z).getPickingTime()) , jsonOrdersList.get(j).getCompleteBy());

                        if(current_value.compareTo(possible_value) > 0 || higher_value_take.isNegative() || lookup.isNegative() || lookup.isZero()  || ordersTaken.indexOf(jsonOrdersList.get(z).getOrderId() + " ") != -1 && i>0){
                            continue;
                        }

                        j=z;
                        break;
                    }
                    if (ordersTaken.indexOf(jsonOrdersList.get(j).getOrderId() + " ") == -1) {
                        CompletedOrders.add(jsonStore.getPickers().get(i) +" "+ jsonOrdersList.get(j).getOrderId() +" "+ next_pick);
                        next_pick = next_pick.plus(jsonOrdersList.get(j).getPickingTime());
                        ordersTaken.append(jsonOrdersList.get(j).getOrderId()).append(" ");
                    }
                }
                next_pick= jsonStore.getPickingStartTime();
            }
            for (String completedOrder : CompletedOrders) {
                System.out.println(completedOrder);
            }
            System.out.println("Number of completed orders " + CompletedOrders.size());
        }
    }
}