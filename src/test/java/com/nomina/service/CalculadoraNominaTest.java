package com.nomina.service;

import com.nomina.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraNominaTest {

    private CalculadoraNomina calculadora;

    @BeforeEach
    void setUp() {
        calculadora = new CalculadoraNomina();
    }

    @Test
    void debeCalcularNominaEmpleadoAsalariadoConBonoAntiguedad() {
        // Más de 5 años: bono 10% del salario bruto
        EmpleadoAsalariado asalariado = new EmpleadoAsalariado("EMP001", "Juan Pérez", 6, false, 3_000_000.0);
        CalculadoraNomina.DetalleNomina detalle = calculadora.calcularNomina(asalariado);
        
        assertEquals(3_000_000.0, detalle.getSalarioBruto(), 0.001);
        // Beneficios: bono alimentación (1M) + bono antigüedad (10% de 3M = 300000)
        assertEquals(1_300_000.0, detalle.getBeneficios(), 0.001);
        // Deducciones: 4% + 0.522% = 4.522% de 3M
        assertEquals(135_660.0, detalle.getDeducciones(), 0.001);
    }

    @Test
    void debeCalcularNominaEmpleadoPorHorasConFondoAhorro() {
        // 45 horas, 2 años, acepta fondo de ahorro
        EmpleadoPorHoras porHoras = new EmpleadoPorHoras("EMP002", "María García", 2, true, 15000.0, 45);
        CalculadoraNomina.DetalleNomina detalle = calculadora.calcularNomina(porHoras);
        
        assertEquals(712500.0, detalle.getSalarioBruto(), 0.001); // 40*15000 + 5*15000*1.5
        // Beneficios: fondo de ahorro 2% (no es permanente)
        assertEquals(14250.0, detalle.getBeneficios(), 0.001);
        // Deducciones: 4.522% de 712500
        assertEquals(32203.125, detalle.getDeducciones(), 0.001);
    }

    @Test
    void debeCalcularNominaEmpleadoPorComisionConBonoAlimentacionYBonoVentas() {
        EmpleadoPorComision porComision = new EmpleadoPorComision("EMP003", "Carlos López", 3, false, 1_000_000.0, 0.05, 25_000_000.0);
        CalculadoraNomina.DetalleNomina detalle = calculadora.calcularNomina(porComision);
        
        double salarioBruto = 1_000_000.0 + 25_000_000.0 * 0.05; // 2,250,000
        assertEquals(salarioBruto, detalle.getSalarioBruto(), 0.001);
        // Beneficios: bono alimentación (1M) + bono adicional 3% sobre ventas (750000)
        assertEquals(1_750_000.0, detalle.getBeneficios(), 0.001);
    }

    @Test
    void debeCalcularNominaEmpleadoTemporalSinBeneficios() {
        EmpleadoTemporal temporal = new EmpleadoTemporal("EMP004", "Ana Martínez", 1, false, 2_000_000.0);
        CalculadoraNomina.DetalleNomina detalle = calculadora.calcularNomina(temporal);
        
        assertEquals(2_000_000.0, detalle.getSalarioBruto(), 0.001);
        assertEquals(0.0, detalle.getBeneficios(), 0.001);
    }

    @Test
    void debeForzarSalarioNetoACeroSiEsNegativo() {
        // Caso extremo para forzar neto negativo
        CalculadoraNomina calc = new CalculadoraNomina();
        EmpleadoPorHoras porHoras = new EmpleadoPorHoras("EMP005", "Test", 0, false, 100.0, 1);
        CalculadoraNomina.DetalleNomina detalle = calc.calcularNomina(porHoras);
        assertTrue(detalle.getSalarioNeto() >= 0);
    }
}
