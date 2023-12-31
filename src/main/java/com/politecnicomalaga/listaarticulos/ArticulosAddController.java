/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.politecnicomalaga.listaarticulos;

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
public class ArticulosAddController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Button insertDataBtn;
    
    private SQLConn connex = new SQLConn();
    @FXML
    private TextField descArtField;
    @FXML
    private TextField fabArtField;
    @FXML
    private TextField catArtField;
    @FXML
    private TextField pvpArtField;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listaArticulos.fxml"));
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
        alertaConf.setContentText("¿Estás seguro que quieres añadir esta información para este artículo?");
        Optional<ButtonType> action = alertaConf.showAndWait();
        if (action.get() == ButtonType.OK)
        connex.addArticulos(descArtField.getText(), fabArtField.getText(),
                catArtField.getText(), pvpArtField.getText());
        else
            System.out.println("OPERACIÓN CANCELADA");
    }
    
}
