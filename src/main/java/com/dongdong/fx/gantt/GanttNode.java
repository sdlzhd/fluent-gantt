package com.dongdong.fx.gantt;

import com.dongdong.fx.gantt.skin.GanttNodeSkin;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class GanttNode<T> extends Control {

    public GanttNode() {
        getStyleClass().add(DEFAULT_STYLE_CLASS);
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
     * Stylesheet Handling                                                     *
     *                                                                         *
     **************************************************************************/

    private static final String DEFAULT_STYLE_CLASS = "gantt-node";

    @Override
    protected Skin<?> createDefaultSkin() {
        return new GanttNodeSkin<>(this);
    }
}
