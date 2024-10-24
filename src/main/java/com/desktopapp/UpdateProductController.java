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

public class UpdateProductController {

    private Product product;

    public Product getProduct() {
        return product;
    }

    public User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public static Scene CreateScene(User user, Product product) throws Exception {

        URL sceneUrl = UpdateProductController.class.getResource("UpdateProduct.fxml");

        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        UpdateProductController controller = loader.getController();
        controller.setProduct(product);
        controller.setUser(user);

        controller.qtInput.setText(product.getQuantity().toString());
        controller.nameInput.setText(product.getName());

        return scene;
    }

    public void onButtonClick(MouseEvent e) throws Exception {

        // Scene warningScene = InteractionWarningController.CreateScene("Are you sure you want to log out?", (Stage) logOutButton.getScene().getWindow());

        Scene warningScene = InteractionWarningController.CreateScene("Are you sure you want to log out?", (Stage) logOutButton.getScene().getWindow(), LoginController.CreateScene());

        // banana.setStage( );
        Stage warningStage = new Stage();
        warningStage.setScene(warningScene);
        warningStage.show();

    }

    public void update(MouseEvent e) throws Exception {

        String message = "An error occured, try again.";

        Context ctx = new Context();

        product.setName(nameInput.getText());
        product.setQuantity(Long.parseLong(qtInput.getText()));

        ctx.begin();
        ctx.update(product);
        ctx.commit();

        message = "Product updated successfully! 😊";

        Scene warningScene = LoginWarningController.CreateScene(message);

        Stage crrStage = (Stage) registerButton.getScene().getWindow();
        crrStage.close();

        Scene nextScene = ProductsController.CreateScene(user);

        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.show();

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
