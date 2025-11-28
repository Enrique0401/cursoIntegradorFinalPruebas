package Repositorio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {

    @Test
    public void testValidarCredencialesCliente_Incorrectas() {
        AuthService auth = new AuthService();

        boolean result = auth.validarCredencialesCliente("", "");

        assertFalse(result);
    }

    @Test
    public void testValidarCredencialesCliente_Correctas() {
        AuthService auth = new AuthService();

        // Aquí debes poner un usuario válido que AuthService realmente reconozca
        boolean result = auth.validarCredencialesCliente("test@correo.com", "1234");

        // Cambia este assert según lo que tu AuthService haga de verdad
        assertTrue(result);
    }

    @Test
    public void testValidarCredencialesDueno_Incorrectas() {
        AuthService auth = new AuthService();

        boolean result = auth.validarCredencialesDueno("", "");

        assertFalse(result);
    }

    @Test
    public void testValidarCredencialesDueno_Correctas() {
        AuthService auth = new AuthService();

        // Poner aquí credenciales que AuthService realmente acepte
        boolean result = auth.validarCredencialesDueno("dueno@correo.com", "abcd");

        assertTrue(result);
    }
}
