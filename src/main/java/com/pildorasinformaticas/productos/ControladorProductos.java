package com.pildorasinformaticas.productos;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.sql.DataSource;

/**
 * Servlet que actúa como CONTROLADOR en el patrón MVC.
 * <p>
 * Gestiona el flujo de la aplicación recibiendo peticiones HTTP y delegando
 * la lógica de negocio al Modelo {@link ModeloProductos}.
 * </p>
 *
 * @author Gaston
 * @version 2.0 (Refactorizado con nomenclatura semántica estilo Spring)
 */
@WebServlet("/ControladorProductos")
public class ControladorProductos extends HttpServlet {

    /**
     * Inyección del DataSource (Pool de conexiones).
     */
    @Resource(name="jdbc/Productos")
    private DataSource miPool;

    /** Referencia al Modelo (Repositorio). */
    private ModeloProductos modeloProductos;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            modeloProductos = new ModeloProductos(miPool);
        } catch (Exception e){
            throw new ServletException(e);
        }
    }

    /**
     * Método principal que gestiona las peticiones GET.
     * Actúa como un "Dispatcher" o enrutador interno.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. Leer el comando
        String elComando = request.getParameter("instruccion");

        // 2. Carga por defecto
        if (elComando == null) elComando = "listar";

        // 3. Enrutamiento según la intención del usuario
        switch (elComando){
            case "listar":
                listarProductos(request, response);
                break;

            case "insertarBBDD":
                guardarProducto(request, response);
                break;

            case "cargar":
                try {
                    mostrarFormularioActualizar(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "actualizarBBDD":
                try {
                    actualizaProducto(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            default:
                listarProductos(request, response);
        }
    }

    // -------------------------------------------------------------------------
    // MÉTODOS AUXILIARES (HANDLERS)
    // -------------------------------------------------------------------------

    /**
     * Recibe los datos del formulario de inserción y solicita al modelo guardarlos.
     * <br>
     * <strong>Semántica:</strong> Equivale a un <code>save()</code> en un Controller de Spring.
     *
     * <p>// Antes llamado por Juan: agregarProductos</p>
     *
     * @param request Petición con los datos del nuevo producto.
     * @param response Respuesta para redireccionar.
     */
    private void guardarProducto(HttpServletRequest request, HttpServletResponse response) {
        // 1. Extraer parámetros del Request
        String codArticulo = request.getParameter("codigo_articulo");
        String seccion = request.getParameter("seccion");
        String nombreArticulo = request.getParameter("nombre_articulo");

        // 2. Parseo de fecha (String -> Date)
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = formatoFecha.parse(request.getParameter("fecha"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 3. Conversión de tipos numéricos
        Double precio = Double.parseDouble((request.getParameter("precio")));
        String importado = request.getParameter("importado");
        String paisOrigen = request.getParameter("pais_origen");

        // 4. Crear la entidad Producto
        Productos nuevoProducto = new Productos(codArticulo, seccion, nombreArticulo, precio, fecha, importado, paisOrigen);

        // 5. Invocar al Modelo para persistir los datos
        try {
            modeloProductos.guardar(nuevoProducto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 6. Redirigir a la lista para ver el cambio
        listarProductos(request, response);
    }

    /**
     * Recupera todos los productos y los envía a la vista principal.
     * <br>
     * <strong>Semántica:</strong> Equivale a un <code>findAll()</code> o <code>list()</code>.
     *
     * <p>// Antes llamado por Juan: obtenerProductos</p>
     */
    private void listarProductos(HttpServletRequest request, HttpServletResponse response) {
        List<Productos> productos;

        try {
            // 1. Pedir la lista al Modelo (Repositorio)
            productos = modeloProductos.buscarTodos();

            // 2. Cargar el atributo en el request (llamamos "LISTAPRODUCTOS" al arraylist "productos" que nos devuelve el modelo)
            request.setAttribute("LISTAPRODUCTOS", productos);

            // 3. Despachar al JSP
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/Lista_Productos.jsp");
            miDispatcher.forward(request, response);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Busca un producto por su ID y carga el formulario de edición con sus datos.
     * <br>
     * <strong>Semántica:</strong> Prepara la UI para editar (Show Edit Form).
     *
     * <p>// Antes llamado por Juan: cargaProductos</p>
     *
     * @throws Exception Si no encuentra el producto o hay error SQL.
     */
    private void mostrarFormularioActualizar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. Obtener el ID del producto seleccionado
        String codArticulo = request.getParameter("cArticulo");

        // 2. Buscar la entidad en la BBDD a través del Modelo
        Productos elProducto = modeloProductos.buscarPorId(codArticulo);

        // 3. Poner el producto en el "sobre" (Request) para que la vista lo lea
        request.setAttribute("ProductoActualizar", elProducto);

        // 4. Enviar al JSP específico de actualización
        RequestDispatcher dispatcher = request.getRequestDispatcher("/actualizarProducto.jsp");
        dispatcher.forward(request, response);
    }

    private void actualizaProducto(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        // 1. Leemos los datos que vienen del formulario actualizar
        String codArticulo = request.getParameter("CArt");
        String seccion = request.getParameter("seccion");
        String nombreArticulo = request.getParameter("nombre_articulo");

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = formatoFecha.parse(request.getParameter("fecha"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Double precio = Double.parseDouble((request.getParameter("precio")));
        String importado = request.getParameter("importado");
        String paisOrigen = request.getParameter("pais_origen");

        // 2. Creamos un objeto de tipo producto con la info del formulario
        Productos productoActualizado = new Productos(codArticulo, seccion, nombreArticulo, precio, fecha, importado, paisOrigen);

        // 3. Actualizamos la BBDD con la info del objeto producto
        try {
            modeloProductos.actualizarProducto(productoActualizado);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 4. Volvemos al listado con la info actualizada
        listarProductos(request, response);
    }
}