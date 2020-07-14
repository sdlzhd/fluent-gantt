package com.dongdong.fx.gantt;

import com.dongdong.fx.gantt.entity.Flight;
import com.dongdong.fx.gantt.entity.PlaneSeat;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TimeAxisTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        GanttPane<PlaneSeat, Flight> ganttPane = new GanttPane<>();
        TimeAxis timeAxis = new TimeAxis(ganttPane);
        timeAxis.setPrefWidth(800);
        borderPane.setCenter(timeAxis);
        Scene scene = new Scene(borderPane, 1200, 300);
        scene.getStylesheets().add("/gantt.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
