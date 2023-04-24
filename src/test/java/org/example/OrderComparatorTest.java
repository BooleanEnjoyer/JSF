package org.example;

import org.example.comparator.OrderComparator;
import org.example.entitiy.Order;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class OrderComparatorTest {
    Order o1 = new Order("1", new BigDecimal("17"), Duration.parse("PT30M"), LocalTime.parse("11:00"));
    Order o2 = new Order("2", new BigDecimal("12"), Duration.parse("PT15M"), LocalTime.parse("10:30"));
    @Test
    void compareEquality() {
        assertNotEquals(0, o1.getCompleteBy().minus(o1.getPickingTime()).compareTo(o2.getCompleteBy().minus(o2.getPickingTime())));
    }

    @Test
    void sortTest(){
        List<Order> jsonOrderList = new ArrayList<>();
        jsonOrderList.add(o1);
        jsonOrderList.add(o2);
        jsonOrderList.sort(new OrderComparator());
        assertEquals("2" , jsonOrderList.get(0).getId());
    }
}