/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ptrguitar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import newpackage.sql.Guitarra;

/**
 *
 * @author usuario
 */
public class Comprobaciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PTRGuitarPU");
            EntityManager em = emf.createEntityManager();
            
            
        Query queryGuitarra = em.createNamedQuery("Guitarra.findAll");
        List<Guitarra> listGuitarra = queryGuitarra.getResultList();
        
        for(int i=0; i<listGuitarra.size(); i++) {
            Guitarra guitarra = listGuitarra.get(i);
            System.out.println(guitarra.getModelo());
        }
        
    }
    
}
