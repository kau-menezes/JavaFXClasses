package com.desktopapp;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginWarningController {


    protected String message;


    public void setMessage(String message) {
        this.message = message;
    }

    @FXML
    protected Button closeButton;

    @FXML
    protected Text messageText;
    
    public static Scene CreateScene(String message) throws Exception
    {

        URL sceneUrl = LoginWarningController.class.getResource("LoginWarningStage.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        LoginWarningController controller = loader.getController();
        controller.setMessage(message);
        
        controller.messageText.setText(message);

        System.out.println(controller.message);

        return scene;
    }


    @FXML
    protected void onButtonClick(MouseEvent e) throws Exception
    {
        Stage crrStage = (Stage) closeButton.getScene().getWindow();

        crrStage.close();
    }
}
