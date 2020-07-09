package com.dongdong.fx.gantt;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GanttTest extends Application {

    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();

        GanttPane ganttPane = new GanttPane();

        borderPane.setCenter(ganttPane);
        Scene scene = new Scene(borderPane, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
