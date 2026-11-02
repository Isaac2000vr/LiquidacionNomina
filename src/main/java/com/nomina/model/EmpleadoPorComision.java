package com.nomina.model;

import com.nomina.util.ValidadorEmpleado;

/**
 * Empleado por Comisión: Salario base + porcentaje de comisión sobre ventas.
 * Si las ventas superan $20.000.000, recibe un bono adicional del 3% sobre las ventas.
 * Es empleado permanente -> aplica bono de alimentación.
 */
public class EmpleadoPorComision extends Empleado {

    private double salarioBase;
    private double porcentajeComision; // 0.0 a 1.0
    private double ventas;

    public EmpleadoPorComision(String id, String nombre, int anosEnEmpresa, boolean aceptaFondoAhorro,
                              double salarioBase, double porcentajeComision, double ventas) {
        super(id, nombre, anosEnEmpresa, aceptaFondoAhorro);
        ValidadorEmpleado.validarSalarioBase(salarioBase);
        ValidadorEmpleado.validarPorcentajeComision(porcentajeComision);
        ValidadorEmpleado.validarVentas(ventas);
        this.salarioBase = salarioBase;
        this.porcentajeComision = porcentajeComision;
        this.ventas = ventas;
    }

    @Override
    public double calcularSalarioBruto() {
        double comision = ventas * porcentajeComision;
        return salarioBase + comision;
    }

    @Override
    public boolean esEmpleadoPermanente() {
        return true;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        ValidadorEmpleado.validarSalarioBase(salarioBase);
        this.salarioBase = salarioBase;
    }

    public double getPorcentajeComision() {
        return porcentajeComision;
    }

    public void setPorcentajeComision(double porcentajeComision) {
        ValidadorEmpleado.validarPorcentajeComision(porcentajeComision);
        this.porcentajeComision = porcentajeComision;
    }

    public double getVentas() {
        return ventas;
    }

    public void setVentas(double ventas) {
        ValidadorEmpleado.validarVentas(ventas);
        this.ventas = ventas;
    }

    @Override
    public String toString() {
        return "EmpleadoPorComision{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", anosEnEmpresa=" + anosEnEmpresa +
                ", salarioBase=" + salarioBase +
                ", porcentajeComision=" + porcentajeComision +
                ", ventas=" + ventas +
                '}';
    }
}
