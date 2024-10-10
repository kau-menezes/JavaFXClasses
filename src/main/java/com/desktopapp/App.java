package com.desktopapp;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class App extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = LoginController.CreateScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
