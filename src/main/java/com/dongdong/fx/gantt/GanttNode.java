package com.dongdong.fx.gantt;

import com.dongdong.fx.gantt.skin.GanttNodeSkin;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.input.*;

public class GanttNode<T> extends Control {

    public GanttNode() {
        getStyleClass().add(DEFAULT_STYLE_CLASS);
        this.setOnDragDetected(dragDetectedEvent);
        this.setOnDragDone(dragDoneEvent);
    }

    /***************************************************************************
     *                                                                         *
     * Properties                                                              *
     *                                                                         *
     **************************************************************************/

    private final ObjectProperty<T> data = new SimpleObjectProperty<>(this, "data");
    private ObjectProperty<T> dataProperty() {
        return data;
    }

    public T getData() {
        return dataProperty().get();
    }

    public void setData(T value) {
        data.setValue(value);
    }

    /***************************************************************************
     *                                                                         *
     * Callbacks and Events                                                    *
     *                                                                         *
     **************************************************************************/

    private EventHandler<MouseEvent> dragDetectedEvent = event -> {
        // 设置传输模式
        // TODO
        System.out.println("开启拖拽");
        Dragboard db = this.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString("111");
        db.setContent(content);
        event.consume();
    };

    private EventHandler<DragEvent> dragDoneEvent = event -> {
        if (event.getTransferMode() == TransferMode.MOVE) {
            // TODO
        }
        System.out.println("完成拖拽");
        event.consume();
    };

    /***************************************************************************
     *                                                                         *
     * Stylesheet Handling                                                     *
     *                                                                         *
     **************************************************************************/

    private static final String DEFAULT_STYLE_CLASS = "gantt-node";

    @Override
    protected Skin<?> createDefaultSkin() {
        return new GanttNodeSkin<>(this);
    }
}
