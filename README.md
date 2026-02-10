# 📦 Sistema de Gestión de Productos (CRUD Web)

Aplicación web completa para la gestión de inventario desarrollada en **Java EE** siguiendo el patrón de arquitectura **MVC (Modelo-Vista-Controlador)**.

Este proyecto permite realizar todas las operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre una base de datos MySQL, utilizando un Pool de Conexiones para optimizar el rendimiento.

---

## 🚀 Funcionalidades Principales

* **Listar Productos:** Visualización de todos los registros en una tabla dinámica con estilos CSS.
* **Alta de Productos:** Formulario para insertar nuevos registros con validación de tipos de datos.
* **Actualización:** Edición de productos existentes con precarga de datos (los campos se llenan automáticamente con la información actual).
* **Eliminación:** Borrado de registros directamente desde la lista principal.
* **Navegación:** Enrutamiento centralizado a través de un Servlet Controlador (Front Controller pattern).

---

## 🛠️ Tecnologías Utilizadas

El proyecto fue construido utilizando tecnologías estándar de la industria (sin frameworks pesados) para comprender los fundamentos del desarrollo web en Java.

| Categoría | Tecnología | Detalles |
| :--- | :--- | :--- |
| **Backend** | Java (JDK 17/21) | Lógica de negocio y control |
| **Web** | Jakarta EE / Java EE | Servlets & JSP (JavaServer Pages) |
| **Frontend** | HTML5, CSS3 | Diseño responsivo y estilos personalizados |
| **Librerías** | JSTL (JSP Standard Tag Library) | Bucles y lógica en vistas |
| **Base de Datos** | MySQL | Motor de base de datos relacional |
| **Conectividad** | JDBC & DataSource | Pool de conexiones gestionado por el servidor |
| **Servidor** | Apache Tomcat 9/10 | Contenedor de Servlets |
| **IDE** | IntelliJ IDEA | Entorno de desarrollo |

---

## 🏗️ Arquitectura del Proyecto (MVC)

El flujo de la aplicación sigue estrictamente el patrón **Modelo-Vista-Controlador**:

1.  **Vista (View):** Archivos `.jsp` (`Lista_Productos.jsp`, `actualizarProducto.jsp`) encargados de la interfaz de usuario.
2.  **Controlador (Controller):** `ControladorProductos.java` (Servlet). Recibe todas las peticiones, decide qué acción ejecutar y delega al modelo.
3.  **Modelo (Model):** `ModeloProductos.java` y `Productos.java`. Encargados de la lógica de negocio y las operaciones SQL directas contra la base de datos.

---

## 💾 Configuración de la Base de Datos

Para ejecutar este proyecto, necesitas una base de datos MySQL con la siguiente estructura:

```sql
CREATE DATABASE proyecto_productos;

USE proyecto_productos;

CREATE TABLE PRODUCTOS (
  CODIGOARTICULO VARCHAR(10) NOT NULL PRIMARY KEY,
  SECCION VARCHAR(20),
  NOMBREARTICULO VARCHAR(20),
  PRECIO DOUBLE,
  FECHA DATE,
  IMPORTADO VARCHAR(10),
  PAISDEORIGEN VARCHAR(15)
);

-- Datos de ejemplo
INSERT INTO PRODUCTOS VALUES ('AR01', 'CERÁMICA', 'JARRÓN', 15.50, '2025-01-20', 'NO', 'ESPAÑA');
INSERT INTO PRODUCTOS VALUES ('AR02', 'DEPORTES', 'RAQUETA', 80.00, '2025-02-10', 'SI', 'USA');
```

## ⚙️ Instalación y Despliegue
Clonar el repositorio:
```
git clone https://github.com/tu-usuario/Web_de_Productos.git
```
Configurar el Servidor:

- Asegúrate de tener Apache Tomcat configurado en tu IDE.

- Configurar el recurso JNDI jdbc/Productos en el context.xml o server.xml de Tomcat apuntando a tu base de datos local.

Ejecutar:

- Desplegar el artefacto (war exploded) en Tomcat.

- Acceder a: http://localhost:8080/Web_de_Productos

📝 Autor
Gastón Armando Giorgio - Desarrollador Java Backend en formación.

Proyecto realizado como parte del curso de Java EE de Píldoras Informáticas (Juan Díaz).

