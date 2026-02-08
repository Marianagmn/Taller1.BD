# Sistema de Gestión de Artículos Fintech - Arquitectura MVC

## 📋 Descripción

Sistema desarrollado en **Java** con arquitectura **MVC (Modelo-Vista-Controlador)** y base de datos **MySQL** para la gestión de artículos científicos sobre Fintech e IA en Finanzas.

El sistema presenta información de búsquedas en Scopus y artículos científicos, además de implementar un **CRUD completo** de Estrategias de Inversión basadas en tecnologías financieras.

---

## 🏗️ Arquitectura MVC

```
ArticulosFintech/
├── src/
│   ├── modelo/                    # MODELO (Datos y Acceso a BD)
│   │   ├── ConexionDB.java       
│   │   ├── Busqueda.java         
│   │   ├── Articulo.java         
│   │   └── EstrategiaInversion.java  ← CRUD Principal
│   │
│   ├── controlador/               # CONTROLADOR (Lógica de Negocio)
│   │   ├── BusquedaControlador.java
│   │   ├── ArticuloControlador.java
│   │   └── EstrategiaControlador.java  ← CRUD Completo
│   │
│   └── vista/                     # VISTA (Interfaz Gráfica)
│       ├── VentanaPrincipal.java
│       ├── PanelBusquedas.java
│       ├── PanelArticulos.java
│       └── PanelEstrategias.java  ← CRUD UI
│
├── sql/
│   └── schema.sql                 # Base de datos con datos iniciales
│
├── lib/                           # mysql-connector-java.jar
├── docs/                          # Documentación
└── README.md
```

---

## 🗄️ Base de Datos

**MySQL - 3 Tablas:**

1. **busquedas**: Información de búsquedas en Scopus (3 búsquedas)
2. **articulos**: 9 artículos científicos completos
3. **estrategias_inversion**: CRUD de estrategias (CREATE, READ, UPDATE, DELETE)

---

## ⚙️ Requisitos

- **Java**: JDK 8 o superior
- **MySQL**: 5.7 o superior
- **MySQL Connector/J**: 8.0 o superior (en carpeta `lib/`)

---

## 🚀 Instalación y Configuración

### 1. Configurar Base de Datos

```bash
# Iniciar MySQL
mysql -u root -p

# Ejecutar script de creación
source sql/schema.sql
```

### 2. Configurar Conexión

Editar `src/modelo/ConexionDB.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/articulos_fintech";
private static final String USUARIO = "root";
private static final String PASSWORD = "tu_password";  // Cambiar aquí
```

### 3. Compilar

```bash
# Desde la raíz del proyecto
javac -cp "lib/*:." -d bin src/modelo/*.java src/controlador/*.java src/vista/*.java
```

### 4. Ejecutar

```bash
java -cp "lib/*:bin" vista.VentanaPrincipal
```

---

## 📚 Funcionalidades

### 🔍 Módulo de Búsquedas
- Visualiza las 3 búsquedas realizadas en Scopus
- Muestra cadenas de búsqueda completas
- Cantidad de documentos encontrados
- Estadísticas generales

### 📖 Módulo de Artículos
- Lista de 9 artículos científicos
- Información completa: título, autores, año, fuente, DOI
- Resumen, palabras clave y cita APA
- Búsqueda por palabras clave

### 💰 Módulo CRUD - Estrategias de Inversión

**CREATE (Crear):**
- Formulario completo para nueva estrategia
- Validación de datos
- Relación con artículos

**READ (Leer):**
- Lista de todas las estrategias
- Detalles completos de cada estrategia
- Filtros y búsquedas

**UPDATE (Actualizar):**
- Edición de estrategias existentes
- Actualización de todos los campos

**DELETE (Eliminar):**
- Eliminación con confirmación
- Manejo de relaciones

---

## 👥 División de Trabajo en GitHub

### Estudiante 1: Julián David Cristancho Niño
**Branch:** `feature/modelo-base-datos`

**Responsabilidades:**
1. Crear estructura del proyecto
2. Implementar clases del MODELO:
   - `ConexionDB.java`
   - `Busqueda.java`
   - `Articulo.java`
   - `EstrategiaInversion.java`
3. Crear script SQL (`schema.sql`) con datos iniciales
4. Documentación de la base de datos

### Estudiante 2: Mariana Alejandra Gordillo Meneses
**Branch:** `feature/controladores`

**Responsabilidades:**
1. Implementar CONTROLADORES:
   - `BusquedaControlador.java`
   - `ArticuloControlador.java`
   - `EstrategiaControlador.java` (CRUD completo)
2. Lógica de negocio
3. Métodos de consulta y manipulación de datos
4. Pruebas de controladores

### Estudiante 3: Ana Sofía Fajardo Leal
**Branch:** `feature/vistas-interfaz`

**Responsabilidades:**
1. Implementar VISTAS (Swing):
   - `VentanaPrincipal.java`
   - `PanelBusquedas.java`
   - `PanelArticulos.java`
   - `PanelEstrategias.java` (interfaz CRUD)
2. Diseño de interfaz gráfica
3. Integración final MVC
4. Manual de usuario

---

## 🔄 Flujo de Trabajo Git

```bash
# 1. Clonar repositorio
git clone [URL_REPOSITORIO]

# 2. Crear branch personal
git checkout -b [nombre-branch]

# 3. Trabajar en su parte
# ... hacer cambios ...

# 4. Commit y push
git add .
git commit -m "Descripción clara del cambio"
git push origin [nombre-branch]

# 5. Crear Pull Request a 'develop'

# 6. Revisión entre compañeros

# 7. Merge a 'develop'

# 8. Al finalizar: merge 'develop' -> 'main'
```

---

## 📊 Datos Incluidos

### Búsquedas:
1. **Julián**: "financial + simulation + python" → 121 docs
2. **Mariana**: "financial simulation + AI + deep learning" → 867 docs
3. **Ana Sofía**: "fintech + machine learning + fraud detection" → 1956 docs

### Artículos:
- 9 artículos científicos completos (2025-2026)
- Temas: IA en finanzas, Python financiero, Portfolio Optimization, Fraud Detection

### Estrategias (ejemplos pre-cargados):
- Optimización de Portafolio con Deep Learning
- Trading Algorítmico con Python
- Detección de Fraude con SMOTE

---

## 📝 Notas Técnicas

- **Patrón MVC** estrictamente implementado
- **Separación de responsabilidades** clara
- **Base de datos MySQL** con relaciones
- **Interfaz gráfica Swing** con pestañas
- **CRUD completo** funcional
- **Código comentado** y documentado

---

## 🎓 Institución

**Fundación Universitaria Konrad Lorenz**  
Proyecto: Sistema de Gestión de Artículos Científicos  
Arquitectura: MVC (Modelo-Vista-Controlador)  
Año: 2026

---

## 📧 Soporte

Para dudas sobre la configuración o uso del sistema, contactar a los desarrolladores.
