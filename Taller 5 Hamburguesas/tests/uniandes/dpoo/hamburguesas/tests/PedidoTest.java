package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {

	private Pedido pedido;
	private Pedido pedido2;
	private ProductoMenu hamburg; 
	private ProductoMenu papas; 
	private Pedido pedidoVacio; 

	
	@BeforeEach
	void setUp( ) throws Exception
    {
        pedido = new Pedido("Melisande", "Calle 57"); 
        pedido2 = new Pedido ("Louise", "Calle 19");
        hamburg = new ProductoMenu("Hamburguesa", 10000);
        hamburg = new ProductoMenu("Papas", 4000);
        pedidoVacio = new Pedido("", ""); 
        pedido.agregarProducto(hamburg);
        pedido2.agregarProducto(hamburg);
        pedido2.agregarProducto(papas);

        
    } 
	
	@AfterEach
    void tearDown( ) throws Exception
    {
    }

    @Test
    void testGetId( )
    {
        assertEquals(1, pedido.getIdPedido( ), "El id del pedido Melisande no es el esperado." );
        assertEquals(2, pedido2.getIdPedido( ), "El id del pedido Louise no es el esperado." );
        
    }
    
    @Test
    void testGetNombreCliente() {
        assertEquals("Melisande", pedido.getNombreCliente());
    }

    @Test
    void testAgregarProducto() {
     // 10000 + 19% = 11900
        assertEquals(11900, pedido.getPrecioTotalPedido()); 
    }

    @Test
    void testGetPrecioTotalPedidoMasProductos() {
    	// 10000+4000 = 14000
        int net = 14000;
        int iva = (int) (net * 0.19);
        assertEquals(net + iva, pedido2.getPrecioTotalPedido());
    }
    
    @Test
    void testGetPrecioTotalPedidoVacio() {
        assertEquals(0, pedidoVacio.getPrecioTotalPedido());
    }

    @Test
    void testGenerarTextoFacturaVacio() {
        String expected = """
            Cliente: 
            Dirección: 
            ----------------
            ----------------
            Precio Neto:  0
            IVA:          0
            Precio Total: 0
            """;
        assertEquals(expected, pedidoVacio.generarTextoFactura());
    }

    @Test
    void testGenerarTextoFacturaAvecProduit() {
        String expected = """
            Cliente: Melisande
            Dirección: Calle 57
            ----------------
            Hamburger
                        10000
            ----------------
            Precio Neto:  10000
            IVA:          1900
            Precio Total: 11900
            """;
        assertEquals(expected, pedido.generarTextoFactura());
    }

    @Test
    void testGuardarFacturaCreeUnFichier() throws IOException {
        File fichier = new File("test_factura.txt");
        pedido.guardarFactura(fichier);

        assertTrue(fichier.exists());
        assertTrue(fichier.length() > 0);

        fichier.delete(); 
    }
}

	


