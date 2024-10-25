package com.desktopapp;

import java.net.URL;

import java.util.*;

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

public class EmployeesController implements Initializable {

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    protected Text greetingsText;

    @FXML
    protected Text usersText;

    @FXML
    protected Button logOutButton;

    @FXML
    protected Button newEmployeeButton;

    @FXML
    protected TableView<User> table;

    @FXML
    protected TableColumn<User, Long> idCol;

    @FXML
    protected TableColumn<User, String> nameCol;

    @FXML
    protected TableColumn<User, String> emailCol;

    @FXML
    protected Button employeesPageButton;


    public static Scene CreateScene(User user) throws Exception {
        URL sceneUrl = EmployeesController.class.getResource("Employees.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        EmployeesController controller = loader.getController();
        controller.setUser(user);

        controller.greetingsText.setText("Welcome, " + user.getName() + "! 😊");
        
        if (getEmployees().size() != 0) {

            controller.table.setItems(getEmployees());
        }

        return scene;
    }

    public void onButtonClick(MouseEvent e) throws Exception {

        var confirm = InteractionWarningController.ShowAndWait(
            "Are you sure you want to log out?"
        );

        if (confirm)
        {
            Stage crrStage = (Stage) logOutButton.getScene().getWindow();
            crrStage.close();

            Scene loginScene = LoginController.CreateScene();
            Stage newStage = new Stage();
            newStage.setScene(loginScene);
            newStage.show();
        }
    }
    @FXML
    protected void goToProductsPage(MouseEvent e) throws Exception {

        Stage crrStage = (Stage) employeesPageButton.getScene().getWindow();
        crrStage.close();
        
        Scene nextScene = ProductsController.CreateScene(user);
        Stage nextStage = new Stage();
        
        nextStage.setScene(nextScene);
        nextStage.show();

    }

    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private static ObservableList<User> getEmployees() {
        Context newContext = new Context();
        var query = newContext.createQuery(User.class, "SELECT u FROM User u").setMaxResults(20).getResultList();
        return FXCollections.observableArrayList(query);

    }

    @FXML
    protected void registerNewEmployee() throws Exception {

        Stage crrStage = (Stage) this.employeesPageButton.getScene().getWindow();
        crrStage.close();

        Scene nextScene = RegisterEmployeeController.CreateScene(user);

        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.show();
    }

}
