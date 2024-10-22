package com.desktopapp;

import java.net.URL;

import java.util.*;

import com.desktopapp.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeController {

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @FXML
    protected Text greetingsText;

    @FXML
    protected Text usersText;

    @FXML
    protected Button logOutButton;

    @FXML
    protected Button registerPageButton;


    public static Scene CreateScene(String message) throws Exception {
        URL sceneUrl = HomeController.class.getResource("Home.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        HomeController controller = loader.getController();
        controller.setMessage(message);

        controller.greetingsText.setText("Welcome, " + message + "! 😊");

        Context newContext = new Context();

        var query = newContext.createQuery(User.class, "SELECT u FROM User u").setMaxResults(20);
        List<User> users = query.getResultList();

        System.out.println(users);
        
        controller.usersText.setText("");

        for (User user : users) {
            controller.usersText.setText( controller.usersText.getText() + user.getName() + " - " + user.getPassword() + "\n");

        }

        return scene;
    }

    public void onButtonClick(MouseEvent e) throws Exception {

        Scene warningScene = InteractionWarningController.CreateScene("Are you sure you want to log out?",
                (Stage) logOutButton.getScene().getWindow());
        // banana.setStage( );
        Stage warningStage = new Stage();
        warningStage.setScene(warningScene);
        warningStage.show();

    }
    @FXML
    protected void registerNav(MouseEvent e) throws Exception {

        System.out.println("chamou e caiu");

        Stage crrStage = (Stage) registerPageButton.getScene().getWindow();

        System.out.println("chamou e caiu 2");

        crrStage.close();

        System.out.println("chamou e caiu 3");


        Scene nextScene = RegisterController.CreateScene(this.message);

        System.out.println("chamou e caiu 4");


        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.show();

    }

    @FXML
    protected static void getUsers(HomeController controller) {

        Context newContext = new Context();

        var query = newContext.createQuery(User.class, "SELECT from User");
        List<User> users = query.getResultList();
        

        for (User user : users) {
            
        }

    }

}
