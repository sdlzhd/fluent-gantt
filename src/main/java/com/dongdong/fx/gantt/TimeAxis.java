package com.dongdong.fx.gantt;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class TimeAxis extends Control {

    private GanttPane ganttPane;

    public TimeAxis(GanttPane ganttPane) {
        this.ganttPane = ganttPane;
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return super.createDefaultSkin();
    }
}
