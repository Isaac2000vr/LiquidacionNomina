# Sistema de Nómina - Actividad Unidad 3

Sistema de nómina desarrollado en Java aplicando POO, principios SOLID, código limpio y pruebas unitarias.

## Tecnologías
- Java 17
- Maven
- JUnit 5

## Estructura del Proyecto
```
src/
├── main/java/com/nomina
│   ├── Main.java
│   ├── model/          # Clases de dominio (Empleado y sus tipos)
│   ├── service/        # Lógica de negocio (Cálculo, Beneficios, Deducciones)
│   └── util/           # Utilidades y configuración
└── test/java/com/nomina
    ├── model/
    ├── service/
    └── util/
```

## Tipos de Empleados

| Tipo | Descripción | Beneficios |
|---|---|---|
| **Empleado Asalariado** | Salario fijo mensual | Bono 10% si > 5 años en la empresa. Bono alimentación ($1.000.000). |
| **Empleado por Horas** | Tarifa por hora. Horas extras (>40) a 1.5x | Fondo de ahorro 2% si > 1 año y acepta el fondo. |
| **Empleado por Comisión** | Salario base + % comisión sobre ventas. Bono 3% si ventas > $20.000.000 | Bono alimentación ($1.000.000). |
| **Empleado Temporal** | Salario fijo mensual, contrato por tiempo definido | Ninguno |

## Deducciones
- Seguro Social y Pensión: 4% del salario bruto
- ARL: 0.522% del salario bruto (configurable en `ConfiguracionNomina`)

## Validaciones
- Las horas trabajadas no pueden ser negativas
- Las ventas no pueden ser menores a $0
- El salario neto nunca puede ser negativo (se ajusta a $0 si ocurre)

## Supuestos Tomados
- **ARL**: No se especificó el porcentaje en el documento, se asumió 0.522% y se dejó configurable.
- **Bono alimentación**: Se suma como beneficio al salario neto para empleados permanentes (Asalariado y Por Comisión).
- **Fondo de ahorro**: Se suma como beneficio (2% del salario bruto) para Empleado por Horas con >1 año que acepte el acceso al fondo.
- **Base para deducciones**: Se calculan sobre el salario bruto antes de beneficios.
- **Neto**: `(Salario Bruto + Beneficios) - Deducciones`. Si resulta negativo, se fuerza a $0.

## Principios Aplicados
- **SRP (Single Responsibility)**: Cada clase tiene una responsabilidad única (modelo, servicios de beneficios/deducciones, calculadora).
- **OCP (Open/Closed)**: Abierto a extensión (nuevos tipos de empleado) cerrado a modificación.
- **LSP (Liskov Substitution)**: Las subclases pueden sustituir a `Empleado` sin romper la lógica.
- **ISP (Interface Segregation)**: Interfaces pequeñas y específicas.
- **DIP (Dependency Inversion)**: `CalculadoraNomina` depende de abstracciones y puede recibir dependencias inyectadas.

## Cómo Ejecutar

### Compilar
```bash
mvn clean compile
```

### Ejecutar pruebas
```bash
mvn test
```

### Ejecutar demostración
```bash
mvn clean compile exec:java -Dexec.mainClass="com.nomina.Main"
```

## Metodología de Desarrollo
Se recomienda utilizar **Scrum**:
- **Sprint 1**: Análisis y diseño (modelo, reglas de negocio)
- **Sprint 2**: Implementación de clases y lógica
- **Sprint 3**: Pruebas unitarias y validaciones
- **Sprint 4**: Refactorización, documentación y evidencias para entrega

## Control de Versiones
Este proyecto debe subirse a GitHub con commits descriptivos que evidencien el trabajo del CIPA.
```bash
git init
git add .
git commit -m "feat: Sistema de nómina con POO y principios SOLID"
git branch -M main
git remote add origin <URL_REPOSITORIO>
git push -u origin main
```
