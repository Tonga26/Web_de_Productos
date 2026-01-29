package com.pildorasinformaticas.productos;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Clase que gestiona la lógica de negocio y acceso a datos para los Productos.
 * <p>
 * Actúa como el componente "Modelo" (DAO) en el patrón MVC.
 * Implementa try-with-resources para una gestión de memoria segura y automática.
 * </p>
 *
 * @author Gaston
 * @version 1.0
 */
public class ModeloProductos {

    /** Fuente de datos (Pool de conexiones) gestionada por el servidor. */
    private DataSource origenDatos;

    /**
     * Constructor del Modelo.
     * Recibe el DataSource mediante inyección de dependencias desde el Servlet.
     *
     * @param origenDatos El Pool de conexiones configurado en context.xml.
     */
    public ModeloProductos(DataSource origenDatos){
        this.origenDatos = origenDatos;
    }

    /**
     * Obtiene el listado completo de productos de la base de datos.
     * Cierra automáticamente la Conexión, el Statement y el ResultSet al terminar.
     *
     * @return Lista de objetos Productos.
     * @throws Exception Si ocurre un error de SQL.
     */
    public List<Productos> getProductos() throws SQLException {

        List<Productos> productos = new ArrayList<>();

        //----- 2. Crear la sentencia SQL (La definimos antes para usarla en el try) -----
        String sentenciaSql = "SELECT * FROM PRODUCTOS";

        try (
                //----- 1. Establecer la conexión -----
                Connection miConexion = origenDatos.getConnection();

                //----- 3. Crear el Statement -----
                Statement miStatement = miConexion.createStatement();

                // ----- 4. Ejecutar la sentencia SQL -----
                ResultSet miResultset = miStatement.executeQuery(sentenciaSql)
        ) {

            // ----- 5. Obtener el Resultset obtenido -----
            while (miResultset.next()){
                String c_art = miResultset.getString("CODIGOARTICULO");
                String seccion = miResultset.getString("SECCION");
                String n_art = miResultset.getString("NOMBREARTICULO");
                Double precio = miResultset.getDouble("PRECIO");
                Date fecha = miResultset.getDate("FECHA");
                String importado = miResultset.getString("IMPORTADO");
                String p_orig = miResultset.getString("PAISDEORIGEN");

                //----- 6. Crear el objeto de tipo Producto con los campos obtenidos de la BBDD -----
                Productos tempProd = new Productos(c_art, seccion, n_art, precio, fecha, importado, p_orig);

                //----- 7. Guardar los productos en la lista productos -----
                productos.add(tempProd);
            }
        }

        //----- 8. Retornar la lista con los productos ya creados -----
        return productos;
    }
}