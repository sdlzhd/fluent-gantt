package com.dongdong.fx.gantt;

import com.dongdong.fx.gantt.skin.TimeAxisSkin;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class TimeAxis extends Control {

    private GanttPane<?, ?> ganttPane;

    public TimeAxis(GanttPane<?, ?> ganttPane) {
        getStyleClass().add(DEFAULT_STYLE_CLASS);
        this.ganttPane = ganttPane;
    }

    public GanttPane<?, ?> getGanttPane() {
        return ganttPane;
    }

    public void setGanttPane(GanttPane<?, ?> ganttPane) {
        this.ganttPane = ganttPane;
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new TimeAxisSkin(this);
    }

    private static final String DEFAULT_STYLE_CLASS = "time-axis";

}
