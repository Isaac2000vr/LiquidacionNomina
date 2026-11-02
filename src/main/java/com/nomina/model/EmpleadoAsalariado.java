package com.nomina.model;

import com.nomina.util.ValidadorEmpleado;

import java.util.Objects;

/**
 * Empleado Asalariado: Salario fijo mensual.
 * Beneficio: Bono mensual del 10% del salario si lleva más de 5 años en la empresa.
 * Es empleado permanente -> aplica bono de alimentación.
 */
public class EmpleadoAsalariado extends Empleado {

    private double salarioFijoMensual;

    public EmpleadoAsalariado(String id, String nombre, int anosEnEmpresa, boolean aceptaFondoAhorro, double salarioFijoMensual) {
        super(id, nombre, anosEnEmpresa, aceptaFondoAhorro);
        ValidadorEmpleado.validarSalarioBase(salarioFijoMensual);
        this.salarioFijoMensual = salarioFijoMensual;
    }

    @Override
    public double calcularSalarioBruto() {
        return salarioFijoMensual;
    }

    @Override
    public boolean esEmpleadoPermanente() {
        return true;
    }

    public double getSalarioFijoMensual() {
        return salarioFijoMensual;
    }

    public void setSalarioFijoMensual(double salarioFijoMensual) {
        ValidadorEmpleado.validarSalarioBase(salarioFijoMensual);
        this.salarioFijoMensual = salarioFijoMensual;
    }

    @Override
    public String toString() {
        return "EmpleadoAsalariado{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", anosEnEmpresa=" + anosEnEmpresa +
                ", salarioFijoMensual=" + salarioFijoMensual +
                '}';
    }
}
