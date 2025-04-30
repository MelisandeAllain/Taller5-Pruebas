package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {

	private ProductoMenu producto;

    @BeforeEach
    void setUp( ) throws Exception
    {
        producto = new ProductoMenu( "Papas", 4000 );
    }
    
    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    public void testGetNombre() {
        assertEquals("Papas", producto.getNombre());
    }

    @Test
    public void testGetPrecio() {
        assertEquals(4000, producto.getPrecio());
    }

    @Test
    public void testGenerarTextoFactura() {
        String expected = "Papas\n            4000\n";
        assertEquals(expected, producto.generarTextoFactura());
    }
    
}
