package com.nomina.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmpleadoAsalariadoTest {

    @Test
    void debeCalcularSalarioBrutoIgualASalarioFijo() {
        EmpleadoAsalariado asalariado = new EmpleadoAsalariado("EMP001", "Juan Pérez", 3, false, 3_000_000.0);
        assertEquals(3_000_000.0, asalariado.calcularSalarioBruto(), 0.001);
    }

    @Test
    void debeSerEmpleadoPermanente() {
        EmpleadoAsalariado asalariado = new EmpleadoAsalariado("EMP001", "Juan Pérez", 3, false, 3_000_000.0);
        assertTrue(asalariado.esEmpleadoPermanente());
    }

    @Test
    void noDebePermitirSalarioNegativo() {
        assertThrows(IllegalArgumentException.class, () ->
            new EmpleadoAsalariado("EMP001", "Juan Pérez", 3, false, -1000.0)
        );
    }
}
