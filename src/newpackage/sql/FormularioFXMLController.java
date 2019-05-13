/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage.sql;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

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
        StackPane rootMain = (StackPane)formularioFXML.getScene().getRoot();
        rootMain.getChildren().remove(formularioFXML);      

        rootFormulariosView.setVisible(true);
    }

    @FXML
    private void onActionButtonCancelar(ActionEvent event) {
        StackPane rootMain = (StackPane)formularioFXML.getScene().getRoot();
        rootMain.getChildren().remove(formularioFXML);      

        rootFormulariosView.setVisible(true);
    }
    
    
}
