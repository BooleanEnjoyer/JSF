package org.example;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class OrderComparatorTest {
    Orders o1 = new Orders("1", new BigDecimal("17"), Duration.parse("PT30M"), LocalTime.parse("11:00"));
    Orders o2 = new Orders("2", new BigDecimal("12"), Duration.parse("PT15M"), LocalTime.parse("10:30"));
    @Test
    void compareEquality() {
        assertNotEquals(0, o1.getCompleteBy().minus(o1.getPickingTime()).compareTo(o2.getCompleteBy().minus(o2.getPickingTime())));
    }

    @Test
    void sortTest(){
        List<Orders> jsonOrdersList = new ArrayList<>();
        jsonOrdersList.add(o1);
        jsonOrdersList.add(o2);
        jsonOrdersList.sort(new OrderComparator());
        assertEquals("2" , jsonOrdersList.get(0).getOrderId());
    }
}