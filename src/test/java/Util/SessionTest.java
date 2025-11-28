/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Util;

import Model.Cliente;
import Model.Dueno;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author USUARIO
 */
public class SessionTest {
    
    public SessionTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of iniciarSesionCliente method, of class Session.
     */
    @Test
    public void testIniciarSesionCliente() {
        System.out.println("iniciarSesionCliente");
        Cliente cliente = null;
        Session.iniciarSesionCliente(cliente);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iniciarSesionDueno method, of class Session.
     */
    @Test
    public void testIniciarSesionDueno() {
        System.out.println("iniciarSesionDueno");
        Dueno dueno = null;
        Session.iniciarSesionDueno(dueno);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cerrarSesion method, of class Session.
     */
    @Test
    public void testCerrarSesion() {
        System.out.println("cerrarSesion");
        Session.cerrarSesion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of haySesionActiva method, of class Session.
     */
    @Test
    public void testHaySesionActiva() {
        System.out.println("haySesionActiva");
        boolean expResult = false;
        boolean result = Session.haySesionActiva();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tipoSesionActiva method, of class Session.
     */
    @Test
    public void testTipoSesionActiva() {
        System.out.println("tipoSesionActiva");
        String expResult = "";
        String result = Session.tipoSesionActiva();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClienteActual method, of class Session.
     */
    @Test
    public void testGetClienteActual() {
        System.out.println("getClienteActual");
        Cliente expResult = null;
        Cliente result = Session.getClienteActual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDuenoActual method, of class Session.
     */
    @Test
    public void testGetDuenoActual() {
        System.out.println("getDuenoActual");
        Dueno expResult = null;
        Dueno result = Session.getDuenoActual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
