package com.mycompany.gymnewbody;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GymNewBodyTest {

    @Test
    void testMain_NoLanzaExcepciones() {
        String[] args = {};

        try {
            GymNewBody.main(args);
        } catch (Exception e) {
            fail("El método main no debe lanzar excepciones: " + e.getMessage());
        }

        // Si llega aquí, la prueba pasa sin problemas.
        assertTrue(true);
    }
}
