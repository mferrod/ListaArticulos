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
public class ClientesEditController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Button modifyDataBtn;

    @FXML
    private TextField nomField;
    @FXML
    private TextField ape1Field;
    @FXML
    private TextField ape2Field;
    @FXML
    private TextField telefonoField;
    @FXML
    private TextField dniField;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private SQLConn connex = new SQLConn();
    private String idcli;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setData(String dni, String nombre, String ape1, String ape2, String telef, String idcl){
        dniField.setText(dni);
        nomField.setText(nombre);
        ape1Field.setText(ape1);
        ape2Field.setText(ape2);
        telefonoField.setText(telef);
        idcli = idcl;
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
    private void modifyButton(ActionEvent event) throws IOException {
            Alert alertaConf = new Alert(Alert.AlertType.CONFIRMATION);
            alertaConf.setHeaderText(null);
            alertaConf.setContentText("¿Estás seguro que quieres modificar este cliente?");
            Optional<ButtonType> action = alertaConf.showAndWait();
            if (action.get() == ButtonType.OK) {
                connex.modifyCliente(dniField.getText(), nomField.getText(),
                ape1Field.getText(), ape2Field.getText(), telefonoField.getText(), idcli);
                backButton(event);
            } else 
                System.out.println("ACCIÓN DENEGADA");
    }
    
}
