package com.dongdong.fx.gantt.entity;

import com.dongdong.fx.gantt.RowBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlaneSeat implements RowBase<Flight> {
    private String name;

    public PlaneSeat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<Flight> flights = new ArrayList<>();

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaneSeat seat = (PlaneSeat) o;

        return Objects.equals(name, seat.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getTipText() {
        return name;
    }

    @Override
    public List<Flight> getChildren() {
        return flights;
    }
}
