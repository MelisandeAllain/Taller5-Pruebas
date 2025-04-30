package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;

import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Pedido;


public class RestauranteTest {
	private Restaurante resto;

    @BeforeEach
    void setUp( ) throws Exception
    {
        resto = new Restaurante();
    }
    
    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    public void testIniciarPedido() throws Exception {
        resto.iniciarPedido("Melisande", "Calle 57");
        assertNotNull(resto.getPedidoEnCurso());
        assertEquals("Melisande", resto.getPedidoEnCurso().getNombreCliente());
    }

    @Test
    public void testIniciarPedidoSiExiste() throws Exception {
        resto.iniciarPedido("Melisande", "Calle 57");
        assertThrows(YaHayUnPedidoEnCursoException.class, () -> {
            resto.iniciarPedido("Louise", "Calle 19");
        });
    }

    @Test
    public void testCerrarYGuardarPedidoSiNoPedido() {
        assertThrows(NoHayPedidoEnCursoException.class, () -> {
            resto.cerrarYGuardarPedido();
        });
    }

    @Test
    public void testCerrarYGuardarSiPedido() throws Exception {
        resto.iniciarPedido("Melisande", "Calle 57");
        File pedi = new File("./facturas/factura_1.txt");
        pedi.mkdirs(); 
        resto.cerrarYGuardarPedido();
        assertNull(resto.getPedidoEnCurso());
    }

    @Test
    public void testCargarIngredientesConRepetition() throws Exception {
        File fichier = new File("ingredientes.txt");
        PrintWriter writer = new PrintWriter(fichier);
        writer.println("Papas, 4000");
        writer.println("Papas, 4000");
        writer.close();

        assertThrows(IngredienteRepetidoException.class, () -> {
            resto.cargarInformacionRestaurante(fichier, new File("menu.txt"), new File("combos.txt"));
        });
    }

    @Test
    public void testCargarMenuConRepetition() throws Exception {
        File menu = new File("menu.txt");
        PrintWriter writer = new PrintWriter(menu);
        writer.println("Hamburguesa, 10000");
        writer.println("Hamburguesa, 10000");
        writer.close();

        assertThrows(ProductoRepetidoException.class, () -> {
            resto.cargarInformacionRestaurante(new File("ingredientes.txt"), menu, new File("combos.txt"));
        });
    }

    
}
