package com.dongdong.fx.gantt;

import com.dongdong.fx.gantt.skin.GanttRowSkin;
import javafx.scene.control.ListCell;
import javafx.scene.control.Skin;

public class GanttRow<T> extends ListCell<T> {

    @Override
    protected Skin<?> createDefaultSkin() {
        return new GanttRowSkin<>(this);
    }
}
