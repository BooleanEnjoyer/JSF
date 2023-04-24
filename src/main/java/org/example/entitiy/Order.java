package org.example.entitiy;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class Order {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Duration getPickingTime() {
        return pickingTime;
    }

    public void setPickingTime(Duration pickingTime) {
        this.pickingTime = pickingTime;
    }

    public LocalTime getCompleteBy() {
        return completeBy;
    }

    public void setCompleteBy(LocalTime completeBy) {
        this.completeBy = completeBy;
    }

    public Order() {

    }

    public Order(String id, BigDecimal value,
                 Duration pickingTime, LocalTime completeBy) {
        this.id = id;
        this.value = value;
        this.pickingTime = pickingTime;
        this.completeBy = completeBy;
    }

    private String id;
    private BigDecimal value;
    private Duration pickingTime;
    private LocalTime completeBy;
}
