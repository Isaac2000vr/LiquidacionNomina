package com.nomina.model;

import com.nomina.util.ConfiguracionNomina;
import com.nomina.util.ValidadorEmpleado;

/**
 * Empleado por Horas: Pago por horas trabajadas.
 * Horas extras (> 40 horas) se pagan a 1.5 x la tarifa normal.
 * No recibe bonos. Si lleva más de 1 año y acepta el fondo de ahorro, aplica acceso a fondo de ahorro.
 */
public class EmpleadoPorHoras extends Empleado {

    private double tarifaPorHora;
    private double horasTrabajadas;

    public EmpleadoPorHoras(String id, String nombre, int anosEnEmpresa, boolean aceptaFondoAhorro,
                            double tarifaPorHora, double horasTrabajadas) {
        super(id, nombre, anosEnEmpresa, aceptaFondoAhorro);
        ValidadorEmpleado.validarTarifaPorHora(tarifaPorHora);
        ValidadorEmpleado.validarHorasTrabajadas(horasTrabajadas);
        this.tarifaPorHora = tarifaPorHora;
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public double calcularSalarioBruto() {
        double horasNormales = Math.min(horasTrabajadas, ConfiguracionNomina.HORAS_NORMALES_LIMITE);
        double horasExtras = Math.max(0, horasTrabajadas - ConfiguracionNomina.HORAS_NORMALES_LIMITE);
        double salarioNormales = horasNormales * tarifaPorHora;
        double salarioExtras = horasExtras * tarifaPorHora * ConfiguracionNomina.TARIFA_HORAS_EXTRAS_MULTIPLICADOR;
        return salarioNormales + salarioExtras;
    }

    @Override
    public boolean esEmpleadoPermanente() {
        return false;
    }

    public boolean aplicaFondoAhorro() {
        return anosEnEmpresa > ConfiguracionNomina.ANOS_MINIMOS_FONDO_AHORRO && aceptaFondoAhorro;
    }

    public double getTarifaPorHora() {
        return tarifaPorHora;
    }

    public void setTarifaPorHora(double tarifaPorHora) {
        ValidadorEmpleado.validarTarifaPorHora(tarifaPorHora);
        this.tarifaPorHora = tarifaPorHora;
    }

    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(double horasTrabajadas) {
        ValidadorEmpleado.validarHorasTrabajadas(horasTrabajadas);
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public String toString() {
        return "EmpleadoPorHoras{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", anosEnEmpresa=" + anosEnEmpresa +
                ", tarifaPorHora=" + tarifaPorHora +
                ", horasTrabajadas=" + horasTrabajadas +
                '}';
    }
}
