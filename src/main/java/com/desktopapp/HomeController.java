package com.desktopapp;

import java.net.URL;

import java.util.*;

import com.desktopapp.model.Product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeController implements Initializable {

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
    protected Button newProductButton;

    @FXML
    protected TableView<Product> table;

    @FXML
    protected TableColumn<Product, Long> idCol;

    @FXML
    protected TableColumn<Product, String> nameCol;

    @FXML
    protected TableColumn<Product, Long> qtCol;

    @FXML
    protected Button employeesPageButton;


    public static Scene CreateScene(String message) throws Exception {
        URL sceneUrl = HomeController.class.getResource("Home.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        HomeController controller = loader.getController();
        controller.setMessage(message);

        controller.greetingsText.setText("Welcome, " + message + "! 😊");

        System.out.println("\n\n\n\n\n\n" + getProducts());

        if (getProducts().size() != 0) {

            controller.table.setItems(getProducts());
        }

        return scene;
    }

    public void onButtonClick(MouseEvent e) throws Exception {

        Scene warningScene = InteractionWarningController.CreateScene("Are you sure you want to log out?", (Stage) logOutButton.getScene().getWindow());
        Stage warningStage = new Stage();
        warningStage.setScene(warningScene);
        warningStage.show();

    }
    @FXML
    protected void goToEmployeesPage(MouseEvent e) throws Exception {

        Stage crrStage = (Stage) employeesPageButton.getScene().getWindow();
        crrStage.close();
        Scene nextScene = RegisterController.CreateScene(this.message);
        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.show();

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        qtCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private static ObservableList<Product> getProducts() {
        Context newContext = new Context();
        var query = newContext.createQuery(Product.class, "SELECT p FROM Product p").setMaxResults(20).getResultList();
        return FXCollections.observableArrayList(query);

    }

    @FXML
    protected void registerNewProduct() throws Exception {

        Stage crrStage = (Stage) this.employeesPageButton.getScene().getWindow();
        crrStage.close();

        Scene nextScene = RegisterProductController.CreateScene(this.message);

        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.show();
    }

}
