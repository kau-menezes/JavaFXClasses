package com.desktopapp;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;

import java.util.ResourceBundle;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {

    // protected Integer id;

    // public void setId(Integer id) {
    //     this.id = id;
    // }


    public static Scene CreateScene() throws Exception
    {
        URL sceneUrl = LoginController.class.getResource("FirstScreen.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        // LoginController controller = loader.getController();
        // controller.setId(id);
        // loader.setController(controller);

        return scene;
    }

    @FXML
    protected void initialize(URL location, ResourceBundle Respurces)
    {

        // this.loginButton.setText(id.toString());

        this.loginButton.setOnAction( e -> {
            System.err.println("erro");
        });

    }

    @FXML
    protected Button loginButton;

    @FXML
    protected TextField emailInput;

    @FXML
    protected CheckBox visibilityButton;

    @FXML
    protected PasswordField passwordInput;

    @FXML
    protected TextField passwordVisibleInput;

    @FXML
    protected void onButtonClick(MouseEvent e) throws Exception
    {
        Stage crrStage = (Stage) loginButton.getScene().getWindow();

        System.out.println(this.emailInput.getText());
        System.out.println(this.passwordInput.getText());
        
        
        String message = "";
        Boolean flag = false;
        
        if (this.emailInput.getText().equals("adm") && this.passwordInput.getText().equals("adm")) {
            message = "Succesfull login, welcome! 😊";
            flag = true;
            
        } else {
            
            message = "Incorret email or password! ☹️";
            }
            
            
        Scene warningScene = LoginWarningController.CreateScene(message);
            
        if (flag) {
            crrStage.close();

            Scene nextScene = HomeController.CreateScene();

            Stage nextStage = new Stage();
            nextStage.setScene(nextScene);
            nextStage.show();

            Stage newStage = new Stage();
            newStage.setScene(warningScene);
            newStage.show();


        } else {

            Stage newStage = new Stage();
            newStage.setScene(warningScene);
            newStage.show();
        }
        
            
    }

    @FXML
    protected void changeVisibility(MouseEvent e) throws Exception
    {
        if (visibilityButton.isSelected()) {
            passwordInput.setText(passwordVisibleInput.getText());
            passwordInput.setVisible(true);
            passwordVisibleInput.setVisible(false);

        } else {
            passwordVisibleInput.setText(passwordInput.getText());
            passwordVisibleInput.setVisible(true);
            passwordInput.setVisible(false);
        }

    }
}
