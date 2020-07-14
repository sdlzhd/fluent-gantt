package com.dongdong.fx.gantt;

import com.dongdong.fx.gantt.entity.Flight;
import com.dongdong.fx.gantt.entity.PlaneSeat;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.*;

public class GanttTest extends Application {

    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();

        GanttPane<PlaneSeat, Flight> ganttPane = new GanttPane<>();
        // 设置甘特图标题
        ganttPane.setTitle("机位");

        // 自定义甘特行
        ganttPane.setDockFactory(row -> {
            DockButton<PlaneSeat> button = new DockButton<>(row);
            button.setOnMouseClicked(e -> System.out.println(row.getItem().getName()));
            return button;
        });

        // 绑定甘特节点和甘特行
        ganttPane.setValueBindFactory(Flight::getPlaneSeat);

        // 添加甘特行
        ArrayList<PlaneSeat> planeSeats = getPlaneSeat();
        ganttPane.getRows().addAll(planeSeats);
        // 设置甘特行对象显示规则
        ganttPane.setRowConverter(new StringConverter<PlaneSeat>() {
            @Override
            public String toString(PlaneSeat object) {
                return object.getName();
            }

            @Override
            public PlaneSeat fromString(String string) {
                return null;
            }
        });

        borderPane.setCenter(ganttPane);
        Scene scene = new Scene(borderPane, 1200, 800);
        scene.getStylesheets().add("/gantt.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private ArrayList<PlaneSeat> getPlaneSeat() {
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

        return new ArrayList<>(Arrays.asList(seat1, seat2, seat3, seat4, seat5,
                seat6, seat7, seat8, seat9, seat10,
                seat11, seat12, seat13, seat14, seat15, seat16));
    }

}
