package com.desktopapp;

import java.net.URL;

import com.desktopapp.model.User;

import jakarta.persistence.EntityManager;
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

    // protected HomeController controller;

    // public void setController(HomeController controller) {
    // this.controller = controller;
    // }

    @FXML
    protected Text greetingsText;

    @FXML
    protected Button logOutButton;

    @FXML
    protected Button registerButton;

    @FXML
    protected PasswordField passwordInput;

    @FXML
    protected TextField emailInput;

    public static Scene CreateScene(String message) throws Exception {
        URL sceneUrl = LoginWarningController.class.getResource("Home.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        LoginWarningController controller = loader.getController();
        controller.setMessage(message);

        controller.messageText.setText(message);

        // deu erro aqui ó 
        // controller.greetingsText.setText(message);

        System.out.println(controller.message);

        return scene;
    }

    public void onButtonClick(MouseEvent e) throws Exception {

        // URL sceneUrl =
        // InteractionWarningController.class.getResource("InteractionWarning.fxml");
        // FXMLLoader loader = new FXMLLoader(sceneUrl);
        // InteractionWarningController banana = loader.getController();

        Scene warningScene = InteractionWarningController.CreateScene("Are you sure you want to log out?",
                (Stage) logOutButton.getScene().getWindow());
        // banana.setStage( );
        Stage warningStage = new Stage();
        warningStage.setScene(warningScene);
        warningStage.show();

    }

    public void register(MouseEvent e) throws Exception {

        String message = "An error occured, try again.";

        if (this.emailInput.getText() == "" || this.passwordInput.getText() == "") {
            message = "Please, enter all information needed.";
        }

        Context ctx = new Context();

        User user = new User();
        user.setName(this.emailInput.getText());
        user.setPassword(this.passwordInput.getText());

        EntityManager em = ctx.creaEntityManager();

        try {
            em.getTransaction().begin();
            ctx.begin();
            ctx.save(user);
            ctx.commit();
            message = "New empolyee inserted succesfully! 😊";

        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            ex.printStackTrace();
            em = null;

        } finally {
            em.close();
        }

        // implement try-catch

        Scene warningScene = LoginWarningController.CreateScene(message);

        Stage newStage = new Stage();
        newStage.setScene(warningScene);
        newStage.show();

    }

}
