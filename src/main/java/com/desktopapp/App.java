package com.desktopapp;

import com.desktopapp.model.*;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class App extends Application{

    public static void main(String[] args) {
        // launch(args);

        User user = new User();
        user.setName("");
        user.setPassword("");

        Context ctx = new Context();
        ctx.begin();
        ctx.save(user);
        ctx.commit();

    

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = LoginController.CreateScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
}
