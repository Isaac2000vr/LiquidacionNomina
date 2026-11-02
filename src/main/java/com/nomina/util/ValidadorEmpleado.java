package com.nomina.util;

/**
 * Validador para reglas de negocio comunes.
 * Aplica principio de Responsabilidad Única.
 */
public final class ValidadorEmpleado {

    private ValidadorEmpleado() {
        // Clase utilitaria, no instanciable
    }

    public static void validarHorasTrabajadas(double horasTrabajadas) {
        if (horasTrabajadas < 0) {
            throw new IllegalArgumentException("Las horas trabajadas no pueden ser negativas.");
        }
    }

    public static void validarVentas(double ventas) {
        if (ventas < 0) {
            throw new IllegalArgumentException("Las ventas de un empleado por comisión no pueden ser menores a $0.");
        }
    }

    public static void validarSalarioBase(double salarioBase) {
        if (salarioBase < 0) {
            throw new IllegalArgumentException("El salario base no puede ser negativo.");
        }
    }

    public static void validarTarifaPorHora(double tarifaPorHora) {
        if (tarifaPorHora < 0) {
            throw new IllegalArgumentException("La tarifa por hora no puede ser negativa.");
        }
    }

    public static void validarPorcentajeComision(double porcentajeComision) {
        if (porcentajeComision < 0 || porcentajeComision > 1) {
            throw new IllegalArgumentException("El porcentaje de comisión debe estar entre 0 y 1.");
        }
    }

    public static void validarAnosEnEmpresa(int anosEnEmpresa) {
        if (anosEnEmpresa < 0) {
            throw new IllegalArgumentException("Los años en la empresa no pueden ser negativos.");
        }
    }

    public static double validarSalarioNetoNoNegativo(double salarioNetoCalculado) {
        if (salarioNetoCalculado < 0) {
            return 0.0;
        }
        return salarioNetoCalculado;
    }
}
