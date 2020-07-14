package com.dongdong.fx.gantt;

import com.dongdong.fx.gantt.skin.GanttRowSkin;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.Skin;

public class GanttRow<R, T> extends ListCell<R> {

    private final ReadOnlyObjectWrapper<GanttPane<R, T>> ganttPane = new ReadOnlyObjectWrapper<>(this, "ganttPane");

    public GanttRow(GanttPane<R, T> ganttPane) {
        this.setGanttPane(ganttPane);
        getStyleClass().add(DEFAULT_STYLE_CLASS);
    }

    /***************************************************************************
     *                                                                         *
     * Properties                                                              *
     *                                                                         *
     **************************************************************************/

    private final ObservableList<T> items = FXCollections.observableArrayList();

    public final ObservableList<T> getItems() {
        return items;
    }

    public final ReadOnlyObjectProperty<GanttPane<R, T>> ganttPaneProperty() {
        return ganttPane.getReadOnlyProperty();
    }

    public final void setGanttPane(GanttPane<R, T> value) {
        ganttPane.set(value);
    }

    public final GanttPane<R, T> getGanttPane() {
        return ganttPane.get();
    }

    /***************************************************************************
     *                                                                         *
     * Stylesheet Handling                                                     *
     *                                                                         *
     **************************************************************************/

    private static final String DEFAULT_STYLE_CLASS = "gantt-row";

    @Override
    protected Skin<?> createDefaultSkin() {
        return new GanttRowSkin<>(this);
    }

    /***************************************************************************
     *                                                                         *
     * Static properties and methods                                           *
     *                                                                         *
     **************************************************************************/

    @Override
    protected void updateItem(R item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
        } else {
            setText(getGanttPane().getRowConverter().toString(item));
        }
    }
}
