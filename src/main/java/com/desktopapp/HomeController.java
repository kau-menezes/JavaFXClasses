package com.desktopapp;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeController {


    public static Scene CreateScene() throws Exception
    {
        URL sceneUrl = LoginController.class.getResource("Home.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        // loader.setController(controller);

        return scene;
    }

    @FXML
    protected Button logOutButton;

    public void onButtonClick(MouseEvent e) throws Exception {
        Scene warningScene = InteractionWarningController.CreateScene("Are you sure you want to log out?");
        Stage warningStage = new Stage();
        warningStage.setScene(warningScene);
        warningStage.show();
    }





    
}
