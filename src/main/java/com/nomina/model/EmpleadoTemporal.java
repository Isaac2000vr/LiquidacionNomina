package com.nomina.model;

import com.nomina.util.ValidadorEmpleado;

/**
 * Empleado Temporal: Salario fijo mensual.
 * Contrato por tiempo definido.
 * No aplican bonos ni beneficios adicionales.
 */
public class EmpleadoTemporal extends Empleado {

    private double salarioFijoMensual;
    private String fechaFinContrato; // Opcional, solo informativo

    public EmpleadoTemporal(String id, String nombre, int anosEnEmpresa, boolean aceptaFondoAhorro,
                            double salarioFijoMensual, String fechaFinContrato) {
        super(id, nombre, anosEnEmpresa, aceptaFondoAhorro);
        ValidadorEmpleado.validarSalarioBase(salarioFijoMensual);
        this.salarioFijoMensual = salarioFijoMensual;
        this.fechaFinContrato = fechaFinContrato;
    }

    public EmpleadoTemporal(String id, String nombre, int anosEnEmpresa, boolean aceptaFondoAhorro,
                            double salarioFijoMensual) {
        this(id, nombre, anosEnEmpresa, aceptaFondoAhorro, salarioFijoMensual, null);
    }

    @Override
    public double calcularSalarioBruto() {
        return salarioFijoMensual;
    }

    @Override
    public boolean esEmpleadoPermanente() {
        return false;
    }

    public double getSalarioFijoMensual() {
        return salarioFijoMensual;
    }

    public void setSalarioFijoMensual(double salarioFijoMensual) {
        ValidadorEmpleado.validarSalarioBase(salarioFijoMensual);
        this.salarioFijoMensual = salarioFijoMensual;
    }

    public String getFechaFinContrato() {
        return fechaFinContrato;
    }

    public void setFechaFinContrato(String fechaFinContrato) {
        this.fechaFinContrato = fechaFinContrato;
    }

    @Override
    public String toString() {
        return "EmpleadoTemporal{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", anosEnEmpresa=" + anosEnEmpresa +
                ", salarioFijoMensual=" + salarioFijoMensual +
                ", fechaFinContrato='" + fechaFinContrato + '\'' +
                '}';
    }
}
