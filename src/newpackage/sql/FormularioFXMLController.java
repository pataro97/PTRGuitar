/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage.sql;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author usuario
 */
public class FormularioFXMLController implements Initializable {

    @FXML
    private TextField modeloTextField;
    @FXML
    private TextField maderaTextField;
    @FXML
    private TextField precioTextField;
    @FXML
    private ComboBox<?> comboFabricante;
    @FXML
    private CheckBox checkStock;
    @FXML
    private RadioButton radioStratoCaster;
    @FXML
    private RadioButton radioTelecaster;
    @FXML
    private RadioButton radioLesPaul;
    @FXML
    private RadioButton radioAcustica;
    @FXML
    private RadioButton radioOtros;
    private Pane rootFormulariosView;
    @FXML
    private AnchorPane formularioFXML;
    
    private TableView guitarraView;
    private Guitarra guitarra;
    private EntityManager entityManager;
    private boolean nuevaGuitarra;
    
    public static final String STRATOCASTER = "Stratocaster";
    public static final String LESPAUL = "LesPaul";
    public static final String TELECASTER = "Telecaster";
    public static final String ACUSTICA = "Acustica";
    public static final String OTROS = "Otros";
    @FXML
    private ToggleGroup tipoGroup;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setRootFormularioView(Pane rootFormulariosView) {
        this.rootFormulariosView = rootFormulariosView;
    }
    @FXML
    private void onActionButtonGuardar(ActionEvent event) {
        //Guardar 
        int numFilaSeleccionada;
        //Almacenar los datos de los campos del formulario
        guitarra.setModelo(modeloTextField.getText());
        guitarra.setMadera(maderaTextField.getText());
        //Ahora hay que hacer lo contrario pasar de string a big decimal
        guitarra.setPrecio(BigDecimal.valueOf(Double.valueOf(precioTextField.getText())));
        //Guardar radio button
        if (radioStratoCaster.isSelected()) {
            guitarra.setTipo(STRATOCASTER);
        } else if (radioLesPaul.isSelected()) {
            guitarra.setTipo(LESPAUL);
        } else if (radioTelecaster.isSelected()) {
            guitarra.setTipo(TELECASTER);
        } else if (radioAcustica.isSelected()) {
            guitarra.setTipo(ACUSTICA);
        } else if (radioOtros.isSelected()) {
            guitarra.setTipo(OTROS);
        }
        //Guardar Check
        guitarra.setStock(checkStock.isSelected());
        
        if(nuevaGuitarra) {
            entityManager.persist(guitarra);
        } else {
            entityManager.merge(guitarra);
        }
        entityManager.getTransaction().commit();
        System.out.println(nuevaGuitarra);
        if(nuevaGuitarra) {
            guitarraView.getItems().add(guitarra);
            numFilaSeleccionada = guitarraView.getItems().size() - 1;
            guitarraView.getSelectionModel().select(numFilaSeleccionada);
            guitarraView.scrollTo(numFilaSeleccionada);
        } else {
            numFilaSeleccionada = guitarraView.getSelectionModel().getSelectedIndex();
            guitarraView.getItems().set(numFilaSeleccionada, guitarra);
        }
        
        StackPane rootMain = (StackPane)formularioFXML.getScene().getRoot();
        rootMain.getChildren().remove(formularioFXML);      

        rootFormulariosView.setVisible(true);
        
        
        
        
       
        TablePosition pos = new TablePosition(guitarraView, numFilaSeleccionada, null);
        guitarraView.getFocusModel().focus(pos);
        guitarraView.requestFocus();
    }

    @FXML
    private void onActionButtonCancelar(ActionEvent event) {
        StackPane rootMain = (StackPane)formularioFXML.getScene().getRoot();
        rootMain.getChildren().remove(formularioFXML);      

        rootFormulariosView.setVisible(true);
        
        //Anular la transaccion
        entityManager.getTransaction().rollback();

        int numFilaSeleccionada = guitarraView.getSelectionModel().getSelectedIndex();
        TablePosition pos = new TablePosition(guitarraView, numFilaSeleccionada, null);
        guitarraView.getFocusModel().focus(pos);
        guitarraView.requestFocus();
    }
    
    
    public void setTableViewPrevio(TableView guitarraView) {
        this.guitarraView = guitarraView;
    }
    
    
    
    public void setGuitarra(EntityManager entityManager, Guitarra guitarra, boolean nuevaGuitarra) {
    this.entityManager = entityManager;
    entityManager.getTransaction().begin();
    if(!nuevaGuitarra) {
        this.guitarra = entityManager.find(Guitarra.class, guitarra.getIdGuitarra());
    } else {
        this.guitarra = guitarra;
    }
    this.nuevaGuitarra = nuevaGuitarra;
}
    public void mostrarDatos() {
        modeloTextField.setText(guitarra.getModelo());
        maderaTextField.setText(guitarra.getMadera());
        //Pasar bigDecimal a string
        precioTextField.setText(String.valueOf(guitarra.getPrecio()));
        //CheckBox
        if (guitarra.getStock() != null) {
            checkStock.setSelected(guitarra.getStock());
        }
        //RadioButton
        if (guitarra.getTipo() != null) {
            switch (guitarra.getTipo()) {
                case STRATOCASTER:
                    radioStratoCaster.setSelected(true);
                    break;
                case LESPAUL:
                    radioLesPaul.setSelected(true);
                    break;
                case TELECASTER:
                    radioTelecaster.setSelected(true);
                    break;
                case ACUSTICA:
                    radioAcustica.setSelected(true);
                    break;
                case OTROS:
                    radioOtros.setSelected(true);
                    break;
            }
            
}
}
    
    
}
