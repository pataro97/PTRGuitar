/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ptrguitar;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import newpackage.sql.Fabricante;
import newpackage.sql.Guitarra;

/**
 *
 * @author usuario
 */
public class MetodosOperacioes {
    Fabricante fabricante = new Fabricante(0, "");
    Guitarra guitarra = new Guitarra(0,"");
    
    public Fabricante fabricante1 (){
        //Nombre
        fabricante.setNombre("Fender");
        // Fecha
         try {
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1946");
            fabricante.setFechaCreacion(date1);
        } catch (ParseException ex) {}
        //Foto
        fabricante.setFoto("https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Fender_guitars_logo.svg/320px-Fender_guitars_logo.svg.png");
        //Descripcion
        fabricante.setHistoria("Fender Musical Instruments Corporation, inicialmente llamada Fender Electric Instrument Manufacturing Company, fue fundada por Leo Fender en la década de 1940, siendo una de las más conocidas empresas fabricantes de guitarras y bajos eléctricos."); 
        return fabricante;
    }
    public Guitarra guitarra1 (){
        guitarra.setModelo("Player Series Strat MN PWT");
        guitarra.setFabricante(fabricante);
        guitarra.setTipo("Stratocaster");
        //Fecha
        try {
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2018");
            guitarra.setAño(date1);
        } catch (ParseException ex) {}
        //-------------
        guitarra.setFoto("https://www.euro-unit.com/images/stories/virtuemart/product/0144502515.png");
        guitarra.setStock(Boolean.TRUE);
        guitarra.setMadera("Aliso y Arce");
        guitarra.setFabricante(fabricante);
        guitarra.setPrecio(new BigDecimal("575.99"));
        return guitarra;
    }
}
