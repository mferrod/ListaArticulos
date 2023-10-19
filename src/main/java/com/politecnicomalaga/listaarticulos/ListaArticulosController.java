/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.politecnicomalaga.listaarticulos;

import java.io.IOException;
import model.Articulo;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.SQLConn;

/**
 * FXML Controller class
 *
 * @author Mariano
 */
public class ListaArticulosController implements Initializable {
    
    @FXML
    private TextField codArtField;
    @FXML
    private TextField descArtField;
    @FXML
    private TextField fabArtField;
    @FXML
    private TextField catArtField;
    @FXML
    private TextField pvpArtField;
    
    @FXML
    private Pane paneButtons;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnModify;
    @FXML
    private TableView<Articulo> tabla;
    @FXML
    private Label labelPrecio;
    @FXML
    private Label labelCategoria;
    @FXML
    private Label labelCodigo;
    @FXML
    private Label labelDesc;
    @FXML
    private Label labelFabri;
    ObservableList<Articulo> listaObservable = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Articulo, String> colcod;
    @FXML
    private TableColumn<Articulo, String> coldesc;
    @FXML
    private TableColumn<Articulo, String> colfab;
    @FXML
    private TableColumn<Articulo, String> colcat;
    private SQLConn connex = new SQLConn();
    private Parent root;
    private Scene scene;
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.addDataToList();
        colcod.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCodigoArt()));
        coldesc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescArt()));
        colfab.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFabricanteArt()));
        colcat.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCatArt()));
        tabla.setItems(listaObservable);
    }
    
    private void addDataToList() {
        ArrayList<String[]> e = connex.getArticulos();
        for (int i = 0; i < connex.getCountRowsArticulos(); i++)
        {
            Articulo a = new Articulo(e.get(i)[0], e.get(i)[1], e.get(i)[2],
                    e.get(i)[3], Integer.valueOf(e.get(i)[4]));
            listaObservable.add(a);
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
        labelCodigo.setText(s[0]);
        labelDesc.setText(s[1]);
        labelFabri.setText(s[2]);
        labelCategoria.setText(s[3]);
        labelPrecio.setText(s[4]);
    }

    @FXML
    private void addBtnFnc(ActionEvent event) throws IOException {
        App.setRoot("articulosAdd");
    }

    @FXML
    private void remBtnFnc(ActionEvent event) {
        TablePosition pos = (TablePosition) tabla.getSelectionModel().getSelectedCells().get(0);
        int index = pos.getRow();
        String selected = tabla.getItems().get(index).getCodigoArt();
        if (Integer.parseInt(selected) >= 0) {
            connex.removeArticulos(selected);
            listaObservable.remove(tabla.getItems().get(index));
        } else {
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("ATENCIÓN");
            alerta.setHeaderText("PERSONA NO SELECCIONADA");
            alerta.setContentText("POR FAVOR, SELECCIONE UNA PERSONA DE LA TABLA");
            alerta.showAndWait();
        }
    }

    @FXML
    private void modBtnFnc(ActionEvent event) throws IOException {
        TablePosition pos2 = (TablePosition) tabla.getSelectionModel().getSelectedCells().get(0);
        int index2 = pos2.getRow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("articulosEdit.fxml"));
        root = loader.load();
        ArticulosEditController laec = loader.getController();
        laec.setData(listaObservable.get(index2).getCodigoArt(),
                listaObservable.get(index2).getDescArt(),
                listaObservable.get(index2).getFabricanteArt(),
                listaObservable.get(index2).getCatArt(),
                String.valueOf(listaObservable.get(index2).getPrecioArt())
        ); //--> El fallo está aquí
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
