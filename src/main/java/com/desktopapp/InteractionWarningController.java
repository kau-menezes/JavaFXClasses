package com.desktopapp;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InteractionWarningController {

    protected String message;
    private Stage stage;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMessage(String message) {
        this.message = message;
        
    }

    public static Scene CreateScene(String message, Stage stage) throws Exception
    {
        URL sceneUrl = InteractionWarningController.class.getResource("InteractionWarning.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        InteractionWarningController controller = loader.getController();
        controller.setMessage(message);
        controller.setStage(stage);
        
        controller.messageText.setText(message);

        System.out.println(controller.message);

        return scene;
    }

    @FXML
    protected Button denyButton;

    @FXML
    protected Button confirmButton;

    @FXML
    protected Text messageText;

    @FXML
    protected void onDeny(MouseEvent e) throws Exception
    {
        Stage crrStage = (Stage) denyButton.getScene().getWindow();

        crrStage.close();
    }

    @FXML
    protected void onConfirm(MouseEvent e) throws Exception
    {
        Stage crrStage = (Stage) confirmButton.getScene().getWindow();

        crrStage.close(); 
        this.stage.close();

        Scene nextScene = LoginController.CreateScene();
        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.show();
    
    }





    
}
