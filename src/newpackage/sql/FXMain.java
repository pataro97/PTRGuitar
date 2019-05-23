/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage.sql;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author usuario
 */
public class FXMain extends Application {
    private EntityManagerFactory emf;
    private EntityManager em;
    StackPane rootMain = new StackPane();
    @Override
    public void start(Stage primaryStage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML.fxml"));
        Pane rootFXML = fxmlLoader.load();
        rootMain.getChildren().add(rootFXML);

        emf = Persistence.createEntityManagerFactory("PTRGuitarPU");
        em = emf.createEntityManager();

        FXMLController fxmlController = (FXMLController) fxmlLoader.getController();                
        fxmlController.setEntityManager(em);
        fxmlController.cargarTodasGuitarras();
        
        Scene scene = new Scene(rootMain, 650, 600);

        primaryStage.setTitle("PTR Guitar");
        primaryStage.setScene(scene);
        primaryStage.show();                
    }

    @Override
    public void stop() throws Exception {
        em.close(); 
        emf.close(); 
        try { 
            DriverManager.getConnection("jdbc:derby:PTRGuitar;shutdown=true"); 
        } catch (SQLException ex) { 
        }        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
