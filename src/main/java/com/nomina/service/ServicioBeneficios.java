package com.nomina.service;

import com.nomina.model.*;
import com.nomina.util.ConfiguracionNomina;

/**
 * Servicio encargado de calcular los beneficios.
 * SRP: Solo se encarga de beneficios.
 */
public class ServicioBeneficios {

    /**
     * Calcula todos los beneficios aplicables al empleado.
     * @param empleado Empleado
     * @param salarioBruto Salario bruto calculado
     * @return Total de beneficios
     */
    public double calcularBeneficios(Empleado empleado, double salarioBruto) {
        if (salarioBruto < 0) {
            salarioBruto = 0;
        }

        double totalBeneficios = 0.0;

        // Bono alimentación para empleados permanentes (Asalariado y Por Comisión)
        if (empleado.esEmpleadoPermanente()) {
            totalBeneficios += ConfiguracionNomina.BONO_ALIMENTACION;
        }

        // Bono por antigüedad para Empleado Asalariado si lleva más de 5 años
        if (empleado instanceof EmpleadoAsalariado) {
            EmpleadoAsalariado asalariado = (EmpleadoAsalariado) empleado;
            if (asalariado.getAnosEnEmpresa() > ConfiguracionNomina.ANOS_MINIMOS_BONO_ASALARIADO) {
                totalBeneficios += salarioBruto * ConfiguracionNomina.PORCENTAJE_BONO_ASALARIADO_ANTIGUEDAD;
            }
        }

        // Bono adicional por ventas para Empleado Por Comisión
        if (empleado instanceof EmpleadoPorComision) {
            EmpleadoPorComision comisionado = (EmpleadoPorComision) empleado;
            if (comisionado.getVentas() > ConfiguracionNomina.UMBRAL_VENTAS_BONO_COMISION) {
                totalBeneficios += comisionado.getVentas() * ConfiguracionNomina.PORCENTAJE_BONO_ADICIONAL_COMISION;
            }
        }

        // Fondo de ahorro para Empleado Por Horas con más de 1 año y que acepte
        if (empleado instanceof EmpleadoPorHoras) {
            EmpleadoPorHoras porHoras = (EmpleadoPorHoras) empleado;
            if (porHoras.aplicaFondoAhorro()) {
                totalBeneficios += salarioBruto * ConfiguracionNomina.PORCENTAJE_FONDO_AHORRO_HORAS;
            }
        }

        // Empleado Temporal: sin bonos ni beneficios adicionales

        return totalBeneficios;
    }
}
