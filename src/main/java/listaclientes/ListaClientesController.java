/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package listaclientes;

import com.politecnicomalaga.listaarticulos.App;
import com.politecnicomalaga.listaarticulos.ArticulosEditController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Cliente;
import model.SQLConn;

/**
 * FXML Controller class
 *
 * @author Mariano
 */
public class ListaClientesController implements Initializable {

    @FXML
    private Pane paneButtons;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnModify;
    @FXML
    private TableView<Cliente> tabla;
    private ObservableList<Cliente> listaObservable = FXCollections.observableArrayList();
    private SQLConn connex = new SQLConn();
    @FXML
    private Label labelDni;
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelApell1;
    @FXML
    private Label labelApell2;
    @FXML
    private Label labelTelef;
    @FXML
    private Button buttonChangeToArts;
    @FXML
    private TableColumn<Cliente, String> coldni;
    @FXML
    private TableColumn<Cliente, String> colnom;
    @FXML
    private TableColumn<Cliente, String> colapell1;
    @FXML
    private TableColumn<Cliente, String> colapell2;
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.addDataToList();
        coldni.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDNI()));
        colnom.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colapell1.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getApellido1()));
        colapell2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getApellido2()));
        tabla.setItems(listaObservable);
    }    

    private void addDataToList() {
        ArrayList<String[]> e = connex.getClientes();
        for (int i = 0; i < connex.getCountRows("clientes"); i++)
        {
            Cliente c = new Cliente(Integer.valueOf(e.get(i)[0]), e.get(i)[1], e.get(i)[2],
                                    e.get(i)[3], e.get(i)[4], e.get(i)[5]);
            listaObservable.add(c);
        }
    }
    
    @FXML
    private void mostrarDatos(MouseEvent event) {
        TablePosition pos = (TablePosition) tabla.getSelectionModel().getSelectedCells().get(0);
        int index = pos.getRow();
        int empiezo = 0;
        String selected = tabla.getItems().get(index).toString();
        String[] s = new String[5];
        for (int i = 0; i < 5; i++)
        {
            //System.out.println("" + i + "pos cursor");
            if (i == 4)
                s[i] = selected.substring(empiezo, selected.length());
            else
                s[i] = selected.substring(empiezo, selected.indexOf("$"));
            //System.out.println("" + empiezo + " " + selected.indexOf(","));
            empiezo = 0;
            //System.out.println("" + empiezo);
            selected = selected.substring(selected.indexOf("$") + 1, selected.length());
            //System.out.println(selected);
        }
        labelDni.setText(s[0]);
        labelNombre.setText(s[1]);
        labelApell1.setText(s[2]);
        labelApell2.setText(s[3]);
        labelTelef.setText(s[4]);
    }
    
    @FXML
    private void addBtnFnc(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("clientesAdd.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void remBtnFnc(ActionEvent event) {
        TablePosition pos = (TablePosition) tabla.getSelectionModel().getSelectedCells().get(0);
        int index = pos.getRow();
        String selected = tabla.getItems().get(index).getIdCliente();
        if (Integer.parseInt(selected) >= 0) {
            Alert alertaConf = new Alert(Alert.AlertType.CONFIRMATION);
            alertaConf.setHeaderText(null);
            alertaConf.setContentText("¿Estás seguro que quieres borrar este cliente?");
            Optional<ButtonType> action = alertaConf.showAndWait();
            if (action.get() == ButtonType.OK) {
                connex.removeCliente(selected);
                listaObservable.remove(tabla.getItems().get(index));
            } else 
                System.out.println("ACCIÓN DENEGADA");
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ATENCIÓN");
            alerta.setHeaderText("CLIENTE NO SELECCIONADO");
            alerta.setContentText("POR FAVOR, SELECCIONE UN CLIENTE DE LA TABLA");
            alerta.showAndWait();
        }
    }

    @FXML
    private void modBtnFnc(ActionEvent event) throws IOException {
        TablePosition pos2 = (TablePosition) tabla.getSelectionModel().getSelectedCells().get(0);
        int index2 = pos2.getRow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("clientesEdit.fxml"));
        root = loader.load();
        ClientesEditController laec = loader.getController();
        laec.setData(listaObservable.get(index2).getDNI(),
                listaObservable.get(index2).getNombre(),
                listaObservable.get(index2).getApellido1(),
                listaObservable.get(index2).getApellido2(),
                String.valueOf(listaObservable.get(index2).getTelefono()),
                listaObservable.get(index2).getIdCliente()
        );
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }    

    @FXML
    private void changeToArts(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("listaArticulos.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
