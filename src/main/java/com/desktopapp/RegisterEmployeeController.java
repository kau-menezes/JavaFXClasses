package com.desktopapp;

import java.net.URL;

import com.desktopapp.model.Product;
import com.desktopapp.model.User;

import jakarta.persistence.EntityManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegisterEmployeeController {

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @FXML
    protected Button logOutButton;

    @FXML
    protected Button registerButton;

    @FXML
    protected Button registerPageButton;

    @FXML
    protected Button homePageButton;

    @FXML
    protected PasswordField passwordInput;

    @FXML
    protected TextField emailInput;

    @FXML
    protected TextField nameInput;

    public static Scene CreateScene(String message) throws Exception {

        URL sceneUrl = RegisterEmployeeController.class.getResource("RegisterEmployee.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        RegisterEmployeeController controller = loader.getController();
        controller.setMessage(message);

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

    public void register(MouseEvent e) throws Exception {

        String message = "An error occured, try again.";

        if (this.emailInput.getText() == "" || this.passwordInput.getText() == "") {
            message = "Please, enter all information needed.";
        }

        Context ctx = new Context();

        User user = new User();
        user.setName(this.nameInput.getText());
        user.setPassword(this.passwordInput.getText());
        user.setEmail(this.emailInput.getText());

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

        Scene warningScene = LoginWarningController.CreateScene(message);

        Stage newStage = new Stage();
        newStage.setScene(warningScene);
        newStage.show();

    }

    public void homeNav(MouseEvent e) throws Exception {

        Stage crrStage = (Stage) this.registerPageButton.getScene().getWindow();
        crrStage.close();

        Scene nextScene = ProductsController.CreateScene(this.message);

        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.show();

    }

}
