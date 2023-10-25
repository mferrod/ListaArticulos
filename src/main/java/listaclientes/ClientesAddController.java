/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package listaclientes;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.SQLConn;

/**
 * FXML Controller class
 *
 * @author Mariano
 */
public class ClientesAddController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Button insertDataBtn;
    @FXML
    private TextField nomField;
    @FXML
    private TextField ape1Field;
    @FXML
    private TextField ape2Field;
    @FXML
    private TextField telefField;
    @FXML
    private TextField dniField;
    private SQLConn connex = new SQLConn();
    private Parent root;
    private Scene scene;
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listaClientes.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void insertData(ActionEvent event) {
        Alert alertaConf = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConf.setHeaderText(null);
        alertaConf.setContentText("¿Estás seguro que quieres añadir esta información para este cliente?");
        Optional<ButtonType> action = alertaConf.showAndWait();
        if (action.get() == ButtonType.OK)
        connex.addCliente(nomField.getText(), ape1Field.getText(),
                ape2Field.getText(), telefField.getText(), dniField.getText());
        else
            System.out.println("OPERACIÓN CANCELADA");
    }
    
}
