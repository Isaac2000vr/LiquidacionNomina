package com.nomina.service;

import com.nomina.model.Empleado;
import com.nomina.util.ConfiguracionNomina;

/**
 * Servicio encargado de calcular las deducciones obligatorias.
 * SRP: Solo se encarga de deducciones.
 */
public class ServicioDeducciones {

    /**
     * Calcula deducciones obligatorias sobre el salario bruto.
     * - Seguro Social y Pensión: 4% del salario bruto
     * - ARL: porcentaje configurable
     * @param empleado Empleado
     * @param salarioBruto Salario bruto calculado
     * @return Total de deducciones
     */
    public double calcularDeducciones(Empleado empleado, double salarioBruto) {
        if (salarioBruto < 0) {
            salarioBruto = 0;
        }
        double seguroSocialPension = salarioBruto * ConfiguracionNomina.PORCENTAJE_SEGURO_SOCIAL_PENSION;
        double arl = salarioBruto * ConfiguracionNomina.PORCENTAJE_ARL;
        return seguroSocialPension + arl;
    }

    public double calcularSeguroSocialPension(double salarioBruto) {
        if (salarioBruto < 0) {
            salarioBruto = 0;
        }
        return salarioBruto * ConfiguracionNomina.PORCENTAJE_SEGURO_SOCIAL_PENSION;
    }

    public double calcularARL(double salarioBruto) {
        if (salarioBruto < 0) {
            salarioBruto = 0;
        }
        return salarioBruto * ConfiguracionNomina.PORCENTAJE_ARL;
    }
}
