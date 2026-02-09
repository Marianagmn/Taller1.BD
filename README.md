# Sistema de Gestión de Artículos Fintech (MVC)

Breve proyecto de ejemplo que implementa el patrón Modelo-Vista-Controlador (MVC) en Java.
Permite gestionar búsquedas en bases de datos, visualizar artículos científicos y administrar estrategias de inversión.

**Resumen rápido**
- **Lenguaje:** Java
- **Base de datos:** MySQL
- **Interfaz:** Swing (ventana con pestañas)

**Qué incluye el proyecto**
- Datos de ejemplo y esquema de la base de datos: `SQL/schema.sql`
- Modelo: clases que representan datos (ej. `modelo/Articulo.java`).
- Controladores: lógica para acceder a la BD (ej. `MVC/Controlador`).
- Vistas: interfaces Swing para interactuar (ej. `MVC/Vista`).

**Requisitos**
- Java 11+ (o JDK compatible)
- MySQL (o MariaDB) y cliente `mysql`
- Conector JDBC de MySQL (`mysql-connector-java`), añadido al classpath cuando se ejecute desde línea de comandos

**Instalación y configuración rápida**
1. Clonar o descargar el proyecto en tu equipo.
2. Crear la base de datos y las tablas importando el archivo SQL:

```bash
mysql -u root -p < SQL/schema.sql
```

3. Revisar y, si es necesario, actualizar las credenciales de conexión en:
- `MVC/modelo/ConexionDB.java`

Por defecto el archivo usa:

- URL: `jdbc:mysql://localhost:3306/articulos_fintech`
- Usuario: `root`
- Contraseña: `` (vacía)

Modifica `USUARIO` y `PASSWORD` según tu entorno.

**Ejecutar el proyecto**

Opción recomendada: abrir el proyecto en un IDE (IntelliJ, Eclipse, NetBeans):
- Importa como proyecto Java
- Añade la dependencia del conector JDBC (librería `mysql-connector-java`)
- Ejecuta la clase `vista.VentanaPrincipal` (método `main`).

Desde línea de comandos (ejemplo en Windows):

1. Compilar todo (ajusta rutas según tu estructura):

```powershell
javac -cp ".;lib/mysql-connector-java.jar" -d out $(Get-ChildItem -Recurse -Filter "*.java" | ForEach-Object FullName)
```

2. Ejecutar la ventana principal:

```powershell
java -cp "out;lib/mysql-connector-java.jar" vista.VentanaPrincipal
```

Nota: si usas Linux/macOS, reemplaza `;` por `:` en el classpath.

**Uso básico**
- Pestaña "Búsquedas": muestra búsquedas guardadas y detalles.
- Pestaña "Artículos": muestra artículos, búsqueda por palabra clave y detalle completo.
- Pestaña "Estrategias (CRUD)": crear, ver, editar y eliminar estrategias de inversión.

**Estructura del proyecto (resumen)**
- `MVC/Modelo` — Clases de datos: `Articulo.java`, `Busqueda.java`, `EstrategiaInversion.java`, `ConexionDB.java`.
- `MVC/Controlador` — Lógica de acceso a BD: `ArticuloControlador.java`, `BusquedaControlador.java`, `EstrategiaControlador.java`.
- `MVC/Vista` — Interfaces Swing: `VentanaPrincipal.java`, `PanelBusquedas.java`, `PanelArticulos.java`, `PanelEstrategias.java`.
- `SQL/schema.sql` — Esquema y datos de ejemplo para crear la BD.

**Consejos y notas**
- Si obtienes errores de conexión, verifica que MySQL esté en marcha y que las credenciales en `ConexionDB.java` son correctas.
- Preferible ejecutar desde el IDE para evitar problemas con classpath y dependencias.
- Los comentarios en el código están en español y pensados para estudiantes; revisa las clases `MVC/Controlador` y `MVC/Modelo` para entender las operaciones.

