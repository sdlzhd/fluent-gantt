package com.dongdong.fx.gantt.entity;

import com.dongdong.fx.gantt.NodeBase;

import java.util.Date;

public class Flight implements NodeBase {
    private String name;
    private Date landingTime;
    private Date takeOffTime;
    private PlaneSeat planeSeat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLandingTime() {
        return landingTime;
    }

    public void setLandingTime(Date landingTime) {
        this.landingTime = landingTime;
    }

    public Date getTakeOffTime() {
        return takeOffTime;
    }

    public void setTakeOffTime(Date takeOffTime) {
        this.takeOffTime = takeOffTime;
    }

    public PlaneSeat getPlaneSeat() {
        return planeSeat;
    }

    public void setPlaneSeat(PlaneSeat planeSeat) {
        this.planeSeat = planeSeat;
    }

    @Override
    public Date getStartDate() {
        return getLandingTime();
    }

    @Override
    public Date getEndDate() {
        return getTakeOffTime();
    }
}
