package com.desktopapp;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomeController {

    
    private Integer id;

    // protected HomeController controller;
     

    // public void setController(HomeController controller) {
    //     this.controller = controller;
    // }

    public static Scene CreateScene() throws Exception
    {
        URL sceneUrl = LoginController.class.getResource("Home.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // gambs
        // HomeController controller = loader.getController();
        // controller.setController(controller);
        
        return scene;
    }
    
    @FXML
    protected Button logOutButton;
    
    public void onButtonClick(MouseEvent e) throws Exception {

        // URL sceneUrl = InteractionWarningController.class.getResource("InteractionWarning.fxml");
        // FXMLLoader loader = new FXMLLoader(sceneUrl);
        // InteractionWarningController banana = loader.getController();

        Scene warningScene = InteractionWarningController.CreateScene("Are you sure you want to log out?", (Stage) logOutButton.getScene().getWindow());
        // banana.setStage( );
        Stage warningStage = new Stage();
        warningStage.setScene(warningScene);
        warningStage.show();
        
    }





    
}
