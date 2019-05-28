/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage.sql;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
        // Asocia las columnas a las propides de la clase entidad
        columnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
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
            //Si los campos de la guitarra que se a seleccionado no son nulos se añadiran en los campos de abajo
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
    //Carga un array con las guitarras de la base de datos
    public void cargarTodasGuitarras() {
        Query queryGuitarraFindAll = entityManager.createNamedQuery("Guitarra.findAll");
        List<Guitarra> listGuitarra = queryGuitarraFindAll.getResultList();
        tablaViewModelos.setItems(FXCollections.observableArrayList(listGuitarra));
    }  

    @FXML
    
    private void onActionButtonGuardar(ActionEvent event) {
        // Si se ha seleccionado un modelo se cogera los datos de los campos de texto e iniciara una transancion para guardar los datos en la base de datos
         if (modeloSeleccionado != null) {
            modeloSeleccionado.setModelo(textFieldModelo.getText());
            modeloSeleccionado.setMadera(textFieldMadera.getText());
            modeloSeleccionado.setTipo(textFieldTipo.getText());
            entityManager.getTransaction().begin();
            entityManager.merge(modeloSeleccionado);
            entityManager.getTransaction().commit();
            //Crea una variable numFilaSeleccionada para almacenar el numero de la fila seleccionada
            int numFilaSeleccionada = tablaViewModelos.getSelectionModel().getSelectedIndex();
            //Mostrara los datos guardados a la colimna seleccionada
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
            formularioFXMLController.setTableViewPrevio(tablaViewModelos);
            modeloSeleccionado = new Guitarra();
            formularioFXMLController.setGuitarra(entityManager, modeloSeleccionado, true);
            //Metodo que mostrara los datos en el formulario(en este caso solo para mostrar la lista de fabricantes)
            formularioFXMLController.mostrarDatos();
            
            // Ocultar la vista de la lista guitarras
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
        if(modeloSeleccionado != null) {
            try {
               // Cargar la vista de detalle
               FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormularioFXML.fxml"));
               Parent rootDetalleView = fxmlLoader.load();  
               FormularioFXMLController formularioFXMLController = (FormularioFXMLController) fxmlLoader.getController();  
               formularioFXMLController.setRootFormularioView(rootGuitarrasView);
               formularioFXMLController.setTableViewPrevio(tablaViewModelos);
               formularioFXMLController.setGuitarra(entityManager, modeloSeleccionado, false);

               // Ocultar la vista de la lista
               rootGuitarrasView.setVisible(false);
               formularioFXMLController.mostrarDatos();
               // Añadir la vista de detalle al StackPane principal para que se muestre
               StackPane rootMain = (StackPane)rootGuitarrasView.getScene().getRoot();
               rootMain.getChildren().add(rootDetalleView);
           } catch (IOException ex) {
               Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
           }
         } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setHeaderText("Debe seleccionar un registro");
            alert.showAndWait();
        }
    }

    @FXML
    private void onActionButtonSuprimir(ActionEvent event) {
          if(modeloSeleccionado != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar");
            alert.setHeaderText("¿Desea suprimir el siguiente registro?");
            alert.setContentText(modeloSeleccionado.getModelo() + " "
                    + modeloSeleccionado.getTipo());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                entityManager.getTransaction().begin();
                entityManager.remove(modeloSeleccionado);
                entityManager.getTransaction().commit();
                tablaViewModelos.getItems().remove(modeloSeleccionado);
                tablaViewModelos.getFocusModel().focus(null);
                tablaViewModelos.requestFocus();
            } else {
                int numFilaSeleccionada = tablaViewModelos.getSelectionModel().getSelectedIndex();
                tablaViewModelos.getItems().set(numFilaSeleccionada, modeloSeleccionado);
                TablePosition pos = new TablePosition(tablaViewModelos, numFilaSeleccionada, null);
                tablaViewModelos.getFocusModel().focus(pos);
                tablaViewModelos.requestFocus();            
            }
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setHeaderText("Debe seleccionar un registro");
            alert.showAndWait();
        }
    }
}

