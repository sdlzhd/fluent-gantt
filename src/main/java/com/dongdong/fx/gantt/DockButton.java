package com.dongdong.fx.gantt;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Labeled;

public class DockButton<T> extends Labeled {

    public DockButton(GanttRow<?, T> ganttRow) {
        setGanttRow(ganttRow);
        textProperty().bind(ganttRow.textProperty());
        graphicProperty().bind(ganttRow.graphicProperty());
    }


    private final ObjectProperty<GanttRow<?, T>> ganttRow = new SimpleObjectProperty<>();

    final void setGanttRow(GanttRow<?, T> value) {
        ganttRow.setValue(value);
    }

    public GanttRow<?, T> getGanttRow() {
        return ganttRow.get();
    }

    public ObjectProperty<GanttRow<?, T>> ganttRowProperty() {
        return ganttRow;
    }

}
