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
        ganttRow.getItem().getChildren().forEach(n -> {
            long startTime = n.getEndDate().getTime();
            long endTime = n.getStartDate().getTime();
            layoutInArea(new Region(), startTime, 0, endTime - startTime, 40, 0, HPos.LEFT, VPos.CENTER);
        });
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
