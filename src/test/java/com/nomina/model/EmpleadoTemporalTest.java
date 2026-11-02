package com.nomina.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmpleadoTemporalTest {

    @Test
    void debeCalcularSalarioBruto() {
        EmpleadoTemporal temporal = new EmpleadoTemporal("EMP004", "Ana Martínez", 1, false, 2_000_000.0);
        assertEquals(2_000_000.0, temporal.calcularSalarioBruto(), 0.001);
    }

    @Test
    void noDebeSerEmpleadoPermanente() {
        EmpleadoTemporal temporal = new EmpleadoTemporal("EMP004", "Ana Martínez", 1, false, 2_000_000.0);
        assertFalse(temporal.esEmpleadoPermanente());
    }
}
