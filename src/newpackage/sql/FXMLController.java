/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage.sql;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author usuario
 */
public class FXMLController implements Initializable {
    //Variable seleccion
    private Guitarra modeloSeleccionado;
    private EntityManager entityManager;
    @FXML
    private TableView<Guitarra> tablaViewModelos;
    @FXML
    private TableColumn<Guitarra, String> columnModelo;
    @FXML
    private TableColumn<Guitarra, String> columnFabricante;
    @FXML
    private TableColumn<Guitarra, String> columnMadera;
    @FXML
    private TableColumn<Guitarra, String> columnTipo;
    @FXML
    private TextField textFieldModelo;
    @FXML
    private TextField textFieldMadera;
    @FXML
    private TextField textFieldTipo;
    @FXML
    private AnchorPane rootGuitarrasView;
    
    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        columnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        columnFabricante.setCellValueFactory(new PropertyValueFactory<>("fabricante"));
        columnMadera.setCellValueFactory(new PropertyValueFactory<>("madera"));
        columnTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        //Nombre del fabricante asociado
        columnFabricante.setCellValueFactory(
        cellData -> {
        SimpleStringProperty property = new SimpleStringProperty();
        if (cellData.getValue().getFabricante()!= null) {
            property.setValue(cellData.getValue().getFabricante().getNombre());
        }
        return property;
        });
        tablaViewModelos.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            modeloSeleccionado = newValue;
            if (modeloSeleccionado != null) {
                textFieldModelo.setText(modeloSeleccionado.getModelo());
                textFieldMadera.setText(modeloSeleccionado.getMadera());
                textFieldTipo.setText(modeloSeleccionado.getTipo());
            } else {
                textFieldModelo.setText("");
                textFieldMadera.setText("");
                textFieldTipo.setText("");

            }
        });
    }    
    public void cargarTodasGuitarras() {
        Query queryGuitarraFindAll = entityManager.createNamedQuery("Guitarra.findAll");
        List<Guitarra> listGuitarra = queryGuitarraFindAll.getResultList();
        tablaViewModelos.setItems(FXCollections.observableArrayList(listGuitarra));
    }  

    @FXML
    private void onActionButtonGuardar(ActionEvent event) {
         if (modeloSeleccionado != null) {
            modeloSeleccionado.setModelo(textFieldModelo.getText());
            modeloSeleccionado.setMadera(textFieldMadera.getText());
            modeloSeleccionado.setTipo(textFieldTipo.getText());
            entityManager.getTransaction().begin();
            entityManager.merge(modeloSeleccionado);
            entityManager.getTransaction().commit();

            int numFilaSeleccionada = tablaViewModelos.getSelectionModel().getSelectedIndex();
            tablaViewModelos.getItems().set(numFilaSeleccionada, modeloSeleccionado);
            TablePosition pos = new TablePosition(tablaViewModelos, numFilaSeleccionada, null);
            tablaViewModelos.getFocusModel().focus(pos);
            tablaViewModelos.requestFocus();
        }
    }

    @FXML
    private void onActionButtonNuevo(ActionEvent event) {
        try {
    // Cargar la vista de detalle
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormularioFXML.fxml"));
            Parent rootDetalleView = fxmlLoader.load();     
            
            
            FormularioFXMLController formularioFXMLController = (FormularioFXMLController) fxmlLoader.getController();  
            formularioFXMLController.setRootFormularioView(rootGuitarrasView);
            
            
            // Ocultar la vista de la lista
            rootGuitarrasView.setVisible(false);

            // Añadir la vista de detalle al StackPane principal para que se muestre
            StackPane rootMain = (StackPane)rootGuitarrasView.getScene().getRoot();
            rootMain.getChildren().add(rootDetalleView);
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void onActionButtonEditar(ActionEvent event) {
         try {
            // Cargar la vista de detalle
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormularioFXML.fxml"));
            Parent rootDetalleView = fxmlLoader.load();     
            
            
            
            FormularioFXMLController formularioFXMLController = (FormularioFXMLController) fxmlLoader.getController();  
            formularioFXMLController.setRootFormularioView(rootGuitarrasView);
            
            
            
            // Ocultar la vista de la lista
            rootGuitarrasView.setVisible(false);

            // Añadir la vista de detalle al StackPane principal para que se muestre
            StackPane rootMain = (StackPane)rootGuitarrasView.getScene().getRoot();
            rootMain.getChildren().add(rootDetalleView);
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onActionButtonSuprimir(ActionEvent event) {
    }
}

