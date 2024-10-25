package com.desktopapp;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InteractionWarningController{

    protected String message;
    private Scene scene;
    private boolean isConfirm = false;

    public boolean isConfirm() {
        return isConfirm;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setMessage(String message) {
        this.message = message;
        
    }

    public static boolean ShowAndWait(String message) throws Exception
    {
        URL sceneUrl = InteractionWarningController.class.getResource("InteractionWarning.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        InteractionWarningController controller = loader.getController();
        controller.setMessage(message);
        
        controller.messageText.setText(message);

        Stage warningStage = new Stage();
        warningStage.setScene(scene);
        warningStage.showAndWait();

        return controller.isConfirm();
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
        isConfirm = true;
        Stage crrStage = (Stage) confirmButton.getScene().getWindow();
        crrStage.close();
    }
    
}
