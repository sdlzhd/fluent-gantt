package com.dongdong.fx.gantt;

import com.dongdong.fx.gantt.entity.PlaneSeat;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GanttRowTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        ListView<PlaneSeat> listView = new ListView<>();

        PlaneSeat seat1 = new PlaneSeat("机位1");
        PlaneSeat seat2 = new PlaneSeat("机位2");
        PlaneSeat seat3 = new PlaneSeat("机位3");
        PlaneSeat seat4 = new PlaneSeat("机位4");
        PlaneSeat seat5 = new PlaneSeat("机位5");
        PlaneSeat seat6 = new PlaneSeat("机位6");
        PlaneSeat seat7 = new PlaneSeat("机位7");
        PlaneSeat seat8 = new PlaneSeat("机位8");
        PlaneSeat seat9 = new PlaneSeat("机位9");
        PlaneSeat seat10 = new PlaneSeat("机位10");
        PlaneSeat seat11 = new PlaneSeat("机位11");
        PlaneSeat seat12 = new PlaneSeat("机位12");
        PlaneSeat seat13 = new PlaneSeat("机位13");
        PlaneSeat seat14 = new PlaneSeat("机位14");
        PlaneSeat seat15 = new PlaneSeat("机位15");
        PlaneSeat seat16 = new PlaneSeat("机位16");

        listView.setFixedCellSize(42.0d);
        listView.getItems().addAll(seat1, seat2, seat3, seat4, seat5,
                seat6, seat7, seat8, seat9, seat10,
                seat11, seat12, seat13, seat14, seat15, seat16);

        listView.setCellFactory(lv -> new GanttRow<PlaneSeat, Object>(null) {
            @Override
            protected void updateItem(PlaneSeat item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        borderPane.setCenter(listView);
        Scene scene = new Scene(borderPane, 1200, 300);
        scene.getStylesheets().add("/gantt.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
