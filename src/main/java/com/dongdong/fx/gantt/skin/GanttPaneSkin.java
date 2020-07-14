package com.dongdong.fx.gantt.skin;

import com.dongdong.fx.gantt.GanttPane;
import com.dongdong.fx.gantt.GanttRow;
import com.dongdong.fx.gantt.TimeAxis;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SkinBase;
import javafx.scene.shape.Line;

import java.util.Objects;

public class GanttPaneSkin<R, T> extends SkinBase<GanttPane<R, T>> {

    private final GanttPane<R, T> ganttPane;
    private final Label title;
    private final TimeAxis timeAxis;
    private final ListView<R> ganttRowView;
    private final Line xAxis;
    private final Line yAxis;

    public GanttPaneSkin(GanttPane<R, T> control) {
        super(control);
        ganttPane = control;
        timeAxis = new TimeAxis(ganttPane);
        title = new Label();
        title.textProperty().bind(ganttPane.titleProperty());
        xAxis = new Line();
        yAxis = new Line();
        ganttRowView = new ListView<>();
        ganttRowView.setFixedCellSize(ganttPane.getRowHeight());
        ganttRowView.getItems().addAll(ganttPane.getRows());
        ganttRowView.setCellFactory(listView -> new GanttRow<>(ganttPane));
        getChildren().addAll(title, timeAxis, ganttRowView);
    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        layoutInArea(title, 0, 0, 100d, ganttPane.getRowHeight(), 0, HPos.CENTER, VPos.CENTER);
        layoutInArea(timeAxis, 100d, 0, ganttPane.getWidth(), ganttPane.getRowHeight(), 0, HPos.CENTER, VPos.CENTER);
        layoutInArea(ganttRowView, 0, ganttPane.getRowHeight(), ganttPane.getWidth(), ganttPane.getHeight(), 0, HPos.CENTER, VPos.CENTER);
    }
}
