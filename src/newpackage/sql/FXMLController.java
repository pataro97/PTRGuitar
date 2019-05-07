/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage.sql;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author usuario
 */
public class FXMLController implements Initializable {

    private EntityManager entityManager;
    @FXML
    private TableView<Guitarra> tablaViewModelos;
    @FXML
    private TableColumn<Guitarra, Integer> columnIDGUITARRA;
    @FXML
    private TableColumn<Guitarra, String> columnModelo;
    @FXML
    private TableColumn<Guitarra, String> columnFabricante;
    
    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        columnIDGUITARRA.setCellValueFactory(new PropertyValueFactory<>("idGuitarra"));
        columnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        columnFabricante.setCellValueFactory(new PropertyValueFactory<>("fabricante"));
        //Nombre del fabricante asociado
        columnFabricante.setCellValueFactory(
        cellData -> {
        SimpleStringProperty property = new SimpleStringProperty();
        if (cellData.getValue().getFabricante()!= null) {
            property.setValue(cellData.getValue().getFabricante().getNombre());
        }
        return property;
    });
        
        
    }    
    public void cargarTodasGuitarras() {
        Query queryGuitarraFindAll = entityManager.createNamedQuery("Guitarra.findAll");
        List<Guitarra> listGuitarra = queryGuitarraFindAll.getResultList();
        tablaViewModelos.setItems(FXCollections.observableArrayList(listGuitarra));
    }  
}
