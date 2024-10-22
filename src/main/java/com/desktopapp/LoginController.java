package com.desktopapp;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;

import java.util.List;
import java.util.ResourceBundle;

import com.desktopapp.model.User;

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

        Context newContext = new Context();

        var query = newContext.createQuery(User.class, "from User u where u.name = :name");
        query.setParameter("name", this.emailInput.getText());
        List<User> users = query.getResultList();
        
        String message = "Incorret email or password! ☹️";
        Boolean flag = false;
        
        if (users.size() > 0) {

            if (this.passwordInput.getText().equals(users.get(0).getPassword())) {
                message = "Succesfull login, welcome! 😊";
                flag = true;
            } 
        }
            
        Scene warningScene = LoginWarningController.CreateScene(message);
            
        if (flag) {
            crrStage.close();

            Scene nextScene = HomeController.CreateScene(users.get(0).getName());

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
            passwordInput.setVisible(false);
            passwordVisibleInput.setVisible(true);

        } else {
            passwordVisibleInput.setText(passwordInput.getText());
            passwordVisibleInput.setVisible(false);
            passwordInput.setVisible(true);
        }

    }
}
