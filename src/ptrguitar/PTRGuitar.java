/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ptrguitar;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author usuario
 */
public class PTRGuitar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            // Conectar con la base de da*tos
            Map<String, String> emfProperties = new HashMap<String, String>();
            emfProperties.put("javax.persistence.schema-generation.database.action", "create");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PTRGuitarPU", emfProperties);
            EntityManager em = emf.createEntityManager();
            
            // REALIZAR AQUÍ LAS OPERACIONES SOBRE LA BASE DE DATOS
            
            //Fabricante 1
            MetodosOperacioes metodosOperaciones = new MetodosOperacioes();
            em.getTransaction().begin();
            em.persist(metodosOperaciones.fabricante1());
            //Guitarra1
            em.persist(metodosOperaciones.guitarra1());
            em.getTransaction().commit();
            
            
            // Cerrar la conexión con la base de datos
            em.close(); 
            emf.close(); 
            try { 
                DriverManager.getConnection("jdbc:derby:Database;shutdown=true"); 
            } catch (SQLException ex) { 
            }
            
    }
    
}
