package com.nomina.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmpleadoPorComisionTest {

    @Test
    void debeCalcularSinBonoAdicional() {
        EmpleadoPorComision porComision = new EmpleadoPorComision("EMP003", "Carlos López", 3, false, 1_000_000.0, 0.05, 10_000_000.0);
        double esperado = 1_000_000.0 + 10_000_000.0 * 0.05;
        assertEquals(esperado, porComision.calcularSalarioBruto(), 0.001);
    }

    @Test
    void debeCalcularConBonoAdicionalSiSuperaUmbral() {
        EmpleadoPorComision porComision = new EmpleadoPorComision("EMP003", "Carlos López", 3, false, 1_000_000.0, 0.05, 25_000_000.0);
        double comision = 25_000_000.0 * 0.05;
        double esperado = 1_000_000.0 + comision;
        assertEquals(esperado, porComision.calcularSalarioBruto(), 0.001);
    }

    @Test
    void debeSerEmpleadoPermanente() {
        EmpleadoPorComision porComision = new EmpleadoPorComision("EMP003", "Carlos López", 3, false, 1_000_000.0, 0.05, 10_000_000.0);
        assertTrue(porComision.esEmpleadoPermanente());
    }

    @Test
    void noDebePermitirVentasNegativas() {
        assertThrows(IllegalArgumentException.class, () ->
            new EmpleadoPorComision("EMP003", "Carlos López", 3, false, 1_000_000.0, 0.05, -1000.0)
        );
    }
}
