package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Combo;

public class ComboTest {

	private ProductoMenu papas;
	private ProductoMenu hamburg;
	private ProductoMenu bebida;

	
	private Combo combo; 
	private Combo combo2; 
	private Combo emptycombo; 

    @BeforeEach
    void setUp( ) throws Exception
    {
        papas = new ProductoMenu( "Papas", 4000 );
        hamburg = new ProductoMenu( "Hamburger", 10000 );
        bebida = new ProductoMenu( "Bebida", 2000 );

        
        ArrayList<ProductoMenu> items = new ArrayList<ProductoMenu>(); 
        ArrayList<ProductoMenu> empty = new ArrayList<ProductoMenu>(); 

        items.add(hamburg); 
        items.add(papas);
        
        ArrayList<ProductoMenu> items2 = new ArrayList<ProductoMenu>(); 
        items2 = items; 
        items2.add(bebida); 
        combo = new Combo("Menu Hamburguesa", 0.80, items);
        combo2 = new Combo("Menu Hamburguesa y Bebida", 0.9, items2);
        emptycombo = new Combo("Empty", 0, empty); 
     
  
    } 
        
    
    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    void testGetNombre() {
        assertEquals("Menu Hamburguesa", combo.getNombre());
    }

    @Test
    void testGetPrecio() {
    	// (10000 + 4000 )*0.8 = 13200
    	// (10000 + 4000 + 2000)*0.9 = 14400
        assertEquals(13200, combo.getPrecio());
        assertEquals(14400, combo2.getPrecio());
        assertEquals(0, emptycombo.getPrecio()); 


    }
    

    @Test
    void testGenerarTextoFactura() {
        String expected = "Menu Hamburguesa \n" +
                          " Descuento: 0.8\n" +
                          "            13200\n";
        assertEquals(expected, combo.generarTextoFactura());
        String expectedempty = "Empty\n" +
                " Descuento: 0\n" +
                "            0\n";
        assertEquals(expectedempty, emptycombo.generarTextoFactura());

    }

    
}
