package com.pildorasinformaticas.productos;

import javax.sql.DataSource;
import java.sql.*;
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
 * @version 1.2
 */
public class ModeloProductos {

    /** Fuente de datos (Pool de conexiones) gestionada por el servidor. */
    private final DataSource origenDatos;

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
     * @throws SQLException Si ocurre un error de SQL.
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

    /**
     * Inserta un nuevo producto en la base de datos.
     * Usa try-with-resources para evitar fugas de memoria.
     *
     * @param nuevoProducto El objeto con los datos del formulario.
     * @throws SQLException Si falla la BBDD.
     */
    public void agregarElNuevoProducto(Productos nuevoProducto) throws SQLException {

        //----- 2. Creamos la instruccion sql para insertar el producto -----
        String sql = "INSERT INTO PRODUCTOS (CODIGOARTICULO, SECCION, NOMBREARTICULO, PRECIO, FECHA, IMPORTADO, PAISDEORIGEN)" +
                "VALUES(?,?,?,?,?,?,?)";

        try (
                //----- 1. Obtenemos la conexión a la BBDD -----
                Connection miConexion = origenDatos.getConnection();

                //----- 3a. Creamos la consulta preparada (PreparedStatement) -----
                PreparedStatement miStatement = miConexion.prepareStatement(sql)
        ) {

            //----- 3b. Establecemos los parámetros para el producto -----
            miStatement.setString(1, nuevoProducto.getcArt());
            miStatement.setString(2, nuevoProducto.getSeccion());
            miStatement.setString(3, nuevoProducto.getnArt());
            miStatement.setDouble(4, nuevoProducto.getPrecio());

            java.util.Date utilDate = nuevoProducto.getFecha();
            java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
            miStatement.setDate(5, fechaConvertida);

            miStatement.setString(6, nuevoProducto.getImportado());
            miStatement.setString(7, nuevoProducto.getpOrig());

            //----- 4. Ejecutamos la instruccion sql -----
            miStatement.execute();
        }
    }

    /**
     * Busca un producto específico por su código.
     * @param codArticulo El código del artículo a buscar.
     * @return El objeto Producto encontrado.
     * @throws Exception Si hay error SQL o no se encuentra el producto.
     */
    public Productos getProducto(String codArticulo) throws Exception {
        Productos elProducto = null;

        // SQL para buscar
        String sentenciaSql = "SELECT * FROM PRODUCTOS WHERE CODIGOARTICULO = ?";

        try (
                // 1. Establecemos la conexión
                Connection miConexion = origenDatos.getConnection();

                // 2. Creamos la consulta preparada usando esa conexión
                PreparedStatement miStatement = miConexion.prepareStatement(sentenciaSql)
        ) {

            // 3. Establecemos el parámetro
            miStatement.setString(1, codArticulo);

            // 4. Ejecutamos. IMPORTANTE: En try-with-resources, el ResultSet
            // idealmente debería estar en el paréntesis del try, pero así también funciona
            // porque al cerrar el Statement se cierra el ResultSet.
            try (ResultSet miResultset = miStatement.executeQuery()) {

                if (miResultset.next()) {
                    String seccion = miResultset.getString("SECCION");
                    String n_art = miResultset.getString("NOMBREARTICULO");
                    Double precio = miResultset.getDouble("PRECIO");
                    Date fecha = miResultset.getDate("FECHA");
                    String importado = miResultset.getString("IMPORTADO");
                    String p_orig = miResultset.getString("PAISDEORIGEN");

                    elProducto = new Productos(seccion, n_art, precio, fecha, importado, p_orig);
                } else {
                    throw new Exception("No hemos encontrado el producto con código articulo: " + codArticulo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        return elProducto;
    }
}