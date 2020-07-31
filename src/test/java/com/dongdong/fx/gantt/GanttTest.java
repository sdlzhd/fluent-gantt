package com.dongdong.fx.gantt;

import com.dongdong.fx.gantt.entity.Flight;
import com.dongdong.fx.gantt.entity.PlaneSeat;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.apache.commons.lang3.RandomStringUtils;

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
        List<PlaneSeat> planeSeats = new ArrayList<>();
        getPlaneSeat(planeSeats);
        ganttPane.getRows().addAll(planeSeats);

        // 添加甘特节点
        List<Flight> flights = getFlights(planeSeats);
        ganttPane.getItems().addAll(flights);


        borderPane.setCenter(ganttPane);
        Scene scene = new Scene(borderPane, 800, 500);
        scene.getStylesheets().add("/gantt.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void getPlaneSeat(List<PlaneSeat> planeSeats) {
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

        planeSeats.clear();
        planeSeats.addAll(Arrays.asList(seat1, seat2, seat3, seat4, seat5,
                seat6, seat7, seat8, seat9, seat10,
                seat11, seat12, seat13, seat14, seat15, seat16));
    }

    private List<Flight> getFlights(List<PlaneSeat> planeSeats) {
        Random rand = new Random();
        List<Flight> flights = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            flights.add(createFlight(planeSeats.get(rand.nextInt(16))));
        }
        return flights;
    }


    Random rand_hours = new Random(20);
    Random rand_min = new Random(60);

    private Flight createFlight(PlaneSeat planeSeat) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, rand_hours.nextInt());
        calendar.set(Calendar.MINUTE, rand_min.nextInt());
        calendar.set(Calendar.SECOND, 0);

        Flight flight = new Flight();
        flight.setName(RandomStringUtils.randomAlphabetic(2) + RandomStringUtils.randomNumeric(4));

        flight.setLandingTime(calendar.getTime());

        calendar.add(Calendar.HOUR_OF_DAY, 1);
        flight.setTakeOffTime(calendar.getTime());

        flight.setPlaneSeat(planeSeat);
        return flight;
    }

}
