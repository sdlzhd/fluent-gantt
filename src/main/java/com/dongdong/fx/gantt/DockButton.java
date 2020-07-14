package com.dongdong.fx.gantt;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;

public class DockButton<S> extends Label {

    public DockButton(GanttRow<S, ?> ganttRow) {
        getStyleClass().clear();
        getStyleClass().add(DEFAULT_STYLE_CLASS);
        setGanttRow(ganttRow);
        textProperty().bind(ganttRow.textProperty());
        graphicProperty().bind(ganttRow.graphicProperty());
    }

    private final ObjectProperty<GanttRow<S, ?>> ganttRow = new SimpleObjectProperty<>();

    final void setGanttRow(GanttRow<S, ?> value) {
        ganttRow.setValue(value);
    }

    public GanttRow<S, ?> getGanttRow() {
        return ganttRow.get();
    }

    public ObjectProperty<GanttRow<S, ?>> ganttRowProperty() {
        return ganttRow;
    }

    /***************************************************************************
     *                                                                         *
     * Stylesheet Handling                                                     *
     *                                                                         *
     **************************************************************************/

    private static final String DEFAULT_STYLE_CLASS = "dock-button";

}
