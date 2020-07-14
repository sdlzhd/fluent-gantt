package com.dongdong.fx.gantt.skin;

import com.dongdong.fx.gantt.GanttPane;
import com.dongdong.fx.gantt.TimeAxis;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeAxisSkin extends SkinBase<TimeAxis> {

    /**
     * 一天总的秒数
     */
    private static final long radix = 24 * 60 * 60 * 1000;

    private final TimeAxis timeAxis;
    private final GanttPane<?, ?> ganttPane;

    private final Path axis = new Path();
    private final List<Text> texts = new ArrayList<>();

    private AnchorPane anchorPane = new AnchorPane();

    public TimeAxisSkin(TimeAxis control) {
        super(control);
        timeAxis = control;
        ganttPane = timeAxis.getGanttPane();
        getChildren().addAll(anchorPane);
        draw();
    }

    private void draw() {
        axis.getElements().clear();
        long startTime = ganttPane.getStartTime().getTime();
        long endTime = ganttPane.getEndTime().getTime();

        double width = 1200;
        double height = ganttPane.getRowHeight();

        long interval;
        long intervalDivNum;

        // 倍率：一天的多少倍
        long multiple = (endTime - startTime) / radix;
        if (multiple == 0L) {
            multiple = 1;
        }
        if (width < 1000 * multiple) {
            interval = 2 * 60 * 60 * 1000 * multiple; // 2h * multiple
            intervalDivNum = 12;
        } else if (width < 3000 * multiple) {
            interval = 60 * 60 * 1000 * multiple; // 1h * multiple
            intervalDivNum = 6;
        } else if (width < 6000 * multiple) {
            interval = 30 * 60 * 1000 * multiple; // 30min * multiple
            intervalDivNum = 6;
        } else {
            interval = 10 * 60 * 1000 * multiple; // 10min * multiple
            intervalDivNum = 10;
        }
        long dividedInterval = interval / intervalDivNum;

        for (long millis = startTime; millis <= endTime; millis += dividedInterval) {
            double currentPos = (double) (millis - startTime) / (endTime - startTime) * width;
            axis.getElements().add(new MoveTo(currentPos, 0));
            if (((millis - startTime) / dividedInterval) % intervalDivNum == 0) {
                String timeString = format(new Date(millis), FORMAT_HOUR_MINUTE);
                // special treatment for 0 o'clock
                if (timeString.equals("00:00")) {
                    timeString = format(new Date(millis), FORMAT_MONTH_DAY);
                }
                Text text = new Text(timeString);
                anchorPane.getChildren().add(text);
                texts.add(text);
                AnchorPane.setLeftAnchor(text, currentPos - text.getFont().getSize() * 5 / 4);
                AnchorPane.setBottomAnchor(text, height / 4);
                axis.getElements().add(new LineTo(currentPos, -height / 4));
            } else {
                axis.getElements().add(new LineTo(currentPos, -height / 8));
            }
        }
        axis.getElements().add(new ClosePath());
        anchorPane.getChildren().add(axis);
        AnchorPane.setLeftAnchor(axis, 0.0);
        AnchorPane.setBottomAnchor(axis, 0.0);
    }

    private static final DateFormat FORMAT_HOUR_MINUTE = new SimpleDateFormat("HH:mm");
    private static final DateFormat FORMAT_MONTH_DAY = new SimpleDateFormat("MM-dd");

    private static String format(Date source, DateFormat dateFormat) {
        return dateFormat.format(source);
    }


    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return ganttPane.getRowHeight();
    }

    @Override
    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return ganttPane.getRowHeight();
    }

    @Override
    protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return ganttPane.getRowHeight();
    }
}
