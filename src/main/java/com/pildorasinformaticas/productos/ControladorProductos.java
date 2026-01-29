package com.pildorasinformaticas.productos;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.sql.DataSource;

/**
 * Servlet que actúa como CONTROLADOR en el patrón MVC (Modelo-Vista-Controlador).
 * <p>
 * Su responsabilidad es:
 * <ol>
 * <li>Recibir la petición del navegador (usuario).</li>
 * <li>Pedirle los datos a la clase {@link ModeloProductos} (Modelo).</li>
 * <li>Guardar esos datos en el request.</li>
 * <li>Enviar todo a la página JSP (Vista) para que se muestre.</li>
 * </ol>
 * </p>
 *
 * @author Gaston
 * @version 1.0
 */
@WebServlet("/ControladorProductos")
public class ControladorProductos extends HttpServlet {

    /**
     * Inyección del DataSource (Pool de conexiones) configurado en el servidor.
     * Tomcat se encarga de iniciar esto antes de que arranque el Servlet.
     */
    @Resource(name="jdbc/Productos")
    private DataSource miPool;

    /** Objeto auxiliar que contiene la lógica de base de datos. */
    private ModeloProductos modeloProductos;

    /**
     * Método del ciclo de vida del Servlet (se ejecuta una sola vez al inicio).
     * <p>
     * Se utiliza para inicializar el {@link ModeloProductos}.
     * Es necesario hacerlo aquí y no en el constructor, porque en el constructor
     * el 'miPool' todavía podría ser null (la inyección de dependencias ocurre después).
     * </p>
     *
     * @throws ServletException Si ocurre un error al inicializar el modelo.
     */
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
     * Maneja las peticiones HTTP GET enviadas al Servlet.
     * Orquesta el flujo de datos desde la Base de Datos hasta la Vista (JSP).
     *
     * @param request  La solicitud del navegador.
     * @param response La respuesta que enviaremos.
     * @throws ServletException Si hay error en el Servlet.
     * @throws IOException      Si hay error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Variable para almacenar la lista recuperada
        List<Productos> productos;

        try {
            // 1. Obtener la lista de producto desde el modelo
            productos = modeloProductos.getProductos();

            // 2. Agregar la lista de productos al request
            // "LISTAPRODUCTOS" es la etiqueta (key) que usará el JSP para leer los datos
            request.setAttribute("LISTAPRODUCTOS", productos);

            // 3. Enviar el Request a la pagina JSP
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/Lista_Productos.jsp");
            miDispatcher.forward(request, response);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
