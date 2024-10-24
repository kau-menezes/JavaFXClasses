package com.desktopapp;

import java.net.URL;

import java.util.*;

import com.desktopapp.model.Product;
import com.desktopapp.model.User;

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

public class ProductsController implements Initializable {

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    private Integer selectedId;

    public Integer getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(Integer selectedId) {
        this.selectedId = selectedId;
    }

    @FXML
    protected Text greetingsText;

    @FXML
    protected Text selectedProduct;

    @FXML
    protected Button logOutButton;

    @FXML
    protected Button newProductButton;

    @FXML
    protected Button editButton;

    @FXML
    protected Button deleteButton;

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


    public static Scene CreateScene(User user) throws Exception {
        URL sceneUrl = ProductsController.class.getResource("Home.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        ProductsController controller = loader.getController();
        controller.setUser(user);

        controller.greetingsText.setText("Welcome, " + user.getName() + "! 😊");

        System.out.println("\n\n\n\n\n\n" + getProducts());

        if (getProducts().size() != 0) {

            controller.table.setItems(getProducts());
        }

        return scene;
    }

    public void onButtonClick(MouseEvent e) throws Exception {

        Scene warningScene = InteractionWarningController.CreateScene("Are you sure you want to log out?", (Stage) logOutButton.getScene().getWindow(), LoginController.CreateScene());

        // Scene warningScene = InteractionWarningController.CreateScene("Are you sure you want to log out?", (Stage) logOutButton.getScene().getWindow());
        Stage warningStage = new Stage();
        warningStage.setScene(warningScene);
        warningStage.show();

    }
    @FXML
    protected void goToEmployeesPage(MouseEvent e) throws Exception {

        Stage crrStage = (Stage) employeesPageButton.getScene().getWindow();
        crrStage.close();
        Scene nextScene = EmployeesController.CreateScene(user);
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

    @FXML
    public void RowSelect(MouseEvent e) {
        @SuppressWarnings("unchecked")
        TablePosition<Product, ?> cell = (TablePosition<Product, ?>) table.getSelectionModel().getSelectedCells().get(0);

        if (cell != null) {
            var crrProduct = table.getItems().get(cell.getRow());
            setSelectedId(crrProduct.getId());
            selectedProduct.setText("Selected: " + getSelectedId());
            EnableActionButtons();
        }
    }

    @FXML
    public void EnableActionButtons() {
        editButton.setDisable(false);
        deleteButton.setDisable(false);
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

        Scene nextScene = RegisterProductController.CreateScene(user);

        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.show();
    }

    @FXML
    protected void editProduct() throws Exception {

        Stage crrStage = (Stage) this.employeesPageButton.getScene().getWindow();
        crrStage.close();

        Context newContext = new Context();
        var cell = newContext.createQuery(Product.class, "SELECT p FROM Product p").setMaxResults(20).getResultList().get(selectedId - 1);

        Scene nextScene = UpdateProductController.CreateScene(user, cell);

        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.show();
    }

    @FXML
    protected void deleteProduct() throws Exception {

        Context ctx = new Context();

        Context newContext = new Context();
        var cell = newContext.createQuery(Product.class, "SELECT p FROM Product p").setMaxResults(20).getResultList().get(0);

        ctx.begin();
        ctx.delete(cell);
        ctx.commit();

        table.setItems(getProducts());



    }

}
