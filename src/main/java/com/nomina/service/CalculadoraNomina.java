package com.nomina.service;

import com.nomina.model.Empleado;
import com.nomina.util.ValidadorEmpleado;

/**
 * Orquestador para el cálculo de nómina.
 * Aplica principios SOLID: depende de abstracciones (Empleado) y delega responsabilidades
 * a servicios especializados (DIP + SRP).
 */
public class CalculadoraNomina {

    private final ServicioBeneficios servicioBeneficios;
    private final ServicioDeducciones servicioDeducciones;

    public CalculadoraNomina() {
        this.servicioBeneficios = new ServicioBeneficios();
        this.servicioDeducciones = new ServicioDeducciones();
    }

    public CalculadoraNomina(ServicioBeneficios servicioBeneficios, ServicioDeducciones servicioDeducciones) {
        this.servicioBeneficios = servicioBeneficios;
        this.servicioDeducciones = servicioDeducciones;
    }

    /**
     * Calcula el detalle completo de nómina para un empleado.
     * @param empleado Empleado a liquidar
     * @return DetalleNomina con todos los valores calculados
     */
    public DetalleNomina calcularNomina(Empleado empleado) {
        if (empleado == null) {
            throw new IllegalArgumentException("El empleado no puede ser nulo");
        }

        double salarioBruto = empleado.calcularSalarioBruto();
        double beneficios = servicioBeneficios.calcularBeneficios(empleado, salarioBruto);
        double deducciones = servicioDeducciones.calcularDeducciones(empleado, salarioBruto);
        double salarioNetoCalculado = (salarioBruto + beneficios) - deducciones;
        double salarioNeto = ValidadorEmpleado.validarSalarioNetoNoNegativo(salarioNetoCalculado);

        return new DetalleNomina(empleado, salarioBruto, beneficios, deducciones, salarioNeto, salarioNetoCalculado < 0);
    }

    /**
     * Clase interna para representar el detalle de nómina.
     * Inmutable y con formato claro para impresión.
     */
    public static class DetalleNomina {
        private final Empleado empleado;
        private final double salarioBruto;
        private final double beneficios;
        private final double deducciones;
        private final double salarioNeto;
        private final boolean netoForzadoACero;

        public DetalleNomina(Empleado empleado, double salarioBruto, double beneficios, double deducciones,
                             double salarioNeto, boolean netoForzadoACero) {
            this.empleado = empleado;
            this.salarioBruto = salarioBruto;
            this.beneficios = beneficios;
            this.deducciones = deducciones;
            this.salarioNeto = salarioNeto;
            this.netoForzadoACero = netoForzadoACero;
        }

        public Empleado getEmpleado() {
            return empleado;
        }

        public double getSalarioBruto() {
            return salarioBruto;
        }

        public double getBeneficios() {
            return beneficios;
        }

        public double getDeducciones() {
            return deducciones;
        }

        public double getSalarioNeto() {
            return salarioNeto;
        }

        public boolean isNetoForzadoACero() {
            return netoForzadoACero;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("========================================\n");
            sb.append("DETALLE DE NÓMINA\n");
            sb.append("========================================\n");
            sb.append("Empleado: ").append(empleado.getNombre()).append(" (").append(empleado.getClass().getSimpleName()).append(")\n");
            sb.append("ID: ").append(empleado.getId()).append("\n");
            sb.append("Años en empresa: ").append(empleado.getAnosEnEmpresa()).append("\n");
            sb.append("----------------------------------------\n");
            sb.append(String.format("Salario Bruto:        $%,.2f\n", salarioBruto));
            sb.append(String.format("Beneficios:           $%,.2f\n", beneficios));
            sb.append(String.format("Deducciones:          $%,.2f\n", deducciones));
            sb.append("----------------------------------------\n");
            sb.append(String.format("Salario Neto:         $%,.2f\n", salarioNeto));
            if (netoForzadoACero) {
                sb.append("ADVERTENCIA: Salario neto negativo detectado. Se ajustó a $0.00\n");
            }
            sb.append("========================================\n");
            return sb.toString();
        }
    }
}
