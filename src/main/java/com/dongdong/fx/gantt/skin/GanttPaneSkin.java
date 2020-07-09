package com.dongdong.fx.gantt.skin;

import com.dongdong.fx.gantt.GanttPane;
import com.dongdong.fx.gantt.GanttRow;
import com.dongdong.fx.gantt.TimeAxis;
import javafx.scene.control.ListView;
import javafx.scene.control.SkinBase;
import javafx.scene.shape.Line;

public class GanttPaneSkin<T> extends SkinBase<GanttPane<T>> {

    private final TimeAxis timeAxis;
    private final ListView<T> ganttRowView;
    private final Line xAxis;
    private final Line yAxis;

    public GanttPaneSkin(GanttPane<T> control) {
        super(control);
        timeAxis = new TimeAxis(control);
        xAxis = new Line();
        yAxis = new Line();
        ganttRowView = new ListView<>();
        ganttRowView.setCellFactory(listView -> new GanttRow<>());
        getChildren().addAll(timeAxis, ganttRowView);
    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        super.layoutChildren(contentX, contentY, contentWidth, contentHeight);
    }
}
