package com.dongdong.fx.gantt.skin;

import com.dongdong.fx.gantt.GanttNode;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;

import java.util.Random;

public class GanttNodeSkin<T> extends SkinBase<GanttNode<T>> {

    private final Label text;
    private final T data;

    public GanttNodeSkin(GanttNode<T> control) {
        super(control);
        data = control.getData();
        text = new Label(data.toString());
        getChildren().addAll(text);
    }

//    @Override
//    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
//        super.layoutChildren(contentX, contentY, contentWidth, contentHeight);
//        layoutInArea(text, new Random().nextInt(1000), 0, 100, 40, 0, HPos.LEFT, VPos.CENTER);
//    }
}
