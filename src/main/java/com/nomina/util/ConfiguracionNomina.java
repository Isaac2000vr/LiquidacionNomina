package com.nomina.util;

import java.math.BigDecimal;

/**
 * Configuración centralizada para el cálculo de nómina.
 * Se centralizan porcentajes para cumplir con el principio de Responsabilidad Única (SRP)
 * y facilitar cambios sin modificar la lógica de negocio.
 */
public final class ConfiguracionNomina {

    private ConfiguracionNomina() {
        // Clase utilitaria, no instanciable
    }

    // Deducciones obligatorias
    public static final double PORCENTAJE_SEGURO_SOCIAL_PENSION = 0.04; // 4%
    public static final double PORCENTAJE_ARL = 0.00522; // 0.522% - Valor asumido (no especificado en el enunciado)

    // Beneficios
    public static final double BONO_ALIMENTACION = 1_000_000.0; // $1.000.000/mes cubierto por la empresa
    public static final double PORCENTAJE_BONO_ASALARIADO_ANTIGUEDAD = 0.10; // 10% si > 5 años
    public static final int ANOS_MINIMOS_BONO_ASALARIADO = 5;

    public static final double UMBRAL_VENTAS_BONO_COMISION = 20_000_000.0; // $20.000.000
    public static final double PORCENTAJE_BONO_ADICIONAL_COMISION = 0.03; // 3% sobre ventas

    public static final double TARIFA_HORAS_EXTRAS_MULTIPLICADOR = 1.5; // 1.5x
    public static final int HORAS_NORMALES_LIMITE = 40;

    public static final double PORCENTAJE_FONDO_AHORRO_HORAS = 0.02; // 2%
    public static final int ANOS_MINIMOS_FONDO_AHORRO = 1;
}
