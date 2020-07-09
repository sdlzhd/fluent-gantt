package com.dongdong.fx.gantt;

import com.dongdong.fx.gantt.skin.GanttRowSkin;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ListCell;
import javafx.scene.control.Skin;

import java.util.function.Consumer;

public class GanttRow<S, T> extends ListCell<T> {


    private final ReadOnlyObjectWrapper<GanttPane<S>> ganttPane = new ReadOnlyObjectWrapper<>(this, "ganttPane");

    public GanttRow() {
        getStyleClass().add(DEFAULT_STYLE_CLASS);
    }

    public GanttRow(String text) {
        this();
        setText(text);
    }

    public final ReadOnlyObjectProperty<GanttPane<S>> ganttPaneProperty() {
        return ganttPane.getReadOnlyProperty();
    }

    final void setGanttPane(GanttPane<S> value) {
        ganttPane.set(value);
    }

    public final GanttPane<S> getGanttPane() {
        return ganttPane.get();
    }

    private final ObjectProperty<Consumer<DockButton<T>>> customDockButton =
            new SimpleObjectProperty<>(this, "customDockButton");

    public void setCustomDockButton(Consumer<DockButton<T>> consumer) {
        customDockButton.setValue(consumer);
    }

    public Consumer<DockButton<T>> getCustomDockButton() {
        return customDockButton.getValue();
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




}
