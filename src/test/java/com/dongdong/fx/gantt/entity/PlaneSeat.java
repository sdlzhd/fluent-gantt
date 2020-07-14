package com.dongdong.fx.gantt.entity;

import java.util.Objects;

public class PlaneSeat {
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
}
