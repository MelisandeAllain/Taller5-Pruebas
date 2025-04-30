package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;


public class ProductoAjustadoTest {
	private Ingrediente tomate;
	private ProductoMenu hamburg;
	private ProductoAjustado hamburg2; 
	private Ingrediente queso; 

    @BeforeEach
    void setUp( ) throws Exception
    {
    	
       
        hamburg = new ProductoMenu("hamburg", 10000);
        
    }
    
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    void testGetNombre() {
        assertEquals("hamburg", hamburg.getNombre());
    }

    @Test
    void testGetPrecio() {
    	
        assertEquals(0, hamburg.getPrecio()); 


    }
    

    @Test
    void testGenerarTextoFactura() {
        String expected = "hamburg\n            10000\n";
        assertEquals(expected, hamburg.generarTextoFactura());
        

    }
    
    
}
