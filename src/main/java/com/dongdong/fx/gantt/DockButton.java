package com.dongdong.fx.gantt;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;

public class DockButton<R extends RowBase<?>> extends Label {

    public DockButton(GanttRow<R, ?> ganttRow) {
        getStyleClass().clear();
        getStyleClass().add(DEFAULT_STYLE_CLASS);
        setGanttRow(ganttRow);
        textProperty().bind(ganttRow.textProperty());
        graphicProperty().bind(ganttRow.graphicProperty());
    }

    private final ObjectProperty<GanttRow<R, ?>> ganttRow = new SimpleObjectProperty<>();

    final void setGanttRow(GanttRow<R, ?> value) {
        ganttRow.setValue(value);
    }

    public GanttRow<R, ?> getGanttRow() {
        return ganttRow.get();
    }

    public ObjectProperty<GanttRow<R, ?>> ganttRowProperty() {
        return ganttRow;
    }

    /***************************************************************************
     *                                                                         *
     * Stylesheet Handling                                                     *
     *                                                                         *
     **************************************************************************/

    private static final String DEFAULT_STYLE_CLASS = "dock-button";

}
