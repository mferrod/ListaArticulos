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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.SQLConn;

/**
 * FXML Controller class
 *
 * @author Mariano
 */
public class ListaArticulosAddController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backButton(ActionEvent event) throws IOException {
        App.setRoot("listaArticulos");
    }

    @FXML
    private void insertData(ActionEvent event) {
        connex.addArticulos(descArtField.getText(), fabArtField.getText(),
                catArtField.getText(), pvpArtField.getText());
        connex.closeConnectionToDB();
    }
    
}
