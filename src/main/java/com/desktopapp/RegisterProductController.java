package com.desktopapp;

import java.net.URL;

import com.desktopapp.model.Product;
import com.desktopapp.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegisterProductController {

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    protected Button logOutButton;

    @FXML
    protected Button registerButton;

    @FXML
    protected Button employeesPageButton;

    @FXML
    protected Button productsPageButton;

    @FXML
    protected TextField qtInput;

    @FXML
    protected TextField nameInput;

    public static Scene CreateScene(User user) throws Exception {

        URL sceneUrl = RegisterProductController.class.getResource("RegisterProduct.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        RegisterProductController controller = loader.getController();
        controller.setUser(user);

        return scene;
    }

    public void onButtonClick(MouseEvent e) throws Exception {

        // Scene warningScene = InteractionWarningController.CreateScene("Are you sure you want to log out?", (Stage) logOutButton.getScene().getWindow());

        // Scene warningScene = InteractionWarningController.CreateScene("Are you sure you want to log out?", (Stage) logOutButton.getScene().getWindow(), LoginController.CreateScene());

        // // banana.setStage( );
        // Stage warningStage = new Stage();
        // warningStage.setScene(warningScene);
        // warningStage.show();

    }

    public void register(MouseEvent e) throws Exception {

        String message = "An error occured, try again.";

        Context ctx = new Context();

        Product product = new Product();
        product.setName(this.nameInput.getText());
        product.setQuantity(Long.parseLong(this.qtInput.getText()));
        // EntityManager em = ctx.creaEntityManager();

        // try {
            // em.getTransaction().begin();
            ctx.begin();
            ctx.save(product);
            ctx.commit();
            message = "New product inserted succesfully! 😊";

        // } catch (Exception ex) {
        //     if (em.getTransaction().isActive()) {
        //         em.getTransaction().rollback();
        //     }

        //     ex.printStackTrace();
        //     em = null;

        // } finally {
        //     em.close();
        // }

        Scene warningScene = LoginWarningController.CreateScene(message);

        Stage newStage = new Stage();
        newStage.setScene(warningScene);
        newStage.show();

    }

    public void homeNav(MouseEvent e) throws Exception {

        Stage crrStage = (Stage) this.employeesPageButton.getScene().getWindow();
        crrStage.close();

        Scene nextScene = ProductsController.CreateScene(user);

        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.show();

    }

}
