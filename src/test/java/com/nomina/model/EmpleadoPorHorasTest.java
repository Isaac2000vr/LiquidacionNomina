package com.nomina.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmpleadoPorHorasTest {

    @Test
    void debeCalcularSinHorasExtras() {
        EmpleadoPorHoras porHoras = new EmpleadoPorHoras("EMP002", "María García", 1, false, 15000.0, 40);
        assertEquals(600000.0, porHoras.calcularSalarioBruto(), 0.001);
    }

    @Test
    void debeCalcularConHorasExtras() {
        // 40 normales + 5 extras a 1.5x
        EmpleadoPorHoras porHoras = new EmpleadoPorHoras("EMP002", "María García", 1, false, 15000.0, 45);
        double esperado = 40 * 15000.0 + 5 * 15000.0 * 1.5;
        assertEquals(esperado, porHoras.calcularSalarioBruto(), 0.001);
    }

    @Test
    void noDebeSerEmpleadoPermanente() {
        EmpleadoPorHoras porHoras = new EmpleadoPorHoras("EMP002", "María García", 1, false, 15000.0, 40);
        assertFalse(porHoras.esEmpleadoPermanente());
    }

    @Test
    void noDebePermitirHorasNegativas() {
        assertThrows(IllegalArgumentException.class, () ->
            new EmpleadoPorHoras("EMP002", "María García", 1, false, 15000.0, -5)
        );
    }

    @Test
    void debeAplicarFondoAhorroSiCumpleCondiciones() {
        EmpleadoPorHoras porHoras = new EmpleadoPorHoras("EMP002", "María García", 2, true, 15000.0, 40);
        assertTrue(porHoras.aplicaFondoAhorro());
    }

    @Test
    void noDebeAplicarFondoAhorroSiMenorAUnAno() {
        EmpleadoPorHoras porHoras = new EmpleadoPorHoras("EMP002", "María García", 1, true, 15000.0, 40);
        assertFalse(porHoras.aplicaFondoAhorro());
    }
}
