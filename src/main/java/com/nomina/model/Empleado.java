package com.nomina.model;

import com.nomina.util.ValidadorEmpleado;

import java.util.Objects;

/**
 * Clase base abstracta para todos los tipos de empleado.
 * Define el contrato común y atributos compartidos.
 * Cumple con el principio de Abierto/Cerrado (OCP) y Sustitución de Liskov (LSP).
 */
public abstract class Empleado {

    protected String id;
    protected String nombre;
    protected int anosEnEmpresa;
    protected boolean aceptaFondoAhorro;

    protected Empleado(String id, String nombre, int anosEnEmpresa, boolean aceptaFondoAhorro) {
        this.id = Objects.requireNonNull(id, "El id no puede ser nulo");
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        ValidadorEmpleado.validarAnosEnEmpresa(anosEnEmpresa);
        this.anosEnEmpresa = anosEnEmpresa;
        this.aceptaFondoAhorro = aceptaFondoAhorro;
    }

    /**
     * Calcula el salario bruto según la regla de negocio de cada tipo de empleado.
     * @return Salario bruto
     */
    public abstract double calcularSalarioBruto();

    /**
     * Indica si el empleado es considerado permanente.
     * Empleados permanentes según el enunciado: Asalariado y Por Comisión.
     * @return true si es permanente
     */
    public abstract boolean esEmpleadoPermanente();

    // Getters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAnosEnEmpresa() {
        return anosEnEmpresa;
    }

    public boolean isAceptaFondoAhorro() {
        return aceptaFondoAhorro;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", anosEnEmpresa=" + anosEnEmpresa +
                ", aceptaFondoAhorro=" + aceptaFondoAhorro +
                '}';
    }
}
