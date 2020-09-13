package com.dongdong.fx.gantt;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FormTest extends Application {

    public void start(Stage primaryStage) throws Exception {


        BorderPane pane = new BorderPane();

        Button button = new Button("test");

        ContextMenu ctx = new ContextMenu();
        Menu m1 = new Menu("1");
        ctx.getItems().add(m1);

        button.setContextMenu(ctx);

        button.contextMenuProperty().addListener(listener -> {
            System.out.println("ContextMenu重新绑定");
        });

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                    Platform.runLater(() -> {
                        ctx.getItems().clear();
                        ctx.getItems().add(new Menu("1"));
                        button.setContextMenu(ctx);
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        pane.setCenter(button);


        Scene scene = new Scene(pane);



        primaryStage.setScene(scene);
        primaryStage.show();
    }



}
