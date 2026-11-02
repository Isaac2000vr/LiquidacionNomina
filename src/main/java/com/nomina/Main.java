package com.nomina;

import com.nomina.model.*;
import com.nomina.service.CalculadoraNomina;

/**
 * Clase principal para demostrar el funcionamiento del sistema de nómina.
 */
public class Main {
    public static void main(String[] args) {
        CalculadoraNomina calculadora = new CalculadoraNomina();

        System.out.println("SISTEMA DE NÓMINA - DEMOSTRACIÓN\n");

        // Empleado Asalariado
        EmpleadoAsalariado asalariado = new EmpleadoAsalariado("EMP001", "Juan Pérez", 6, false, 3_000_000.0);
        System.out.println(calculadora.calcularNomina(asalariado));

        // Empleado por Horas
        EmpleadoPorHoras porHoras = new EmpleadoPorHoras("EMP002", "María García", 2, true, 15000.0, 45);
        System.out.println(calculadora.calcularNomina(porHoras));

        // Empleado por Comisión
        EmpleadoPorComision porComision = new EmpleadoPorComision("EMP003", "Carlos López", 3, false, 1_000_000.0, 0.05, 25_000_000.0);
        System.out.println(calculadora.calcularNomina(porComision));

        // Empleado Temporal
        EmpleadoTemporal temporal = new EmpleadoTemporal("EMP004", "Ana Martínez", 1, false, 2_000_000.0, "2026-12-31");
        System.out.println(calculadora.calcularNomina(temporal));
    }
}
