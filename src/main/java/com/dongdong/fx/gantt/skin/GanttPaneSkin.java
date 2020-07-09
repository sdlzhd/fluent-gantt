package com.dongdong.fx.gantt.skin;

import com.dongdong.fx.gantt.GanttPane;
import com.dongdong.fx.gantt.GanttRow;
import javafx.scene.control.ListView;
import javafx.scene.control.SkinBase;

public class GanttPaneSkin<T> extends SkinBase<GanttPane<T>> {

    private ListView<T> ganttRowView = new ListView<T>();

    public GanttPaneSkin(GanttPane<T> control) {
        super(control);
        getChildren().add(ganttRowView);
        ganttRowView.setCellFactory(listView -> new GanttRow<>());
    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        super.layoutChildren(contentX, contentY, contentWidth, contentHeight);
    }
}
