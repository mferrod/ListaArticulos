/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.politecnicomalaga.listaarticulos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.SQLConn;

/**
 * FXML Controller class
 *
 * @author Mariano
 */
public class ListaArticulosEditController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Button modifyDataBtn;
    @FXML
    private TextField descArtField;
    @FXML
    private TextField fabArtField;
    @FXML
    private TextField catArtField;
    @FXML
    private TextField pvpArtField;
    @FXML
    private TextField codArtField;
    
    private SQLConn connex = new SQLConn();
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void setData(String codigo){
        this.codArtField.setText("hola");
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
    private void modifyButton(ActionEvent event) {
        connex.modifyArticulos(codArtField.getText(), descArtField.getText(),
                fabArtField.getText(), catArtField.getText(), pvpArtField.getText());
        connex.closeConnectionToDB();
    }
    
}
