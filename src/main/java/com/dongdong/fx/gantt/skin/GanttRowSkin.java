package com.dongdong.fx.gantt.skin;

import com.dongdong.fx.gantt.*;
import javafx.beans.InvalidationListener;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.SkinBase;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import java.util.Date;
import java.util.stream.Collectors;

public class GanttRowSkin<R extends RowBase<T>, T extends NodeBase> extends SkinBase<GanttRow<R, T>> {

    private final GanttPane<R, T> ganttPane;
    private final GanttRow<R, T> ganttRow;
    private final DockButton<R> dockButton;
    private final AnchorPane track = new AnchorPane();

    public GanttRowSkin(GanttRow<R, T> control) {
        super(control);
        ganttRow = control;
        ganttPane = control.getGanttPane();
        dockButton = ganttPane.getDockFactory().call(ganttRow);
        track.getChildren().addAll(ganttRow.getItems().stream().map(item -> {
            GanttNode<Object> gn = new GanttNode<>();
            gn.setData(item);
            return gn;
        }).collect(Collectors.toList()));
        track.getChildren().addListener((InvalidationListener) listener -> System.out.println(track.getChildren().size()));
        getChildren().addAll(dockButton, track);
        track.setOnDragOver(dragOverEvent);
        track.setOnDragEntered(dragEnterEvent);
        track.setOnDragExited(dragExitEvent);
    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        super.layoutChildren(contentX, contentY, contentWidth, contentHeight);
        double h = dockButton.prefHeight(-1);
        double w = dockButton.prefWidth(-1);
        layoutInArea(dockButton, 0, 0, w, h, 0, HPos.LEFT, VPos.CENTER);
        layoutInArea(track, w, 0, ganttRow.getWidth() - w, h, 0, HPos.LEFT, VPos.CENTER);
        Date ganttStartDate = this.ganttPane.getStartTime();
        Date ganttEndDate = this.ganttPane.getEndTime();

        for (T node : ganttRow.getItem().getChildren()) {
            Date nodeStartDate = node.getStartDate();
            Date nodeEndDate = node.getEndDate();
            // 忽略如下情况的节点
            // 节点开始时间>当前面板结束时间
            // 节点结束时间<当前面板开始时间
            if (nodeEndDate.before(ganttStartDate) || nodeStartDate.after(ganttEndDate)) {
                continue;
            }
            double offsetX = (double) (nodeStartDate.getTime() - ganttStartDate.getTime()) / (nodeEndDate.getTime() - ganttEndDate.getTime())
                    * track.getWidth();
            layoutInArea(new Region(), offsetX, 0, calcNodeWidth(node), 40, 0, HPos.LEFT, VPos.CENTER);
        }
    }

    /**
     * 计算GanttNode的宽度
     */
    private double calcNodeWidth(T node) {
        long ganttStartMillis = this.ganttPane.getStartTime().getTime();
        long ganttEndMillis = this.ganttPane.getEndTime().getTime();
        double ganttWidth = track.getWidth();
        long nodeStartMillis = node.getStartDate().getTime();
        long nodeEndMillis = node.getStartDate().getTime();
        return (double) (nodeEndMillis - nodeStartMillis) / (ganttEndMillis - ganttStartMillis) * ganttWidth;
    }

    private EventHandler<DragEvent> dragOverEvent = event -> {
        if (event.getGestureSource() != this &&
                event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    };

    private EventHandler<DragEvent> dragEnterEvent = event -> {
        if (event.getGestureSource() != this &&
                event.getDragboard().hasString()) {
            track.setStyle("-fx-background-color: #999999");
            event.consume();
        }
    };

    private EventHandler<DragEvent> dragExitEvent = event -> {
        track.setStyle("");
        event.consume();
    };

    private EventHandler<DragEvent> dragDroppedEvent = event -> {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {
            success = true;
        }
        event.setDropCompleted(success);
        event.consume();
    };
}
