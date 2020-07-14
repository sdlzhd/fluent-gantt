package com.dongdong.fx.gantt.skin;

import com.dongdong.fx.gantt.DockButton;
import com.dongdong.fx.gantt.GanttPane;
import com.dongdong.fx.gantt.GanttRow;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.AnchorPane;

public class GanttRowSkin<R, T> extends SkinBase<GanttRow<R, T>> {

    private final GanttPane<R, T> ganttPane;
    private final GanttRow<R, T> ganttRow;
    private DockButton<R> dockButton;
    private AnchorPane track;

    public GanttRowSkin(GanttRow<R, T> control) {
        super(control);
        ganttRow = control;
        ganttPane = control.getGanttPane();
        dockButton = ganttPane.getDockFactory().call(ganttRow);
        track = new AnchorPane();
        getChildren().addAll(dockButton, track);
    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        super.layoutChildren(contentX, contentY, contentWidth, contentHeight);
        double h = dockButton.prefHeight(-1);
        double w = dockButton.prefWidth(-1);
        layoutInArea(dockButton, 0, 0, w, h, 0, HPos.LEFT, VPos.CENTER);
        layoutInArea(track, w, 0, ganttRow.getWidth() - w, h, 0, HPos.LEFT, VPos.CENTER);
    }
}
